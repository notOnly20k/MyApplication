package com.example.administrator.util;

import android.util.Log;
import android.util.Pair;

import com.example.administrator.model.Order;
import com.example.administrator.model.pinlun;
import com.example.administrator.model.pinlunhuifu;
import com.example.administrator.model.shangpin;
import com.example.administrator.model.yonghu;

import org.w3c.dom.ls.LSException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class DBService {
    private Connection conn = null; //打开数据库对象
    private PreparedStatement ps = null;//操作整合sql语句的对象
    private ResultSet rs = null;//查询结果的集合

    //DBService 对象
    public static DBService dbService = null;

    // 构造方法，私有化
    private DBService() {

    }


    //获取MySQL数据库单例类对象
    public static DBService getDbService() {
        if (dbService == null) {
            dbService = new DBService();
        }
        return dbService;
    }

    // 插入数据
    public Observable<Integer> insertUserData(yonghu user) {
        return Observable.just(user)
                .map(new Function<yonghu, Integer>() {

                    @Override
                    public Integer apply(yonghu user) throws Exception {
                        int result = -1;
                        conn = DBOpenHelper.getConn();
                        String sql = "INSERT INTO yonghu (xuehao,lianxidianhua,xingbie,mima,xingming) VALUES (?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                        String xuehao = user.getXuehao();
                        String lianxidianhua = user.getLianxidianhua();
                        String xingbie = user.getXingbie();
                        String mima = user.getMima();
                        String xingming = user.getXingming();
                        ps.setString(1, xuehao);//第一个参数 name 规则同上
                        ps.setString(2, lianxidianhua);//第二个参数 phone 规则同上
                        ps.setString(3, xingbie);//第三个参数 content 规则同上
                        ps.setString(4, mima);//第四个参数 state 规则同上
                        ps.setString(5, xingming);//第四个参数 state 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });

    }

    public Observable<Integer> insertGoodsData(shangpin good) {
        return Observable.just(good)
                .map(new Function<shangpin, Integer>() {

                    @Override
                    public Integer apply(shangpin good) throws Exception {
                        int result = -1;
                        conn = DBOpenHelper.getConn();
                        String sql = "INSERT INTO shangpin (mincheng,yonghuid,shijian,leibie,xijie,jiage,yuanjia,tupian,quhuofangshi,xingjiuchengdu) " +
                                "VALUES (?,?,?,?,?,?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, good.getMincheng());//第一个参数 name 规则同上
                        ps.setInt(2, good.getYonghuid());//第二个参数 phone 规则同上
                        ps.setDate(3, new Date(good.getShijian().getTime()) );//第三个参数 content 规则同上
                        ps.setString(4, good.getLeibie());//第四个参数 state 规则同上
                        ps.setString(5, good.getXijie());//第四个参数 state 规则同上
                        ps.setDouble(6, good.getJiage());//第二个参数 phone 规则同上
                        ps.setDouble(7, good.getYuanjia());//第三个参数 content 规则同上
                        ps.setString(8, good.getTupian());//第四个参数 state 规则同上
                        ps.setString(9, good.getQuhuofangshi());//第四个参数 state 规则同上
                        ps.setDouble(10, good.getXingjiuchengdu());//第四个参数 state 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });

    }

    public Observable<Integer> updateGoodsData(shangpin good) {
        return Observable.just(good)
                .map(new Function<shangpin, Integer>() {

                    @Override
                    public Integer apply(shangpin good) throws Exception {
                        int result = -1;
                        conn = DBOpenHelper.getConn();
//                        UPDATE `secondhand`.`shangpin` SET `mincheng`='111112' WHERE `shangpinid`='2';
//
                        String sql = "UPDATE shangpin set mincheng=?,yonghuid=?,shijian=?,leibie=?,xijie=?,jiage=?,yuanjia=?,tupian=?,quhuofangshi=?,xingjiuchengdu=? where shangpinid =? ";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, good.getMincheng());//第一个参数 name 规则同上
                        ps.setInt(2, good.getYonghuid());//第二个参数 phone 规则同上
                        ps.setDate(3, new Date(good.getShijian().getTime()) );//第三个参数 content 规则同上
                        ps.setString(4, good.getLeibie());//第四个参数 state 规则同上
                        ps.setString(5, good.getXijie());//第四个参数 state 规则同上
                        ps.setDouble(6, good.getJiage());//第二个参数 phone 规则同上
                        ps.setDouble(7, good.getYuanjia());//第三个参数 content 规则同上
                        ps.setString(8, good.getTupian());//第四个参数 state 规则同上
                        ps.setString(9, good.getQuhuofangshi());//第四个参数 state 规则同上
                        ps.setDouble(10, good.getXingjiuchengdu());//第四个参数 state 规则同上
                        ps.setDouble(11, good.getShangpinid());//第四个参数 state 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });

    }


    public Observable<List<shangpin>> getGoodsData(final Pair<String,String> pair) {
        return Observable.just(pair)
                .map(new Function<Pair, List<shangpin>>() {

                    @Override
                    public List<shangpin> apply(Pair pair1) throws Exception {
                       List<shangpin>list=new ArrayList<>();
                        conn = DBOpenHelper.getConn();
                        String type=pair.first;
                        String state=pair.second;
                        String order="";
                        order="";
                        switch (state){
                            case "2":
                                order="order by liulanrenshu";
                                break;
                            case "3":
                                order="order by shijian";
                                break;
                            case "4":
                                order="order by jiage ";
                                break;
                            case "5":
                                order="order by jiage DESC";
                                break;
                        }
                        String sql = "SELECT * FROM shangpin inner join yonghu where shangpin.yonghuid = yonghu.yonghuid "+ "and leibie = ? "+order;
                        Log.e("sql",sql);
                        ps = conn.prepareStatement(sql);
                        if (ps!=null){
                            ps.setString(1,type);
                            rs = ps.executeQuery();
                            while (rs.next()){
                                shangpin good=new shangpin();
                                yonghu u = new yonghu();
                                u.setDizhi(rs.getString("dizhi"));
                                u.setLianxidianhua(rs.getString("lianxidianhua"));
                                u.setType(rs.getString("type"));
                                u.setXingbie(rs.getString("xingbie"));
                                u.setXuehao(rs.getString("xuehao"));
                                u.setYonghuid(rs.getInt("yonghuid"));
                                u.setXingming(rs.getString("xingming"));
                                good.setJiage(rs.getDouble("jiage"));
                                good.setLeibie(rs.getString("leibie"));
                                good.setLiulanrenshu(rs.getInt("liulanrenshu"));
                                good.setMincheng(rs.getString("mincheng"));
                                good.setQuhuofangshi(rs.getString("quhuofangshi"));
                                good.setShifoujiajia(rs.getInt("shifoujiajia"));
                                good.setTupian(rs.getString("tupian"));
                                good.setShangpinid(rs.getInt("shangpinid"));
                                good.setZhuangtai(rs.getInt("zhuangtai"));
                                good.setXingjiuchengdu(rs.getDouble("xingjiuchengdu"));
                                good.setXijie(rs.getString("xijie"));
                                good.setYuanjia(rs.getDouble("yuanjia"));
                                good.setYonghuid(rs.getInt("yonghuid"));
                                good.setShijian(rs.getDate("shijian"));
                                good.setYonghu(u);
                                list.add(good);
                            }
                        }
                        DBOpenHelper.closeAll(conn, ps);
                        return list;
                    }
                }).onErrorReturn(new Function<Throwable,  List<shangpin>>() {
                    @Override
                    public  List<shangpin> apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return new ArrayList<>();
                    }
                });

    }




    // 获取数据
    public Observable<Optional<yonghu>> getUserData(final yonghu user) {
        return Observable.just(user)
                .map(new Function<yonghu, Optional<yonghu>>() {
                    @Override
                    public Optional apply(yonghu yonghu) throws Exception {
                        conn = DBOpenHelper.getConn();
                        String sql = "select * from yonghu where xuehao = ? and mima = ?";
                        if (conn != null && (!conn.isClosed())) {
                            ps = (PreparedStatement) conn.prepareStatement(sql);
                            ps.setString(1, user.getXuehao());
                            ps.setString(2, user.getMima());
                            if (ps != null) {
                                rs = ps.executeQuery();
                                if (rs != null) {
                                    while (rs.next()) {
                                        yonghu u = new yonghu();
                                        u.setDizhi(rs.getString("dizhi"));
                                        u.setLianxidianhua(rs.getString("lianxidianhua"));
                                        u.setType(rs.getString("type"));
                                        u.setXingbie(rs.getString("xingbie"));
                                        u.setXuehao(rs.getString("xuehao"));
                                        u.setYonghuid(rs.getInt("yonghuid"));
                                        u.setXingming(rs.getString("xingming"));
                                        u.setMima(rs.getString("mima"));
                                        return Optional.of(u);
                                    }
                                }
                            }
                        }
                        return Optional.empty();
                    }
                }).onErrorReturn(new Function<Throwable, Optional<yonghu>>() {
                    @Override
                    public Optional apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return Optional.empty();
                    }
                });
    }

    public Observable<Integer> updateUserData(final yonghu user) {
        return Observable.just(user)
                .map(new Function<yonghu, Integer>() {

                    @Override
                    public Integer apply(yonghu user) throws Exception {
                        int result = -1;
                        conn = DBOpenHelper.getConn();
                        String sql="update yonghu set lianxidianhua=? ,xingming=?,mima=? where yonghuid=?";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, user.getLianxidianhua());//第一个参数 name 规则同上
                        ps.setString(2, user.getXingming());//第二个参数 phone 规则同上
                        ps.setString(3, user.getMima());//第二个参数 phone 规则同上
                        ps.setInt(4, user.getYonghuid());//第三个参数 content 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }

    public Observable<Integer> insertComment(pinlun comment) {
        return Observable.just(comment)
                .map(new Function<pinlun, Integer>() {

                    @Override
                    public Integer apply(pinlun comment) throws Exception {
                        int result = -1;
                        conn = DBOpenHelper.getConn();
                        String sql="INSERT INTO pinlun (shangpingid,yonghuid,pinglunshijian,maijiaid,neirong,zhuangtai) VALUES (?,?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, comment.getShangpingid());//第一个参数 name 规则同上
                        ps.setInt(2, comment.getYonghuid());//第二个参数 phone 规则同上
                        ps.setDate(3, new Date(comment.getPinglunshijian().getTime()));//第二个参数 phone 规则同上
                        ps.setInt(4, comment.getMaijiaid());//第三个参数 content 规则同上
                        ps.setString(5, comment.getNeirong());//第三个参数 content 规则同上
                        ps.setInt(6, comment.getZhuangtai());//第三个参数 content 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }

    public Observable<List<pinlun>> getCommentData(int id) {
        return Observable.just(id)
                .map(new Function<Integer, List<pinlun>>() {

                    @Override
                    public List<pinlun> apply(Integer id) throws Exception {
                        List<pinlun>list=new ArrayList<>();
                        conn = DBOpenHelper.getConn();
                        String sql = "SELECT * FROM pinlun inner join yonghu where pinlun.yonghuid = yonghu.yonghuid and shangpingid = ? ";
                        ps = conn.prepareStatement(sql);
                        if (ps!=null){
                            ps.setInt(1,id);
                            rs = ps.executeQuery();
                            while (rs.next()){
                                yonghu u = new yonghu();
                                u.setDizhi(rs.getString("dizhi"));
                                u.setLianxidianhua(rs.getString("lianxidianhua"));
                                u.setType(rs.getString("type"));
                                u.setXingbie(rs.getString("xingbie"));
                                u.setXuehao(rs.getString("xuehao"));
                                u.setYonghuid(rs.getInt("yonghuid"));
                                u.setXingming(rs.getString("xingming"));
                                pinlun p = new pinlun();
                                p.setPinlunid(rs.getInt("pinlunid"));
                                p.setMaijiaid(rs.getInt("maijiaid"));
                                p.setNeirong(rs.getString("neirong"));
                                p.setPinglunshijian(rs.getDate("pinglunshijian"));
                                p.setShangpingid(rs.getInt("shangpingid"));
                                p.setZhuangtai(rs.getInt("zhuangtai"));
                                p.setYonghuid(rs.getInt("yonghuid"));
                                p.setYonghu(u);
                                list.add(p);
                            }
                        }
                        DBOpenHelper.closeAll(conn, ps);
                        return list;
                    }
                }).onErrorReturn(new Function<Throwable,  List<pinlun>>() {
                    @Override
                    public  List<pinlun> apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return new ArrayList<>();
                    }
                });

    }

    public Observable<Integer> insertReplay(pinlunhuifu comment) {
        return Observable.just(comment)
                .map(new Function<pinlunhuifu, Integer>() {

                    @Override
                    public Integer apply(pinlunhuifu comment) throws Exception {
                        int result=-1;
                        conn = DBOpenHelper.getConn();
                        String sql="INSERT INTO pinlunhuifu (shangpingid,yonghuid,pinglunshijian,maijiaid,neirong,zhuangtai,pinlunid) VALUES (?,?,?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, comment.getShangpingid());//第一个参数 name 规则同上
                        ps.setInt(2, comment.getYonghuid());//第二个参数 phone 规则同上
                        ps.setDate(3, new Date(comment.getPinglunshijian().getTime()));//第二个参数 phone 规则同上
                        ps.setInt(4, comment.getMaijiaid());//第三个参数 content 规则同上
                        ps.setString(5, comment.getNeirong());//第三个参数 content 规则同上
                        ps.setInt(6, comment.getZhuangtai());//第三个参数 content 规则同上
                        ps.setInt(7, comment.getPinlunid());//第三个参数 content 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }


    public Observable<List<shangpin>> getMyGoodsData(int id) {
        return Observable.just(id)
                .map(new Function<Integer, List<shangpin>>() {
                    @Override
                    public List<shangpin> apply(Integer id) throws Exception {
                        List<shangpin>list=new ArrayList<>();
                        conn = DBOpenHelper.getConn();
                        String sql = "SELECT * FROM shangpin inner join yonghu where shangpin.yonghuid = yonghu.yonghuid and shangpin.yonghuid = ? ";
                        Log.e("sql",sql);
                        ps = conn.prepareStatement(sql);
                        if (ps!=null){
                            ps.setInt(1,id);
                            rs = ps.executeQuery();
                            while (rs.next()){
                                Log.e("getGoodsData",rs.getDouble("jiage")+"");
                                shangpin good=new shangpin();
                                yonghu u = new yonghu();
                                u.setDizhi(rs.getString("dizhi"));
                                u.setLianxidianhua(rs.getString("lianxidianhua"));
                                u.setType(rs.getString("type"));
                                u.setXingbie(rs.getString("xingbie"));
                                u.setXuehao(rs.getString("xuehao"));
                                u.setYonghuid(rs.getInt("yonghuid"));
                                u.setXingming(rs.getString("xingming"));
                                good.setJiage(rs.getDouble("jiage"));
                                good.setLeibie(rs.getString("leibie"));
                                good.setLiulanrenshu(rs.getInt("liulanrenshu"));
                                good.setMincheng(rs.getString("mincheng"));
                                good.setQuhuofangshi(rs.getString("quhuofangshi"));
                                good.setShifoujiajia(rs.getInt("shifoujiajia"));
                                good.setTupian(rs.getString("tupian"));
                                good.setShangpinid(rs.getInt("shangpinid"));
                                good.setZhuangtai(rs.getInt("zhuangtai"));
                                good.setXingjiuchengdu(rs.getDouble("xingjiuchengdu"));
                                good.setXijie(rs.getString("xijie"));
                                good.setYuanjia(rs.getDouble("yuanjia"));
                                good.setYonghuid(rs.getInt("yonghuid"));
                                good.setShijian(rs.getDate("shijian"));
                                good.setYonghu(u);
                                list.add(good);
                            }
                        }
                        DBOpenHelper.closeAll(conn, ps);
                        return list;
                    }
                }).onErrorReturn(new Function<Throwable,  List<shangpin>>() {
                    @Override
                    public  List<shangpin> apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return new ArrayList<>();
                    }
                });

    }

    public Observable<List<shangpin>> getCollectGoodsData(int id) {
        return Observable.just(id)
                .map(new Function<Integer, List<shangpin>>() {
                    @Override
                    public List<shangpin> apply(Integer id) throws Exception {
                        List<shangpin>list=new ArrayList<>();
                        conn = DBOpenHelper.getConn();
                        String sql = "SELECT * FROM shangpin inner join yonghu where shangpin.yonghuid = yonghu.yonghuid and shangpinid in (select goodId from user2good where userId = ?)";
                        Log.e("sql",sql);
                        ps = conn.prepareStatement(sql);
                        if (ps!=null){
                            ps.setInt(1,id);
                            rs = ps.executeQuery();
                            while (rs.next()){
                                Log.e("getGoodsData",rs.getDouble("jiage")+"");
                                shangpin good=new shangpin();
                                yonghu u = new yonghu();
                                u.setDizhi(rs.getString("dizhi"));
                                u.setLianxidianhua(rs.getString("lianxidianhua"));
                                u.setType(rs.getString("type"));
                                u.setXingbie(rs.getString("xingbie"));
                                u.setXuehao(rs.getString("xuehao"));
                                u.setYonghuid(rs.getInt("yonghuid"));
                                u.setXingming(rs.getString("xingming"));
                                good.setJiage(rs.getDouble("jiage"));
                                good.setLeibie(rs.getString("leibie"));
                                good.setLiulanrenshu(rs.getInt("liulanrenshu"));
                                good.setMincheng(rs.getString("mincheng"));
                                good.setQuhuofangshi(rs.getString("quhuofangshi"));
                                good.setShifoujiajia(rs.getInt("shifoujiajia"));
                                good.setTupian(rs.getString("tupian"));
                                good.setShangpinid(rs.getInt("shangpinid"));
                                good.setZhuangtai(rs.getInt("zhuangtai"));
                                good.setXingjiuchengdu(rs.getDouble("xingjiuchengdu"));
                                good.setXijie(rs.getString("xijie"));
                                good.setYuanjia(rs.getDouble("yuanjia"));
                                good.setYonghuid(rs.getInt("yonghuid"));
                                good.setShijian(rs.getDate("shijian"));
                                good.setYonghu(u);
                                list.add(good);
                            }
                        }
                        DBOpenHelper.closeAll(conn, ps);
                        return list;
                    }
                }).onErrorReturn(new Function<Throwable,  List<shangpin>>() {
                    @Override
                    public  List<shangpin> apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return new ArrayList<>();
                    }
                });

    }


    public Observable<Integer> collectionGood(final Pair<Integer,Integer> pair) {
        return Observable.just(pair)
                .map(new Function<Pair, Integer>() {

                    @Override
                    public Integer apply(Pair comment) throws Exception {
                        int result=-1;
                        conn = DBOpenHelper.getConn();
                        String sql="INSERT INTO user2good (userId,goodId) VALUES (?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, pair.first);//第一个参数 name 规则同上
                        ps.setInt(2, pair.second);//第二个参数 phone 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }

    public Observable<Integer> delcollectionGood(final Pair<Integer,Integer> pair) {
        return Observable.just(pair)
                .map(new Function<Pair, Integer>() {

                    @Override
                    public Integer apply(Pair comment) throws Exception {
                        int result=-1;
                        conn = DBOpenHelper.getConn();
                        String sql="delete  from  user2good where userId =? and goodId =?";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, pair.first);//第一个参数 name 规则同上
                        ps.setInt(2, pair.second);//第二个参数 phone 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }

    public Observable<Integer> delGood(int id) {
        return Observable.just(id)
                .map(new Function<Integer, Integer>() {

                    @Override
                    public Integer apply(Integer id) throws Exception {
                        int result=-1;
                        conn = DBOpenHelper.getConn();
                        String sql="delete  from  shangpin where shangpinid =?";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1,id);//第一个参数 name 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }


    public Observable<Integer> insertOrder(Order order) {
        return Observable.just(order)
                .map(new Function<Order, Integer>() {

                    @Override
                    public Integer apply(Order order) throws Exception {
                        int result=-1;
                        conn = DBOpenHelper.getConn();
                        String sql = "INSERT INTO good_order (goodName,buyerName,tel,address,sellerId,status,createTime) VALUES (?,?,?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, order.getGoodName());//第一个参数 name 规则同上
                        ps.setString(2, order.getBuyerName());//第二个参数 phone 规则同上
                        ps.setString(3, order.getTel());//第三个参数 content 规则同上
                        ps.setString(4, order.getAddress());//第四个参数 state 规则同上
                        ps.setInt(5, order.getSellerId());//第四个参数 state 规则同上
                        ps.setInt(6, order.getStatus());//第四个参数 state 规则同上
                        ps.setDate(7, new Date(order.getCreateTime().getTime()));//第四个参数 state 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }

    public Observable<Integer> updateOrderStatus(Order order) {
        return Observable.just(order)
                .map(new Function<Order, Integer>() {

                    @Override
                    public Integer apply(Order order) throws Exception {
                        int result=-1;
                        conn = DBOpenHelper.getConn();
                        String sql = "update good_order set status=? where id= ?";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, order.getStatus());//第一个参数 name 规则同上
                        ps.setInt(2, order.getId());//第二个参数 phone 规则同上
                        result = ps.executeUpdate();//返回1 执行成功
                        DBOpenHelper.closeAll(conn, ps);
                        return result;
                    }
                }).onErrorReturn(new Function<Throwable, Integer>() {
                    @Override
                    public Integer apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return -1;
                    }
                });
    }
    public Observable<List<Order>> getOrderList(int id) {
        return Observable.just(id)
                .map(new Function<Integer, List<Order>>() {
                    @Override
                    public List<Order> apply(Integer id) throws Exception {
                        List<Order>list=new ArrayList<>();
                        conn = DBOpenHelper.getConn();
                        String sql = "SELECT * FROM good_order where sellerId =?";
                        Log.e("sql",sql);
                        ps = conn.prepareStatement(sql);
                        if (ps!=null){
                            ps.setInt(1,id);
                            rs = ps.executeQuery();
                            while (rs.next()){
                                Order order=new Order();
                                yonghu u = new yonghu();
                               order.setAddress(rs.getString("address"));
                               order.setBuyerName(rs.getString("buyerName"));
                               order.setCreateTime(rs.getDate("createTime"));
                               order.setGoodName(rs.getString("goodName"));
                               order.setId(rs.getInt("id"));
                               order.setSellerId(rs.getInt("sellerId"));
                               order.setStatus(rs.getInt("status"));
                               order.setTel(rs.getString("tel"));
                                list.add(order);
                            }
                        }
                        DBOpenHelper.closeAll(conn, ps);
                        return list;
                    }
                }).onErrorReturn(new Function<Throwable,  List<Order>>() {
                    @Override
                    public  List<Order> apply(Throwable throwable) {
                        throwable.printStackTrace();
                        DBOpenHelper.closeAll(conn, ps);
                        return new ArrayList<>();
                    }
                });

    }





}
