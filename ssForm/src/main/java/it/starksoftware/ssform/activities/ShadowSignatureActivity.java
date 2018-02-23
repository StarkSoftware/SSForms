package it.starksoftware.ssform.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import it.starksoftware.ssform.signaturepad.SignaturePicker;
import it.starksoftware.ssform.signaturepad.SignaturePickerActivity;

public class ShadowSignatureActivity extends Activity {

    private static final int RC_SIGANTURE_PICKER = 7785;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SignaturePickerActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivityForResult(intent, RC_SIGANTURE_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap images = SignaturePicker.getImages(data);
        RxSignaturePicker.getInstance().onHandleResult(images);
        finish();
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    public static Intent getStartIntent(Context context, Bundle bundle) {
        return new Intent(context, ShadowSignatureActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtras(bundle);
    }
}

