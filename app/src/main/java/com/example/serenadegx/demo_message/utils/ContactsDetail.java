package com.example.serenadegx.demo_message.utils;

public class ContactsDetail {
    public String id;
    private String name;
    private String number;
    private String createTime;

    public ContactsDetail() {
    }

    public ContactsDetail(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public ContactsDetail(String name, String number, String createTime) {
        this.name = name;
        this.number = number;
        this.createTime = createTime;
    }

    public ContactsDetail(String id, String name, String number, String createTime) {
        this.id = id;
        this.name = name;
        this.number = number;
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

    public String getCreateTime() {
        return createTime;
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

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
