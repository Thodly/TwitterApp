package com.codepath.apps.TwitterApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.TwitterApp.Tools.CountFormatter;
import com.codepath.apps.TwitterApp.Tools.ParseRelativeDate;
import com.codepath.apps.TwitterApp.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by thodlydugue on 8/4/2017.
 */

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {
    public  TweetsArrayAdapter(Context context, List<Tweet> tweets){
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Tweet tweet = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        TextView tvCreateAt = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvScreenName = (TextView) convertView.findViewById(R.id.tvScreenName);
        tvUserName.setText(tweet.getUser().getScreenName());
        tvScreenName.setText(String.format("@%s", tweet.getUser().getScreenName()));
        tvBody.setText(tweet.getBody());
        ImageView ivMediaPhoto = (ImageView) convertView.findViewById(R.id.ivMediaPhoto);
        ivMediaPhoto.setImageResource(0);
        TextView tvRetweetCount = (TextView) convertView.findViewById(R.id.tvRetweetCount);
        TextView tvFavoriteCount = (TextView) convertView.findViewById(R.id.tvFavoriteCount);

        String timeAgo = ParseRelativeDate.getAbbreviatedTimeAgo(tweet.getCreatedAt());
        tvCreateAt.setText(timeAgo);
        tvRetweetCount.setText(CountFormatter.format(tweet.getRetweetCount()));
        tvFavoriteCount.setText(CountFormatter.format(tweet.getFavoriteCount()));

        ivProfileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(ivProfileImage);

        if (tweet.getPhotoUrls().size() > 0) {
            String mediaPhoto = tweet.getPhotoUrls().get(0);
            }


        return convertView;
    }
}
