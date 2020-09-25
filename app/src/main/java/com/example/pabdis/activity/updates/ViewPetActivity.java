package com.example.pabdis.activity.updates;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;

public class ViewPetActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petview);
        myDB = new DatabaseHelper(getApplicationContext());





        }

}
