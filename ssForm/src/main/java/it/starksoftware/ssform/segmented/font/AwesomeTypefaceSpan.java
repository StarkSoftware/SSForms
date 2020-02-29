package it.starksoftware.ssform.segmented.font;

import android.content.Context;
import androidx.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import it.starksoftware.ssform.segmented.utils.TypefaceProvider;

/**
 * A custom span which paints text using the typeface specified by the IconSet passed to the constructor
 */
public class AwesomeTypefaceSpan extends TypefaceSpan {

    private final Context context;
    private final IconSet iconSet;

    public AwesomeTypefaceSpan(Context context, IconSet iconSet) {
        super(iconSet.fontPath().toString());
        this.context = context.getApplicationContext();
        this.iconSet = iconSet;
    }

    @Override public void updateDrawState(@NonNull TextPaint ds) {
        ds.setTypeface(TypefaceProvider.getTypeface(context, iconSet));
    }

    @Override public void updateMeasureState(@NonNull TextPaint paint) {
        paint.setTypeface(TypefaceProvider.getTypeface(context, iconSet));
    }

}
