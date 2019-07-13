package com.example.pabdis.activity.survey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FisheryActivity extends AppCompatActivity {

    Button btnNext;
    String ownerid, petid, api, fish;
    CheckBox withfishery, withapiary;
    DatabaseHelper myDB;
    FloatingActionButton skip;
    EditText edtTotalArea,edtProd,edtIncome,edtColonyNum,edtProdH,edtTotalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_survey_fishery);
        btnNext = findViewById(R.id.btnProceedSurvey);
        skip = findViewById(R.id.fab);
        edtTotalArea = findViewById(R.id.edtTotalArea);
        edtProd = findViewById(R.id.edtProd);
        edtIncome = findViewById(R.id.edtIncome);
        edtColonyNum = findViewById(R.id.edtColonyNum);
        edtProdH = findViewById(R.id.edtProdH);
        edtTotalIncome = findViewById(R.id.edtTotalIncome);

        withapiary = findViewById(R.id.withapiary);
        withfishery = findViewById(R.id.withfishery);

        edtTotalArea.setEnabled(false);
        edtProd.setEnabled(false);
        edtIncome.setEnabled(false);
        edtColonyNum.setEnabled(false);
        edtProdH.setEnabled(false);
        edtTotalIncome.setEnabled(false);
        withapiary.setChecked(false);
        withfishery.setChecked(false);
        fish = "false";
        api = "false";





        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("ownerid");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
        }


        withfishery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    fish = "true";
                    edtTotalArea.setEnabled(true);
                    edtProd.setEnabled(true);
                    edtIncome.setEnabled(true);

                }else{
                    fish = "false";
                    edtTotalArea.setEnabled(false);
                    edtProd.setEnabled(false);
                    edtIncome.setEnabled(false);

                }

            }
        });

        withapiary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    api = "true";
                    edtColonyNum.setEnabled(true);
                    edtProdH.setEnabled(true);
                    edtTotalIncome.setEnabled(true);
                }else{
                    api = "false";
                    edtColonyNum.setEnabled(false);
                    edtProdH.setEnabled(false);
                    edtTotalIncome.setEnabled(false);
                }

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FisheryActivity.this);

                // Set a title for alert dialog
                builder.setTitle("Skipping the process.");

                // Ask the final question
                builder.setMessage("Are you sure you want to skip this survey?");

                // Set click listener for alert dialog buttons
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button
                                Intent intent = new Intent(getApplicationContext(), HouseholdActivity.class);
                                intent.putExtra("ownerid",ownerid);
                                intent.putExtra("petid", petid);
                                startActivity(intent);
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String f_area = edtTotalArea.getText().toString();
                final String f_prod = edtProd.getText().toString();
                final String f_inc = edtIncome.getText().toString();
                final String a_col = edtColonyNum.getText().toString();
                final String a_prod = edtProdH.getText().toString();
                final String a_inc = edtTotalIncome.getText().toString();



                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FisheryActivity.this);

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

                                    try {

                                        if(api.equals("true") && fish.equals("false") )
                                        {
                                            if ( a_col.equals("") || a_prod.equals("") || a_inc.equals("")) {
                                                Toast.makeText(FisheryActivity.this, "Check your input!"+ api + fish , Toast.LENGTH_SHORT).show();
                                            }else {
                                                myDB.addApiary(ownerid, a_col, a_prod, a_inc, created_at);
                                                Toast.makeText(FisheryActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(), HouseholdActivity.class);
                                                intent.putExtra("ownerid", ownerid);
                                                intent.putExtra("petid", petid);
                                                startActivity(intent);
                                            }
                                        }else if(fish.equals("true") && api.equals("false") )
                                        {
                                            if ( (f_area.equals("") ||  f_prod.equals("") ||  f_inc.equals(""))) {
                                                Toast.makeText(FisheryActivity.this, "Check your input!"+ api + fish , Toast.LENGTH_SHORT).show();
                                            }else {
                                                myDB.addFishery(ownerid, f_area, f_prod, f_inc, created_at);
                                                Toast.makeText(FisheryActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(), HouseholdActivity.class);
                                                intent.putExtra("ownerid", ownerid);
                                                intent.putExtra("petid", petid);
                                                startActivity(intent);
                                            }
                                        }else if(fish.equals("true") && api.equals("true"))
                                        {

                                            if ( f_area.equals("") ||  f_prod.equals("") ||  f_inc.equals("") ||  a_col.equals("") || a_prod.equals("") || a_inc.equals("")) {
                                                Toast.makeText(FisheryActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();
                                            }else {
                                                myDB.addApiary(ownerid, a_col, a_prod, a_inc, created_at);
                                                myDB.addFishery(ownerid, f_area, f_prod, f_inc, created_at);
                                                Toast.makeText(FisheryActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(getApplicationContext(), HouseholdActivity.class);
                                                intent.putExtra("ownerid", ownerid);
                                                intent.putExtra("petid", petid);
                                                startActivity(intent);
                                            }


                                        }





                                    } catch (Exception e) {
                                        e.printStackTrace();

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
