package com.example.pabdis.activity.helper;

public class Owner {

    String id = null;
    String ownerid = null;
    String owner_info = null;
    String r_full = null;
    String contact_no = null;
    String municipality = null;
    String barangay = null;
    String house = null;
    String createdat = null;
    String latitude = null;
    String longitude = null;


    public Owner(String id, String ownerid,
                 String r_full, String contact_no, String house, String municipality, String barangay, String latitude, String longitude,
                 String createdat) {

        super();

        this.id = id;
        this.ownerid = ownerid;
//        this.owner_info =
        this.r_full = r_full;
        this.contact_no = contact_no;
        this.house = house;
        this.municipality = municipality;
        this.barangay = barangay;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getOwner_info() {

        return owner_info;

    }
    public void setOwner_info(String owner_info) {

        this.owner_info = owner_info;

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

    public String getLatitude(){
       return  latitude;
    }

    public  void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getCreatedat() {

        return createdat;

    }
    public void setCreatedat(String createdat) {

        this.createdat = createdat;

    }


    @Override
    public String toString() {

        return  id + " " + ownerid  + " " +  r_full + " " +
                contact_no + " " + house + " " + municipality  + " "
                + barangay + " " + createdat;

    }


}
