package it.starksoftware.ssform.model;

import android.widget.RadioButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.interfaces.SegmentCallBack;


public class FormElementSegment implements FormObject {

    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private boolean mValue; // value to be shown on right

    private boolean mValueCheckA;
    private boolean mValueCheckB;
    private boolean visibility = true;

    private List<String> mOptions; // list of options for single and multi picker
    private List<String> mOptionsSelected; // list of selected options for single and multi picker
    private ArrayList<RadioButton> segmentedButtons;
    private SegmentCallBack segmentCallBack;
    private String mAttribute;
    private String dbField;
    public ArrayList<RadioButton> getSegmentedButtons() {
        return segmentedButtons;
    }
    private boolean required = false;
    private String requiredResponseMessage = mTitle;

    public String getRequiredResponseMessage() {
        return requiredResponseMessage;
    }

    public FormElementSegment setRequiredResponseMessage(String requiredResponseMessage) {
        this.requiredResponseMessage = requiredResponseMessage;
        return this;
    }

    public SegmentCallBack getSegmentCallBack() {
        return segmentCallBack;
    }

    public FormElementSegment setCallBack(SegmentCallBack segmentCallBack) {
        this.segmentCallBack = segmentCallBack;
        return this;
    }

    public String getDbField() { return dbField; }
    public FormElementSegment setDbField(String dbField) {
        this.dbField = dbField;
        return this;
    }

    public FormElementSegment setSegmentedButtons(ArrayList<RadioButton> segmentedButtons) {
        this.segmentedButtons = segmentedButtons;
        return this;
    }

    private FormElementSegment() {
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    public FormElementSegment setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public static FormElementSegment createInstance() {
        return new FormElementSegment();
    }

    public FormElementSegment setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    // getters and setters
    public FormElementSegment setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElementSegment setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElementSegment setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElementSegment setValue(boolean mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElementSegment setValueCheckA(boolean mValue) {
        this.mValueCheckA = mValue;
        return this;
    }

    public FormElementSegment setValueCheckB(boolean mValue) {
        this.mValueCheckB = mValue;
        return this;
    }

    public FormElementSegment setOptions(List<String> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElementSegment setOptionsSelected(List<String> mOptionsSelected) {
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

    public boolean getValue() {
        return mValue;
    }

    public boolean getValueCheckA() {
        return mValueCheckA;
    }
    public boolean getValueCheckB() {
        return mValueCheckB;
    }

    public FormElementSegment setAttribute(String attribute) {
        this.mAttribute = attribute;
        return this;
    }

    public String getAttribute() {
        return mAttribute;
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

        return "Segment";
    }

    @NotNull
    @Override
    public String toString() {
        return "TAG: " + this.mTag + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue ;
    }
}
