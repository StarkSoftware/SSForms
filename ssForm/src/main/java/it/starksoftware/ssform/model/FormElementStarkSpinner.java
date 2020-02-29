package it.starksoftware.ssform.model;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormSpinAdapter;
import it.starksoftware.ssform.adapter.StarkSpinnerAdapter;
import it.starksoftware.ssform.interfaces.SpinnerCallBack;
import it.starksoftware.ssform.interfaces.SpinnerStarkCallBack;

public class FormElementStarkSpinner implements FormObject {

    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private FormSpinnerObject mValue; // value to be shown on right
    private Context mCtx;
    private Activity mAct;
    private List<FormSpinnerObject> mOptions;
    private FormSpinnerObject mOptionsSelected; // list of selected options for single and multi picker
    private SpinnerStarkCallBack spinCallback;
    private boolean refresh;
    private boolean visibility = true;
    private boolean showIcon = true;
    private boolean allowEmptySelection = true;
    private int pos;
    private boolean required = false;
    private String requiredResponseMessage = mTitle;
    private String mAttribute;
    private String dbField;
    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementStarkSpinner setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public FormElementStarkSpinner() {
    }



    public static FormElementStarkSpinner createInstance() {
        return new FormElementStarkSpinner();
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public String getDbField() { return dbField; }
    public FormElementStarkSpinner setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public boolean isShowIcon() {
        return showIcon;
    }

    public FormElementStarkSpinner setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        return this;
    }

    public FormElementStarkSpinner setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public boolean isAllowEmptySelection() {
        return allowEmptySelection;
    }

    public FormElementStarkSpinner setAllowEmptySelection(boolean allowEmptySelection) {
        this.allowEmptySelection = allowEmptySelection;
        return this;
    }

    // getters and setters
    public FormElementStarkSpinner setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementStarkSpinner setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementStarkSpinner setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementStarkSpinner setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public FormElementStarkSpinner setIndex(int index) {
        this.pos = index;
        return this;
    }

    public FormElementStarkSpinner setValue(FormSpinnerObject mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementStarkSpinner setSpinnerObject(List<FormSpinnerObject> data) {
        this.mOptions = data;
        return this;
    }

    public FormElementStarkSpinner setContext(Context ctx) {
        this.mCtx = ctx;
        return this;
    }

    public FormElementStarkSpinner setActivity(Activity act) {
        this.mAct = act;
        return this;
    }

    public FormElementStarkSpinner setCallback(SpinnerStarkCallBack callback) {
        this.spinCallback = callback;
        return this;
    }

    public SpinnerStarkCallBack getCallback() {
        return this.spinCallback;
    }

    public StarkSpinnerAdapter getSpinnerAdapter() {
        return new StarkSpinnerAdapter(this.mAct, this.mOptions, this.showIcon, this.allowEmptySelection);
    }

    public FormElementStarkSpinner notifyAdapterChange() {
        StarkSpinnerAdapter adapter = getSpinnerAdapter();
        adapter.notifyDataSetChanged();
        return this;
    }


    public FormElementStarkSpinner setSpinnerObjectSelected(FormSpinnerObject mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public FormElementStarkSpinner setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
    }


    public FormElementStarkSpinner setRefresh(Boolean refresh) {
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

        return "StarkSpinner";
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
