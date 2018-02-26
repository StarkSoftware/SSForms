package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormSpinAdapter;
import it.starksoftware.ssform.interfaces.CheckBoxCallBack;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;

public class FormElementCheckBox implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Context mCtx;
    private Activity mAct;
    private boolean value;
    private CheckBoxCallBack checkBoxCallBack;
    private boolean refresh;
    private boolean visibility = true;
    private int pos;
    private boolean required = false;

    public FormElementCheckBox() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }


    public static FormElementCheckBox createInstance() {
        return new FormElementCheckBox();
    }

    // getters and setters
    public FormElementCheckBox setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public boolean isChecked() {
        return value;
    }

    public FormElementCheckBox setValue(boolean value) {
        this.value = value;
        return this;
    }

    public FormElementCheckBox setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementCheckBox setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementCheckBox setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementCheckBox setIndex(int index) {
        this.pos = index;
        return this;
    }


    public FormElementCheckBox setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementCheckBox setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementCheckBox setCallback(CheckBoxCallBack callback) {
        this.checkBoxCallBack = callback;
        return this;
    }

    public CheckBoxCallBack getCallback() {
        return this.checkBoxCallBack;
    }


    public FormElementCheckBox setRefresh(Boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    public boolean getRefresh() {
        return refresh;
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


    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "CheckBox";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: 1";
    }
}
