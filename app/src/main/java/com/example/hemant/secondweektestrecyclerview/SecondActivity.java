package com.example.hemant.secondweektestrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.getFragmentManager().beginTransaction().add(R.id.secFragmentHolder, new MySecondFragment(), "MY_SECOND_FRAGMENT").commit();
    }
}
