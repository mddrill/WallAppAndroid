package com.wallapp.mddrill.wallappandroid.services;

import com.android.volley.Response;
import com.wallapp.mddrill.wallappandroid.models.Post;
import com.wallapp.mddrill.wallappandroid.networklayer.NetworkClient;
import com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts;
import com.wallapp.mddrill.wallappandroid.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mddrill on 7/9/17.
 */

public class PostService implements Service<Post>{

    NetworkClient client;

    public PostService(NetworkClient client){
        this.client = client;
    }

    @Override
    public void getAll(Response.Listener<JSONArray> onSuccess, Response.ErrorListener onError){
        client.getList(endpoint(), onSuccess, onError);
    }

    @Override
    public void get(int id, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError) {
        client.get(endpointForPostNumber(id), onSuccess, onError);
    }

    @Override
    public void create(Post post, Response.Listener onSuccess, Response.ErrorListener onError) {
        JSONObject params = new JSONObject();
        try {
            params.put(ServiceContracts.PostServiceContract.Fields.TEXT, post.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.post(endpoint(), params, onSuccess, onError);
    }

    @Override
    public void update(Post post, int id, Response.Listener onSuccess, Response.ErrorListener onError) {
        JSONObject params = new JSONObject();
        try {
            params.put(ServiceContracts.PostServiceContract.Fields.TEXT, post.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.patch(endpointForPostNumber(id), params, onSuccess, onError);
    }

    @Override
    public void delete(int id, Response.Listener onSuccess, Response.ErrorListener onError) {
        client.delete(endpointForPostNumber(id), onSuccess, onError);
    }

    // Endpoints
    private static final String ENDPOINT = "/post/";

    private static String endpoint(){
        return Utils.URLS.SERVER_ADDRESS + ENDPOINT + "/";
    }

    private static String endpointForPostNumber(int id){
        return Utils.URLS.SERVER_ADDRESS + ENDPOINT + String.valueOf(id) + "/";
    }
}
