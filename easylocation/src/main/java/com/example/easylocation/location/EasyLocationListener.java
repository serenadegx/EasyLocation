package com.example.easylocation.location;

import android.location.Location;

public interface EasyLocationListener {
    void onLocationListenerSuccess(Location location);

    void onLocationListenerError(String message);

    void onNotPermission();
}
