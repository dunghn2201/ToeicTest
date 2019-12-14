package com.dunghn.toeictest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.VocabularyActivity;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.VocalLevels;

import java.util.List;


public class VocabLevelAdapter extends RecyclerView.Adapter<VocabLevelAdapter.MyViewHolder> {

    List<VocalLevels> al;
    Context mcontext;
    public VocabLevelAdapter(List<VocalLevels> al, Context context) {
        this.al = al;
        this.mcontext=context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View singlecardview;

        public MyViewHolder(View itemView) {
            super(itemView);
            singlecardview = (itemView);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewthatcontainscardview = inflater.inflate(R.layout.item_levelvocal, parent, false);

        return new MyViewHolder(viewthatcontainscardview);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        View localcardview = holder.singlecardview;
        TextView tv1;
        tv1 = (TextView) (localcardview.findViewById(R.id.vocableveltv));
        VocalLevels obj = al.get(position);

        tv1.setText(obj.getLevelName());

        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, VocabularyActivity.class);
                in.putExtra("filename",al.get(position).getLevelIcon());
                in.putExtra("levelname",al.get(position).getLevelName());
                mcontext.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {

        return al.size();
    }
}
