package it.starksoftware.ssform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.starksoftware.ssform.DateSwitcher.DateSwitcher;
import it.starksoftware.ssform.interfaces.DateSwitcherCallBack;
import it.starksoftware.ssform.interfaces.DateTimeCallBack;

public class FormElementDateSwitcher implements FormObject {

    private boolean visibility = true;
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Date mValue; // value to be shown on right
    private Date minDate; // value to be shown on right
    private Date maxDate; // value to be shown on right
    private Date defaultDate; // value to be shown on right
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private DateSwitcher.Type dateSwitcherType;
    private DateSwitcherCallBack dateSwitcherCallBack;
    private String mAttribute;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementDateSwitcher setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementDateSwitcher() {
    }

    public DateSwitcherCallBack getDateSwitcherCallBack() {
        return dateSwitcherCallBack;
    }

    public FormElementDateSwitcher setDateSwitcherCallBack(DateSwitcherCallBack dateSwitcherCallBack) {
        this.dateSwitcherCallBack = dateSwitcherCallBack;
        return this;
    }

    public DateSwitcher.Type getDateSwitcherType() {
        return dateSwitcherType;
    }

    public FormElementDateSwitcher setDateSwitcherType(DateSwitcher.Type dateSwitcherType) {
        this.dateSwitcherType = dateSwitcherType;
        return this;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementDateSwitcher setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementDateSwitcher setVisibility(boolean visibility) {
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
    public static FormElementDateSwitcher createInstance() {
        return new FormElementDateSwitcher();
    }

    // getters and setters
    public FormElementDateSwitcher setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementDateSwitcher setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementDateSwitcher setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementDateSwitcher setValue(Date mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementDateSwitcher setAttribute(String attribute) {
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

    public String getTitle() {
        return mTitle;
    }

    public Date getValue() {
        return mValue;
    }

    public Date getMinDate() {
        return minDate;
    }

    public FormElementDateSwitcher setMinDate(Date minDate) {
        this.minDate = minDate;
        return this;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public FormElementDateSwitcher setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public Date getDefaultDate() {
        return defaultDate;
    }

    public FormElementDateSwitcher setDetafultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
        return this;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "DateSwitcher";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
