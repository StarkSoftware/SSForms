package it.starksoftware.ssform.file_explorer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import it.starksoftware.ssform.AppSSForms;

public class TextDetailDocumentsCell extends FrameLayout {

    private TextView textView;
    private TextView valueTextView;
    private TextView typeTextView;
    private ImageView imageView;
    private CheckBox checkBox;
    public static float density = 1;
    public static Point displaySize = new Point();


    public static void checkDisplaySize() {
        try {
            WindowManager manager = (WindowManager) AppSSForms.applicationContext
                    .getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                Display display = manager.getDefaultDisplay();
                if (display != null) {
                    if (android.os.Build.VERSION.SDK_INT < 13) {
                        displaySize
                                .set(display.getWidth(), display.getHeight());
                    } else {
                        display.getSize(displaySize);
                    }
                    Log.e("tmessages", "display size = " + displaySize.x+ " " + displaySize.y);
                }
            }
        } catch (Exception e) {
            Log.e("tmessages", e.toString());
        }
    }

    public TextDetailDocumentsCell(Context context) {

        super(context);

        density = context.getResources().getDisplayMetrics().density;
        checkDisplaySize();

        textView = new TextView(context);
        textView.setTextColor(0xff212121);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        textView.setLines(1);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setGravity(Gravity.LEFT);
        addView(textView);
        LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.topMargin = AndroidUtilities.dp(10, density);
        layoutParams.leftMargin = AndroidUtilities.dp(71, density);
        layoutParams.rightMargin = AndroidUtilities.dp(16, density);
        layoutParams.gravity = Gravity.LEFT;
        textView.setLayoutParams(layoutParams);

        valueTextView = new TextView(context);
        valueTextView.setTextColor(0xff8a8a8a);
        valueTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        valueTextView.setLines(1);
        valueTextView.setMaxLines(1);
        valueTextView.setSingleLine(true);
        valueTextView.setGravity(Gravity.LEFT);
        addView(valueTextView);
        layoutParams = (LayoutParams) valueTextView.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        layoutParams.topMargin = AndroidUtilities.dp(35, density);
        layoutParams.leftMargin = AndroidUtilities.dp(71, density);
        layoutParams.rightMargin = AndroidUtilities.dp(16, density);
        layoutParams.gravity = Gravity.LEFT;
        valueTextView.setLayoutParams(layoutParams);

        typeTextView = new TextView(context);
        typeTextView.setBackgroundColor(0xff757575);
        typeTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        typeTextView.setGravity(Gravity.CENTER);
        typeTextView.setSingleLine(true);
        typeTextView.setTextColor(0xffd1d1d1);
        typeTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        typeTextView.setTypeface(Typeface.DEFAULT_BOLD);
        addView(typeTextView);
        layoutParams = (LayoutParams) typeTextView.getLayoutParams();
        layoutParams.width = AndroidUtilities.dp(40, density);
        layoutParams.height = AndroidUtilities.dp(40, density);
        layoutParams.leftMargin = AndroidUtilities.dp(16, density);
        layoutParams.rightMargin = AndroidUtilities.dp(0, density);
        layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        typeTextView.setLayoutParams(layoutParams);

        imageView = new ImageView(context);
        addView(imageView);
        layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = AndroidUtilities.dp(40, density);
        layoutParams.height = AndroidUtilities.dp(40, density);
        layoutParams.leftMargin = AndroidUtilities.dp(16, density);
        layoutParams.rightMargin = AndroidUtilities.dp(0, density);
        layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        imageView.setLayoutParams(layoutParams);

        checkBox = new CheckBox(context);
        checkBox.setVisibility(GONE);
        addView(checkBox);
        layoutParams = (LayoutParams) checkBox.getLayoutParams();
        layoutParams.width = AndroidUtilities.dp(22, density);
        layoutParams.height = AndroidUtilities.dp(22, density);
        layoutParams.topMargin = AndroidUtilities.dp(34, density);
        layoutParams.leftMargin = AndroidUtilities.dp(38, density) ;
        layoutParams.rightMargin = 0;
        layoutParams.gravity = Gravity.LEFT;
        checkBox.setLayoutParams(layoutParams);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64, density), MeasureSpec.EXACTLY));
    }

    public void setTextAndValueAndTypeAndThumb(String text, String value, String type, String thumb, int resId) {
        textView.setText(text);
        valueTextView.setText(value);
        if (type != null) {
            typeTextView.setVisibility(VISIBLE);
            typeTextView.setText(type);
        } else {
            typeTextView.setVisibility(GONE);
        }
        if (thumb != null || resId != 0) {
            if (thumb != null) {
//                imageView.setImage(thumb, "40_40", null);
            } else  {
                imageView.setImageResource(resId);
            }
            imageView.setVisibility(VISIBLE);
        } else {
            imageView.setVisibility(GONE);
        }
    }

    public void setChecked(boolean checked, boolean animated) {
        if (checkBox.getVisibility() != VISIBLE) {
            checkBox.setVisibility(VISIBLE);
        }
        checkBox.setChecked(checked);
    }
}
