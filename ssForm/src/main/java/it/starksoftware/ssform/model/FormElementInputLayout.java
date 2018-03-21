package it.starksoftware.ssform.model;

import java.util.ArrayList;
import java.util.List;


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

    private boolean visibility = true;

    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mHint; // title to be shown on left
    private String mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker

    private boolean required = false;
    private String requiredResponseMessage = mHint;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementInputLayout setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public String getmHint() {
        return mHint;
    }

    public FormElementInputLayout setmHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElementInputLayout() {
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

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mHint + ", VALUE: " + this.mValue ;
    }
}
