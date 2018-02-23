package it.starksoftware.ssform.interfaces;

import it.starksoftware.ssform.model.FormElementSwitch;

public interface SwitchCallBack {
    void callbackSwitchReturn(FormElementSwitch object, Object tag, boolean switchStatus);
}
