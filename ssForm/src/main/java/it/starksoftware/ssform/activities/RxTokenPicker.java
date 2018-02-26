package it.starksoftware.ssform.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.features.ImagePicker;
import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.Image;
import it.starksoftware.ssform.model.TokesTags;
import it.starksoftware.ssform.tokens.TokensPicker;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;


public class RxTokenPicker {

    @SuppressLint("StaticFieldLeak")
    private static RxTokenPicker INSTANCE;

    public static RxTokenPicker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RxTokenPicker();
        }
        return INSTANCE;
    }

    /* --------------------------------------------------- */
    /* > RxTokenPicker */
    /* --------------------------------------------------- */

    private SerializedSubject<ArrayList<FormTokenObject>, ArrayList<FormTokenObject>> subject;

    private RxTokenPicker() {
        this.subject = new SerializedSubject<>(PublishSubject.<ArrayList<FormTokenObject>>create());
    }

    public Observable<ArrayList<FormTokenObject>> start(Context context, TokensPicker tokensPicker, ArrayList<FormTokenObject> objectTokens) {
        startTokenPicker(context, tokensPicker, objectTokens);
        return subject;
    }

    private void startTokenPicker(Context context, TokensPicker tokensPicker, ArrayList<FormTokenObject> objectTokens) {

        Bundle bundle = tokensPicker.getIntent(context).getExtras();
        Intent intent = ShadowTokenActivity.getStartIntent(context, bundle)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra("DATA_SOURCE", objectTokens);
        context.startActivity(intent);
    }

    void onHandleResult(ArrayList<FormTokenObject> images) {

        subject.onNext(images);
    }
}

