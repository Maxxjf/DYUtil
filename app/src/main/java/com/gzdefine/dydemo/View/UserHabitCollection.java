package com.gzdefine.dydemo.View;

import java.io.Serializable;

/**
 * TODO：
 * Created by Max on 2017/9/19.
 */
public class UserHabitCollection implements Serializable {
    //设备标识
    private String uuid;
    //登录用户id
    private String user;
//用户名
private String user_name;
//操作备注
private String operation;// read
    //消息类型
    private String operate_type;// 1 2 3 4 5.
    //开始阅读时间
    private String operate_time;//时间戳字符串
    //阅读停留时间 单位秒
    private String stay;
//网络状态
private String network;
//地址
private String location;



//手机型号
private String device;
//操作系统版本
private String android;
//客户端版本
private String version;

    //页面名字
    private String actName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    @Override
    public String toString() {
        return "UserHabitCollection{" +
                "uuid='" + uuid + '\'' +
                ", user='" + user + '\'' +
                ", user_name='" + user_name + '\'' +
                ", operation='" + operation + '\'' +
                ", operate_type='" + operate_type + '\'' +
                ", operate_time='" + operate_time + '\'' +
                ", stay='" + stay + '\'' +
                ", network='" + network + '\'' +
                ", location='" + location + '\'' +
                ", device='" + device + '\'' +
                ", android='" + android + '\'' +
                ", version='" + version + '\'' +
                ", actName='" + actName + '\'' +
                '}';
    }
}
