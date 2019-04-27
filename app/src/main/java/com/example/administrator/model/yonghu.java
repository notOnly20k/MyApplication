package com.example.administrator.model;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ManyToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

@Table(database =shujuku.class)
@ManyToMany(referencedTable = shangpin.class)
// 用户数据表
public class yonghu extends BaseModel  implements Serializable {
    @PrimaryKey(autoincrement = true)
    private int yonghuid;
    @Column
    private String xuehao;
    @Column
    private String xingming;
    @Column
    private String mima;
    @Column
    private String lianxidianhua;
    @Column
    private String xingbie;
    @Column
    private String dizhi;
    @Column
    private String type;

    public int getYonghuid() {
        return yonghuid;
    }

    public void setYonghuid(int yonghuid) {
        this.yonghuid = yonghuid;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getLianxidianhua() {
        return lianxidianhua;
    }

    public void setLianxidianhua(String lianxidianhua) {
        this.lianxidianhua = lianxidianhua;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
