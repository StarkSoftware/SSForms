package it.starksoftware.ssform.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import it.starksoftware.ssform.features.ImagePicker;
import it.starksoftware.ssform.features.ImagePickerActivity;
import it.starksoftware.ssform.model.Image;

import java.util.List;

public class ShadowActivity extends Activity {

    private static final int RC_IMAGE_PICKER = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ImagePickerActivity.class);
        intent.putExtras(getIntent().getExtras());
        intent.putExtra(ImagePicker.EXTRA_MODE, ImagePicker.MODE_SINGLE);
        intent.putExtra(ImagePicker.EXTRA_LIMIT, 1);
        intent.putExtra(ImagePicker.EXTRA_SHOW_CAMERA, true);
        startActivityForResult(intent, RC_IMAGE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<Image> images = ImagePicker.getImages(data);
        RxImagePicker.getInstance().onHandleResult(images);
        finish();
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    public static Intent getStartIntent(Context context, Bundle bundle) {
        return new Intent(context, ShadowActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtras(bundle);
    }
}

