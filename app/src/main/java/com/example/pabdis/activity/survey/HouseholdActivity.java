package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.Owner;
import com.example.pabdis.activity.ui.MainActivity;
import com.example.pabdis.activity.ui.OwnerActivity;
import com.example.pabdis.activity.ui.VaccinationActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HouseholdActivity extends AppCompatActivity {


    Button btnDone,btnUpdate;
    String ownerid, petid, update;
    FloatingActionButton skip;
    DatabaseHelper myDB;
    EditText edtBeef,edtCarabeef,edtPork,edtChicken,edtFish,edtEgg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_survey_household);
        btnDone = findViewById(R.id.btnProceedSurvey);
        btnUpdate = findViewById(R.id.btnUpdate);
        skip = findViewById(R.id.fab);
        edtBeef = findViewById(R.id.edtBeef);
        edtCarabeef = findViewById(R.id.edtCarabeef);
        edtPork = findViewById(R.id.edtPork);
        edtChicken = findViewById(R.id.edtChicken);
        edtFish = findViewById(R.id.edtFish);
        edtEgg = findViewById(R.id.edtEgg);
        btnUpdate.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");

            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
        }



        Cursor rs = myDB.getHousehold(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0)
        {
            skip.setVisibility(View.GONE);
            btnDone.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);



            String beef = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_4));
            String carabeef = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_5));
            String pork = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_6));
            String chicken = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_7));
            String fish = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_8));
            String egg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_9));

            edtBeef.setText(beef);
            edtCarabeef.setText(carabeef);
            edtPork.setText(pork);
            edtChicken.setText(chicken);
            edtFish.setText(fish);
            edtEgg.setText(egg);





            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String beef = edtBeef.getText().toString();
                    final String carabeef = edtCarabeef.getText().toString();
                    final String pork = edtPork.getText().toString();
                    final String chicken = edtChicken.getText().toString();
                    final String fish = edtFish.getText().toString();
                    final String egg = edtEgg.getText().toString();



                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(HouseholdActivity.this);

                    // Set a title for alert dialog
                    builder.setTitle("UPDATE.");

                    // Ask the final question
                    builder.setMessage("Do you want to update the data?");

                    // Set click listener for alert dialog buttons
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button

                                    if (beef.equals("") || carabeef.equals("") || pork.equals("") ||
                                            chicken.equals("") || fish.equals("") || egg.equals("")) {
                                        Toast.makeText(HouseholdActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();
                                    }else {
                                        try {
                                            myDB.updateHousehold(ownerid, beef, carabeef, pork,chicken,fish,egg);
                                            Toast.makeText(HouseholdActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid", ownerid);
                                            intent.putExtra("petid", petid);
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }
                                    }

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    // User clicked the No button


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

                Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                startActivity(intent);



            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String beef = edtBeef.getText().toString();
                final String carabeef = edtCarabeef.getText().toString();
                final String pork = edtPork.getText().toString();
                final String chicken = edtChicken.getText().toString();
                final String fish = edtFish.getText().toString();
                final String egg = edtEgg.getText().toString();



                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(HouseholdActivity.this);

                // Set a title for alert dialog
                builder.setTitle("Almost done.");

                // Ask the final question
                builder.setMessage("Do you have pets to be vaccinated?");

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button

                                if (beef.equals("") || carabeef.equals("") || pork.equals("") ||
                                        chicken.equals("") || fish.equals("") || egg.equals("")) {
                                    Toast.makeText(HouseholdActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();
                                }else {
                                    try {
                                        myDB.addHousehold(ownerid, beef, carabeef, pork,chicken,fish,egg,created_at);
                                        Toast.makeText(HouseholdActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                                        intent.putExtra("ownerid", ownerid);
                                        intent.putExtra("petid", petid);
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();

                                    }
                                }

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                // User clicked the No button

                                if (beef.equals("") || carabeef.equals("") || pork.equals("") ||
                                        chicken.equals("") || fish.equals("") || egg.equals("")) {
                                    Toast.makeText(HouseholdActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();
                                }else {
                                    try {
                                        myDB.addHousehold(ownerid, beef, carabeef, pork,chicken,fish,egg,created_at);
                                        Toast.makeText(HouseholdActivity.this, "Success survey!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), OwnerActivity.class);
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();

                                    }
                                }
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