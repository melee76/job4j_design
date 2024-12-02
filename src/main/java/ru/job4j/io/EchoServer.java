package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstline = in.readLine();
                    if (firstline != null && !firstline.isEmpty()) {
                        String[] parts = firstline.split("=");
                        String key = parts[1].split(" ")[0];
                        if ("Exit".equals(key)) {
                            server.close();
                        } else if ("Hello".equals(key)) {
                            out.write("Hello, dear friend.".getBytes());
                        } else {
                            out.write(key.getBytes());
                        }
                    }
                    out.flush();
                } catch (IOException e) {
                    LOG.error("Exception during socket IO operations", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Exception during ServerSocket operations", e);
        }
    }
}