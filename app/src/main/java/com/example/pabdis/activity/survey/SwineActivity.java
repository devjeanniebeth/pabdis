package com.example.pabdis.activity.survey;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


public class SwineActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    Button btnNext, compute, btnUpdate;
    FloatingActionButton skip, skip_vacc;
    ArrayList<String> mylist = new ArrayList<String>();
    ArrayList<String> mylist2 = new ArrayList<String>();

    ArrayList<String> mylistup = new ArrayList<String>();
    EditText edtboarn, edtboaru, edtGrowN, edtGrowU, edtSowN, edtSowU, edtPigN,edtPigU,
            edtSwineTotal,edtSF_sw_kg,edtSF_sw_hd,edtSA_sw_kg,edtSA_sw_hd,edtSwineTotalArea,edtSwineTotalIncome;
    String ownerid, petid, update;
    CheckBox cbhog, cbpcv2, cbmyco;
    DatabaseHelper myDB;
    RadioButton rbyes, rbno, rby, rbn;
    Spinner vacc;
    String vaccstat, deworm;
    Integer pos;
    TextView textView, txtincome;
    final Calendar myCalendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_swine);
        myDB = new DatabaseHelper(getApplicationContext());
        btnNext = findViewById(R.id.btnProceedSurvey);
        btnUpdate = findViewById(R.id.btnUpdate);
        skip = findViewById(R.id.fab);
        skip_vacc = findViewById(R.id.fab2);
        cbhog = findViewById(R.id.cbhog);
        cbpcv2 = findViewById(R.id.cbpcv2);
        cbmyco = findViewById(R.id.cbmyco);
        cbpcv2.setVisibility(View.GONE);
        cbhog.setVisibility(View.GONE);
        cbmyco.setVisibility(View.GONE);
        compute = findViewById(R.id.btnCompute);
        edtboarn = findViewById(R.id.edtBoarN);
        edtboaru = findViewById(R.id.edtBoarU);
        edtGrowN = findViewById(R.id.edtGrowN);
        edtGrowU = findViewById(R.id.edtGrowU);
        edtSowN = findViewById(R.id.edtSowN);
        edtSowU = findViewById(R.id.edtSowU);
        edtPigN = findViewById(R.id.edtPigN);
        edtPigU = findViewById(R.id.edtPigU);
        edtSwineTotal = findViewById(R.id.edtSwineTotal);
        edtSF_sw_kg = findViewById(R.id.edtSF_sw_kg);
        edtSF_sw_hd = findViewById(R.id.edtSF_sw_hd);
        edtSA_sw_kg = findViewById(R.id.edtSA_sw_kg);
        edtSA_sw_hd = findViewById(R.id.edtSA_sw_hd);
        edtSwineTotalArea = findViewById(R.id.edtSwineTotalArea);
        edtSwineTotalIncome = findViewById(R.id.edtSwineTotalIncome);
        rbno = findViewById(R.id.rb2);
        rbyes = findViewById(R.id.rb1);

        rbn = findViewById(R.id.rbn);
        rby = findViewById(R.id.rby);
//        vacc = findViewById(R.id.vaccination);
        textView = findViewById(R.id.textView);

        edtSwineTotal.setEnabled(false);
        Calendar now = Calendar.getInstance();
        now = Calendar.getInstance();
        now.add(Calendar.YEAR, 1);

        txtincome = findViewById(R.id.txtincome);
        txtincome.setText("Total Income for " + now.get(Calendar.YEAR));

        btnUpdate.setVisibility(View.GONE);

        cbpcv2.setChecked(false);
        cbhog.setChecked(false);
        cbmyco.setChecked(false);




        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                petid = null;
                update = null;
                pos = null;
            } else {
                ownerid= extras.getString("ownerid");
                petid= extras.getString("petid");

                pos= extras.getInt("position");
            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            petid = (String) savedInstanceState.getSerializable("petid");
            update = (String) savedInstanceState.getSerializable("update");
            pos = (Integer) savedInstanceState.getSerializable("position");


        }




        Cursor rs = myDB.getSwine(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0)
        {

            cbpcv2.setVisibility(View.VISIBLE);
            cbhog.setVisibility(View.VISIBLE);
            cbmyco.setVisibility(View.VISIBLE);

            cbpcv2.setChecked(false);
            cbhog.setChecked(false);
            cbmyco.setChecked(false);

            skip.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);

            String boarn = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_4));
            String boaru = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_5));
            String sown = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_6));
            String sowu = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_7));
            String grown = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_8));
            String growu = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_9));

            String pign = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_10));
            String pigu = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_11));
            String total_inv = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_12));
            String sl_f_kg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_13));
            String sl_f_hd = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_14));
            String sl_a_kg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_15));
            String sl_a_hd = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_16));
            String total_area = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_17));
            String total_inc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_18));

            String vacc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_19));


            if(vacc.equals("1"))
            {
                rbyes.setChecked(true);
                rbno.setChecked(false);
                vaccstat = "1";

            }else{
                rbno.setChecked(true);
                rbyes.setChecked(false);
                vaccstat = "2";

            }

             String vacctype = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_20));
            vacctype = vacctype.replace("[", "");
            vacctype = vacctype.replace("]", "");
            vacctype = vacctype.replace(", ", ",");



            mylist2 = new ArrayList<String>(Arrays.asList(vacctype.split(",")));




            final String vacca = vacctype;


            if(mylist2.contains(cbhog.getText().toString()))
            {
                mylistup.add(cbhog.getText().toString());
                cbhog.setChecked(true);

            }

            if(mylist2.contains(cbpcv2.getText().toString()))
            {
                mylistup.add(cbpcv2.getText().toString());
                cbpcv2.setChecked(true);

            }

            if(mylist2.contains(cbmyco.getText().toString()))
            {
                mylistup.add(cbmyco.getText().toString());
                cbmyco.setChecked(true);

            }

            String dewormed = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_21));


            if(dewormed.equals("1"))
            {
                rby.setChecked(true);
                rbn.setChecked(false);
                deworm = "1";


            }else{
                rbn.setChecked(true);
                rby.setChecked(false);
                deworm = "2";


            }

            edtboarn.setText(boarn);
            edtboaru.setText(boaru);
            edtGrowN.setText(grown);
            edtGrowU.setText(growu);
            edtSowN.setText(sown);
            edtSowU.setText(sowu);
            edtPigN.setText(pign);
            edtPigU.setText(pigu);
            edtSwineTotal.setText(total_inv);
            edtSF_sw_kg.setText(sl_f_kg);
            edtSF_sw_hd.setText(sl_f_hd);
            edtSA_sw_kg.setText(sl_a_kg);
            edtSA_sw_hd.setText(sl_a_hd);
            edtSwineTotalArea.setText(total_area);
            edtSwineTotalIncome.setText(total_inc);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final String boarn = edtboarn.getText().toString();
                    final String boaru = edtboaru.getText().toString();
                    final String grown = edtGrowN.getText().toString();
                    final String growu = edtGrowU.getText().toString();
                    final String sown = edtSowN.getText().toString();
                    final String sowu = edtSowU.getText().toString();
                    final String pign = edtPigN.getText().toString();
                    final String pigu = edtPigU.getText().toString();
                    final String swntotal = edtSwineTotal.getText().toString();
                    final String swn_sf_kg = edtSF_sw_kg.getText().toString();
                    final String swn_sf_hd = edtSF_sw_hd.getText().toString();
                    final String swn_sa_kg = edtSA_sw_kg.getText().toString();
                    final String swn_sa_hd = edtSA_sw_hd.getText().toString();
                    final String swn_totala = edtSwineTotalArea.getText().toString();
                    final String swn_totali = edtSwineTotalIncome.getText().toString();
                    final String vacc = vaccstat;
                    final String vacct = mylistup.toString();
                    final String dewormed = deworm;

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 0);
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    final String created_at = format1.format(cal.getTime());

                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(SwineActivity.this);

                    // Set a title for alert dialog
                    builder.setTitle("UPDATE.");

                    // Ask the final question
                    builder.setMessage("Are you sure you want to update the data?");

                    // Set click listener for alert dialog buttons
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    // User clicked the Yes button
                                    if (boarn.equals("") || boaru.equals("") || grown.equals("") ||
                                            growu.equals("") || sown.equals("") || sowu.equals("") ||
                                            pign.equals("") || pigu.equals("") || swntotal.equals("") ||
                                            swn_sf_kg.equals("") || swn_sf_hd.equals("") || swn_sa_kg.equals("") || swn_sa_hd.equals("") || swn_totala.equals("") || swn_totali.equals("")) {
                                        Toast.makeText(SwineActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();


                                    }else {
                                        try {
                                            myDB.updateSwine(ownerid, boarn.trim(), boaru.trim(), sown.trim(),
                                                    sowu.trim(), grown.trim(), growu.trim(), pign.trim(), pigu.trim(),
                                                    swntotal.trim(), swn_sf_kg.trim(), swn_sf_hd.trim(),swn_sa_kg.trim(), swn_sa_hd.trim(), swn_totala.trim(),
                                                    swn_totali.trim(),vacc.trim(), vacct.trim(), dewormed.trim());
                                            Toast.makeText(SwineActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid", ownerid);
                                            intent.putExtra("petid", petid);
                                            intent.putExtra("position", pos);

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

        }else{
            Toast.makeText(SwineActivity.this, "WALANG LAMAN!", Toast.LENGTH_SHORT).show();

        }



        cbhog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {

                    if(!Arrays.asList(mylist).contains(cbhog.getText().toString()))
                    {
                        mylist.add(cbhog.getText().toString());
                        mylistup.add(cbhog.getText().toString());
                        Toast.makeText(SwineActivity.this, "Check your input!" + mylistup, Toast.LENGTH_SHORT).show();

                    }

                }else{
                    mylist.remove(cbhog.getText().toString());
                    mylistup.remove(cbhog.getText().toString());
                }
            }
        });
        cbpcv2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    if(!Arrays.asList(mylist).contains(cbpcv2.getText().toString()))
                    {
                        mylist.add(cbpcv2.getText().toString());
                        mylistup.add(cbpcv2.getText().toString());
                        Toast.makeText(SwineActivity.this, "Check your input!" + mylistup, Toast.LENGTH_SHORT).show();
                    }


                }else{
                    mylist.remove(cbpcv2.getText().toString());
                    mylistup.remove(cbpcv2.getText().toString());

                }
            }
        });
        cbmyco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    if(!Arrays.asList(mylist).contains(cbmyco.getText().toString()))
                    {
                        mylist.add(cbmyco.getText().toString());
                        mylistup.add(cbmyco.getText().toString());

                        Toast.makeText(SwineActivity.this, "Check your input!" + mylistup, Toast.LENGTH_SHORT).show();
                    }

                }else{
                    mylist.remove(cbmyco.getText().toString());
                    mylistup.remove(cbmyco.getText().toString());

                }
            }
        });




        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Integer boarn = Integer.parseInt(edtboarn.getText().toString());
                final Integer boaru = Integer.parseInt(edtboaru.getText().toString());
                final Integer grown = Integer.parseInt(edtGrowN.getText().toString());
                final Integer growu = Integer.parseInt(edtGrowU.getText().toString());
                final Integer sown = Integer.parseInt(edtSowN.getText().toString());
                final Integer sowu = Integer.parseInt(edtSowU.getText().toString());
                final Integer pign = Integer.parseInt(edtPigN.getText().toString());
                final Integer pigu = Integer.parseInt(edtPigU.getText().toString());

                if(edtboarn.equals("") || edtboaru.equals("") || edtGrowN.equals("") || edtGrowU.equals("")
                        || edtSowN.equals("") || edtSowU.equals("") || edtPigN.equals("") || edtPigU.equals("") )
                {
                    Toast.makeText(SwineActivity.this, "Empty input!" + ownerid , Toast.LENGTH_SHORT).show();
                }else {
                    final Integer total = boarn + boaru + grown + growu +  sown + sowu + pign + pigu;
                    edtSwineTotal.setText(String.valueOf(total));
                }

            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ChickenActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
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

                final String boarn = edtboarn.getText().toString();
                final String boaru = edtboaru.getText().toString();
                final String grown = edtGrowN.getText().toString();
                final String growu = edtGrowU.getText().toString();
                final String sown = edtSowN.getText().toString();
                final String sowu = edtSowU.getText().toString();
                final String pign = edtPigN.getText().toString();
                final String pigu = edtPigU.getText().toString();
                final String swntotal = edtSwineTotal.getText().toString();
                final String swn_sf_kg = edtSF_sw_kg.getText().toString();
                final String swn_sf_hd = edtSF_sw_hd.getText().toString();
                final String swn_sa_kg = edtSA_sw_kg.getText().toString();
                final String swn_sa_hd = edtSA_sw_hd.getText().toString();
                final String swn_totala = edtSwineTotalArea.getText().toString();
                final String swn_totali = edtSwineTotalIncome.getText().toString();
                final String vacc = vaccstat;
                final String vacct = mylist.toString();
                final String dewormed = deworm;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SwineActivity.this);

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
                                if (boarn.equals("") || boaru.equals("") || grown.equals("") ||
                                        growu.equals("") || sown.equals("") || sowu.equals("") ||
                                        pign.equals("") || pigu.equals("") || swntotal.equals("") ||
                                        swn_sf_kg.equals("") || swn_sf_hd.equals("") || swn_sa_kg.equals("") || swn_sa_hd.equals("") || swn_totala.equals("") || swn_totali.equals("")) {
                                    Toast.makeText(SwineActivity.this, "Check your input!", Toast.LENGTH_SHORT).show();


                                }else {
                                    try {
                                        myDB.addSwine(ownerid, boarn.trim(), boaru.trim(), sown.trim(),
                                                sowu.trim(), grown.trim(), growu.trim(), pign.trim(), pigu.trim(),
                                                swntotal.trim(), swn_sf_kg.trim(), swn_sf_hd.trim(),swn_sa_kg.trim(), swn_sa_hd.trim(), swn_totala.trim(),
                                                swn_totali.trim(),vacc.trim(), vacct.trim(), dewormed.trim(),created_at.trim());
                                        Toast.makeText(SwineActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), ChickenActivity.class);
                                        intent.putExtra("ownerid", ownerid);
                                        intent.putExtra("petid", petid);
                                        intent.putExtra("pos", pos);
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

    public void disable()
    {

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb1:
                if (checked)
                    // Pirates are the best

                    cbpcv2.setVisibility(View.VISIBLE);
                cbhog.setVisibility(View.VISIBLE);
                cbmyco.setVisibility(View.VISIBLE);
                    vaccstat = "1";
                    textView.setVisibility(View.VISIBLE);
                    break;
            case R.id.rb2:
                if (checked)
                    textView.setVisibility(View.GONE);
                    vaccstat = "2";
                    mylist.add("");
                    cbpcv2.setVisibility(View.GONE);
                    cbhog.setVisibility(View.GONE);
                    cbmyco.setVisibility(View.GONE);
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
        if(pos != null)
        {
            Intent i = new Intent(SwineActivity.this, ListUpdateActivity.class);
            i.putExtra("position", pos);
            i.putExtra("ownerid", ownerid);
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
            finish();
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            finish();
            Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_owner) {
            finish();
            Intent intent=new Intent(getApplicationContext(), OwnerActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_list_pet) {
            finish();
            Intent intent=new Intent(getApplicationContext(), PetActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
