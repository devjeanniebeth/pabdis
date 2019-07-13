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
import com.example.pabdis.activity.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ChickenActivity extends AppCompatActivity {

    Button btnNext, compute;
    EditText edtBroiler,edtLayers,edtNative,edtTotal,edtProd,
            edtSF_sw_kg,edtSF_sw_hd,edtSA_sw_kg,edtSA_sw_hd,edtTotalArea,edtTotalIncome;
    String ownerid;
    DatabaseHelper myDB;
    RadioButton rbyes, rbno;
    ArrayList<String> mylist = new ArrayList<String>();
    FloatingActionButton skip;
    Spinner vacc;
    CheckBox cbncdls,cbfp,cbib,cbncdb1,cbncdcombo;
    String vaccstat, vacctype, deworm, petid;
    TextView textView, txtincome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_chicken);
        myDB = new DatabaseHelper(getApplicationContext());
        btnNext = findViewById(R.id.btnProceedSurvey);
        cbncdls = findViewById(R.id.cbncdls);
        cbfp = findViewById(R.id.cbfp);
        cbib = findViewById(R.id.cbib);
        cbncdb1 = findViewById(R.id.cbncdb1);
        cbncdcombo = findViewById(R.id.cbncdcombo);

        cbncdls.setVisibility(View.GONE);
        cbfp.setVisibility(View.GONE);
        cbib.setVisibility(View.GONE);
        cbncdb1.setVisibility(View.GONE);
        cbncdcombo.setVisibility(View.GONE);


        skip = findViewById(R.id.fab);
        edtBroiler = findViewById(R.id.edtBroiler);
        edtLayers = findViewById(R.id.edtLayers);
        edtNative = findViewById(R.id.edtNative);
        edtTotal = findViewById(R.id.edtTotal);
        edtProd = findViewById(R.id.edtProd);
        edtSF_sw_kg = findViewById(R.id.edtSF_sw_kg);
        edtSF_sw_hd = findViewById(R.id.edtSF_sw_hd);
        edtSA_sw_kg = findViewById(R.id.edtSA_sw_kg);
        edtSA_sw_hd = findViewById(R.id.edtSA_sw_hd);
        edtTotalArea = findViewById(R.id.edtTotalArea);
        edtTotalIncome = findViewById(R.id.edtTotalIncome);
        rbno = findViewById(R.id.rb2);
        rbyes = findViewById(R.id.rb1);
        textView = findViewById(R.id.textView);

        compute = findViewById(R.id.btnCompute);
        edtTotal.setEnabled(false);
        txtincome = findViewById(R.id.txtincome);
        txtincome.setText("Total Income for 2018");




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


        cbncdls.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b)
                {

                    if(!Arrays.asList(mylist).contains(cbncdls.getText().toString()))
                    {
                        mylist.add(cbncdls.getText().toString());
                    }

                }else{
                    mylist.remove(cbncdls.getText().toString());
                }

            }
        });
        cbfp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b)
                {

                    if(!Arrays.asList(mylist).contains(cbfp.getText().toString()))
                    {
                        mylist.add(cbfp.getText().toString());
                    }

                }else{
                    mylist.remove(cbfp.getText().toString());
                }

            }
        });
                cbib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                        if(b)
                        {

                            if(!Arrays.asList(mylist).contains(cbib.getText().toString()))
                            {
                                mylist.add(cbib.getText().toString());
                            }

                        }else{
                            mylist.remove(cbib.getText().toString());
                        }

                    }
                });
        cbncdb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b)
                {

                    if(!Arrays.asList(mylist).contains(cbncdb1.getText().toString()))
                    {
                        mylist.add(cbncdb1.getText().toString());
                    }

                }else{
                    mylist.remove(cbncdb1.getText().toString());
                }

            }
        });
                cbncdcombo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                        if(b)
                        {

                            if(!Arrays.asList(mylist).contains(cbncdcombo.getText().toString()))
                            {
                                mylist.add(cbncdcombo.getText().toString());
                            }

                        }else{
                            mylist.remove(cbncdcombo.getText().toString());
                        }

                    }
                });

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Integer broiler = Integer.parseInt(edtBroiler.getText().toString());
                final Integer layer = Integer.parseInt(edtLayers.getText().toString());
                final Integer snative = Integer.parseInt(edtNative.getText().toString());
                final Integer total = broiler + layer + snative ;
                edtTotal.setText(String.valueOf(total));


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ChickenActivity.this);

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
                                Intent intent = new Intent(getApplicationContext(), CattleActivity.class);
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


                final String broiler = edtBroiler.getText().toString();
                final String layer = edtLayers.getText().toString();
                final String snative = edtNative.getText().toString();
                final String total = edtTotal.getText().toString();
                final String prod = edtProd.getText().toString();
                final String ch_sf_kg = edtSF_sw_kg.getText().toString();
                final String ch_sf__hd = edtSF_sw_hd.getText().toString();
                final String ch_sa_kg = edtSA_sw_kg.getText().toString();
                final String ch_sa__hd = edtSA_sw_hd.getText().toString();
                final String ch_totala = edtTotalArea.getText().toString();
                final String ch_totali = edtTotalIncome.getText().toString();
                final String vacc = vaccstat;
                final String vacct = mylist.toString();
                final String dewormed = deworm;


                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ChickenActivity.this);

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


                                if (broiler.equals("") || layer.equals("") || snative.equals("") ||
                                        total.equals("") || prod.equals("") || ch_sf_kg.equals("") || ch_sf__hd.equals("")
                                        || ch_sa_kg.equals("") || ch_sa__hd.equals("")  || ch_totala.equals("") || ch_totali.equals("")  ) {
                                    Toast.makeText(ChickenActivity.this, "Check your input!"+vacct  , Toast.LENGTH_SHORT).show();
                                }else {

                                    try {
                                        myDB.addChicken(ownerid,broiler, layer,snative,total,prod,ch_sf_kg,ch_sf__hd,ch_sa_kg,ch_sa__hd,
                                                ch_totala,ch_totali,vacc.trim(), vacct.trim(), dewormed.trim(),created_at );
                                        Toast.makeText(ChickenActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), CattleActivity.class);
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
                textView.setVisibility(View.VISIBLE);
                cbncdls.setVisibility(View.VISIBLE);
                cbfp.setVisibility(View.VISIBLE);
                cbib.setVisibility(View.VISIBLE);
                cbncdb1.setVisibility(View.VISIBLE);
                cbncdcombo.setVisibility(View.VISIBLE);
//                vacctype = vacc.getSelectedItem().toString();
                break;
            case R.id.rb2:
                if (checked)
                    cbncdls.setVisibility(View.GONE);
                cbfp.setVisibility(View.GONE);
                cbib.setVisibility(View.GONE);
                cbncdb1.setVisibility(View.GONE);
                cbncdcombo.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                vaccstat = "2";
                mylist.add("");

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