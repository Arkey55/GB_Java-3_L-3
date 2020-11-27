package ru.geekbrains.lesson_3.homework.server;

import ru.geekbrains.lesson_3.homework.authentication.AuthenticationService;
import ru.geekbrains.lesson_3.homework.db.Service;

public interface Server {
    void broadcastMsg(String message);
    void sendPrivateMessage(ClientHandler sender, String nickname, String message);
    boolean isLoggedIn(String nickname);
    void subscribe(ClientHandler client);
    void  unsubscribe(ClientHandler client);
    AuthenticationService getAuthenticationService();
    Service getChangeNickname();
}
