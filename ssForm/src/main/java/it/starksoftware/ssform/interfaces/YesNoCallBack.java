package it.starksoftware.ssform.interfaces;

import android.widget.RadioGroup;

public interface YesNoCallBack {
    void callbackYesNoReturn(Object tag, RadioGroup radioGroup, int value, String extraValue);
}
