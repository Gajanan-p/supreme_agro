package com.samprama.milkrecieptapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samprama.milkrecieptapp.R;
import com.samprama.milkrecieptapp.adapters.AllTestersAdapter;
import com.samprama.milkrecieptapp.model.GetAllTesterModel;

import java.util.ArrayList;

public class TesterCustomDialog extends Dialog  {

    ArrayList<GetAllTesterModel> allTesterModels;
    TesterItemClickListener testerItemClickListener;
    AppCompatEditText editTextSearchName;
    AllTestersAdapter adapter;
    RecyclerView recyclerView;
    public TesterCustomDialog(@NonNull Context context, ArrayList<GetAllTesterModel> allTesterModels,
                                 TesterItemClickListener testerItemClickListener) {
        super(context);
        this.allTesterModels = allTesterModels;
        this.testerItemClickListener = testerItemClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_all_tester_custom_layout);
        recyclerView = (RecyclerView) findViewById(R.id.list_item_all_tester);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new AllTestersAdapter(getContext(),allTesterModels,testerItemClickListener);
        recyclerView.setAdapter(adapter);
        editTextSearchName = findViewById(R.id.edit_search_supplier_tester);
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
