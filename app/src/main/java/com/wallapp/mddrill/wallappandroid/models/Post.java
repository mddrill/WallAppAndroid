package com.wallapp.mddrill.wallappandroid.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.AUTHOR;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.POSTED_AT;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.ID;
import static com.wallapp.mddrill.wallappandroid.networklayer.ServiceContracts.PostServiceContract.Fields.TEXT;

/**
 * Created by mddrill on 7/8/17.
 */

public class Post {
    private int id;
    private String authorDateTag;
    private String text;

    public Post(JSONObject json) throws JSONException {
        this.id = json.getInt(ID);
        String unformattedDateTime = json.getString(POSTED_AT);
        DateTime dt = new DateTime(unformattedDateTime);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                        .appendClockhourOfHalfday(1)
                                        .appendLiteral(':')
                                        .appendMinuteOfHour(2)
                                        .appendLiteral(' ')
                                        .appendHalfdayOfDayText()
                                        .appendLiteral(" on ")
                                        .appendMonthOfYearShortText()
                                        .appendLiteral(' ')
                                        .appendDayOfMonth(1)
                                        .appendLiteral(", ")
                                        .appendYear(1, 4)
                                        .toFormatter();
        String formattedDateTime = formatter.print(dt);
        this.authorDateTag = json.getString(AUTHOR) + " posted this at " + formattedDateTime;
        this.text = json.getString(TEXT);
    }

    public Post(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getAuthorDateTag() {
        return authorDateTag;
    }

    public String getText() {
        return text;
    }
}
