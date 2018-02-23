package it.starksoftware.ssform.interfaces;

import android.widget.CheckBox;
import android.widget.Spinner;

import it.starksoftware.ssform.model.FormSpinnerObject;

public interface CheckBoxCallBack {
    void callbackCheckBoxReturn(Object tag, CheckBox checkBox, boolean isChecked);
}
