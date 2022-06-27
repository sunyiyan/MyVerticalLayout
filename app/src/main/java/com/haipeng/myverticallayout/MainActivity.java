package com.haipeng.myverticallayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;
import android.view.View;

import com.haipeng.verticallayout.VerticalTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyiyan
 * time:2022-06-21
 * 用户手册APP活动页面
 */
public class MainActivity extends AppCompatActivity {


    private List mListMenu = null;

    private AppCompatButton lastBtnClick = null;

    private ViewPager2 mImageViewPager;

    private ImageShowAdapter mImageAdapter;

    private VerticalTabLayout mVLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使得布局延伸到状态栏和导航栏区域
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        initMenuData();
        initView();
    }

    private void initMenuData() {
        mListMenu = new ArrayList();
        mListMenu.add("日程");
        mListMenu.add("主题");
        mListMenu.add("设置");
        mListMenu.add("图库");
    }

    private void initView() {
        mImageViewPager = this.findViewById(R.id.image_view_pager);
        mImageViewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        mImageAdapter = new ImageShowAdapter();
        mVLayout = this.findViewById(R.id.left_menu);
        initMenuData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        exeTransaction();
    }

    public void refreshData(List<Integer> list) {

    }

    private void exeTransaction() {

        mVLayout.setMenuData(mListMenu); //赋值菜单显示数据
        mVLayout.setViewPager2(mImageViewPager);

        mImageViewPager.setAdapter(mImageAdapter);
        mImageAdapter.setData(ImageSourceManager.aMapIntroList);//赋值图片数据显示

    }


    private void click(AppCompatButton button) {
        if (null != lastBtnClick) {
            lastBtnClick.setTextColor(getResources().getColor(R.color.menu_text_gray));
            lastBtnClick.setBackground(null);
        }
        lastBtnClick = button;
        lastBtnClick.setTextColor(getResources().getColor(R.color.menu_text_blue));
        lastBtnClick.setBackgroundResource(R.drawable.ic_selected);
    }


}