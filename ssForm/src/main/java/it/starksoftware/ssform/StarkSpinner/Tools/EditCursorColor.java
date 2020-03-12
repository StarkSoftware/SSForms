package it.starksoftware.ssform.StarkSpinner.Tools;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

public class EditCursorColor {

    private EditCursorColor() { }

    public static void setCursorColor(EditText editText, int color) {
        try {
            final Field drawableResField = TextView.class.getDeclaredField("mCursorDrawableRes");
            drawableResField.setAccessible(true);
            final Drawable drawable = getDrawable(editText.getContext(), drawableResField.getInt(editText));
            if (drawable == null) {
                return;
            }
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            final Object drawableFieldOwner;
            final Class<?> drawableFieldClass;
            final Field editorField = TextView.class.getDeclaredField("mEditor");
            editorField.setAccessible(true);
            drawableFieldOwner = editorField.get(editText);
            drawableFieldClass = drawableFieldOwner.getClass();
            final Field drawableField = drawableFieldClass.getDeclaredField("mCursorDrawable");
            drawableField.setAccessible(true);
            drawableField.set(drawableFieldOwner, new Drawable[] {drawable, drawable});
        } catch (Exception ignored) {
        }
    }

    private static Drawable getDrawable(Context context, int id) {
        return context.getDrawable(id);
    }
}
