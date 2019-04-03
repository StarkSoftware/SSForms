package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import it.starksoftware.ssform.interfaces.YesNoCallBack;
import it.starksoftware.ssform.interfaces.YesNoNACallBack;

public class FormElementYesNoNA implements FormObject {

    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String dbField; // title to be shown on left
    private Context mCtx;
    private Activity mAct;
    private int value;
    private YesNoNACallBack yesNoNACallBack;
    private boolean refresh;
    private boolean visibility = true;
    private int pos;
    private boolean required = false;
    private String mAttribute;
    private String requiredResponseMessage = mTitle;

    public FormElementYesNoNA() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }


    public static FormElementYesNoNA createInstance() {
        return new FormElementYesNoNA();
    }

    // getters and setters
    public FormElementYesNoNA setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementYesNoNA setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementYesNoNA setValue(int value) {
        this.value = value;
        return this;
    }

    public FormElementYesNoNA setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementYesNoNA setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public FormElementYesNoNA setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementYesNoNA setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementYesNoNA setIndex(int index) {
        this.pos = index;
        return this;
    }


    public FormElementYesNoNA setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementYesNoNA setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementYesNoNA setCallback(YesNoNACallBack callback) {
        this.yesNoNACallBack = callback;
        return this;
    }

    public YesNoNACallBack getCallback() {
        return this.yesNoNACallBack;
    }


    public FormElementYesNoNA setRefresh(Boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    public FormElementYesNoNA setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementYesNoNA setAttribute(String attribute) {
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

        return "YesNoNA";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: 1";
    }
}
