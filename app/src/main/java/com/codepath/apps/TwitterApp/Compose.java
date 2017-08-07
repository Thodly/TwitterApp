package com.codepath.apps.TwitterApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.TwitterApp.models.Tweet;
import com.codepath.apps.TwitterApp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class Compose extends AppCompatActivity {
    User user;
    TextView tvRemainingChars;
    ImageView ivProfileCompose;
    EditText etBody;
    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        user = (User) getIntent().getSerializableExtra("user");
        client = TwitterApplication.getRestClient();
        setUpMyView();

    }
    public void setUpMyView(){
        etBody = (EditText) findViewById(R.id.etBody);
        TextView tvUserName = (TextView) findViewById(R.id.tvUserName);
        TextView tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvRemainingChars = (TextView) findViewById(R.id.tvRemainingChars);
        ivProfileCompose = (ImageView) findViewById(R.id.ivProfileImage);
        String myProfileImg = user.getProfileImageUrl();
        tvUserName.setText(user.getName());
        tvScreenName.setText(String.format("@%s", user.getScreenName()));
        if (!TextUtils.isEmpty(myProfileImg)){
            Picasso.with(this).load(myProfileImg).into(ivProfileCompose);
        }
        listenBodyText();
    }
    public void listenBodyText() {
        // count remaining characters on text changed
        etBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int remainCount = 140 - editable.length();
                tvRemainingChars.setText(String.valueOf(remainCount));
            }

        });
    }
    public void onTweet(View view){
        String status =  etBody.getText().toString();
        Toast.makeText(this,"I tweet " ,Toast.LENGTH_LONG).show();
        client.postUpdateStatus(status, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = Tweet.fromJSON(response);
                Intent i = new Intent();
                i.putExtra("tweet", tweet);
                setResult(RESULT_OK,i);
                finish();
            }

            // on faillure
            @Override
            public void onFailure (int statusCode , Header[] headers, Throwable throwable, JSONObject errorResponse){
                Log.d("DEBUG", errorResponse.toString());
                if(throwable.getMessage().contains("resolve host")){
                    Toast.makeText(Compose.this, " no connexion to internet", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void onClose(View view){
        finish();
        overridePendingTransition(0,0);
    }


}
