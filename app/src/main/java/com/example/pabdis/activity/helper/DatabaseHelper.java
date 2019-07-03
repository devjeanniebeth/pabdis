package com.example.pabdis.activity.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

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
    public static final String OWNERCOL_4 = "owner_info ";
    public static final String OWNERCOL_5 = "r_lname";
    public static final String OWNERCOL_6 = "r_fname";
    public static final String OWNERCOL_7 = "contact_no";
    public static final String OWNERCOL_8 = "municipality";
    public static final String OWNERCOL_9 = "barangay";
    public static final String OWNERCOL_10 = "house";
    public static final String OWNERCOL_11 = "latitude";
    public static final String OWNERCOL_12 = "longitude";
    public static final String OWNERCOL_13 = "full_add";
    public static final String OWNERCOL_14 = "created_at";

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
    public static final String SURVEYCOL_13 = "slaughtered_farm";
    public static final String SURVEYCOL_14 = "slaughtered_abattoir";
    public static final String SURVEYCOL_15 = "total_area";
    public static final String SURVEYCOL_16 = "total_income";
    public static final String SURVEYCOL_18 = "IsVaccinated";
    public static final String SURVEYCOL_19 = "vaccination";
    public static final String SURVEYCOL_20 = "IsDewormed";
    public static final String SURVEYCOL_17 = "created_at";

    public static final String TABLE_SURVEY2 = "pvet_survey_chicken";
    public static final String SURVEY2COL_1 = "id";
    public static final String SURVEY2COL_2 = "user_id";
    public static final String SURVEY2COL_3 = "owner_id";
    public static final String SURVEY2COL_4 = "broilers";
    public static final String SURVEY2COL_5 = "layers";
    public static final String SURVEY2COL_6 = "native";
    public static final String SURVEY2COL_7 = "total";
    public static final String SURVEY2COL_8 = "production";
    public static final String SURVEY2COL_9 = "slaughtered_farm";
    public static final String SURVEY2COL_10 = "slaughtered_abattoir";
    public static final String SURVEY2COL_11 = "total_area";
    public static final String SURVEY2COL_12 = "total_income";
    public static final String SURVEY2COL_14 = "IsVaccinated";
    public static final String SURVEY2COL_15 = "vaccination";
    public static final String SURVEY2COL_16 = "IsDewormed";
    public static final String SURVEY2COL_13 = "created_at";

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
    public static final String SURVEY3COL_10 = "slaughtered_farm";
    public static final String SURVEY3COL_11 = "slaughtered_abattoir";
    public static final String SURVEY3COL_12 = "total_area";
    public static final String SURVEY3COL_13 = "total_income";
    public static final String SURVEY3COL_15 = "IsVaccinated";
    public static final String SURVEY3COL_16 = "vaccination";
    public static final String SURVEY3COL_17 = "IsDewormed";
    public static final String SURVEY3COL_14 = "created_at";

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
    public static final String SURVEY4COL_10 = "slaughtered_farm";
    public static final String SURVEY4COL_11 = "slaughtered_abattoir";
    public static final String SURVEY4COL_12 = "total_area";
    public static final String SURVEY4COL_13 = "total_income";
    public static final String SURVEY4COL_15 = "IsVaccinated";
    public static final String SURVEY4COL_16 = "vaccination";
    public static final String SURVEY4COL_17 = "IsDewormed";
    public static final String SURVEY4COL_14 = "created_at";

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
    public static final String SURVEY5COL_10 = "slaughtered_farm";
    public static final String SURVEY5COL_11 = "slaughtered_abattoir";
    public static final String SURVEY5COL_12 = "total_area";
    public static final String SURVEY5COL_13 = "total_income";
    public static final String SURVEY5COL_15 = "IsVaccinated";
    public static final String SURVEY5COL_16 = "vaccination";
    public static final String SURVEY5COL_17 = "IsDewormed";
    public static final String SURVEY5COL_14 = "created_at";

    public static final String TABLE_SURVEY6 = "pvet_survey_goat";
    public static final String SURVEY6COL_1 = "id";
    public static final String SURVEY6COL_2 = "user_id";
    public static final String SURVEY6COL_3 = "owner_id";
    public static final String SURVEY6COL_4 = "sheep";
    public static final String SURVEY6COL_5 = "horse";
    public static final String SURVEY6COL_6 = "rabbit";
    public static final String SURVEY6COL_7 = "duck";
    public static final String SURVEY6COL_8 = "pigeon";
    public static final String SURVEY6COL_9 = "quail";
    public static final String SURVEY6COL_10 = "turkey";
    public static final String SURVEY6COL_11 = "others";
    public static final String SURVEY6COL_12 = "total_income";
    public static final String SURVEY6COL_14 = "IsDewormed";
    public static final String SURVEY6COL_13 = "created_at";

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
    public static final String VACCCOL_2 = "user_id";
    public static final String VACCCOL_3 = "owner_id";
    public static final String VACCCOL_4 = "petname";
    public static final String VACCCOL_5 = "species";
    public static final String VACCCOL_6 = "breed";
    public static final String VACCCOL_7 = "sex";
    public static final String VACCCOL_8 = "birthday";
    public static final String VACCCOL_9 = "color_marking";
    public static final String VACCCOL_10 = "Pet_ID";
    public static final String VACCCOL_11 = "created_at";

    public static final String TABLE_VACC_DATE = "pvet_pet_vaccination";
    public static final String VACC_DATE_1 = "id";
    public static final String VACC_DATE_2 = "pet_id";
    public static final String VACC_DATE_3 = "date_vaccination";
    public static final String VACC_DATE_4 = "created_at";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + USERCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + USERCOL_2 + " TEXT,"  + USERCOL_3 + " TEXT,"  + USERCOL_4 + " TEXT,"
                + USERCOL_5 + " TEXT, "+ USERCOL_6 + " TEXT " + ")";

        String CREATE_OWNER_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_OWNER + "("
                + OWNERCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + OWNERCOL_2 + " TEXT," + OWNERCOL_3 + " TEXT,"  + OWNERCOL_4 + " TEXT,"
                + OWNERCOL_5 + " TEXT," + OWNERCOL_6 + " TEXT," + OWNERCOL_7 + " TEXT," + OWNERCOL_8 + " TEXT,"
                + OWNERCOL_9 + " TEXT," + OWNERCOL_10 + " TEXT,"  + OWNERCOL_11 + " TEXT,"  + OWNERCOL_12 + " TEXT,"  + OWNERCOL_13 + " TEXT,"  + OWNERCOL_14 + " TEXT " +  ")";

        String CREATE_SURVEY1_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY1 + "("
                + SURVEYCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  SURVEYCOL_3 + " TEXT,"  + SURVEYCOL_4 + " TEXT,"
                + SURVEYCOL_5 + " TEXT, " + SURVEYCOL_6 + " TEXT, " + SURVEYCOL_7 + " TEXT, " + SURVEYCOL_8 + " TEXT, " + SURVEYCOL_9 + " TEXT , "
                + SURVEYCOL_10 + " TEXT ," + SURVEYCOL_11 + " TEXT , " +SURVEYCOL_12 + " TEXT, " +SURVEYCOL_13 + " TEXT, " + SURVEYCOL_14 + " TEXT , "
                + SURVEYCOL_15 + " TEXT , " + SURVEYCOL_16 + " TEXT , " +  SURVEYCOL_18 + " TEXT , " + SURVEYCOL_19 + " TEXT , " + SURVEYCOL_20 + " TEXT , " +SURVEYCOL_17 + " TEXT" +")";


        String CREATE_SURVEY2_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY2 + "("
                + SURVEY2COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  SURVEY2COL_3 + " TEXT,"  + SURVEY2COL_4 + " TEXT,"
                + SURVEY2COL_5 + " TEXT, " + SURVEY2COL_6 + " TEXT, " + SURVEY2COL_7 + " TEXT, " + SURVEY2COL_8 + " TEXT, " + SURVEY2COL_9 + " TEXT, "
                + SURVEY2COL_10 + " TEXT, " +  SURVEY2COL_11 + " TEXT, " +  SURVEY2COL_12 + " TEXT, " +  SURVEY2COL_14 + " TEXT , " + SURVEY2COL_15
                + " TEXT , " + SURVEY2COL_16 + " TEXT , "  +  SURVEY2COL_13 + " TEXT " +  ")";

        String CREATE_SURVEY3_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY3 + "("
                + SURVEY3COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY3COL_3 + " TEXT,"  + SURVEY3COL_4 + " TEXT,"
                + SURVEY3COL_5 + " TEXT," +  SURVEY3COL_6 + " TEXT," +  SURVEY3COL_7 + " TEXT," +  SURVEY3COL_8 + " TEXT," +  SURVEY3COL_9 + " TEXT,"
                + SURVEY3COL_10 + " TEXT," +  SURVEY3COL_11 + " TEXT," +  SURVEY3COL_12 + " TEXT," + SURVEY3COL_13
                + " TEXT," + SURVEY3COL_15  + " TEXT,"  + SURVEY3COL_16 + " TEXT," + SURVEY3COL_17 + " TEXT , " +  SURVEY3COL_14 + " TEXT" +  ")";

        String CREATE_SURVEY4_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY4 + "("
                + SURVEY4COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY4COL_3 + " TEXT,"  + SURVEY4COL_4 + " TEXT,"
                + SURVEY4COL_5 + " TEXT, " + SURVEY4COL_6 + " TEXT, " +  SURVEY4COL_7 + " TEXT, " +  SURVEY4COL_8 + " TEXT, " +  SURVEY4COL_9 + " TEXT, "
                + SURVEY4COL_10 + " TEXT, " +  SURVEY4COL_11 + " TEXT, " +  SURVEY4COL_12 + " TEXT, " +  SURVEY4COL_13 + " TEXT, "
                + SURVEY4COL_15  + " TEXT,"  + SURVEY4COL_16 + " TEXT," + SURVEY4COL_17 + " TEXT , " +  SURVEY4COL_14 + " TEXT " + ")";

        String CREATE_SURVEY5_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY5 + "("
                + SURVEY5COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY5COL_3 + " TEXT,"  + SURVEY5COL_4 + " TEXT,"
                + SURVEY5COL_5 + " TEXT, " + SURVEY5COL_6 + " TEXT, " +  SURVEY5COL_7 + " TEXT, " +  SURVEY5COL_8 + " TEXT, " +  SURVEY5COL_9 + " TEXT, "
                + SURVEY5COL_10 + " TEXT, " +  SURVEY5COL_11 + " TEXT, " +  SURVEY5COL_12 + " TEXT, " +  SURVEY5COL_13 + " TEXT, "
                + SURVEY5COL_15  + " TEXT,"  + SURVEY5COL_16 + " TEXT," + SURVEY5COL_17 + " TEXT , " + SURVEY5COL_14 + " TEXT " + ")";

        String CREATE_SURVEY6_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_SURVEY6 + "("
                + SURVEY6COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + SURVEY6COL_3 + " TEXT,"  + SURVEY6COL_4 + " TEXT,"
                + SURVEY6COL_5 + " TEXT, " + SURVEY6COL_6 + " TEXT, " + SURVEY6COL_7 + " TEXT, " + SURVEY6COL_8 + " TEXT, " + SURVEY6COL_9 + " TEXT, "
                + SURVEY6COL_10 + " TEXT, " + SURVEY6COL_11 + " TEXT, " + SURVEY6COL_12 + " TEXT, " + SURVEY6COL_14 + " TEXT , " + SURVEY6COL_13 + " TEXT " +" )";

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
                + VACCCOL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +  VACCCOL_3 + " TEXT,"  + VACCCOL_4 + " TEXT,"
                + VACCCOL_5 + " TEXT, " + VACCCOL_6 + " TEXT, " + VACCCOL_7 + " TEXT, " + VACCCOL_8 + " TEXT, " + VACCCOL_9 + " TEXT, " + VACCCOL_10 + " TEXT, "
                + VACCCOL_11 + " TEXT" +")";

        String CREATE_VACC_DATE_TABLE = " CREATE TABLE IF NOT EXISTS " + TABLE_VACC_DATE + "("
                + VACC_DATE_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + VACC_DATE_2 + " TEXT,"  + VACC_DATE_3 + " TEXT,"  + VACC_DATE_4 + " TEXT" +")";


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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OWNER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY4);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY5);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY6);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY7);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY8);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SURVEY9);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_VACC);
        onCreate(db);

    }


    public boolean addOwner(String owner_id, String ownertype, String ownerinfo, String rfname, String rlname, String contact, String house,
                            String muni, String brgy, String lat, String longt, String fulladd, String createdat)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OWNERCOL_2,ownertype);
        contentValues.put(OWNERCOL_3,owner_id);
        contentValues.put(OWNERCOL_4,ownerinfo);
        contentValues.put(OWNERCOL_5,rlname);
        contentValues.put(OWNERCOL_6,rfname);
        contentValues.put(OWNERCOL_7,contact);
        contentValues.put(OWNERCOL_8,muni);
        contentValues.put(OWNERCOL_9,brgy);
        contentValues.put(OWNERCOL_10,house);
        contentValues.put(OWNERCOL_11,lat);
        contentValues.put(OWNERCOL_12,longt);
        contentValues.put(OWNERCOL_13,fulladd);
        contentValues.put(OWNERCOL_14,createdat);
        long result = db.insert(TABLE_OWNER,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addSwine(String owner_id, String boar_n, String boar_u, String sow_n, String sow_u, String grow_n,
                             String grow_u, String wean_n, String wean_u, String total, String sl_f, String sl_a,
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
        contentValues.put(SURVEYCOL_13,sl_f);
        contentValues.put(SURVEYCOL_14,sl_a);
        contentValues.put(SURVEYCOL_15,total_area);
        contentValues.put(SURVEYCOL_16,totl_inc);
        contentValues.put(SURVEYCOL_18,vaccstatus);
        contentValues.put(SURVEYCOL_19,vacctype);
        contentValues.put(SURVEYCOL_20,deworm);
        contentValues.put(SURVEYCOL_17,createdAt);
        long result = db.insert(TABLE_SURVEY1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addChicken(String owner_id, String broilers, String layers, String local, String total, String prod,
                              String sl_f, String sl_a, String total_area, String totl_inc,String vaccstatus,String vacctype,String deworm,String createdAt)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY2COL_3,owner_id);
        contentValues.put(SURVEY2COL_4,broilers);
        contentValues.put(SURVEY2COL_5,layers);
        contentValues.put(SURVEY2COL_6,local);
        contentValues.put(SURVEY2COL_7,total);
        contentValues.put(SURVEY2COL_8,prod);
        contentValues.put(SURVEY2COL_9,sl_f);
        contentValues.put(SURVEY2COL_10,sl_a);
        contentValues.put(SURVEY2COL_11,total_area);
        contentValues.put(SURVEY2COL_12,totl_inc);
        contentValues.put(SURVEY2COL_14,vaccstatus);
        contentValues.put(SURVEY2COL_15,vacctype);
        contentValues.put(SURVEY2COL_16,deworm);
        contentValues.put(SURVEY2COL_13,createdAt);
        long result = db.insert(TABLE_SURVEY2,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addCattle(String owner_id, String bull_d, String bull_m, String cow_d, String cow_m, String calf_d,
                              String calf_m, String sl_f, String sl_a, String total_area, String totl_inc,
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
        contentValues.put(SURVEY3COL_10,sl_f);
        contentValues.put(SURVEY3COL_11,sl_a);
        contentValues.put(SURVEY3COL_12,total_area);
        contentValues.put(SURVEY3COL_13,totl_inc);
        contentValues.put(SURVEY3COL_15,vaccstatus);
        contentValues.put(SURVEY3COL_16,vacctype);
        contentValues.put(SURVEY3COL_17,deworm);
        contentValues.put(SURVEY3COL_14,createdAt);
        long result = db.insert(TABLE_SURVEY3,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addCarabao(String owner_id, String carabull_c, String carabull_n, String caracow_c, String caracow_n,
                               String caracalf_c,
                               String caracalf_n, String sl_f, String sl_a, String total_area,
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
        contentValues.put(SURVEY4COL_10,sl_f);
        contentValues.put(SURVEY4COL_11,sl_a);
        contentValues.put(SURVEY4COL_12,total_area);
        contentValues.put(SURVEY4COL_13,totl_inc);
        contentValues.put(SURVEY4COL_15,vaccstatus);
        contentValues.put(SURVEY4COL_16,vacctype);
        contentValues.put(SURVEY4COL_17,deworm);
        contentValues.put(SURVEY4COL_14,createdAt);
        long result = db.insert(TABLE_SURVEY4,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addGoat(String owner_id, String buck_d, String buck_m, String doe_d, String doe_m, String kids_d,
                             String kids_m, String sl_f, String sl_a, String total_area, String totl_inc,
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
        contentValues.put(SURVEY5COL_10,sl_f);
        contentValues.put(SURVEY5COL_11,sl_a);
        contentValues.put(SURVEY5COL_12,total_area);
        contentValues.put(SURVEY5COL_13,totl_inc);
        contentValues.put(SURVEY5COL_15,vaccstatus);
        contentValues.put(SURVEY5COL_16,vacctype);
        contentValues.put(SURVEY5COL_17,deworm);
        contentValues.put(SURVEY5COL_14,createdAt);
        long result = db.insert(TABLE_SURVEY5,null ,contentValues);
        if(result == -1)

            return false;
        else
            return true;
    }

    public boolean addOther(String owner_id, String sheep, String horse, String rabbit, String duck, String pigeon,
                            String quail, String turkey,  String other, String total,String createdAt)
    {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY6COL_3,owner_id);
        contentValues.put(SURVEY6COL_4,sheep);
        contentValues.put(SURVEY6COL_5,horse);
        contentValues.put(SURVEY6COL_6,rabbit);
        contentValues.put(SURVEY6COL_7,duck);
        contentValues.put(SURVEY6COL_8,pigeon);
        contentValues.put(SURVEY6COL_9,quail);
        contentValues.put(SURVEY6COL_10,turkey);
        contentValues.put(SURVEY6COL_11, other);
        contentValues.put(SURVEY6COL_12,total);
        contentValues.put(SURVEY6COL_13,createdAt);
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
        contentValues.put(SURVEY9COL_6,chicken);
        contentValues.put(SURVEY9COL_6,fish);
        contentValues.put(SURVEY9COL_6,egg);
        contentValues.put(SURVEY9COL_7,createdAt);
        long result = db.insert(TABLE_SURVEY9,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean addVaccination(String owner_id,
                                  String petname, String species,String breed, String sex,String birthday, String colormarking,
                                  String petid, String createdAt )
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACCCOL_3,owner_id);
        contentValues.put(VACCCOL_4,petname);
        contentValues.put(VACCCOL_5,species);
        contentValues.put(VACCCOL_6,breed);
        contentValues.put(VACCCOL_7,sex);
        contentValues.put(VACCCOL_8,birthday);
        contentValues.put(VACCCOL_9,colormarking);
        contentValues.put(VACCCOL_10,createdAt);
        long result = db.insert(TABLE_VACC,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    public boolean addVaccinationDate(String petid, String datevacc, String created_at)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(VACC_DATE_2,petid);
        contentValues.put(VACC_DATE_3,datevacc);
        contentValues.put(VACC_DATE_4,created_at);
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

    public int getCountPet(String ownerid, String breed) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_VACC+" WHERE "+VACCCOL_3+"='"+ownerid+" AND "+VACCCOL_6+"="+breed+"";
        Cursor res =  db.rawQuery(query,null);
        res.moveToFirst();
        int count = res.getInt(0);
        return count;
    }


    public Cursor getCount()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) from "+TABLE_OWNER+"";
        Cursor res =  db.rawQuery(query,null);
        return res;

    }
}
