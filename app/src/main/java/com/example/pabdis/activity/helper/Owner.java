package com.example.pabdis.activity.helper;

public class Owner {

    String id = null;
    String ownerid = null;
    String h_full = null;
    String r_full = null;
    String contact_no = null;
    String municipality = null;
    String barangay = null;
    String house = null;
    String createdat = null;


    public Owner(String id, String ownerid, String h_full,
                 String r_full, String contact_no, String house, String municipality, String barangay,
                 String createdat) {

        super();

            this.id = id;
        this.ownerid = ownerid;
        this.h_full = h_full;
        this.r_full = r_full;
        this.contact_no = contact_no;
        this.house = house;
        this.municipality = municipality;
        this.barangay = barangay;
        this.createdat = createdat;
    }


    public String getId() {

        return id;

    }
    public void setId(String id2) {

        this.id = id2;

    }
    public String getOwnerid() {

        return ownerid;

    }
    public void setOwnerid(String ownerid) {

        this.ownerid = ownerid;

    }

    public String getH_full() {

        return h_full;

    }
    public void setH_full(String h_full) {

        this.h_full = h_full;

    }

    public String getR_full() {

        return r_full;

    }
    public void setR_full(String r_full) {

        this.r_full = r_full;

    }
    public String getContact_no() {

        return contact_no;

    }
    public void setContact_no(String contact_no) {

        this.contact_no = contact_no;

    }

    public String getMunicipality() {

        return municipality;

    }
    public void setMunicipality(String municipality) {

        this.municipality = municipality;

    }

    public String getHouse() {

        return house;

    }
    public void setHouse(String house) {

        this.house = house;

    }

    public String getBarangay() {

        return barangay;

    }
    public void setBarangay(String barangay) {

        this.barangay = barangay;

    }

    public String getCreatedat() {

        return createdat;

    }
    public void setCreatedat(String createdat) {

        this.createdat = createdat;

    }


    @Override
    public String toString() {

        return  id + " " + ownerid + " " +  h_full + " " +  r_full + " " +
                contact_no + " " + house + " " + municipality  + " "
                + barangay + " " + createdat;

    }


}
