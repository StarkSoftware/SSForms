package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormSpinAdapter;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;

public class FormElementMultiSelect implements FormObject {


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

    public FormElementMultiSelect setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    private FormElementMultiSelect() {
    }



    public static FormElementMultiSelect createInstance() {
        return new FormElementMultiSelect();
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public String getDbField() { return dbField; }
    public FormElementMultiSelect setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public FormElementMultiSelect setRequired(boolean required) {
        this.required = required;
        return this;
    }

    // getters and setters
    public FormElementMultiSelect setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementMultiSelect setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementMultiSelect setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementMultiSelect setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementMultiSelect setIndex(int index) {
        this.pos = index;
        return this;
    }

    public FormElementMultiSelect setValue(FormSpinnerObject mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementMultiSelect setSpinnerObject(List<FormSpinnerObject> data) {
        this.mOptions = data;
        return this;
    }

    public FormElementMultiSelect setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementMultiSelect setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementMultiSelect setCallback(SpinnerCallBack callback) {
        this.spinCallback = callback;
        return this;
    }

    public SpinnerCallBack getCallback() {
        return this.spinCallback;
    }

    public FormSpinAdapter getSpinnerAdapter() {
        return new FormSpinAdapter(this.mAct, R.layout.spinner_item, this.mOptions);
    }

    public FormElementMultiSelect notifyAdapterChange() {
        FormSpinAdapter adapter = getSpinnerAdapter();
        adapter.notifyDataSetChanged();
        return this;
    }


    public FormElementMultiSelect setSpinnerObjectSelected(FormSpinnerObject mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementMultiSelect setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }


    public FormElementMultiSelect setRefresh(Boolean refresh) {
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

        return "MultiSelect";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
