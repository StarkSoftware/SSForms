package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.adapter.ImagePickerAdapter;


public class FormElementImageView implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Bitmap mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private Context mCtx;
    private boolean visibility = true;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;
    private String dbField;
    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementImageView setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    private FormElementImageView() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementImageView setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public String getDbField() { return dbField; }
    public FormElementImageView setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public static FormElementImageView createInstance() {
        return new FormElementImageView();
    }

    public FormElementImageView setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }


    // getters and setters
    public FormElementImageView setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementImageView setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementImageView setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementImageView setValue(Bitmap mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementImageView setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementImageView setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementImageView setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementImageView setActivity(Activity act) {
        return this;
    }

    public FormElementImageView setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }

    private ImagePickerAdapter getSpinnerAdapter() {
        return new ImagePickerAdapter(mCtx,null,null);
    }

    public FormElementImageView notifyAdapterChange() {
        ImagePickerAdapter adapter = getSpinnerAdapter();
        adapter.notifyDataSetChanged();
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

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "ImageView";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
