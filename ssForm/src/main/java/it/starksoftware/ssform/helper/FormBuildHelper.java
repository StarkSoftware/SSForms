package it.starksoftware.ssform.helper;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormDivider;
import it.starksoftware.ssform.model.FormElement;
import it.starksoftware.ssform.model.FormElementImageView;
import it.starksoftware.ssform.model.FormElementMemo;
import it.starksoftware.ssform.model.FormElementRating;
import it.starksoftware.ssform.model.FormElementSpinner;
import it.starksoftware.ssform.model.FormElementSwitch;
import it.starksoftware.ssform.model.FormObject;

public class FormBuildHelper {

    private FormAdapter mFormAdapter;

    public FormBuildHelper(Context context, Activity activity, RecyclerView recyclerView, FragmentManager fragmentManager) {
        // initialize form adapter
        this.mFormAdapter = new FormAdapter(context, activity, fragmentManager);

        // set up the recyclerview with adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mFormAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    public static String getCurrentVersion()
    {



        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        return String.format("%s.%s", versionName, versionCode);
    }

    public void addFormElements(List<FormObject> formElements) {
        this.mFormAdapter.addElements(formElements);
    }

    public void removeFormElement(FormObject formElement) {
        this.mFormAdapter.removeElement(formElement);
        //this.mFormAdapter.notifyDataSetChanged();
    }


    public void hideFormElement(FormObject formElement) {
        this.mFormAdapter.removeElement(formElement);
    }

    public void refreshView() {
        this.mFormAdapter.notifyDataSetChanged();
    }

    public void refreshElementByTag(int tag) {

        this.mFormAdapter.getValueAtTag(tag).notifyAll();
    }

    public void refreshSpinnerByTag(int tag) {
        synchronized(this.mFormAdapter.getSpinnerValueAtTag(tag)){
            this.mFormAdapter.getSpinnerValueAtTag(tag).notifyAdapterChange();
        }
    }

    public void refreshItemByTag(int tag) {
        int position = this.mFormAdapter.getPositionByTag(tag);
        this.mFormAdapter.notifyItemChanged(position);
    }

    public FormElement getFormElement(int tag) {

        return this.mFormAdapter.getValueAtTag(tag);
    }

    public FormElementRating getFormRatingElement(int tag) {

        return this.mFormAdapter.getRatingValueAtTag(tag);
    }


    public FormDivider getDividerElement(int tag) {

        return this.mFormAdapter.getDividerValueAtTag(tag);
    }

    public FormElementMemo getFormMemoElement(int tag) {

        return this.mFormAdapter.getMemoValueAtTag(tag);
    }

    public FormElementImageView getFormImageViewElement(int tag) {

        return this.mFormAdapter.getImageValueAtTag(tag);
    }

    public FormElementSwitch getFormSwitchElement(int tag) {

        return this.mFormAdapter.getSwitchValueAtTag(tag);
    }

    public FormElementSpinner getFormSpinnerElement(int tag) {

        return this.mFormAdapter.getSpinnerValueAtTag(tag);
    }
}