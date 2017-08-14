package com.codepath.apps.TwitterApp.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.TwitterApp.TwitterApplication;
import com.codepath.apps.TwitterApp.TwitterClient;
import com.codepath.apps.TwitterApp.models.Tweet;
import com.codepath.apps.TwitterApp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static java.util.Collections.addAll;

/**
 * Created by Thodly on 8/12/2017.
 */

public class UserTimelineFragment extends TweetsListFragment {
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    public static UserTimelineFragment newInstance(String screen_name){
       UserTimelineFragment userFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screen_name);
        userFragment.setArguments(args);
        return userFragment;
    }

    private void populateTimeline(){
        String screenName = getArguments().getString("screen_name");
        client.getUserTimeline(screenName,new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                addAll(Tweet.fromJSONArray(json));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }
        });
    }
}
