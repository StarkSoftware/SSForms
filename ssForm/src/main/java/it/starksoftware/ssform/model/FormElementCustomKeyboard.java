package it.starksoftware.ssform.model;

import android.content.Context;
import android.inputmethodservice.KeyboardView;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.customKeyboard.CustomKeyboard;

/**
 * Created by Adib on 16-Apr-17.
 */

public class FormElementCustomKeyboard implements FormObject {


    private boolean visibility = true;

    // private variables
    private Context ctx;
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private KeyboardView.OnKeyboardActionListener customListner;
    private CustomKeyboard mKeyboard;
//    private KeyboardView mKeyboardView;


    public FormElementCustomKeyboard() {
    }

    public Context getCtx() {
        return ctx;
    }

    public FormElementCustomKeyboard setContext(Context ctx) {
        this.ctx = ctx;
        return this;
    }

    public CustomKeyboard getmKeyboard() {
        return mKeyboard;
    }

    public FormElementCustomKeyboard setmKeyboard(CustomKeyboard mKeyboard) {
        this.mKeyboard = mKeyboard;
        return this;
    }

    public FormElementCustomKeyboard setVisibility(boolean visibility) {
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
    public static FormElementCustomKeyboard createInstance() {
        return new FormElementCustomKeyboard();
    }

    // getters and setters
    public FormElementCustomKeyboard setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementCustomKeyboard setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementCustomKeyboard setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementCustomKeyboard setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementCustomKeyboard setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementCustomKeyboard setOptionsSelected(List<String> mOptionsSelected) {
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

        return "CustomKeyboard";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
