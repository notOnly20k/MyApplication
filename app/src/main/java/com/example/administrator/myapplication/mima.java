package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.util.DBService;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class mima extends AppCompatActivity {
    yonghu yonghu;
    EditText oldPwd,newPwd,rePwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mima);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        bindView();
    }
    public void bindView(){
        oldPwd=findViewById(R.id.oldpwd);
        newPwd=findViewById(R.id.newpwd);
        rePwd=findViewById(R.id.renewpwd);
    }
    public void xiugaimima(View view){
    if(!oldPwd.getText().toString().equals(yonghu.getMima())){
            AlertDialog.Builder builder  = new AlertDialog.Builder(mima.this);
            builder.setTitle("错误" ) ;
            builder.setMessage("原密码不正确" ) ;
            builder.setPositiveButton("是" ,  null );
            builder.show();
        }else{
            if(newPwd.getText().toString().equals(rePwd.getText().toString())){
                yonghu.setMima(newPwd.getText().toString());
                disposable= DBService.getDbService().updateUserData(yonghu)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) {
                                if (integer==1){
                                    Intent intent2 = new Intent(mima.this, gerenzhongxin.class);
                                    intent2.putExtra("dengluuser",yonghu);
                                    startActivity(intent2);
                                }else {
                                    AlertDialog.Builder builder  = new AlertDialog.Builder(mima.this);
                                    builder.setTitle("错误" ) ;
                                    builder.setMessage("修改失败" ) ;
                                    builder.setPositiveButton("是" ,  null );
                                    builder.show();
                                }
                            }
                        });
            }else {
                AlertDialog.Builder builder  = new AlertDialog.Builder(mima.this);
                builder.setTitle("错误" ) ;
                builder.setMessage("两次密码输入不一致" ) ;
                builder.setPositiveButton("是" ,  null );
                builder.show();
            }
        }
    }

    Disposable disposable=null;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null)
            disposable.dispose();
    }
    public void lijidenglu(View view){
        Intent intent2 = new Intent(mima.this, MainActivity.class);
        startActivity(intent2);
    }
}
