package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.util.DBService;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class geren_ziliao extends AppCompatActivity {
    yonghu yonghu;
    TextView idView,nicheng,tel;
    RadioButton sex_nan,sex_nv;
    Disposable disposable=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geren_ziliao);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        bindview();
        initData();
    }
    public void bindview(){
        idView=findViewById(R.id.id);
        nicheng=findViewById(R.id.nicheng);
        tel=findViewById(R.id.tel);
        sex_nan=findViewById(R.id.male_rb);
        sex_nv=findViewById(R.id.famale_rb);

    }
    public void initData(){
        idView.setText(String.valueOf(yonghu.getXuehao()));
        nicheng.setText(yonghu.getXingming());
        tel.setText(yonghu.getLianxidianhua());
        if(yonghu.getXingbie().equals("男")){
            sex_nan.setChecked(true);
        }else{
            sex_nv.setChecked(true);
        }

    }
    //打开按钮的编辑状态
    public void jinxinbianji(View view){
        nicheng.setEnabled(true);
        tel.setEnabled(true);
    }

    public void baocun(View view){
        yonghu.setXingming(nicheng.getText().toString());
        yonghu.setLianxidianhua(tel.getText().toString());
        disposable= DBService.getDbService().updateUserData(yonghu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        if (integer==1){
                            Intent intent2 = new Intent(geren_ziliao.this, gerenzhongxin.class);
                            intent2.putExtra("dengluuser",yonghu);
                            startActivity(intent2);
                        }else {
                            AlertDialog.Builder builder  = new AlertDialog.Builder(geren_ziliao.this);
                            builder.setTitle("确认" ) ;
                            builder.setMessage("更新失败" ) ;
                            builder.setPositiveButton("是" ,  null );
                            builder.show();
                        }
                    }
                });
//        if(yonghu.update()){
//            yonghu dengluhoudeyonghu= SQLite.select()
//                    .from(yonghu.class)
//                    .where(yonghu_Table.yonghuid.eq(yonghu.getYonghuid()))
//                    .querySingle();
//            Intent intent2 = new Intent(geren_ziliao.this, gerenzhongxin.class);
//            intent2.putExtra("dengluuser",dengluhoudeyonghu);
//            startActivity(intent2);
//        }else{
//            AlertDialog.Builder builder  = new AlertDialog.Builder(geren_ziliao.this);
//            builder.setTitle("确认" ) ;
//            builder.setMessage("更新失败" ) ;
//            builder.setPositiveButton("是" ,  null );
//            builder.show();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null)
            disposable.dispose();
    }
}
