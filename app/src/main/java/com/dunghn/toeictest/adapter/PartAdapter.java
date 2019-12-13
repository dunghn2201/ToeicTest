package com.dunghn.toeictest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.Part;

import java.util.List;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.PartViewHolder> {
    private Context mcontext;
    private List<Part> mparts;
    private static OnItemClickListener listener1;

    public PartAdapter(Context context,List<Part> mparts) {
        this.mcontext=context;
        this.mparts = mparts;
    }

    @NonNull
    @Override
    public PartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View partView=inflater.inflate(R.layout.item_layout_part,parent,false);
        PartViewHolder partViewHolder=new PartViewHolder(partView);
        return partViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PartViewHolder holder, int position) {
        Part part = mparts.get(position);
        Bitmap bitmap=part.getPic();
        holder.img.setImageBitmap(bitmap);
        holder.tvName.setText(part.getNamePart());
        holder.tvPart.setText(part.getStt());
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(PartAdapter.OnItemClickListener listener1) {
        this.listener1 = listener1;
    }

    @Override
    public int getItemCount() {
        return mparts.size();
    }

    public class PartViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName, tvPart;
        CardView cv;
        public PartViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.cv = (CardView)itemView.findViewById(R.id.cardviewpart);
            this.img = itemView.findViewById(R.id.imgPart);
            this.tvName = itemView.findViewById(R.id.tvPartName);
            this.tvPart = itemView.findViewById(R.id.tvPart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener1 != null) {
                        listener1.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });

        }
    }
}
