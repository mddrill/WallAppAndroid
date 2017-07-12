package com.wallapp.mddrill.wallappandroid.services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wallapp.mddrill.wallappandroid.networklayer.NetworkClient;

import java.util.HashMap;

import static com.wallapp.mddrill.wallappandroid.services.ServiceFactory.SericeTypes.ACCOUNTS_SERVICE;
import static com.wallapp.mddrill.wallappandroid.services.ServiceFactory.SericeTypes.POST_SERVICE;

/**
 * Created by mddrill on 7/10/17.
 */

public class ServiceFactory {

    public static class SericeTypes {
        public static final String ACCOUNTS_SERVICE = "ACCOUNTS_SERVICE";
        public static final String POST_SERVICE = "POST_SERVICE";
    }

    private interface ServiceBuilder {
        Service build(NetworkClient client);
    }

    private static final HashMap<String, ServiceBuilder> serviceBuilders = new HashMap<>();
    static {
        serviceBuilders.put(ACCOUNTS_SERVICE, new ServiceBuilder(){

            @Override
            public Service build(NetworkClient client) {
                return new AccountsService(client);
            }
        });
        serviceBuilders.put(POST_SERVICE, new ServiceBuilder(){

            @Override
            public Service build(NetworkClient client) {
                return new PostService(client);
            }
        });
    }

    public static Service getInstanceOf(String typeOfService, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        NetworkClient client = new NetworkClient(requestQueue);
        ServiceBuilder builder = serviceBuilders.get(typeOfService);
        return builder.build(client);
    }
}
