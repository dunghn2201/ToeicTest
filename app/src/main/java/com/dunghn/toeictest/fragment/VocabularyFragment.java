package com.dunghn.toeictest.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dunghn.toeictest.R;
import com.dunghn.toeictest.adapter.VocabAdapter;
import com.dunghn.toeictest.model.VocalLevels;

import java.util.ArrayList;

public class VocabularyFragment extends Fragment {
    ArrayList<VocalLevels> vocalLevels = new ArrayList<>();
    RecyclerView rcv;
    VocabAdapter adapterVocal;
    public VocabularyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView=inflater.inflate(R.layout.fragment_vocabulary, container, false);;
              rcv = (RecyclerView) (rootView.findViewById(R.id.rcv1));


        vocalLevels.clear();
        vocalLevels.add(new VocalLevels("Level 1", "level1.txt"));
        vocalLevels.add(new VocalLevels("Level 2", "level2.txt"));
        vocalLevels.add(new VocalLevels("Level 3", "level3.txt"));
        vocalLevels.add(new VocalLevels("Level 4", "level4.txt"));
        vocalLevels.add(new VocalLevels("Level 5", "level5.txt"));
        vocalLevels.add(new VocalLevels("Level 6", "level6.txt"));
        vocalLevels.add(new VocalLevels("Level 7", "level7.txt"));
        vocalLevels.add(new VocalLevels("Level 8", "level8.txt"));


        adapterVocal = new VocabAdapter(vocalLevels, getContext());
        rcv.setAdapter(adapterVocal);
        adapterVocal.notifyDataSetChanged();
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getContext().getApplicationContext(), 3);
        rcv.setLayoutManager(linearLayoutManager);
        return rootView;
    }

}
