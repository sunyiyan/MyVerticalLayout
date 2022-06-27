# MyVerticalLayout
垂直/竖向 菜单 适配viewPager2,viewPager2滑动菜单跟着滑动

使用方法：
外部build.gradle:

buildscript {
    
    repositories {
        
        maven {url 'https://jitpack.io'}
    
    }
   
}

app:build.gradle:
  
  implementation 'com.github.sunyiyan:MyVerticalLayout:v1.0.0'

xml:

    syyVerticalMenu:菜单的问题颜色，文字背景的更换
    
      <com.haipeng.verticallayout.VerticalTabLayout
        android:id="@+id/left_menu"
        android:layout_width="150dp"
        android:layout_height="0dp"
        android:background="@color/menu_bar_bg"
        app:layout_constraintBottom_toTopOf="@+id/bottom_view"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toBottomOf="@id/top_view"
        syyVertivalMenu:textDefaultIcon="@null"
        syyVertivalMenu:textDefaultColor="@color/menu_text_gray"
        syyVertivalMenu:textSelectedColor="@color/menu_text_blue"
        syyVertivalMenu:textSelectedIcon="@drawable/ic_selected" />
activity:

        private VerticalTabLayout mVLayout;
        mVLayout.setMenuData(mListMenu); //赋值菜单显示数据
        mVLayout.setViewPager2(mImageViewPager);


