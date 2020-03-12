package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FormElementSignature implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Bitmap mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private Context mCtx;
    private Activity mAct;
    private boolean visibility = true;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;
    private String dbField;
    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementSignature setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    private FormElementSignature() {
    }

    public static FormElementSignature createInstance() {
        return new FormElementSignature();
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementSignature setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public String getDbField() { return dbField; }
    public FormElementSignature setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    // getters and setters
    public FormElementSignature setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementSignature setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementSignature setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementSignature setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementSignature setValue(Bitmap mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementSignature setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementSignature setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementSignature setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementSignature setOptionsSelected(List<String> mOptionsSelected) {
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

    public Bitmap getValue() {
        return mValue;
    }

    public List<String> getOptions() {
        return (mOptions == null) ? new ArrayList<String>() : mOptions;
    }

    public List<String> getOptionsSelected() {
        return (mOptionsSelected == null) ? new ArrayList<String>() : mOptionsSelected;
    }


    public FormElementSignature setAttribute(String attribute) {
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

        return "Signature";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
