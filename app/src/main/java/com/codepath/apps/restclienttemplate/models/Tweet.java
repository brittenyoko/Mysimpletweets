package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


@Parcel
public class Tweet {

    // list out the atttributes
    public String body;
    public long uid;  // database ID for the tweet
    public User user;
    public String createdAt;
    public String likes;
    public String retweets;

    // derialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //extract the values from JSON
        tweet.body = jsonObject.getString("text");

        tweet.uid = jsonObject.getLong("id");

        tweet.createdAt = jsonObject.getString("created_at");

        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        tweet.likes = jsonObject.getString("favorite_count");

        tweet.retweets = jsonObject.getString("retweet_count");
        return tweet;
    }

    public Tweet(){

    }
}
