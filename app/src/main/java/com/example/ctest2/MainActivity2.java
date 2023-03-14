package com.example.ctest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationListener;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity implements CTPushNotificationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


//        CleverTapAPI.enableXiaomiPushOn(PushConstants.ALL_DEVICES);
//        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
//       // CleverTapAPI cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(this);
//        cleverTapAPI.setCTPushNotificationListener(this);
        setContentView(R.layout.activity_main2);
      //  Serializable hashMap;
      //  Bundle bundle = this.getIntent().getExtras();
//                Log.d("afterclick","afterclick: "+ bundle);
//        Log.d("afterclick","afterclick: "+  new Gson().toJson(bundle));
//            hashMap = bundle.getSerializable("HashMap");
//        Log.d("afterclick","afterclick: "+ hashMap);
      //  Log.d("afterclick",bundle.get("key").toString());
//        for (String key : bundle.keySet()) {
//            Object value = bundle.get(key);
//            Log.d("afterclick", String.format("%s %s (%s)", key, value.toString(), value.getClass().getName()));
//        }
        findViewById(R.id.login).setOnClickListener(v->{
            Intent i = new Intent(MainActivity2.this,MainActivity.class);
            MainActivity2.this.startActivity(i);
        });

    }

    @Override
    public void onNotificationClickedPayloadReceived(HashMap<String, Object> payload) {

    }
//    @Override
//    protected void onNewIntent(Intent intent)
//    {
//        super.onNewIntent(intent);
//        Log.d("afterclick","afterclick:");
//
////        Log.d("afterclick","afterclick: "+ intent.getStringExtra("key"));
//    }
}