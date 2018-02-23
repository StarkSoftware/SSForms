package it.starksoftware.ssform.interfaces;

import android.widget.Spinner;

import it.starksoftware.ssform.model.FormSpinnerObject;

public interface SpinnerCallBack {
    void callbackSpinnerReturn(FormSpinnerObject object, Object tag, Spinner spinner);
}
