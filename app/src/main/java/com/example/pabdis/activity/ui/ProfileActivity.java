package com.example.pabdis.activity.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.CSWriter;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.helper.SessionManager;
import com.example.pabdis.activity.helper.User;
import com.example.pabdis.activity.login.LoginActivity;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button export,sync;
    DatabaseHelper mydb;
    Context context;
    String SAMPLE_DB_NAME = "pabdis";
    private SessionManager session;
    private ProgressDialog pDialog;
    TextView user_profile_name,user_profile_short_bio,user_type;
    Spinner muni, brgy;
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


                    displayLoader();
                    exportDB_Owner();
                    exportDB_PETS();
                    exportDB_PetVacc();
                    pDialog.dismiss();


            }
        });

        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }


    private void displayLoader() {
        pDialog = new ProgressDialog(ProfileActivity.this);
        pDialog.setMessage("Exporting.. Please wait...");
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
            Cursor curCSV = db.rawQuery("SELECT id,owner_id,petname,species,breed,sex,birthday,color_marking,distinguish_feature,source,pet_id,status,created_at FROM pvet_pet",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2),curCSV.getString(3),curCSV.getString(4),curCSV.getString(5),
                        curCSV.getString(6),curCSV.getString(7),curCSV.getString(8),curCSV.getString(9),curCSV.getString(10),
                        curCSV.getString(11),curCSV.getString(12),curCSV.getString(13)};
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
                    "oth.sheep,oth.horse, oth.rabbit, oth.duck, oth.turkey, oth.goose, oth.total_income as oth_income,"+
                    /*FISHERY*/
                    "f.total_area as f_area, f.total_production as f_prod, f.total_income as f_income,"+
                    /*APIARY*/
                    "ap.total_area as ap_col, ap.total_production as ap_prod, ap.total_income as ap_income,"+
                    /*HOUSEHOLD*/
                    "hh.beef, hh.carabeef, hh.pork,hh.chicken,hh.fish,hh.egg"+
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
                        curCSV.getString(111),curCSV.getString(112), curCSV.getString(113), curCSV.getString(114), curCSV.getString(115), curCSV.getString(116), curCSV.getString(117), curCSV.getString(118), curCSV.getString(119)

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
