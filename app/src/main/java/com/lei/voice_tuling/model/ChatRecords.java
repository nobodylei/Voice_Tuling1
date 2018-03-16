package com.lei.voice_tuling.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yanle on 2018/3/15.
 */
@Entity
public class ChatRecords {

    @Id
    private Long id;
    @Property
    private int textOrVoice;
    @Property
    private int who;
    @Property
    private Date date;
    @Property
    private String text;

    public ChatRecords(int textOrVoice, int who, Date date, String text) {
        this.textOrVoice = textOrVoice;
        this.who = who;
        this.date = date;
        this.text = text;
    }

    @Generated(hash = 837070394)
    public ChatRecords(Long id, int textOrVoice, int who, Date date, String text) {
        this.id = id;
        this.textOrVoice = textOrVoice;
        this.who = who;
        this.date = date;
        this.text = text;
    }
    @Generated(hash = 128109854)
    public ChatRecords() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getTextOrVoice() {
        return this.textOrVoice;
    }
    public void setTextOrVoice(int textOrVoice) {
        this.textOrVoice = textOrVoice;
    }
    public int getWho() {
        return this.who;
    }
    public void setWho(int who) {
        this.who = who;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
