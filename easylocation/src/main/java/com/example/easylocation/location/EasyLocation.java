package com.example.easylocation.location;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import java.util.List;

public class EasyLocation {
    private static final String TAG = "EasyLocation";
    private long minTime;
    private float minDistance;
    private Context context;
    private String provider;
    private LocationManager locationManager;
    private LocationControl locationControl;
    private static EasyLocation easyLocation;

    private EasyLocation(Context context) {
        this(new Builder(context));
    }

    private EasyLocation(Builder builder){
        this.context = builder.context;
        this.locationManager = builder.locationManager;
        this.minTime = builder.minTime;
        this.minDistance = builder.minDistance;
    }

    /**
     * 创建EasyLocation
     *
     * @param context
     * @return
     */
    public static EasyLocation with(Context context) {
        if (easyLocation == null) {
            synchronized (Builder.class) {
                if (easyLocation == null) {
                    easyLocation = new EasyLocation.Builder(context)
                            .setMinDistance(3.0f)
                            .build();
                }
            }
        }
        return easyLocation;
    }

    /**
     * 选择定位方式
     *
     * @return
     */
    public LocationControl location() {
        if (locationControl == null) {
            locationControl = location(locationManager);
        }
        return locationControl;
    }

    private LocationControl location(LocationManager locationManager) {
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {//
            provider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Log.i(TAG, "无可用定位");
        }
        return new LocationControl(context, locationManager, provider,minTime,minDistance);
    }

    private static class Builder {
        private Context context;
        private LocationManager locationManager;
        private long minTime;//单位：毫秒
        private float minDistance;//单位：米

        public Builder(Context context) {
            this.context = context;
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            minTime = 2000;
            minDistance = 2.0f;
        }

        public Builder setMinTime(long minTime){
            this.minTime = minTime;
            return this;
        }

        public Builder setMinDistance(float minDistance){
            this.minDistance = minDistance;
            return this;
        }

        public EasyLocation build(){
            return new EasyLocation(this);
        }
    }


}
