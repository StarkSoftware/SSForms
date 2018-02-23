package it.starksoftware.ssform.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.starksoftware.ssform.R;


public class ImageCardAdapter extends RecyclerView.Adapter<ImageCardAdapter.MyViewHolder> {

    private Context mContext;
    private List<Bitmap> imagesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public ImageCardAdapter(Context mContext, List<Bitmap> albumList) {
        this.mContext = mContext;
        this.imagesList = albumList;
    }

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

                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Gestione Immagini")
                        .setContentText("Vuoi eliminare l'immagine selezionata ?")
                        .setConfirmText("Elimina")
                        .setCancelText("Annulla")
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                imagesList.remove(position);
                                notifyDataSetChanged();
                                sDialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }
}