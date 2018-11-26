package com.example.serenadegx.demo_message.utils;

public class CallLogDetail {
    private String id;
    private String name;
    private String number;
    /**
     * 1：来电，2：去电，3：未接
     */
    private int type;
    private String date;

    public CallLogDetail() {
    }

    public CallLogDetail(String id, String number, int type, String date) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.date = date;
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

    public int getType() {
        return type;
    }

    public String getDate() {
        return date;
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

    public void setType(int type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
