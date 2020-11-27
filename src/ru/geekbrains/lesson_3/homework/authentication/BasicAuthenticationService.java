package ru.geekbrains.lesson_3.homework.authentication;

import ru.geekbrains.lesson_3.homework.db.ClientService;
import ru.geekbrains.lesson_3.homework.entity.User;

import java.util.Optional;

public class BasicAuthenticationService implements AuthenticationService {

    private static ClientService service = new ClientService();

    @Override
    public Optional<User> doAuthentication(String email, String password) {
        User user = service.getUserByEmailAndPassword(email, password);
        return Optional.of(user);
    }
}
