package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private Connection getConnection() throws Exception {
        Class.forName(cfg.getProperty("jdbc.driver"));
        String url = cfg.getProperty("jdbc.url");
        String username = cfg.getProperty("jdbc.username");
        String password = cfg.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s varchar(100), %s varchar(100));",
                tableName,
                "id serial primary key", "fullName", "email"
        );
        try (Statement stm = getConnection().createStatement()) {
            stm.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void isValid(String line) {
        String[] parts = line.split(";", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Wrong template");
        }
        if (line.startsWith(";")) {
            throw new IllegalArgumentException("Name or email is empty");
        }
        if (parts[1].isEmpty()) {
            throw new IllegalArgumentException("email is empty");
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            String line;
            while ((line = rd.readLine()) != null) {
                isValid(line);
                String[] parts = line.split(";", 2);
                users.add(new User(parts[0], parts[1]));
            }
        }
        return users;
    }

    public void save(List<User> users) {
        try (Connection cnt = getConnection()) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO spammers (fullName, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("prepadd.properties")) {
            cfg.load(in);
            ImportDB db = new ImportDB(cfg, "C:/Projects/job4j_design/data/dump.txt");
            db.createTable("spammers");
            List<User> users = db.load();
            db.save(users);
        }
    }
}