package it.starksoftware.ssform.model;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.interfaces.RatingCallBack;


public class FormElementRating implements FormObject {



    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private int stars;
    private long ratingValue;
    private RatingCallBack ratingCallBack;
    private boolean visibility = true;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;
    private String dbField;
    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementRating setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementRating() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementRating setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public String getDbField() { return dbField; }
    public FormElementRating setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public static FormElementRating createInstance() {
        return new FormElementRating();
    }

    public int getStars() {
        return stars;
    }

    public long getRatingValue() {
        return ratingValue;
    }

    public RatingCallBack getRatingCallBack() {
        return ratingCallBack;
    }

    public FormElementRating setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementRating setCallBack(RatingCallBack ratingCallBack) {
        this.ratingCallBack = ratingCallBack;
        return this;
    }

    public FormElementRating setRatingValue(long ratingValue) {
        this.ratingValue = ratingValue;
        return this;
    }

    public FormElementRating setStars(int stars) {
        this.stars = stars;
        return this;
    }

    // getters and setters
    public FormElementRating setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementRating setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementRating setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementRating setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementRating setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementRating setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementRating setAttribute(String attribute) {
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

        return "Rating";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
