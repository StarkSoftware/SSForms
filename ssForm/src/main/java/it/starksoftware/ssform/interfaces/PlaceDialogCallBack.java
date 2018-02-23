package it.starksoftware.ssform.interfaces;

import android.widget.Spinner;

import it.starksoftware.ssform.model.FormSpinnerObject;

public interface PlaceDialogCallBack {
    void callbackPlaceDialogReturn(FormSpinnerObject object, Object tag, Spinner spinner);
}
