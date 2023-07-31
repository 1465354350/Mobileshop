package com.example.mobilephoneshop.community.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mobilephoneshop.base.BaseFragment;

import static android.content.ContentValues.TAG;

public class CommunityFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initview() {
        Log.e(TAG,"发现的视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"发现视图的数据被初始化了");
        textView.setText("发现内容");
    }
}
