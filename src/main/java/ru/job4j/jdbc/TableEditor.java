package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;
import java.sql.DriverManager;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = getConnection(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(Properties properties) throws Exception {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

    public void createStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s);",
                tableName,
                "id serial primary key"
        );
        createStatement(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        createStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type
        );
        createStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        );
        createStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        createStatement(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (TableEditor te = new TableEditor(config)) {
                te.createTable("testTable");
                System.out.println(te.getTableScheme("testTable"));
                te.addColumn("testTable", "testColumn", "Text");
                System.out.println(te.getTableScheme("testTable"));
                te.renameColumn("testTable", "testColumn", "newTestColumn");
                System.out.println(te.getTableScheme("testTable"));
                te.dropColumn("testTable", "newTestColumn");
                System.out.println(te.getTableScheme("testTable"));
                te.dropTable("testTable");
                System.out.println(te.getTableScheme("testTable"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}