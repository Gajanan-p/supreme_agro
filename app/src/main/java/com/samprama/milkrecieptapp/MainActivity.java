package com.samprama.milkrecieptapp;

import static android.Manifest.permission.READ_PHONE_NUMBERS;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.samprama.milkrecieptapp.dialogs.AgencyItemClickListener;
import com.samprama.milkrecieptapp.dialogs.GetAgencyAgencyDialog;
import com.samprama.milkrecieptapp.dialogs.TesterCustomDialog;
import com.samprama.milkrecieptapp.dialogs.TesterItemClickListener;
import com.samprama.milkrecieptapp.dialogs.VehicleCustomDialog;
import com.samprama.milkrecieptapp.dialogs.VehicleItemClickListener;
import com.samprama.milkrecieptapp.model.CheckMobileModel;
import com.samprama.milkrecieptapp.model.CreateMilkRectModel;
import com.samprama.milkrecieptapp.model.GetAllAgencyModel;
import com.samprama.milkrecieptapp.model.GetAllTesterModel;
import com.samprama.milkrecieptapp.model.GetAllVehicleModel;
import com.samprama.milkrecieptapp.model.RecResponseModel;
import com.samprama.milkrecieptapp.model.RectNoModel;
import com.samprama.milkrecieptapp.model.ResponseDataModels;
import com.samprama.milkrecieptapp.model.TesterNameData;
import com.samprama.milkrecieptapp.model.TesterNameModel;
import com.samprama.milkrecieptapp.model.VehicleNoData;
import com.samprama.milkrecieptapp.model.VehicleNoModel;
import com.samprama.milkrecieptapp.webservices.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        VehicleItemClickListener, TesterItemClickListener, AgencyItemClickListener {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private TextInputEditText editTextRectDate;
    private TextInputEditText editTextRectNO;
    private TextInputEditText editTextMobileNo;
    private TextInputEditText editTextVehicleNo;
    private TextInputEditText editTextAgency;
    private TextInputEditText editTextZone;
    private TextInputEditText editTextTester;
    private TextInputEditText editTextQty;
    private TextInputEditText editTextFAT;
    private TextInputEditText editTextCLR;
    private TextInputEditText editTextSNF;
    private TextInputEditText editTextTemp;
    private TextInputEditText editTextFlushValue;
    private TextInputEditText editTextDIPReading;
    private TextInputEditText editTextBMQty;
    private TextInputEditText editTextBMFAT;
    private TextInputEditText editTextBMCLR;
    private TextInputEditText editTextBMSNF;
    private TextInputEditText editTextCMQty;
    private TextInputEditText editTextCMFAT;
    private TextInputEditText editTextCMCLR;
    private TextInputEditText editTextCMSNF;
    private AppCompatButton buttonSaveDataBase;

    String currentDate;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    private GetAgencyAgencyDialog agencyAgencyDialog;
    private TesterCustomDialog testerCustomDialog;
    private VehicleCustomDialog vehicleCustomDialog;
    GetAllVehicleModel vehicleModel;
    GetAllTesterModel testerModel;
    GetAllAgencyModel agencyModel;
    TesterNameData testerNameData;
    VehicleNoData vehicleNoData;
    private ProgressDialog progressDialog;
    Date now = new Date();
    String formattedDate;
    boolean isVehicleClick = false;
    boolean isTesterClick = false;
    private Handler handler = new Handler();
    private Runnable saveRunnable;
    private long delay = 500; // 500 milliseconds delay for debounce

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blue_tool_bar));
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.READ_PHONE_NUMBERS
                    }, PERMISSION_REQUEST_CODE);
        } else {
            getPhoneNumber();
        }
        // Define the desired format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Format the current date and time
        formattedDate = formatter.format(now);

        editTextRectDate = findViewById(R.id.edit_main_rect_date);
        editTextMobileNo = findViewById(R.id.edit_main_mobile_no);
        editTextVehicleNo = findViewById(R.id.edit_main_vehicle_no);
        editTextVehicleNo.setOnClickListener(this);
        editTextAgency = findViewById(R.id.edit_main_agency_name);
        editTextAgency.setOnClickListener(this);
        editTextZone = findViewById(R.id.edit_main_zone);
        editTextTester = findViewById(R.id.edit_main_tester);
        editTextTester.setOnClickListener(this);
        editTextQty = findViewById(R.id.edit_main_qty);
        editTextFAT = findViewById(R.id.edit_main_fat);
        editTextCLR = findViewById(R.id.edit_main_clr);
        editTextSNF = findViewById(R.id.edit_main_snf);
        editTextTemp = findViewById(R.id.edit_main_temp);
        editTextFlushValue = findViewById(R.id.edit_main_flush_value);
        editTextDIPReading = findViewById(R.id.edit_main_dip_reading);
        editTextBMQty = findViewById(R.id.edit_main_bm_qty);
        editTextBMFAT = findViewById(R.id.edit_main_bm_fat);
        editTextBMCLR = findViewById(R.id.edit_main_bm_clr);
        editTextBMSNF = findViewById(R.id.edit_main_bm_snf);
        editTextCMQty = findViewById(R.id.edit_main_cm_qty);
        editTextCMFAT = findViewById(R.id.edit_main_cm_fat);
        editTextCMCLR = findViewById(R.id.edit_main_cm_clr);
        editTextCMSNF = findViewById(R.id.edit_main_cm_snf);
        buttonSaveDataBase = findViewById(R.id.button_main_submit_database);
        buttonSaveDataBase.setOnClickListener(this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed here
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (saveRunnable != null) {
                    handler.removeCallbacks(saveRunnable);
                }
                saveRunnable = new Runnable() {
                    @Override
                    public void run() {
                        performCalculations();
                    }
                };
                handler.postDelayed(saveRunnable, delay);

            }
        };
        // Attach the TextWatcher to the relevant EditText fields

        editTextFAT.addTextChangedListener(textWatcher);
        editTextCLR.addTextChangedListener(textWatcher);
//      temporary set values

//        --------------------------------

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        currentDate = simpleDateFormat.format(calendar.getTime());
        editTextRectDate.setText(currentDate);

        editTextRectNO = findViewById(R.id.edit_main_rect_no);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchRectNumberData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_submit_database: {
                if (!TextUtils.isEmpty(editTextQty.getText().toString())
                        && !TextUtils.isEmpty(editTextAgency.getText().toString())
                        && !TextUtils.isEmpty(editTextZone.getText().toString())
                        && !TextUtils.isEmpty(editTextVehicleNo.getText().toString())
                        && !TextUtils.isEmpty(editTextSNF.getText().toString())
                        && !TextUtils.isEmpty(editTextTester.getText().toString())
                        && !TextUtils.isEmpty(editTextFAT.getText().toString())
                        && !TextUtils.isEmpty(editTextCLR.getText().toString())) {
                    sendReceiptDataFromServer();
                } else {
                    Toast.makeText(MainActivity.this, "VehicleNo,Agency,Zone,Tester,Qty,CLR,Fat field are mandatory please fill this fields!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.edit_main_vehicle_no: {
                fetchVehicleData();
                break;
            }
            case R.id.edit_main_agency_name: {
                fetchAllAgencyData();
                break;
            }
            case R.id.edit_main_tester: {
                fetchTesterData();
                break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length == permissions.length) {
                boolean allGranted = true;
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        allGranted = false;
                        break;
                    }
                }
                if (allGranted) {
                    getPhoneNumber();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permission Request Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getPhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String phoneNumber = telephonyManager.getLine1Number();
        fetchMobileData(phoneNumber);
    }


    public void fetchMobileData(String mobileNo){
        ProgressDialog progressDialog1 = createProgressDialog(MainActivity.this);
        if (mobileNo!=null) {
            Call<CheckMobileModel> callMobileData = RetrofitClient
                    .getApiServices()
                    .fetchMobileNoDataFromServer(mobileNo);
            callMobileData.enqueue(new Callback<CheckMobileModel>() {
                @Override
                public void onResponse(Call<CheckMobileModel> call, Response<CheckMobileModel> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getMessage().equals("Valid MobileNo")) {
                            editTextMobileNo.setText(mobileNo);
                            fetchRectNumberData();
                            getLastTesterByMobile(mobileNo);
                            getLastVehicleByMobile(mobileNo);
                            progressDialog1.dismiss();
                        } else {
                            progressDialog1.dismiss();
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                            alertDialog.setTitle("User Authentication");
                            alertDialog.setMessage("Your mobile no is not valid !");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finishAffinity();
                                            System.exit(0);
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                        }


                    }else {
                        progressDialog1.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("User Authentication");
                        alertDialog.setMessage("Your mobile no is not valid !");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finishAffinity();
                                        System.exit(0);
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }

                }

                @Override
                public void onFailure(Call<CheckMobileModel> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                    progressDialog.dismiss();
                }
            });
        }else {
            Toast.makeText(MainActivity.this, "Your mobile no is not valid !", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void fetchAllAgencyData(){
        Call<ArrayList<GetAllAgencyModel>> callAgencyData = RetrofitClient
                .getApiServices()
                .getAllAgencyDataFromServer();
        callAgencyData.enqueue(new Callback<ArrayList<GetAllAgencyModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllAgencyModel>> call, Response<ArrayList<GetAllAgencyModel>> response) {
                if (response.isSuccessful()){

                    agencyAgencyDialog = new GetAgencyAgencyDialog(MainActivity.this,
                            response.body(),
                            MainActivity.this);
                    agencyAgencyDialog.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllAgencyModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void fetchTesterData(){
        Call<ArrayList<GetAllTesterModel>>callTesterData = RetrofitClient
                .getApiServices()
                .getAllTesterDataFromServer();
        callTesterData.enqueue(new Callback<ArrayList<GetAllTesterModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllTesterModel>> call, Response<ArrayList<GetAllTesterModel>> response) {
                if (response.isSuccessful()){
                    testerCustomDialog = new TesterCustomDialog(MainActivity.this,response.body(),
                            MainActivity.this);
                    testerCustomDialog.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllTesterModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void fetchVehicleData(){
        Call<ArrayList<GetAllVehicleModel>> callVehicleData = RetrofitClient
                .getApiServices()
                .getAllVehicleDataFromServer();
        callVehicleData.enqueue(new Callback<ArrayList<GetAllVehicleModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllVehicleModel>> call, Response<ArrayList<GetAllVehicleModel>> response) {
                if (response.isSuccessful()){
                    vehicleCustomDialog = new VehicleCustomDialog(MainActivity.this,
                            response.body(),
                            MainActivity.this);
                    vehicleCustomDialog.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetAllVehicleModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onAgencyItemClick(GetAllAgencyModel model, int position) {
        if (model!=null){
            this.agencyModel = model;
            editTextAgency.setText(model.getAgencyName());
            if (model.getAgencyZone()=="")
             editTextZone.setText("Not Available");
            else
                editTextZone.setText(model.getAgencyZone());
            agencyAgencyDialog.dismiss();
        }

    }

    @Override
    public void onTesterItemClick(GetAllTesterModel model, int position) {
        if (model!=null){
            this.testerModel = model;
            isTesterClick=true;
            editTextTester.setText(model.getTesterName());
            testerCustomDialog.dismiss();
        }

    }

    @Override
    public void onVehicleItemClick(GetAllVehicleModel model, int position) {
        if (model!=null){
            this.vehicleModel = model;
            isVehicleClick=true;
            editTextVehicleNo.setText(model.getVehicleNo());
            vehicleCustomDialog.dismiss();
        }
    }

    public void fetchRectNumberData(){
        Call<RectNoModel> callRectNo = RetrofitClient
                .getApiServices()
                .getRectNoDataFromServer();
        callRectNo.enqueue(new Callback<RectNoModel>() {
            @Override
            public void onResponse(Call<RectNoModel> call, Response<RectNoModel> response) {
                if(response.isSuccessful()){
                    ResponseDataModels rectNo = response.body().getData();
                    editTextRectNO.setText(String.valueOf(rectNo.getRectNo()));
                }
            }

            @Override
            public void onFailure(Call<RectNoModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }


    private void performCalculations() {
        try {
            String clrStr = editTextCLR.getText().toString();
            String fatStr = editTextFAT.getText().toString();
            if (!clrStr.isEmpty() && !fatStr.isEmpty()) {
                double clr = Double.parseDouble(clrStr);
                double fat = Double.parseDouble(fatStr);
                double snrQtyEast = calculationForBMQTY(clr, fat);
                double snrQtyNorth = calculationForCMQTY(clr,fat);

                if (agencyModel!=null){
                    if (agencyModel.getAgencyName().equals("EAST")&&agencyModel.getAgencyName().equals("LDH")){
                        double value = snrQtyEast;
                        double roundedValue = (double) Math.round(value * 1000) / 1000;
                        editTextSNF.setText(String.valueOf(roundedValue));
                    }else {
                        double value = snrQtyNorth;
                        double roundedValue = (double) Math.round(value * 1000) / 1000;
                        editTextSNF.setText(String.valueOf(roundedValue));
                    }
                }

            } else {
                editTextBMQty.setText(""); // Clear the BM Qty field if inputs are empty
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private double calculationForBMQTY(double clr, double fat) {
        return clr / 4 + fat * 0.2 + 0.9;
    }

    private double calculationForCMQTY(double fat, double clr) {
        return (fat + clr) / 4 + 0.30;
    }

    public void getLastTesterByMobile(String mobileNo){
        Call<TesterNameModel> callTesterByMobileData = RetrofitClient
                .getApiServices()
                .fetchLastTesterDataFromServer(mobileNo);
        callTesterByMobileData.enqueue(new Callback<TesterNameModel>() {
            @Override
            public void onResponse(Call<TesterNameModel> call, Response<TesterNameModel> response) {
                if (response.isSuccessful()){
                    testerNameData = response.body().getData();
                    editTextTester.setText(testerNameData.getTesterName());
                }
            }

            @Override
            public void onFailure(Call<TesterNameModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void getLastVehicleByMobile(String mobileNo){
        Call<VehicleNoModel> callGetLastVehicle = RetrofitClient
                .getApiServices()
                .fetchVehicleNoDataFromServer(mobileNo);
        callGetLastVehicle.enqueue(new Callback<VehicleNoModel>() {
            @Override
            public void onResponse(Call<VehicleNoModel> call, Response<VehicleNoModel> response) {
                if (response.isSuccessful()){
                    vehicleNoData =response.body().getData();
                    editTextVehicleNo.setText(vehicleNoData.getVehicleNo());
                }
            }

            @Override
            public void onFailure(Call<VehicleNoModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    //    Save receipt data from server

    public void sendReceiptDataFromServer(){
        CreateMilkRectModel rectModel = new CreateMilkRectModel();
        rectModel.setMilkReceiptId(0);
        rectModel.setRectNo(Integer.parseInt(editTextRectNO.getText().toString()));
        rectModel.setRectDate(formattedDate);
        rectModel.setMobileNo(Long.parseLong(editTextMobileNo.getText().toString()));
        if (isVehicleClick) {
            rectModel.setVehicleId(vehicleModel.getVehicleID());
        }else {
            rectModel.setVehicleId(vehicleNoData.getVehicleID());
        }
        rectModel.setAgencyId(agencyModel.getAgencyId());
        rectModel.setAgencyZone(agencyModel.getAgencyZone());

        if (isTesterClick) {
            rectModel.setTesterId(testerModel.getTesterID());
        }else {
            rectModel.setTesterId(testerNameData.getTesterID());
        }
        rectModel.setQuantity(editTextQty.getText().toString());
        rectModel.setFatPer(editTextFAT.getText().toString());
        rectModel.setClr(editTextCLR.getText().toString());
        rectModel.setSnfPer(editTextSNF.getText().toString());
        if (!TextUtils.isEmpty(editTextTemp.getText().toString())
                &&!TextUtils.isEmpty(editTextFlushValue.getText().toString())
                &&!TextUtils.isEmpty(editTextDIPReading.getText().toString())) {
            rectModel.setTemperature(editTextTemp.getText().toString());
            rectModel.setFlushValue(editTextFlushValue.getText().toString());
            rectModel.setDipReading(editTextDIPReading.getText().toString());
        }else {
            rectModel.setTemperature("0");
            rectModel.setFlushValue("0");
            rectModel.setDipReading("0");
        }
        rectModel.setBmQty("0");
        rectModel.setBmFat("0");
        rectModel.setBmClr("0");
        rectModel.setBmSnfPer("0");
        rectModel.setCmQty("0");
        rectModel.setCmFat("0");
        rectModel.setCmClr("0");
        rectModel.setCmSnfPer("0");
        rectModel.setDateInsert(formattedDate);

        Call<RecResponseModel> callSendReceiptData = RetrofitClient
                .getApiServices()
                .sendReceiptDataFromServer(rectModel);
        callSendReceiptData.enqueue(new Callback<RecResponseModel>() {
            @Override
            public void onResponse(Call<RecResponseModel> call, Response<RecResponseModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    clearAllEditTextData();
                }else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecResponseModel> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void clearAllEditTextData(){
        editTextVehicleNo.setText("");
        editTextAgency .setText("");
        editTextZone .setText("");
        editTextTester .setText("");
        editTextQty .setText("");
        editTextFAT.setText("");
        editTextCLR .setText("");
        editTextSNF .setText("");
        editTextTemp .setText("");
        editTextFlushValue.setText("");
        editTextDIPReading.setText("");

    }


    public ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.blue(100)));
        dialog.setContentView(R.layout.dialog_progress_layout);
        return dialog;
    }

}