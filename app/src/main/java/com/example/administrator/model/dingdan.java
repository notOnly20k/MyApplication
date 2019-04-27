package com.example.administrator.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

@Table(database =shujuku.class)
public class dingdan extends BaseModel {
    @PrimaryKey(autoincrement = true)
    private int dingdanid; // 订单id
    @Column
    private String shouhuodizhi; // 收货地址
    @Column
    private String youbian; // 邮编
    @Column
    private String shouhuoren; // 收货人
    @Column
    private String dianhua; // 电话
    @Column
    private String yonghuliuyan; // 用户留言
    @Column
    private String xiadanshijian; // 下单时间
    @Column
    private String dingdanzhuangtai; //订单状态
    @Column
    private Date fahuoshijian; // 发货时间
    @Column
    private Date querengshouhuoshijian; // 确认收货时间
    @Column
    private int goumaizheid;// 购买者id
    @Column
    private int shangpingid; //货物id

    @ForeignKey(saveForeignKeyModel = false)
    private yonghu yonghu;
    @ForeignKey(saveForeignKeyModel = false)
    private shangpin shangpin;

    public int getDingdanid() {
        return dingdanid;
    }

    public void setDingdanid(int dingdanid) {
        this.dingdanid = dingdanid;
    }

    public String getShouhuodizhi() {
        return shouhuodizhi;
    }

    public void setShouhuodizhi(String shouhuodizhi) {
        this.shouhuodizhi = shouhuodizhi;
    }

    public String getYoubian() {
        return youbian;
    }

    public void setYoubian(String youbian) {
        this.youbian = youbian;
    }

    public String getShouhuoren() {
        return shouhuoren;
    }

    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getYonghuliuyan() {
        return yonghuliuyan;
    }

    public void setYonghuliuyan(String yonghuliuyan) {
        this.yonghuliuyan = yonghuliuyan;
    }

    public String getXiadanshijian() {
        return xiadanshijian;
    }

    public void setXiadanshijian(String xiadanshijian) {
        this.xiadanshijian = xiadanshijian;
    }

    public String getDingdanzhuangtai() {
        return dingdanzhuangtai;
    }

    public void setDingdanzhuangtai(String dingdanzhuangtai) {
        this.dingdanzhuangtai = dingdanzhuangtai;
    }

    public Date getFahuoshijian() {
        return fahuoshijian;
    }

    public void setFahuoshijian(Date fahuoshijian) {
        this.fahuoshijian = fahuoshijian;
    }

    public Date getQuerengshouhuoshijian() {
        return querengshouhuoshijian;
    }

    public void setQuerengshouhuoshijian(Date querengshouhuoshijian) {
        this.querengshouhuoshijian = querengshouhuoshijian;
    }

    public int getGoumaizheid() {
        return goumaizheid;
    }

    public void setGoumaizheid(int goumaizheid) {
        this.goumaizheid = goumaizheid;
    }

    public int getShangpingid() {
        return shangpingid;
    }

    public void setShangpingid(int shangpingid) {
        this.shangpingid = shangpingid;
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
