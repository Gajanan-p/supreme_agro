package com.samprama.milkrecieptapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;


import com.samprama.milkrecieptapp.R;
import com.samprama.milkrecieptapp.dialogs.VehicleItemClickListener;
import com.samprama.milkrecieptapp.model.GetAllVehicleModel;

import java.util.ArrayList;
import java.util.List;

public class AllVehicleAdapter extends RecyclerView.Adapter<AllVehicleAdapter.ViewHolder> implements Filterable {

    ArrayList<GetAllVehicleModel> allVehicleModels;
    ArrayList<GetAllVehicleModel> filteredAllVehicleModel;
    Context context;
    VehicleItemClickListener vehicleItemClickListener;

    public AllVehicleAdapter(Context context, ArrayList<GetAllVehicleModel> allVehicleModels,
                             VehicleItemClickListener vehicleItemClickListener) {
        this.allVehicleModels = allVehicleModels;
        this.filteredAllVehicleModel = allVehicleModels;
        this.context = context;
        this.vehicleItemClickListener = vehicleItemClickListener;
    }

    @NonNull
    @Override
    public AllVehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_all_vehicle_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllVehicleAdapter.ViewHolder holder, int position) {
        GetAllVehicleModel vehicleModel = allVehicleModels.get(position);
        holder.textViewVehicleNo.setText(vehicleModel.getVehicleNo());
        holder.textViewVehicleNo.setOnClickListener(view -> {
            vehicleItemClickListener.onVehicleItemClick(vehicleModel,position);
        });
    }

    @Override
    public int getItemCount() {
        return allVehicleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewVehicleNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewVehicleNo = itemView.findViewById(R.id.row_list_vehicle_code);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String queryString = constraint != null ? constraint.toString().toLowerCase() : null;
                FilterResults filterResults = new FilterResults();
                List<GetAllVehicleModel> filteredList = new ArrayList<>();

                if (queryString == null || queryString.isEmpty()) {
                    filteredList.addAll(allVehicleModels);
                } else {
                    for (GetAllVehicleModel item : allVehicleModels) {
                        if (item.getVehicleNo().contains(queryString)) {
                            filteredList.add(item);
                        }
                    }
                }

                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredAllVehicleModel.clear();
                filteredAllVehicleModel.addAll((ArrayList<GetAllVehicleModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
