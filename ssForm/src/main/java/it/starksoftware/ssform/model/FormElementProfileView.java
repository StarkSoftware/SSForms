package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.adapter.ImagePickerAdapter;


public class FormElementProfileView implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Bitmap mProfileImage; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private Context mCtx;
    private Activity mAct;
    private boolean visibility = true;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mProfileName;
    private String mAttribute;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementProfileView setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementProfileView() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementProfileView setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public static FormElementProfileView createInstance() {
        return new FormElementProfileView();
    }

    public FormElementProfileView setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }


    // getters and setters
    public FormElementProfileView setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public FormElementProfileView setProfileName(String mProfileName) {
        this.mProfileName = mProfileName;
        return this;
    }

    public FormElementProfileView setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementProfileView setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementProfileView setProfileImage(Bitmap mValue) {
        this.mProfileImage = mValue;
        return this;
    }

    public FormElementProfileView setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementProfileView setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementProfileView setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementProfileView setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementProfileView setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }

    public ImagePickerAdapter getSpinnerAdapter() {
        return new ImagePickerAdapter(mCtx,null,null);
    }

    public FormElementProfileView notifyAdapterChange() {
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

    public Bitmap getProfileImage() {
        return mProfileImage;
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

        return "ProfileView";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mProfileImage ;
    }
}
