package com.example.mobilephoneshop.shoppingcart.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mobilephoneshop.R;
import com.example.mobilephoneshop.base.BaseFragment;
import com.example.mobilephoneshop.home.bean.GoodsBean;
import com.example.mobilephoneshop.shoppingcart.adapter.ShoppingCartAdapter;


import com.example.mobilephoneshop.shoppingcart.utils.CartStorage;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static com.example.mobilephoneshop.R.id.ll_check_all;
import static com.example.mobilephoneshop.R.id.tv_shopcart_edit;


public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;

    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingCartAdapter adapter;


    private static final int ACTION_EDIT = 1;

    private static final int ACTION_COMPLETE = 2;


    private void initListener() {

        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT) {

                    showDelete();
                } else {

                    hideDelete();
                }
            }
        });


    }

    private void hideDelete() {

        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");

        if (adapter != null) {
            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }

        llDelete.setVisibility(View.GONE);

        llCheckAll.setVisibility(View.VISIBLE);

    }

    private void showDelete() {

        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("完成");

        if (adapter != null) {
            adapter.checkAll_none(false);
            adapter.checkAll();
        }

        llDelete.setVisibility(View.VISIBLE);

        llCheckAll.setVisibility(View.GONE);

    }


    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {


        } else if (v == btnDelete) {

            adapter.deleteData();

            adapter.checkAll();

            if (adapter.getItemCount() == 0) {
                emptyShoppingCart();
            }

        } else if (v == btnCollection) {

        }
    }

    @Override
    public View initview() {
        Log.e(TAG, "购物车的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        tvShopcartEdit = (TextView) view.findViewById(tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) view.findViewById(ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);

        ll_empty_shopcart = (LinearLayout) view.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) view.findViewById(R.id.tv_empty_cart_tobuy);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);

        initListener();


        return view;

    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "购物车的Fragment的数据被初始化了");


    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }


    private void showData() {
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();

        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.VISIBLE);

            ll_empty_shopcart.setVisibility(View.GONE);


            adapter = new ShoppingCartAdapter(mContext, goodsBeanList, tvShopcartTotal, checkboxAll, cbAll);
            recyclerview.setAdapter(adapter);


            recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));


        } else {

            emptyShoppingCart();
        }
    }

    private void emptyShoppingCart() {

        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
    }


}
