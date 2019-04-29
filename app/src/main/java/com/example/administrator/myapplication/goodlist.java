package com.example.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.adapter.GoodslistAdapter;
import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;
import com.example.administrator.util.DBService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class goodlist extends AppCompatActivity implements View.OnClickListener {

    /**
     * 返回
     */
    private Button mButton3;
    private ListView mGoodslist;
    private LinearLayout mBack;
    yonghu yonghu;
    private String type;
    ProgressDialog pd ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodlist);
        initView();
    }

    private void initView() {
        pd = new ProgressDialog(this);
        pd.setMessage("宝贝加载中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        Intent intent = getIntent();
        mGoodslist = (ListView) findViewById(R.id.goodslist);
        mBack = (LinearLayout) findViewById(R.id.back);
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        type = intent.getStringExtra("type");

    }

    @Override
    protected void onStart() {
        super.onStart();
        switch (type){
            case COLLECTION:
                getCollectionData();
                break;
            case SETTING:
                getMyData();
                break;
            case MINE:
                getMyData();
                break;
        }
    }

    private void getCollectionData() {
        DBService.getDbService().getCollectGoodsData(yonghu.getYonghuid())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<shangpin>>() {
                    @Override
                    public void accept(List<shangpin> list) {
                        show(list);
                        pd.dismiss();
                    }
                });
    }

    private void getMyData() {
        DBService.getDbService().getMyGoodsData(yonghu.getYonghuid())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        compositeDisposable.add(disposable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<shangpin>>() {
                    @Override
                    public void accept(List<shangpin> list) {
                        show(list);
                        pd.dismiss();
                    }
                });
    }
    ArrayList<shangpin> mData= new ArrayList<shangpin>();

    public void show(List<shangpin> shangpins){
        mData.clear();
        pd.cancel();
        if(shangpins.size()>= 1)
        {
            mData.addAll(shangpins);
            GoodslistAdapter adapter = new GoodslistAdapter(goodlist.this,mData);
            adapter.notifyDataSetChanged();
            mGoodslist = findViewById(R.id.goodslist);
            mGoodslist.setAdapter(adapter);
            mBack.setVisibility(View.INVISIBLE);
            mGoodslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                        long id) {
                    if (type.equals(SETTING)){
                        Log.e("ssss",type);
                        Intent intent = new Intent(goodlist.this,fabu_gunali.class);
                        intent.putExtra("chakanshangping",mData.get(position));
                        intent.putExtra("dengluuser",yonghu);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(goodlist.this,shangpin_xiangqing.class);
                        intent.putExtra("chakanshangping",mData.get(position));
                        intent.putExtra("dengluuser",yonghu);
                        startActivity(intent);
                    }

                }
            });
        }
        else mBack.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button3:
                finish();
                break;
        }
    }

    public static final String COLLECTION="collection";
    public static final String SETTING="setting";
    public static final String MINE="mine";
}
