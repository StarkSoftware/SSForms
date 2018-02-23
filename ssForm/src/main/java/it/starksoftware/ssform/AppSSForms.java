package it.starksoftware.ssform;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppSSForms extends Application {

    static AppSSForms sSingleton;
    public static volatile Context applicationContext = null;

    public static AppSSForms getInstance() {

        return sSingleton;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        sSingleton = this;
        applicationContext = getApplicationContext();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SegoeUI.ttf")
                .setFontAttrId(it.starksoftware.ssform.R.attr.fontPath)
                .build()
        );

    }

    public Context getContext() {
        return getApplicationContext();
    }

}
