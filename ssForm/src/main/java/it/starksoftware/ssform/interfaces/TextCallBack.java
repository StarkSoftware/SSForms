package it.starksoftware.ssform.interfaces;

import android.support.v7.widget.AppCompatEditText;

import java.util.Date;

import it.starksoftware.ssform.model.FormElement;
import it.starksoftware.ssform.model.FormElementDateTime;
import it.starksoftware.ssform.model.FormElementInputLayout;

public interface TextCallBack {
    void callbackTextReturn(String value, FormElementInputLayout object, Object tag);
    void callbackTextFEReturn(String value, AppCompatEditText object, Object tag);
}
