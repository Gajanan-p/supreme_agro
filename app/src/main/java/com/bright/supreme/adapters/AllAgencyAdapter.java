package com.bright.supreme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import com.bright.supreme.R;
import com.bright.supreme.dialogs.AgencyItemClickListener;
import com.bright.supreme.model.GetAllAgencyModel;

import java.util.ArrayList;
import java.util.List;

public class AllAgencyAdapter extends RecyclerView.Adapter<AllAgencyAdapter.ViewHolder> implements Filterable {

    ArrayList<GetAllAgencyModel> allAgencyModels;
    ArrayList<GetAllAgencyModel> filteredAllAgencyModels;
    Context context;
    AgencyItemClickListener agencyItemClickListener;

    public AllAgencyAdapter(Context context,ArrayList<GetAllAgencyModel> allAgencyModels,
                             AgencyItemClickListener agencyItemClickListener) {
        this.context = context;
        this.allAgencyModels = allAgencyModels;
        this.agencyItemClickListener = agencyItemClickListener;
        this.filteredAllAgencyModels = new ArrayList<>(allAgencyModels);
    }

    @NonNull
    @Override
    public AllAgencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_all_agency_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllAgencyAdapter.ViewHolder holder, int position) {
        GetAllAgencyModel agencyModel = filteredAllAgencyModels.get(position);
        holder.textViewAgencyCode.setText(agencyModel.getAgencyCode());
        holder.textViewAgencyName.setText(agencyModel.getAgencyName());
        holder.textViewAgencyName.setOnClickListener(view -> {
            agencyItemClickListener.onAgencyItemClick(agencyModel,position);
        });
    }

    @Override
    public int getItemCount() {
        return filteredAllAgencyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewAgencyCode;
        AppCompatTextView textViewAgencyName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAgencyCode = itemView.findViewById(R.id.row_list_agency_code);
            textViewAgencyName = itemView.findViewById(R.id.row_list_agency_name);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String queryString = constraint != null ? constraint.toString().toLowerCase() : null;
                FilterResults filterResults = new FilterResults();
                List<GetAllAgencyModel> filteredList = new ArrayList<>();

                if (queryString == null || queryString.isEmpty()) {
                    filteredList.addAll(allAgencyModels);
                } else {
                    for (GetAllAgencyModel item : allAgencyModels) {
                        if (item.getAgencyCode().contains(queryString)||item.getAgencyName().toLowerCase().contains(queryString)) {
                            filteredList.add(item);
                        }
                    }
                }

                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredAllAgencyModels.clear();
                filteredAllAgencyModels.addAll((ArrayList<GetAllAgencyModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
