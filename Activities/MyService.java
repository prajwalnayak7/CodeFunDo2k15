package com.anonarmy.prajwalnayak.myapplication;

import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import android.app.ActivityManager;

import java.io.File;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MyService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

    }
        @Override
        public void onCreate() {
            super.onCreate();
            //File file=new File(getApplicationContext().getFilesDir(),"data_file.txt");
           //getApplicationContext().deleteFile("data_file.txt");
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    final PackageManager pm = getApplicationContext().getPackageManager();
                    ApplicationInfo ai;
                    String s;
                    ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService( Context.ACTIVITY_SERVICE );
                    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
                    for(ActivityManager.RunningAppProcessInfo appProcess : appProcesses){
                        if(appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                           // Log.i("Foreground App", appProcess.processName);
                            if(appProcess.processName!="com.anonarmy.prajwalnayak.myapplication") {
                                try {
                                    //s= (String)pm.getApplicationLabel(pm.getApplicationInfo(appProcess.processName,PackageManager.GET_META_DATA));
                                    ai=pm.getApplicationInfo(appProcess.processName,0);
                                } catch (final PackageManager.NameNotFoundException e) {
                                   // s = null;
                                    ai=null;
                                }
                                final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
                                MainActivity.writeToFile("  \n  \r  ", getApplicationContext());
                                MainActivity.writeToFile(applicationName,getApplicationContext());
                            }
                        }
                    }

                }
            };
            new Thread(runnable).start();
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
       /* Calendar endcal=Calendar.getInstance();
        Calendar begincal=Calendar.getInstance();
        begincal.add(Calendar.MINUTE,-30);
        UsageStatsManager usm=(UsageStatsManager)getSystemService(Context.USAGE_STATS_SERVICE);
        List<UsageStats>stats=usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,begincal.getTimeInMillis(),endcal.getTimeInMillis());
        Collections.sort(stats, new Comparator<UsageStats>() {
            @Override
            public int compare(UsageStats lhs, UsageStats rhs) {
                long time1=lhs.getLastTimeUsed();
                long time2=rhs.getLastTimeUsed();
                if(time1>time2)
                    return -1;
                else if(time1<time2)
                    return 1;
                return 0;
            }
        });
        */
        return START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }
    @Override
        public AssetManager getAssets() {
            return super.getAssets();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Toast.makeText(getApplicationContext(), " Service ended ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

    }
