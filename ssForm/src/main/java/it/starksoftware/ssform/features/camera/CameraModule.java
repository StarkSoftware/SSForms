package it.starksoftware.ssform.features.camera;

import android.content.Context;
import android.content.Intent;

import it.starksoftware.ssform.features.ImagePickerConfig;

public interface CameraModule {
    Intent getCameraIntent(Context context, ImagePickerConfig config);

    void getImage(Context context, Intent intent, OnImageReadyListener imageReadyListener);
}