package com.dunghn.toeictest.fragment;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dunghn.toeictest.DAO.ResultDAO;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.adapter.ResultAdapter;
import com.dunghn.toeictest.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreGeneralFragment extends Fragment {
    private View rootView;
    RecyclerView recyclerViewKQ;
    List<Result> mreSult = new ArrayList<>();
    ResultAdapter adapterResult;
    private TextView tvStatusScoreGeneral;
    private ImageView imgNull;

    public ScoreGeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_score_general, container, false);
        recyclerViewKQ = (RecyclerView) rootView.findViewById(R.id.RecyclerViewKQ);
        tvStatusScoreGeneral = (TextView) rootView.findViewById(R.id.tvStatusScoreGeneral);
        imgNull = (ImageView) rootView.findViewById(R.id.img_null_list);
        final ResultDAO resultDAO = new ResultDAO(getActivity());  //lay database
        try {
            mreSult = resultDAO.getKQ();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mreSult.isEmpty()) {
            tvStatusScoreGeneral.setVisibility(View.VISIBLE);
            imgNull.setVisibility(View.VISIBLE);
        } else {
            tvStatusScoreGeneral.setVisibility(View.GONE);
            imgNull.setVisibility(View.GONE);
        }
        Log.d("XEMDATA", String.valueOf(mreSult));
        adapterResult = new ResultAdapter(getActivity(), mreSult);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewKQ.setHasFixedSize(true);
        recyclerViewKQ.setLayoutManager(layoutManager);
        recyclerViewKQ.setAdapter(adapterResult);
        return rootView;
    }

}
