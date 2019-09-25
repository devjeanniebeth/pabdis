package com.example.pabdis.activity.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static String DB_FILEPATH = "/data/com.example.pabdis.activity.updates/databases/database.db";


    public static final String DATABASE_NAME = "PABDIS.db";
//    FOR USER LOGIN CREDENTIALS
    public static final String TABLE_USER = "pvet_user";
    public static final String USERCOL_1 = "id";
    public static final String USERCOL_2 = "username";
    public static final String USERCOL_3 = "password";
    public static final String USERCOL_4 = "user_type";
    public static final String USERCOL_5 = "status";
    public static final String USERCOL_6 = "created_at";

//    FOR OWNER

    public static final String TABLE_OWNER = "pvet_owner";
    public static final String OWNERCOL_1 = "id";
    public static final String OWNERCOL_2 = "owner_type";
    public static final String OWNERCOL_3 = "owner_id";
    public static final String OWNERCOL_4 = "owner_info";
    public static final String OWNERCOL_5 = "r_lname";
    public static final String OWNERCOL_6 = "r_fname";
    public static final String OWNERCOL_7 = "members";
    public static final String OWNERCOL_8 = "contact_no";
    public static final String OWNERCOL_9 = "municipality";
    public static final String OWNERCOL_10 = "barangay";
    public static final String OWNERCOL_11 = "house";
    public static final String OWNERCOL_12 = "latitude";
    public static final String OWNERCOL_13 = "longitude";
    public static final String OWNERCOL_14 = "full_add";
    public static final String OWNERCOL_15 = "created_at";



    public static final String TABLE_LOGS = "pvet_logs";
    public static final String LOGSCOL1 = "id";
    public static final String LOGSCOL2 = "owner_id";
    public static final String LOGSCOL3 = "updated_at";
    public static final String LOGSCOL4 = "deleted_at";

//   FOR SURVEY

    public static final String TABLE_SURVEY1 = "pvet_survey_swine";
    public static final String SURVEYCOL_1 = "id";
    public static final String SURVEYCOL_2 = "user_id";
    public static final String SURVEYCOL_3 = "owner_id";
    public static final String SURVEYCOL_4 = "boar_n";
    public static final String SURVEYCOL_5 = "boar_u";
    public static final String SURVEYCOL_6 = "sowgit_n";
    public static final String SURVEYCOL_7 = "sowgit_u";
    public static final String SURVEYCOL_8 = "growers_n";
    public static final String SURVEYCOL_9 = "growers_u";
    public static final String SURVEYCOL_10 = "piglet_n";
    public static final String SURVEYCOL_11 = "piglet_u";
    public static final String SURVEYCOL_12 = "total";
    public static final String SURVEYCOL_13 = "sl_farm_kg";
    public static final String SURVEYCOL_14 = "sl_farm_hd";
    public static final String SURVEYCOL_15 = "sl_abb_kg";
    public static final String SURVEYCOL_16 = "sl_abb_hd";
    public static final String SURVEYCOL_17 = "total_area";
    public static final String SURVEYCOL_18 = "total_income";
    public static final String SURVEYCOL_19 = "IsVaccinated";
    public static final String SURVEYCOL_20 = "vaccination";
    public static final String SURVEYCOL_21 = "IsDewormed";
    public static final String SURVEYCOL_22 = "created_at";

    public static final String TABLE_SURVEY2 = "pvet_survey_chicken";
    public static final String SURVEY2COL_1 = "id";
    public static final String SURVEY2COL_2 = "user_id";
    public static final String SURVEY2COL_3 = "owner_id";
    public static final String SURVEY2COL_4 = "broilers";
    public static final String SURVEY2COL_5 = "layers";
    public static final String SURVEY2COL_6 = "native";
    public static final String SURVEY2COL_7 = "total";
    public static final String SURVEY2COL_8 = "production";
    public static final String SURVEY2COL_9 = "sl_farm_kg";
    public static final String SURVEY2COL_10 = "sl_farm_hd";
    public static final String SURVEY2COL_11 = "sl_abb_kg";
    public static final String SURVEY2COL_12 = "sl_abb_hd";
    public static final String SURVEY2COL_13 = "total_area";
    public static final String SURVEY2COL_14 = "total_income";
    public static final String SURVEY2COL_15 = "IsVaccinated";
    public static final String SURVEY2COL_16 = "vaccination";
    public static final String SURVEY2COL_17 = "IsDewormed";
    public static final String SURVEY2COL_18 = "created_at";

    public static final String TABLE_SURVEY3 = "pvet_survey_cattle";
    public static final String SURVEY3COL_1 = "id";
    public static final String SURVEY3COL_2 = "user_id";
    public static final String SURVEY3COL_3 = "owner_id";
    public static final String SURVEY3COL_4 = "bull_d";
    public static final String SURVEY3COL_5 = "bull_m";
    public static final String SURVEY3COL_6 = "cow_d";
    public static final String SURVEY3COL_7 = "cow_m";
    public static final String SURVEY3COL_8 = "calf_d";
    public static final String SURVEY3COL_9 = "calf_m";
    public static final String SURVEY3COL_10 = "total";
    public static final String SURVEY3COL_11 = "sl_farm_kg";
    public static final String SURVEY3COL_12 = "sl_farm_hd";
    public static final String SURVEY3COL_13 = "sl_abb_kg";
    public static final String SURVEY3COL_14 = "sl_abb_hd";
    public static final String SURVEY3COL_15 = "total_area";
    public static final String SURVEY3COL_16 = "total_income";
    public static final String SURVEY3COL_17 = "IsVaccinated";
    public static final String SURVEY3COL_18 = "vaccination";
    public static final String SURVEY3COL_19 = "IsDewormed";
    public static final String SURVEY3COL_20= "created_at";

    public static final String TABLE_SURVEY4 = "pvet_survey_carabao";
    public static final String SURVEY4COL_1 = "id";
    public static final String SURVEY4COL_2 = "user_id";
    public static final String SURVEY4COL_3 = "owner_id";
    public static final String SURVEY4COL_4 = "carabull_c";
    public static final String SURVEY4COL_5 = "carabull_n";
    public static final String SURVEY4COL_6 = "caracow_c";
    public static final String SURVEY4COL_7 = "caracow_n";
    public static final String SURVEY4COL_8 = "caracalf_c";
    public static final String SURVEY4COL_9 = "caracalf_n";
    public static final String SURVEY4COL_10 = "total";
    public static final String SURVEY4COL_11 = "sl_farm_kg";
    public static final String SURVEY4COL_12 = "sl_farm_hd";
    public static final String SURVEY4COL_13 = "sl_abb_kg";
    public static final String SURVEY4COL_14 = "sl_abb_hd";
    public static final String SURVEY4COL_15 = "total_area";
    public static final String SURVEY4COL_16 = "total_income";
    public static final String SURVEY4COL_17 = "IsVaccinated";
    public static final String SURVEY4COL_18 = "vaccination";
    public static final String SURVEY4COL_19 = "IsDewormed";
    public static final String SURVEY4COL_20 = "created_at";

    public static final String TABLE_SURVEY5 = "pvet_survey_goat";
    public static final String SURVEY5COL_1 = "id";
    public static final String SURVEY5COL_2 = "user_id";
    public static final String SURVEY5COL_3 = "owner_id";
    public static final String SURVEY5COL_4 = "buck_d";
    public static final String SURVEY5COL_5 = "buck_m";
    public static final String SURVEY5COL_6 = "doe_d";
    public static final String SURVEY5COL_7 = "doe_m";
    public static final String SURVEY5COL_8 = "kids_d";
    public static final String SURVEY5COL_9 = "kids_m";
    public static final String SURVEY5COL_10 = "total";
    public static final String SURVEY5COL_11 = "sl_farm_kg";
    public static final String SURVEY5COL_12 = "sl_farm_hd";
    public static final String SURVEY5COL_13 = "sl_abb_kg";
    public static final String SURVEY5COL_14 = "sl_abb_hd";
    public static final String SURVEY5COL_15 = "total_area";
    public static final String SURVEY5COL_16 = "total_income";
    public static final String SURVEY5COL_17 = "IsVaccinated";
    public static final String SURVEY5COL_18 = "vaccination";
    public static final String SURVEY5COL_19 = "IsDewormed";
    public static final String SURVEY5COL_20 = "created_at";

    public static final String TABLE_SURVEY6 = "pvet_survey_other";
    public static final String SURVEY6COL_1 = "id";
    public static final String SURVEY6COL_2 = "user_id";
    public static final String SURVEY6COL_3 = "owner_id";
    public static final String SURVEY6COL_4 = "sheep";
    public static final String SURVEY6COL_5 = "horse";
    public static final String SURVEY6COL_6 = "rabbit";
    public static final String SURVEY6COL_7 = "duck";
    public static final String SURVEY6COL_8 = "turkey";
    public static final String SURVEY6COL_9 = "goose";
    public static final String SURVEY6COL_10 = "total_income";
    public static final String SURVEY6COL_11 = "created_at";

    public static final String TABLE_SURVEY7= "pvet_survey_fishery";
    public static final String SURVEY7COL_1 = "id";
    public static final String SURVEY7COL_2 = "user_id";
    public static final String SURVEY7COL_3 = "owner_id";
    public static final String SURVEY7COL_4 = "total_area";
    public static final String SURVEY7COL_5 = "total_production";
    public static final String SURVEY7COL_6 = "total_income";
    public static final String SURVEY7COL_7 = "created_at";

    public static final String TABLE_SURVEY8= "pvet_survey_apiary";
    public static final String SURVEY8COL_1 = "id";
    public static final String SURVEY8COL_2 = "user_id";
    public static final String SURVEY8COL_3 = "owner_id";
    public static final String SURVEY8COL_4 = "total_area";
    public static final String SURVEY8COL_5 = "total_production";
    public static final String SURVEY8COL_6 = "total_income";
    public static final String SURVEY8COL_7 = "created_at";

    public static final String TABLE_SURVEY9= "pvet_survey_household";
    public static final String SURVEY9COL_1 = "id";
    public static final String SURVEY9COL_2 = "user_id";
    public static final String SURVEY9COL_3 = "owner_id";
    public static final String SURVEY9COL_4 = "beef";
    public static final String SURVEY9COL_5 = "carabeef";
    public static final String SURVEY9COL_6 = "pork";
    public static final String SURVEY9COL_7 = "chicken";
    public static final String SURVEY9COL_8 = "fish";
    public static final String SURVEY9COL_9 = "egg";
    public static final String SURVEY9COL_10 = "created_at";



    //   FOR PET INFO

    public static final String TABLE_VACC = "pvet_pet";
    public static final String VACCCOL_1 = "id";
    public static final String VACCCOL_2 = "imgpet";
    public static final String VACCCOL_3 = "owner_id";
    public static final String VACCCOL_4 = "petname";
    public static final String VACCCOL_5 = "species";
    public static final String VACCCOL_6 = "breed";
    public static final String VACCCOL_7 = "sex";
    public static final String VACCCOL_8 = "birthday";
    public static final String VACCCOL_9 = "color_marking";
    public static final String VACCCOL_10 = "distinguish_feature";
    public static final String VACCCOL_11 = "source";
    public static final String VACCCOL_12 = "pet_id";
    public static final String VACCCOL_13 = "status";
    public static final String VACCCOL_14 = "date_died";
    public static final String VACCCOL_15 = "created_at";

    public static final String TABLE_VACC_DATE = "pvet_pet_vaccination";
    public static final String VACC_DATE_1 = "id";
    public static final String VACC_DATE_2 = "pet_id";
    public static final String VACC_DATE_3 = "date_vaccination";
    public static final String VACC_DATE_4 = "vaccinated_by";
    public static final String VACC_DATE_5 = "created_at";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    public boolean importDatabase(String dbPath) throws IOException {

        // Close the SQLiteOpenHelper so it will commit the created empty
        // database to internal storage.
        close();
        File newDb = new File(dbPath);
        File oldDb = new File(DB_FILEPATH);
        if (newDb.exists()) {
            FileUtils.copyFile(new FileInputStream(newDb), new FileOutputStream(oldDb));
            // Access the copied database so SQLiteHelper will cache it and mark
            // it as created.
            getWritableDatabase().close();
            return true;
        }
        return false;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + USERCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERCOL_2 + " TEXT,"  + USERCOL_3 + " TEXT,"  + USERCOL_4 + " TEXT,"
                + USERCOL_5 + " TEXT, "+ USERCOL_6 + " TEXT " + ")";

        String CREATE_OWNER_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_OWNER + "("
                + OWNERCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + OWNERCOL_2 + " TEXT," + OWNERCOL_3 + " TEXT,"  + OWNERCOL_4 + " TEXT,"
                + OWNERCOL_5 + " TEXT," + OWNERCOL_6 + " TEXT," + OWNERCOL_7 + " TEXT," + OWNERCOL_8 + " TEXT,"
                + OWNERCOL_9 + " TEXT," + OWNERCOL_10 + " TEXT,"  + OWNERCOL_11 + " TEXT,"  + OWNERCOL_12 + " TEXT,"
                + OWNERCOL_13 + " TEXT,"  + OWNERCOL_14 + " TEXT,"   + OWNERCOL_15 + " TEXT" +  ")";

        String CREATE_SURVEY1_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY1 + "("
                + SURVEYCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  SURVEYCOL_3 + " TEXT,"  + SURVEYCOL_4 + " TEXT,"
                + SURVEYCOL_5 + " TEXT, " + SURVEYCOL_6 + " TEXT, " + SURVEYCOL_7 + " TEXT, " + SURVEYCOL_8 + " TEXT, " + SURVEYCOL_9 + " TEXT , "
                + SURVEYCOL_10 + " TEXT ," + SURVEYCOL_11 + " TEXT , " +SURVEYCOL_12 + " TEXT, " +SURVEYCOL_13 + " TEXT, " + SURVEYCOL_14 + " TEXT , "
                + SURVEYCOL_15 + " TEXT , " + SURVEYCOL_16 + " TEXT , " +  SURVEYCOL_17 + " TEXT , " + SURVEYCOL_18
                + " TEXT , " + SURVEYCOL_19 + " TEXT , "+ SURVEYCOL_20 + " TEXT , "+ SURVEYCOL_21 + " TEXT , " +SURVEYCOL_22 + " TEXT" +")";

        String CREATE_SURVEY2_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY2 + "("
                + SURVEY2COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  SURVEY2COL_3 + " TEXT,"  + SURVEY2COL_4 + " TEXT,"
                + SURVEY2COL_5 + " TEXT, " + SURVEY2COL_6 + " TEXT, " + SURVEY2COL_7 + " TEXT, " + SURVEY2COL_8 + " TEXT, " + SURVEY2COL_9 + " TEXT, "
                + SURVEY2COL_10 + " TEXT, " +  SURVEY2COL_11 + " TEXT, " +  SURVEY2COL_12 + " TEXT, " +  SURVEY2COL_13 + " TEXT , " + SURVEY2COL_14
                + " TEXT , " + SURVEY2COL_15 + " TEXT , " + SURVEY2COL_16 + " TEXT , "+ SURVEY2COL_17 + " TEXT , "
                +  SURVEY2COL_18 + " TEXT " +  ")";

        String CREATE_SURVEY3_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY3 + "("
                + SURVEY3COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY3COL_3 + " TEXT,"  + SURVEY3COL_4 + " TEXT,"
                + SURVEY3COL_5 + " TEXT," +  SURVEY3COL_6 + " TEXT," +  SURVEY3COL_7 + " TEXT," +  SURVEY3COL_8 + " TEXT," +  SURVEY3COL_9 + " TEXT,"
                + SURVEY3COL_10 + " TEXT," +  SURVEY3COL_11 + " TEXT," +  SURVEY3COL_12 + " TEXT," + SURVEY3COL_13
                + " TEXT," + SURVEY3COL_14  + " TEXT,"  + SURVEY3COL_15 + " TEXT," + SURVEY3COL_16 + " TEXT , "
                + SURVEY3COL_17 + " TEXT , "  + SURVEY3COL_18 + " TEXT , "  + SURVEY3COL_19 + " TEXT , "  +   SURVEY3COL_20 + " TEXT" +  ")";

        String CREATE_SURVEY4_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY4 + "("
                + SURVEY4COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY4COL_3 + " TEXT,"  + SURVEY4COL_4 + " TEXT,"
                + SURVEY4COL_5 + " TEXT, " + SURVEY4COL_6 + " TEXT, " +  SURVEY4COL_7 + " TEXT, " +  SURVEY4COL_8 + " TEXT, " +  SURVEY4COL_9 + " TEXT, "
                + SURVEY4COL_10 + " TEXT, " +  SURVEY4COL_11 + " TEXT, " +  SURVEY4COL_12 + " TEXT, " +  SURVEY4COL_13 + " TEXT, " +  SURVEY4COL_14 + " TEXT, "
                + SURVEY4COL_15  + " TEXT,"  + SURVEY4COL_16 + " TEXT," +  SURVEY4COL_17 + " TEXT, "
                + SURVEY4COL_18 + " TEXT , " + SURVEY4COL_19 + " TEXT , " +  SURVEY4COL_20 + " TEXT " + ")";

        String CREATE_SURVEY5_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY5 + "("
                + SURVEY5COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY5COL_3 + " TEXT,"  + SURVEY5COL_4 + " TEXT,"
                + SURVEY5COL_5 + " TEXT, " + SURVEY5COL_6 + " TEXT, " +  SURVEY5COL_7 + " TEXT, " +  SURVEY5COL_8 + " TEXT, " +  SURVEY5COL_9 + " TEXT, "
                + SURVEY5COL_10 + " TEXT, " +  SURVEY5COL_11 + " TEXT, " +  SURVEY5COL_12 + " TEXT, " +  SURVEY5COL_13 + " TEXT, "
                + SURVEY5COL_14  + " TEXT,"  + SURVEY5COL_15 + " TEXT,"+ SURVEY5COL_16
                + " TEXT,"  + SURVEY5COL_17 + " TEXT," + SURVEY5COL_18 + " TEXT , " + SURVEY5COL_19 + " TEXT , " + SURVEY5COL_20 + " TEXT " + ")";

        String CREATE_SURVEY6_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY6 + "("
                + SURVEY6COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY6COL_3 + " TEXT,"  + SURVEY6COL_4 + " TEXT,"
                + SURVEY6COL_5 + " TEXT, " + SURVEY6COL_6 + " TEXT, " + SURVEY6COL_7 + " TEXT, " + SURVEY6COL_8 + " TEXT, " + SURVEY6COL_9 + " TEXT, " + SURVEY6COL_10 + " TEXT, "
                + SURVEY6COL_11 + " TEXT " +" )";

        String CREATE_SURVEY7_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY7 + "("
                + SURVEY7COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY7COL_3 + " TEXT,"  + SURVEY7COL_4 + " TEXT,"
                + SURVEY7COL_5 + " TEXT, " +  SURVEY7COL_6 + " TEXT, " + SURVEY7COL_7 + " TEXT " + ")";

        String CREATE_SURVEY8_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY8 + "("
                + SURVEY8COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  SURVEY8COL_3 + " TEXT,"  + SURVEY8COL_4 + " TEXT,"
                + SURVEY8COL_5 + " TEXT, " + SURVEY8COL_6 + " TEXT, " +  SURVEY8COL_7 + " TEXT" + ")";

        String CREATE_SURVEY9_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY9 + "("
                + SURVEY9COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  SURVEY9COL_3 + " TEXT,"  + SURVEY9COL_4 + " TEXT,"
                + SURVEY9COL_5 + " TEXT, " + SURVEY9COL_6 + " TEXT, " +  SURVEY9COL_7 + " TEXT, " +  SURVEY9COL_8 + " TEXT, " +  SURVEY9COL_9 + " TEXT, "
                + SURVEY9COL_10 + " TEXT" +  ")";

        String CREATE_VACC_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_VACC + "("
                + VACCCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + VACCCOL_2 + " NONE," + VACCCOL_3 + " TEXT,"  + VACCCOL_4 + " TEXT,"
                + VACCCOL_5 + " TEXT, " + VACCCOL_6 + " TEXT, " + VACCCOL_7 + " TEXT, " + VACCCOL_8 + " TEXT, " + VACCCOL_9 + " TEXT, "
                + VACCCOL_10 + " TEXT, " + VACCCOL_11 + " TEXT, " + VACCCOL_12 + " TEXT, " + VACCCOL_13 + " TEXT, "
                + VACCCOL_15 + " TEXT" +")";

        String CREATE_VACC_DATE_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_VACC_DATE + "("
                + VACC_DATE_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + VACC_DATE_2 + " TEXT,"  + VACC_DATE_3 + " TEXT,"  + VACC_DATE_4 + " TEXT,"   + VACC_DATE_5 + " TEXT" +")";

        String CREATE_LOGS_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_LOGS + "("
                + LOGSCOL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + LOGSCOL2 + " TEXT,"  + LOGSCOL3 + " TEXT,"  + LOGSCOL4 + " TEXT" +")";


        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_OWNER_TABLE);
        db.execSQL(CREATE_SURVEY1_TABLE);
        db.execSQL(CREATE_SURVEY2_TABLE);
        db.execSQL(CREATE_SURVEY3_TABLE);
        db.execSQL(CREATE_SURVEY4_TABLE);
        db.execSQL(CREATE_SURVEY5_TABLE);
        db.execSQL(CREATE_SURVEY6_TABLE);
        db.execSQL(CREATE_SURVEY7_TABLE);
        db.execSQL(CREATE_SURVEY8_TABLE);
        db.execSQL(CREATE_SURVEY9_TABLE);
        db.execSQL(CREATE_VACC_TABLE);
        db.execSQL(CREATE_VACC_DATE_TABLE);
        db.execSQL(CREATE_LOGS_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OWNER);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY1);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY2);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY3);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY4);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY5);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY6);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY7);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY8);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY9);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_VACC);
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LOGS);
//        onCreate(db);
//
//


    }


    public boolean addOwner(String owner_id, String ownertype, String ownerinfo, String rfname, String rlname,String members, String contact,
                            String muni, String brgy,  String house,String lat, String longt, String fulladd, String createdat)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OWNERCOL_2,ownertype);
        contentValues.put(OWNERCOL_3,owner_id);
        contentValues.put(OWNERCOL_4,ownerinfo);
        contentValues.put(OWNERCOL_5,rlname);
        contentValues.put(OWNERCOL_6,rfname);
        contentValues.put(OWNERCOL_7,members);
        contentValues.put(OWNERCOL_8,contact);
        contentValues.put(OWNERCOL_9,muni);
        contentValues.put(OWNERCOL_10,brgy);
        contentValues.put(OWNERCOL_11,house);
        contentValues.put(OWNERCOL_12,lat);
        contentValues.put(OWNERCOL_13,longt);
        contentValues.put(OWNERCOL_14,fulladd);
        contentValues.put(OWNERCOL_15,createdat);
        long result = db.insert(TABLE_OWNER,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addSwine(String owner_id, String boar_n, String boar_u, String sow_n, String sow_u, String grow_n,
                             String grow_u, String wean_n, String wean_u, String total, String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd,
                             String total_area, String totl_inc,String vaccstatus,String vacctype,String deworm,String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEYCOL_3,owner_id);
        contentValues.put(SURVEYCOL_4,boar_n);
        contentValues.put(SURVEYCOL_5,boar_u);
        contentValues.put(SURVEYCOL_6,sow_n);
        contentValues.put(SURVEYCOL_7,sow_u);
        contentValues.put(SURVEYCOL_8,grow_n);
        contentValues.put(SURVEYCOL_9,grow_u);
        contentValues.put(SURVEYCOL_10,wean_n);
        contentValues.put(SURVEYCOL_11,wean_u);
        contentValues.put(SURVEYCOL_12,total);
        contentValues.put(SURVEYCOL_13,sl_f_kg);
        contentValues.put(SURVEYCOL_14,sl_f_hd);
        contentValues.put(SURVEYCOL_15,sl_a_kg);
        contentValues.put(SURVEYCOL_16,sl_a_hd);
        contentValues.put(SURVEYCOL_17,total_area);
        contentValues.put(SURVEYCOL_18,totl_inc);
        contentValues.put(SURVEYCOL_19,vaccstatus);
        contentValues.put(SURVEYCOL_20,vacctype);
        contentValues.put(SURVEYCOL_21,deworm);
        contentValues.put(SURVEYCOL_22,createdAt);
        long result = db.insert(TABLE_SURVEY1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addChicken(String owner_id, String broilers, String layers, String local, String total, String prod,
                              String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area,
                              String totl_inc,String vaccstatus,String vacctype,String deworm,String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY2COL_3,owner_id);
        contentValues.put(SURVEY2COL_4,broilers);
        contentValues.put(SURVEY2COL_5,layers);
        contentValues.put(SURVEY2COL_6,local);
        contentValues.put(SURVEY2COL_7,total);
        contentValues.put(SURVEY2COL_8,prod);
        contentValues.put(SURVEY2COL_9,sl_f_kg);
        contentValues.put(SURVEY2COL_10,sl_f_hd);
        contentValues.put(SURVEY2COL_11,sl_a_kg);
        contentValues.put(SURVEY2COL_12,sl_a_hd);
        contentValues.put(SURVEY2COL_13,total_area);
        contentValues.put(SURVEY2COL_14,totl_inc);
        contentValues.put(SURVEY2COL_15,vaccstatus);
        contentValues.put(SURVEY2COL_16,vacctype);
        contentValues.put(SURVEY2COL_17,deworm);
        contentValues.put(SURVEY2COL_18,createdAt);
        long result = db.insert(TABLE_SURVEY2,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addCattle(String owner_id, String bull_d, String bull_m, String cow_d, String cow_m, String calf_d,
                              String calf_m, String total,String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area, String totl_inc,
                             String vaccstatus,String vacctype,String deworm, String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY3COL_3,owner_id);
        contentValues.put(SURVEY3COL_4,bull_d);
        contentValues.put(SURVEY3COL_5,bull_m);
        contentValues.put(SURVEY3COL_6,cow_d);
        contentValues.put(SURVEY3COL_7,cow_m);
        contentValues.put(SURVEY3COL_8,calf_d);
        contentValues.put(SURVEY3COL_9,calf_m);
        contentValues.put(SURVEY3COL_10,total);
        contentValues.put(SURVEY3COL_11,sl_f_kg);
        contentValues.put(SURVEY3COL_12,sl_f_hd);
        contentValues.put(SURVEY3COL_13,sl_a_kg);
        contentValues.put(SURVEY3COL_14,sl_a_hd);
        contentValues.put(SURVEY3COL_15,total_area);
        contentValues.put(SURVEY3COL_16,totl_inc);
        contentValues.put(SURVEY3COL_17,vaccstatus);
        contentValues.put(SURVEY3COL_18,vacctype);
        contentValues.put(SURVEY3COL_19,deworm);
        contentValues.put(SURVEY3COL_20,createdAt);
        long result = db.insert(TABLE_SURVEY3,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addCarabao(String owner_id, String carabull_c, String carabull_n, String caracow_c, String caracow_n,
                               String caracalf_c,String caracalf_n, String total, String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area,
                              String totl_inc,String vaccstatus,String vacctype,String deworm, String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY4COL_3,owner_id);
        contentValues.put(SURVEY4COL_4,carabull_c);
        contentValues.put(SURVEY4COL_5,carabull_n);
        contentValues.put(SURVEY4COL_6,caracow_c);
        contentValues.put(SURVEY4COL_7,caracow_n);
        contentValues.put(SURVEY4COL_8,caracalf_c);
        contentValues.put(SURVEY4COL_9,caracalf_n);
        contentValues.put(SURVEY4COL_10,total);
        contentValues.put(SURVEY4COL_11,sl_f_kg);
        contentValues.put(SURVEY4COL_12,sl_f_hd);
        contentValues.put(SURVEY4COL_13,sl_a_kg);
        contentValues.put(SURVEY4COL_14,sl_a_hd);
        contentValues.put(SURVEY4COL_15,total_area);
        contentValues.put(SURVEY4COL_16,totl_inc);
        contentValues.put(SURVEY4COL_17,vaccstatus);
        contentValues.put(SURVEY4COL_18,vacctype);
        contentValues.put(SURVEY4COL_19,deworm);
        contentValues.put(SURVEY4COL_20,createdAt);
        long result = db.insert(TABLE_SURVEY4,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addGoat(String owner_id, String buck_d, String buck_m, String doe_d, String doe_m, String kids_d,
                             String kids_m,String total, String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area, String totl_inc,
                           String vaccstatus,String vacctype,String deworm, String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY5COL_3,owner_id);
        contentValues.put(SURVEY5COL_4,buck_d);
        contentValues.put(SURVEY5COL_5,buck_m);
        contentValues.put(SURVEY5COL_6,doe_d);
        contentValues.put(SURVEY5COL_7,doe_m);
        contentValues.put(SURVEY5COL_8,kids_d);
        contentValues.put(SURVEY5COL_9,kids_m);
        contentValues.put(SURVEY5COL_10,total);
        contentValues.put(SURVEY5COL_11,sl_f_kg);
        contentValues.put(SURVEY5COL_12,sl_f_hd);
        contentValues.put(SURVEY5COL_13,sl_a_kg);
        contentValues.put(SURVEY5COL_14,sl_a_hd);
        contentValues.put(SURVEY5COL_15,total_area);
        contentValues.put(SURVEY5COL_16,totl_inc);
        contentValues.put(SURVEY5COL_17,vaccstatus);
        contentValues.put(SURVEY5COL_18,vacctype);
        contentValues.put(SURVEY5COL_19,deworm);
        contentValues.put(SURVEY5COL_20,createdAt);
        long result = db.insert(TABLE_SURVEY5,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }
    public boolean addOther(String owner_id, String sheep, String horse, String rabbit, String duck,  String turkey, String goose, String total,String createdAt)
    {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY6COL_3,owner_id);
        contentValues.put(SURVEY6COL_4,sheep);
        contentValues.put(SURVEY6COL_5,horse);
        contentValues.put(SURVEY6COL_6,rabbit);
        contentValues.put(SURVEY6COL_7,duck);
        contentValues.put(SURVEY6COL_8,turkey);
        contentValues.put(SURVEY6COL_9,goose);
        contentValues.put(SURVEY6COL_10,total);
        contentValues.put(SURVEY6COL_11,createdAt);
        long result = db.insert(TABLE_SURVEY6,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addFishery(String owner_id, String total_area_f, String total_prod_f, String total_income_f, String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY7COL_3,owner_id);
        contentValues.put(SURVEY7COL_4,total_area_f);
        contentValues.put(SURVEY7COL_5,total_prod_f);
        contentValues.put(SURVEY7COL_6,total_income_f);
        contentValues.put(SURVEY7COL_7,createdAt);
        long result = db.insert(TABLE_SURVEY7,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }
    public boolean addApiary(String owner_id,
                              String total_area_a, String total_prod_a,
                              String total_income_a, String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY8COL_3,owner_id);
        contentValues.put(SURVEY8COL_4,total_area_a);
        contentValues.put(SURVEY8COL_5,total_prod_a);
        contentValues.put(SURVEY8COL_6,total_income_a);
        contentValues.put(SURVEY8COL_7,createdAt);
        long result = db.insert(TABLE_SURVEY8,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addHousehold(String owner_id,
                             String beef, String carabeef,String pork, String chicken,String fish, String egg,
                              String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY9COL_3,owner_id);
        contentValues.put(SURVEY9COL_4,beef);
        contentValues.put(SURVEY9COL_5,carabeef);
        contentValues.put(SURVEY9COL_6,pork);
        contentValues.put(SURVEY9COL_7,chicken);
        contentValues.put(SURVEY9COL_8,fish);
        contentValues.put(SURVEY9COL_9,egg);
        contentValues.put(SURVEY9COL_10,createdAt);
        long result = db.insert(TABLE_SURVEY9,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addVaccination(byte[]  imgPet,String owner_id,
                                  String petname, String species,String breed, String sex,String birthday, String colormarking, String distinctfeat,
                                  String source, String petid, String status, String createdAt )
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACCCOL_2,imgPet);
        contentValues.put(VACCCOL_3,owner_id);
        contentValues.put(VACCCOL_4,petname);
        contentValues.put(VACCCOL_5,species);
        contentValues.put(VACCCOL_6,breed);
        contentValues.put(VACCCOL_7,sex);
        contentValues.put(VACCCOL_8,birthday);
        contentValues.put(VACCCOL_9,colormarking);
        contentValues.put(VACCCOL_10,distinctfeat);
        contentValues.put(VACCCOL_11,source);
        contentValues.put(VACCCOL_12,petid);
        contentValues.put(VACCCOL_13,status);
        contentValues.put(VACCCOL_15,createdAt);
        long result = db.insert(TABLE_VACC,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public boolean addVaccinationDate(String petid, String datevacc, String vacc_by,String created_at)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACC_DATE_2,petid);
        contentValues.put(VACC_DATE_3,datevacc);
        contentValues.put(VACC_DATE_4,vacc_by);
        contentValues.put(VACC_DATE_5,created_at);
        long result = db.insert(TABLE_VACC_DATE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public static String strSeparator = "__,__";
    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
    // Getting single contact
    public int getData(String muni) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_OWNER+" WHERE "+OWNERCOL_9+" = '"+muni+"'";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }
    public int getCountPet(String ownerid, String species) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_VACC+" WHERE "+VACCCOL_3+"='"+ownerid+"' AND "+VACCCOL_5+"='"+species+"'";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }

    public int getCountAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_OWNER+"";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }

    public int getCountOwner(String ownerid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_OWNER+" WHERE "+SURVEYCOL_3+"='"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_OWNER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(SURVEYCOL_3, cursor.getString(0));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }
    public Cursor getCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_OWNER+"";
        Cursor res =  db.rawQuery(query,null);
        return res;

    }
    public String getBrgy(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT barangay from "+TABLE_OWNER+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        String count = res.getString(0);
        return count;

    }

    public String getMuni(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT municipality from "+TABLE_OWNER+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        String count = res.getString(0);
        return count;
    }
    public Cursor getUserInfo(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_OWNER+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;

    }
    public Cursor getSwine(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY1+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;

    }
    public Cursor getChicken(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY2+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;

    }
    public Cursor getCattle(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY3+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getCarabao(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY4+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getGoat(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY5+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getOther(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY6+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getFishery(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY7+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getApiary(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY8+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getHousehold(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_SURVEY9+" WHERE owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }
    public Cursor getVacc(Integer ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_VACC+" WHERE id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }


    public Cursor getVaccMain(Integer ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+TABLE_VACC_DATE+" WHERE id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);
        return res;
    }







    public boolean updateSwine(String owner_id, String boar_n, String boar_u, String sow_n, String sow_u, String grow_n,
                              String grow_u, String wean_n, String wean_u, String total, String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd,
                              String total_area, String totl_inc,String vaccstatus,String vacctype,String deworm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEYCOL_4,boar_n);
        contentValues.put(SURVEYCOL_5,boar_u);
        contentValues.put(SURVEYCOL_6,sow_n);
        contentValues.put(SURVEYCOL_7,sow_u);
        contentValues.put(SURVEYCOL_8,grow_n);
        contentValues.put(SURVEYCOL_9,grow_u);
        contentValues.put(SURVEYCOL_10,wean_n);
        contentValues.put(SURVEYCOL_11,wean_u);
        contentValues.put(SURVEYCOL_12,total);
        contentValues.put(SURVEYCOL_13,sl_f_kg);
        contentValues.put(SURVEYCOL_14,sl_f_hd);
        contentValues.put(SURVEYCOL_15,sl_a_kg);
        contentValues.put(SURVEYCOL_16,sl_a_hd);
        contentValues.put(SURVEYCOL_17,total_area);
        contentValues.put(SURVEYCOL_18,totl_inc);
        contentValues.put(SURVEYCOL_19,vaccstatus);
        contentValues.put(SURVEYCOL_20,vacctype);
        contentValues.put(SURVEYCOL_21,deworm);
        db.update(TABLE_SURVEY1, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;
    }

    public boolean updateChicken(String owner_id, String broilers, String layers, String local, String total, String prod,
                                 String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area,
                                 String totl_inc,String vaccstatus,String vacctype,String deworm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY2COL_4,broilers);
        contentValues.put(SURVEY2COL_5,layers);
        contentValues.put(SURVEY2COL_6,local);
        contentValues.put(SURVEY2COL_7,total);
        contentValues.put(SURVEY2COL_8,prod);
        contentValues.put(SURVEY2COL_9,sl_f_kg);
        contentValues.put(SURVEY2COL_10,sl_f_hd);
        contentValues.put(SURVEY2COL_11,sl_a_kg);
        contentValues.put(SURVEY2COL_12,sl_a_hd);
        contentValues.put(SURVEY2COL_13,total_area);
        contentValues.put(SURVEY2COL_14,totl_inc);
        contentValues.put(SURVEY2COL_15,vaccstatus);
        contentValues.put(SURVEY2COL_16,vacctype);
        contentValues.put(SURVEY2COL_17,deworm);
        db.update(TABLE_SURVEY2, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;
    }


    public boolean updateCattle(String owner_id, String bull_d, String bull_m, String cow_d, String cow_m, String calf_d,
                                String calf_m, String total,String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area, String totl_inc,
                                String vaccstatus,String vacctype,String deworm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY3COL_4,bull_d);
        contentValues.put(SURVEY3COL_5,bull_m);
        contentValues.put(SURVEY3COL_6,cow_d);
        contentValues.put(SURVEY3COL_7,cow_m);
        contentValues.put(SURVEY3COL_8,calf_d);
        contentValues.put(SURVEY3COL_9,calf_m);
        contentValues.put(SURVEY3COL_10,total);
        contentValues.put(SURVEY3COL_11,sl_f_kg);
        contentValues.put(SURVEY3COL_12,sl_f_hd);
        contentValues.put(SURVEY3COL_13,sl_a_kg);
        contentValues.put(SURVEY3COL_14,sl_a_hd);
        contentValues.put(SURVEY3COL_15,total_area);
        contentValues.put(SURVEY3COL_16,totl_inc);
        contentValues.put(SURVEY3COL_17,vaccstatus);
        contentValues.put(SURVEY3COL_18,vacctype);
        contentValues.put(SURVEY3COL_19,deworm);
        db.update(TABLE_SURVEY3, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateCarabao(String owner_id, String carabull_c, String carabull_n, String caracow_c, String caracow_n,
                                 String caracalf_c,String caracalf_n, String total, String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area,
                                 String totl_inc,String vaccstatus,String vacctype,String deworm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY4COL_4,carabull_c);
        contentValues.put(SURVEY4COL_5,carabull_n);
        contentValues.put(SURVEY4COL_6,caracow_c);
        contentValues.put(SURVEY4COL_7,caracow_n);
        contentValues.put(SURVEY4COL_8,caracalf_c);
        contentValues.put(SURVEY4COL_9,caracalf_n);
        contentValues.put(SURVEY4COL_10,total);
        contentValues.put(SURVEY4COL_11,sl_f_kg);
        contentValues.put(SURVEY4COL_12,sl_f_hd);
        contentValues.put(SURVEY4COL_13,sl_a_kg);
        contentValues.put(SURVEY4COL_14,sl_a_hd);
        contentValues.put(SURVEY4COL_15,total_area);
        contentValues.put(SURVEY4COL_16,totl_inc);
        contentValues.put(SURVEY4COL_17,vaccstatus);
        contentValues.put(SURVEY4COL_18,vacctype);
        contentValues.put(SURVEY4COL_19,deworm);
        db.update(TABLE_SURVEY4, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateGoat(String owner_id, String buck_d, String buck_m, String doe_d, String doe_m, String kids_d,
                              String kids_m,String total, String sl_f_kg,String sl_f_hd, String sl_a_kg,String sl_a_hd, String total_area, String totl_inc,
                              String vaccstatus,String vacctype,String deworm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY5COL_4,buck_d);
        contentValues.put(SURVEY5COL_5,buck_m);
        contentValues.put(SURVEY5COL_6,doe_d);
        contentValues.put(SURVEY5COL_7,doe_m);
        contentValues.put(SURVEY5COL_8,kids_d);
        contentValues.put(SURVEY5COL_9,kids_m);
        contentValues.put(SURVEY5COL_10,total);
        contentValues.put(SURVEY5COL_11,sl_f_kg);
        contentValues.put(SURVEY5COL_12,sl_f_hd);
        contentValues.put(SURVEY5COL_13,sl_a_kg);
        contentValues.put(SURVEY5COL_14,sl_a_hd);
        contentValues.put(SURVEY5COL_15,total_area);
        contentValues.put(SURVEY5COL_16,totl_inc);
        contentValues.put(SURVEY5COL_17,vaccstatus);
        contentValues.put(SURVEY5COL_18,vacctype);
        contentValues.put(SURVEY5COL_19,deworm);
        db.update(TABLE_SURVEY5, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateOther(String owner_id, String sheep, String horse, String rabbit, String duck,  String turkey, String goose, String total)
    {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY6COL_4,sheep);
        contentValues.put(SURVEY6COL_5,horse);
        contentValues.put(SURVEY6COL_6,rabbit);
        contentValues.put(SURVEY6COL_7,duck);
        contentValues.put(SURVEY6COL_8,turkey);
        contentValues.put(SURVEY6COL_9,goose);
        contentValues.put(SURVEY6COL_10,total);
        db.update(TABLE_SURVEY6, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateFishery(String owner_id, String total_area_f, String total_prod_f, String total_income_f)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY7COL_4,total_area_f);
        contentValues.put(SURVEY7COL_5,total_prod_f);
        contentValues.put(SURVEY7COL_6,total_income_f);
        db.update(TABLE_SURVEY7, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateApiary(String owner_id,
                                String total_area_a, String total_prod_a,
                                String total_income_a)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY8COL_4,total_area_a);
        contentValues.put(SURVEY8COL_5,total_prod_a);
        contentValues.put(SURVEY8COL_6,total_income_a);
        db.update(TABLE_SURVEY8, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateOwner(String owner_id, String ownertype, String ownerinfo, String rfname, String rlname,String members, String contact,
                               String muni, String brgy,String house)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OWNERCOL_2,ownertype);
        contentValues.put(OWNERCOL_3,owner_id);
        contentValues.put(OWNERCOL_4,ownerinfo);
        contentValues.put(OWNERCOL_5,rlname);
        contentValues.put(OWNERCOL_6,rfname);
        contentValues.put(OWNERCOL_7,members);
        contentValues.put(OWNERCOL_8,contact);
        contentValues.put(OWNERCOL_9,muni);
        contentValues.put(OWNERCOL_10,brgy);
        contentValues.put(OWNERCOL_11,house);
        db.update(TABLE_OWNER, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }


    public boolean updateHousehold(String owner_id,
                                   String beef, String carabeef,String pork, String chicken,String fish, String egg)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY9COL_4,beef);
        contentValues.put(SURVEY9COL_5,carabeef);
        contentValues.put(SURVEY9COL_6,pork);
        contentValues.put(SURVEY9COL_7,chicken);
        contentValues.put(SURVEY9COL_8,fish);
        contentValues.put(SURVEY9COL_9,egg);
        db.update(TABLE_SURVEY9, contentValues, "owner_id = ?",new String[] { owner_id });
        return true;

    }

    public boolean updateVaccination(String petname, String species,String breed, String sex,String birthday, String colormarking, String distinctfeat,
                                     String source, String petid, String status)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACCCOL_4,petname);
        contentValues.put(VACCCOL_5,species);
        contentValues.put(VACCCOL_6,breed);
        contentValues.put(VACCCOL_7,sex);
        contentValues.put(VACCCOL_8,birthday);
        contentValues.put(VACCCOL_9,colormarking);
        contentValues.put(VACCCOL_10,distinctfeat);
        contentValues.put(VACCCOL_11,source);
        contentValues.put(VACCCOL_13,status);
        db.update(TABLE_VACC, contentValues, "pet_id = ?",new String[] { petid });
        return true;



    }

    public void deleteUser(String ownerid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OWNER, "owner_id = ?",new String[] { ownerid });
        db.close();

        Log.d(TAG,"Successfully deleted user!");

    }

    public void deletePet(String ownerid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VACC, "pet_id = ?",new String[] { ownerid });
        db.close();

        Log.d(TAG,"Successfully deleted user!");

    }

    public void deleteVacc(String ownerid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VACC_DATE, "pet_id = ?",new String[] { ownerid });
        db.close();

        Log.d(TAG,"Successfully deleted pet vaccination!");

    }

    public Cursor viewInfo(String ownerid)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT o.*, " +
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
                "LEFT JOIN pvet_survey_household as hh ON hh.owner_id = o.owner_id " +
                "WHERE o.owner_id = '"+ownerid+"'";
        Cursor res =  db.rawQuery(query,null);

        return res;



    }


    }

