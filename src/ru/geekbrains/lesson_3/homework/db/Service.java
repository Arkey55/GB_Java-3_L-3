package ru.geekbrains.lesson_3.homework.db;


import java.util.List;

public interface Service<T> {
    T getUserByEmailAndPassword(String email, String password);
    List<T> findAll();
    int saveInDB(T obj);
    int doChangeNickname(int id, String newName);
}
