package com.wallapp.mddrill.wallappandroid.modeltests;

import com.wallapp.mddrill.wallappandroid.models.Post;

import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import static com.wallapp.mddrill.wallappandroid.testutils.TestUtils.TEST_AUTHOR_DATE_TAG;
import static com.wallapp.mddrill.wallappandroid.testutils.TestUtils.TEST_POST_TEXT;
import static com.wallapp.mddrill.wallappandroid.testutils.TestUtils.TEST_POST_TIME;
import static com.wallapp.mddrill.wallappandroid.testutils.TestUtils.TEST_USERNAME;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.AUTHOR;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.ID;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.POSTED_AT;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.TEXT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static com.wallapp.mddrill.wallappandroid.testutils.TestUtils.TEST_POST_ID;


public class PostTest {
    @Test
    public void constructor_formatsDateCorrectly() throws Exception {

        JSONObject json = Mockito.mock(JSONObject.class);
        when(json.getInt(ID)).thenReturn(TEST_POST_ID);
        when(json.getString(AUTHOR)).thenReturn(TEST_USERNAME);
        when(json.getString(POSTED_AT)).thenReturn(TEST_POST_TIME);
        when(json.getString(TEXT)).thenReturn(TEST_POST_TEXT);

        Post post = new Post(json);
        assertEquals(TEST_POST_ID, post.getId());
        assertEquals(TEST_POST_TEXT, post.getText());
        assertEquals(TEST_AUTHOR_DATE_TAG, post.getAuthorDateTag());
    }
}
