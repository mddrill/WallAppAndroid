package com.wallapp.mddrill.wallappandroid.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.AccountServiceContract.Fields.EMAIL;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.AccountServiceContract.Fields.ID;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.AccountServiceContract.Fields.POSTS;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.AccountServiceContract.Fields.USERNAME;

/**
 * Created by mddrill on 7/10/17.
 */

public class Account {
    private String username;
    private String password;
    private String email;
    private int id;
    private ArrayList<String> posts;

    public Account(JSONObject json) throws JSONException{
        username = json.getString(USERNAME);
        email = json.getString(EMAIL);
        id = json.getInt(ID);
        posts = new ArrayList<>();
        // TODO: Add posts later...
//        jsonPosts = json.getJSONArray(POSTS);
//
    }
    public Account(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
