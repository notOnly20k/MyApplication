package com.example.administrator.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.model.shangpin;
import com.example.administrator.myapplication.R;
import com.example.administrator.util.AsyncBitmapLoader;

import java.util.List;

/**
 * Created by 30579 on 2018/2/16.
 */

public class MyGoodslistAdapter extends BaseAdapter {
    private AsyncBitmapLoader asyncBitmapLoader;
    // 创建ImageLoader对象
    private Context context;
    private List<shangpin> list;
    public MyGoodslistAdapter(Context context, List<shangpin> list){
        asyncBitmapLoader=new AsyncBitmapLoader();
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
        if (convertView == null) {
            convertView  = LayoutInflater.from(context).inflate(R.layout.layout_searchlist, parent,false);
            holder = new ViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.gname = (TextView) convertView.findViewById(R.id.name);
            holder.detail = (TextView) convertView.findViewById(R.id.detail);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.hownew = (TextView) convertView.findViewById(R.id.hownew);
            holder.getway = (TextView) convertView.findViewById(R.id.getway);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.scannum = (TextView) convertView.findViewById(R.id.scannum);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bitmap bitmap=asyncBitmapLoader.loadBitmap(holder.image, list.get(position).getTupian(), new AsyncBitmapLoader.ImageCallBack() {

            @Override
            public void imageLoad(ImageView imageView, Bitmap bitmap) {
                // TODO Auto-generated method stub
                imageView.setImageBitmap(bitmap);
            }
        });
        if(bitmap == null)
        {
            holder.image.setImageResource(R.drawable.touxiang);
        }
        else
        {
            holder.image.setImageBitmap(bitmap);
        }
        holder.image.setTag(list.get(position).getTupian());
        holder.gname.setText(list.get(position).getMincheng());
        holder.detail.setText(list.get(position).getXijie());
        holder.type.setText(list.get(position).getLeibie());
        holder.hownew.setText(String.valueOf(list.get(position).getXingjiuchengdu()));
        holder.getway.setText(list.get(position).getQuhuofangshi());
        holder.price.setText(String.valueOf(list.get(position).getJiage()));
        Log.i("123",list.get(position).getLiulanrenshu()+"");
        holder.scannum.setText(String.valueOf(list.get(position).getLiulanrenshu()));
        return convertView;
    }
    class ViewHolder{
        TextView gname,detail,type,hownew,getway,price,scannum;
        ImageView image;
    }

}