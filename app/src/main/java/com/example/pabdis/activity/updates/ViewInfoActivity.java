package com.example.pabdis.activity.updates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pabdis.R;

public class ViewInfoActivity extends AppCompatActivity {


    String ownerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
            } else {
                ownerid= extras.getString("ownerid");

            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
        }



    }




}
