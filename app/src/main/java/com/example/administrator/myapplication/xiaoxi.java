package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.model.message;
import com.example.administrator.model.message_Table;
import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xiaoxi extends AppCompatActivity {
    yonghu yonghu;
    SimpleAdapter adapter;
    LinearLayout backlayout ;

    ListView mListView;
    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);
        backlayout = findViewById(R.id.back);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        getdata();
    }
    public void getdata()
    {
        mData.clear();

        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
        // 读取是当前用户的消息
        List<message> messages= SQLite.select(message_Table.sendId).distinct()
                .from(message.class)
                .where(message_Table.receiverId.eq(yonghu.getYonghuid()))
                .queryList();
        yonghu yonghutemp;
        if(messages.size()==0){
            backlayout.setVisibility(View.VISIBLE);
        }else{
            // 查询发送人
            for(message message:messages){
                yonghutemp=SQLite.select()
                        .from(yonghu.class)
                        .where(yonghu_Table.yonghuid.eq(message.getSendId()))
                        .querySingle();
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("id",yonghutemp.getYonghuid());
                item.put("name",yonghutemp.getXingming()+"发来的消息");
                mData.add(item);
            }
            mListView = findViewById(R.id.messagelist);

            backlayout.setVisibility(View.INVISIBLE);

            adapter = new SimpleAdapter(xiaoxi.this,mData,R.layout.message_detail_layout,
                    new String[]{"name"},new int[]{R.id.content});
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                        long id) {
                    int fasongid=Integer.parseInt(mData.get(position).get("id").toString());
                    Intent intent=new Intent(xiaoxi.this,chat.class);
                    intent.putExtra("shoujianren", fasongid);
                    intent.putExtra("dengluuser",yonghu);
                    startActivity(intent);
                }
            });
        }


    }
}
