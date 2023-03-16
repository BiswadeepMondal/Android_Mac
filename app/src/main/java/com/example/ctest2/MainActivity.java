package com.example.ctest2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CTInboxListener;
import com.clevertap.android.sdk.CTInboxStyleConfig;
import com.clevertap.android.sdk.CTWebInterface;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.displayunits.DisplayUnitListener;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit;
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent;
//import com.clevertap.android.sdk.inapp.CTLocalInApp;
import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements CTInboxListener, DisplayUnitListener {

    Button createu, pushpbt, appinbox, getmsg, nativedisp, inboxcount, logout, evtbtn;
    CardView c;
    TextView text1, titlem, msg, txtinbox;
    private FirebaseAnalytics mFirebaseAnalytics;
    EditText phone, id, email, evt;
    private String myphone, myid, myemail;
    private CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CleverTapAPI.setNotificationHandler((NotificationHandler) new PushTemplateNotificationHandler());
        ActivityLifecycleCallback.register(getApplication());
        super.onCreate(savedInstanceState);
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();


        Log.d("Activity", "onCreate MainActivity");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main);
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
       // CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        CleverTapAPI cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(this);

         // Returns true if permission is granted, else returns false if permission is denied.
//
//
//        JSONObject jsonObject = CTLocalInApp.builder()
//                .setInAppType(CTLocalInApp.InAppType.ALERT)
//                .setTitleText("Get Notified")
//                .setMessageText("For cupon and offers")
//                .followDeviceOrientation(true)
//                .setPositiveBtnText("Allow")
//                .setNegativeBtnText("Cancel")
//                .build();
//        cleverTapDefaultInstance.promptPushPrimer(jsonObject);
//
//        Log.d("clevertap", "push permission: "+cleverTapDefaultInstance.isPushPermissionGranted());
//       // Log.d("clevertap", "push permission: "+cleverTapDefaultInstance.promptForPushPermission(true));
//if(!cleverTapDefaultInstance.isPushPermissionGranted()) {
//    cleverTapDefaultInstance.promptForPushPermission(true);
//}

        //CleverTapAPI.setDebugLevel(3);
      //  TemplateRenderer.setDebugLevel(3);
        cleverTapDefaultInstance.enableDeviceNetworkInfoReporting(true);
       // CleverTapInstanceConfig config =  CleverTapInstanceConfig.createInstance(this,"TEST-4W5-9RR-646Z","TEST-22c-504");
        //CleverTapAPI cleverTapAPI = CleverTapAPI.instanceWithConfig(this,config);

        //webview
        WebView mywebview = (WebView) findViewById(R.id.web);
        //mywebview.addJavascriptInterface(new CTWebInterface(CleverTapAPI.getDefaultInstance(this)), "CleverTap");
        mywebview.addJavascriptInterface(new CTWebInterface(clevertapDefaultInstance),"CleverTap");
        mywebview.getSettings().setJavaScriptEnabled(true);


        //clevertapDefaultInstance.pushFcmRegistrationId(task.getResult(),true);

//        MiPushClient.registerPush(this, "2882303761520478796", "5382047861796");
//        String xiaomiToken = MiPushClient.getRegId(this);
//        if (clevertapDefaultInstance != null) {
//        //    clevertapDefaultInstance.pushXiaomiRegistrationId(xiaomiToken, true);
//            Log.d("1231", "1231" + xiaomiToken);
//        } else {
//            Log.d("1231", "CleverTap is NULL no token");
//        }
        // Log.d("myphone", "phone: "+myphone);
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
////                        sendFCMTokenToDatabase(task.getResult());
//                       // Toast.makeText(getApplicationContext(),"Notification Token is"+task.getResult(),Toast.LENGTH_SHORT).show();
//                        Log.d("ttest", "ttest: "+task.getResult());
//                       clevertapDefaultInstance.pushFcmRegistrationId(task.getResult(),true);
//                        Log.d(	"Push Unregistered", "Push Unregistered: ");
//                    }
//                });
        //TemplateRenderer.setDebugLevel(3);
        if (cleverTapDefaultInstance != null) {
            //Set the Notification Inbox Listener
            cleverTapDefaultInstance.setCTNotificationInboxListener(this);
            //Initialize the inbox and wait for callbacks on overridden methods
            cleverTapDefaultInstance.initializeInbox();
            // cleverTapDefaultInstance.getInboxMessageCount();
            // cleverTapDefaultInstance.getAllInboxMessages();
        }
//
//        Bundle bundle = this.getIntent().getExtras();
//        if(bundle != null) {
//            Log.d("afterclick", "inside mainactivity" + bundle.get("key").toString());
//        }
        CleverTapAPI.getDefaultInstance(this).setDisplayUnitListener(this);


        createu = findViewById(R.id.createuser);
        pushpbt = findViewById(R.id.pushnotification);
        text1 = findViewById(R.id.textv);
        appinbox = findViewById(R.id.appinbox);
        evt = findViewById(R.id.evt);
        evtbtn = findViewById(R.id.evtbtn);
        nativedisp = findViewById(R.id.nativedisp);
        inboxcount = findViewById(R.id.inboxcount);
        txtinbox = findViewById(R.id.txtinbox);
        c = findViewById(R.id.c1);
        titlem = findViewById(R.id.titlem);
        msg = findViewById(R.id.msg);
        logout = findViewById(R.id.logout);


        appinbox.setOnClickListener(v -> {
            // cleverTapDefaultInstance.showAppInbox();
            ArrayList<String> tabs = new ArrayList<>();
            tabs.add("Promotions");
            tabs.add("Offers");//We support upto 2 tabs only. Additional tabs will be ignored
            CTInboxStyleConfig styleConfig = new CTInboxStyleConfig();
            styleConfig.setFirstTabTitle("Text Tab");
            // styleConfig.setTabs(tabs);//Do not use this if you don't want to use tabs
            styleConfig.setTabBackgroundColor("#FF0000");
            styleConfig.setSelectedTabIndicatorColor("#0000FF");
            styleConfig.setSelectedTabColor("#0000FF");
            styleConfig.setUnselectedTabColor("#FFFFFF");
            styleConfig.setBackButtonColor("#000014");
            styleConfig.setNavBarTitleColor("#000014");
            styleConfig.setNavBarTitle("App Inbox");
            styleConfig.setNavBarColor("#FFFFFF"); // WHITE
            styleConfig.setInboxBackgroundColor("#ADD8E6");
            if (cleverTapDefaultInstance != null) {
                cleverTapDefaultInstance.showAppInbox(styleConfig);
            }
        });

//        getmsg.setOnClickListener(v -> {
//
////            Bundle params = new Bundle();
////            String v1="v1";
////            String evt="evt";
////            params.putString(v1,"data1");
////            mFirebaseAnalytics.logEvent(evt,params);
//
//          //  HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
//
//            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//            Date date = new Date();
//            String time=dateFormat.format(date);
//            profileUpdate.put("DATE",time);
//            Log.d("mytime", "mytime: "+time);
//            clevertapDefaultInstance.pushProfile(profileUpdate);
//
//            clevertapDefaultInstance.pushEvent("notify",profileUpdate);
//
////            clevertapDefaultInstance.pushEvent("notify",profileUpdate);
//
//        });
        pushpbt.setOnClickListener(v -> {
            HashMap<String, Object> vdata = new HashMap<String, Object>();
            vdata.put("vname","CT000001");
            clevertapDefaultInstance.pushEvent("viet",vdata);
            Analytics.with(getApplicationContext()).track("testEvent",
                    new Properties().putValue("value", "testValue") .putValue("testDate", new Date(System.currentTimeMillis()))
            );
        });

        evtbtn.setOnClickListener(v -> {

            clevertapDefaultInstance.pushEvent(evt.getText().toString());

        });

        nativedisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clevertapDefaultInstance.pushEvent("biswanative");
            }
        });

        inboxcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              int ct= cleverTapDefaultInstance.getInboxMessageCount(); // to count total inbox msg
////              Log.d("Count","Count"+cleverTapDefaultInstance.getInboxMessageCount());
//               //  text1.setText(ct);
//                String ctr= String.valueOf(ct);
//                txtinbox.setText("Your inbox message count is "+ctr);

                //Log.d("Count","data: "+cleverTapDefaultInstance.getAllInboxMessages());

                mywebview.loadUrl("https://biswaemailunsub.000webhostapp.com/");
            }
        });


        createu.setOnClickListener(view -> {

            phone = findViewById(R.id.phone);
            id = findViewById(R.id.id);
            email = findViewById(R.id.email);
            myphone = phone.getText().toString();
            myid = id.getText().toString();
            myemail = email.getText().toString();
            Log.d("MYPHONE", "MYPHONE: " + myphone);
            Log.d("id", "id: " + myid);
            Log.d("email", "email: " + myemail);
            // each of the below mentioned fields are optional
            //HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Name", "Biswa");    // String
            // String or number
            profileUpdate.put("Email", myemail); // Email address of the user
            profileUpdate.put("Phone", myphone);   // Phone (with the country code, starting with +)
            profileUpdate.put("Identity", myid);
            profileUpdate.put("Gender", "M");             // Can be either M or F

            profileUpdate.put("DOB", new Date());            // Date of Birth. Set the Date object to the appropriate value first
            // optional fields. controls whether the user will be sent email, push etc.

            profileUpdate.put("MSG-email", true);        // Disable email notifications
            profileUpdate.put("MSG-push", true);          // Enable push notifications
            profileUpdate.put("MSG-sms", true);          // Disable SMS notifications
            profileUpdate.put("MSG-whatsapp", true);      // Enable WhatsApp notifications
            //ArrayList<String> stuff = new ArrayList<String>();
            //stuff.add("https://picsum.photos/");
            //stuff.add("shoes");
            //profileUpdate.put("MyStuff7", stuff);                        //ArrayList of Strings

           // profileUpdate.put("Name", "Shu");
            clevertapDefaultInstance.pushEvent("ONUSER LOGIN on BTN", profileUpdate);

            clevertapDefaultInstance.onUserLogin(profileUpdate);

            text1.setText("User Created");
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  ProcessPhoenix.triggerRebirth(getApplicationContext());

                try {

                    // clearing app data
                    String packageName = getApplicationContext().getPackageName();
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("pm clear " + packageName);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.d("dismissid", "onNewIntent() called with: intent = [" + intent + "]");
        /**
         * On Android 12, clear notification on CTA click when Activity is already running in activity backstack
         */
        NotificationUtils.dismissNotification(intent, this);
    }


    @Override
    protected void onDestroy() {
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
     //   clevertapDefaultInstance.pushEvent("App closed");
        Toast.makeText(this, "onDestroy MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onDestroy MainActivity");

        super.onDestroy();

    }

    @Override
    protected void onStop() {

        Toast.makeText(this, "onStop MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onStop MainActivity");

        super.onStop();

    }

    @Override
    protected void onPause() {

        Toast.makeText(this, "onPause MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onPause MainActivity");

        super.onPause();
    }

    @Override
    protected void onResume() {

        Toast.makeText(this, "onResume MainActivity", Toast.LENGTH_SHORT).show();
        Log.d("Activity", "onResume MainActivity");
        super.onResume();
     //   clevertapDefaultInstance.pushEvent("activity resumed");
    }

    @Override
    public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units) {
        Log.d("clevertap", "onDisplayUnitsLoaded: ");
        for (int i = 0; i < units.size(); i++) {
            CleverTapDisplayUnit unit = units.get(i);
            prepareDisplayView(unit);
           Log.d("PAYLOAD","NATIVE DISPLAYIS"+unit.getContents());
            Log.d("PAYLOAD","------"+units.get(i));
        }
    }

    private void prepareDisplayView(CleverTapDisplayUnit unit) {
        Log.d("clevertap", "prepareDisplayView: ");
        for (CleverTapDisplayUnitContent i : unit.getContents()) {
            titlem.setText(i.getTitle());
            msg.setText(i.getMessage());
            Toast.makeText(getApplicationContext(), "VALUE" + i, Toast.LENGTH_SHORT).show();
            //Notification Viewed Event
            CleverTapAPI.getDefaultInstance(this).pushDisplayUnitViewedEventForID(unit.getUnitID());
           // clevertapDefaultInstance.
            //Notification Clicked Event
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    CleverTapAPI.getDefaultInstance(getApplicationContext()).pushDisplayUnitClickedEventForID(unit.getUnitID());

                }
            });
        }
    }

    @Override
    public void inboxDidInitialize() {


    }

    @Override
    public void inboxMessagesDidUpdate() {

    }


}