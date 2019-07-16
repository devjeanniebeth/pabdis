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

/**
 * Created by Jinimon on 12/02/2018.
 */

public class ListAdapter extends ArrayAdapter<Owner> {

    Context context;
    public ArrayList<Owner> MainList;
    public ArrayList<Owner> OwnerListTemp;
    public ListAdapter.SubjectDataFilter studentDataFilter;
    public ArrayList<Owner> ID;
    public ArrayList<Owner> QR_Code;
    public ArrayList<Owner> Owner;
    public ArrayList<Owner> Pet;
    public ArrayList<Owner> DateVacc;

    public ListAdapter(Context context, int id,  ArrayList<Owner> ownerArrayList) {
        super(context, id, ownerArrayList);
        this.OwnerListTemp = new ArrayList<Owner>();
        this.OwnerListTemp.addAll(ownerArrayList);
        this.MainList = new ArrayList<Owner>();
        this.MainList.addAll(ownerArrayList);
    }

    @Override
    public Filter getFilter() {
        if (studentDataFilter == null) {
            studentDataFilter = new ListAdapter.SubjectDataFilter();
        }
        return studentDataFilter;
    }

    public class ViewHolder {

        TextView ID;
        TextView OWNER_ID;
        TextView R_FULL;
        TextView CONTACT;
        TextView HOUSE;
        TextView MUNI;
        TextView BRGY;
        TextView LATI;
        TextView LONGI;
        TextView CREATED;
    }





    @Override
    public View getView(final int position, View child, ViewGroup viewGroup) {

        ListAdapter.ViewHolder holder = null;

        if (child == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);
            holder = new ListAdapter.ViewHolder();
            holder.ID = child.findViewById(R.id.txtViewID);
            holder.OWNER_ID = child.findViewById(R.id.txtViewOwner);
            holder.R_FULL = child.findViewById(R.id.txtViewFullR);
            holder.CONTACT = child.findViewById(R.id.txtContact);
            holder.HOUSE = child.findViewById(R.id.txtHouse);
            holder.MUNI = child.findViewById(R.id.txtMuni);
            holder.BRGY = child.findViewById(R.id.txtBrgy);
            holder.LATI = child.findViewById(R.id.txtLati);
            holder.LONGI = child.findViewById(R.id.txtLongi);
            holder.CREATED = child.findViewById(R.id.txtCreatedat);
            child.setTag(holder);



        } else {

            holder = (ListAdapter.ViewHolder) child.getTag();
        }

        final Owner owner = OwnerListTemp.get(position);

        holder.ID.setText( owner.getId());
        holder.OWNER_ID.setText(owner.getOwnerid());
        holder.R_FULL.setText(owner.getR_full());
        holder.CONTACT.setText(owner.getContact_no());
        holder.HOUSE.setText(owner.getHouse());
        holder.MUNI.setText(owner.getMunicipality());
        holder.BRGY.setText(owner.getBarangay());
        holder.LATI.setText(owner.getLatitude());
        holder.LONGI.setText(owner.getLongitude());
        holder.CREATED.setText(owner.getCreatedat());

        return child;
    }



    private class SubjectDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {

                ArrayList<Owner> arrayList1 = new ArrayList<Owner>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    Owner subject = MainList.get(i);

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

            OwnerListTemp = (ArrayList<Owner>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = OwnerListTemp.size(); i < l; i++)
                add(OwnerListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }

}
