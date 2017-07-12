package com.wallapp.mddrill.wallappandroid.models;

/**
 * Created by mddrill on 7/9/17.
 */

public class CurrentUser {
    //TODO: fix this mess...
    public static boolean isLoggedIn(){
        return token != null;
    }
    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        CurrentUser.token = token;
    }
    private static String token;
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        CurrentUser.username = username;
    }
}
