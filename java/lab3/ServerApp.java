package org.example;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ServerApp {
    private static final int PORT = 8080;
    private static final String READY_STATE_MESSAGE = "ready";
    private static final String READY_FOR_MESSAGES_STATE_MESSAGE = "ready for messages";
    private static final String FINISHED_STATE_MESSAGE = "finished";
    private static final Logger LOGGER = Logger.getLogger(ServerApp.class.getName());
    private static final AtomicInteger CLIENT_ID_COUNTER = new AtomicInteger(1);
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        try {
            ServerSocket serverSocket = startServer();
            handleClientConnections(serverSocket, executor);
        } catch (IOException e) {
            LOGGER.severe("Server error: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    private static ServerSocket startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT, 50);
        LOGGER.info("Server started on port " + PORT);
        return serverSocket;
    }

    private static void handleClientConnections(ServerSocket serverSocket, ExecutorService executor) {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                LOGGER.info("New client connected: " + clientSocket.getInetAddress());
                int clientId = CLIENT_ID_COUNTER.getAndIncrement();
                LOGGER.info("ClientID granted: " + clientId);
                executor.submit(() -> handleClient(clientSocket, clientId));
            } catch (IOException e) {
                LOGGER.severe("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    private static void handleClient(Socket clientSocket, int clientId) {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            sendReadyStateMessage(out);

            int n = (int) in.readObject();
            LOGGER.info("Received n: " + n + " from client: " + clientId);

            sendReadyForMessagesStateMessage(out);

            receiveAndProcessMessages(in, clientId, n);

            sendFinishedStateMessage(out);

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.severe("Error handling client: " + e.getMessage());
        }
    }

    private static void sendReadyStateMessage(ObjectOutputStream out) throws IOException {
        out.writeObject(READY_STATE_MESSAGE);
    }

    private static void sendReadyForMessagesStateMessage(ObjectOutputStream out) throws IOException {
        out.writeObject(READY_FOR_MESSAGES_STATE_MESSAGE);
    }

    private static void receiveAndProcessMessages(ObjectInputStream in, int clientId, int messagesCount)
            throws IOException, ClassNotFoundException {
        for (int i = 0; i < messagesCount; i++) {
            Message message = (Message) in.readObject();
            LOGGER.info("Received message no: " + message.getNumber() + " of content: " + message.getContent()
                    + " from client: " + clientId);
        }
    }

    private static void sendFinishedStateMessage(ObjectOutputStream out) throws IOException {
        out.writeObject(FINISHED_STATE_MESSAGE);
    }
}
