package it.starksoftware.ssform.file_explorer;

import android.content.Context;

public class AndroidUtilities {



    public static Context context;

    public AndroidUtilities(Context current){
        this.context = current;
    }



    public static int dp(float value, float density) {
        return (int) Math.ceil(density * value);
    }

    public static float dpf2(float value, float density) {

        return density * value;
    }



}

