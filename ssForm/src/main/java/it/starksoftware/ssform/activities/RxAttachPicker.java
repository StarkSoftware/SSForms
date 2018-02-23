package it.starksoftware.ssform.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import it.starksoftware.ssform.attach.AttachPicker;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;


public class RxAttachPicker {

    @SuppressLint("StaticFieldLeak")
    private static RxAttachPicker INSTANCE;

    public static RxAttachPicker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RxAttachPicker();
        }
        return INSTANCE;
    }

    /* --------------------------------------------------- */
    /* > RxImagePicker */
    /* --------------------------------------------------- */

    private SerializedSubject<ArrayList<String>, ArrayList<String>> subject;

    private RxAttachPicker() {
        this.subject = new SerializedSubject<>(PublishSubject.<ArrayList<String>>create());
    }

    public Observable<ArrayList<String>> start(Context context, AttachPicker imagePicker, ArrayList<String> currentAttachs) {
        startImagePicker(context, imagePicker, currentAttachs);
        return subject;
    }

    private void startImagePicker(Context context, AttachPicker attachPicker, ArrayList<String> currentAttachs) {
        attachPicker.single();
        attachPicker.folderMode(true);
        attachPicker.setCurrentAttachs(currentAttachs);
        Bundle bundle = attachPicker.getIntent(context).getExtras();
        Intent intent = ShadowAttachActivity.getStartIntent(context, bundle)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        context.startActivity(intent);
    }

    void onHandleResult(ArrayList<String> attachs) {

        subject.onNext(attachs);
    }
}

