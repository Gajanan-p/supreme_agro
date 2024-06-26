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
import com.samprama.milkrecieptapp.adapters.AllAgencyAdapter;
import com.samprama.milkrecieptapp.model.GetAllAgencyModel;

import java.util.ArrayList;

public class GetAgencyAgencyDialog extends Dialog {
    ArrayList<GetAllAgencyModel> allAgencyModels;
    AgencyItemClickListener agencyItemClickListener;
    AppCompatEditText editTextSearchName;
    AllAgencyAdapter adapter;
    RecyclerView recyclerView;
    public GetAgencyAgencyDialog(@NonNull Context context, ArrayList<GetAllAgencyModel> allAgencyModels,
                                 AgencyItemClickListener agencyItemClickListener) {
        super(context);
        this.allAgencyModels = allAgencyModels;
        this.agencyItemClickListener = agencyItemClickListener;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_all_agency_custom_layout);
        recyclerView = (RecyclerView) findViewById(R.id.list_item_all_agency);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new AllAgencyAdapter(getContext(),allAgencyModels,agencyItemClickListener);
        recyclerView.setAdapter(adapter);
        editTextSearchName = findViewById(R.id.edit_search_agency_code);
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
