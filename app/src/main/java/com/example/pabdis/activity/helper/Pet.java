package com.example.pabdis.activity.helper;

public class Pet {
    String id = null;
    String petid = null;
    String owner_id = null;
    String petname = null;
    String species = null;
    String breed = null;
    String sex = null;
    
    String birthday = null;
    String color_marking = null;
    String Pet_ID = null;
    String created_at = null;

    public Pet(String id,String owner_id, String petid, String petname, String species,
               String breed, String sex, String birthday, String color_marking,
               String created_at){

        super();

        this.id = id;
        this.owner_id = owner_id;
        this.petid = petid;
        this.petname = petname;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.birthday = birthday;
        this.color_marking = color_marking;
        this.created_at = created_at;

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


    public String getCreated_at(){

        return created_at;

    }

    public void setCreated_at(String created_at){

        this.created_at = created_at;

    }

    @Override
    public String toString(){

        return id  + " " + owner_id + " " + petname
                + " " + species + " " + breed + " " + sex + " " + birthday
                + " " + color_marking + " " + Pet_ID + " " + created_at;
    }

}
