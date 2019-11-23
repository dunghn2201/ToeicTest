package com.dunghn.toeictest.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dunghn.toeictest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToeicTestQuizFragment extends Fragment {


    public ToeicTestQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_toeic_test_quiz, container, false);
    }

}
