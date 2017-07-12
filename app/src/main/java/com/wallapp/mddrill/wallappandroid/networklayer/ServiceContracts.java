package com.wallapp.mddrill.wallappandroid.networklayer;

/**
 * Created by mddrill on 7/9/17.
 */

public class ServiceContracts {
    public static class PostServiceContract {
        public static class Fields {
            public static final String TEXT = "text";
            public static final String ID = "id";
            public static final String POSTED_AT = "posted_at";
            public static final String AUTHOR = "author";
        }
    }
    public static class AccountServiceContract {
        public static class Fields {
            public static final String USERNAME = "username";
            public static final String TOKEN = "token";
            public static final String PASSWORD = "password";
            public static final String EMAIL = "email";
            public static final String POSTS = "posts";
            public static final String ID = "id";
        }
    }
    public static class Headers {
        public static final String AUTHORIZATION = "Authorization";
    }
}
