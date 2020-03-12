package it.starksoftware.ssform.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.interfaces.TextCallBack;


public class FormElementLabel implements FormObject {

    private boolean visibility = true;
    private boolean refresh;
    private String dbField;

    // private variables
    private int mTag; // unique tag to identify the object
    private String mAttribute; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker

    private boolean required = false;
    private String requiredResponseMessage = mTitle;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementLabel setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    private FormElementLabel() {
    }

    public FormElementLabel setRefresh(Boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    public String getDbField() { return dbField; }
    public FormElementLabel setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public boolean getRefresh() {
        return refresh;
    }

    public FormElementLabel setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public static FormElementLabel createInstance() {
        return new FormElementLabel();
    }


    // getters and setters
    public FormElementLabel setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementLabel setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementLabel setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementLabel setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementLabel setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementLabel setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementLabel setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementLabel setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public int getTag() {
        return mTag;
    }


    public int getType() {
        return mType;
    }

    public String getTitle() {
        return mTitle;
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

        return "Label";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
