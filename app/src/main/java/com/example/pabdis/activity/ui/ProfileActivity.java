package com.example.pabdis.activity.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.CSReader;
import com.example.pabdis.activity.helper.CSWriter;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.MySingleton;
import com.example.pabdis.activity.helper.SessionManager;
import com.example.pabdis.activity.helper.User;
import com.example.pabdis.activity.login.LoginActivity;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button export,sync,clear, export2;
    DatabaseHelper mydb;
    Context context;
    String SAMPLE_DB_NAME = "pabdis";
    private SessionManager session;
    private ProgressDialog pDialog;
    public static final int requestcode = 1;
    static final int ACTIVITY_CHOOSE_FILE1 = 1;
    TextView user_profile_name,user_profile_short_bio,user_type;
    Spinner muni, brgy;
    Switch stch;
    Integer stat = 0;
    ArrayAdapter<CharSequence> types,munici, brgylt, brgy_kib, brgy_it, brgy_bug, brgy_kab, brgy_sab, brgy_man, brgy_bak, brgy_tba, brgy_tbl, brgy_at, brgy_bok, brgy_kap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mydb = new DatabaseHelper(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        User user = session.getUserDetails();
        user_profile_name =  findViewById(R.id.user_profile_name);
        user_profile_short_bio  = findViewById(R.id.user_profile_short_bio);
        user_type  = findViewById(R.id.user_type);
        stch = findViewById(R.id.switch_brgy);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {

                stat = 0;
            } else {

                stat= extras.getInt("stat");
            }
        } else {

            stat = (Integer) savedInstanceState.getSerializable("stat");

        }


        stch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Toast.makeText(ProfileActivity.this, "Barangay Setting is on!" , Toast.LENGTH_SHORT).show();
                    stch.setChecked(true);
                    stat = 1;
                }else{
                    Toast.makeText(ProfileActivity.this, "Barangay Setting is off!" , Toast.LENGTH_SHORT).show();
                    stch.setChecked(false);
                    stat = 0;
                }
            }
        });

        if(stat.equals(1))
        {
            stch.setChecked(true);

        }else{
            stch.setChecked(false);
        }



        if(user != null)
        {
            user_profile_name.setText("Username: " + user.getUsername());
            user_type.setText("Full Name: " + user.getFullName());
        }else{

            user_profile_name.setText("Username: ");
            user_type.setText("Full Name: ");

        }

        user_profile_short_bio.setText("User Type : Enumerator");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        brgylt = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_lt, R.layout.support_simple_spinner_dropdown_item);
        brgy_at = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_at, R.layout.support_simple_spinner_dropdown_item);
        brgy_bak = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_bak, R.layout.support_simple_spinner_dropdown_item);
        brgy_bok = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_bok, R.layout.support_simple_spinner_dropdown_item);
        brgy_bug = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_bug, R.layout.support_simple_spinner_dropdown_item);
        brgy_it = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_it, R.layout.support_simple_spinner_dropdown_item);
        brgy_kab = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_kab, R.layout.support_simple_spinner_dropdown_item);
        brgy_kap = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_kap, R.layout.support_simple_spinner_dropdown_item);
        brgy_kib = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_kib, R.layout.support_simple_spinner_dropdown_item);
        brgy_man = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_man, R.layout.support_simple_spinner_dropdown_item);
        brgy_sab = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_sab, R.layout.support_simple_spinner_dropdown_item);
        brgy_tba = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_tba, R.layout.support_simple_spinner_dropdown_item);
        brgy_tbl = ArrayAdapter.createFromResource(ProfileActivity.this, R.array.brgy_tbl, R.layout.support_simple_spinner_dropdown_item);
        muni = findViewById(R.id.muni);
        brgy = findViewById(R.id.brgy);



        export = findViewById(R.id.export);
        sync = findViewById(R.id.sync);
//        sync.setVisibility(View.GONE);
        muni.setVisibility(View.GONE);
        brgy.setVisibility(View.GONE);
        clear = findViewById(R.id.clear);
        export2 = findViewById(R.id.export2);




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




        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Build an AlertDialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ProfileActivity.this);

                // Set a title for alert dialog
                builder2.setTitle("Export.");

                // Ask the final question
                builder2.setMessage("Do you want to export the data? ");

                // Set click listener for alert dialog buttons

                DialogInterface.OnClickListener dialogClickListener1 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button

                                displayLoader();


                                try {
                                    exportDB_Owner();

                                    Toast.makeText(ProfileActivity.this, "Success EXPORTING the data!" , Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                pDialog.dismiss();


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:


                                break;
                        }
                    }
                };

                // Set the alert dialog yes button click listener
                builder2.setPositiveButton("Yes", dialogClickListener1);

                // Set the alert dialog no button click listener
                builder2.setNegativeButton("No",dialogClickListener1);

                AlertDialog dialog4 = builder2.create();
                // Display the alert dialog on interface
                dialog4.show();




            }
        });

        export2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Build an AlertDialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ProfileActivity.this);

                // Set a title for alert dialog
                builder2.setTitle("Export.");

                // Ask the final question
                builder2.setMessage("Do you want to export the data? ");

                // Set click listener for alert dialog buttons

                DialogInterface.OnClickListener dialogClickListener1 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button

                                displayLoader();


                                try {
                                    exportDB_PETS();
                                    exportDB_PetVacc();

                                    Toast.makeText(ProfileActivity.this, "Success EXPORTING the data!" , Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                pDialog.dismiss();


                                break;

                            case DialogInterface.BUTTON_NEGATIVE:


                                break;
                        }
                    }
                };

                // Set the alert dialog yes button click listener
                builder2.setPositiveButton("Yes", dialogClickListener1);

                // Set the alert dialog no button click listener
                builder2.setNegativeButton("No",dialogClickListener1);

                AlertDialog dialog4 = builder2.create();
                // Display the alert dialog on interface
                dialog4.show();




            }
        });

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Build an AlertDialog
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ProfileActivity.this);

                // Set a title for alert dialog
                builder2.setTitle("Import.");

                // Ask the final question
                builder2.setMessage("Do you want to import the data? ");

                // Set click listener for alert dialog buttons

                DialogInterface.OnClickListener dialogClickListener1 = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button




                                try {

                                    displayLoader2();
                                    CheckPermission();
                                    selectCSVFile();

                                    pDialog.dismiss();

                                    Toast.makeText(ProfileActivity.this, "Success IMPORTING the data!" , Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }



                                break;

                            case DialogInterface.BUTTON_NEGATIVE:


                                break;
                        }
                    }
                };

                // Set the alert dialog yes button click listener
                builder2.setPositiveButton("Yes", dialogClickListener1);

                // Set the alert dialog no button click listener
                builder2.setNegativeButton("No",dialogClickListener1);

                AlertDialog dialog4 = builder2.create();
                // Display the alert dialog on interface
                dialog4.show();




            }



        });







        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);

                // Set a title for alert dialog
                builder.setTitle("DELETE ALL.");

                // Ask the final question
                builder.setMessage("Do you want to clear the data? ");

                // Set click listener for alert dialog buttons

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                // User clicked the Yes button


                                            // Build an AlertDialog
                                            AlertDialog.Builder builder2 = new AlertDialog.Builder(ProfileActivity.this);

                                            // Set a title for alert dialog
                                            builder2.setTitle("DELETE ALL.");

                                            // Ask the final question
                                            builder2.setMessage("After clicking Yes, you cannot retrieve the cleared data.");

                                            // Set click listener for alert dialog buttons

                                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    switch(which){
                                                        case DialogInterface.BUTTON_POSITIVE:
                                                            // User clicked the Yes button

                                                            try {
                                                                mydb.deleteAll();
                                                                Toast.makeText(ProfileActivity.this, "Success cleared the database!" , Toast.LENGTH_SHORT).show();

                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }

                                                            break;

                                                        case DialogInterface.BUTTON_NEGATIVE:


                                                            break;
                                                    }
                                                }
                                            };

                                            // Set the alert dialog yes button click listener
                                            builder2.setPositiveButton("Yes", dialogClickListener);

                                            // Set the alert dialog no button click listener
                                            builder2.setNegativeButton("No",dialogClickListener);

                                            AlertDialog dialog2 = builder2.create();
                                            // Display the alert dialog on interface
                                            dialog2.show();



                                break;

                            case DialogInterface.BUTTON_NEGATIVE:


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


    private void selectCSVFile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Open CSV"), ACTIVITY_CHOOSE_FILE1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        switch(requestCode) {

            case ACTIVITY_CHOOSE_FILE1: {


    //                   Log.e("FROM",data.getData().getPath().toString());

                    if (resultCode == RESULT_OK){
//                        proImportCSV(new File(Environment.getExternalStorageDirectory() + "/sample.csv"));
                        proImportCSV(new File(data.getData().getPath()));
                }
            }
        }
    }


    private void proImportCSV(File from){
        try {


            // Delete everything above here since we're reading from the File we already have
            ContentValues cv_owner = new ContentValues();
            ContentValues cv_pet = new ContentValues();
            ContentValues cv_vacc = new ContentValues();
            ContentValues cv_swine = new ContentValues();

            String path = from.getPath();
                               Log.e("PATH",path);




            String filename=path.substring(path.lastIndexOf("/")+1);
                               Log.e("FILENAME",filename);

            // reading CSV and writing table

            File csvfile = new File(Environment.getExternalStorageDirectory() + "/Download/"+ filename);


            Log.e("csvfile",csvfile.getAbsolutePath());


            CSReader dataRead = new CSReader(new FileReader(csvfile.getAbsolutePath()));


//            CSReader dataRead = new CSReader(new FileReader(from.getAbsolutePath()));

            SQLiteDatabase db = mydb.getWritableDatabase(); // LEt's just put this here since you'll probably be using it a lot more than once
            String[] vv = null;

            int inter = 0;
            while((vv = dataRead.readNext())!= null) {

                if(inter == 0)
                {
                    inter++;
                }
                cv_owner.clear();
                cv_pet.clear();
                cv_vacc.clear();

                SimpleDateFormat currFormater  = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat postFormater = new SimpleDateFormat("yyyy-MM-dd");

                String eDDte;
                try {
                    Date nDate = currFormater.parse(vv[0]);
                    eDDte = postFormater.format(nDate);
                }
                catch (Exception e) {
                }

                int rs2 = mydb.getCountOwner(vv[1]);

                if(rs2 == 0 && !vv[1].equals("owner_id"))
                {

                    cv_owner.put(DatabaseHelper.OWNERCOL_2,vv[0]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_3,vv[1]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_4,vv[2]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_5,vv[3]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_6,vv[4]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_7,vv[5]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_8,vv[6]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_9,vv[7]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_10,vv[8]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_11,vv[9]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_12,vv[10]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_13,vv[11]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_14,vv[12]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_15,vv[13]);
                    cv_owner.put(DatabaseHelper.OWNERCOL_16,vv[14]);
                    db.insert(DatabaseHelper.TABLE_OWNER,null,cv_owner);

                }




                int rs4 = mydb.getCountSwine(vv[1]);

                if(rs4 == 0 && !vv[1].equals("owner_id"))
                {
                    cv_swine.put(DatabaseHelper.VACCCOL_3,vv[0]);

                    cv_swine.put(DatabaseHelper.SURVEYCOL_4,vv[15]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_5,vv[16]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_6,vv[17]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_7,vv[18]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_8,vv[19]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_9,vv[20]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_10,vv[21]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_11,vv[22]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_12,vv[23]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_13,vv[24]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_14,vv[25]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_15,vv[26]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_16,vv[27]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_17,vv[28]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_18,vv[29]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_19,vv[30]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_20,vv[31]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_21,vv[32]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_22,vv[33]);
                    cv_swine.put(DatabaseHelper.SURVEYCOL_23,vv[34]);
                    db.insert(DatabaseHelper.TABLE_SURVEY1,null,cv_swine);
                }
                int rs = mydb.getCountPetAll(vv[1]);

                if(rs == 0 && !vv[1].equals("owner_id"))
                {
                    cv_pet.put(DatabaseHelper.VACCCOL_3,vv[1]);
                    cv_pet.put(DatabaseHelper.VACCCOL_4,vv[35]);
                    cv_pet.put(DatabaseHelper.VACCCOL_5,vv[36]);
                    cv_pet.put(DatabaseHelper.VACCCOL_6,vv[37]);
                    cv_pet.put(DatabaseHelper.VACCCOL_7,vv[38]);
                    cv_pet.put(DatabaseHelper.VACCCOL_8,vv[39]);
                    cv_pet.put(DatabaseHelper.VACCCOL_9,vv[40]);
                    cv_pet.put(DatabaseHelper.VACCCOL_10,vv[41]);
                    cv_pet.put(DatabaseHelper.VACCCOL_11,vv[42]);
                    cv_pet.put(DatabaseHelper.VACCCOL_12,vv[43]);
                    cv_pet.put(DatabaseHelper.VACCCOL_13,vv[44]);
                    cv_pet.put(DatabaseHelper.VACCCOL_15,vv[45]);
                    cv_pet.put(DatabaseHelper.VACCCOL_16,vv[46]);
                    db.insert(DatabaseHelper.TABLE_VACC,null,cv_pet);
                }

                int rs3 = mydb.getCountVacc(vv[43],vv[47],vv[48]);
                if(rs3 == 0 && !vv[43].equals("pet_id"))
                {
                    cv_vacc.put(DatabaseHelper.VACC_DATE_2,vv[43]);
                    cv_vacc.put(DatabaseHelper.VACC_DATE_3,vv[47]);
                    cv_vacc.put(DatabaseHelper.VACC_DATE_4,vv[48]);
                    cv_vacc.put(DatabaseHelper.VACC_DATE_5,vv[49]);
                    cv_vacc.put(DatabaseHelper.VACC_DATE_6,vv[50]);
                    db.insert(DatabaseHelper.TABLE_VACC_DATE,null,cv_vacc);

                }





            } dataRead.close();

        } catch (Exception e) {
            Log.e("TAG",e.toString());
            Toast.makeText(ProfileActivity.this, "File not found!" , Toast.LENGTH_SHORT).show();


        }



    }


    private void displayLoader() {
        pDialog = new ProgressDialog(ProfileActivity.this);
        pDialog.setMessage("Exporting.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    public void CheckPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);

        }
    }


    private void displayLoader2() {
        pDialog = new ProgressDialog(ProfileActivity.this);
        pDialog.setMessage("Importing.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    private void exportDB_PetVacc() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "PABDIS_PetVacc.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_pet_vaccination",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),curCSV.getString(3),curCSV.getString(4)};
                csvWrite.writeNext(arrStr);
            }
            Toast.makeText(ProfileActivity.this, "Success!" , Toast.LENGTH_SHORT).show();
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }
    private void exportDB_PETS() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "PABDIS_Pet.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("" +
                    "SELECT " +
                    "p.id," +
                    "p.owner_id as pet_owner," +
                    "p.petname,p.species,p.breed,p.sex,p.birthday,p.color_marking,p.distinguish_feature,p.source,p.pet_id,p.status," +
                    "p.created_at as pet_created," +
                    "o.onwer_type,o.owner_info,o.r_lname,o.r_fname,o.members,o.contact_no, " +
                    "o.municipality,o.barangay, o.house, o.latitude, o.longitude, o.full_add,o.created_at as owner_created" +
                    " FROM pvet_pet as p INNER JOIN pvet_owner as o WHERE p.owner_id = o.owner_id",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),curCSV.getString(3),curCSV.getString(4),curCSV.getString(5),
                        curCSV.getString(6),curCSV.getString(7),curCSV.getString(8),curCSV.getString(9),curCSV.getString(10),
                        curCSV.getString(11),curCSV.getString(12),curCSV.getString(13),curCSV.getString(14),curCSV.getString(15),curCSV.getString(16)
                        ,curCSV.getString(17),curCSV.getString(18),curCSV.getString(19),curCSV.getString(20),curCSV.getString(21),curCSV.getString(22),
                        curCSV.getString(23),curCSV.getString(24),curCSV.getString(25),curCSV.getString(26)};
                csvWrite.writeNext(arrStr);
            }
            Toast.makeText(ProfileActivity.this, "Success!" , Toast.LENGTH_SHORT).show();
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }
    private void exportDB_Owner() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pabdis_survey.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery( " SELECT o.*, " +
                    /*SWINE*/
                    "sw.owner_id, sw.boar_n, sw.boar_u, sw.sowgit_n, " +
                    "sw.sowgit_u, sw.growers_n, sw.growers_u, sw.piglet_n, sw.piglet_u," +
                    "sw.total as sw_total, sw.sl_farm_kg as sw_f_kg , sw.sl_farm_hd as sw_f_hd , sw.sl_abb_kg as sw_a_kg , sw.sl_abb_hd as sw_a_hd, " +
                    "sw.total_area as sw_area, sw.total_income as sw_income, sw.IsVaccinated as sw_vacc, sw.vaccination as sw_vacctype, " +
                    "sw.IsDewormed as sw_deworm, sw.created_at as sw_created," +
                    /*CHCKEN*/
                    "ch.broilers, ch.layers, ch.native," +
                    "ch.total as ch_total, ch.production as ch_prod,ch.sl_farm_kg as ch_f_kg , ch.sl_farm_hd as ch_f_hd , ch.sl_abb_kg as ch_a_kg , ch.sl_abb_hd as ch_a_hd, " +
                    "ch.total_area as ch_area, ch.total_income as ch_income, ch.IsVaccinated as ch_vacc, ch.vaccination as ch_vacctype, " +
                    "ch.IsDewormed as ch_deworm, ch.created_at as ch_created," +
                    /*CATTLE*/
                    "cat.bull_d, cat.bull_m, cat.cow_d, cat.cow_m, cat.calf_d, cat.calf_m,"+
                    "cat.total as cat_total, cat.sl_farm_kg as cat_f_kg , cat.sl_farm_hd as cat_f_hd , cat.sl_abb_kg as cat_a_kg , cat.sl_abb_hd as cat_a_hd, " +
                    "cat.total_area as cat_area, cat.total_income as cat_income, cat.IsVaccinated as cat_vacc, cat.vaccination as cat_vacctype, " +
                    "cat.IsDewormed as cat_deworm, cat.created_at as cat_created," +
                    /*CARABAO*/
                    "car.carabull_c, car.carabull_n, car.caracow_c, car.caracow_n, car.caracalf_c, car.caracalf_n,"+
                    "car.total as car_total, car.sl_farm_kg as car_f_kg , car.sl_farm_hd as car_f_hd , car.sl_abb_kg as car_a_kg , car.sl_abb_hd as car_a_hd, " +
                    "car.total_area as car_area, car.total_income as car_income, car.IsVaccinated as car_vacc, car.vaccination as car_vacctype, " +
                    "car.IsDewormed as car_deworm, car.created_at as car_created," +
                    /*GOAT*/
                    "got.buck_d, got.buck_m, got.doe_d, got.doe_m, got.kids_d, got.kids_m,"+
                    "got.total as got_total, got.sl_farm_kg as got_f_kg , got.sl_farm_hd as got_f_hd , got.sl_abb_kg as got_a_kg , got.sl_abb_hd as got_a_hd, " +
                    "got.total_area as got_area, got.total_income as got_income, got.IsVaccinated as got_vacc, got.vaccination as got_vacctype, " +
                    "got.IsDewormed as got_deworm, got.created_at as got_created," +
                    /*OTHER*/
                    "oth.sheep,oth.horse, oth.rabbit, oth.duck, oth.turkey, oth.goose, oth.total_income as oth_income, oth.created_at as oth_created,"+
                    /*FISHERY*/
                    "f.total_area as f_area, f.total_production as f_prod, f.total_income as f_income, f.created_at as f_created,"+
                    /*APIARY*/
                    "ap.total_area as ap_col, ap.total_production as ap_prod, ap.total_income as ap_income, ap.created_at as ap_created,"+
                    /*HOUSEHOLD*/
                    "hh.beef, hh.carabeef, hh.pork,hh.chicken,hh.fish,hh.egg,hh.created_at as hh_created"+
                    " FROM pvet_owner as o LEFT JOIN pvet_survey_swine as sw ON o.owner_id = sw.owner_id " +
                    "LEFT JOIN pvet_survey_chicken as ch ON ch.owner_id = o.owner_id  " +
                    "LEFT JOIN pvet_survey_cattle as cat ON cat.owner_id = o.owner_id " +
                    "LEFT JOIN pvet_survey_carabao as car ON car.owner_id = o.owner_id " +
                    "LEFT JOIN pvet_survey_goat as got ON  got.owner_id = o.owner_id " +
                    "LEFT JOIN pvet_survey_other as oth ON oth.owner_id = o.owner_id " +
                    "LEFT JOIN pvet_survey_fishery as f ON f.owner_id = o.owner_id " +
                    "LEFT JOIN pvet_survey_apiary as ap ON ap.owner_id = o.owner_id " +
                    "LEFT JOIN pvet_survey_household as hh ON hh.owner_id = o.owner_id ",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={
                        curCSV.getString(0),curCSV.getString(1), curCSV.getString(2), curCSV.getString(3), curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7), curCSV.getString(8), curCSV.getString(9), curCSV.getString(10),
                        curCSV.getString(11),curCSV.getString(12), curCSV.getString(13), curCSV.getString(14), curCSV.getString(15), curCSV.getString(16), curCSV.getString(17), curCSV.getString(18), curCSV.getString(19), curCSV.getString(20),
                        curCSV.getString(21),curCSV.getString(22), curCSV.getString(23), curCSV.getString(24), curCSV.getString(25), curCSV.getString(26), curCSV.getString(27), curCSV.getString(28), curCSV.getString(29), curCSV.getString(30),
                        curCSV.getString(31),curCSV.getString(32), curCSV.getString(33), curCSV.getString(34), curCSV.getString(35), curCSV.getString(36), curCSV.getString(37), curCSV.getString(38), curCSV.getString(39), curCSV.getString(40),
                        curCSV.getString(41),curCSV.getString(42), curCSV.getString(43), curCSV.getString(44), curCSV.getString(45), curCSV.getString(46), curCSV.getString(47), curCSV.getString(48), curCSV.getString(49), curCSV.getString(50),
                        curCSV.getString(51),curCSV.getString(52), curCSV.getString(53), curCSV.getString(54), curCSV.getString(55), curCSV.getString(56), curCSV.getString(57), curCSV.getString(58), curCSV.getString(59), curCSV.getString(60),
                        curCSV.getString(61),curCSV.getString(62), curCSV.getString(63), curCSV.getString(64), curCSV.getString(65), curCSV.getString(66), curCSV.getString(67), curCSV.getString(68), curCSV.getString(69), curCSV.getString(70),
                        curCSV.getString(71),curCSV.getString(72), curCSV.getString(73), curCSV.getString(74), curCSV.getString(75), curCSV.getString(76), curCSV.getString(77), curCSV.getString(78), curCSV.getString(79), curCSV.getString(80),
                        curCSV.getString(81),curCSV.getString(82), curCSV.getString(83), curCSV.getString(84), curCSV.getString(85), curCSV.getString(86), curCSV.getString(87), curCSV.getString(88), curCSV.getString(89), curCSV.getString(90),
                        curCSV.getString(91),curCSV.getString(92), curCSV.getString(93), curCSV.getString(94), curCSV.getString(95), curCSV.getString(96), curCSV.getString(97), curCSV.getString(98), curCSV.getString(99), curCSV.getString(100),
                        curCSV.getString(101),curCSV.getString(102), curCSV.getString(103), curCSV.getString(104), curCSV.getString(105), curCSV.getString(106), curCSV.getString(107), curCSV.getString(108), curCSV.getString(109), curCSV.getString(110),
                        curCSV.getString(111),curCSV.getString(112), curCSV.getString(113), curCSV.getString(114), curCSV.getString(115), curCSV.getString(116), curCSV.getString(117), curCSV.getString(118), curCSV.getString(119), curCSV.getString(120), curCSV.getString(121), curCSV.getString(122), curCSV.getString(123)

                };
                csvWrite.writeNext(arrStr);
            }
            Toast.makeText(ProfileActivity.this, "Success!" , Toast.LENGTH_SHORT).show();
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            intent.putExtra("stat", stat);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
            intent.putExtra("stat", stat);
            startActivity(intent);
        } else if (id == R.id.nav_list_owner) {
            Intent intent=new Intent(getApplicationContext(), OwnerActivity.class);
            intent.putExtra("stat", stat);
            startActivity(intent);
        } else if (id == R.id.nav_list_pet) {
            Intent intent=new Intent(getApplicationContext(), PetActivity.class);
            intent.putExtra("stat", stat);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            session.logoutUser();
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
            finish();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
