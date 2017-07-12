package com.wallapp.mddrill.wallappandroid.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wallapp.mddrill.wallappandroid.models.Post;
import com.wallapp.mddrill.wallappandroid.R;

import java.util.ArrayList;

public class WallAdapter extends ArrayAdapter<Post> {

    private ArrayList<Post> postSet;

    WallAdapter(ArrayList<Post> posts, Context context) {
        super(context, R.layout.wall_adapter, posts);
        this.postSet = posts;
    }

    @Override
    public int getCount() {
        return this.postSet.size();
    }

    @Override
    public Post getItem(int i) {
        return this.postSet.get(i);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Post post = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.wall_adapter, parent, false);
        TextView authorDateTextView = (TextView) convertView.findViewById(R.id.author_date_tag);
        TextView postContentTextView = (TextView) convertView.findViewById(R.id.post_content);


        authorDateTextView.setText(post.getAuthorDateTag());
        postContentTextView.setText(post.getText());
        // Return the completed view to render on screen
        return convertView;
    }

    public ArrayList<Post> getPostSet() {
        return postSet;
    }
}
