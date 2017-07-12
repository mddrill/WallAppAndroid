package com.wallapp.mddrill.wallappandroid.services;



import com.android.volley.Response;
import com.wallapp.mddrill.wallappandroid.models.Account;
import com.wallapp.mddrill.wallappandroid.networklayer.NetworkClient;
import com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.AccountServiceContract;
import com.wallapp.mddrill.wallappandroid.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by mddrill on 7/9/17.
 */

public class AccountsService implements Service<Account>{

    NetworkClient client;

    AccountsService(NetworkClient client){
        this.client = client;
    }

    public void login(Account account, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError){
        JSONObject params = new JSONObject();
        try {
            params.put(AccountServiceContract.Fields.USERNAME, account.getUsername());
            params.put(AccountServiceContract.Fields.PASSWORD, account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        client.post(loginEndpoint(), params, onSuccess, onError);
    }

    @Override
    public void get(int id, Response.Listener<JSONObject> onSuccess, Response.ErrorListener onError) {
        client.get(endpointForAccountNumber(id), onSuccess, onError);
    }

    @Override
    public void create(Account account, Response.Listener onSuccess, Response.ErrorListener onError) {
        JSONObject params = new JSONObject();
        try {
            params.put(AccountServiceContract.Fields.USERNAME, account.getUsername());
            params.put(AccountServiceContract.Fields.PASSWORD, account.getPassword());
            params.put(AccountServiceContract.Fields.EMAIL, account.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        client.post(endpoint(), params, onSuccess, onError);
    }

    @Override
    public void update(Account account, int id, Response.Listener onSuccess, Response.ErrorListener onError) {
        JSONObject params = new JSONObject();
        try {
            params.put(AccountServiceContract.Fields.EMAIL, account.getEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.patch(endpointForAccountNumber(id), params, onSuccess, onError);
    }

    @Override
    public void delete(int id, Response.Listener onSuccess, Response.ErrorListener onError) {
        client.delete(endpointForAccountNumber(id), onSuccess, onError);
    }

    @Override
    public void getAll(Response.Listener<JSONArray> onSuccess, Response.ErrorListener onError) {
        throw new UnsupportedOperationException("Didn't implement getAll in AccountsService");
    }

    //Endpoints
    private static final String ENDPOINT = "/accounts/";

    private static final String LOGIN_ENDPOINT = "/api-token-auth/";

    private static String endpoint(){
        return Utils.URLS.SERVER_ADDRESS + ENDPOINT + "/";
    }

    private static String loginEndpoint() {
        return Utils.URLS.SERVER_ADDRESS + LOGIN_ENDPOINT + "/";
    }

    private static String endpointForAccountNumber(int id){
        return Utils.URLS.SERVER_ADDRESS + ENDPOINT + String.valueOf(id) + "/";
    }
}
