package it.starksoftware.ssform.interfaces;

import java.util.Date;

import it.starksoftware.ssform.model.FormElementDateTime;

public interface DateTimeCallBack {
    void callbackDateTimeReturn(Date value, FormElementDateTime object, Object tag);
}
