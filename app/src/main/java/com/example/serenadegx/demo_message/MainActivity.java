package com.example.serenadegx.demo_message;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easylocation.location.EasyLocation;
import com.example.easylocation.location.EasyLocationListener;
import com.example.easylocation.location.WrapperPermissionActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends WrapperPermissionActivity {

    private static final String TAG = "MainActivity";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_SMS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                111);

    }

    @Override
    protected void getPermissionSuccess(int requestCode, String[] permissions, int[] grantResults) {
//        getContacts();
//        getSMS(100);
//        getCallLogs(100);
//        getThirdApp();
//        getLocation();
        EasyLocation
                .with(this)
                .location()
                .start(new EasyLocationListener() {
                    @Override
                    public void onLocationListenerSuccess(Location location) {
                        Log.i(TAG, "经度：" + location.getLongitude() + "  纬度：" + location.getLatitude() +
                                "   定位方式：" + location.getProvider());
                        tv.setText("经度：" + location.getLongitude() + "  纬度：" + location.getLatitude() +
                                "   定位方式：" + location.getProvider());
                    }

                    @Override
                    public void onLocationListenerError(String message) {
                        Log.i(TAG, message);
                        Toast.makeText(MainActivity.this, "无可用定位，请手动打开定位", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNotPermission() {
                        Log.i(TAG, "无权限");
                        Toast.makeText(MainActivity.this, "无权限", Toast.LENGTH_SHORT).show();
                    }
                });
    }

//    private String[] getLocation() {
//        String[] data = new String[2];
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        List<String> providers = locationManager.getProviders(true);
//        String provider = null;
//        if (providers.contains(LocationManager.GPS_PROVIDER)) {//
//            provider = LocationManager.GPS_PROVIDER;
//            Log.i(TAG, "GPS定位");
//        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//            provider = LocationManager.NETWORK_PROVIDER;
//            Log.i(TAG, "NetWork定位");
//        } else {
//            Log.i(TAG, "无可用定位");
//            return null;
//        }
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return null;
//        }
//        locationManager.requestLocationUpdates(provider, 2000, 2, new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                Log.i(TAG, "经度：" + location.getLongitude() + "  纬度：" + location.getLatitude());
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        });
//
//        return data;
//    }
//
//    private List<AppInfo> getThirdApp() {
//        List<AppInfo> data = new ArrayList<>();
//        PackageManager packageManager = getPackageManager();
//        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
//        for (PackageInfo packageInfo :
//                packageInfoList) {
//            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
//                data.add(new AppInfo(packageInfo.packageName, packageInfo.applicationInfo.loadLabel(packageManager).toString()));
//                Log.i(TAG, packageInfo.packageName + "        " + packageInfo.applicationInfo.loadLabel(packageManager).toString());
//            }
//        }
//        return data;
//    }
//
//    private List<CallLogDetail> getCallLogs(int limit) {
//        List<CallLogDetail> data = new ArrayList<>();
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return null;
//        }
//        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null,
//                null, limit > 0 ? "date desc limit " + limit : "date desc");
//        CallLogDetail callLogDetail;
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex(CallLog.Calls._ID));
//            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
//            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
//            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE))));
//            callLogDetail = new CallLogDetail(id, number, type, date);
//            data.add(callLogDetail);
//            Log.i(TAG, id + "  " + number + "  " + type + "    " + date);
//        }
//        return data;
//    }
//
//    private List<SMSDetail> getSMS(int limit) {
//        /**
//         *_id：短信序号，如100 　　 　　
//         * thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的 　　 　　
//         * address：发件人地址，即手机号，如+8613811810000 　　 　　
//         * person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null 　　 　　
//         * date：日期，long型，如1256539465022，可以对日期显示格式进行设置 　　 　　
//         * protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信 　　 　　
//         * read：是否阅读0未读，1已读 　　 　　
//         * status：短信状态-1接收，0complete,64pending,128failed 　　 　　
//         * type：短信类型1是接收到的，2是已发出 　　 　　
//         * body：短信具体内容 　　 　　
//         * service_center：短信服务中心号码编号，如+8613800755500
//         */
//        List<SMSDetail> data = new ArrayList<>();
//        Cursor cursor = getContentResolver().query(Telephony.Sms.CONTENT_URI, null, null,
//                null, limit > 0 ? "date desc limit " + limit : "date desc");
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex(Telephony.Sms._ID));
//            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
//            String name = cursor.getString(cursor.getColumnIndex(Telephony.Sms.PERSON));
//            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursor.getLong(cursor.getColumnIndex(Telephony.Sms.DATE))));
//            String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
//            data.add(new SMSDetail(id, name, address, body, createTime));
//            Log.i(TAG, id + "  " + address + "  " + body + "    " + createTime);
//        }
//        return data;
//    }
//
//    private List<ContactsDetail> getContacts() {
//        List<ContactsDetail> data = new ArrayList<>();
//        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                null, null, null, ContactsContract.CommonDataKinds
//                        .Phone.CONTACT_STATUS_TIMESTAMP + " desc");
//        ContactsDetail contactsDetail;
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
//            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.STATUS_TIMESTAMP))));
//            contactsDetail = new ContactsDetail(id, name, number, createTime);
//            data.add(contactsDetail);
//            Log.i(TAG, id + "  " + name + "  " + number + "    " + createTime);
//        }
//        return data;
//    }

    @Override
    protected void getPermissionError(final int requestCode, final String[] permissions, final int[] grantResults) {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("缺少必要权限，会造成app部分功能无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPermissionSuccess(requestCode, permissions, grantResults);
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EasyLocation.with(this).location().stop();
    }
}
