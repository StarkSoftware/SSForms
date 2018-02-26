package it.starksoftware.ssform.adapter;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import it.starksoftware.ssform.model.FormTokenObject;

public abstract class TokensAdapter {

    private FormAdapter tokensView;

    public ArrayList<FormTokenObject> data = new ArrayList<>();

    public abstract FormTokenObject getItem(int pos);

    public abstract boolean isSelected(int pos);

    public abstract View createSearchView(Context context,boolean is_checked,int pos);

    public abstract View createToken(Context context,int pos);
    public abstract View createCustomToken(Context context, final int pos, ArrayList<FormTokenObject> selectedTokens);

    public void setChipView(FormAdapter tokensView){
        this.tokensView = tokensView;
    }

    public abstract int getCount();


}
