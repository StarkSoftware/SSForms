package it.starksoftware.ssform.customKeyboard;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import it.starksoftware.ssform.listeners.OnCustomKeyboardActionListener;
import it.starksoftware.ssform.model.FormElementCustomKeyboard;


public class CustomKeyboard {

    private KeyboardView mKeyboardView;
    private Activity mHostActivity;

    public CustomKeyboard(Activity host, int viewid, int layoutid) {
        mHostActivity = host;
        mKeyboardView = (KeyboardView) mHostActivity.findViewById(viewid);
        mKeyboardView.setKeyboard(new Keyboard(mHostActivity, layoutid));
        mKeyboardView.setPreviewEnabled(false);
        mHostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public boolean isCustomKeyboardVisible() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    public void showCustomKeyboard(View v, AppCompatEditText edittext) {
        mKeyboardView.setVisibility(View.VISIBLE);

        OnCustomKeyboardActionListener mOnKeyboardActionListener = new OnCustomKeyboardActionListener(edittext, mKeyboardView);
        mKeyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);

        mKeyboardView.setEnabled(true);
        if (v != null)
            ((InputMethodManager) mHostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void hideCustomKeyboard() {
        mKeyboardView.setVisibility(View.GONE);
        mKeyboardView.setEnabled(false);

        mKeyboardView.setOnKeyboardActionListener(null);
    }


    public void registerEditText(final AppCompatEditText edittext, final FormElementCustomKeyboard customKeyboard) {
        edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    showCustomKeyboard(v, edittext);
                else {
                    customKeyboard.setValue(edittext.getText().toString());
                    hideCustomKeyboard();
                }
            }
        });
        edittext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomKeyboard(v, edittext);
            }
        });

        edittext.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText edittext = (EditText) v;
                int inType = edittext.getInputType();       // Backup the input type
                edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                edittext.onTouchEvent(event);               // Call native handler
                edittext.setInputType(inType);              // Restore input type
                return true; // Consume touch event
            }
        });

        edittext.setInputType(edittext.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

}
