package com.example.hemant.secondweektestrecyclerview;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyFragment extends Fragment {

    private List<Person> personsList = new ArrayList<>();
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager myLayoutManager;
    private MyPersonAdapter myPersonAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.my_fragment_view, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout);

        // Initialize the adapter with a list which is empty at this point
        myPersonAdapter = new MyPersonAdapter(personsList, getContext());

        // RecyclerView needs a layout manager : Set the layout manager
        myLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(myLayoutManager);

        // Adding the basic animations like ripple effect on tap provided by the system
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Adding the divider line between items
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        // Setting the adapter to the RV
        recyclerView.setAdapter(myPersonAdapter);

        prepareListData();

        setHasOptionsMenu(true);

        return view;
    }

    // Method to add some hard coded list of movies
    protected void prepareListData() {
        Person person = new Person("Hemant Acharya");
        personsList.add(person);

        person = new Person("John Doe");
        personsList.add(person);

        person = new Person("Marry Johnson");
        personsList.add(person);

        person = new Person("Travis Neilson");
        personsList.add(person);

        person = new Person("Scott Dickson");
        personsList.add(person);

        myPersonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.shuffle:

                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                    Collections.shuffle(personsList);

                    myPersonAdapter = new MyPersonAdapter(personsList, getContext());
                    recyclerView.swapAdapter(myPersonAdapter, true);
                    Log.i("INFO", "EXECUTED");
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);
                }
                return true;

            default:
                return false;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Collections.shuffle(personsList);

            myPersonAdapter = new MyPersonAdapter(personsList, getContext());
            recyclerView.swapAdapter(myPersonAdapter, true);

        } else {

            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Camera permission is required!", Snackbar.LENGTH_LONG)
                        .setAction("Close", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                snackbar.show();

            } else {
                Toast.makeText(getContext(), "Never ask again was selected.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
