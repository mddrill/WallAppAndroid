package com.wallapp.mddrill.wallappandroid.services;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mddrill on 7/10/17.
 */

public interface Service<T> {
    public void create(T obj, Response.Listener onSuccess, Response.ErrorListener onError);
    public void get(int id, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError);
    public void update(T obj, int id, Response.Listener onSuccess, Response.ErrorListener onError);
    public void delete(int id, Response.Listener onSuccess, Response.ErrorListener onError);
    public void getAll(Response.Listener<JSONArray> onSuccess, Response.ErrorListener onError);

}
