package com.example.pabdis.activity.updates;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pabdis.R;
import com.example.pabdis.activity.helper.DatabaseHelper;

public class ViewInfoActivity extends AppCompatActivity {


    String ownerid;
    Integer position;
    DatabaseHelper myDB;
    TextView txt_owner_type,txt_owner_info,txt_r_fname,txt_r_lname,txt_members,txt_contact_no,txt_full_address,txt_full_geotagged;
    TextView txt_boar_n, txt_boar_u,txt_growers_n,txt_growers_u,txt_piglet_n,txt_piglet_u,
            txt_sw_total,txt_sw_farm_kg,txt_sw_farm_hd,txt_sw_abb_kg,txt_sw_abb_hd,txt_sw_total_area,txt_sw_total_income,txt_sw_IsVaccinated,txt_sw_vaccination;
    TextView txt_broilers,txt_layers,txt_native,txt_ch_total,txt_ch_production,txt_ch_farm_kg,txt_ch_ch_farm_hd,txt_ch_abb_kg,
            txt_ch_abb_hd,txt_ch_total_area,txt_ch_total_income,txt_ch_IsVaccinated,txt_ch_vaccination,txt_ch_IsDewormed;
    TextView txt_bull_d,txt_bull_m,txt_cow_d,txt_cow_m,txt_calf_d,txt_calf_m,txt_cat_total,txt_cat_farm_kg,txt_cat_farm_hd,
            txt_cat_abb_kg,txt_cat_abb_hd,txt_cat_total_area,txt_cat_total_income,txt_cat_IsVaccinated,txt_cat_vaccination,txt_cat_IsDewormed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

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

        if(rs.getCount() > 0)
        {

        }




    }




}
