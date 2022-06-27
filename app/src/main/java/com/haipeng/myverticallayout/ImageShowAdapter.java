package com.haipeng.myverticallayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageShowAdapter extends RecyclerView.Adapter<ImageShowAdapter.ImageShowViewHolder> {

    List<Integer> mList = new ArrayList<>();


    class ImageShowViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView aiv;

        public ImageShowViewHolder(@NonNull View itemView) {
            super(itemView);
            aiv = itemView.findViewById(R.id.aIV);
        }
    }

    public void setData(List<Integer> list){
        this.mList = list;
        notifyItemChanged(0);
    }


    @NonNull
    @Override
    public ImageShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.d("tag","position = "+position);
        return new ImageShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_show,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageShowViewHolder holder, int position) {
        Log.d("tag","position = "+position);
        holder.aiv.setImageResource(mList.get(position));
    }

    @Override
    public int getItemCount() {
//        Log.d("tag","list size = "+(null == mList ? 0 : mList.size()));
        return null == mList ? 0 : mList.size();
    }

    @Override
    public void onViewRecycled(@NonNull ImageShowViewHolder holder) {
        super.onViewRecycled(holder);
        Log.d("tag","onViewRecycled = ");

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ImageShowViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.d("tag","onViewDetachedFromWindow = ");

    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.d("tag","onDetachedFromRecyclerView = ");

    }
}
