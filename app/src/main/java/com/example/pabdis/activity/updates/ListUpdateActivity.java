package com.example.pabdis.activity.updates;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.Owner;
import com.example.pabdis.activity.survey.CarabaoActivity;
import com.example.pabdis.activity.survey.CattleActivity;
import com.example.pabdis.activity.survey.ChickenActivity;
import com.example.pabdis.activity.survey.FisheryActivity;
import com.example.pabdis.activity.survey.GoatActivity;
import com.example.pabdis.activity.survey.HouseholdActivity;
import com.example.pabdis.activity.survey.OtherActivity;
import com.example.pabdis.activity.survey.SwineActivity;
import com.example.pabdis.activity.ui.MainActivity;
import com.example.pabdis.activity.ui.OwnerActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;

public class ListUpdateActivity extends AppCompatActivity {


    Button btnOwner,btnSwine,btnChicken,btnCattle,btnCarabao,btnGoat,btnOther,btnFishery,btnApiary,btnWeekly, btnPet;
    FloatingActionButton back;
    String ownerid, update;
    Integer position;
    DatabaseHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        myDB = new DatabaseHelper(getApplicationContext());
        btnOwner = findViewById(R.id.btnOwner);
        btnSwine = findViewById(R.id.btnSwine);
        btnChicken = findViewById(R.id.btnChicken);
        btnCattle = findViewById(R.id.btnCattle);
        btnCarabao = findViewById(R.id.btnCarabao);
        btnGoat = findViewById(R.id.btnGoat);
        btnOther = findViewById(R.id.btnOther);
        btnFishery = findViewById(R.id.btnFishery);
        btnApiary = findViewById(R.id.btnApiary);
        btnWeekly = findViewById(R.id.btnWeekly);
        btnPet = findViewById(R.id.btnPet);
        back = findViewById(R.id.fab);
        btnFishery.setText("Fishery");
        btnApiary.setText("Apiary");


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                position = null;
            } else {
                ownerid= extras.getString("ownerid");
                position= extras.getInt("position");

            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            position= (Integer) savedInstanceState.getSerializable("position");


        }

        Cursor rs = myDB.getUserInfo(ownerid);

        rs.moveToFirst();

        if(rs.getCount() > 0 )
        {
            String ownertype = rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_2));



          if(ownertype.equals("Establishment"))
            {
                btnSwine.setVisibility(View.GONE);
                btnChicken.setVisibility(View.GONE);
                btnCattle.setVisibility(View.GONE);
                btnCarabao.setVisibility(View.GONE);
                btnGoat.setVisibility(View.GONE);
                btnOther.setVisibility(View.GONE);
                btnFishery.setVisibility(View.GONE);
                btnApiary.setVisibility(View.GONE);
                btnWeekly.setVisibility(View.GONE);



            }



        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(ListUpdateActivity.this, OwnerActivity.class);
                i.putExtra("pos", position);
                startActivity(i);

            }
        });



        btnOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, MainActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);


            }
        });


        btnPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ListUpdateActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();

                update = "add";
                Intent i = new Intent(ListUpdateActivity.this, VaccinationActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("add", "true");
                i.putExtra("position", position);
                i.putExtra("add", update);



                startActivity(i);


            }
        });



        btnSwine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, SwineActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);


            }
        });

        btnChicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ListUpdateActivity.this, ChickenActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });
        btnCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, CattleActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });
        btnCarabao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, CarabaoActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });
        btnGoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, GoatActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });
        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ListUpdateActivity.this, OtherActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });

        btnApiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ListUpdateActivity.this, ApiaryActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("position", position);
                startActivity(i);

            }
        });

        btnFishery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ListUpdateActivity.this, FisheryActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });

        btnWeekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ListUpdateActivity.this, HouseholdActivity.class);
                i.putExtra("ownerid", ownerid);
                i.putExtra("update", "true");
                i.putExtra("position", position);

                startActivity(i);

            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent(ListUpdateActivity.this, OwnerActivity.class);
        i.putExtra("pos", position);
        i.putExtra("ownerid", ownerid);
        startActivity(i);
//        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }



}
