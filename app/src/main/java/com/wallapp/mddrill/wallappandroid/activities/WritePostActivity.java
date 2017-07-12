package com.wallapp.mddrill.wallappandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wallapp.mddrill.wallappandroid.models.CurrentUser;
import com.wallapp.mddrill.wallappandroid.R;
import com.wallapp.mddrill.wallappandroid.models.Post;
import com.wallapp.mddrill.wallappandroid.services.PostService;
import com.wallapp.mddrill.wallappandroid.services.ServiceFactory;
import com.wallapp.mddrill.wallappandroid.utils.Utils.RequestCodes;

import static com.wallapp.mddrill.wallappandroid.services.ServiceFactory.SericeTypes.POST_SERVICE;


public class WritePostActivity extends BaseActivity {

    EditText mWritePostEditView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        Button sendButton = (Button) findViewById(R.id.send_post_button);
        sendButton.setOnClickListener(onSendButtonClickListener);

        mWritePostEditView = (EditText) findViewById(R.id.write_something_editview);
    }

    private View.OnClickListener onSendButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (CurrentUser.isLoggedIn()) {
                tryToSendPost();
            }
            else {
                startActivityForResult(new Intent(WritePostActivity.this, LoginActivity.class), RequestCodes.WRITE_TO_LOGIN);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RequestCodes.WRITE_TO_LOGIN:
                tryToSendPost();
        }
    }
    private void tryToSendPost(){
        String text = mWritePostEditView.toString();
        PostService postService = (PostService) ServiceFactory.getInstanceOf(POST_SERVICE, this);
        postService.create(new Post(text),
                new Response.Listener(){

                    @Override
                    public void onResponse(Object response) {
                        startActivity(new Intent(WritePostActivity.this, WallActivity.class));
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        popUpNetworkError(error);
                    }
                });

    }
}
