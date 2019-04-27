package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import it.starksoftware.ssform.interfaces.CheckBoxCallBack;
import it.starksoftware.ssform.interfaces.YesNoCallBack;

public class FormElementYesNo implements FormObject {

    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String dbField; // title to be shown on left
    private Context mCtx;
    private Activity mAct;
    private int value;
    private YesNoCallBack yesNoCallBack;
    private boolean refresh;
    private boolean visibility = true;
    private int pos;
    private boolean required = false;
    private String mAttribute;
    private String extraValue;
    private String requiredResponseMessage = mTitle;

    public FormElementYesNo() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }


    public static FormElementYesNo createInstance() {
        return new FormElementYesNo();
    }

    public FormElementYesNo setRequired(boolean required) {
        this.required = required;
        return this;
    }

    // getters and setters
    public FormElementYesNo setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementYesNo setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementYesNo setValue(int value) {
        this.value = value;
        return this;
    }

    public FormElementYesNo setType(int mType) {
        this.mType = mType;
        return this;
    }

    public String getExtraValue() {
        return extraValue;
    }

    public FormElementYesNo setExtraValue(String extraValue) {
        this.extraValue = extraValue;
        return this;
    }

    public FormElementYesNo setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementYesNo setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementYesNo setIndex(int index) {
        this.pos = index;
        return this;
    }


    public FormElementYesNo setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementYesNo setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementYesNo setCallback(YesNoCallBack callback) {
        this.yesNoCallBack = callback;
        return this;
    }

    public YesNoCallBack getCallback() {
        return this.yesNoCallBack;
    }


    public FormElementYesNo setRefresh(Boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    public FormElementYesNo setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }

    public boolean getRefresh() {
        return refresh;
    }

    public int getTag() {
        return mTag;
    }

    public int getValue () {
        return value;
    }

    public String getDbField() { return dbField; }
    public FormElementYesNo setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }
    public int getType() {
        return mType;
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "YesNo";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: 1";
    }
}
