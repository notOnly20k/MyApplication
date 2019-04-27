package com.example.administrator.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.model.message;
import com.example.administrator.myapplication.R;
import java.util.LinkedList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<message> mDatas;
    private int dengluyonghuId;
    //定义两个类别标志
    private static final int type1= 1; // 这个是发送
    private static final int type2= 2; // 这个是接收
    public ChatAdapter(Context context, List<message> datas, int dengluyonghu) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDatas = datas;
        dengluyonghuId=dengluyonghu;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        int type = getItemViewType(position);
        Log.d("123344",type+"type______");
        if (type == type2) {
            View view = mLayoutInflater.inflate(R.layout.item_chat_left, parent, false);
            return new ChatLeftViewHolder(view);
        } else {
            View view = mLayoutInflater.inflate(R.layout.item_chat_right, parent, false);
            return new ChatRightViewHolder(view);
        }

    }
    public void add(message data) {
        if (mDatas == null) {
            mDatas = new LinkedList<>();
        }
        mDatas.add(data);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        message msg = mDatas.get(position);
        String time = msg.getTime();
        String content = msg.getContent();
        if(holder instanceof ChatLeftViewHolder){
            ((ChatLeftViewHolder) holder).mTvLeftTime.setText(time);
            ((ChatLeftViewHolder) holder).User.setText(msg.getJieshouyonghu().getXingming());
            ((ChatLeftViewHolder) holder).mTvMsgLeft.setText(content);
        }else if(holder instanceof ChatRightViewHolder){
            ((ChatRightViewHolder) holder).mTvRightTime.setText(time);
            ((ChatRightViewHolder) holder).User.setText(msg.getJieshouyonghu().getXingming());
            ((ChatRightViewHolder) holder).mTvMsgRight.setText(content);
        }
    }
    public int getItemViewType(int position) {
        Log.d("123344","_____");
        if ( mDatas.get(position).getSendId()==dengluyonghuId) {

            Log.d("123344",type1+"RIGHT;");
            return  type1;
        } else {

            Log.d("123344",type2+"LEFT;");
            return type2 ;
        }
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    //添加消息显示在RecyclerView中
    public void addItem(message msg) {
        mDatas.add(msg);
        notifyDataSetChanged();
    }

    static class ChatLeftViewHolder extends RecyclerView.ViewHolder {
        TextView mTvLeftTime;
        TextView User;
        TextView mTvMsgLeft;

        ChatLeftViewHolder(View view) {
            super(view);
            mTvLeftTime=view.findViewById(R.id.tv_left_time);
            mTvMsgLeft=view.findViewById(R.id.tv_msg_left);
            User=view.findViewById(R.id.tv_msg_name);
        }
    }

    static class ChatRightViewHolder extends RecyclerView.ViewHolder{
        TextView mTvRightTime;
        TextView mTvMsgRight;
        TextView User;
        ChatRightViewHolder(View view) {
            super(view);
            mTvRightTime=view.findViewById(R.id.tv_right_time);
            mTvMsgRight=view.findViewById(R.id.tv_msg_right);
            User=view.findViewById(R.id.tv_msg_name);
        }
    }
}
