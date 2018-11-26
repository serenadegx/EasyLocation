package com.example.serenadegx.demo_message.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MDDUtils {

    private static final String TAG = "MDDUtils";

    /**
     * 获取三方应用
     *
     * @param context
     * @param limit   0：无限制
     * @return
     */
    public static List<AppInfo> getThirdApp(Context context, int limit) {
        int index = 0;
        List<AppInfo> data = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        if (limit <= 0) {
            for (PackageInfo packageInfo :
                    packageInfoList) {
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    data.add(new AppInfo(packageInfo.packageName, packageInfo.applicationInfo.loadLabel(packageManager).toString()));
                    Log.i(TAG, packageInfo.packageName + "        " + packageInfo.applicationInfo.loadLabel(packageManager).toString());
                }
            }
        } else {
            for (PackageInfo packageInfo :
                    packageInfoList) {
                if (index >= limit) {
                    break;
                }
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    index++;
                    data.add(new AppInfo(packageInfo.packageName, packageInfo.applicationInfo.loadLabel(packageManager).toString()));
                    Log.i(TAG, packageInfo.packageName + "        " + packageInfo.applicationInfo.loadLabel(packageManager).toString());
                }
            }
        }
        return data;
    }

    /**
     * 获取APP清单
     *
     * @param context
     * @param limit
     * @return
     */
    public static List<String> getThirdAppDetails(Context context, int limit) {
        int index = 0;
        List<String> data = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        if (limit <= 0) {
            for (PackageInfo packageInfo :
                    packageInfoList) {
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    data.add(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    Log.i(TAG, packageInfo.applicationInfo.loadLabel(packageManager).toString());
                }
            }
        } else {
            for (PackageInfo packageInfo :
                    packageInfoList) {
                if (index >= limit) {
                    break;
                }
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    index++;
                    data.add(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    Log.i(TAG, packageInfo.applicationInfo.loadLabel(packageManager).toString());
                }
            }
        }
        return data;
    }

    /**
     * 获取APP包名
     *
     * @param context
     * @param limit
     * @return
     */
    public static List<String> getThirdAppPackage(Context context, int limit) {
        int index = 0;
        List<String> data = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        if (limit <= 0) {
            for (PackageInfo packageInfo :
                    packageInfoList) {
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    data.add(packageInfo.packageName);
                    Log.i(TAG, packageInfo.packageName);
                }
            }
        } else {
            for (PackageInfo packageInfo :
                    packageInfoList) {
                if (index >= limit) {
                    break;
                }
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                    index++;
                    data.add(packageInfo.packageName);
                    Log.i(TAG, packageInfo.packageName);
                }
            }
        }
        return data;
    }

    /**
     * 获取短信记录
     *
     * @param context
     * @param limit
     * @return
     */
    public static List<SMSDetail> getSMS(Context context, int limit) {
        /**
         *_id：短信序号，如100 　　 　　
         * thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的 　　 　　
         * address：发件人地址，即手机号，如+8613811810000 　　 　　
         * person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null 　　 　　
         * date：日期，long型，如1256539465022，可以对日期显示格式进行设置 　　 　　
         * protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信 　　 　　
         * read：是否阅读0未读，1已读 　　 　　
         * status：短信状态-1接收，0complete,64pending,128failed 　　 　　
         * type：短信类型1是接收到的，2是已发出 　　 　　
         * body：短信具体内容 　　 　　
         * service_center：短信服务中心号码编号，如+8613800755500
         */
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List<SMSDetail> data = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Telephony.Sms.CONTENT_URI, null, null,
                null, limit > 0 ? "date desc limit " + limit : "date desc");
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(Telephony.Sms._ID));
            String address = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
            String name = cursor.getString(cursor.getColumnIndex(Telephony.Sms.PERSON));
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursor.getLong(cursor.getColumnIndex(Telephony.Sms.DATE))));
            String body = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));
            data.add(new SMSDetail(id, name, address, body, createTime));
            Log.i(TAG, id + "  " + address + "  " + body + "    " + createTime);
        }
        return data;
    }

    /**
     * 获取联系人
     *
     * @param context
     * @return
     */
    public static List<ContactsDetail> getContacts(Context context, int limit) {
        List<ContactsDetail> data = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, limit > 0 ? ContactsContract.CommonDataKinds
                        .Phone.CONTACT_STATUS_TIMESTAMP + " desc limit " + limit : ContactsContract.CommonDataKinds
                        .Phone.CONTACT_STATUS_TIMESTAMP + " desc");
        ContactsDetail contactsDetail;
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.STATUS_TIMESTAMP))));
            contactsDetail = new ContactsDetail(id, name, number, createTime);
            data.add(contactsDetail);
            Log.i(TAG, id + "  " + name + "  " + number + "    " + createTime);
        }
        return data;
    }

    /**
     * 获取通话记录
     *
     * @param context
     * @param limit
     * @return
     */
    public static List<CallLogDetail> getCallLogs(Context context, int limit) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List<CallLogDetail> data = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null,
                null, limit > 0 ? "date desc limit " + limit : "date desc");
        CallLogDetail callLogDetail;
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(CallLog.Calls._ID));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE))));
            callLogDetail = new CallLogDetail(id, number, type, date);
            data.add(callLogDetail);
            Log.i(TAG, id + "  " + number + "  " + type + "    " + date);
        }
        return data;
    }


    /**
     * 获取手机IMEI号
     */
    public static String getIMEI(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }

    /**
     * 获取手机IMSI号
     */
    public static String getIMSI(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = mTelephonyMgr.getSubscriberId();
        return imsi;
    }

    /**
     * 获取手机号
     */
    public static String getLocalNumber(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String number = mTelephonyMgr.getLine1Number();
        return number;
    }


}
