package ru.geekbrains.lesson_3.homework;

import ru.geekbrains.lesson_3.homework.apps.ClientApp;

import java.io.File;

public class ClientTwo {
    public static void main(String[] args) {
        File file = new File("./src/ru/geekbrains/lesson_3/homework/logs/log_ClientTwo.txt");
        ClientApp clientApp = new ClientApp(file);
    }
}
