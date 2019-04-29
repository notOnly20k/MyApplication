package com.example.administrator.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.adapter.OrderAdapter;
import com.example.administrator.model.Order;
import com.example.administrator.model.message;
import com.example.administrator.model.message_Table;
import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.util.DBService;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class xiaoxi extends AppCompatActivity {
    yonghu yonghu;
    OrderAdapter adapter;
    LinearLayout backlayout ;

    ListView mListView;
    ArrayList<Order> mData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoxi);
        backlayout = findViewById(R.id.back);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");

       getOrder();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private void getOrder() {
        DBService.getDbService().getOrderList(yonghu.getYonghuid())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Order>>() {
                    @Override
                    public void accept(List<Order> list) {
                        getdata(list);
                    }
                });
    }
    public void getdata(final List<Order> list)
    {


        mData.clear();
        mData.addAll(list);
        if(mData.size()==0){
            backlayout.setVisibility(View.VISIBLE);
        }else{
            // 查询发送人
            mListView = findViewById(R.id.messagelist);

            backlayout.setVisibility(View.INVISIBLE);

            adapter =new OrderAdapter(this,mData);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                        long id) {
                    final Order order=mData.get(position);

                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(xiaoxi.this);
                    normalDialog.setTitle("提示");
                    normalDialog.setMessage("确认联系买家");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int which) {
                                    order.setStatus(1);
                                    DBService.getDbService().updateOrderStatus(order)
                                            .subscribeOn(Schedulers.io())
                                            .doOnSubscribe(new Consumer<Disposable>() {
                                                @Override
                                                public void accept(Disposable disposable) throws Exception {
                                                    compositeDisposable.add(disposable);
                                                }
                                            })
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<Integer>() {
                                                @Override
                                                public void accept(Integer resulet) {
                                                   if (resulet==1){
                                                       getOrder();
                                                       dialog.dismiss();
                                                   }
                                                }
                                            });
                                }
                            });
                    normalDialog.setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    // 显示
                    normalDialog.show();
                }
            });
        }


    }
}
