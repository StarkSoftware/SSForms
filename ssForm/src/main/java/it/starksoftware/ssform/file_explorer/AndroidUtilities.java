package it.starksoftware.ssform.file_explorer;

import android.annotation.SuppressLint;
import android.content.Context;

public class AndroidUtilities {

    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public AndroidUtilities(Context current){
        context = current;
    }

    static int dp(float value, float density) {
        return (int) Math.ceil(density * value);
    }

    public static float dpf2(float value, float density) {

        return density * value;
    }



}

