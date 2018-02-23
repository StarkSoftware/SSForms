package it.starksoftware.ssform.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.adapter.FormAttachAdapter;


public class FormElementAttach implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private ArrayList<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private Context mCtx;
    private boolean visibility = true;

    public FormElementAttach() {
    }


    public static FormElementAttach createInstance() {
        return new FormElementAttach();
    }

    public FormElementAttach setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }


    // getters and setters
    public FormElementAttach setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementAttach setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementAttach setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementAttach setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementAttach setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }


    public FormElementAttach refresh() {
        this.notifyAdapterChange();
        return this;
    }

    public FormElementAttach setOptions(ArrayList<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementAttach setOptionsSelected(List<String> mOptionsSelected) {
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
        return mValue;
    }

    public List<String> getOptions() {
        return (mOptions == null) ? new ArrayList<String>() : mOptions;
    }

    public FormAttachAdapter getAttachAdapter() {
        return new FormAttachAdapter(mOptions, this.mCtx);
    }

    public FormElementAttach notifyAdapterChange() {
        FormAttachAdapter adapter = getAttachAdapter();
        adapter.notifyDataSetChanged();
        return this;
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

        return "Attach";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
