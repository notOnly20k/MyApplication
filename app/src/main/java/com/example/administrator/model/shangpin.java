package com.example.administrator.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

@Table(database =shujuku.class)
// 商品表
@ManyToMany(referencedTable = yonghu.class)
public class shangpin extends BaseModel implements Serializable {
    @PrimaryKey(autoincrement = true)
    private int shangpinid; // 商品id 主键
    @Column
    private String mincheng; // 商品名称
    @Column
    private int yonghuid;// 发布人
    @Column
    private Date shijian; // 发布时间
    @Column
    private String leibie; // 商品类别
    @Column
    private String xijie; //商品详情
    @Column
    private double jiage; //价格
    @Column
    private double yuanjia;// 商品入手价格
    @Column
    private String tupian; // 商品图片
    @Column
    private int zhuangtai; // 商品状态 1发布中 0 下架 2 交易成功
    @Column
    private int shifoujiajia; // 是否加急 1表示加急，2表示不加急
    @Column
    private String quhuofangshi; // 取货方式
    @Column
    private double xingjiuchengdu; // 继承性
    @Column
    private int liulanrenshu; // 浏览人数

    private yonghu yonghu;

    public com.example.administrator.model.yonghu getYonghu() {
        return yonghu;
    }

    public void setYonghu(com.example.administrator.model.yonghu yonghu) {
        this.yonghu = yonghu;
    }

    public int getShangpinid() {
        return shangpinid;
    }

    public void setShangpinid(int shangpinid) {
        this.shangpinid = shangpinid;
    }

    public String getMincheng() {
        return mincheng;
    }

    public void setMincheng(String mincheng) {
        this.mincheng = mincheng;
    }

    public int getYonghuid() {
        return yonghuid;
    }

    public void setYonghuid(int yonghuid) {
        this.yonghuid = yonghuid;
    }

    public Date getShijian() {
        return shijian;
    }

    public void setShijian(Date shijian) {
        this.shijian = shijian;
    }

    public String getLeibie() {
        return leibie;
    }

    public void setLeibie(String leibie) {
        this.leibie = leibie;
    }

    public String getXijie() {
        return xijie;
    }

    public void setXijie(String xijie) {
        this.xijie = xijie;
    }

    public double getJiage() {
        return jiage;
    }

    public void setJiage(double jiage) {
        this.jiage = jiage;
    }

    public double getYuanjia() {
        return yuanjia;
    }

    public void setYuanjia(double yuanjia) {
        this.yuanjia = yuanjia;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    public int getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(int zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public int getShifoujiajia() {
        return shifoujiajia;
    }

    public void setShifoujiajia(int shifoujiajia) {
        this.shifoujiajia = shifoujiajia;
    }

    public String getQuhuofangshi() {
        return quhuofangshi;
    }

    public void setQuhuofangshi(String quhuofangshi) {
        this.quhuofangshi = quhuofangshi;
    }

    public double getXingjiuchengdu() {
        return xingjiuchengdu;
    }

    public void setXingjiuchengdu(double xingjiuchengdu) {
        this.xingjiuchengdu = xingjiuchengdu;
    }

    public int getLiulanrenshu() {
        return liulanrenshu;
    }

    public void setLiulanrenshu(int liulanrenshu) {
        this.liulanrenshu = liulanrenshu;
    }
}
