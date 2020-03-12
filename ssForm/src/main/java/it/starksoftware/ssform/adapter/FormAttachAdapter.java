package it.starksoftware.ssform.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

import it.starksoftware.ssform.R;

public class FormAttachAdapter extends RecyclerView.Adapter<FormAttachAdapter.MyViewHolder> {

    private ArrayList<String> dataList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cell_filename;
        ImageView cell_image;


        MyViewHolder(View view) {
            super(view);
            cell_filename = view.findViewById(R.id.cell_filename);
            cell_image = view.findViewById(R.id.cell_image);
        }
    }


    public FormAttachAdapter(ArrayList<String> data) {
        this.dataList = data;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attach_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        String item = dataList.get(position);
        try {
            File file = new File(item);
            holder.cell_filename.setText(file.getName());
        } catch (Exception ignored) {

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}