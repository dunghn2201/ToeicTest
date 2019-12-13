package com.dunghn.toeictest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.R;
import com.dunghn.toeictest.VocalDetailActivity;
import com.dunghn.toeictest.model.VocalDetail;

import java.util.ArrayList;


public class VocalDetailAdapter extends RecyclerView.Adapter<VocalDetailAdapter.MyViewHolder> {

    ArrayList<VocalDetail> mlevelData;
    Context mcontext;


    public VocalDetailAdapter(ArrayList<VocalDetail> levelData, Context context) {
        this.mlevelData = levelData;
        this.mcontext = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View customview;

        public MyViewHolder(View itemView) {
            super(itemView);
            customview = itemView;
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View singleview = inflater.inflate(R.layout.item_levelvocal_detail, parent, false);

        return new MyViewHolder(singleview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        View localcardview = holder.itemView;
        TextView tv1, tv2;
        tv1 = (TextView) (localcardview.findViewById(R.id.vocaltitle));
        tv2 = (TextView) (localcardview.findViewById(R.id.vocalImage));
        final VocalDetail obj = mlevelData.get(position);
        tv1.setText(obj.title);
        tv2.setText(obj.answer);

        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, VocalDetailActivity.class);
                in.putExtra("title", obj.title);
                in.putExtra("id", obj.id);
                in.putExtra("answer", obj.answer);
                in.putExtra("additional", obj.additional);
                in.putExtra("photo", obj.photo);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlevelData.size();
    }
}
