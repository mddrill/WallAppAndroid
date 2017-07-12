package com.wallapp.mddrill.wallappandroid.servicetests;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wallapp.mddrill.wallappandroid.models.Post;
import com.wallapp.mddrill.wallappandroid.networklayer.NetworkClient;
import com.wallapp.mddrill.wallappandroid.services.PostService;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.TEXT;
import static com.wallapp.mddrill.wallappandroid.testutils.TestUtils.TEST_POST_TEXT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by mddrill on 7/10/17.
 */

public class PostServiceTest {
    @Test
    public void create_callsClientPostMethodWithCallbacks(){

        final NetworkClient mockClient = Mockito.mock(NetworkClient.class);
        PostService postService = new PostService(mockClient);

        Post testPost = Mockito.mock(Post.class);
        when(testPost.getText()).thenReturn(TEST_POST_TEXT);

        JSONObject expectedParams = new JSONObject();
//        try {
////            expectedParams.put(TEXT, TEST_POST_TEXT);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        Response.Listener expectedOnSuccessCallback = new Response.Listener(){
            @Override
            public void onResponse(Object response) {
            }
        };
        Response.ErrorListener expectedOnErrorCallback = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };

        postService.create(testPost, expectedOnSuccessCallback, expectedOnErrorCallback);
        verify(mockClient).post("http://10.0.2.2/post/", expectedParams, expectedOnSuccessCallback, expectedOnErrorCallback);
    }
}
