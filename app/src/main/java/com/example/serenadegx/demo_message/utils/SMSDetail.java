package com.example.serenadegx.demo_message.utils;

public class SMSDetail {
    private String id;
    private String name;
    private String number;
    private String body;
    private String createTime;

    public SMSDetail() {
    }

    public SMSDetail(String id, String name, String number, String body, String createTime) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.body = body;
        this.createTime = createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getBody() {
        return body;
    }

    public String getCreateTime() {
        return createTime;
    }
}
