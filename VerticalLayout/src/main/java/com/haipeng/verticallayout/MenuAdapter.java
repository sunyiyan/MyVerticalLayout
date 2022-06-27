package com.haipeng.verticallayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<String> mList;
    private OnItemClickListener itemClickListener;
    private Context mContext;
    private int clickPosition;

    private int mTextDefaultColor;
    private int mTextSelectedColor;
    private int mTextSize;
    private Drawable mTextDefaultIcon;
    private Drawable mTextSelectedIcon;


    public MenuAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void setAttr(int textDefaultColor,
                        int textSelectedColor,
                        int textSize,
                        Drawable textDefaultIcon,
                        Drawable textSelectedIcon
    ) {
        this.mTextDefaultColor = textDefaultColor;
        this.mTextSelectedColor = textSelectedColor;
        this.mTextSize = textSize;
        this.mTextDefaultIcon = textDefaultIcon;
        this.mTextSelectedIcon = textSelectedIcon;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.btnText.setText(mList.get(position));
        if (position == clickPosition) {
            holder.btnBg.setBackgroundDrawable(mTextSelectedIcon);
            holder.btnText.setTextColor(mTextSelectedColor);
        } else {
            if (null == mTextDefaultIcon) {
                holder.btnBg.setBackgroundDrawable(null);
            } else {
                holder.btnBg.setBackgroundDrawable(mTextDefaultIcon);
            }
            holder.btnText.setTextColor(mTextDefaultColor);
        }
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    private AppCompatImageView clickBtnBg = null;
    private TextView clickText = null;

    class MenuViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView btnBg = null;
        TextView btnText = null;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            btnBg = itemView.findViewById(R.id.btn_bg);
            btnText = itemView.findViewById(R.id.btn_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickPosition = getAdapterPosition();
                    itemClickListener.OnItemClick(clickPosition);
//                    refreshBackground(btnBg, btnText);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void refreshClick(int position) {
        clickPosition = position;
        notifyDataSetChanged();
    }

//    public void refreshBackground(AppCompatImageView btnBg, TextView btnText) {
//        if (null != clickBtnBg) {
//            clickBtnBg.setImageBitmap(null);
//            clickText.setTextColor(mContext.getColor(R.color.menu_text_gray));
//        }
//        clickBtnBg = btnBg;
//        clickText = btnText;
//
//        btnBg.setImageResource(R.drawable.ic_selected);
//        btnText.setTextColor(mContext.getColor(R.color.menu_text_blue));
//    }
}
