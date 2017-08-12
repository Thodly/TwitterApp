package com.codepath.apps.TwitterApp.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.TwitterApp.TwitterApplication;
import com.codepath.apps.TwitterApp.TwitterClient;
import com.codepath.apps.TwitterApp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Thodly on 8/11/2017.
 */

public class HomeTimelineFragment extends TweetsListFragment {
    private TwitterClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();
        populateTimeline();
    }

    private void populateTimeline(){
        client.getHomeTimeline(1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG",json.toString());
                addAll(Tweet.fromJSONArray(json));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                if(throwable != null)
                    throwable.printStackTrace();

                if(errorResponse != null)
                    Log.d("DEBUG", errorResponse.toString());
            }
        });
    }
}
