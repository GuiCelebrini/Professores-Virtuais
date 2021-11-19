package com.android.guicelebrini.professoresvirtuais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.model.App;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class AppActivity extends YouTubeBaseActivity {

    private Toolbar toolbar;
    private Button buttonDownload;
    private TextView textDesc;
    private ImageView imagePrint;
    private YouTubePlayerView playerView;

    private FirebaseFirestore db;
    private String selectedAppId, selectedAppName, selectedAppType;
    private App selectedApp;

    public static final String GOOGLE_API_KEY = "AIzaSyBIkJyyaMXwVcAnr52DM5nAXCOOqh2eFKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        findViewsById();

        getExtras();

        configureToolbar();

        getAppFromFirebase();
        
    }

    private void findViewsById(){
        toolbar = findViewById(R.id.toolbarApp);
        buttonDownload = findViewById(R.id.buttonDownload);
        textDesc = findViewById(R.id.textAppDesc);
        imagePrint = findViewById(R.id.imagePrint);

        playerView = findViewById(R.id.youtubeView);
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        selectedAppId = extras.getString("appId");
        selectedAppName= extras.getString("appName");
        selectedAppType = extras.getString("appType");
    }

    private void configureToolbar(){
        //setSupportActionBar(toolbar);
        toolbar.setTitle(selectedAppName);
    }

    private void getAppFromFirebase(){
        db = FirebaseFirestore.getInstance();
        db.collection("apps").document(selectedAppType).collection("apps").document(selectedAppId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot snapshot = task.getResult();
                        selectedApp = snapshot.toObject(App.class);
                        set(selectedApp);
                    }
                });
    }

    private void set(App app){

        String formattedDescription = app.getDescription().replaceAll("\\\\n", "\n");
        textDesc.setText(formattedDescription);

        Picasso.get().load(app.getPrintURL()).into(imagePrint);

        buttonDownload.setOnClickListener(view -> {
            Uri uri = Uri.parse(app.getDownloadLink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            /*Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, formattedDescription);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);*/
        });

        playerView.initialize(GOOGLE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasPlaying) {
                if (!wasPlaying){
                    youTubePlayer.cueVideo(app.getVideoID());
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}