package com.example.pabdis.activity.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.pabdis.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PetVaccAdapter extends ArrayAdapter<PetVacc> {

    Context context;
    public ArrayList<PetVacc> MainList;
    public ArrayList<PetVacc> PetListTemp;
    public PetVaccAdapter.SubjectDataFilter studentDataFilter;
    public ArrayList<PetVacc> ID;
    public ArrayList<PetVacc> PETID;
    public ArrayList<PetVacc> DATEVACC;
    public ArrayList<PetVacc> VACCBY;
    public ArrayList<PetVacc> CREATED_AT;


    public PetVaccAdapter(Context context, int id,  ArrayList<PetVacc> petArrayList) {
        super(context, id, petArrayList);
        this.PetListTemp = new ArrayList<PetVacc>();
        this.PetListTemp.addAll(petArrayList);
        this.MainList = new ArrayList<PetVacc>();
        this.MainList.addAll(petArrayList);
    }

    @Override
    public Filter getFilter() {
        if (studentDataFilter == null) {
            studentDataFilter = new PetVaccAdapter.SubjectDataFilter();
        }
        return studentDataFilter;
    }

    public class ViewHolder {

        TextView ID;
        TextView PET_ID;
        TextView PETNAME;
        TextView DATEVACC;
        TextView VACCBY;
        TextView CREATED;
    }

    @Override
    public View getView(final int position, View child, ViewGroup viewGroup) {

        PetVaccAdapter.ViewHolder holder = null;

        if (child == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items_petvacc, null);
            holder = new PetVaccAdapter.ViewHolder();
            holder.ID = child.findViewById(R.id.txtViewID);
            holder.PET_ID = child.findViewById(R.id.petID);
            holder.PETNAME = child.findViewById(R.id.txtPetName);
            holder.DATEVACC = child.findViewById(R.id.txtDateVacc);
            holder.VACCBY = child.findViewById(R.id.txtVaccBy);
            holder.CREATED = child.findViewById(R.id.txtCreatedat);
            child.setTag(holder);

        } else {

            holder = (PetVaccAdapter.ViewHolder) child.getTag();
        }

        final PetVacc pet = PetListTemp.get(position);

        holder.ID.setText(pet.getId());
        holder.PET_ID.setText(pet.getPetid());
        holder.PETNAME.setText(pet.getPetname());
        holder.DATEVACC.setText(pet.getDate_vacc());
        holder.VACCBY.setText(pet.getVacc_by());
        holder.CREATED.setText(pet.getCreated_at());

        return child;
    }
    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {

                ArrayList<PetVacc> arrayList1 = new ArrayList<PetVacc>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    PetVacc subject = MainList.get(i);

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

            PetListTemp = (ArrayList<PetVacc>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = PetListTemp.size(); i < l; i++)
                add(PetListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }




}
