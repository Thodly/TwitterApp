package com.codepath.apps.TwitterApp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.TwitterApp.R;
import com.codepath.apps.TwitterApp.Tools.EndlessScrollListener;
import com.codepath.apps.TwitterApp.TweetsArrayAdapter;
import com.codepath.apps.TwitterApp.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thodly on 8/11/2017.
 */

public class TweetsListFragment  extends Fragment{
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
     View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter((aTweets));
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                loadNextDataFromApi(page);
                return true;
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }
    public void loadNextDataFromApi(int offset) {

    }

    public void addAll(List<Tweet> tweets){
     aTweets.addAll(tweets);
    }

}
