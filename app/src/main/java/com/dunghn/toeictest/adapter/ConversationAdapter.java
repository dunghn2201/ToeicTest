package com.dunghn.toeictest.adapter;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.ConversationActivity;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.Conversation;
import java.util.List;


public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.MyViewHolder> {

    List<Conversation> mlistconversation;
    ConversationActivity mConverActivity;
    LinearLayout.LayoutParams params;


    public ConversationAdapter(List<Conversation> conversations, ConversationActivity converActivity) {
        this.mlistconversation = conversations;
        this.mConverActivity = converActivity;
        DisplayMetrics metrics = converActivity.getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.85);
        params = new LinearLayout.LayoutParams(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

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
        View viewthatcontainscardview = inflater.inflate(R.layout.item_conversation, parent, false);
        return new MyViewHolder(viewthatcontainscardview);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        View localcardview = holder.singlecardview;
        TextView tv1;
        LinearLayout chatll, chatllin;

        tv1 = localcardview.findViewById(R.id.chattv);
        chatll = localcardview.findViewById(R.id.chatll);
        chatllin = localcardview.findViewById(R.id.chatllin);
        chatllin.setLayoutParams(params);

        Conversation obj = mlistconversation.get(position);

        tv1.setText(obj.getTextspeech());


        if (obj.getSide().equalsIgnoreCase("left")) {
            chatll.setGravity(Gravity.LEFT);
            chatllin.setBackgroundResource(R.drawable.roundedcorners);
            tv1.setTextColor(Color.parseColor("#000000"));

        } else {
            chatll.setGravity(Gravity.RIGHT);
            chatllin.setBackgroundResource(R.drawable.roundedcorners1);
            tv1.setTextColor(Color.parseColor("#ffffff"));
        }


        localcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConverActivity.sayIt(mlistconversation.get(position).getTextspeech());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlistconversation.size();
    }
}
