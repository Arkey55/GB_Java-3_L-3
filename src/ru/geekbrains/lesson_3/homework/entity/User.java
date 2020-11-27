package ru.geekbrains.lesson_3.homework.entity;

public class User {
    private int id;
    private String nickname;
    private String email;
    private String password;

    public User(int id, String nickname, String email, String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", nickname='" + nickname + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                '}' + "\n";
//    }
}
