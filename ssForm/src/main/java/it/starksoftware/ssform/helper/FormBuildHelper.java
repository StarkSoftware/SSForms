package it.starksoftware.ssform.helper;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.adapter.FormAdapter;
import it.starksoftware.ssform.model.FormDivider;
import it.starksoftware.ssform.model.FormElement;
import it.starksoftware.ssform.model.FormElementAttach;
import it.starksoftware.ssform.model.FormElementButton;
import it.starksoftware.ssform.model.FormElementCheckBox;
import it.starksoftware.ssform.model.FormElementCustomKeyboard;
import it.starksoftware.ssform.model.FormElementDateTime;
import it.starksoftware.ssform.model.FormElementImageMultipleView;
import it.starksoftware.ssform.model.FormElementImageView;
import it.starksoftware.ssform.model.FormElementMemo;
import it.starksoftware.ssform.model.FormElementPlaceDialog;
import it.starksoftware.ssform.model.FormElementRating;
import it.starksoftware.ssform.model.FormElementSearchableSpinner;
import it.starksoftware.ssform.model.FormElementSegment;
import it.starksoftware.ssform.model.FormElementSignature;
import it.starksoftware.ssform.model.FormElementSpinner;
import it.starksoftware.ssform.model.FormElementSwitch;
import it.starksoftware.ssform.model.FormElementToken;
import it.starksoftware.ssform.model.FormElementYesNo;
import it.starksoftware.ssform.model.FormElementYesNoNA;
import it.starksoftware.ssform.model.FormHeader;
import it.starksoftware.ssform.model.FormObject;
import it.starksoftware.ssform.model.Validator;

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

    public List<Validator> validateForm()
    {
        List<Validator> validatedItems = new ArrayList<>();

        List<FormObject> items = this.mFormAdapter.getFormItems();
        Validator vItem;

        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).isRequired()){
                if (items.get(i).getElementType().contentEquals("Switch")) {
                    FormElementSwitch element = (FormElementSwitch) items.get(i);
                    if (!element.getValue()) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("ImageView")) {
                    FormElementImageView element = (FormElementImageView) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Spinner")) {
                    FormElementSpinner element = (FormElementSpinner) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Memo")) {
                    FormElementMemo element = (FormElementMemo) items.get(i);
                    if (element.getValue().length() < 1) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Segment")) {
                    FormElementSegment element = (FormElementSegment) items.get(i);
                    if (!element.getValue()) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Attach")) {
                    FormElementAttach element = (FormElementAttach) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Signature")) {
                    FormElementSignature element = (FormElementSignature) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Rating")) {
                    FormElementRating element = (FormElementRating) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Basic")) {
                    FormElement element = (FormElement) items.get(i);
                    if (element.getValue().length() < 1) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("ImageViewMultiple")) {
                    FormElementImageMultipleView element = (FormElementImageMultipleView) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("DateTime")) {
                    FormElementDateTime element = (FormElementDateTime) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("SearchableSpinner")) {
                    FormElementSearchableSpinner element = (FormElementSearchableSpinner) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("PlaceDialog")) {
                    FormElementPlaceDialog element = (FormElementPlaceDialog) items.get(i);
                    if (element.getValue() == null) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }

                } else if (items.get(i).getElementType().contentEquals("Token")) {
                    FormElementToken element = (FormElementToken) items.get(i);
                    if (element.getValue().size() < 1) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }
                } else if (items.get(i).getElementType().contentEquals("YesNo")) {
                    FormElementYesNo element = (FormElementYesNo) items.get(i);
                    if (element.getValue() == 0) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }
                } else if (items.get(i).getElementType().contentEquals("YesNoNA")) {
                    FormElementYesNoNA element = (FormElementYesNoNA) items.get(i);
                    if (element.getValue() == 0) {
                        vItem = new Validator();
                        vItem.setValidatorMessage(element.getRequiredResponseMessage());
                        vItem.setValidatorTag(element.getTag());
                        vItem.setValidatorStatus(false);
                        validatedItems.add(vItem);
                    }
                }
            }

        }

        return validatedItems;
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

    public FormElementYesNo getFormElementYesNo(int tag) {

        return this.mFormAdapter.getFormElementYesNoByTag(tag);
    }

    public FormElementYesNoNA getFormElementYesNoNA(int tag) {

        return this.mFormAdapter.getFormElementYesNoNAByTag(tag);
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