package it.starksoftware.ssform.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.model.FormTokenObject;

public class TokensChipAdapter extends TokensAdapter{

    ArrayList<FormTokenObject> search_data = new ArrayList<>();
    ArrayList<FormTokenObject> tokens = new ArrayList<>();

    public TokensChipAdapter(ArrayList<FormTokenObject>search_data){
        this.search_data = search_data;
        this.data = search_data;
    }

    @Override
    public FormTokenObject getItem(int pos) {
        return search_data.get(pos);
    }

    @Override
    public boolean isSelected(int pos) {
        if(tokens.contains(search_data.get(pos))) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public View createSearchView(Context context, boolean is_checked, final int pos) {
        View view = View.inflate(context, R.layout.tokens_search,null);
        CheckBox cbCheck = view.findViewById(R.id.cbCheck);
        cbCheck.setText((String)search_data.get(pos).getValue());
        cbCheck.setChecked(is_checked);
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    tokens.add(search_data.get(pos));
                    //refresh();
                }else{
                    tokens.remove(search_data.get(pos));
                    //refresh();
                }
            }
        });
        return view;
    }

    @Override
    public View createToken(Context context, final int pos) {
        View view = View.inflate(context,R.layout.token_item,null);
        TextView tvChip = view.findViewById(R.id.tvChip);
        tvChip.setText((String)search_data.get(pos).getValue());
        ImageView ivClose = view.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tokens.remove(search_data.get(pos));
                //refresh();
            }
        });
        return view;
    }

    @Override
    public View createCustomToken(Context context, final int pos, ArrayList<FormTokenObject> selectedTokens) {
        View view = View.inflate(context,R.layout.token_item,null);
        TextView tvChip = view.findViewById(R.id.tvChip);
        tvChip.setText(selectedTokens.get(pos).getValue());
        ImageView ivClose = view.findViewById(R.id.ivClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tokens.remove(search_data.get(pos));
                //refresh();
            }
        });
        return view;
    }

    public ArrayList<FormTokenObject> getTokens() {
        return tokens;
    }

    @Override
    public int getCount() {
        return search_data.size();
    }

}
