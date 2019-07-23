package com.example.pabdis.activity.updates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.survey.SwineActivity;
import com.example.pabdis.activity.ui.OwnerActivity;

public class ListUpdateActivity extends AppCompatActivity {


    Button btnSwine,btnChicken,btnCattle,btnCarabao,btnGoat,btnOther,btnFishery,btnWeekly;
    String ownerid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        btnSwine = findViewById(R.id.btnSwine);
        btnChicken = findViewById(R.id.btnChicken);
        btnCattle = findViewById(R.id.btnCattle);
        btnCarabao = findViewById(R.id.btnCarabao);
        btnGoat = findViewById(R.id.btnGoat);
        btnOther = findViewById(R.id.btnOther);
        btnFishery = findViewById(R.id.btnFishery);
        btnWeekly = findViewById(R.id.btnWeekly);



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





        btnSwine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, SwineActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                startActivity(i);


            }
        });

        btnChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });
        btnCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });
        btnCarabao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });
        btnGoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });

        btnFishery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });

        btnWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "" + ownerid, Toast.LENGTH_SHORT).show();


            }
        });

    }



}
