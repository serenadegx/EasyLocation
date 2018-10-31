package com.example.easylocation.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

public class LocationControl {

    private static final String TAG = "LocationControl";
    private Context context;
    private String provider;
    private LocationManager locationManager;
    private EasyLocationListener listener;
    private long minTime;
    private float minDistance;

    public LocationControl(Context context, LocationManager locationManager, String provider, long minTime, float minDistance) {
        this.locationManager = locationManager;
        this.provider = provider;
        this.context = context;
        this.minTime = minTime;
        this.minDistance = minDistance;
    }

    /**
     * 开始定位
     *
     * @param listener
     */
    public void start(final EasyLocationListener listener) {
        this.listener = listener;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            listener.onNotPermission();
            return;
        }
        if (TextUtils.isEmpty(provider)) {
            listener.onLocationListenerError("provider is null");
        } else {
            locationManager.requestLocationUpdates(provider, minTime, minDistance, locationListener);
        }
    }

    /**
     * 停止定位
     */
    public void stop() {
        if (listener != null) {
            locationManager.removeUpdates(locationListener);
            Log.i(TAG, "移除定位");
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            listener.onLocationListenerSuccess(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };
}