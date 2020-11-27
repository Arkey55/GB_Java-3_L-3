package ru.geekbrains.lesson_3.test;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
