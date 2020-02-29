package it.starksoftware.ssform.interfaces;

import androidx.appcompat.widget.AppCompatEditText;

import it.starksoftware.ssform.model.FormElementInputLayout;

public interface TextCallBack {
    void callbackTextReturn(String value, FormElementInputLayout object, Object tag);
    void callbackTextFEReturn(String value, AppCompatEditText object, Object tag);
}
