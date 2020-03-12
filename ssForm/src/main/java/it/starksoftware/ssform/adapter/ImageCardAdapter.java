package it.starksoftware.ssform.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import it.starksoftware.ssform.R;


public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Bitmap> imagesList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }


    ImageCardAdapter(Context mContext, List<Bitmap> albumList) {
        this.mContext = mContext;
        this.imagesList = albumList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_image_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Bitmap bmp = imagesList.get(position);
        holder.thumbnail.setImageBitmap(bmp);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Gestione Immagini")
                        .setMessage("Vuoi eliminare l'immagine selezionata ?")
                        .setCancelable(false)
                        .setPositiveButton("Elimina", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                imagesList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }
}