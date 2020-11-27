package ru.geekbrains.lesson_3.homework;

import ru.geekbrains.lesson_3.homework.apps.ClientApp;

import java.io.File;

public class ClientThree {
    public static void main(String[] args) {
        File file = new File("./src/ru/geekbrains/lesson_3/homework/logs/log_ClientThree.txt");
        ClientApp clientApp = new ClientApp(file);
    }
}
