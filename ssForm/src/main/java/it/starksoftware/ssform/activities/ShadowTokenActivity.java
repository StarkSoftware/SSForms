package it.starksoftware.ssform.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.starksoftware.ssform.features.ImagePicker;
import it.starksoftware.ssform.features.ImagePickerActivity;
import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.Image;
import it.starksoftware.ssform.model.TokesTags;
import it.starksoftware.ssform.signaturepad.SignaturePicker;
import it.starksoftware.ssform.signaturepad.SignaturePickerActivity;
import it.starksoftware.ssform.tokens.AddTokensActivity;
import it.starksoftware.ssform.tokens.TokensPicker;

public class ShadowTokenActivity extends Activity {

    private static final int RC_TOKENS_PICKER = 7715;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, AddTokensActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivityForResult(intent, RC_TOKENS_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<FormTokenObject> tokens = TokensPicker.getTokens(data);
        RxTokenPicker.getInstance().onHandleResult(tokens);
        finish();
    }

    /* --------------------------------------------------- */
    /* > Stater */
    /* --------------------------------------------------- */

    public static Intent getStartIntent(Context context, Bundle bundle) {
        return new Intent(context, ShadowTokenActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtras(bundle);
    }
}

