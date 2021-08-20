package com.android.guicelebrini.professoresvirtuais.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.helper.Preferences;


public class StartActivity extends AppCompatActivity {

    private EditText editName;
    private Button buttonStart;

    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewsById();

        preferences = new Preferences(this);
        verifyUser();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserName();
            }
        });

    }

    private void findViewsById(){
        editName = findViewById(R.id.editName);
        buttonStart = findViewById(R.id.buttonStart);
    }

    private void verifyUser(){
        if (preferences.getUserName() != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void getUserName(){
        String userName = editName.getText().toString();

        if (userName != null){
            preferences.saveData(userName);
            verifyUser();
        } else {
            Toast.makeText(getApplicationContext(), "Por favor insira um nome", Toast.LENGTH_SHORT).show();
        }
    }
}