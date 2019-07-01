package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class OtherActivity extends AppCompatActivity {

    Button btnNext;
    String ownerid;
    DatabaseHelper myDB;

    ArrayList<String> n;





    EditText edtSheep,edtHorse,edtRabbit,edtDuck,edtPigeon,edtQuail,edtTurkey,edtOthersName,edtOthersNum,edtTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_other);
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
        myDB = new DatabaseHelper(getApplicationContext());
        btnNext = findViewById(R.id.btnProceedSurvey);
        edtSheep = findViewById(R.id.edtSheep);
        edtHorse = findViewById(R.id.edtHorse);
        edtRabbit = findViewById(R.id.edtRabbit);
        edtDuck = findViewById(R.id.edtDuck);
        edtPigeon = findViewById(R.id.edtPigeon);
        edtQuail = findViewById(R.id.edtQuail);
        edtTurkey = findViewById(R.id.edtTurkey);
        edtOthersName = findViewById(R.id.edtOthersName);
        edtOthersNum = findViewById(R.id.edtOthersNum);
        edtTotal = findViewById(R.id.edtTotal);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String sheep = edtSheep.getText().toString();
                final String horse = edtHorse.getText().toString();
                final String rabbit = edtRabbit.getText().toString();
                final String duck = edtDuck.getText().toString();
                final String pigeon = edtPigeon.getText().toString();
                final String quail = edtQuail.getText().toString();
                final String turkey = edtTurkey.getText().toString();
                final String othern = edtOthersName.getText().toString();
                final String othernum = edtOthersNum.getText().toString();
                final  String total = edtTotal.getText().toString();



                n.add(othern);
                n.add(othernum);



                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
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
                                        duck.equals("") || pigeon.equals("") || quail.equals("") ||
                                        turkey.equals("") ) {
                                    Toast.makeText(OtherActivity.this, ""+n , Toast.LENGTH_SHORT).show();

                                }else {
                                    try {
                                        myDB.addOther(ownerid, sheep, horse, rabbit, duck, pigeon, quail, turkey,othern,total, created_at);
                                        Intent intent = new Intent(getApplicationContext(), FisheryActivity.class);
                                        intent.putExtra("owner_id", ownerid);
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
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }
}