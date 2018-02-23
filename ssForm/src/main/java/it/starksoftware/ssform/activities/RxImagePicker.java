package it.starksoftware.ssform.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import it.starksoftware.ssform.features.ImagePicker;
import it.starksoftware.ssform.model.Image;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;


public class RxImagePicker {

    @SuppressLint("StaticFieldLeak")
    private static RxImagePicker INSTANCE;

    public static RxImagePicker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RxImagePicker();
        }
        return INSTANCE;
    }

    /* --------------------------------------------------- */
    /* > RxImagePicker */
    /* --------------------------------------------------- */

    private SerializedSubject<List<Image>, List<Image>> subject;

    private RxImagePicker() {
        this.subject = new SerializedSubject<>(PublishSubject.<List<Image>>create());
    }

    public Observable<List<Image>> start(Context context, ImagePicker imagePicker, int maxImage) {
        startImagePicker(context, imagePicker, maxImage);
        return subject;
    }

    private void startImagePicker(Context context, ImagePicker imagePicker, int maxImage) {
        if (maxImage == 0) {
            imagePicker.single();

            imagePicker.folderMode(true);
            Bundle bundle = imagePicker.getIntent(context).getExtras();
            Intent intent = ShadowActivity.getStartIntent(context, bundle)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            context.startActivity(intent);

        }
        else {
            imagePicker.multi();
            imagePicker.limit(maxImage);

            imagePicker.folderMode(true);
            Bundle bundle = imagePicker.getIntent(context).getExtras();
            Intent intent = ShadowMultipleActivity.getStartIntent(context, bundle)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            context.startActivity(intent);
        }


    }

    void onHandleResult(List<Image> images) {

        subject.onNext(images);
    }
}

