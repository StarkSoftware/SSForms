package it.starksoftware.ssform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.starksoftware.ssform.interfaces.DateTimeCallBack;

/**
 * Created by Adib on 16-Apr-17.
 */

public class FormElementDateTime implements FormObject {

    public static final int TYPE_PICKER_DATE = 6;
    public static final int TYPE_PICKER_TIME = 7;
    public static final int TYPE_PICKER_DATE_TIME = 9;

    private boolean visibility = true;
    private DateTimeCallBack dateTimeCallBack;
    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Date mValue; // value to be shown on right
    private Date minDate; // value to be shown on right
    private Date maxDate; // value to be shown on right
    private Date defaultDate; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker

    public FormElementDateTime() {
    }

    public FormElementDateTime setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    /**
     * static method to create instance
     * @return
     */
    public static FormElementDateTime createInstance() {
        return new FormElementDateTime();
    }

    // getters and setters
    public FormElementDateTime setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementDateTime setCallback(DateTimeCallBack callback) {
        this.dateTimeCallBack = callback;
        return this;
    }

    public DateTimeCallBack getCallback() {
        return this.dateTimeCallBack;
    }

    public FormElementDateTime setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementDateTime setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementDateTime setValue(Date mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementDateTime setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementDateTime setOptionsSelected(List<String> mOptionsSelected) {
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

    public Date getValue() {
        return mValue;
    }

    public List<String> getOptions() {
        return (mOptions == null) ? new ArrayList<String>() : mOptions;
    }

    public List<String> getOptionsSelected() {
        return (mOptionsSelected == null) ? new ArrayList<String>() : mOptionsSelected;
    }

    public Date getMinDate() {
        return minDate;
    }

    public FormElementDateTime setMinDate(Date minDate) {
        this.minDate = minDate;
        return this;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public FormElementDateTime setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public Date getDefaultDate() {
        return defaultDate;
    }

    public FormElementDateTime setDetafultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
        return this;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "DateTime";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
