package it.starksoftware.ssform.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.starksoftware.ssform.model.FormTokenObject;

public class TokensSearchAdapter extends ArrayAdapter<FormTokenObject>{

    private TokensAdapter adapter;
    private ArrayList<FormTokenObject> data = new ArrayList<>();
    private List<FormTokenObject> searchList = new ArrayList<>();

    public TokensSearchAdapter(Context context, TokensAdapter adapter){
        super(context,-1);
        this.adapter = adapter;
        this.data = adapter.data;
        this.searchList = cloneList(data);
    }

    public static List<FormTokenObject> cloneList(List<FormTokenObject> list) {
        List<FormTokenObject> clone = new ArrayList<FormTokenObject>(list.size());
        for (FormTokenObject item : list) clone.add(item);
        return clone;
    }


    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(searchList);
        } else {
            for (FormTokenObject s : searchList) {

                if (s.getValue().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(s);
                }
            }
        }
        notifyDataSetChanged();
    }

    public TokensAdapter getAdapter() {
        return adapter;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    results.values = adapter.data;
                    results.count = adapter.data.size();
                }else{
                    ArrayList<Object> tmp = new ArrayList<>();
                    for(int i = 0;i < adapter.data.size();i++){
                        if(adapter.data.get(i).getValue().toLowerCase(Locale.getDefault()).contains(charSequence.toString().toLowerCase(Locale.getDefault()))){
                            tmp.add(adapter.data.get(i));
                        }
                    }
                    results.values = tmp;
                    results.count = tmp.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<FormTokenObject>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int pos = adapter.data.indexOf(data.get(position));
        if(pos != -1) {
            return adapter.createSearchView(getContext(), adapter.isSelected(pos), pos);
        }else {
            return null;
        }
    }
}
