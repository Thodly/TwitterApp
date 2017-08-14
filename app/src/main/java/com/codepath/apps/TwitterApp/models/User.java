package com.codepath.apps.TwitterApp.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by thodlydugue on 8/3/2017.
 */

public class User implements Serializable {
    private  String name;
    private long uid;
    private String screenName;
    private String tagline;
    private String profileImageUrl;
    private int followersCount;
    private int followingCount;


    public String getTagline() {
        return tagline;
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {return followingCount;
    }


    public static  User fromJSON(JSONObject json){
        User u = new User();
        try {
            u.name = json.getString("name");
            u.uid = json.getLong("id");
            u.screenName = json.getString("screen_name");
            u.profileImageUrl = json.getString("profile_image_url");
            u.tagline = json.getString("description");
            u.followersCount = json.getInt("followers_count");
            u.followingCount = json.getInt("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }
}
