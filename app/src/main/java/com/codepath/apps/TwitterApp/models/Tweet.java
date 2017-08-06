package com.codepath.apps.TwitterApp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thodlydugue on 8/3/2017.
 */

public class Tweet implements Serializable{
    private String body;
    private long uid;
    private User user;
    private String createdAt;
    private ArrayList<String> photoUrls;

    public User getUser() {
        return user;
    }
    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }
    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.uid = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

            tweet.photoUrls = new ArrayList<>();
            if (jsonObject.has("entities") && jsonObject.getJSONObject("entities").has("media")) {
                JSONArray medias = jsonObject.getJSONObject("entities").getJSONArray("media");
                for (int i = 0; i < medias.length(); i++) {
                    JSONObject media = medias.getJSONObject(i);

                    if (media.getString("type").equals("photo")) {
                        tweet.photoUrls.add(media.getString("media_url"));
                    }
                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            try {
                JSONObject tweetJson = jsonArray.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if(tweet != null){
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }
}
