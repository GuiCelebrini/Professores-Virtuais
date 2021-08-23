package com.android.guicelebrini.professoresvirtuais.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.guicelebrini.professoresvirtuais.R;

public class AppsFragment extends Fragment {

    public AppsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apps, container, false);

        TextView textView = view.findViewById(R.id.textView);
        Bundle args = getArguments();

        if (args != null){
            textView.setText(args.getString("fragmentName"));
        }

        return view;
    }
}