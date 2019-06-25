package com.example.pabdis.activity.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Pair;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pabdis.BuildConfig;
import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.survey.ChickenActivity;
import com.example.pabdis.activity.survey.GoatActivity;
import com.example.pabdis.activity.survey.SwineActivity;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText lastname, lastname2, firstname2, firstname, dateSurvey, houseno, contact;
    Spinner muni, brgy;
    Integer position, year, ctr;
    DatabaseHelper myDB;
    Character first;
    String ownerid;
    Button btndate, proceedSurvey;
    ArrayAdapter<CharSequence> munici, brgylt, brgy_kib, brgy_it,brgy_bug,brgy_kab,brgy_sab,brgy_man,brgy_bak,brgy_tba,brgy_tbl,brgy_at,brgy_bok,brgy_kap;
    final Calendar myCalendar = Calendar.getInstance();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        lastname = findViewById(R.id.edtLastName);
        firstname = findViewById(R.id.edtFirstName);
        lastname2 = findViewById(R.id.edtLastName1);
        firstname2 = findViewById(R.id.edtFirstName1);
        contact = findViewById(R.id.edtContact);
        houseno = findViewById(R.id.edtHouseNo);

        proceedSurvey = findViewById(R.id.btnProceedSurvey);
//        dateSurvey.setEnabled(false);


        muni = findViewById(R.id.muni);
        brgy = findViewById(R.id.brgy);

        munici = ArrayAdapter.createFromResource(this, R.array.muni, R.layout.support_simple_spinner_dropdown_item);
        brgylt = ArrayAdapter.createFromResource(this, R.array.brgy_lt, R.layout.support_simple_spinner_dropdown_item);
        brgy_at = ArrayAdapter.createFromResource(this, R.array.brgy_at, R.layout.support_simple_spinner_dropdown_item);
        brgy_bak = ArrayAdapter.createFromResource(this, R.array.brgy_bak, R.layout.support_simple_spinner_dropdown_item);
        brgy_bok = ArrayAdapter.createFromResource(this, R.array.brgy_bok, R.layout.support_simple_spinner_dropdown_item);
        brgy_bug = ArrayAdapter.createFromResource(this, R.array.brgy_bug, R.layout.support_simple_spinner_dropdown_item);
        brgy_it = ArrayAdapter.createFromResource(this, R.array.brgy_it, R.layout.support_simple_spinner_dropdown_item);
        brgy_kab = ArrayAdapter.createFromResource(this, R.array.brgy_kab, R.layout.support_simple_spinner_dropdown_item);
        brgy_kap = ArrayAdapter.createFromResource(this, R.array.brgy_kap, R.layout.support_simple_spinner_dropdown_item);
        brgy_kib = ArrayAdapter.createFromResource(this, R.array.brgy_kib, R.layout.support_simple_spinner_dropdown_item);
        brgy_man = ArrayAdapter.createFromResource(this, R.array.brgy_man, R.layout.support_simple_spinner_dropdown_item);
        brgy_sab = ArrayAdapter.createFromResource(this, R.array.brgy_sab, R.layout.support_simple_spinner_dropdown_item);
        brgy_tba = ArrayAdapter.createFromResource(this, R.array.brgy_tba, R.layout.support_simple_spinner_dropdown_item);
        brgy_tbl = ArrayAdapter.createFromResource(this, R.array.brgy_tbl, R.layout.support_simple_spinner_dropdown_item);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        muni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();


                switch (selectedItem) {
                    case "La Trinidad":
                        brgy.setAdapter(brgylt);
                        break;
                    case "Itogon":
                        brgy.setAdapter(brgy_it);
                        break;
                    case "Buguias":
                        brgy.setAdapter(brgy_bug);
                        break;
                    case "Kabayan":
                        brgy.setAdapter(brgy_kab);
                        break;
                    case "Sablan":
                        brgy.setAdapter(brgy_sab);
                        break;
                    case "Mankayan":
                        brgy.setAdapter(brgy_man);
                        break;
                    case "Kapangan":
                        brgy.setAdapter(brgy_kap);
                        break;
                    case "Bokod":
                        brgy.setAdapter(brgy_bok);
                        break;
                    case "Atok":
                        brgy.setAdapter(brgy_at);
                        break;
                    case "Kibungan":
                        brgy.setAdapter(brgy_kib);
                        break;
                    case "Tublay":
                        brgy.setAdapter(brgy_tbl);
                        break;
                    case "Tuba":
                        brgy.setAdapter(brgy_tba);
                        break;
                    case "Bakun":
                        brgy.setAdapter(brgy_bak);
                        break;
                }
                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        proceedSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String hfname = firstname.getText().toString();
                final String hlname = lastname.getText().toString();
                final String rfname = firstname2.getText().toString();
                final String rlname = lastname2.getText().toString();

                final String num = contact.getText().toString();
                final String house = houseno.getText().toString();
                final String mun = muni.getSelectedItem().toString();
                final String brg = brgy.getSelectedItem().toString();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                final String created_at = format1.format(cal.getTime());

                String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 18) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }


                position = brgy.getSelectedItemPosition();
                position++;
                year = Calendar.getInstance().get(Calendar.YEAR);

                ctr = myDB.getData(mun);
                ctr++;
                first = mun.charAt(0);
                ownerid = first.toString() + position.toString() + year.toString() + ctr.toString();

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

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

                                if (hfname.equals("") || hlname.equals("") || rfname.equals("") || rlname.equals("") || num.equals("") || house.equals("") ) {
                                    Toast.makeText(MainActivity.this, ""+ctr , Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), VaccinationActivity.class);
                                    startActivity(intent);
                                }else{

                                    try {
                                        myDB.addOwner(ownerid.trim(),hfname.trim(),hlname.trim(),rfname.trim(),rlname.trim(),num.trim(), house.trim(), mun.trim(), brg.trim(),created_at);
                                        Toast.makeText(MainActivity.this, "Success!" , Toast.LENGTH_LONG).show();
//                                        showDebugDBAddressLogToast(MainActivity.this);
                                        Intent intent = new Intent(getApplicationContext(), SwineActivity.class);
                                        intent.putExtra("ownerid", ownerid.trim());
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

    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
            } catch (Exception ignore) {

            }
        }
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateSurvey.setText(sdf.format(myCalendar.getTime()));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
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

        } else if (id == R.id.nav_map) {

            Intent intent=new Intent(getApplicationContext(), MapActivity.class);
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
