package com.dunghn.toeictest.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.Part1Activity;
import com.dunghn.toeictest.Part2Activity;
import com.dunghn.toeictest.Part3Activity;
import com.dunghn.toeictest.Part4Activity;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.adapter.PartAdapter;
import com.dunghn.toeictest.model.Part;
import com.dunghn.toeictest.model.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToeicTestQuizFragment extends Fragment {
    private View rootView;
    public static int MOVE_PART = 111;
    public static int IN_PROGESS = 100;
    private RecyclerView recyclerViewPart;
    Part itemPartModel;
    List<Part> listparts;
    PartAdapter partAdapter;
    TextView tvPart;
    //Mang luu trang thai lam bai
    Status status;
    public ToeicTestQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_toeic_test_quiz, container, false);
        tvPart = (TextView) rootView.findViewById(R.id.tvPart);

        recyclerViewPart = (RecyclerView)rootView.findViewById(R.id.recycler_view_part);
        status = new Status();
        listparts = new ArrayList<>();
        addPart();
        partAdapter = new PartAdapter(getActivity(), listparts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewPart.setHasFixedSize(true);
        recyclerViewPart.setLayoutManager(layoutManager);
        recyclerViewPart.setAdapter(partAdapter);
        partAdapter.setOnItemClickListener(new PartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent sendingIntent = new Intent();
                switch (position) {
                    case 0:
                        sendingIntent = new Intent(getActivity(), Part1Activity.class);
                        break;
                    case 1:
                        sendingIntent = new Intent(getActivity(), Part2Activity.class);
                        break;
                    case 2:
                        sendingIntent = new Intent(getActivity(), Part3Activity.class);
                        break;
                    case 3:
                        sendingIntent = new Intent(getActivity(), Part4Activity.class);
                        break;
                }
                sendingIntent.putExtra("status", status);
                sendingIntent.putExtra("count", 0);
                if (sendingIntent != null)
                    getActivity().startActivityForResult(sendingIntent, MOVE_PART);
            }
        });

        return rootView;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if (requestCode == MOVE_PART) {
            if (resultCode == IN_PROGESS) //dang lam bai (chua nop)
                status = (Status) data.getSerializableExtra("status");    //cap nhat trang thai lam bai
        }
    }

    public void addPart() {
        itemPartModel = new Part();
        itemPartModel.setNamePart("Photographs");
        itemPartModel.setStt("Part 1");
        itemPartModel.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.icon_part1));

        listparts.add(itemPartModel);
        itemPartModel = new Part();
        itemPartModel.setNamePart("Question – Response");
        itemPartModel.setStt("Part 2");
        itemPartModel.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.icon_part2));

        listparts.add(itemPartModel);
        itemPartModel = new Part();
        itemPartModel.setNamePart("Short Conversations");
        itemPartModel.setStt("Part 3");
        itemPartModel.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.icon_part3));

        listparts.add(itemPartModel);
        itemPartModel = new Part();
        itemPartModel.setNamePart("Short Talks");
        itemPartModel.setStt("Part 4");
        itemPartModel.setPic(BitmapFactory.decodeResource(getResources(), R.drawable.icon_part4));

        listparts.add(itemPartModel);
    }

}
