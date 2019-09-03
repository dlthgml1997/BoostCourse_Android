package com.sohee.boostcourse_pjt.ui.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sohee.boostcourse_pjt.R;
import com.sohee.boostcourse_pjt.ui.movie.item.GalleryItem;

import java.util.ArrayList;

public class GalleryRVAdapter extends RecyclerView.Adapter<GalleryRVAdapter.ViewHolder> {
    Context context;

    ArrayList<GalleryItem> items = new ArrayList<>();

    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder, View view, int position);
    }

    public GalleryRVAdapter(Context context,OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gallery_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        GalleryItem item = items.get(position);
        holder.setItem(context, item);

        holder.photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder,view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(GalleryItem item) {
        items.add(item);
    }

    public void addItems(ArrayList<GalleryItem> items) {
        this.items = items;
    }

    public GalleryItem getItem(int position) {
        return items.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        ImageView playImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = (ImageView) itemView.findViewById(R.id.img_gallery_item_photo);
            playImg = (ImageView) itemView.findViewById(R.id.img_gallery_item_video_play);

        }

        public void setItem(Context context, GalleryItem item) {

            if (item.isVideo()) {
                String videoId = item.getLink().substring(item.getLink().lastIndexOf("/"));

                Glide.with(context).load("https://img.youtube.com/vi" + videoId + "/0.jpg").into(photo);

                playImg.setVisibility(View.VISIBLE);
            } else {
                Glide.with(context).load(item.getLink()).into(photo);
                playImg.setVisibility(View.INVISIBLE);
            }
        }

    }

}
