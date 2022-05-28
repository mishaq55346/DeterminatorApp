package ru.mikhail.determinatorapp;

import android.app.Application;

import ru.mikhail.determinatorapp.orm.HelperFactory;

public class Determinator extends Application {
    public static final String TAG = "DET";
    public static String globalUsername = "admin";
    public static String globalPassword = "password";
    public static final String URL = "http://10.0.2.2:3000";
//    public static final String URL = "http://192.168.237.128:3000";

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
