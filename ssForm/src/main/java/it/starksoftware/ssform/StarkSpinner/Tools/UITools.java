package it.starksoftware.ssform.StarkSpinner.Tools;

import android.content.Context;

public class UITools {

    private UITools() { }

    public static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }
}
