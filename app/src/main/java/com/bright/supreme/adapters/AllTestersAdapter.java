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
import com.bright.supreme.dialogs.TesterItemClickListener;
import com.bright.supreme.model.GetAllTesterModel;

import java.util.ArrayList;
import java.util.List;

public class AllTestersAdapter extends RecyclerView.Adapter<AllTestersAdapter.ViewHolder> implements Filterable {

    ArrayList<GetAllTesterModel> allTesterModels;
    ArrayList<GetAllTesterModel> filteredAllTesterModels;
    Context context;
    TesterItemClickListener testerItemClickListener;

    public AllTestersAdapter(Context context,ArrayList<GetAllTesterModel> allTesterModels, TesterItemClickListener testerItemClickListener) {
        this.context = context;
        this.allTesterModels = allTesterModels;
        this.filteredAllTesterModels = new ArrayList<>(allTesterModels);
        this.testerItemClickListener = testerItemClickListener;
    }

    @NonNull
    @Override
    public AllTestersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_all_tester_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTestersAdapter.ViewHolder holder, int position) {
        GetAllTesterModel testerModel = filteredAllTesterModels.get(position);
        holder.textViewTesterCode.setText(testerModel.getCode());
        holder.textViewTesterName.setText(testerModel.getTesterName());
        holder.textViewTesterName.setOnClickListener(view -> {
            testerItemClickListener.onTesterItemClick(testerModel,position);
        });
    }

    @Override
    public int getItemCount() {
        return filteredAllTesterModels.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewTesterCode;
        AppCompatTextView textViewTesterName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTesterCode = itemView.findViewById(R.id.row_list_tester_code);
            textViewTesterName = itemView.findViewById(R.id.row_list_tester_name);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String queryString = constraint != null ? constraint.toString().toLowerCase() : null;
                FilterResults filterResults = new FilterResults();
                List<GetAllTesterModel> filteredList = new ArrayList<>();

                if (queryString == null || queryString.isEmpty()) {
                    filteredList.addAll(allTesterModels);
                } else {
                    for (GetAllTesterModel item : allTesterModels) {
                        if (item.getCode().contains(queryString)||item.getTesterName().toLowerCase().contains(queryString)) {
                            filteredList.add(item);
                        }
                    }
                }

                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredAllTesterModels.clear();
                filteredAllTesterModels.addAll((ArrayList<GetAllTesterModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }

}
