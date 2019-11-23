package com.dunghn.toeictest.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dunghn.toeictest.GrammarDetailActivity;
import com.dunghn.toeictest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GramFragmentTab extends Fragment {

  private View rootView;
    public GramFragmentTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_gram_fragment_tab, container, false);
        View rootView = inflater.inflate(R.layout.fragment_gram_fragment_tab, container, false);
        TextView tv = rootView.findViewById(R.id.basictermtv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), GrammarDetailActivity.class);
                in.putExtra("filename", "basicterm.html");
                startActivity(in);
            }
        });
        return rootView;
    }

}
