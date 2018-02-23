package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.adapter.ImagePickerAdapter;

/**
 * Created by Adib on 16-Apr-17.
 */

public class FormElementImageView implements FormObject {


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

    public FormElementImageView() {
    }

    /**
     * static method to create instance
     * @return
     */
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
        this.mAct = act;
        return this;
    }

    public ImagePickerAdapter getSpinnerAdapter() {
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

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
