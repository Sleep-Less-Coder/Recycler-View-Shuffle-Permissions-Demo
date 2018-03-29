package com.example.hemant.secondweektestrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getFragmentManager().beginTransaction().add(R.id.fragmentHolder, new MyFragment(), "MY_FRAGMENT").commit();
    }
}
