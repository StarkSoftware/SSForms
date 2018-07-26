package it.starksoftware.ssform.model;


import android.content.Context;

import com.hsalf.smilerating.BaseRating;

import java.util.HashMap;
import java.util.List;

import it.starksoftware.ssform.interfaces.RatingCallBack;
import it.starksoftware.ssform.interfaces.RatingSmileCallBack;

public class FormElementSmileRating implements FormObject {

    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private int mValue; // value to be shown on right
    private boolean visibility = true;
    private String mTitle;
    private Context mCtx;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private RatingSmileCallBack ratingSmileCallBack;
    public HashMap<Integer, String> smileTitleByValue;
    private String mAttribute;

    public HashMap<Integer, String> getSmileTitleByValue() {
        return smileTitleByValue;
    }

    public FormElementSmileRating setSmileTitleByValue(HashMap<Integer, String> smileTitleByValue) {
        this.smileTitleByValue = smileTitleByValue;
        return this;
    }

    public RatingSmileCallBack getRatingSmileCallBack() {
        return ratingSmileCallBack;
    }

    public FormElementSmileRating setRatingSmileCallBack(RatingSmileCallBack ratingSmileCallBack) {
        this.ratingSmileCallBack = ratingSmileCallBack;
        return this;
    }

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementSmileRating setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementSmileRating() {

    }

    public static FormElementSmileRating createInstance() {

        return new FormElementSmileRating();
    }

    public FormElementSmileRating setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementSmileRating setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementSmileRating setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementSmileRating setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementSmileRating setType(int mType) {
        this.mType = mType;
        return this;
    }
    public FormElementSmileRating setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementSmileRating setValue(int mValue) {
        this.mValue = mValue;
        return this;
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

    public int getValue() {

        return mValue;
    }

    public FormElementSmileRating setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }


    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "SmileRating";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", VALUE: " + this.mValue ;
    }
}
