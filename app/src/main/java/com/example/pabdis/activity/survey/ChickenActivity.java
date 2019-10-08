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
import com.example.pabdis.activity.ui.MapActivity;
import com.example.pabdis.activity.ui.OwnerActivity;
import com.example.pabdis.activity.ui.PetActivity;
import com.example.pabdis.activity.ui.ProfileActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ChickenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnNext, compute,btnUpdate;
    EditText edtBroiler,edtLayers,edtNative,edtTotal,edtProd,
            edtSF_sw_kg,edtSF_sw_hd,edtSA_sw_kg,edtSA_sw_hd,edtTotalArea,edtTotalIncome;
    String ownerid, update;
    DatabaseHelper myDB;
    RadioButton rbyes, rbno, rby, rbn;
    ArrayList<String> mylist = new ArrayList<String>();
    ArrayList<String> mylist2 = new ArrayList<String>();
    ArrayList<String> mylistup = new ArrayList<String>();
    FloatingActionButton skip;
    Spinner vacc;
    Integer pos;
    CheckBox cbncdls,cbfp,cbib,cbncdb1,cbncdcombo;
    String vaccstat, vacctype, deworm, petid;
    TextView textView, txtincome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_chicken);
        myDB = new DatabaseHelper(getApplicationContext());
        btnNext = findViewById(R.id.btnProceedSurvey);
        btnUpdate = findViewById(R.id.btnUpdate);
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

        rbn = findViewById(R.id.rbn);
        rby = findViewById(R.id.rby);
        textView = findViewById(R.id.textView);

        compute = findViewById(R.id.btnCompute);
        edtTotal.setEnabled(false);
        txtincome = findViewById(R.id.txtincome);
        txtincome.setText("Total Income for 2018");
        btnUpdate.setVisibility(View.GONE);


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



        Cursor rs = myDB.getChicken(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0)
        {

            skip.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);



            cbncdls.setVisibility(View.VISIBLE);
            cbfp.setVisibility(View.VISIBLE);
            cbib.setVisibility(View.VISIBLE);
            cbncdb1.setVisibility(View.VISIBLE);
            cbncdcombo.setVisibility(View.VISIBLE);

            cbncdls.setChecked(false);
            cbfp.setChecked(false);
            cbib.setChecked(false);
            cbncdb1.setChecked(false);
            cbncdcombo.setChecked(false);


            String broilers = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_4));
            String layers = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_5));
            String natives = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_6));
            String total_inv = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_7));
            String total_prod = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_8));
            String sl_f_kg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_9));
            String sl_f_hd = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_10));
            String sl_a_kg = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_11));
            String sl_a_hd = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_12));
            String total_area = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_13));
            String total_inc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_14));
            String vacc = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_15));


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

            edtBroiler.setText(broilers);
            edtLayers.setText(layers);
            edtNative.setText(natives);
            edtTotal.setText(total_inv);
            edtProd.setText(total_prod);
            edtSF_sw_kg.setText(sl_f_kg);
            edtSF_sw_hd.setText(sl_f_hd);
            edtSA_sw_kg.setText(sl_a_kg);
            edtSA_sw_hd.setText(sl_a_hd);
            edtTotalArea.setText(total_area);
            edtTotalIncome.setText(total_inc);

            String vacctype = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_16));
            vacctype = vacctype.replace("[", "");
            vacctype = vacctype.replace("]", "");
            vacctype = vacctype.replace(", ", ",");



            mylist2 = new ArrayList<String>(Arrays.asList(vacctype.split(",")));




            final String vacca = vacctype;


            if(mylist2.contains(cbncdls.getText().toString()))
            {
                mylistup.add(cbncdls.getText().toString());
                cbncdls.setChecked(true);

            }

            if(mylist2.contains(cbfp.getText().toString()))
            {
                mylistup.add(cbfp.getText().toString());
                cbfp.setChecked(true);

            }

            if(mylist2.contains(cbib.getText().toString()))
            {
                mylistup.add(cbib.getText().toString());
                cbib.setChecked(true);

            }

            if(mylist2.contains(cbncdb1.getText().toString()))
            {
                mylistup.add(cbncdb1.getText().toString());
                cbncdb1.setChecked(true);

            }


            if(mylist2.contains(cbncdcombo.getText().toString()))
            {
                mylistup.add(cbncdcombo.getText().toString());
                cbncdcombo.setChecked(true);
            }

            String dewormed = rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_17));


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


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
                    final String vacct = mylistup.toString();
                    final String dewormed = deworm;




                    // Build an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChickenActivity.this);

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


                                    if (broiler.equals("") || layer.equals("") || snative.equals("") ||
                                            total.equals("") || prod.equals("") || ch_sf_kg.equals("") || ch_sf__hd.equals("")
                                            || ch_sa_kg.equals("") || ch_sa__hd.equals("")  || ch_totala.equals("") || ch_totali.equals("")  ) {
                                        Toast.makeText(ChickenActivity.this, "Check your input!"  , Toast.LENGTH_SHORT).show();
                                    }else {

                                        try {
                                            myDB.updateChicken(ownerid,broiler, layer,snative,total,prod,ch_sf_kg,ch_sf__hd,ch_sa_kg,ch_sa__hd,
                                                    ch_totala,ch_totali,vacc.trim(), vacct.trim(), dewormed.trim());
                                            Toast.makeText(ChickenActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getApplicationContext(), ListUpdateActivity.class);
                                            intent.putExtra("ownerid",ownerid);
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




        }


        cbncdls.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b)
                {

                    if(!Arrays.asList(mylist).contains(cbncdls.getText().toString()))
                    {
                        mylist.add(cbncdls.getText().toString());
                        mylistup.add(cbncdls.getText().toString());
                    }

                }else{
                    mylist.remove(cbncdls.getText().toString());
                    mylistup.remove(cbncdls.getText().toString());

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
                        mylistup.add(cbfp.getText().toString());

                    }

                }else{
                    mylist.remove(cbfp.getText().toString());
                    mylistup.remove(cbfp.getText().toString());

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
                                mylistup.add(cbib.getText().toString());

                            }

                        }else{
                            mylist.remove(cbib.getText().toString());
                            mylistup.remove(cbib.getText().toString());

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
                        mylistup.add(cbncdb1.getText().toString());

                    }

                }else{
                    mylist.remove(cbncdb1.getText().toString());
                    mylistup.remove(cbncdb1.getText().toString());

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
                                mylistup.add(cbncdcombo.getText().toString());

                            }

                        }else{
                            mylist.remove(cbncdcombo.getText().toString());
                            mylistup.remove(cbncdcombo.getText().toString());

                        }

                    }
                });

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Integer broiler = Integer.parseInt(edtBroiler.getText().toString());
                final Integer layer = Integer.parseInt(edtLayers.getText().toString());
                final Integer snative = Integer.parseInt(edtNative.getText().toString());




                if(edtBroiler.equals("") || edtLayers.equals("") || edtNative.equals("") )
                {

                    Toast.makeText(ChickenActivity.this, "Empty input!" + ownerid , Toast.LENGTH_SHORT).show();



                }else {

                    final Integer total = broiler + layer + snative ;
                    edtTotal.setText(String.valueOf(total));
                }




            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CattleActivity.class);
                intent.putExtra("ownerid",ownerid);
                intent.putExtra("petid", petid);
                intent.putExtra("pos", pos);
                startActivity(intent);



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
                cal.add(Calendar.DATE, 0);
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
                                    Toast.makeText(ChickenActivity.this, "Check your input!"  , Toast.LENGTH_SHORT).show();
                                }else {

                                    try {
                                        myDB.addChicken(ownerid,broiler, layer,snative,total,prod,ch_sf_kg,ch_sf__hd,ch_sa_kg,ch_sa__hd,
                                                ch_totala,ch_totali,vacc.trim(), vacct.trim(), dewormed.trim(),created_at );
                                        Toast.makeText(ChickenActivity.this, "Success!" , Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), CattleActivity.class);
                                        intent.putExtra("ownerid",ownerid);
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
        if(pos != null)
        {
            Intent i = new Intent(ChickenActivity.this, ListUpdateActivity.class);
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
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);

        }  else if (id == R.id.nav_list_owner) {

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