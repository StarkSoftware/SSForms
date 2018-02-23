package it.starksoftware.ssform.helper;

import android.content.Context;
import android.content.Intent;

import it.starksoftware.ssform.features.ImagePickerConfig;
import it.starksoftware.ssform.model.Image;

import static it.starksoftware.ssform.features.ImagePicker.EXTRA_FOLDER_MODE;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_FOLDER_TITLE;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_IMAGE_DIRECTORY;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_IMAGE_TITLE;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_LIMIT;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_MODE;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_RETURN_AFTER_FIRST;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_SELECTED_IMAGES;
import static it.starksoftware.ssform.features.ImagePicker.EXTRA_SHOW_CAMERA;
import static it.starksoftware.ssform.features.ImagePicker.MAX_LIMIT;
import static it.starksoftware.ssform.features.ImagePicker.MODE_MULTIPLE;

public class IntentHelper {

    public static ImagePickerConfig makeConfigFromIntent(Context context, Intent intent) {
        ImagePickerConfig config = new ImagePickerConfig(context);
        config.setMode(intent.getIntExtra(EXTRA_MODE, MODE_MULTIPLE));
        config.setLimit(intent.getIntExtra(EXTRA_LIMIT, MAX_LIMIT));
        config.setShowCamera(intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true));
        config.setFolderTitle(intent.getStringExtra(EXTRA_FOLDER_TITLE));
        config.setImageTitle(intent.getStringExtra(EXTRA_IMAGE_TITLE));
        config.setSelectedImages(intent.<Image>getParcelableArrayListExtra(EXTRA_SELECTED_IMAGES));
        config.setFolderMode(intent.getBooleanExtra(EXTRA_FOLDER_MODE, true));
        config.setImageDirectory(intent.getStringExtra(EXTRA_IMAGE_DIRECTORY));
        config.setReturnAfterFirst(intent.getBooleanExtra(EXTRA_RETURN_AFTER_FIRST, false));
        return config;
    }
}
