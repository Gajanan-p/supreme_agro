package com.bright.supreme.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import com.bright.supreme.R;
import com.bright.supreme.adapters.ReportListAdapter;
import com.bright.supreme.model.Datum;
import com.bright.supreme.model.LoginData;
import com.bright.supreme.model.ReportReqModel;
import com.bright.supreme.model.ReportViewModel;
import com.bright.supreme.utils.PreferenceUtil;
import com.bright.supreme.webservices.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportsActivity extends AppCompatActivity {

    private RecyclerView viewReportList;
    private AppCompatEditText textFromDate;
    private AppCompatEditText textToDate;
    private AppCompatButton buttonView;
    LoginData userModel;
    ReportListAdapter adapter;
    List<Datum> reportDataList ;
    String formattedDate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        reportDataList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blue_tool_bar));
        }

        viewReportList = findViewById(R.id.recycle_report_list);
        textFromDate = findViewById(R.id.edit_report_from_date);
        textToDate = findViewById(R.id.edit_report_to_date);
        buttonView = findViewById(R.id.button_report_view);

        viewReportList.setLayoutManager(new LinearLayoutManager(ReportsActivity.this));
        viewReportList.setHasFixedSize(true);

        BottomNavigationView bottomNavigationView = findViewById(R.id.report_bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu_action_home:
                        Intent intentAddRepair = new Intent(ReportsActivity.this, MainActivity.class);
                        startActivity(intentAddRepair);
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });

        textFromDate.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(ReportsActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // Handle the selected date
                            String selectedDate = year +"-" + (monthOfYear + 1) + "-" +"0"+ dayOfMonth;
                            textFromDate.setText(selectedDate);
                        }
                    }, year, month, day);

            // Show the date picker dialog
            datePickerDialog.show();
        });

        textToDate.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(ReportsActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // Handle the selected date
                            String selectedDate = year +"-" + (monthOfYear + 1) + "-" +"0"+ dayOfMonth;
                            textToDate.setText(selectedDate);
                            //fetchReportDataFromServer();
                        }
                    }, year, month, day);

            // Show the date picker dialog
            datePickerDialog.show();
        });

        buttonView.setOnClickListener(view -> {
//            fetchReportDataFromServer();
        });

        userModel = PreferenceUtil.getLoginDataPreferences(ReportsActivity.this);
        fetchReportDataFromServer();
    }

    public void fetchReportDataFromServer(){
        String fromDate = getCurrentDateTimeSimpleFormat();
        String toDate = getCurrentDateTimeSimpleFormat();
        String mobileNo = userModel.getMobileNo();

        Call<ReportViewModel> callReportData = RetrofitClient
                .getApiServices()
                .fetchReportFromServer(fromDate,toDate,mobileNo);
        callReportData.enqueue(new Callback<ReportViewModel>() {
            @Override
            public void onResponse(Call<ReportViewModel> call, Response<ReportViewModel> response) {
                if (response.isSuccessful()){
                    reportDataList =response.body().getData();
                    adapter = new ReportListAdapter(reportDataList);
                    viewReportList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ReportViewModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }


    public String getCurrentDateTimeSimpleFormat() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        return simpleDateFormat.format(calendar.getTime());

    }
}