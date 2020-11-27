package ru.geekbrains.lesson_3.test;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int score;
    private Book book;

    public Student(int id, String name, int score) {
        System.out.println("Student class constructor");
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public void info(){
        System.out.println(String.format("%d %s %d", id, name, score));
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
