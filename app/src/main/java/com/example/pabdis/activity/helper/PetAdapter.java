package com.example.pabdis.activity.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.pabdis.R;

import java.util.ArrayList;

public class PetAdapter extends ArrayAdapter<Pet> {

    Context context;
    public ArrayList<Pet> MainList;
    public ArrayList<Pet> PetListTemp;
    public PetAdapter.SubjectDataFilter studentDataFilter;
    public ArrayList<Pet> ID;
    public ArrayList<Pet> QR_Code;
    public ArrayList<Pet> Owner;
    public ArrayList<Pet> Pet;
    public ArrayList<Pet> DateVacc;


    public PetAdapter(Context context, int id,  ArrayList<Pet> petArrayList) {
        super(context, id, petArrayList);
        this.PetListTemp = new ArrayList<Pet>();
        this.PetListTemp.addAll(petArrayList);
        this.MainList = new ArrayList<Pet>();
        this.MainList.addAll(petArrayList);
    }

    @Override
    public Filter getFilter() {
        if (studentDataFilter == null) {
            studentDataFilter = new PetAdapter.SubjectDataFilter();
        }
        return studentDataFilter;
    }

    public class ViewHolder {

        TextView ID;
        TextView OWNER_ID;
        TextView PET_ID;
        TextView PETNAME;
        TextView SPECIES;
        TextView BREED;
        TextView SEX;
        TextView BIRTH;
        TextView COLOR;
        TextView CREATED;
    }


    @Override
    public View getView(final int position, View child, ViewGroup viewGroup) {

        PetAdapter.ViewHolder holder = null;

        if (child == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items_pet, null);
            holder = new PetAdapter.ViewHolder();
            holder.ID = child.findViewById(R.id.txtViewID);
            holder.OWNER_ID = child.findViewById(R.id.ownerID);
            holder.PET_ID = child.findViewById(R.id.txtPetID);
            holder.PETNAME = child.findViewById(R.id.txtPetName);
            holder.SPECIES = child.findViewById(R.id.txtPetSpecie);
            holder.BREED = child.findViewById(R.id.txtPetBreed);
            holder.SEX = child.findViewById(R.id.txtPetGender);
            holder.BIRTH = child.findViewById(R.id.txtPetBirthdate);
            holder.COLOR = child.findViewById(R.id.txtPetColor);
            holder.CREATED = child.findViewById(R.id.txtCreatedat);
            child.setTag(holder);



        } else {

            holder = (PetAdapter.ViewHolder) child.getTag();
        }

        final Pet pet = PetListTemp.get(position);

        holder.ID.setText( pet.getId());
        holder.OWNER_ID.setText(pet.getOwner_id());
        holder.PET_ID.setText(pet.getPetid());
        holder.PETNAME.setText(pet.getPetname());
        holder.SPECIES.setText(pet.getSpecies());
        holder.BREED.setText(pet.getBreed());
        holder.SEX.setText(pet.getSex());
        holder.BIRTH.setText(pet.getBirthday());
        holder.COLOR.setText(pet.getColor_marking());
        holder.CREATED.setText(pet.getCreated_at());

        return child;
    }

    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {

                ArrayList<Pet> arrayList1 = new ArrayList<Pet>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    Pet subject = MainList.get(i);

                    if (subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            PetListTemp = (ArrayList<Pet>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = PetListTemp.size(); i < l; i++)
                add(PetListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }
}
