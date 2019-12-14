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
import java.util.List;


public class VocalAdapter extends RecyclerView.Adapter<VocalAdapter.MyViewHolder> {

    List<VocalDetail> mlevelData;
    Context mcontext;


    public VocalAdapter(List<VocalDetail> levelData, Context context) {
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
        TextView vocaltitle, vocaldecription;
        vocaltitle = (TextView) (localcardview.findViewById(R.id.vocaltitle));
        vocaldecription = (TextView) (localcardview.findViewById(R.id.vocaldecription));
        final VocalDetail obj = mlevelData.get(position);
        vocaltitle.setText(obj.title);
        vocaldecription.setText(obj.answer);

        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, VocalDetailActivity.class);
                in.putExtra("title", obj.title);
                in.putExtra("id", obj.id);
                in.putExtra("answer", obj.answer);
                in.putExtra("additional", obj.additional);
                in.putExtra("photo", obj.photo);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlevelData.size();
    }
}
