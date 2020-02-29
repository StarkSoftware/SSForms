package it.starksoftware.ssform.signaturepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import it.starksoftware.ssform.model.Image;

public abstract class SignaturePicker {

    public static final String EXTRA_SELECTED_IMAGES = "selectedImages";
    public static final String EXTRA_LIMIT = "limit";
    public static final String EXTRA_SHOW_CAMERA = "showCamera";
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_FOLDER_MODE = "folderMode";
    public static final String EXTRA_FOLDER_TITLE = "folderTitle";
    public static final String EXTRA_IMAGE_TITLE = "imageTitle";
    public static final String EXTRA_IMAGE_DIRECTORY = "imageDirectory";
    public static final String EXTRA_RETURN_AFTER_FIRST = "returnAfterFirst";

    public static final int MAX_LIMIT = 99;

    public static final int MODE_SINGLE = 1;
    public static final int MODE_MULTIPLE = 2;

    private SignaturePickerConfig config;

    public abstract void start(int requestCode);

    public static class SignaturePickerWithActivity extends SignaturePicker {

        private Activity activity;

        public SignaturePickerWithActivity(Activity activity) {
            this.activity = activity;
            init(activity);
        }

        @Override
        public void start(int requestCode) {
            Intent intent = getIntent(activity);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static class SignaturePickerWithFragment extends SignaturePicker {

        private Fragment fragment;

        public SignaturePickerWithFragment(Fragment fragment) {
            this.fragment = fragment;
            init(fragment.getActivity());
        }

        @Override
        public void start(int requestCode) {
            Intent intent = getIntent(fragment.getActivity());
            fragment.startActivityForResult(intent, requestCode);
        }
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    public void init(Context context) {
        config = new SignaturePickerConfig(context);
    }

    public static SignaturePickerWithActivity create(Activity activity) {
        return new SignaturePickerWithActivity(activity);
    }

    public static SignaturePickerWithFragment create(Fragment fragment) {
        return new SignaturePickerWithFragment(fragment);
    }

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    public SignaturePicker single() {
        config.setMode(SignaturePicker.MODE_SINGLE);
        return this;
    }

    public SignaturePicker multi() {
        config.setMode(SignaturePicker.MODE_MULTIPLE);
        return this;
    }

    public SignaturePicker returnAfterFirst(boolean returnAfterFirst) {
        config.setReturnAfterFirst(returnAfterFirst);
        return this;
    }

    public SignaturePicker limit(int count) {
        config.setLimit(count);
        return this;
    }

    public SignaturePicker showCamera(boolean show) {
        config.setShowCamera(show);
        return this;
    }

    public SignaturePicker folderTitle(String title) {
        config.setFolderTitle(title);
        return this;
    }

    public SignaturePicker imageTitle(String title) {
        config.setImageTitle(title);
        return this;
    }

    public SignaturePicker origin(ArrayList<Image> images) {
        config.setSelectedImages(images);
        return this;
    }

    public SignaturePicker folderMode(boolean folderMode) {
        config.setFolderMode(folderMode);
        return this;
    }

    public SignaturePicker imageDirectory(String directory) {
        config.setImageDirectory(directory);
        return this;
    }

    public SignaturePicker theme(@StyleRes int theme) {
        config.setTheme(theme);
        return this;
    }

    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, SignaturePickerActivity.class);
        intent.putExtra(SignaturePickerConfig.class.getSimpleName(), config);
        return intent;
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    public static Bitmap getImages(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Bitmap) intent.getExtras().get("BitMapSignature");
    }
}
