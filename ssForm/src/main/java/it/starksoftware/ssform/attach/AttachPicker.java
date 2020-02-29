package it.starksoftware.ssform.attach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import it.starksoftware.ssform.model.Image;

public abstract class AttachPicker {

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

    private AttachPickerConfig config;

    private ArrayList<String> currentAttachs;

    public ArrayList<String> getCurrentAttachs() {
        return currentAttachs;
    }

    public void setCurrentAttachs(ArrayList<String> currentAttachs) {
        this.currentAttachs = currentAttachs;
    }

    public abstract void start(int requestCode);

    public static class AttachPickerWithActivity extends AttachPicker {

        private Activity activity;

        public AttachPickerWithActivity(Activity activity) {
            this.activity = activity;
            init(activity);
        }

        @Override
        public void start(int requestCode) {
            Intent intent = getIntent(activity);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static class AttachPickerWithFragment extends AttachPicker {

        private Fragment fragment;

        public AttachPickerWithFragment(Fragment fragment) {
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
        config = new AttachPickerConfig(context);
    }

    public static AttachPickerWithActivity create(Activity activity) {
        return new AttachPickerWithActivity(activity);
    }

    public static AttachPickerWithFragment create(Fragment fragment) {
        return new AttachPickerWithFragment(fragment);
    }

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    public AttachPicker single() {
        config.setMode(AttachPicker.MODE_SINGLE);
        return this;
    }

    public AttachPicker multi() {
        config.setMode(AttachPicker.MODE_MULTIPLE);
        return this;
    }

    public AttachPicker returnAfterFirst(boolean returnAfterFirst) {
        config.setReturnAfterFirst(returnAfterFirst);
        return this;
    }

    public AttachPicker limit(int count) {
        config.setLimit(count);
        return this;
    }

    public AttachPicker showCamera(boolean show) {
        config.setShowCamera(show);
        return this;
    }

    public AttachPicker folderTitle(String title) {
        config.setFolderTitle(title);
        return this;
    }

    public AttachPicker imageTitle(String title) {
        config.setImageTitle(title);
        return this;
    }

    public AttachPicker origin(ArrayList<Image> images) {
        config.setSelectedImages(images);
        return this;
    }

    public AttachPicker folderMode(boolean folderMode) {
        config.setFolderMode(folderMode);
        return this;
    }

    public AttachPicker imageDirectory(String directory) {
        config.setImageDirectory(directory);
        return this;
    }

    public AttachPicker theme(@StyleRes int theme) {
        config.setTheme(theme);
        return this;
    }

    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, AttachPickerActivity.class);
        intent.putExtra(AttachPickerConfig.class.getSimpleName(), config);
        intent.putExtra("ArrayCurrentAttach", getCurrentAttachs());
        return intent;
    }

    public static ArrayList<String> getAttach(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (ArrayList<String>)intent.getExtras().getSerializable("ArrayList");
    }
}
