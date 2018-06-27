package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import it.starksoftware.ssform.interfaces.CheckBoxCallBack;
import it.starksoftware.ssform.interfaces.PlaceDialogCallBack;

public class FormElementPlaceDialog implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private Context mCtx;
    private Activity mAct;
    private String value;
    private PlaceDialogCallBack placeDialogCallBack;
    private boolean refresh;
    private boolean visibility = true;
    private int pos;
    private String dialogTitle;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementPlaceDialog setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementPlaceDialog() {
    }

    public Context getmCtx() {
        return mCtx;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public FormElementPlaceDialog setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
        return this;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementPlaceDialog setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public FormElementPlaceDialog setmCtx(Context mCtx) {
        this.mCtx = mCtx;
        return this;
    }

    public Activity getmAct() {
        return mAct;
    }

    public FormElementPlaceDialog setmAct(Activity mAct) {
        this.mAct = mAct;
        return this;
    }

    public static FormElementPlaceDialog createInstance() {
        return new FormElementPlaceDialog();
    }

    // getters and setters
    public FormElementPlaceDialog setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public String getValue() {
        return value;
    }

    public FormElementPlaceDialog setValue(String value) {
        this.value = value;
        return this;
    }

    public FormElementPlaceDialog setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementPlaceDialog setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementPlaceDialog setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }

    public FormElementPlaceDialog setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }


    public boolean getVisibility() {
        return visibility;
    }

    public FormElementPlaceDialog setIndex(int index) {
        this.pos = index;
        return this;
    }


    public FormElementPlaceDialog setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementPlaceDialog setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementPlaceDialog setCallback(PlaceDialogCallBack callback) {
        this.placeDialogCallBack = callback;
        return this;
    }

    public PlaceDialogCallBack getCallback() {
        return this.placeDialogCallBack;
    }


    public FormElementPlaceDialog setRefresh(Boolean refresh) {
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

        return "PlaceDialog";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.value;
    }
}
