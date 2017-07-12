package com.wallapp.mddrill.wallappandroid.networklayer;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wallapp.mddrill.wallappandroid.models.CurrentUser;
import com.wallapp.mddrill.wallappandroid.models.Post;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mddrill on 7/8/17.
 */

public class NetworkClient {

    private RequestQueue requestQueue;
    Post post;

    public NetworkClient(RequestQueue requestQueue){
        this.requestQueue = requestQueue;
    }

    public void getList(String endpoint, Response.Listener<JSONArray> onSuccess, Response.ErrorListener onError) {
        JsonArrayRequest jsArrayRequest;
        if (CurrentUser.isLoggedIn()) {
            jsArrayRequest = new JsonArrayRequest(endpoint, onSuccess, onError){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put(ServiceContracts.Headers.AUTHORIZATION, "Token " + CurrentUser.getToken());
                    return headers;
                }
            };
        }
        else {
            jsArrayRequest = new JsonArrayRequest(endpoint, onSuccess, onError);
        }

        requestQueue.add(jsArrayRequest);

    }

    public void get(String endpoint, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError){
        JsonObjectRequest jsObjRequest;
        if (CurrentUser.isLoggedIn()) {
            jsObjRequest = new JsonObjectRequest(Request.Method.GET, endpoint, null, onSuccess, onError){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put(ServiceContracts.Headers.AUTHORIZATION, "Token " + CurrentUser.getToken());
                    return headers;
                }
            };
        }
        else {
            jsObjRequest = new JsonObjectRequest(Request.Method.GET, endpoint, null, onSuccess, onError);
        }

        requestQueue.add(jsObjRequest);
    }

    public void post(String endpoint, JSONObject params, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError){
        JsonObjectRequest jsObjRequest;
        if (CurrentUser.isLoggedIn()) {
            jsObjRequest = new JsonObjectRequest(Request.Method.PUT, endpoint, params, onSuccess, onError) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put(ServiceContracts.Headers.AUTHORIZATION, "Token " + CurrentUser.getToken());
                    return headers;
                }
            };
        }
        else {
            jsObjRequest = new JsonObjectRequest(Request.Method.PUT, endpoint, params, onSuccess, onError);
        }

        requestQueue.add(jsObjRequest);
    }

    public void patch(String endpoint, JSONObject params, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError){
        JsonObjectRequest jsObjRequest;
        if (CurrentUser.isLoggedIn()) {
            jsObjRequest = new JsonObjectRequest(Request.Method.PATCH, endpoint, params, onSuccess, onError) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put(ServiceContracts.Headers.AUTHORIZATION, "Token " + CurrentUser.getToken());
                    return headers;
                }
            };
        }
        else {
            jsObjRequest = new JsonObjectRequest(Request.Method.PATCH, endpoint, params, onSuccess, onError);
        }

        requestQueue.add(jsObjRequest);
    }

    public void delete(String endpoint, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError) {
        JsonObjectRequest jsObjRequest;
        if (CurrentUser.isLoggedIn()) {
            jsObjRequest = new JsonObjectRequest(Request.Method.DELETE, endpoint, null, onSuccess, onError) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put(ServiceContracts.Headers.AUTHORIZATION, "Token " + CurrentUser.getToken());
                    return headers;
                }
            };
        }
        else {
            jsObjRequest = new JsonObjectRequest(Request.Method.DELETE, endpoint, null, onSuccess, onError);
        }

        requestQueue.add(jsObjRequest);
    }
}
