package com.example.mobilephoneshop.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilephoneshop.R;
import com.example.mobilephoneshop.home.bean.GoodsBean;
import com.example.mobilephoneshop.shoppingcart.utils.CartStorage;
import com.example.mobilephoneshop.shoppingcart.view.AddSubView;
import com.example.mobilephoneshop.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;


public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;

    private final CheckBox cbAll;


    public ShoppingCartAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        showTotalPrice();

        setListener();

        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                GoodsBean goodsBean = datas.get(position);

                goodsBean.setSelected(!goodsBean.isSelected());

                notifyItemChanged(position);

                checkAll();

                showTotalPrice();
            }
        });


        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isCheck = checkboxAll.isChecked();


                checkAll_none(isCheck);


                showTotalPrice();

            }
        });

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isCheck = cbAll.isChecked();


                checkAll_none(isCheck);


            }
        });

    }


    public void checkAll_none(boolean isCheck) {
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    public void checkAll() {
        if(datas != null && datas.size() >0){
            int number = 0;
            for (int i=0;i<datas.size();i++){
                GoodsBean goodsBean = datas.get(i);
                if(!goodsBean.isSelected()){
                    //非全选
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                }else{
                    //选中的
                    number ++;
                }
            }

            if(number == datas.size()){
                //全选
                checkboxAll.setChecked(true);
                cbAll.setChecked(true);
            }
        }else{
            checkboxAll.setChecked(false);
            cbAll.setChecked(false);
        }
    }



    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:" + getTotalPrice());
    }


    public double getTotalPrice() {
        double totalPrice = 0.0;
        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isSelected()){

                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());
                }
            }
        }
        return totalPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final GoodsBean goodsBean = datas.get(position);

        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.ddSubView.setValue(goodsBean.getNumber());
        holder.ddSubView.setMinValue(1);
        holder.ddSubView.setMaxValue(8);


        holder.ddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {

                goodsBean.setNumber(value);

                CartStorage.getInstance().updateData(goodsBean);

                notifyItemChanged(position);

                showTotalPrice();

            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){

                GoodsBean goodsBean = datas.get(i);
                if(goodsBean.isSelected()){

                    datas.remove(goodsBean);

                    CartStorage.getInstance().deleteData(goodsBean);

                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView ddSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            ddSubView = (AddSubView) itemView.findViewById(R.id.addSubView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }


    public interface  OnItemClickListener{

       public void  onItemClick(int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
