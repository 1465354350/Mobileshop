package com.example.mobilephoneshop.type.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mobilephoneshop.base.BaseFragment;

import static android.content.ContentValues.TAG;

public class TypeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initview() {
        Log.e(TAG,"分类视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"分类视图的数据被初始化了");
        textView.setText("分类内容");
    }
}
