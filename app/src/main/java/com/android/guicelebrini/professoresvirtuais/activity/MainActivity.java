package com.android.guicelebrini.professoresvirtuais.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.fragment.AppsFragment;
import com.android.guicelebrini.professoresvirtuais.helper.Preferences;
import com.android.guicelebrini.professoresvirtuais.model.App;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
        configureToolbar();
        configureTabLayout();

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

    private void configureTabLayout(){

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Conferência", AppsFragment.class, new Bundler().putString("fragmentName", "conference").get())
                .add("Gerenciar", AppsFragment.class, new Bundler().putString("fragmentName", "management").get())
                .add("Criar", AppsFragment.class, new Bundler().putString("fragmentName", "creation").get())
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tabLayoutMain);
        viewPagerTab.setViewPager(viewPager);
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