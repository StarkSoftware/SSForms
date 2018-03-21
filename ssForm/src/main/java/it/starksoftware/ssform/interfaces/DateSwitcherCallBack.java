package it.starksoftware.ssform.interfaces;

import java.util.Date;

import it.starksoftware.ssform.model.FormElementDateSwitcher;
import it.starksoftware.ssform.model.FormElementDateTime;

public interface DateSwitcherCallBack {
    void callbackDateSwitcherReturn(Date topDate, Date bottomDate, FormElementDateSwitcher object, Object tag);
}
