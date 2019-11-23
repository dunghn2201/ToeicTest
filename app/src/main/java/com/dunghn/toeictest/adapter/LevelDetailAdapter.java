package com.dunghn.toeictest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.LevelData;

import java.util.ArrayList;

/**
 * Created by Dell on 5/29/2018.
 */

public class LevelDetailAdapter extends RecyclerView.Adapter<LevelDetailAdapter.MyViewHolder> {

    ArrayList<LevelData> al;
    Context context;


    public LevelDetailAdapter(ArrayList<LevelData> al, Context context) {
        this.al = al;
        this.context = context;
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
        View singleview = inflater.inflate(R.layout.item_layoutfordata, parent, false);
        // This will call Constructor of MyViewHolder, which will further copy its reference
        // to customview (instance variable name) to make its usable in all other methods of class
        Log.d("MYMESSAGE", "On CreateView Holder Done");
        return new MyViewHolder(singleview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        View localcardview = holder.itemView;
        TextView tv1, tv2;
        tv1 = (TextView) (localcardview.findViewById(R.id.datatitle));
        tv2 = (TextView) (localcardview.findViewById(R.id.dataans));
        final LevelData obj = al.get(position);
        tv1.setText(obj.title);
        tv2.setText(obj.answer);

    }

    @Override
    public int getItemCount() {

        Log.d("MYMESSAGE", "get Item Count Called");
        return al.size();
    }
}
