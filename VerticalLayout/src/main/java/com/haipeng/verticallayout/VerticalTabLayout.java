package com.haipeng.verticallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class VerticalTabLayout extends RecyclerView {


    MenuAdapter menuAdapter;
    List<String> mList;
    ViewPager2 mViewPager;

    private int textDefaultColor;
    private int textSelectedColor;
    private int textSize;
    private Drawable textDefaultIcon;
    private Drawable textSelectedIcon;


    public VerticalTabLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public VerticalTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.syyVerticalMenu);
        textDefaultColor = typedArray.getColor(R.styleable.syyVerticalMenu_textDefaultColor, Color.GRAY);
        textSelectedColor = typedArray.getColor(R.styleable.syyVerticalMenu_textSelectedColor, Color.GRAY);
        textSize = (int) typedArray.getDimension(R.styleable.syyVerticalMenu_textSize, 20f);
        textDefaultIcon = typedArray.getDrawable(R.styleable.syyVerticalMenu_textDefaultIcon);
        textSelectedIcon = typedArray.getDrawable(R.styleable.syyVerticalMenu_textSelectedIcon);

        typedArray.recycle();
        initView(context);
    }

    public void initView(Context context) {
        menuAdapter = new MenuAdapter(context);
        menuAdapter.setAttr(textDefaultColor, textSelectedColor, textSize, textDefaultIcon, textSelectedIcon);
        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        setAdapter(menuAdapter);
    }

    boolean isClick = false;
    public void setViewPager2(ViewPager2 viewPager2) {
        this.mViewPager = viewPager2;
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                Log.d("tag", "position =" + position + "positionOffset =" + positionOffset + " positionOffsetPixels =" + positionOffsetPixels);
            }

            int index = 0;

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                Log.d("tag", " onPageSelected position break;" + position);

                // viewPager滑动刷新菜单
                index = position;

                if (isClick == true) {
                    isClick = false;
                } else {
                    smoothScrollToPosition(index);
                    menuAdapter.refreshClick(index);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
//                Log.d("tag", " onPageScrollStateChanged break;" + state);
            }
        });

        menuAdapter.setItemClickListener(position -> {
            isClick = true;
            //菜单点击 ，刷新 viewPager
            mViewPager.setCurrentItem(position);
        });
    }

    public void setMenuData(List<String> list) {
        menuAdapter.setData(list);
    }
}
