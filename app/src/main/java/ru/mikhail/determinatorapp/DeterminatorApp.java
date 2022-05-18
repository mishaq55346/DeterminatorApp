package ru.mikhail.determinatorapp;

import android.app.Application;

import ru.mikhail.determinatorapp.orm.HelperFactory;

public class DeterminatorApp extends Application {
    public static final String TAG = "DET";
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
