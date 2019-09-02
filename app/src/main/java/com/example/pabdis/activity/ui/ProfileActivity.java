package com.example.pabdis.activity.ui;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.CSWriter;
import com.example.pabdis.activity.helper.DatabaseHelper;
import com.example.pabdis.activity.updates.ListUpdateActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Button export;
    DatabaseHelper mydb;
    Context context;
    String SAMPLE_DB_NAME = "pabdis";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mydb = new DatabaseHelper(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        export = findViewById(R.id.export);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    exportDB();
                    exportDB_PETS();
                    exportDB_PetVacc();


            }
        });

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
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
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
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
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
    private void exportDB() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "PABDIS.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_owner LEFT JOIN pvet_survey_swine ON pvet_owner.owner_id = pvet_survey_swine.owner_id " +
                    "LEFT JOIN pvet_survey_chicken ON pvet_survey_chicken.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_cattle ON pvet_survey_cattle.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_carabao ON pvet_survey_carabao.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_goat ON pvet_survey_goat.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_other ON pvet_survey_other.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_fishery ON pvet_survey_fishery.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_apiary ON pvet_survey_apiary.owner_id = pvet_owner.owner_id " +
                    "LEFT JOIN pvet_survey_household ON pvet_survey_household.owner_id = pvet_owner.owner_id ",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
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

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
