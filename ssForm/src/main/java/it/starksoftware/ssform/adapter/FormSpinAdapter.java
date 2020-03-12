package it.starksoftware.ssform.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.model.FormSpinnerObject;

public class FormSpinAdapter extends ArrayAdapter<FormSpinnerObject> {

    private Context context;
    private List<FormSpinnerObject> values;
    private LayoutInflater flater;

    public FormSpinAdapter(Activity context, int textViewResourceId,
                           List<FormSpinnerObject> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
        this.flater = context.getLayoutInflater();
    }

    public int getCount(){

        return values.size();
    }

    public FormSpinnerObject getItem(int position){

        return values.get(position);
    }


    int indexOfSpinner(FormSpinnerObject value)
    {
        for (int index = 0, count = this.values.size(); index < count; ++index)
        {
            if (this.values.get(index).getValue().equals(value.getValue()))
            {
                return index;
            }
        }
        return -1;
    }

    public long getItemId(int position){

        return position;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getValue());
        return label;

    }

    @Override
    public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
        @SuppressLint("InflateParams") View rowview = flater.inflate(R.layout.spinner_item,null,true);
        TextView v = rowview.findViewById(R.id.text1);
        v.setText(values.get(position).getValue());
        return rowview;
    }

}