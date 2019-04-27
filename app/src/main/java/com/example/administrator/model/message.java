package com.example.administrator.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database =shujuku.class)
public class message extends BaseModel {
    public static final int TYPE_BLE = 0;
    public static final int TYPE_PHONE = 1;

    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String content; //内容
    @Column
    private String time; //留言时间
    @Column
    private int receiverId; // 接收者id
    @Column
    private int sendId; // 发送者id
    private yonghu jieshouyonghu;
    private yonghu fasongyonghu;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public yonghu getJieshouyonghu() {
        return jieshouyonghu;
    }

    public void setJieshouyonghu(yonghu jieshouyonghu) {
        this.jieshouyonghu = jieshouyonghu;
    }

    public yonghu getFasongyonghu() {
        return fasongyonghu;
    }

    public void setFasongyonghu(yonghu fasongyonghu) {
        this.fasongyonghu = fasongyonghu;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "_id=" + id +
                ", content='" + content + '\'' +
                ", receiverId=" + receiverId+
                ", time='" + time + '\'' +
                '}';
    }
}
