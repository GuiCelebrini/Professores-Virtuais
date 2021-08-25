package com.android.guicelebrini.professoresvirtuais.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.adapter.AdapterRecyclerApps;
import com.android.guicelebrini.professoresvirtuais.model.App;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AppsFragment extends Fragment {

    private String fragmentName;

    private RecyclerView recyclerApps;
    private AdapterRecyclerApps adapter;
    private List<App> appsList = new ArrayList<>();

    private View view;

    public AppsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_apps, container, false);
        findViewsById();
        createAppsList();
        configureRecyclerApps();

        Bundle args = getArguments();
        if (args != null){
            fragmentName = args.getString("fragmentName");
        }

        return view;
    }

    private void findViewsById(){
        recyclerApps = view.findViewById(R.id.recyclerApps);
    }

    private void createAppsList(){
    }

    private void configureRecyclerApps(){
        adapter = new AdapterRecyclerApps(appsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        recyclerApps.setLayoutManager(layoutManager);
        recyclerApps.setHasFixedSize(true);
        //recyclerApps.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayout.VERTICAL));

        recyclerApps.setAdapter(adapter);
    }
}