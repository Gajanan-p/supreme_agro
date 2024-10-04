package com.bright.supreme.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bright.supreme.R;
import com.bright.supreme.model.Datum;
import com.bright.supreme.model.ReportViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    List<Datum> reportViewModels;

    public ReportListAdapter(List<Datum> reportViewModels) {
        this.reportViewModels = reportViewModels;
    }

    @NonNull
    @Override
    public ReportListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportListAdapter.ViewHolder holder, int position) {
        Datum reportViewModel = reportViewModels.get(position);
        holder.textViewAgencyName.setText(reportViewModel.getAgencyname());
        holder.textViewAgencyCode.setText(reportViewModel.getAgencycode());
        holder.textViewVehicleNo.setText(reportViewModel.getVehicleNo());
        holder.textViewDipQty.setText(reportViewModel.getDipqty());
        holder.textViewMilkQty.setText(reportViewModel.getMilkQty());
        holder.textViewTemp.setText(reportViewModel.getTemperature());
        holder.textViewFat.setText(reportViewModel.getFatper());
        holder.textViewClr.setText(reportViewModel.getClr());
        holder.textViewSnf.setText(reportViewModel.getSnf());
        holder.textViewFlashValue.setText(reportViewModel.getFlushvalue());
        holder.textViewTesterName.setText(reportViewModel.getTestername());
    }

    @Override
    public int getItemCount() {
        return reportViewModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewAgencyName;
        AppCompatTextView textViewAgencyCode;
        AppCompatTextView textViewVehicleNo;
        AppCompatTextView textViewDipQty;
        AppCompatTextView textViewMilkQty;
        AppCompatTextView textViewTemp;
        AppCompatTextView textViewFat;
        AppCompatTextView textViewSnf;
        AppCompatTextView textViewClr;
        AppCompatTextView textViewFlashValue;
        AppCompatTextView textViewTesterName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAgencyName = itemView.findViewById(R.id.text_row_report_agency_name);
            textViewAgencyCode = itemView.findViewById(R.id.text_row_report_agency_code);
            textViewVehicleNo = itemView.findViewById(R.id.text_row_report_vehicle_no);
            textViewDipQty = itemView.findViewById(R.id.text_row_report_dip_qty);
            textViewMilkQty = itemView.findViewById(R.id.text_row_report_milk_qty);
            textViewTemp = itemView.findViewById(R.id.text_row_report_temp);
            textViewFat = itemView.findViewById(R.id.text_row_report_fat);
            textViewSnf = itemView.findViewById(R.id.text_row_report_snf);
            textViewClr = itemView.findViewById(R.id.text_row_report_clr);
            textViewFlashValue = itemView.findViewById(R.id.text_row_report_flush_value);
            textViewTesterName = itemView.findViewById(R.id.text_row_report_tester_name);
        }
    }
}
