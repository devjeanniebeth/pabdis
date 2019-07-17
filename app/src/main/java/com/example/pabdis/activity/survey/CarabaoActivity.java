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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CarabaoActivity extends AppCompatActivity {

    Button btnNext, compute;
    String ownerid,vaccstat, vacctype, deworm, petid;
    DatabaseHelper myDB;
    EditText edtCarabullC,edtCarabullN,edtCaracowC,edtCaracowN,edtCaracalfC,edtCaracalfN, edtTotal,
            edtSF_sw_kg,edtSF_sw_hd,edtSA_sw_kg,edtSA_sw_hd,edtSwineTotalArea,edtSwineTotalIncome;
    RadioButton rbyes, rbno;
    Spinner vacc;
    CheckBox cbbl;
    ArrayList<String> mylist = new ArrayList<String>();
    FloatingActionButton skip;
    TextView textView, txtincome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_carabao);
        myDB = new DatabaseHelper(getApplicationContext());
        skip = findViewById(R.id.fab);
        cbbl = findViewById(R.id.cbbl);
        cbbl.setVisibility(View.GONE);
        edtTotal = findViewById(R.id.edtTotal);
        compute = findViewById(R.id.btnCompute);
        edtCarabullC = findViewById(R.id.edtCarabullC);
        edtCarabullN = findViewById(R.id.edtCarabullN);
        edtCaracowC = findViewById(R.id.edtCaracowC);
        edtCaracowN = findViewById(R.id.edtCaracowN);
        edtCaracalfC = findViewById(R.id.edtCaracalfC);
        edtCaracalfN = findViewById(R.id.edtCaracalfN);
        edtSF_sw_kg = findViewById(R.id.edtSF_sw_kg);
        edtSF_sw_hd = findViewById(R.id.edtSF_sw_hd);
        edtSA_sw_kg = findViewById(R.id.edtSA_sw_kg);
        edtSA_sw_hd = findViewById(R.id.edtSA_sw_hd);
        edtSwineTotalArea = findViewById(R.id.edtSwineTotalArea);
        edtSwineTotalIncome = findViewById(R.id.edtSwineTotalIncome);
        btnNext = findViewById(R.id.btnProceedSurvey);
        rbno = findViewById(R.id.rb2);
        rbyes = findViewById(R.id.rb1);
        textView = findViewById(R.id.textView);
        txtincome = findViewById(R.id.txtincome);
        txtincome.setText("Total Income for 2018");
        edtTotal.setEnabled(false);



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


        cbbl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    if(!Arrays.asList(mylist).contains(cbbl.getText().toString()))
                    {
                        mylist.add(cbbl.getText().toString());
                    }
                }else{
                    mylist.remove(cbbl.getText().toString());
                }
            }
        });



        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Integer boarn = Integer.parseInt(edtCarabullC.getText().toString());
                final Integer boaru = Integer.parseInt(edtCarabullN.getText().toString());
                final Integer grown = Integer.parseInt(edtCaracowC.getText().toString());
                final Integer growu = Integer.parseInt(edtCaracowN.getText().toString());
                final Integer sown = Integer.parseInt(edtCaracalfC.getText().toString());
                final Integer sowu = Integer.parseInt(edtCaracalfN.getText().toString());


                if(edtCarabullC.equals("") || edtCarabullN.equals("") || edtCaracowC.equals("") || edtCaracowN.equals("")
                        || edtCaracalfC.equals("") || edtCaracalfN.equals("") )
                {

                    Toast.makeText(CarabaoActivity.this, "Empty input!" + ownerid , Toast.LENGTH_SHORT).show();



                }else {



                    final Integer total = boarn + boaru + grown + growu +  sown + sowu ;
                    edtTotal.setText(String.valueOf(total));
                }




            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CarabaoActivity.this);

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
                                Intent intent = new Intent(getApplicationContext(), GoatActivity.class);
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


                final String carabullc = edtCarabullC.getText().toString();
                final String carabulln = edtCarabullN.getText().toString();
                final String caracowc = edtCaracowC.getText().toString();
                final String caracown = edtCaracowN.getText().toString();
                final String caracalfc = edtCaracalfC.getText().toString();
                final String caracalfn = edtCaracalfN.getText().toString();
                final String total = edtTotal.getText().toString();
                final String car_sf_kg = edtSF_sw_kg.getText().toString();
                final String car_sf_hd = edtSF_sw_hd.getText().toString();
                final String car_sa_kg = edtSA_sw_kg.getText().toString();
                final String car_sa_hd = edtSA_sw_hd.getText().toString();
                final String car_totala = edtSwineTotalArea.getText().toString();
                final String car_totali = edtSwineTotalIncome.getText().toString();

                final String vacc = vaccstat;
                final String vacct = mylist.toString();
                final String dewormed = deworm;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CarabaoActivity.this);

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




                                // User clicked the Yes button
                                if (carabullc.equals("") || carabulln.equals("") || caracowc.equals("") ||
                                        caracown.equals("") || caracalfc.equals("") || caracalfn.equals("") ||
                                        car_sf_kg.equals("") || car_sf_hd.equals("") || car_sa_kg.equals("") ||
                                        car_sa_hd.equals("") || car_totala.equals("") || car_totali.equals("") ||  vacc.equals("") || vacct.equals("") || dewormed.equals("")) {
                                    Toast.makeText(CarabaoActivity.this, "Check your input!" , Toast.LENGTH_SHORT).show();

                                }else {
                                    try {
                                        myDB.addCarabao(ownerid,carabullc, carabulln,caracowc,caracown,caracalfc,caracalfn,total,car_sf_kg,car_sf_hd,car_sa_kg,car_sa_hd,
                                                car_totala,car_totali ,vacc.trim(), vacct.trim(),dewormed.trim(), created_at );
                                        Toast.makeText(CarabaoActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), GoatActivity.class);
                                        intent.putExtra("ownerid",ownerid);
                                        intent.putExtra("petid", petid);
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb1:
                if (checked)
                    // Pirates are the best
                vaccstat = "1";
                cbbl.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                break;
            case R.id.rb2:
                if (checked)
                textView.setVisibility(View.GONE);
                cbbl.setVisibility(View.GONE);
                mylist.add("");
                vaccstat = "2";
                vacctype = "";
                // Ninjas rule
                break;
        }
    }

    public void onRadioButtonClicked2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rby:
                if (checked)
                    // Pirates are the best
                    deworm = "1";
                break;
            case R.id.rbn:
                if (checked)
                    deworm = "2";
                // Ninjas rule
                break;
        }
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
