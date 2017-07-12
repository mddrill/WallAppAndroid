package com.wallapp.mddrill.wallappandroid.models;

import com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mddrill on 7/9/17.
 */

public class Token {
    private String value;

    public Token(JSONObject json) throws JSONException {
        value = json.getString(ServiceContracts.AccountServiceContract.Fields.TOKEN);
    }

    public String getValue() {
        return value;
    }
}
