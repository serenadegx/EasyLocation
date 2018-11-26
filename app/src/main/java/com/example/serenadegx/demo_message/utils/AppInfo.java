package com.example.serenadegx.demo_message.utils;

public class AppInfo {
    private String packageName;
    private String label;

    public AppInfo(String packageName, String label) {
        this.packageName = packageName;
        this.label = label;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPackageName() {

        return packageName;
    }

    public String getLabel() {
        return label;
    }
}
