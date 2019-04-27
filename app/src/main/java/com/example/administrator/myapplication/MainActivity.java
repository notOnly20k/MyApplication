package com.example.administrator.myapplication;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.util.DBService;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.raizlabs.android.dbflow.sql.language.Method.count;

public class MainActivity extends AppCompatActivity {
    EditText yonghu,mima;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    boolean premission=false;
    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxPermissions= new RxPermissions(this);
        getPremission();
        bind();
    }

    private void getPremission() {
        rxPermissions.requestEach(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            premission=true;
                            return;
                        }
                        if (permission.shouldShowRequestPermissionRationale) {
                            premission=false;
                            Toast.makeText(MainActivity.this,"请同意权限",Toast.LENGTH_SHORT).show();
                            getPremission();
                            return;
                        }
                    }
                });
    }

    public void bind(){
        yonghu=findViewById(R.id.zhanghao);
        mima=findViewById(R.id.mima);
    }
    // 登录按钮绑定的事件
    public void denglu(View view){
        Log.d("123",yonghu.getText().toString());
        Log.d("123",mima.getText().toString());
        yonghu user=new yonghu();
        user.setXuehao(yonghu.getText().toString());
        user.setMima(mima.getText().toString());
         DBService.getDbService().getUserData(user)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Optional<yonghu>>() {
                    @Override
                    public void accept(Optional<yonghu> optional) {
                        if(optional.isPresent()){
                            Intent intent2 = new Intent(MainActivity.this, fenlei.class);
                            intent2.putExtra("dengluuser", optional.get());
                            startActivity(intent2);
                        }else{
                            AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("确认" ) ;
                            builder.setMessage("登录失败" ) ;
                            builder.setPositiveButton("是" ,  null );
                            builder.show();
                        }
                    }
                });



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    // 立即注册按钮绑定的事件
    public void lijizhuce(View view){
        Intent intent = new Intent(MainActivity.this,zhu_ce.class);
        startActivity(intent);
    }

}
