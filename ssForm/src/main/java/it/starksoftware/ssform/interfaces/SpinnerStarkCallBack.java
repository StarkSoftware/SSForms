package it.starksoftware.ssform.interfaces;

import android.widget.Spinner;

import it.starksoftware.ssform.StarkSpinner.StarkSpinner;
import it.starksoftware.ssform.model.FormSpinnerObject;

public interface SpinnerStarkCallBack {
    void callbackSpinnerStarkReturn(FormSpinnerObject object, Object tag, StarkSpinner spinner);
}
