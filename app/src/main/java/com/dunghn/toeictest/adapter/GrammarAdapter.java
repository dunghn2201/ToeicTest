package com.dunghn.toeictest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.R;


public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.GrammarViewHolder> {
    public int images[];
    public String title[];
    public String meaning[];
    View v;
    LayoutInflater inflater;
    private static OnItemClickListener listener;

    public GrammarAdapter(int images[], String title[], String meaning[]) {
        this.images = images;
        this.title = title;
        this.meaning = meaning;
    }

    @Override
    public GrammarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.item_grammar, parent, false);
        return new GrammarViewHolder(v);
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(GrammarViewHolder holder, int position) {
        holder.image.setImageResource(images[position]);
        holder.title.setText(title[position]);
        holder.meaning.setText(meaning[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static final class GrammarViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView image;
        TextView title;
        TextView meaning;

        public GrammarViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvgrammar);
            image = (ImageView) itemView.findViewById(R.id.grammarimage);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            meaning = (TextView) itemView.findViewById(R.id.tvMeaning);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }
}
