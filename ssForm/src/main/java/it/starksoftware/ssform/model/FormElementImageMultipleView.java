package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.adapter.ImageCardAdapter;
import it.starksoftware.ssform.adapter.ImageMultiplePickerAdapter;

/**
 * Created by Adib on 16-Apr-17.
 */

public class FormElementImageMultipleView implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private List<Bitmap> mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private Context mCtx;
    private Activity mAct;
    private int maxImages;
    private boolean visibility = true;
    private ImageCardAdapter imgAdapter;

    public FormElementImageMultipleView() {
    }

    public ImageCardAdapter getImgAdapter() {
        return imgAdapter;
    }

    public FormElementImageMultipleView setImgAdapter(ImageCardAdapter imgAdapter) {
        this.imgAdapter = imgAdapter;
        return this;
    }

    /**
     * static method to create instance
     * @return
     */
    public static FormElementImageMultipleView createInstance() {
        return new FormElementImageMultipleView();
    }

    public FormElementImageMultipleView setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }


    // getters and setters
    public FormElementImageMultipleView setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementImageMultipleView setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementImageMultipleView setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementImageMultipleView setValue(List<Bitmap> mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementImageMultipleView setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementImageMultipleView setOptionsSelected(List<String> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementImageMultipleView setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementImageMultipleView setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementImageMultipleView setMaxImages(int maxImages) {
        this.maxImages = maxImages;
        return this;
    }

    public ImageMultiplePickerAdapter getSpinnerAdapter() {
        return new ImageMultiplePickerAdapter(mCtx,null,null);
    }

    public FormElementImageMultipleView notifyAdapterChange() {
        ImageMultiplePickerAdapter adapter = getSpinnerAdapter();
        adapter.notifyDataSetChanged();
        return this;
    }

    public int getMaxImages() {
        return maxImages;
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

    public List<Bitmap> getValue() {
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

        return "ImageViewMultiple";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
