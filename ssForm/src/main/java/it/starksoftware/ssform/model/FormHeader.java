package it.starksoftware.ssform.model;

/**
 * Object for header of the form lists
 * Created by Adib on 18-Apr-17.
 */

public class FormHeader implements FormObject {

    private String mTitle;
    private boolean visibility = true;
    private int mTag;

    public FormHeader() {
    }

    public FormHeader setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public int getTag() {
        return mTag;
    }

    /**
     * static method to create instance
     * @return
     */
    public static FormHeader createInstance() {
        return new FormHeader();
    }

    /**
     * sets the title, returns itself
     * @param title
     * @return
     */
    public FormHeader setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public FormHeader setCustomVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getCustomVisibility() {
        return visibility;
    }

    /**
     * returns the title
     * @return
     */
    public String getTitle() {

        return this.mTitle;
    }

    @Override
    public boolean isHeader() {

        return true;
    }

    @Override
    public String getElementType() {

        return "Header";
    }

    @Override
    public String toString() {

        return this.mTitle;
    }
}
