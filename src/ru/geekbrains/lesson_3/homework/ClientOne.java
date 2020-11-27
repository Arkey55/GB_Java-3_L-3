package ru.geekbrains.lesson_3.homework;

import ru.geekbrains.lesson_3.homework.apps.ClientApp;

import java.io.File;

public class ClientOne {
    public static void main(String[] args) {
        File file = new File("./src/ru/geekbrains/lesson_3/homework/logs/log_ClientOne.txt");
        ClientApp clientApp = new ClientApp(file);
    }
}
