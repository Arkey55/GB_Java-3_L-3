package ru.geekbrains.lesson_3.homework.db;

import ru.geekbrains.lesson_3.homework.entity.User;

public class CreateDB {
    public static void main(String[] args) {
        Service<User> clients = new ClientService();
        User user1 = new User(0, "nick1", "email1", "pass1");
        User user2 = new User(0, "nick2", "email2", "pass2");
        User user3 = new User(0, "nick3", "email3", "pass3");

        clients.saveInDB(user1);
        clients.saveInDB(user2);
        clients.saveInDB(user3);
    }
}
