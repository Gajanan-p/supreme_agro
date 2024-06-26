package com.samprama.milkrecieptapp.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samprama.milkrecieptapp.R;
import com.samprama.milkrecieptapp.adapters.AllVehicleAdapter;
import com.samprama.milkrecieptapp.model.GetAllVehicleModel;

import java.util.ArrayList;

public class VehicleCustomDialog extends Dialog  {
    ArrayList<GetAllVehicleModel> allVehicleModels;
    VehicleItemClickListener vehicleItemClickListener;
    AppCompatEditText editTextSearchName;
    AllVehicleAdapter adapter;
    RecyclerView recyclerView;
    public VehicleCustomDialog(@NonNull Context context, ArrayList<GetAllVehicleModel> allVehicleModels,
                              VehicleItemClickListener vehicleItemClickListener) {
        super(context);
        this.allVehicleModels = allVehicleModels;
        this.vehicleItemClickListener = vehicleItemClickListener;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_all_vehicle_custom_layout);
        recyclerView = (RecyclerView) findViewById(R.id.list_item_all_vehicles);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new AllVehicleAdapter(getContext(),allVehicleModels,vehicleItemClickListener);
        recyclerView.setAdapter(adapter);
        editTextSearchName = findViewById(R.id.edit_search_vehicle_no);
        editTextSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(arg0);
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }
}
