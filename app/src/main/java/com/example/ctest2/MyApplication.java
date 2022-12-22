package com.example.ctest2;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CTWebInterface;
import com.clevertap.android.sdk.CleverTapAPI;
//import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.PushConstants;

import org.json.JSONObject;

import java.util.HashMap;

public class MyApplication extends Application implements CTPushNotificationListener, Application.ActivityLifecycleCallbacks {
    private static final String TAG = MyApplication.class.getName();

    @Override
    public void onCreate() {


        Log.d(TAG, "onCreate() called");
        CleverTapAPI.changeCredentials("TEST-4W5-9RR-646Z", "TEST-22c-504");
     CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"biswa2","biswa2","biswa2", NotificationManager.IMPORTANCE_MAX,true,"sound1.mp3");
       //CleverTapAPI.createNotificationChannel(getApplicationContext(),"biswa1","biswa1","biswa1", NotificationManager.IMPORTANCE_MAX,true,"sound2.mp3");
        CleverTapAPI.createNotificationChannel(getApplicationContext(),"nutrabay_push", "Nutrabay Push","Notification related to app", 5, true) ;
        // possible values are PushConstants.ALL_DEVICES, PushConstants.XIAOMI_MIUI_DEVICES,PushConstants.NO_DEVICES
        // possible values are PushConstants.ALL_DEVICES, PushConstants.XIAOMI_MIUI_DEVICES,PushConstants.NO_DEVICES
// default is PushConstants.ALL_DEVICES

       CleverTapAPI.enableXiaomiPushOn(PushConstants.ALL_DEVICES);
       CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
      cleverTapAPI.setCTPushNotificationListener(this);
       // CleverTapInstanceConfig config =  CleverTapInstanceConfig.createInstance(this,"TEST-4W5-9RR-646Z","TEST-22c-504");
       //CleverTapAPI cleverTapAPI = CleverTapAPI.instanceWithConfig(this,config);

//

  registerActivityLifecycleCallbacks(this);
  ActivityLifecycleCallback.register(this);
//TemplateRenderer.setDebugLevel(3);
  CleverTapAPI.setDebugLevel(3);
    super.onCreate();


    }
    @Override
    public void onNotificationClickedPayloadReceived(HashMap<String, Object> extras) {
        Log.d("afterclick", "afterclick in application: "+extras);
        Log.d("clicked DL","in application Id: "+extras.get("wzrk_dl"));

//        Intent intent = new Intent(this,MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("key",extras);
//        startActivity(intent);
        //  CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
//        WebView mywebview = (WebView) findViewById(R.id.web);
//        //mywebview.addJavascriptInterface(new CTWebInterface(CleverTapAPI.getDefaultInstance(this)), "CleverTap");
//        mywebview.addJavascriptInterface(new CTWebInterface(clevertapDefaultInstance),"CleverTap");
//        mywebview.getSettings().setJavaScriptEnabled(true);
//
//        mywebview.loadUrl("https://biswaemailunsub.000webhostapp.com/");





    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated() called with: activity = [" + activity.getIntent() + "], savedInstanceState = [" + savedInstanceState + "]");
        NotificationUtils.dismissNotification(activity.getIntent(), this);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d(TAG, "onActivityStarted() called with: activity = [" + activity + "]");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d(TAG, "onActivityResumed() called with: activity = [" + activity + "]");
        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        cleverTapAPI.pushEvent("Application resumed");
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d(TAG, "onActivityPaused() called with: activity = [" + activity + "]");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d(TAG, "onActivityStopped() called with: activity = [" + activity + "]");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.d(TAG, "onActivitySaveInstanceState() called with: activity = [" + activity + "], outState = [" + outState + "]");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {




    }

}
