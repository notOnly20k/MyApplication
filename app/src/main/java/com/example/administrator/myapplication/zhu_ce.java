package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.model.yonghu;
import com.example.administrator.util.DBService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class zhu_ce extends AppCompatActivity {
    EditText xuehao,mima,lianxidianhua,xingming,reMima;
    TextView xuehaoResult,mimaResult,lianxidianhuaResult,xingmingResult,reMimaResult;
    RadioGroup sex;
    ProgressDialog pd;
    Disposable disposable=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        bindView();
        check();
    }
    public void bindView(){
        xuehao=findViewById(R.id.xuehao);
        mima=findViewById(R.id.mima);
        reMima=findViewById(R.id.mima_re);
        lianxidianhua=findViewById(R.id.dianhua);
        sex=findViewById(R.id.sex);
        xingming=findViewById(R.id.xingming);
        //  获取显示结果的控件
        xuehaoResult=findViewById(R.id.xuehao_result);
        mimaResult=findViewById(R.id.mima_result);
        reMimaResult=findViewById(R.id.remima_result);
        lianxidianhuaResult=findViewById(R.id.dianhua_result);
        xingmingResult=findViewById(R.id.mingzi_result);
        // 绑定进度条
        pd=new ProgressDialog(zhu_ce.this);
        pd.setMessage("注册中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
    }
    //登录跳转按钮绑定的方法
    public void denglutiaozhuan(android.view.View view){
        Intent integer=new Intent(zhu_ce.this,MainActivity.class);
        startActivity(integer);
    }
    // 点击注册后，然后存入数据库中
    public void lijizhuce(android.view.View view){
        pd.show();
        yonghu yonghu=new yonghu();
        yonghu.setXuehao(xuehao.getText().toString());
        yonghu.setXingming(xingming.getText().toString());
        yonghu.setMima(mima.getText().toString());
        yonghu.setLianxidianhua(lianxidianhua.getText().toString());
        switch (sex.getCheckedRadioButtonId ()) {
            case R.id.nan:
                yonghu.setXingbie("男");
                break;
            case R.id.nv:
                yonghu.setXingbie("女");
                break;
        }
        disposable= DBService.getDbService().insertUserData(yonghu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        if (integer==1){
                            pd.cancel();
                            Intent intent=new Intent(zhu_ce.this,MainActivity.class);
                            startActivity(intent);
                            Log.d("message","注册成功");
                        }else {
                            AlertDialog.Builder builder  = new AlertDialog.Builder(zhu_ce.this);
                            builder.setTitle("确认" ) ;
                            builder.setMessage("注册失败" ) ;
                            builder.setPositiveButton("是" ,  null );
                            builder.show();
                        }
                        pd.dismiss();
                    }
                });
//
//        if(yonghu.save()){
//            pd.cancel();
//            Intent integer=new Intent(zhu_ce.this,MainActivity.class);
//            startActivity(integer);
//            Log.d("message","注册成功");
//        }else{
//            AlertDialog.Builder builder  = new AlertDialog.Builder(zhu_ce.this);
//            builder.setTitle("确认" ) ;
//            builder.setMessage("注册失败" ) ;
//            builder.setPositiveButton("是" ,  null );
//            builder.show();
//        }
    }
    public void check(){
        xingming.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                } else {
                    // 此处为失去焦点时的处理内容
                    if(xingming.getText().toString().trim().isEmpty())
                        xingmingResult.setVisibility(View.VISIBLE);
                    else   xingmingResult.setVisibility(View.GONE);
                }
            }
        });

        mima.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                } else {
                    // 此处为失去焦点时的处理内容
                    if(mima.getText().toString().trim().length()<6)
                        mimaResult.setVisibility(View.VISIBLE);
                    else  mimaResult.setVisibility(View.GONE);
                }
            }
        });

        reMima.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                } else {
                    // 此处为失去焦点时的处理内容
                    if(reMima.getText().toString().equals(mima.getText().toString()))
                        reMimaResult.setVisibility(View.INVISIBLE);
                    else reMimaResult.setVisibility(View.GONE);
                }
            }
        });

        xuehao.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                } else {
                    // 此处为失去焦点时的处理内容
                    if(xuehao.getText().toString().trim().isEmpty())  xuehaoResult.setVisibility(View.VISIBLE);
                    else    xuehaoResult.setVisibility(View.GONE);
                }
            }
        });
        lianxidianhua.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                } else {
                    // 此处为失去焦点时的处理内容
                    if(lianxidianhua.getText().toString().trim().isEmpty())lianxidianhuaResult.setVisibility(View.VISIBLE);
                    else lianxidianhuaResult.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null){
            disposable.dispose();
        }
    }
}
