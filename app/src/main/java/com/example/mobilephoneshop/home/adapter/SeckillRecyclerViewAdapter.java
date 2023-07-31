package com.example.mobilephoneshop.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobilephoneshop.R;
import com.example.mobilephoneshop.home.bean.ResultBeanData;
import com.example.mobilephoneshop.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;


public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHodler> {

    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;
    private final Context mContext;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.list = list;
        this.mContext  = mContext;

    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {

        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);



        Glide.with(mContext).load(Constants.BASE_URl_IMAGE+listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onSeckillRecyclerView != null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }


    public interface OnSeckillRecyclerView{

        public void onItemClick(int position);
    }

    private  OnSeckillRecyclerView onSeckillRecyclerView;


    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
