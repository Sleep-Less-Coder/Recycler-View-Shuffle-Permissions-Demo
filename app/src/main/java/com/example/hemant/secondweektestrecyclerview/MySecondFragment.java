package com.example.hemant.secondweektestrecyclerview;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MySecondFragment extends Fragment {

    TextView personNameTxtView;
    ImageView personImageImgView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_second_fragment_view, container, false);

        personNameTxtView = (TextView) view.findViewById(R.id.nameOfPerson);
        personImageImgView = (ImageView) view.findViewById(R.id.imageOfPerson);

        Intent intent = getActivity().getIntent();
        personNameTxtView.setText(intent.getStringExtra("name"));

        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("image");
        personImageImgView.setImageBitmap(bitmap);

        return view;
    }
}
