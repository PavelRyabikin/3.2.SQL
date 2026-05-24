package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}