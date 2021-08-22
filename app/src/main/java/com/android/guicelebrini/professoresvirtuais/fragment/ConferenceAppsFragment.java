package com.android.guicelebrini.professoresvirtuais.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guicelebrini.professoresvirtuais.R;


public class ConferenceAppsFragment extends Fragment {

    public ConferenceAppsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conference_apps, container, false);
    }
}