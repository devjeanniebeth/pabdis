package com.example.pabdis.activity.updates;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

public class ViewInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    String ownerid;
    Integer position;
    DatabaseHelper myDB;
    TextView txt_owner_type,txt_owner_info,txt_r_fname,txt_r_lname,txt_members,txt_contact_no,txt_full_address,txt_full_geotagged;
    TextView txt_boar_n, txt_boar_u,txt_sowgit_n, txt_sowgit_u,txt_growers_n,txt_growers_u,txt_piglet_n,txt_piglet_u,
            txt_sw_total,txt_sw_farm_kg,txt_sw_farm_hd,txt_sw_abb_kg,txt_sw_abb_hd,txt_sw_total_area,txt_sw_total_income,txt_sw_IsVaccinated,txt_sw_vaccination,txt_sw_IsDewormed;
    TextView txt_broilers,txt_layers,txt_native,txt_ch_total,txt_ch_production,txt_ch_farm_kg,txt_ch_farm_hd,txt_ch_abb_kg,
            txt_ch_abb_hd,txt_ch_total_area,txt_ch_total_income,txt_ch_IsVaccinated,txt_ch_vaccination,txt_ch_IsDewormed;
    TextView txt_bull_d,txt_bull_m,txt_cow_d,txt_cow_m,txt_calf_d,txt_calf_m,txt_cat_total,txt_cat_farm_kg,txt_cat_farm_hd,
            txt_cat_abb_kg,txt_cat_abb_hd,txt_cat_total_area,txt_cat_total_income,txt_cat_IsVaccinated,txt_cat_vaccination,txt_cat_IsDewormed;
    TextView txt_carabull_c,txt_carabull_n,txt_caracow_c,txt_caracow_n,txt_caracalf_c,txt_caracalf_n,txt_car_total,txt_car_farm_kg,txt_car_farm_hd,
            txt_car_abb_kg,txt_car_abb_hd,txt_car_total_area,txt_car_total_income,txt_car_IsVaccinated,txt_car_vaccination,txt_car_IsDewormed;
    TextView txt_buck_d,txt_buck_m,txt_doe_d,txt_doe_m,txt_kids_d,txt_kids_m,txt_got_total,txt_got_farm_kg,txt_got_farm_hd,
            txt_got_abb_kg,txt_got_abb_hd,txt_got_total_area,txt_got_total_income,txt_got_IsVaccinated,txt_got_vaccination,txt_got_IsDewormed;
    TextView txt_sheep,txt_horse,txt_rabbit,txt_duck,txt_turkey,txt_goose,txt_other_total_inc;
    TextView total_fish_area,txt_fish_prod,txt_food_inc,txt_ap_col,txt_ap_prod,txt_ap_inc;
    TextView txt_beef,txt_carabeef,txt_pork,txt_chicken,txt_fish,txt_egg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        myDB = new DatabaseHelper(getApplicationContext());


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                position = null;
            } else {
                ownerid= extras.getString("ownerid");
                position= extras.getInt("position");

            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            position= (Integer) savedInstanceState.getSerializable("position");


        }


        txt_contact_no = findViewById(R.id.txt_contact_no);
        txt_owner_info = findViewById(R.id.txt_owner_info);
        txt_owner_type = findViewById(R.id.txt_owner_type);
        txt_r_fname = findViewById(R.id.txt_r_fname);
        txt_r_lname = findViewById(R.id.txt_r_lname);
        txt_members = findViewById(R.id.txt_members);
        txt_full_address = findViewById(R.id.txt_full_address);
        txt_full_geotagged = findViewById(R.id.txt_full_geotagged);

        txt_boar_n = findViewById(R.id.txt_boar_n);
        txt_boar_u = findViewById(R.id.txt_boar_u);
        txt_sowgit_n= findViewById(R.id.txt_sowgit_n);
        txt_sowgit_u = findViewById(R.id.txt_sowgit_u);
        txt_growers_n = findViewById(R.id.txt_growers_n);
        txt_growers_u = findViewById(R.id.txt_growers_u);
        txt_piglet_n = findViewById(R.id.txt_piglet_n);
        txt_piglet_u = findViewById(R.id.txt_piglet_u);
        txt_sw_total = findViewById(R.id.txt_sw_total);
        txt_sw_farm_kg = findViewById(R.id.txt_sw_farm_kg);
        txt_sw_farm_hd = findViewById(R.id.txt_sw_farm_hd);
        txt_sw_abb_kg = findViewById(R.id.txt_sw_abb_kg);
        txt_sw_abb_hd = findViewById(R.id.txt_sw_abb_hd);
        txt_sw_total_area = findViewById(R.id.txt_sw_total_area);
        txt_sw_total_income = findViewById(R.id.txt_sw_total_income);
        txt_sw_IsVaccinated = findViewById(R.id.txt_sw_IsVaccinated);
        txt_sw_vaccination = findViewById(R.id.txt_sw_vaccination);
        txt_sw_IsDewormed = findViewById(R.id.txt_sw_IsDewormed);

        txt_broilers = findViewById(R.id.txt_broilers);
        txt_layers = findViewById(R.id.txt_layers);
        txt_native = findViewById(R.id.txt_native);
        txt_ch_total = findViewById(R.id.txt_ch_total);
        txt_ch_production = findViewById(R.id.txt_ch_production);
        txt_ch_farm_kg = findViewById(R.id.txt_ch_farm_kg);
        txt_ch_farm_hd = findViewById(R.id.txt_ch_farm_hd);
        txt_ch_abb_kg = findViewById(R.id.txt_ch_abb_kg);
        txt_ch_abb_hd = findViewById(R.id.txt_ch_abb_hd);
        txt_ch_total_area = findViewById(R.id.txt_ch_total_area);
        txt_ch_total_income = findViewById(R.id.txt_ch_total_income);
        txt_ch_IsVaccinated = findViewById(R.id.txt_ch_IsVaccinated);
        txt_ch_vaccination = findViewById(R.id.txt_ch_vaccination);
        txt_ch_IsDewormed = findViewById(R.id.txt_ch_IsDewormed);

        txt_bull_d = findViewById(R.id.txt_bull_d);
        txt_bull_m = findViewById(R.id.txt_bull_m);
        txt_cow_d = findViewById(R.id.txt_cow_d);
        txt_cow_m = findViewById(R.id.txt_cow_m);
        txt_calf_d= findViewById(R.id.txt_calf_d);
        txt_calf_m = findViewById(R.id.txt_calf_m);
        txt_cat_total = findViewById(R.id.txt_cat_total);
        txt_cat_farm_kg = findViewById(R.id.txt_cat_farm_kg);
        txt_cat_farm_hd = findViewById(R.id.txt_cat_farm_hd);
        txt_cat_abb_kg = findViewById(R.id.txt_cat_abb_kg);
        txt_cat_abb_hd = findViewById(R.id.txt_cat_abb_hd);
        txt_cat_total_area = findViewById(R.id.txt_cat_total_area);
        txt_cat_total_income = findViewById(R.id.txt_cat_total_income);
        txt_cat_IsVaccinated = findViewById(R.id.txt_cat_IsVaccinated);
        txt_cat_vaccination = findViewById(R.id.txt_cat_vaccination);
        txt_cat_IsDewormed = findViewById(R.id.txt_cat_IsDewormed);


        txt_carabull_c = findViewById(R.id.txt_carabull_c);
        txt_carabull_n = findViewById(R.id.txt_carabull_n);
        txt_caracow_c = findViewById(R.id.txt_caracow_c);
        txt_caracow_n = findViewById(R.id.txt_caracow_n);
        txt_caracalf_c= findViewById(R.id.txt_caracalf_c);
        txt_caracalf_n = findViewById(R.id.txt_caracalf_n);
        txt_car_total = findViewById(R.id.txt_car_total);
        txt_car_farm_kg = findViewById(R.id.txt_car_farm_kg);
        txt_car_farm_hd = findViewById(R.id.txt_car_farm_hd);
        txt_car_abb_kg = findViewById(R.id.txt_car_abb_kg);
        txt_car_abb_hd = findViewById(R.id.txt_car_abb_hd);
        txt_car_total_area = findViewById(R.id.txt_car_total_area);
        txt_car_total_income = findViewById(R.id.txt_car_total_income);
        txt_car_IsVaccinated = findViewById(R.id.txt_car_IsVaccinated);
        txt_car_vaccination = findViewById(R.id.txt_car_vaccination);
        txt_car_IsDewormed = findViewById(R.id.txt_car_IsDewormed);

        txt_buck_d = findViewById(R.id.txt_buck_d);
        txt_buck_m = findViewById(R.id.txt_buck_m);
        txt_doe_d = findViewById(R.id.txt_doe_d);
        txt_doe_m = findViewById(R.id.txt_doe_m);
        txt_kids_d = findViewById(R.id.txt_kids_d);
        txt_kids_m = findViewById(R.id.txt_kids_m);
        txt_got_total = findViewById(R.id.txt_got_total);
        txt_got_farm_kg = findViewById(R.id.txt_got_farm_kg);
        txt_got_farm_hd = findViewById(R.id.txt_got_farm_hd);
        txt_got_abb_kg = findViewById(R.id.txt_got_abb_kg);
        txt_got_abb_hd = findViewById(R.id.txt_got_abb_hd);
        txt_got_total_area = findViewById(R.id.txt_got_total_area);
        txt_got_total_income = findViewById(R.id.txt_got_total_income);
        txt_got_IsVaccinated = findViewById(R.id.txt_got_IsVaccinated);
        txt_got_vaccination = findViewById(R.id.txt_got_vaccination);
        txt_got_IsDewormed = findViewById(R.id.txt_got_IsDewormed);


        txt_sheep = findViewById(R.id.txt_sheep);
        txt_horse = findViewById(R.id.txt_horse);
        txt_rabbit = findViewById(R.id.txt_rabbit);
        txt_duck = findViewById(R.id.txt_duck);
        txt_turkey = findViewById(R.id.txt_turkey);
        txt_goose = findViewById(R.id.txt_goose);
        txt_other_total_inc = findViewById(R.id.txt_other_total_inc);


        total_fish_area  = findViewById(R.id.total_fish_area);
        txt_fish_prod  = findViewById(R.id.txt_fish_prod);
        txt_food_inc = findViewById(R.id.txt_food_inc);
        txt_ap_col = findViewById(R.id.txt_ap_col);
        txt_ap_prod  = findViewById(R.id.txt_ap_prod);
        txt_ap_inc = findViewById(R.id.txt_ap_inc);


        txt_beef  = findViewById(R.id.txt_beef);
        txt_carabeef  = findViewById(R.id.txt_carabeef);
        txt_pork  = findViewById(R.id.txt_pork);
        txt_chicken  = findViewById(R.id.txt_chicken);
        txt_fish  = findViewById(R.id.txt_fish);
        txt_egg  = findViewById(R.id.txt_egg);





        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerid= null;
                position = null;
            } else {
                ownerid= extras.getString("ownerid");
                position= extras.getInt("position");

            }
        } else {
            ownerid= (String) savedInstanceState.getSerializable("ownerid");
            position= (Integer) savedInstanceState.getSerializable("position");


        }




        Cursor rs = myDB.viewInfo(ownerid);
        rs.moveToFirst();

        if(rs.getCount() > 0 )
        {
            String ownertype = "Owner Type: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_2));
            String ownerinfo = "Owner Info: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_4));
            String rlname = "Respondent First Name: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_5));
            String rfname = "Respondent Last Name: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_6));
            String members = "Members: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_7));
            String contact = "Contact No. : " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_8));
            String fulladd = " Address: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_11)) + "," + rs.getColumnIndex(DatabaseHelper.OWNERCOL_10) + "," + rs.getColumnIndex(DatabaseHelper.OWNERCOL_9);
            String geotagged = "Geolocation: " + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_12)) + "," + rs.getString(rs.getColumnIndex(DatabaseHelper.OWNERCOL_13));
            txt_contact_no.setText(contact);
            txt_owner_info.setText(ownerinfo);
            txt_owner_type.setText(ownertype);
            txt_r_fname.setText(rfname);
            txt_r_lname.setText(rlname);
            txt_members.setText(members);
            txt_full_address.setText(fulladd);
            txt_full_geotagged.setText(geotagged);



            String boarn = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_4)) != null) ? "Boar (Native): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_4)) : " Boar Native: N/A";
            String boaru = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_5)) != null) ?"Boar (Upgrade): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_5)) : " Boar Upgrade: N/A";
            String sown = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_6)) != null) ? "Sow/Gilt (Native): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_6)): " Sow/Gilt Native: N/A";
            String sowu = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_7)) != null) ?  "Sow/Gilt (Upgrade): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_7)) : " Sow/Gilt Upgrade: N/A";
            String grown = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_8)) != null) ?  "Growers/Finishers (Native): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_8)) : " Growers/Finishers Native:N/A";
            String growu = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_9)) != null) ? "Growers/Finishers (Upgrade): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_9)) : " Growers/Finishers Native:N/A";
            String pign = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_10)) != null) ? "Piglet/s (Native) : " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_10)) : " Piglet/s Native: N/A";
            String pigu = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_11)) != null) ?  "Piglet/s (Upgrade) : " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_11)) : " Piglet/s Upgrade: N/A";
            String sw_total_inv = (rs.getString(rs.getColumnIndex("sw_total")) != null) ? "Total: " + rs.getString(rs.getColumnIndex("sw_total")) : " Total:N/A";
            String sw__f_kg = (rs.getString(rs.getColumnIndex("sw_f_kg" )) != null) ? "Slaughtered in Farm (per kg): " + rs.getString(rs.getColumnIndex("sw_f_kg")): " Slaughtered in Farm (per kg): N/A" ;
            String sw__f_hd = (rs.getString(rs.getColumnIndex("sw_f_hd")) != null) ? "Slaughtered in Farm (per head): " + rs.getString(rs.getColumnIndex("sw_f_hd")) : " Slaughtered in Farm (per head): N/A";
            String sw__a_kg = (rs.getString(rs.getColumnIndex("sw_a_kg")) != null) ? "Slaughtered in Slaughterhouse (per kg): " + rs.getString(rs.getColumnIndex("sw_a_kg")) : " Slaughtered in Slaughterhouse (per kg): N/A";
            String sw__a_hd = (rs.getString(rs.getColumnIndex("sw_a_hd")) != null) ? "Slaughtered in Slaughterhouse (per head): " + rs.getString(rs.getColumnIndex("sw_a_hd")) : " Slaughtered in Slaughterhouse (per head): N/A";
            String sw_total_area = (rs.getString(rs.getColumnIndex("sw_area")) != null) ? "Total Area: " + rs.getString(rs.getColumnIndex("sw_area")) : " Total Area: N/A";
            String sw_total_inc = (rs.getString(rs.getColumnIndex("sw_income")) != null) ? "Total Income: " + rs.getString(rs.getColumnIndex("sw_income")) : "Total Income: N/A";
            String sw_vacc = (rs.getString(rs.getColumnIndex("sw_vacc")) != null) ? rs.getString(rs.getColumnIndex("sw_vacc")) : "N/A";
            String sw_vacctype = (rs.getString(rs.getColumnIndex("sw_vacctype")) != null) ? "Vaccination Type: " + rs.getString(rs.getColumnIndex("sw_vacctype")) : " N/A";
            String sw_dewormed = (rs.getString(rs.getColumnIndex("sw_deworm")) != null) ? rs.getString(rs.getColumnIndex("sw_deworm")) : " N/A";

            if(sw_vacc.equals(1) )
            {

                txt_sw_IsVaccinated.setText("Vaccinated: Yes");
                txt_sw_vaccination.setText(sw_vacctype);
                if(sw_dewormed.equals(1))
                {
                    txt_sw_IsDewormed.setText("Dewormed: Yes");
                }else{
                    txt_sw_IsDewormed.setText("Dewormed: No");
                }


            }else if(sw_vacc.equals(2)){
                txt_sw_IsDewormed.setVisibility(View.GONE);
                txt_sw_IsVaccinated.setVisibility(View.GONE);
                txt_sw_vaccination.setVisibility(View.GONE);


            }else{

                txt_sw_IsDewormed.setVisibility(View.GONE);
                txt_sw_IsVaccinated.setVisibility(View.GONE);
                txt_sw_vaccination.setVisibility(View.GONE);

            }


            txt_boar_n.setText(boarn);
            txt_boar_u.setText(boaru);
            txt_sowgit_n.setText(sown);
            txt_sowgit_u.setText(sowu);
            txt_growers_n.setText(grown);
            txt_growers_u.setText(growu);
            txt_piglet_n.setText(pign);
            txt_piglet_u.setText(pigu);
            txt_sw_total.setText(sw_total_inv);
            txt_sw_farm_kg.setText(sw__f_kg);
            txt_sw_farm_hd.setText(sw__f_hd);
            txt_sw_abb_kg.setText(sw__a_kg);
            txt_sw_abb_hd.setText(sw__a_hd);
            txt_sw_total_area.setText(sw_total_area);
            txt_sw_total_income.setText(sw_total_inc);



            /*CHICKEN */

           String broilers = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_4)) != null) ? "Broilers: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_4)) : "Broilers: N/A";
            String layers = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_5)) != null) ? "Layers: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_5)) : "Layers: N/A";
            String natives = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_6)) != null) ? "Native: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY2COL_6)) : "Native: N/A";
            String ch_total_inv = (rs.getString(rs.getColumnIndex("ch_total")) != null) ? "Total Inventory: "+ rs.getString(rs.getColumnIndex("ch_total")) : "Total Inventory: N/A";
            String ch_total_prod = (rs.getString(rs.getColumnIndex("ch_prod")) != null) ? "Total Production: " + rs.getString(rs.getColumnIndex("ch_prod")) : "Total Production: N/A";
            String ch_f_kg = (rs.getString(rs.getColumnIndex("ch_f_kg")) != null) ? "Slaughtered in Farm (per kg): " + rs.getString(rs.getColumnIndex("ch_f_kg")): "Slaughtered in Farm (per kg): N/A";
            String ch_f_hd = (rs.getString(rs.getColumnIndex("ch_f_hd")) != null) ? "Slaughtered in Farm (per head): "  + rs.getString(rs.getColumnIndex("ch_f_hd")) : "Slaughtered in Farm (per head): N/Aa ";
            String ch_a_kg = (rs.getString(rs.getColumnIndex("ch_a_kg")) != null) ? "Slaughtered in Slaughterhouse (per kg): " + rs.getString(rs.getColumnIndex("ch_a_kg")) : "Slaughtered in Slaughterhouse (per kg): N/A";
            String ch_a_hd = (rs.getString(rs.getColumnIndex("ch_a_hd")) != null) ? "Slaughtered in Slaughterhouse (per head): " + rs.getString(rs.getColumnIndex("ch_a_hd")): "Slaughtered in Slaughterhouse (per head): N/A";
            String ch_total_area = (rs.getString(rs.getColumnIndex("ch_area")) != null) ? "Total Area: " + rs.getString(rs.getColumnIndex("ch_area")): "Total Area: N/A";
            String ch_total_inc =(rs.getString(rs.getColumnIndex("ch_income")) != null) ? "Total Income: " +  rs.getString(rs.getColumnIndex("ch_income")): "Total Income: N/A";
            String ch_vacc = (rs.getString(rs.getColumnIndex("ch_vacc")) != null) ? rs.getString(rs.getColumnIndex("ch_vacc")) : "N/A";
            String ch_vacctype = (rs.getString(rs.getColumnIndex("ch_vacctype")) != null) ? "Vaccination Type: " + rs.getString(rs.getColumnIndex("ch_vacctype")) : " N/A";
            String ch_dewormed = (rs.getString(rs.getColumnIndex("ch_deworm")) != null) ? rs.getString(rs.getColumnIndex("ch_deworm")) : " N/A";


            if(ch_vacc.equals(1) )
            {

                txt_ch_IsVaccinated.setText("Vaccinated: Yes");
                txt_ch_vaccination.setText(ch_vacctype);
                if(ch_dewormed.equals(1))
                {
                    txt_ch_IsDewormed.setText("Dewormed: Yes");
                }else{
                    txt_ch_IsDewormed.setText("Dewormed: No");
                }


            }else if(ch_vacc.equals(2)){
                txt_ch_IsDewormed.setVisibility(View.GONE);
                txt_ch_IsVaccinated.setVisibility(View.GONE);
                txt_ch_vaccination.setVisibility(View.GONE);


            }else{

                txt_ch_IsDewormed.setVisibility(View.GONE);
                txt_ch_IsVaccinated.setVisibility(View.GONE);
                txt_ch_vaccination.setVisibility(View.GONE);

            }


            txt_broilers.setText(broilers);
            txt_layers.setText(layers);
            txt_native.setText(natives);
            txt_ch_total.setText(ch_total_inv);
            txt_ch_production.setText(ch_total_prod);
            txt_ch_farm_kg.setText(ch_f_kg);
            txt_ch_farm_hd.setText(ch_f_hd);
            txt_ch_abb_kg.setText(ch_a_kg);
            txt_ch_abb_hd.setText(ch_a_hd);
            txt_ch_total_area.setText(ch_total_area);
            txt_ch_total_income.setText(ch_total_inc);

            /* CATTLE */

            String bulld =  (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_4)) != null) ? "Bull (Dairy): "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_4)) : "Bull (Dairy): N/A";
            String bullm = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_5)) != null) ? "Bull (Meat): "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_5)) : "Bull (Meat): N/A";
            String cowd = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_6)) != null) ? "Cow (Dairy): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_6)) : "Cow (Dairy): N/A";
            String cowm = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_7)) != null) ? "Cow (Meat): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_7)) : "Cow (Meat): N/A";
            String calfd = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_8)) != null) ? "Calf (Dairy): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_8)) : "Calf (Dairy): N/A";
            String calfm = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_9)) != null) ? "Calf (Meat): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY3COL_9)) : "Calf (Meat): N/A";

            String cat_total_inv = (rs.getString(rs.getColumnIndex("cat_total")) != null) ? "Total Inventory: "+rs.getString(rs.getColumnIndex("cat_total")) : "Total Inventory: N/A";
            String cat_f_kg = (rs.getString(rs.getColumnIndex("cat_f_kg")) != null) ? "Slaughtered in Farm (per kg): " +rs.getString(rs.getColumnIndex("cat_f_kg")) : "Slaughtered in Farm (per kg): N/A";
            String cat_f_hd = (rs.getString(rs.getColumnIndex("cat_f_hd")) != null) ? "Slaughtered in Farm (per head): " +rs.getString(rs.getColumnIndex("cat_f_hd")) : "Slaughtered in Farm (per head): N/A";
            String cat_a_kg = (rs.getString(rs.getColumnIndex("cat_a_kg")) != null) ? "Slaughtered in Slaughterhouse (per kg): " +rs.getString(rs.getColumnIndex("cat_a_kg")) : "Slaughtered in Slaughterhouse (per kg): N/A";
            String cat_a_hd = (rs.getString(rs.getColumnIndex("cat_a_hd")) != null) ? "Slaughtered in Slaughterhouse (per head): " + rs.getString(rs.getColumnIndex("cat_a_hd")) : "Slaughtered in Slaughterhouse (per head): N/A";
            String cat_total_area = (rs.getString(rs.getColumnIndex("cat_area")) != null) ? "Total Area: " +rs.getString(rs.getColumnIndex("cat_area")) : "Total Area: N/A" ;
            String cat_total_inc = (rs.getString(rs.getColumnIndex("cat_income")) != null) ? "Total Income: " +  rs.getString(rs.getColumnIndex("cat_income")) : "Total Income: N/A";

            String cat_vacc = (rs.getString(rs.getColumnIndex("cat_vacc")) != null) ?  rs.getString(rs.getColumnIndex("cat_vacc")) : "N/A";
            String cat_vacctype = (rs.getString(rs.getColumnIndex("cat_deworm")) != null) ? rs.getString(rs.getColumnIndex("cat_deworm")): "N/A";
            String cat_dewormed = (rs.getString(rs.getColumnIndex("cat_deworm")) != null) ? rs.getString(rs.getColumnIndex("cat_deworm")) : "N/A";

            if(cat_vacc.equals(1) )
            {

                txt_cat_IsVaccinated.setText("Vaccinated: Yes");
                txt_cat_vaccination.setText(cat_vacctype);
                if(cat_dewormed.equals(1))
                {
                    txt_cat_IsDewormed.setText("Dewormed: Yes");
                }else{
                    txt_cat_IsDewormed.setText("Dewormed: No");
                }


            }else if(cat_vacc.equals(2)){
                txt_cat_IsDewormed.setVisibility(View.GONE);
                txt_cat_IsVaccinated.setVisibility(View.GONE);
                txt_cat_vaccination.setVisibility(View.GONE);


            }else{

                txt_cat_IsDewormed.setVisibility(View.GONE);
                txt_cat_IsVaccinated.setVisibility(View.GONE);
                txt_cat_vaccination.setVisibility(View.GONE);

            }

            txt_bull_d.setText(bulld);
            txt_bull_m.setText(bullm);
            txt_cow_d.setText(cowd);
            txt_cow_m.setText(cowm);
            txt_calf_d.setText(calfd);
            txt_calf_m.setText(calfm);
            txt_cat_total.setText(cat_total_inv);
            txt_cat_farm_kg.setText(cat_f_kg);
            txt_cat_farm_hd.setText(cat_f_hd);
            txt_cat_abb_kg.setText(cat_a_kg);
            txt_cat_abb_hd.setText(cat_a_hd);
            txt_cat_total_area.setText(cat_total_area);
            txt_cat_total_income.setText(cat_total_inc);


            /* CARABAO */


            String carabullc =  (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_4)) != null) ? "Carabull (Crossbreed): "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_4)) : "Carabull (Crossbreed): N/A";
            String carabulln = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_5)) != null) ? "Carabull (Native): "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_5)) : "Carabull (Native ): N/A";
            String caracowc = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_6)) != null) ? "Caracow (Crossbreed): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_6)) : "Caracow (Crossbreed): N/A";
            String caracown = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_7)) != null) ? "Caracow (Native): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_7)) : "Caracow (Native): N/A";
            String caracalfc = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_8)) != null) ? "Caracalf (Crossbreed): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_8)) : "Caracalf (Crossbreed): N/A";
            String caracalfn = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_9)) != null) ? "Caracalf (Native): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY4COL_9)) : "Caracalf (Native): N/A";

            String car_total_inv = (rs.getString(rs.getColumnIndex("car_total")) != null) ? "Total Inventory: "+rs.getString(rs.getColumnIndex("car_total")) : "Total Inventory: N/A";
            String car_f_kg = (rs.getString(rs.getColumnIndex("car_f_kg")) != null) ? "Slaughtered in Farm (per kg): " +rs.getString(rs.getColumnIndex("car_f_kg")) : "Slaughtered in Farm (per kg): N/A";
            String car_f_hd = (rs.getString(rs.getColumnIndex("car_f_hd")) != null) ? "Slaughtered in Farm (per head): " +rs.getString(rs.getColumnIndex("car_f_hd")) : "Slaughtered in Farm (per head): N/A";
            String car_a_kg = (rs.getString(rs.getColumnIndex("car_a_kg")) != null) ? "Slaughtered in Slaughterhouse (per kg): " +rs.getString(rs.getColumnIndex("car_a_kg")) : "Slaughtered in Slaughterhouse (per kg): N/A";
            String car_a_hd = (rs.getString(rs.getColumnIndex("car_a_hd")) != null) ? "Slaughtered in Slaughterhouse (per head): " + rs.getString(rs.getColumnIndex("car_a_hd")) : "Slaughtered in Slaughterhouse (per head): N/A";
            String car_total_area = (rs.getString(rs.getColumnIndex("car_area")) != null) ? "Total Area: " +rs.getString(rs.getColumnIndex("car_area")) : "Total Area: N/A" ;
            String car_total_inc = (rs.getString(rs.getColumnIndex("car_income")) != null) ? "Total Income: " +  rs.getString(rs.getColumnIndex("car_income")) : "Total Income: N/A";

            String car_vacc = (rs.getString(rs.getColumnIndex("car_vacc")) != null) ?  rs.getString(rs.getColumnIndex("car_vacc")) : "N/A";
            String car_vacctype = (rs.getString(rs.getColumnIndex("car_vacctype")) != null) ? rs.getString(rs.getColumnIndex("car_vacctype")): "N/A";
            String car_dewormed = (rs.getString(rs.getColumnIndex("car_deworm")) != null) ? rs.getString(rs.getColumnIndex("car_deworm")) : "N/A";

            if(car_vacc.equals(1) )
            {

                txt_car_IsVaccinated.setText("Vaccinated: Yes");
                txt_car_vaccination.setText(car_vacctype);
                if(car_dewormed.equals(1))
                {
                    txt_car_IsDewormed.setText("Dewormed: Yes");
                }else{
                    txt_car_IsDewormed.setText("Dewormed: No");
                }


            }else if(car_vacc.equals(2)){
                txt_car_IsDewormed.setVisibility(View.GONE);
                txt_car_IsVaccinated.setVisibility(View.GONE);
                txt_car_vaccination.setVisibility(View.GONE);


            }else{

                txt_car_IsDewormed.setVisibility(View.GONE);
                txt_car_IsVaccinated.setVisibility(View.GONE);
                txt_car_vaccination.setVisibility(View.GONE);

            }

            txt_carabull_c.setText(carabullc);
            txt_carabull_n.setText(carabulln);
            txt_caracow_c.setText(caracowc);
            txt_caracow_n.setText(caracown);
            txt_caracalf_c.setText(caracalfc);
            txt_caracalf_n.setText(caracalfn);
            txt_car_total.setText(car_total_inv);
            txt_car_farm_kg.setText(car_f_kg);
            txt_car_farm_hd.setText(car_f_hd);
            txt_car_abb_kg.setText(car_a_kg);
            txt_car_abb_hd.setText(car_a_hd);
            txt_car_total_area.setText(car_total_area);
            txt_car_total_income.setText(car_total_inc);


            /* GOAT */


            String buckd =  (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_4)) != null) ? "Buck (Dairy): "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_4)) : "Buck (Dairy): N/A";
            String buckm = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_5)) != null) ? "Buck (Meat): "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_5)) : "Buck (Meat ): N/A";
            String doed = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_6)) != null) ? "Doe (Dairy): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_6)) : "Doe (Dairy): N/A";
            String doem = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_7)) != null) ? "Doe (Meat): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_7)) : "Doe (Meat): N/A";
            String kidsd = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_8)) != null) ? "Kids (Dairy): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_8)) : "Kids (Dairy): N/A";
            String kidsm = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_9)) != null) ? "Kids (Meat): "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY5COL_9)) : "Kids (Meat): N/A";

            String got_total_inv = (rs.getString(rs.getColumnIndex("got_total")) != null) ? "Total Inventory: "+rs.getString(rs.getColumnIndex("got_total")) : "Total Inventory: N/A";
            String got_f_kg = (rs.getString(rs.getColumnIndex("got_f_kg")) != null) ? "Slaughtered in Farm (per kg): " +rs.getString(rs.getColumnIndex("got_f_kg")) : "Slaughtered in Farm (per kg): N/A";
            String got_f_hd = (rs.getString(rs.getColumnIndex("got_f_hd")) != null) ? "Slaughtered in Farm (per head): " +rs.getString(rs.getColumnIndex("got_f_hd")) : "Slaughtered in Farm (per head): N/A";
            String got_a_kg = (rs.getString(rs.getColumnIndex("got_a_kg")) != null) ? "Slaughtered in Slaughterhouse (per kg): " +rs.getString(rs.getColumnIndex("got_a_kg")) : "Slaughtered in Slaughterhouse (per kg): N/A";
            String got_a_hd = (rs.getString(rs.getColumnIndex("got_a_hd")) != null) ? "Slaughtered in Slaughterhouse (per head): " + rs.getString(rs.getColumnIndex("got_a_hd")) : "Slaughtered in Slaughterhouse (per head): N/A";
            String got_total_area = (rs.getString(rs.getColumnIndex("got_area")) != null) ? "Total Area: " +rs.getString(rs.getColumnIndex("got_area")) : "Total Area: N/A" ;
            String got_total_inc = (rs.getString(rs.getColumnIndex("got_income")) != null) ? "Total Income: " +  rs.getString(rs.getColumnIndex("got_income")) : "Total Income: N/A";

            String got_vacc = (rs.getString(rs.getColumnIndex("got_vacc")) != null) ?  rs.getString(rs.getColumnIndex("got_vacc")) : "N/A";
            String got_vacctype = (rs.getString(rs.getColumnIndex("got_vacctype")) != null) ? rs.getString(rs.getColumnIndex("got_vacctype")): "N/A";
            String got_dewormed = (rs.getString(rs.getColumnIndex("got_deworm")) != null) ? rs.getString(rs.getColumnIndex("got_deworm")) : "N/A";

            if(got_vacc.equals(1) )
            {

                txt_got_IsVaccinated.setText("Vaccinated: Yes");
                txt_got_vaccination.setText(got_vacctype);
                if(got_dewormed.equals(1))
                {
                    txt_got_IsDewormed.setText("Dewormed: Yes");
                }else{
                    txt_got_IsDewormed.setText("Dewormed: No");
                }


            }else if(got_vacc.equals(2)){
                txt_got_IsDewormed.setVisibility(View.GONE);
                txt_got_IsVaccinated.setVisibility(View.GONE);
                txt_got_vaccination.setVisibility(View.GONE);


            }else{

                txt_got_IsDewormed.setVisibility(View.GONE);
                txt_got_IsVaccinated.setVisibility(View.GONE);
                txt_got_vaccination.setVisibility(View.GONE);

            }

            txt_buck_d.setText(buckd);
            txt_buck_m.setText(buckm);
            txt_doe_d.setText(doed);
            txt_doe_m.setText(doem);
            txt_kids_d.setText(kidsd);
            txt_kids_m.setText(kidsm);
            txt_got_total.setText(got_total_inv);
            txt_got_farm_kg.setText(got_f_kg);
            txt_got_farm_hd.setText(got_f_hd);
            txt_got_abb_kg.setText(got_a_kg);
            txt_got_abb_hd.setText(got_a_hd);
            txt_got_total_area.setText(got_total_area);
            txt_got_total_income.setText(got_total_inc);


            String sheep =  (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_4)) != null) ? "Sheep: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_4)) : "Sheep: N/A";
            String horse = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_5)) != null) ? "Horse: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_5)) : "Horse: N/A";
            String rabbit = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_6)) != null) ? "Rabbit: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_6)) : "Rabbit: N/A";
            String duck = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_7)) != null) ? "Duck: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_7)) : "Duck: N/A";
            String turkey = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_8)) != null) ? "Turkey: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_8)) : "Turkey: N/A";
            String goose = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_9)) != null) ? "Goose: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY6COL_9)) : "Goose: N/A";
            String total_other_inc = (rs.getString(rs.getColumnIndex("oth_income")) != null) ? "Kids (Dairy): "+rs.getString(rs.getColumnIndex("oth_income")) : "Kids (Dairy): N/A";

            txt_sheep.setText(sheep);
            txt_horse.setText(horse);
            txt_rabbit.setText(rabbit);
            txt_duck.setText(duck);
            txt_turkey.setText(turkey);
            txt_goose.setText(goose);
            txt_other_total_inc.setText(total_other_inc);


            String total_f_area =  (rs.getString(rs.getColumnIndex("f_area")) != null) ? "Total Area: "+ rs.getString(rs.getColumnIndex("f_area")) : "Total Area: N/A";
            String fish_prod = (rs.getString(rs.getColumnIndex("f_prod")) != null) ? "Total Production in kg: "+ rs.getString(rs.getColumnIndex("f_prod")) : "Total Production in kg: N/A";
            String food_inc = (rs.getString(rs.getColumnIndex("f_income")) != null) ? "Income from Fishery: "+rs.getString(rs.getColumnIndex("f_income")) : "Income from Fishery: N/A";


            String ap_col = (rs.getString(rs.getColumnIndex("ap_col")) != null) ? "Number of Colonies: "+rs.getString(rs.getColumnIndex("ap_col")) : "Number of Colonies: N/A";
            String ap_prod = (rs.getString(rs.getColumnIndex("ap_prod")) != null) ? "Total Production of HONEY in kg: "+rs.getString(rs.getColumnIndex("ap_prod")) : "Total Production of HONEY in kg: N/A";
            String ap_inc = (rs.getString(rs.getColumnIndex("ap_income")) != null) ? "Income from Apiary: "+rs.getString(rs.getColumnIndex("ap_income")) : "Income from Apiary: N/A";


            total_fish_area.setText(total_f_area);
            txt_fish_prod.setText(fish_prod);
            txt_food_inc.setText(food_inc);
            txt_ap_col.setText(ap_col);
            txt_ap_prod.setText(ap_prod);
            txt_ap_inc.setText(ap_inc);

            String beef =  (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_4)) != null) ? "Beef: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_4)) : "Beef: N/A";
            String carabeef = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_5)) != null) ? "Carabeef: "+ rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_5)) : "Carabeef: N/A";
            String pork = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_6)) != null) ? "Pork: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_6)) : "Pork: N/A";
            String chicken = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_7)) != null) ? "Chicken: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_7)) : "Chicken: N/A";
            String fish = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_8)) != null) ? "Fish: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_8)) : "Fish: N/A";
            String egg = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_9)) != null) ? "Egg: "+rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEY9COL_9)) : "Egg: N/A";



            txt_beef.setText(beef);
            txt_carabeef.setText(carabeef);
            txt_pork.setText(pork);
            txt_chicken.setText(chicken);
            txt_fish.setText(fish);
            txt_egg.setText(egg);


















        }










    }
    @Override
    public void onBackPressed() {
        if(position != null)
        {
            Intent i = new Intent(ViewInfoActivity.this, OwnerActivity.class);
            i.putExtra("pos", position);
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
