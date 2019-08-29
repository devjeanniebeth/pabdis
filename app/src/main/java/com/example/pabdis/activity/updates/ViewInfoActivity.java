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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        myDB = new DatabaseHelper(getApplicationContext());


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



            String boarn = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_4)) != null) ? "Boar Native: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_4)) : " Boar Native: N/A";
            String boaru = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_5)) != null) ?"Boar Upgrade: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_5)) : " Boar Upgrade: N/A";
            String sown = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_6)) != null) ? "Sow/Gilt Native: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_6)): " Sow/Gilt Native: N/A";
            String sowu = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_7)) != null) ?  "Sow/Gilt Upgrade: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_7)) : " Sow/Gilt Upgrade: N/A";
            String grown = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_8)) != null) ?  "Growers/Finishers Native: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_8)) : " Growers/Finishers Native:N/A";
            String growu = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_9)) != null) ? "Growers/Finishers Native: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_9)) : " Growers/Finishers Native:N/A";
            String pign = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_10)) != null) ? "Piglet/s Native: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_10)) : " Piglet/s Native: N/A";
            String pigu = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_11)) != null) ?  "Piglet/s Upgrade: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_11)) : " Piglet/s Upgrade: N/A";
            String sw_total_inv = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_12)) != null) ? "Total: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_12)) : " Total:N/A";
            String sw__f_kg = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_13 )) != null) ? "Slaughtered in Farm (per kg): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_13)): " Slaughtered in Farm (per kg): N/A" ;
            String sw__f_hd = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_14)) != null) ? "Slaughtered in Farm (per head): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_14)) : " Slaughtered in Farm (per head): N/A";
            String sw__a_kg = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_15)) != null) ? "Slaughtered in Slaughterhouse (per kg): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_15)) : " Slaughtered in Slaughterhouse (per kg): N/A";
            String sw__a_hd = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_16)) != null) ? "Slaughtered in Slaughterhouse (per head): " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_16)) : " Slaughtered in Slaughterhouse (per head): N/A";
            String sw_total_area = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_17)) != null) ? "Total Area: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_17)) : " Total Area: N/A";
            String sw_total_inc = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_18)) != null) ? "Total Income: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_18)) : "Total Income: N/A";
            String sw_vacc = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_19)) != null) ? rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_19)) : "N/A";
            String sw_vacctype = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_19)) != null) ? "Vaccination Type: " + rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_20)) : " N/A";
            String sw_dewormed = (rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_19)) != null) ? rs.getString(rs.getColumnIndex(DatabaseHelper.SURVEYCOL_21)) : " N/A";

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