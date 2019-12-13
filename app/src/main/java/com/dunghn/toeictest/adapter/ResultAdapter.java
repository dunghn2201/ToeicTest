package com.dunghn.toeictest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.Result;

import java.util.List;
import java.util.Random;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.SavedPointViewHolder> {
    Context mcontext;
    List<Result> mreSult;
    List<Result> mfullList;

    public ResultAdapter(Context context, List<Result> results) {
        mreSult = results;
        mfullList = results;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public SavedPointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View savedpointView = inflater.inflate(R.layout.item_list_score_general, parent, false);
        SavedPointViewHolder savedPointViewHolder = new SavedPointViewHolder(savedpointView);
        return savedPointViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPointViewHolder holder, int position) {
        Result result=mreSult.get(position);
        holder.tvNamePoint.setText(result.getName());
        holder.tvScore.setText("Điểm: "+result.getScore());
        holder.tvTime.setText(result.getTime());
        Random random=new Random();
        int color= Color.argb(0,0,random.nextInt(),random.nextInt(255));
        holder.tvIcon.setBackgroundColor(color);
        holder.tvIcon.setText(result.getName().charAt(0)+"");

    }

    @Override
    public int getItemCount() {
        return mreSult.size();
    }

    public class SavedPointViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamePoint, tvTime, tvScore, tvIcon;
        CardView cvsp;
        public SavedPointViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvsp = (CardView)itemView.findViewById(R.id.cardviewketqua);
            this.tvNamePoint = (TextView) itemView.findViewById(R.id.tvName);
            this.tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            this.tvScore = (TextView) itemView.findViewById(R.id.tvScore);
            this.tvIcon = (TextView) itemView.findViewById(R.id.imgKQ_itemp);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
