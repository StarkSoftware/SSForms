package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.interfaces.SearchableSpinnerCallBack;


public class FormElementSearchableSpinner implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private String mValue; // value to be shown on right
    private Context mCtx;
    private Activity mAct;

    private String dialogTitle;
    private String dbField;
    private List<FormSpinnerObject> mOptions;
    private ArrayList<String> items;
    private FormSpinnerObject mOptionsSelected; // list of selected options for single and multi picker
    private SearchableSpinnerCallBack spinCallback;
    private boolean refresh;
    private boolean visibility = true;
    private int pos;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementSearchableSpinner setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementSearchableSpinner() {
    }

    public String getDbField() { return dbField; }
    public FormElementSearchableSpinner setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public static FormElementSearchableSpinner createInstance() {
        return new FormElementSearchableSpinner();
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementSearchableSpinner setRequired(boolean required) {
        this.required = required;
        return this;
    }

    // getters and setters
    public FormElementSearchableSpinner setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementSearchableSpinner setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementSearchableSpinner setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public ArrayList<String> getItems() {

        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < mOptions.size(); i++) {
            values.add(mOptions.get(i).getValue());
        }

        return values;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public FormElementSearchableSpinner setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
        return this;
    }

    public FormElementSearchableSpinner setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementSearchableSpinner setIndex(int index) {
        this.pos = index;
        return this;
    }

    public FormElementSearchableSpinner setValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementSearchableSpinner setSpinnerObject(List<FormSpinnerObject> data) {
        this.mOptions = data;
        return this;
    }

    public List<FormSpinnerObject> getSpinnerObject() {
        return mOptions;
    }



    public FormElementSearchableSpinner setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementSearchableSpinner setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementSearchableSpinner setCallback(SearchableSpinnerCallBack callback) {
        this.spinCallback = callback;
        return this;
    }

    public FormElementSearchableSpinner setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }

    public SearchableSpinnerCallBack getCallback() {
        return this.spinCallback;
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

    public String getValue() {
        return mValue;
    }

    public FormSpinnerObject getOptionsSelected() {
        return mOptionsSelected;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String getElementType() {

        return "SearchableSpinner";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
