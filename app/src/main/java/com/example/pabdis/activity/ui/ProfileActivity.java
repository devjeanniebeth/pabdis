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



                    exportDB_Owner();
                    exportDB_Apiary();
                    exportDB_Carabao();
                    exportDB_Chicken();
                    exportDB_Fishery();
                    exportDB_Goat();
                    exportDB_Household();
                    exportDB_Other();
                    exportDB_Swine();
                    exportDB_Other();
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
    private void exportDB_Owner() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_owner.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_owner",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Swine() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_swine.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_swine",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Chicken() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_chicken.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_chicken",null);

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
                        curCSV.getString(61)


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
    private void exportDB_Carabao() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_carabao.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_carabao",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Goat() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_goat.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_goat",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Other() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_other.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_other",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Fishery() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_fishery.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_fishery",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Apiary() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_apiary.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_apiary",null);
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
                        curCSV.getString(61)


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
    private void exportDB_Household() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "pvet_survey_household.csv");
        try
        {
            file.createNewFile();
            CSWriter csvWrite = new CSWriter(new FileWriter(file));
            SQLiteDatabase db = mydb.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM pvet_survey_household",null);
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
                        curCSV.getString(61)


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

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
