package com.dunghn.toeictest.fragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dunghn.toeictest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroduceFragment extends Fragment {
    private View rootView;
    TextView tvPart1,tvPart2,tvPart3,tvPart4;

    public IntroduceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_introduce, container, false);
        tvPart1=(TextView)rootView.findViewById(R.id.tvHelep_p1);
        tvPart2=(TextView)rootView.findViewById(R.id.tvHelep_p2);
        tvPart3=(TextView)rootView.findViewById(R.id.tvHelep_p3);
        tvPart4=(TextView)rootView.findViewById(R.id.tvHelep_p4);
        tvPart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_introduce1);
                dialog.show();

            }
        });
        tvPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_introduce2);

                dialog.show();
            }
        });
        tvPart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_introduce3_4);

                dialog.show();
            }
        });
        tvPart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_introduce3_4);
                TextView tv=(TextView)dialog.findViewById(R.id.tvHelp_part3) ;
                tv.setText("Part 4");
                dialog.show();
            }
        });
        return rootView;
    }

}
