package com.example.helloworld.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloworld.R;

/**
 * Created by Administrator on 2016/12/6.
 */

public class MainTabbarFragment extends Fragment {

    View main_tab_feeds, main_tab_note, main_tab_search, main_tab_me, main_tab_btnnew;
    View[] tabbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tabbar, null);

        main_tab_feeds = view.findViewById(R.id.main_tab_feeds);
        main_tab_note = view.findViewById(R.id.main_tab_notes);
        main_tab_btnnew = view.findViewById(R.id.main_tab_btnnew);
        main_tab_search = view.findViewById(R.id.main_tab_search);
        main_tab_me = view.findViewById(R.id.main_tab_me);

        tabbar = new View[]{
                main_tab_feeds, main_tab_note, main_tab_search, main_tab_me
        };

        for (final View tab : tabbar) {
            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTabbarClick(tab);
                }
            });
        }

        main_tab_btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewClicked();
            }
        });

        return view;
    }

    public  void setSelectedItem(int index){
        if (index >= 0 && index < tabbar.length) {
            onTabbarClick(tabbar[index]);
        }
    }

    public int getSelectedIndex() {
        for (int i = 0 ;i < tabbar.length ; i++) {
            if (tabbar[i].isSelected()) {
                return i;
            }
        }
        return  -1;
    }

    private void onNewClicked() {
        if (onNewClickListener != null) {
            onNewClickListener.onNewClicked();
        }
    }

    public static interface OnNewClickListener{
        void onNewClicked();
    }

    OnNewClickListener onNewClickListener;

    public void setOnNewClickListener(OnNewClickListener onNewClickListener) {
        this.onNewClickListener = onNewClickListener;
    }

    public static interface OnTabbarSelectedListener{
        void onTabbarSelected(int index);
    }

    public void setOnTabbarSelectedListener(OnTabbarSelectedListener onTabbarSelectedListener) {
        this.onTabbarSelectedListener = onTabbarSelectedListener;
    }

    OnTabbarSelectedListener onTabbarSelectedListener;



    private void onTabbarClick(View tab) {
        int selectedIndex = -1;

        //设置5个按钮选取状态的背景色
        for (int i=0;i<tabbar.length;i++) {
            View temp = tabbar[i];

            if (temp == tab) {
                temp.setSelected(true);
                selectedIndex = i;
            } else {
                temp.setSelected(false);
            }
        }


        //回调传值后，点击view后调用重写的onTabbarSelected方法
        if (onTabbarSelectedListener != null && selectedIndex>=0) {
            onTabbarSelectedListener.onTabbarSelected(selectedIndex);
        }


    }
}
