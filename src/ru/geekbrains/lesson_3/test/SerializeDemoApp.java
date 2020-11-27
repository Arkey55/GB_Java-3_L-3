package ru.geekbrains.lesson_3.test;

import java.io.*;

public class SerializeDemoApp {
    public static void main(String[] args) {
        Student studentOneOut = new Student(1, "Bob", 80);
        Student studentTwoOut = new Student(2, "Bill", 70);
        Book jungleBook = new Book("Jungle Book");
        studentOneOut.setBook(jungleBook);
        studentTwoOut.setBook(jungleBook);

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("students.ser")
        )){
            out.writeObject(studentOneOut);
            out.writeObject(studentTwoOut);
        } catch (IOException e){
            e.printStackTrace();
        }

        Student studentOneIn = null;
        Student studentTwoIn = null;

        try (ObjectInputStream objIn = new ObjectInputStream(
                new FileInputStream("students.ser")
        )){
            studentOneIn = (Student) objIn.readObject();
            studentTwoIn = (Student) objIn.readObject();
            studentOneIn.info();
            studentTwoIn.info();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(studentOneIn.getBook());
        System.out.println(studentTwoIn.getBook());
    }
}
