package com.example.hemant.secondweektestrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;

public class MyPersonAdapter extends RecyclerView.Adapter<MyPersonAdapter.GenericViewHolder> {

    List<Person> listOfPersons;
    Context context;

    public MyPersonAdapter(List<Person> listOfPersons, Context context) {
        this.context = context;
        this.listOfPersons = listOfPersons;
    }

    // Create MyViewHolder class extending RecyclerView.ViewHolder
    public class MyViewHolder extends GenericViewHolder {

        // Add reference of the layout controls
        private TextView personNameTxtView;
        private ImageView personImageImgView;

        // Find the views in the constructor using findViewById
        public MyViewHolder(View view) {
            super(view);
            personNameTxtView = (TextView) view.findViewById(R.id.personNameTxtView);
            personImageImgView = (ImageView) view.findViewById(R.id.personImageImgView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, SecondActivity.class);
                    intent.putExtra("name", personNameTxtView.getText());

                    personImageImgView.buildDrawingCache();
                    Bitmap bitmap = personImageImgView.getDrawingCache();

                    intent.putExtra("image", bitmap);

                    context.startActivity(intent);
                }
            });
        }
    }

    // Create another view holder
    public class MySecondViewHolder extends GenericViewHolder {

        ImageView personImageImgView2nd;
        TextView personNameTxtView2nd;

        public MySecondViewHolder(View itemView) {
            super(itemView);
            personImageImgView2nd = (ImageView) itemView.findViewById(R.id.personImageImgView2nd);
            personNameTxtView2nd = (TextView) itemView.findViewById(R.id.personNameTxtView2nd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SecondActivity.class);
                    intent.putExtra("name", personNameTxtView2nd.getText());

                    personImageImgView2nd.buildDrawingCache();
                    Bitmap bitmap = personImageImgView2nd.getDrawingCache();

                    intent.putExtra("image", bitmap);

                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_single, parent, false);
            return new MyViewHolder(view);
        } else if (viewType == 2) {
            View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_single_second, parent, false);
            return new MySecondViewHolder(view2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 1:
                ((MyViewHolder) holder).personNameTxtView.setText(listOfPersons.get(position).getName());
                ((MyViewHolder) holder).personImageImgView.setImageResource(someRandomImage());
                break;

            case 2:
                ((MySecondViewHolder) holder).personNameTxtView2nd.setText(listOfPersons.get(position).getName());
                ((MySecondViewHolder) holder).personImageImgView2nd.setImageResource(someRandomImage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listOfPersons.size();
    }

    /**
     * The generic view holder
     */
    public class GenericViewHolder extends RecyclerView.ViewHolder {

        public GenericViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * Convinence method which return a random drawble from a set of images
     */

    public static int someRandomImage() {
        int[] photos = {
                android.R.drawable.ic_lock_idle_alarm,
                android.R.drawable.sym_action_chat,
                android.R.drawable.ic_menu_save,
                android.R.drawable.sym_contact_card,
                android.R.drawable.ic_btn_speak_now,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_menu_camera,
                android.R.drawable.ic_media_next,
                android.R.drawable.ic_menu_send,
                android.R.drawable.ic_menu_gallery
        };

        Random ran = new Random();
        int i = ran.nextInt(photos.length);
        return photos[i];
    }
}
