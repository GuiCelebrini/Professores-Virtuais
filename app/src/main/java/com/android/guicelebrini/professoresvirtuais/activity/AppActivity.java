package com.android.guicelebrini.professoresvirtuais.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;

import com.android.guicelebrini.professoresvirtuais.R;

public class AppActivity extends AppCompatActivity {

    private Toolbar toolbar;

    String appId, appType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        findViewsById();
        getExtras();
        configureToolbar();



    }

    private void findViewsById(){
        toolbar = findViewById(R.id.toolbarApp);
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        appId = extras.getString("appId");
        appType = extras.getString("appType");
    }
}