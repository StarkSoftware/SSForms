package it.starksoftware.ssform.model;




public class FormDivider implements FormObject {

    private boolean visibility = true;
    private int mTag;
    private boolean required = false;

    public boolean isRequired() {
        return required;
    }


    public FormDivider() {
    }

    public static FormDivider createInstance() {
        return new FormDivider();
    }

    public FormDivider setVisibility(boolean visibility) {
        this.visibility = visibility;
        return this;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public int getTag() {
        return mTag;
    }
    public FormDivider setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    @Override
    public boolean isHeader() {

        return true;
    }

    @Override
    public String getElementType() {

        return "Divider";
    }


}
