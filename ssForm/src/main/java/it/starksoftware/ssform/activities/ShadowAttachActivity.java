package it.starksoftware.ssform.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import it.starksoftware.ssform.attach.AttachPicker;
import it.starksoftware.ssform.attach.AttachPickerActivity;
import it.starksoftware.ssform.features.ImagePicker;

import java.util.ArrayList;

public class ShadowAttachActivity extends Activity {

    private static final int RC_IMAGE_PICKER = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, AttachPickerActivity.class);
        intent.putExtras(getIntent().getExtras());
        intent.putExtra(ImagePicker.EXTRA_MODE, AttachPicker.MODE_SINGLE);
        intent.putExtra(ImagePicker.EXTRA_LIMIT, 1);
        intent.putExtra(ImagePicker.EXTRA_SHOW_CAMERA, true);
        startActivityForResult(intent, RC_IMAGE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> attach = AttachPicker.getAttach(data);
        RxAttachPicker.getInstance().onHandleResult(attach);
        finish();
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    public static Intent getStartIntent(Context context, Bundle bundle) {
        return new Intent(context, ShadowAttachActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtras(bundle);
    }
}

