package com.example.ctest2;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NotificationUtils {

    //Require to close notification on action button click
    public static void dismissNotification(Intent intent, Context applicationContext) {
        Log.d("dismissid", "dismissNotification() called with: intent = [" + intent + "], applicationContext = [" + applicationContext + "]");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String actionId = extras.getString("wzrk_c2a");
            //Log.d("myactionId", actionId);
            if (actionId != null) {
                boolean autoCancel = extras.getBoolean("autoCancel", true);
                int notificationId = extras.getInt("notificationId", -1);
                if (autoCancel && notificationId > -1) {
                    NotificationManager notifyMgr =
                            (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
                    notifyMgr.cancel(notificationId);
                }

            }
        }
    }}