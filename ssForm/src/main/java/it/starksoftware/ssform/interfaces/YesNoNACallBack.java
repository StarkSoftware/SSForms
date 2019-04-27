package it.starksoftware.ssform.interfaces;

import android.widget.RadioGroup;

public interface YesNoNACallBack {
    void callbackYesNoNAReturn(Object tag, RadioGroup radioGroup, int value, String extraValue);
}
