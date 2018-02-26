package it.starksoftware.ssform.model;

import java.io.Serializable;

public class FormTokenObject implements Serializable {

    private String key;
    private String value;
    private String extra;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
