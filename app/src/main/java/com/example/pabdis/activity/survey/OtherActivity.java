package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.ui.MainActivity;
import com.example.pabdis.activity.ui.MapActivity;
import com.example.pabdis.activity.ui.OwnerActivity;
import com.example.pabdis.activity.ui.PetActivity;
import com.example.pabdis.activity.ui.ProfileActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class OtherActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnNext, compute, btnUpdate;
    FloatingActionButton skip, skip_vacc;
    String ownerid, petid, update, add;
    DatabaseHelper myDB;
    Integer pos,stat;
    EditText edtSheep,edtHorse,edtRabbit,edtDuck,edtPigeon,edtQuail,edtTurkey,edtGoose,edtOthersNum,edtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_other);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
                update = null;
                pos = null;
                stat = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");
                stat= extras.getInt("stat");
                pos= extras.getInt("position");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
            update = (String) savedInstanceState.getSerializable("update");
            pos = (Integer) savedInstanceState.getSerializable("position");
            stat= (Integer) savedInstanceState.getSerializable("stat");

        }










        myDB = new DatabaseHelper(getApplicationContext());
        btnNext = findViewById(R.id.btnProceedSurvey);
        btnUpdate = findViewById(R.id.btnUpdate);
        skip = findViewById(R.id.fab);
        skip_vacc = findViewById(R.id.fab2);
        edtSheep = findViewById(R.id.edtSheep);
        edtHorse = findViewById(R.id.edtHorse);
        edtRabbit = findViewById(R.id.edtRabbit);
        edtDuck = findViewById(R.id.edtDuck);
        edtGoose = findViewById(R.id.edtGoose);
        edtTurkey = findViewById(R.id.edtTurkey);
        edtTotal = findViewById(R.id.edtTotal);
        btnUpdate.setVisibility(View.GONE);



        Cursor rs = myDB.getOther(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0)
        {
            skip.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);




            String sheep = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_4));
            String horse = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_5));
            String rabbit = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_6));
            String duck = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_7));
            String turkey = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_8));
            String goose = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_9));
            String total_inv = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_10));


            edtSheep.setText(sheep);
            edtHorse.setText(horse);
            edtRabbit.setText(rabbit);
            edtDuck.setText(duck);
            edtGoose.setText(goose);
            edtTurkey.setText(turkey);
            edtTotal.setText(total_inv);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String sheep = edtSheep.getText().toString();
                    final String horse = edtHorse.getText().toString();
                    final String rabbit = edtRabbit.getText().toString();
                    final String duck = edtDuck.getText().toString();
                    final String goose = edtGoose.getText().toString();
                    final String turkey = edtTurkey.getText().toString();
                    final  String total = edtTotal.getText().toString();


                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(OtherActivity.this);

                    // Set a title for alert dialog
                    builder.setTitle("UPDATE.");

                    // Ask the final question
                    builder.setMessage("Are you sure you want to survey the data?");

                    // Set click listener for alert dialog buttons
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button
                                    if (sheep.equals("") || horse.equals("") || rabbit.equals("") ||
                                            duck.equals("") || turkey.equals("") ) {
                                        Toast.makeText(OtherActivity.this, "Check your input!" , Toast.LENGTH_LONG).show();


                                    }else {
                                        try {
                                            myDB.updateOther(ownerid, sheep,horse, rabbit, duck, turkey,goose ,total);
                                            Toast.makeText(OtherActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid", ownerid);
                                            intent.putExtra("petid", petid);
                                            intent.putExtra("position", pos);
                                            intent.putExtra("stat", stat);
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }
                                    }
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    // User clicked the No button
                                    break;
                            }
                        }
                    };

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Yes", dialogClickListener);

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("No",dialogClickListener);

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();


                }
            });



        }





        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), FisheryActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                intent.putExtra("stat", stat);
                startActivity(intent);



            }
        });

        skip_vacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sheep = edtSheep.getText().toString();
                final String horse = edtHorse.getText().toString();
                final String rabbit = edtRabbit.getText().toString();
                final String duck = edtDuck.getText().toString();
                final String goose = edtGoose.getText().toString();
                final String turkey = edtTurkey.getText().toString();
                final  String total = edtTotal.getText().toString();







                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(OtherActivity.this);

                // Set a title for alert dialog
                builder.setTitle("There's no going back.");

                // Ask the final question
                builder.setMessage("Are you sure you want to save the data?");

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button
                                if (sheep.equals("") || horse.equals("") || rabbit.equals("") ||
                                        duck.equals("") || turkey.equals("") ) {
                                    Toast.makeText(OtherActivity.this, "Check your input!" , Toast.LENGTH_LONG).show();


                                }else {
                                    try {
                                        myDB.addOther(ownerid, sheep,horse, rabbit, duck, turkey,goose ,total, created_at);
                                        Toast.makeText(OtherActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), FisheryActivity.class);
                                        intent.putExtra("ownerid", ownerid);
                                        intent.putExtra("petid", petid);
                                        intent.putExtra("pos", pos);
                                        intent.putExtra("stat", stat);
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();

                                    }
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // User clicked the No button
                                break;
                        }
                    }
                };

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", dialogClickListener);

                // Set the alert dialog no button click listener
                builder.setNegativeButton("No",dialogClickListener);

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();



            }
        });


    }

    @Override
    public void onBackPressed() {
        if(pos != null)
        {
            Intent i = new Intent(OtherActivity.this, ListUpdateActivity.class);
            i.putExtra("position", pos);
            i.putExtra("ownerid", ownerid);
            i.putExtra("stat", stat);
            startActivity(i);
//        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_survey) {
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_owner) {

            Intent intent=new Intent(getApplicationContext(), OwnerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_pet) {

            Intent intent=new Intent(getApplicationContext(), PetActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}