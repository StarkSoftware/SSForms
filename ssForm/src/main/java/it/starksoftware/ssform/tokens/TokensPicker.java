package it.starksoftware.ssform.tokens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.Image;
import it.starksoftware.ssform.signaturepad.SignaturePickerConfig;

public abstract class TokensPicker {


    private SignaturePickerConfig config;

    public abstract void start(int requestCode);

    public static class TokensPickerWithActivity extends TokensPicker {

        private Activity activity;

        public TokensPickerWithActivity(Activity activity) {
            this.activity = activity;
            init(activity);
        }

        @Override
        public void start(int requestCode) {
            Intent intent = getIntent(activity);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static class TokensPickerWithFragment extends TokensPicker {

        private Fragment fragment;

        public TokensPickerWithFragment(Fragment fragment) {
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

    public static TokensPickerWithActivity create(Activity activity) {
        return new TokensPickerWithActivity(activity);
    }

    public static TokensPickerWithFragment create(Fragment fragment) {
        return new TokensPickerWithFragment(fragment);
    }

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    public TokensPicker returnAfterFirst(boolean returnAfterFirst) {
        config.setReturnAfterFirst(returnAfterFirst);
        return this;
    }

    public TokensPicker limit(int count) {
        config.setLimit(count);
        return this;
    }

    public TokensPicker showCamera(boolean show) {
        config.setShowCamera(show);
        return this;
    }

    public TokensPicker folderTitle(String title) {
        config.setFolderTitle(title);
        return this;
    }

    public TokensPicker imageTitle(String title) {
        config.setImageTitle(title);
        return this;
    }

    public TokensPicker origin(ArrayList<Image> images) {
        config.setSelectedImages(images);
        return this;
    }

    public TokensPicker folderMode(boolean folderMode) {
        config.setFolderMode(folderMode);
        return this;
    }

    public TokensPicker imageDirectory(String directory) {
        config.setImageDirectory(directory);
        return this;
    }

    public TokensPicker theme(@StyleRes int theme) {
        config.setTheme(theme);
        return this;
    }

    public Intent getIntent(Context context) {
        Intent intent = new Intent(context, AddTokensActivity.class);
        intent.putExtra(AddTokensActivity.class.getSimpleName(), config);
        return intent;
    }

    /* --------------------------------------------------- */
    /* > Helper */
    /* --------------------------------------------------- */

    public static ArrayList<FormTokenObject> getTokens(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (ArrayList<FormTokenObject>) intent.getExtras().get("TOKENS");
    }
}
