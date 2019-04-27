package com.example.administrator.app;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;
import com.example.administrator.model.yonghu_Table;
import com.example.administrator.myapplication.mima;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

public class MyApp  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化DBFLOW
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        FlowManager.init(this);
        chushihua();
    }
    public void chushihua(){
        List<shangpin> shangpins = SQLite.select()
                .from(shangpin.class).queryList();
        Log.i("条数",shangpins.size()+"");
        if(shangpins.size()==0){
            shangpin shangpin=new shangpin();
            shangpin.setJiage(1.1);
            shangpin.setLeibie("化妆品");
            shangpin.setLiulanrenshu(90);
            shangpin.setMincheng("测试商品1");
            shangpin.setQuhuofangshi("快递");
            shangpin.setShifoujiajia(1);
            shangpin.setShijian(new Date());
            shangpin.setTupian("https://img13.360buyimg.com/n1/jfs/t1/11669/17/7746/134003/5c63e086E9bf0abfb/df3ecf7a5920d80c.jpg");
            shangpin.setXijie("商品详情");
            shangpin.setXingjiuchengdu(9.5);
            shangpin.setYonghuid(1);
            shangpin.setYuanjia(100);

            shangpin.save();

            shangpin shangpin90=new shangpin();
            shangpin90.setJiage(2.1);
            shangpin90.setLeibie("化妆品");
            shangpin90.setLiulanrenshu(90);
            shangpin90.setMincheng("测试商品2");
            shangpin90.setQuhuofangshi("快递");
            shangpin90.setShifoujiajia(1);
            shangpin90.setShijian(new Date());
            shangpin90.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin90.setXijie("商品详情");
            shangpin90.setXingjiuchengdu(9.5);
            shangpin90.setYonghuid(1);
            shangpin90.setYuanjia(100);

            shangpin90.save();
            shangpin shangpin1=new shangpin();
            shangpin1.setJiage(3.1);
            shangpin1.setLeibie("化妆品");
            shangpin1.setLiulanrenshu(90);
            shangpin1.setMincheng("测试商品3");
            shangpin1.setQuhuofangshi("快递");
            shangpin1.setShifoujiajia(1);
            shangpin1.setShijian(new Date());
            shangpin1.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin1.setXijie("商品详情");
            shangpin1.setXingjiuchengdu(9.5);
            shangpin1.setYonghuid(1);
            shangpin1.setYuanjia(100);

            shangpin1.save();
            shangpin shangpin2=new shangpin();
            shangpin2.setJiage(4.1);
            shangpin2.setLeibie("化妆品");
            shangpin2.setLiulanrenshu(90);
            shangpin2.setMincheng("测试商品4");
            shangpin2.setQuhuofangshi("快递");
            shangpin2.setShifoujiajia(1);
            shangpin2.setShijian(new Date());
            shangpin2.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin2.setXijie("商品详情");
            shangpin2.setXingjiuchengdu(9.5);
            shangpin2.setYonghuid(1);
            shangpin2.setYuanjia(100);

            shangpin2.save();
            shangpin shangpin3=new shangpin();
            shangpin3.setJiage(5.1);
            shangpin3.setLeibie("化妆品");
            shangpin3.setLiulanrenshu(90);
            shangpin3.setMincheng("测试商品5");
            shangpin3.setQuhuofangshi("快递");
            shangpin3.setShifoujiajia(1);
            shangpin3.setShijian(new Date());
            shangpin3.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin3.setXijie("商品详情");
            shangpin3.setXingjiuchengdu(9.5);
            shangpin3.setYonghuid(1);
            shangpin3.setYuanjia(100);

            shangpin3.save();
            shangpin shangpin4=new shangpin();
            shangpin4.setJiage(6.1);
            shangpin4.setLeibie("化妆品");
            shangpin4.setLiulanrenshu(90);
            shangpin4.setMincheng("测试商品6");
            shangpin4.setQuhuofangshi("快递");
            shangpin4.setShifoujiajia(1);
            shangpin4.setShijian(new Date());
            shangpin4.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin4.setXijie("商品详情");
            shangpin4.setXingjiuchengdu(9.5);
            shangpin4.setYonghuid(1);
            shangpin4.setYuanjia(100);

            shangpin4.save();
            shangpin shangpin5=new shangpin();
            shangpin5.setJiage(7.1);
            shangpin5.setLeibie("化妆品");
            shangpin5.setLiulanrenshu(90);
            shangpin5.setMincheng("测试商品7");
            shangpin5.setQuhuofangshi("快递");
            shangpin5.setShifoujiajia(1);
            shangpin5.setShijian(new Date());
            shangpin5.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin5.setXijie("商品详情");
            shangpin5.setXingjiuchengdu(9.5);
            shangpin5.setYonghuid(1);
            shangpin5.setYuanjia(100);

            shangpin5.save();
            shangpin shangpin6=new shangpin();
            shangpin6.setJiage(8.1);
            shangpin6.setLeibie("化妆品");
            shangpin6.setLiulanrenshu(90);
            shangpin6.setMincheng("测试商品8");
            shangpin6.setQuhuofangshi("快递");
            shangpin6.setShifoujiajia(1);
            shangpin6.setShijian(new Date());
            shangpin6.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin6.setXijie("商品详情");
            shangpin6.setXingjiuchengdu(9.5);
            shangpin6.setYonghuid(1);
            shangpin6.setYuanjia(100);
            shangpin6.save();
            shangpin shangpin7=new shangpin();
            shangpin7.setJiage(9.1);
            shangpin7.setLeibie("化妆品");
            shangpin7.setLiulanrenshu(90);
            shangpin7.setMincheng("测试商品9");
            shangpin7.setQuhuofangshi("快递");
            shangpin7.setShifoujiajia(1);
            shangpin7.setShijian(new Date());
            shangpin7.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin7.setXijie("商品详情");
            shangpin7.setXingjiuchengdu(9.5);
            shangpin7.setYonghuid(1);
            shangpin7.setYuanjia(100);

            shangpin7.save();
            shangpin shangpin8=new shangpin();
            shangpin8.setJiage(10.1);
            shangpin8.setLeibie("化妆品");
            shangpin8.setLiulanrenshu(90);
            shangpin8.setMincheng("测试商品10");
            shangpin8.setQuhuofangshi("快递");
            shangpin8.setShifoujiajia(1);
            shangpin8.setShijian(new Date());
            shangpin8.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin8.setXijie("商品详情");
            shangpin8.setXingjiuchengdu(9.5);
            shangpin8.setYonghuid(1);
            shangpin8.setYuanjia(100);

            shangpin8.save();
            shangpin shangpin9=new shangpin();
            shangpin9.setJiage(11.1);
            shangpin9.setLeibie("玩具");
            shangpin9.setLiulanrenshu(90);
            shangpin9.setMincheng("测试商品1");
            shangpin9.setQuhuofangshi("快递");
            shangpin9.setShifoujiajia(1);
            shangpin9.setShijian(new Date());
            shangpin9.setTupian("https://img12.360buyimg.com//n0/jfs/t1/28000/28/11660/111268/5c906504E55f685cb/a5a8a37a2588d180.jpg");
            shangpin9.setXijie("商品详情");
            shangpin9.setXingjiuchengdu(9.5);
            shangpin9.setYonghuid(1);
            shangpin9.setYuanjia(100);
            shangpin9.save();
        }

    }
}
