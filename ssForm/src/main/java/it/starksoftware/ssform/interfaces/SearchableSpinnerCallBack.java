package it.starksoftware.ssform.interfaces;

import it.starksoftware.ssform.model.FormElementSearchableSpinner;
import it.starksoftware.ssform.model.FormSpinnerObject;

public interface SearchableSpinnerCallBack {
    void callbackSearchableSpinnerReturn(FormElementSearchableSpinner object, Object tag, FormSpinnerObject spinnerObject);
}
