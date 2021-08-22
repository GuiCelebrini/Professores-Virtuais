package com.android.guicelebrini.professoresvirtuais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.helper.Preferences;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        configureToolbar();

        preferences = new Preferences(this);
        changeToolbarTitle();
    }

    private void changeToolbarTitle(){
        if (preferences.getUserName() != null){
            String userName = preferences.getUserName();
            toolbar.setTitle("Olá, " + userName);
        }
    }

    private void findViewsById(){
        toolbar = findViewById(R.id.toolbarMain);
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.actionChangeName:
                createChangeNameDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createChangeNameDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        alertDialog.setTitle("Insira seu novo nome de usuário: ");

        EditText editNewName = new EditText(this);
        alertDialog.setView(editNewName);

        alertDialog.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newName = editNewName.getText().toString();
                if (newName != null){
                    preferences.saveData(newName);
                    changeToolbarTitle();
                }
            }
        });

        alertDialog.create();
        alertDialog.show();
    }
}