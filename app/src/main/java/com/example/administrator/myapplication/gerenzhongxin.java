package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.model.yonghu;

public class gerenzhongxin extends AppCompatActivity {
    TextView idView,nicheng;
    private TextView me_text;

    private ImageView me_image;

    //定义颜色值
    private int Green =0xFF45C01A;
    yonghu yonghu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhongxin);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        bindview();
        initData();
        me_text.setTextColor(Green);
        me_image.setImageResource(R.drawable.me_botton_green);
    }
    public void bindview(){
        idView=findViewById(R.id.nicheng);
        nicheng=findViewById(R.id.uid);
        me_image = findViewById(R.id.me_image);
        me_text = findViewById(R.id.me_text);
    }
    public void initData(){
        idView.setText(String.valueOf(yonghu.getYonghuid()));
        nicheng.setText(yonghu.getXingming());

    }
    // 个人资料跳转
    public void dakaigerenziliao(View view){
        Intent intent=new Intent(gerenzhongxin.this,geren_ziliao.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);

    }
    // 商品管理跳转
    public void dakaishangpinguanli(View view){
        Intent intent=new Intent(gerenzhongxin.this,shangpinguanli.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 修改密码跳转
    public void xiugaimima(View view){
        Intent intent=new Intent(gerenzhongxin.this,mima.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    public void loginOut(android.view.View view){
        Intent intent=new Intent(gerenzhongxin.this,MainActivity.class);
        startActivity(intent);
    }
    // 进入消息
    public void xiaoxi(View view){
        Intent intent=new Intent(gerenzhongxin.this,xiaoxi.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 下部三个控制按钮操作
    //进入分类界面
    public void enterFenLei(android.view.View view){
        Intent intent=new Intent(gerenzhongxin.this,fenlei.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    //进入发售界面
    public void  enterFashou(android.view.View view){
        Intent intent=new Intent(gerenzhongxin.this,fabu_shangpin.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 进入我的界面
    public void enterWode(android.view.View view){

    }
}
