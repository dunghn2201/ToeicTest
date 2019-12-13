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
import com.dunghn.toeictest.model.Status;


public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    Context mcontext;
    Status mstatuses;
    Status mfullList;

    public StatusAdapter(Context context, Status status) {
        mstatuses = status;
        mfullList = status;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View statusView = inflater.inflate(R.layout.item_list_status, parent, false);
        StatusViewHolder statusViewHolder = new StatusViewHolder(statusView);
        return statusViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatusAdapter.StatusViewHolder holder, int position) {
        if (mstatuses.getCustomerAnwser(position) == mstatuses.getKey()[position])
            holder.tvAnswer.setTextColor(Color.rgb(2, 154, 204));
        else
            holder.tvAnswer.setTextColor(Color.rgb(221, 22, 142));
        switch (mstatuses.getCustomerAnswer()[position]) {
            case 0:
                holder.tvAnswer.setText("");
                break;
            case 1:
                holder.tvAnswer.setText("A");
                break;
            case 2:
                holder.tvAnswer.setText("B");
                break;
            case 3:
                holder.tvAnswer.setText("C");
                break;
            case 4:
                holder.tvAnswer.setText("D");
                break;
        }

        switch (mstatuses.getKey()[position]) {
            case 0:
                holder.tvKey.setText("-");
                break;
            case 1:
                holder.tvKey.setText("A");
                break;
            case 2:
                holder.tvKey.setText("B");
                break;
            case 3:
                holder.tvKey.setText("C");
                break;
            case 4:
                holder.tvKey.setText("D");
                break;
        }
        int ID = position + 1;
        holder.tvIDstatus.setText("Question: " + ID);

    }

    @Override
    public int getItemCount() {
        return mstatuses.getKey().length;
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder {
        TextView tvAnswer, tvIDstatus, tvKey;
        CardView cvstatus;

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvstatus = (CardView) itemView.findViewById(R.id.cardviewstatus);
            this.tvAnswer = (TextView) itemView.findViewById(R.id.tvcustomerAnswer);
            this.tvIDstatus = (TextView) itemView.findViewById(R.id.tvID);
            this.tvKey = (TextView) itemView.findViewById(R.id.tvKey);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
