package com.example.pabdis.activity.helper;

public class Pet {
    String id = null;
    String petid = null;
    String owner_id = null;
    String petname = null;
    String species = null;
    String breed = null;
    String sex = null;
    String respondent = null;
    String birthday = null;
    String color_marking = null;
    String Pet_ID = null;
    String created_at = null;
    String last_vacc = null;
    String pet_latitude = null;
    String pet_longitude = null;


    public Pet( String id, String owner_id, String petid, String respondent, String petname, String species,
               String breed, String sex, String birthday, String color_marking,
               String created_at, String last_vacc, String pet_latitude, String pet_longitude){

        super();

        this.id = id;
        this.owner_id = owner_id;
        this.petid = petid;
        this.respondent = respondent;
        this.petname = petname;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.birthday = birthday;
        this.color_marking = color_marking;
        this.created_at = created_at;
        this.last_vacc = last_vacc;
        this.pet_latitude= pet_latitude;
        this.pet_longitude = pet_longitude;

    }

    public String getId(){

        return id;
    }

    public void setId(String id2){

        this.id = id2;

    }

    public String getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid = petid;
    }

    public String getOwner_id() {

        return owner_id;

    }

    public String getRespondent()
    {

        return respondent;
    }

    public void setRespondent(String respondent)
    {
        this.respondent = respondent;
    }

    public void setOwner_id(String owner_id) {

        this.owner_id = owner_id;

    }

    public String getPetname() {

        return petname;
    }

    public void setPetname(String petname){

        this.petname = petname;
    }

    public String getSpecies(){

        return species;

    }

    public void setSpecies(String species){

        this.species = species;

    }

    public String getBreed(){

        return breed;

    }

    public void setBreed(String breed){

        this.breed = breed;

    }

    public String getSex(){

        return sex;

    }

    public void setSex(String sex){

        this.sex = sex;

    }

    public String getBirthday(){

        return birthday;
    }

    public void setBirthday(String birthday){

        this.birthday = birthday;

    }

    public String getColor_marking(){

        return color_marking;

    }

    public void setColor_marking(String color_marking){

        this.color_marking = color_marking;
    }

    public void setLast_vacc(String last_vacc)
    {
        this.last_vacc = last_vacc;
    }
    public String getLast_vacc()
    {
        return last_vacc;
    }


    public String getCreated_at(){

        return created_at;

    }

    public void setCreated_at(String created_at){

        this.created_at = created_at;

    }

    public String getPet_latitude(){

        return pet_latitude;

    }

    public void setPet_latitude(String pet_latitude){

        this.pet_latitude = pet_latitude;

    }

    public String getPet_longitude(){

        return pet_longitude;

    }

    public void setPet_longitude(String pet_longitude){

        this.pet_longitude = pet_longitude;

    }

    @Override
    public String toString(){

        return id  + " " + owner_id + " " + petname
                + " " + species + " " + breed + " " + sex + " " + birthday + " " + color_marking
                + " " + Pet_ID + " " + created_at + " " + pet_latitude + " " + pet_longitude;
    }

}
