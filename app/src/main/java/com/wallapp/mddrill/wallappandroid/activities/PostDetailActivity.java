package com.wallapp.mddrill.wallappandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wallapp.mddrill.wallappandroid.R;
import com.wallapp.mddrill.wallappandroid.models.Post;
import com.wallapp.mddrill.wallappandroid.services.PostService;
import com.wallapp.mddrill.wallappandroid.services.ServiceFactory;

import static com.wallapp.mddrill.wallappandroid.services.ServiceFactory.SericeTypes.POST_SERVICE;
import static com.wallapp.mddrill.wallappandroid.utils.Utils.ExtraKeys.POST_AUTHOR_DATE_TAG;
import static com.wallapp.mddrill.wallappandroid.utils.Utils.ExtraKeys.POST_CONTENT;
import static com.wallapp.mddrill.wallappandroid.utils.Utils.ExtraKeys.POST_ID;

public class PostDetailActivity extends BaseActivity {
    String authorDateTag;
    String postContent;
    int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        Bundle extras = getIntent().getExtras();
        authorDateTag = extras.getString(POST_AUTHOR_DATE_TAG);
        postContent = extras.getString(POST_CONTENT);
        postId = extras.getInt(POST_ID);

        TextView authorDateTextView = (TextView) findViewById(R.id.author_date_tag_detail);
        authorDateTextView.setText(authorDateTag);
        TextView postContentTextView = (TextView) findViewById(R.id.post_content_detail);
        postContentTextView.setText(postContent);
        postContentTextView.setMovementMethod(new ScrollingMovementMethod());

        Button editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(onEditButtonClickListener);
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(onDeleteButtonClickListener);

        Button doneButton = (Button) findViewById(R.id.done_button);
        doneButton.setOnClickListener(onDoneButtonClickListener);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(onCancelButtonClickListener);
    }
    private View.OnClickListener onEditButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText postContentTextViewEditable = (EditText) findViewById(R.id.post_content_detail_editable);
            postContentTextViewEditable.setText(postContent);
            ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.edit_post_content_switcher);
            switcher.showNext();
            switcher = (ViewSwitcher) findViewById(R.id.edit_to_done_button_switcher);
            switcher.showNext();
            switcher = (ViewSwitcher) findViewById(R.id.delete_to_cancel_button);
            switcher.showNext();
        }
    };

    private View.OnClickListener onDeleteButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            PostService postService = (PostService) ServiceFactory.getInstanceOf(POST_SERVICE, PostDetailActivity.this);
            postService.delete(postId, new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            startActivity(new Intent(PostDetailActivity.this, WallActivity.class));
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            popUpNetworkError(error);
                        }
                    });
        }
    };
    private View.OnClickListener onDoneButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText postContentTextViewEditable = (EditText) findViewById(R.id.post_content_detail_editable);
            postContent = postContentTextViewEditable.toString();

            PostService postService = (PostService) ServiceFactory.getInstanceOf(POST_SERVICE, PostDetailActivity.this);
            postService.update(new Post(postContent), postId,
                    new Response.Listener(){

                @Override
                public void onResponse(Object response) {
                    startActivity(new Intent(PostDetailActivity.this, WallActivity.class));
                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    popUpNetworkError(error);
                }
            });
        }
    };

    private View.OnClickListener onCancelButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.edit_post_content_switcher);
            switcher.showPrevious();
            switcher = (ViewSwitcher) findViewById(R.id.edit_to_done_button_switcher);
            switcher.showPrevious();
            switcher = (ViewSwitcher) findViewById(R.id.delete_to_cancel_button);
            switcher.showPrevious();
        }
    };
}
