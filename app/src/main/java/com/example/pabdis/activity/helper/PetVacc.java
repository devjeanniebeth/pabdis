package com.example.pabdis.activity.helper;

public class PetVacc {

    String id = null;
    String petid = null;
    String petname = null;
    String date_vacc = null;
    String vacc_by = null;
    String created_at = null;

    public PetVacc(String id, String petid, String petname, String date_vacc,String vacc_by, String created_at)
    {
        super();
        this.id = id;
        this.petid = petid;
        this.petname = petname;
        this.date_vacc = date_vacc;
        this.vacc_by = vacc_by;
        this.created_at = created_at;
    }

    public String getId(){

        return id;
    }

    public void setId(String id){

        this.id = id;

    }

    public String getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid = petid;
    }

    public String getPetname() {

        return petname;
    }

    public void setPetname(String petname){

        this.petname = petname;
    }

    public String getDate_vacc()
    {
        return date_vacc;
    }

    public void setDate_vacc(String date_vacc){
        this.date_vacc = date_vacc;
    }

    public String getVacc_by()
    {
        return vacc_by;
    }

    public void setVacc_by(String vacc_by)
    {
        this.vacc_by = vacc_by;
    }

    public String getCreated_at(){
        return created_at;
    }

    public void setCreated_at(String created_at){

        this.created_at = created_at;
    }

    @Override
    public String toString(){

        return id  + " " + petid + " " + date_vacc
                + " " + vacc_by + " " + created_at;
    }







}
