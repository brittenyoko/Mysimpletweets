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
    public String mediaUrl;
    //public String replies;

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

        if (jsonObject.has("extended_entities")) {
            if (jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("type").equals("photo")) {
                tweet.mediaUrl = jsonObject.getJSONObject("extended_entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
            }
        }

        //tweet.replies = jsonObject.getString("reply_count");
        return tweet;
    }

    public Tweet(){

    }
}
