package it.starksoftware.ssform.model;


import android.content.Context;

import org.jetbrains.annotations.NotNull;

public class FormElementMemo implements FormObject {

    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mValue; // value to be shown on right
    private boolean visibility = true;
    private String mTitle;
    private Context mCtx;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;
    private String dbField;
    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementMemo setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    private FormElementMemo() {
    }

    public static FormElementMemo createInstance() {

        return new FormElementMemo();
    }

    public FormElementMemo setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public String getDbField() { return dbField; }
    public FormElementMemo setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public FormElementMemo setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementMemo setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementMemo setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementMemo setType(int mType) {
        this.mType = mType;
        return this;
    }
    public FormElementMemo setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementMemo setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementMemo setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getTag() {
        return mTag;
    }

    public int getType() {
        return mType;
    }

    public String getValue() {

        return (mValue == null) ? "" : mValue;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "Memo";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", VALUE: " + this.mValue ;
    }
}
