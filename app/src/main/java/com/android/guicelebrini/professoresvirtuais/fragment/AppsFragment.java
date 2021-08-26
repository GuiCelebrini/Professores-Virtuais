package com.android.guicelebrini.professoresvirtuais.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.adapter.AdapterRecyclerApps;
import com.android.guicelebrini.professoresvirtuais.helper.RecyclerItemClickListener;
import com.android.guicelebrini.professoresvirtuais.model.App;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AppsFragment extends Fragment {

    private String fragmentName;
    private View view;

    private RecyclerView recyclerApps;
    private AdapterRecyclerApps adapter;
    private List<App> appsList = new ArrayList<>();

    private FirebaseFirestore db;

    public AppsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_apps, container, false);

        findViewsById();

        getFragmentName();

        createAppsList();

        configureRecyclerApps();

        return view;
    }

    private void findViewsById(){
        recyclerApps = view.findViewById(R.id.recyclerApps);
    }

    private void getFragmentName(){
        Bundle args = getArguments();
        if (args != null){
            fragmentName = args.getString("fragmentName");
        }
    }

    private void createAppsList(){
        db = FirebaseFirestore.getInstance();

        db.collection("apps").document(fragmentName).collection("apps").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {

                        appsList.clear();

                        for (DocumentSnapshot snapshot : task.getResult()) {
                            App app = snapshot.toObject(App.class);
                            appsList.add(app);
                            app.setFirebaseId(snapshot.getId());
                        }

                        adapter.notifyDataSetChanged();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.i("MessageError", e.getMessage());
                        Toast.makeText(view.getContext(), "Oops... algo deu errado", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void configureRecyclerApps(){
        adapter = new AdapterRecyclerApps(appsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerApps.setLayoutManager(layoutManager);
        recyclerApps.setHasFixedSize(true);

        recyclerApps.addOnItemTouchListener(new RecyclerItemClickListener(view.getContext(), recyclerApps, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                App app = appsList.get(position);

                Uri uri = Uri.parse(app.getDownloadLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        recyclerApps.setAdapter(adapter);
    }
}