package com.example.administrator.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.adapter.GoodslistAdapter;
import com.example.administrator.model.shangpin;
import com.example.administrator.model.shangpin_Table;
import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.util.DBService;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class shangpinliebiao extends AppCompatActivity {
    yonghu yonghu;
    ProgressDialog pd ;
    LinearLayout moren,renqi,shijian,jiagedi,jiagegao;
    TextView moren_text,renqi_text,shijian_text,jiagedi_text,jiagegao_text;
    ListView goodslist;
    private String type;
    LinearLayout backlayout;
    private OnClickListener listener;
    //定义颜色值
    private int Black = 0xFF000000;
    private int Red =0xFFFF0000;
    ArrayList<shangpin> mData= new ArrayList<shangpin>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpinliebiao);
        Intent intent = getIntent();
        yonghu = (yonghu) intent.getSerializableExtra("dengluuser");
        type = intent.getStringExtra("type");
        initview();
        initstate();
    }
    public void initview()
    {
        pd = new ProgressDialog(shangpinliebiao.this);
        pd.setMessage("宝贝加载中...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        backlayout = findViewById(R.id.back);

        moren = findViewById(R.id.moren);
        renqi = findViewById(R.id.renqi);
        shijian = findViewById(R.id.shijian);
        jiagedi = findViewById(R.id.jiagedi);
        jiagegao = findViewById(R.id.jiagegao);

        moren_text = findViewById(R.id.moren_text);
        renqi_text = findViewById(R.id.renqi_text);
        shijian_text = findViewById(R.id.shijian_text);
        jiagedi_text = findViewById(R.id.jiagedi_text);
        jiagegao_text = findViewById(R.id.jiagegao_text);

        listener = new OnClickListener();

        moren.setOnClickListener(listener);
        renqi.setOnClickListener(listener);
        shijian.setOnClickListener(listener);
        jiagedi.setOnClickListener(listener);
        jiagegao.setOnClickListener(listener);

        goodslist = findViewById(R.id.goodslist);
        show(getdata(type,"1"));
    }
    public void initstate()
    {
        moren_text.setTextColor(Red);
        renqi_text.setTextColor(Black);
        shijian_text.setTextColor(Black);
        jiagegao_text.setTextColor(Black);
        jiagedi_text.setTextColor(Black);
    }
    private class OnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.moren:
                    clearChioce();
                    moren_text.setTextColor(Red);
                    show(getdata(type,"1"));
                    break;
                case R.id.renqi:
                    clearChioce();
                    renqi_text.setTextColor(Red);
                    show(getdata(type,"2"));
                    break;
                case R.id.shijian:
                    clearChioce();
                    shijian_text.setTextColor(Red);
                    show(getdata(type,"3"));
                    break;
                case R.id.jiagedi:
                    clearChioce();
                    jiagedi_text.setTextColor(Red);
                    show(getdata(type,"4"));
                    break;
                case R.id.jiagegao:
                    clearChioce();
                    jiagegao_text.setTextColor(Red);
                    show(getdata(type,"5"));
                    break;
                default:
                    break;
            }
        }
    }
    //建立一个清空选中状态的方法
    public void clearChioce()
    {
        moren_text.setTextColor(Black);
        renqi_text.setTextColor(Black);
        shijian_text.setTextColor(Black);
        jiagegao_text.setTextColor(Black);
        jiagedi_text.setTextColor(Black);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    // 从数据库中查询数据
    public List<shangpin> getdata(final String type,final String state)
    {
        final List<shangpin> shangpins=new ArrayList<shangpin>();
        pd.show();
        mData.clear();
        DBService.getDbService().getGoodsData(new Pair<String, String>(type,state))
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
//        if(state.equals("1")){
//            shangpins= SQLite.select()
//                    .from(shangpin.class)
//                    .where(shangpin_Table.leibie.eq(type))
//                    .queryList();
//        }else if(state.equals("2")){
//            shangpins= SQLite.select()
//                    .from(shangpin.class)
//                    .where(shangpin_Table.leibie.eq(type))
//                    .orderBy(shangpin_Table.liulanrenshu,false)
//                    .queryList();
//        }else if(state.equals("3")){
//            shangpins= SQLite.select()
//                    .from(shangpin.class)
//                    .where(shangpin_Table.leibie.eq(type))
//                    .orderBy(shangpin_Table.shijian,false)
//                    .queryList();
//        }else if(state.equals("4")){
//            shangpins= SQLite.select()
//                    .from(shangpin.class)
//                    .where(shangpin_Table.leibie.eq(type))
//                    .orderBy(shangpin_Table.jiage,true)
//                    .queryList();
//        }else if(state.equals("5")){
//            shangpins= SQLite.select()
//                    .from(shangpin.class)
//                    .where(shangpin_Table.leibie.eq(type))
//                    .orderBy(shangpin_Table.jiage,false)
//                    .queryList();
//        }
        Log.i("条数",shangpins.size()+"");
        return shangpins;
    }
    public void show(List<shangpin> shangpins){
        pd.cancel();
        if(shangpins.size()>= 1)
        {
            mData.addAll(shangpins);
            GoodslistAdapter adapter = new GoodslistAdapter(shangpinliebiao.this,mData);
            adapter.notifyDataSetChanged();
            goodslist = findViewById(R.id.goodslist);
            goodslist.setAdapter(adapter);
            backlayout.setVisibility(View.INVISIBLE);
            goodslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                        long id) {
                    Intent intent = new Intent(shangpinliebiao.this,shangpin_xiangqing.class);
                    intent.putExtra("chakanshangping",mData.get(position));
                    intent.putExtra("dengluuser",yonghu);
                    startActivity(intent);
                }
            });
        }
        else backlayout.setVisibility(View.VISIBLE);

    }
    // 返回按钮键触发的事件
    public void fanhui(android.view.View v){
        this.finish();
        Intent it=new Intent(shangpinliebiao.this,fenlei.class);
        it.putExtra("dengluuser",yonghu);
        startActivity(it);

    }
}
