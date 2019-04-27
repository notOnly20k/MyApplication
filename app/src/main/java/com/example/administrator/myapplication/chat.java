package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.adapter.ChatAdapter;
import com.example.administrator.model.message;
import com.example.administrator.model.message_Table;
import com.example.administrator.model.pinlunhuifu;
import com.example.administrator.model.pinlunhuifu_Table;
import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class chat extends AppCompatActivity {
    yonghu yonghu;
    int shoujianren;

    private List<message> mMsgs;
    private ChatAdapter mAdapter;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    RecyclerView mRvChatList;
    EditText mEtContent;
    Button mBtSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        // 发送人
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        // 收件人
        shoujianren=intent.getIntExtra("shoujianren",0);
        bindView();
        mMsgs=messages();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvChatList.setLayoutManager(linearLayoutManager);
        mAdapter = new ChatAdapter(this, mMsgs,yonghu.getYonghuid());
        mRvChatList.setAdapter(mAdapter);
        //初试加载历史记录呈现最新消息
        mRvChatList.scrollToPosition(mAdapter.getItemCount() - 1);

        //设置下滑隐藏软键盘
        mRvChatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < -10) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEtContent.getWindowToken(), 0);
                }
            }
        });

    }
    public void bindView(){
        mRvChatList=findViewById(R.id.rv_chatList);
        mEtContent=findViewById(R.id.et_content);
        mBtSend=findViewById(R.id.bt_send);

    }
    // 查询全部消息
    public List<message> messages(){
        List<message> messages= SQLite.select()
                .from(message.class)
                .where(message_Table.receiverId.eq(yonghu.getYonghuid()))
                .and(message_Table.sendId.eq(shoujianren))
                .queryList();
        for(message message:messages){
            message.setFasongyonghu(chaxunyonghu(message.getSendId()));
            message.setJieshouyonghu(chaxunyonghu(message.getReceiverId()));
        }
        List<message> messages2= SQLite.select()
                .from(message.class)
                .where(message_Table.receiverId.eq(shoujianren))
                .and(message_Table.sendId.eq(yonghu.getYonghuid()))
                .queryList();
        for(message message:messages2){
            message.setFasongyonghu(chaxunyonghu(message.getSendId()));
            message.setJieshouyonghu(chaxunyonghu(message.getReceiverId()));
        }
        messages.addAll(messages2);
        return  messages;
    }
    public yonghu chaxunyonghu(int yomnghuid){
        yonghu yonghutemp=SQLite.select()
                .from(yonghu.class)
                .where(yonghu_Table.yonghuid.eq(yomnghuid))
                .querySingle();
        return  yonghutemp;
    }
    public void onViewClicked(View view){
        String content = mEtContent.getText().toString();
        message message=new message();
        message.setContent(content);
        message.setSendId(yonghu.getYonghuid());
        message.setReceiverId(shoujianren);
        message.setTime(df.format(new Date()));
        message.save();
        mAdapter.add(message);
        mEtContent.setText("");
    }
}
