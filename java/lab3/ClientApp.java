package org.example;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class ClientApp {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final Logger LOGGER = Logger.getLogger(ClientApp.class.getName());

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            LOGGER.info("Connected to server on " + HOST + ":" + PORT);

            String readySignal = waitForReadySignal(in);
            LOGGER.info("Server says: " + readySignal);

            int n = readNumberOfMessages(scanner);
            out.writeObject(n);

            String readyForMessagesSignal = waitForReadyForMessagesSignal(in);
            LOGGER.info("Server says: " + readyForMessagesSignal);

            sendMessages(out, n);

            String finishedSignal = waitForFinishedSignal(in);
            LOGGER.info("Server says: " + finishedSignal);

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            LOGGER.severe("Client error: " + e.getMessage());
        }
    }

    private static String waitForReadySignal(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return (String) in.readObject();
    }

    private static int readNumberOfMessages(Scanner scanner) {
        int n;
        do {
            LOGGER.info("Enter a positive number: ");
            while (!scanner.hasNextInt()) {
                LOGGER.warning("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            n = scanner.nextInt();
        } while (n <= 0);
        return n;
    }

    private static String waitForReadyForMessagesSignal(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return (String) in.readObject();
    }

    private static void sendMessages(ObjectOutputStream out, int n) throws IOException, InterruptedException {
        for (int i = 0; i < n; i++) {
            LOGGER.info("Enter message " + (i + 1) + ": ");
            Message message = new Message();
            message.setNumber(i + 1);
            message.setContent(String.valueOf(i));
            out.writeObject(message);
            Thread.sleep(new Random().nextInt(3000) + 1000);
        }
    }

    private static String waitForFinishedSignal(ObjectInputStream in) throws IOException, ClassNotFoundException {
        return (String) in.readObject();
    }
}
