package com.example.administrator.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

// 评论回复数据库
@Table(database =shujuku.class)
public class pinlunhuifu extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private int id;// 回复评论id
    @Column
    private int shangpingid;// 商品id
    @Column
    private int yonghuid; // 当前用户id
    @Column
    private Date pinglunshijian; // 时间
    @Column
    private int maijiaid; // 卖家id
    @Column
    private int pinlunid; // 对应的评论id
    @Column
    private String neirong; //评论内容
    @Column
    private int zhuangtai;// 评论状态 // 未回复评论0 ，已回复评论1
    @ForeignKey(saveForeignKeyModel = false)
    private yonghu yonghu;
    @ForeignKey(saveForeignKeyModel = false)
    private shangpin shangpin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShangpingid() {
        return shangpingid;
    }

    public void setShangpingid(int shangpingid) {
        this.shangpingid = shangpingid;
    }

    public int getYonghuid() {
        return yonghuid;
    }

    public void setYonghuid(int yonghuid) {
        this.yonghuid = yonghuid;
    }

    public Date getPinglunshijian() {
        return pinglunshijian;
    }

    public void setPinglunshijian(Date pinglunshijian) {
        this.pinglunshijian = pinglunshijian;
    }

    public int getMaijiaid() {
        return maijiaid;
    }

    public void setMaijiaid(int maijiaid) {
        this.maijiaid = maijiaid;
    }

    public int getPinlunid() {
        return pinlunid;
    }

    public void setPinlunid(int pinlunid) {
        this.pinlunid = pinlunid;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public int getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(int zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public com.example.administrator.model.yonghu getYonghu() {
        return yonghu;
    }

    public void setYonghu(com.example.administrator.model.yonghu yonghu) {
        this.yonghu = yonghu;
    }

    public com.example.administrator.model.shangpin getShangpin() {
        return shangpin;
    }

    public void setShangpin(com.example.administrator.model.shangpin shangpin) {
        this.shangpin = shangpin;
    }
}
