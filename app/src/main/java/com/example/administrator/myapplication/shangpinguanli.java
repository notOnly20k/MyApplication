package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.model.yonghu;

public class shangpinguanli extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout mMygoods;
    private LinearLayout mCollection;
    private LinearLayout mSetting;
    private LinearLayout mFinish;
    yonghu yonghu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpinguanli);
        initView();

    }


    private void initView() {
        yonghu = (yonghu) getIntent().getSerializableExtra("dengluuser");
        mMygoods = (LinearLayout) findViewById(R.id.mygoods);
        mMygoods.setOnClickListener(this);
        mCollection = (LinearLayout) findViewById(R.id.collection);
        mCollection.setOnClickListener(this);
        mSetting = (LinearLayout) findViewById(R.id.setting);
        mSetting.setOnClickListener(this);
        mFinish = (LinearLayout) findViewById(R.id.finish);
        mFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,goodlist.class);
        switch (v.getId()) {
            default:
                break;
            case R.id.mygoods:
                intent.putExtra("type",goodlist.MINE);
                intent.putExtra("dengluuser",yonghu);
                startActivity(intent);
                break;
            case R.id.collection:
                intent.putExtra("type",goodlist.COLLECTION);
                intent.putExtra("dengluuser",yonghu);
                startActivity(intent);
                break;
            case R.id.setting:
                intent.putExtra("type",goodlist.SETTING);
                intent.putExtra("dengluuser",yonghu);
                startActivity(intent);

                break;
            case R.id.finish:
                finish();
                break;
        }
    }
}
