package ru.geekbrains.lesson_3.homework.apps;

import ru.geekbrains.lesson_3.homework.logs.HistoryLog;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    public ClientApp(File file) {
        try {
            HistoryLog log = new HistoryLog(file);
            log.readFromLog();
            Socket socket = new Socket("localhost", 8080);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try {
                    while (true){
                        String msg = in.readUTF();
                        log.writeToLog(msg);
                        System.out.println(msg);
                    }
                } catch (IOException e){
                    throw new RuntimeException("SWW in ClientApp during receive message", e);
                }
            }).start();
            Scanner scanner = new Scanner(System.in);
            while (true){
                try {
                    System.out.println("...");
                    out.writeUTF(scanner.nextLine());

                } catch (IOException e) {
                    throw new RuntimeException("SWW in ClientApp during sending message", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW in ClientApp", e);
        }
    }
}
