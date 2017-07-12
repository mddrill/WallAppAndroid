package com.wallapp.mddrill.wallappandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wallapp.mddrill.wallappandroid.models.Post;
import com.wallapp.mddrill.wallappandroid.R;
import com.wallapp.mddrill.wallappandroid.services.PostService;
import com.wallapp.mddrill.wallappandroid.services.ServiceFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static com.wallapp.mddrill.wallappandroid.services.ServiceFactory.SericeTypes.POST_SERVICE;
import static com.wallapp.mddrill.wallappandroid.utils.Utils.ExtraKeys.POST_AUTHOR_DATE_TAG;
import static com.wallapp.mddrill.wallappandroid.utils.Utils.ExtraKeys.POST_CONTENT;
import static com.wallapp.mddrill.wallappandroid.utils.Utils.ExtraKeys.POST_ID;

public class WallActivity extends BaseActivity {
    ListView listView;
    private static WallAdapter adapter;
    private Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(onFABClickListener);

        listView = (ListView) findViewById(R.id.posts_list_view);

        PostService postService = (PostService) ServiceFactory.getInstanceOf(POST_SERVICE, this);
        postService.getAll(
                    new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            ArrayList<Post> posts = new ArrayList<Post>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject json = null;
                                try {
                                    json = response.getJSONObject(i);
                                    posts.add(new Post(json));
                                } catch (JSONException e) {
                                    popUpInvalidResponseError();
                                }
                                adapter = new WallAdapter(posts, getApplicationContext());
                                listView.setOnItemClickListener(onPostClickListener);
                                listView.setAdapter(adapter);
                            }
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            popUpNetworkError(error);
                        }
                    }
            );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wall, menu);
        menu.findItem(R.id.logout_button).setVisible(false);
        optionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.login_button:
                item.setVisible(false);
                MenuItem logoutButton = optionsMenu.findItem(R.id.logout_button);
                logoutButton.setVisible(true);
                startActivity(new Intent(WallActivity.this, LoginActivity.class));
                return true;
            case R.id.logout_button:
                item.setVisible(false);
                MenuItem loginButton = optionsMenu.findItem(R.id.login_button);
                loginButton.setVisible(true);
                finish();
                startActivity(getIntent());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onFABClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(WallActivity.this, WritePostActivity.class));
        }
    };

    private AdapterView.OnItemClickListener onPostClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Intent i = new Intent(WallActivity.this, PostDetailActivity.class);
            i.putExtra(POST_AUTHOR_DATE_TAG, adapter.getPostSet().get(position).getAuthorDateTag());
            i.putExtra(POST_CONTENT, adapter.getPostSet().get(position).getText());
            i.putExtra(POST_ID, adapter.getPostSet().get(position).getId());
            startActivity(i);
        }
    };
}
