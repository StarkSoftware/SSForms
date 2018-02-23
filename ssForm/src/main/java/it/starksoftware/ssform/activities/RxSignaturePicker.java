package it.starksoftware.ssform.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import it.starksoftware.ssform.signaturepad.SignaturePicker;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;


public class RxSignaturePicker {

    @SuppressLint("StaticFieldLeak")
    private static RxSignaturePicker INSTANCE;

    public static RxSignaturePicker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RxSignaturePicker();
        }
        return INSTANCE;
    }

    /* --------------------------------------------------- */
    /* > RxImagePicker */
    /* --------------------------------------------------- */

    private SerializedSubject<Bitmap, Bitmap> subject;

    private RxSignaturePicker() {
        this.subject = new SerializedSubject<>(PublishSubject.<Bitmap>create());
    }

    public Observable<Bitmap> start(Context context, SignaturePicker imagePicker) {
        startImagePicker(context, imagePicker);
        return subject;
    }

    private void startImagePicker(Context context, SignaturePicker imagePicker) {
        imagePicker.single();
        imagePicker.folderMode(true);
        Bundle bundle = imagePicker.getIntent(context).getExtras();
        Intent intent = ShadowSignatureActivity.getStartIntent(context, bundle)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        context.startActivity(intent);
    }

    void onHandleResult(Bitmap images) {

        subject.onNext(images);
    }
}

