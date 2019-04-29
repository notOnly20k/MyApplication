package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.model.Order;
import com.example.administrator.model.shangpin;
import com.example.administrator.myapplication.R;
import com.example.administrator.util.AsyncBitmapLoader;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 30579 on 2018/2/16.
 */

public class OrderAdapter extends BaseAdapter {
    // 创建ImageLoader对象
    private Context context;
    private List<Order> list;

    public OrderAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        Order order=list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
            holder = new ViewHolder();
            holder.mTvAddress=convertView.findViewById(R.id.tv_address);
            holder.mTvBuyerName=convertView.findViewById(R.id.tv_buyerName);
            holder.mTvGoodName=convertView.findViewById(R.id.tv_goodName);
            holder.mTvStatus=convertView.findViewById(R.id.tv_status);
            holder.mTvTel=convertView.findViewById(R.id.tv_tel);
            holder.mTvTime=convertView.findViewById(R.id.tv_time);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvAddress.setText(order.getAddress());
        holder.mTvBuyerName.setText(order.getBuyerName());
        holder.mTvGoodName.setText(order.getGoodName());
        holder.mTvStatus.setText(order.getStatus()==1?"已读":"未读");
        holder.mTvTel.setText(order.getTel());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.mTvTime.setText(sdf.format(order.getCreateTime()));
        return convertView;
    }

    class ViewHolder {
        /**
         * 订单消息
         */
        private TextView mTvTitle;
        /**
         * 商品名称：
         */
        private TextView mTvGoodName1;
        private TextView mTvGoodName;
        /**
         * 买家：
         */
        private TextView mTvBuyerName1;
        private TextView mTvBuyerName;
        /**
         * 时间：
         */
        private TextView mTvTime1;
        private TextView mTvTime;
        /**
         * 电话：
         */
        private TextView mTvTel1;
        private TextView mTvTel;
        /**
         * 地址：
         */
        private TextView mTvAddress1;
        private TextView mTvAddress;
        private TextView mTvStatus;
    }

}