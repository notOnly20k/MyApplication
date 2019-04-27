package com.example.administrator.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

// 评论数据库
@Table(database =shujuku.class)
public class pinlun extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private int pinlunid;
    @Column
    private int shangpingid;// 商品id
    @Column
    private int yonghuid; // 当前用户id
    @Column
    private Date pinglunshijian; // 时间
    @Column
    private int maijiaid; // 卖家id
    @Column
    private String neirong; //评论内容
    @Column
    private int zhuangtai;// 评论状态 // 未回复评论0 ，已回复评论1
    @ForeignKey(saveForeignKeyModel = false)
    private yonghu yonghu;
    @ForeignKey(saveForeignKeyModel = false)
    private shangpin shangpin;
    public int getPinlunid() {
        return pinlunid;
    }

    public void setPinlunid(int pinlunid) {
        this.pinlunid = pinlunid;
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
    private int replyTotal;
    private List<pinlunhuifu> replyList;

    public int getReplyTotal() {
        return replyTotal;
    }

    public void setReplyTotal(int replyTotal) {
        this.replyTotal = replyTotal;
    }

    public List<pinlunhuifu> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<pinlunhuifu> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return this.getYonghu()+""+this. getShangpingid()+""+this.getYonghuid();
    }
}
