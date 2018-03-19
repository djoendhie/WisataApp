package com.kun.jun.wisataapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.Email)
    Button Email;
    @BindView(R.id.Github)
    Button Github;
    @BindView(R.id.Facebook)
    Button Facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Email)
    public void onEmailClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkurl))));
    }

    @OnClick(R.id.Github)
    public void onGithubClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkurl1))));
    }

    @OnClick(R.id.Facebook)
    public void onFacebookClicked() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkurl2))));
    }
}
