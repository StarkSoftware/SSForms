package it.starksoftware.ssform.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import it.starksoftware.ssform.R;

public class FormAttachAdapter extends RecyclerView.Adapter<FormAttachAdapter.MyViewHolder> {

    private ArrayList<String> dataList;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cell_filename;
        public ImageView cell_image;


        public MyViewHolder(View view) {
            super(view);
            cell_filename = (TextView) view.findViewById(R.id.cell_filename);
            cell_image = (ImageView) view.findViewById(R.id.cell_image);
        }
    }


    public FormAttachAdapter(ArrayList<String> data, Context ctx) {
        this.dataList = data;
        this.ctx = ctx;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attach_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String item = dataList.get(position);
        try {
            File file = new File(item.toString());
            holder.cell_filename.setText(file.getName());
        } catch (Exception ex) {

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}