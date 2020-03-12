package it.starksoftware.ssform.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.interfaces.TextCallBack;


public class FormElementInputLayout implements FormObject {

    // different types for the form elements
    public static final int TYPE_EDITTEXT_TEXT_SINGLELINE = 1;
    public static final int TYPE_EDITTEXT_TEXT_MULTILINE = 2;
    public static final int TYPE_EDITTEXT_NUMBER = 3;
    public static final int TYPE_EDITTEXT_EMAIL = 4;
    public static final int TYPE_EDITTEXT_PHONE = 5;
    public static final int TYPE_PICKER_DATE = 6;
    public static final int TYPE_PICKER_TIME = 7;
    public static final int TYPE_SPINNER_DROPDOWN = 8;
    public static final int TYPE_PICKER_MULTI_CHECKBOX = 9;
    public static final int TYPE_EDITTEXT_PASSWORD = 10;
    public static final int TYPE_EDITTEXT_NUMBER_INTEGER = 11;
    private TextCallBack textCallBack;
    private boolean visibility = true;
    private boolean readOnly = false;
    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mHint; // title to be shown on left
    private String mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private String dbField;
    private boolean required = false;
    private String requiredResponseMessage = mHint;
    private String mAttribute;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementInputLayout setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public String getDbField() { return dbField; }
    public FormElementInputLayout setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public String getmHint() {
        return mHint;
    }

    public FormElementInputLayout setmHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    private FormElementInputLayout() {
    }

    public FormElementInputLayout setCallback(TextCallBack callback) {
        this.textCallBack = callback;
        return this;
    }

    public TextCallBack getCallback() {
        return this.textCallBack;
    }

    public FormElementInputLayout setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public static FormElementInputLayout createInstance() {
        return new FormElementInputLayout();
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public FormElementInputLayout setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    // getters and setters
    public FormElementInputLayout setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementInputLayout setType(int mType) {
        this.mType = mType;
        return this;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementInputLayout setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementInputLayout setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementInputLayout setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementInputLayout setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementInputLayout setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
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

    public List<String> getOptions() {
        return (mOptions == null) ? new ArrayList<String>() : mOptions;
    }

    public List<String> getOptionsSelected() {
        return (mOptionsSelected == null) ? new ArrayList<String>() : mOptionsSelected;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "InputLayout";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", TITLE: " + this.mHint + ", VALUE: " + this.mValue ;
    }
}
