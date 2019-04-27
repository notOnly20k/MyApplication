package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;

public class fenlei extends AppCompatActivity {
    yonghu yonghu;
    private int Green =0xFF45C01A;
    private TextView mainpage_text,type_text,sale_text,find_text,me_text;

    private ImageView mainpage_image,type_image,sale_image,find_image,me_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");

        type_image = findViewById(R.id.type_image);
        type_text = findViewById(R.id.type_text);
        type_text.setTextColor(Green);
        type_image.setImageResource(R.drawable.fenlei_botton_green);
    }
    // 打开生活百货
    public void Openshenghuobaihuo(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","生活百货");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开数码产品
    public void Openshumachanpin(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","数码产品");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开服装
    public void Openfuzhuang(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","服装");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开房屋租赁
    public void Openfangwu(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","房屋租赁");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开化妆品
    public void Openhuazhuangping(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","化妆品");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开游戏
    public void Openyouxi(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","游戏交易");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开学习用具
    public void Openxuexi(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","学习用具");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开玩具
    public void Openwanju(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","玩具");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 打开运动器材
    public void Openyundong(android.view.View view){
        Intent intent=new Intent(fenlei.this,shangpinliebiao.class);
        intent.putExtra("type","运动器材");
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }

    // 下部三个控制按钮操作
    //进入分类界面
    public void enterFenLei(android.view.View view){

    }
    //进入发售界面
    public void  enterFashou(android.view.View view){
        Intent intent=new Intent(fenlei.this,fabu_shangpin.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
    // 进入我的界面
    public void enterWode(android.view.View view){
        Intent intent=new Intent(fenlei.this,gerenzhongxin.class);
        intent.putExtra("dengluuser",yonghu);
        startActivity(intent);
    }
}
