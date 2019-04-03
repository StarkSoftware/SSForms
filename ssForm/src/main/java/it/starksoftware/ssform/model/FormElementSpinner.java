package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormSpinAdapter;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;

public class FormElementSpinner implements FormObject {


    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private FormSpinnerObject mValue; // value to be shown on right
    private Context mCtx;
    private Activity mAct;
    private List<FormSpinnerObject> mOptions;
    private FormSpinnerObject mOptionsSelected; // list of selected options for single and multi picker
    private SpinnerCallBack spinCallback;
    private boolean refresh;
    private boolean visibility = true;
    private int pos;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;
    private String dbField;
    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementSpinner setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementSpinner() {
    }



    public static FormElementSpinner createInstance() {
        return new FormElementSpinner();
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public String getDbField() { return dbField; }
    public FormElementSpinner setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public FormElementSpinner setRequired(boolean required) {
        this.required = required;
        return this;
    }

    // getters and setters
    public FormElementSpinner setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementSpinner setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementSpinner setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementSpinner setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementSpinner setIndex(int index) {
        this.pos = index;
        return this;
    }

    public FormElementSpinner setValue(FormSpinnerObject mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementSpinner setSpinnerObject(List<FormSpinnerObject> data) {
        this.mOptions = data;
        return this;
    }

    public FormElementSpinner setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementSpinner setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementSpinner setCallback(SpinnerCallBack callback) {
        this.spinCallback = callback;
        return this;
    }

    public SpinnerCallBack getCallback() {
        return this.spinCallback;
    }

    public FormSpinAdapter getSpinnerAdapter() {
        return new FormSpinAdapter(this.mAct, R.layout.spinner_item, this.mOptions);
    }

    public FormElementSpinner notifyAdapterChange() {
        FormSpinAdapter adapter = getSpinnerAdapter();
        adapter.notifyDataSetChanged();
        return this;
    }


    public FormElementSpinner setSpinnerObjectSelected(FormSpinnerObject mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementSpinner setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }


    public FormElementSpinner setRefresh(Boolean refresh) {
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

    public FormSpinnerObject getValue() {
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

        return "Spinner";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
