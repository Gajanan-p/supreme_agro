package com.bright.supreme;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bright.supreme.dialogs.GetAllAgencyDialog;
import com.bright.supreme.model.LoginData;
import com.bright.supreme.ui.LoginActivity;
import com.bright.supreme.utils.PreferenceUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.bright.supreme.dialogs.AgencyItemClickListener;
import com.bright.supreme.dialogs.TesterCustomDialog;
import com.bright.supreme.dialogs.TesterItemClickListener;
import com.bright.supreme.dialogs.VehicleCustomDialog;
import com.bright.supreme.dialogs.VehicleItemClickListener;
import com.bright.supreme.model.CheckMobileModel;
import com.bright.supreme.model.CreateMilkRectModel;
import com.bright.supreme.model.DipReadingModel;
import com.bright.supreme.model.GetAllAgencyModel;
import com.bright.supreme.model.GetAllTesterModel;
import com.bright.supreme.model.GetAllVehicleModel;
import com.bright.supreme.model.RecResponseModel;
import com.bright.supreme.model.RectNoModel;
import com.bright.supreme.model.ResponseDataModels;
import com.bright.supreme.model.TesterNameData;
import com.bright.supreme.model.TesterNameModel;
import com.bright.supreme.model.VehicleNoData;
import com.bright.supreme.model.VehicleNoModel;
import com.bright.supreme.webservices.RetrofitClient;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        VehicleItemClickListener, TesterItemClickListener, AgencyItemClickListener {

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
    private AppCompatButton buttonScanQRCode;
    private LinearLayout containerAfterScanQRCode;
    private LinearLayout containerBeforeScanQRCode;
    private DecoratedBarcodeView barcodeView;
    private AppCompatCheckBox checkBoxManuallyData;

    String currentDate;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    private TesterCustomDialog testerCustomDialog;
    private VehicleCustomDialog vehicleCustomDialog;
    private GetAllAgencyDialog allAgencyDialog;
    GetAllVehicleModel vehicleModel;
    GetAllTesterModel testerModel;
    GetAllAgencyModel agencyModel;
    TesterNameData testerNameData;
    VehicleNoData vehicleNoData;
    DipReadingModel dipReadingModel;
    Date now = new Date();
    String formattedDate;
    boolean isVehicleClick = false;
    boolean isTesterClick = false;
    private Handler handler = new Handler();
    private Runnable saveRunnable;
    private long delay = 500; // 500 milliseconds delay for debounce
    String barcodeValue = null;
    LoginData userModel;
    String mobileNumber=null;
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
// Define the desired format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

// Format the current date and time
        formattedDate = formatter.format(now);
        barcodeView = findViewById(R.id.barcode_scanner);
        editTextRectDate = findViewById(R.id.edit_main_rect_date);
        editTextMobileNo = findViewById(R.id.edit_main_mobile_no);
        editTextVehicleNo = findViewById(R.id.edit_main_vehicle_no);
        editTextVehicleNo.setOnClickListener(this);
        editTextAgency = findViewById(R.id.edit_main_agency_name);
        editTextAgency.setOnClickListener(this);
        editTextAgency.setClickable(false);
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
        buttonScanQRCode = findViewById(R.id.button_main_scan_qr_code);
        buttonScanQRCode.setOnClickListener(this);
        containerAfterScanQRCode = findViewById(R.id.layout_container_after_qrcode_scan);
        containerBeforeScanQRCode = findViewById(R.id.layout_container_before_qrcode_scan);
        checkBoxManuallyData = findViewById(R.id.checkbox_main_manually);
        checkBoxManuallyData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    buttonScanQRCode.setVisibility(View.GONE);
                    containerBeforeScanQRCode.setVisibility(View.VISIBLE);
                    containerAfterScanQRCode.setVisibility(View.VISIBLE);
                    checkBoxManuallyData.setVisibility(View.GONE);
                }else {
                    buttonScanQRCode.setVisibility(View.VISIBLE);
                    containerBeforeScanQRCode.setVisibility(View.VISIBLE);
                    containerAfterScanQRCode.setVisibility(View.GONE);
                }
            }
        });
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

        TextWatcher textWatcher1 = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length()!=0 && !TextUtils.isEmpty(editTextAgency.getText().toString())) {
                    getDipReadingValuesFromDataBase(agencyModel);
                }
            }
        };
        editTextDIPReading.addTextChangedListener(textWatcher1);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        currentDate = simpleDateFormat.format(calendar.getTime());
        editTextRectDate.setText(currentDate);
        editTextRectNO = findViewById(R.id.edit_main_rect_no);
        userModel = PreferenceUtil.getLoginDataPreferences(MainActivity.this);
        if (userModel!=null) {
            mobileNumber = userModel.getMobileNo();
            editTextMobileNo.setText(mobileNumber);
            getLastTesterByMobile(mobileNumber);
            getLastVehicleByMobile(mobileNumber);
        }
        checkForLogin();


    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
        fetchRectNumberData();
        checkForLogin();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_scan_qr_code:{
                scannedQRCodeData();
//                scannedData();
                break;
            }
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

            case R.id.edit_main_tester: {
                fetchTesterData();
                break;
            }
            case R.id.edit_main_agency_name:{
                fetchAgencyDataForManual();
                break;
            }
        }
    }

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            barcodeValue = result.getText();
            fetchAllAgencyData(barcodeValue);
            barcodeView.pause();
            barcodeView.clearFocus();
            barcodeView.setVisibility(View.GONE);

        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    public void checkForLogin() {
        if (userModel==null) {
            Intent intent =new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void fetchAllAgencyData(String barcodeValue){
        Call<ArrayList<GetAllAgencyModel>> callAgencyData = RetrofitClient
                .getApiServices()
                .getAllAgencyDataFromServer(barcodeValue);
        callAgencyData.enqueue(new Callback<ArrayList<GetAllAgencyModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllAgencyModel>> call, Response<ArrayList<GetAllAgencyModel>> response) {
                if (response.isSuccessful()){
                    agencyModel=response.body().get(0);
                    editTextAgency.setText(agencyModel.getAgencyName());
                    editTextZone.setText(agencyModel.getAgencyZone());
                    checkBoxManuallyData.setVisibility(View.GONE);
                    containerBeforeScanQRCode.setVisibility(View.VISIBLE);
                    containerAfterScanQRCode.setVisibility(View.VISIBLE);
                    buttonSaveDataBase.setVisibility(View.VISIBLE);
                    buttonScanQRCode.setVisibility(View.GONE);
                }else {
                    containerBeforeScanQRCode.setVisibility(View.VISIBLE);
                    buttonScanQRCode.setVisibility(View.VISIBLE);
                    showAlertDialog("Agency is not valid please scan another QR Code","Alert QR Code Scan");
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
        }
        allAgencyDialog.dismiss();
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
                if (response.isSuccessful()) {
                    vehicleNoData = response.body().getData();
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
        editTextQty .setText("");
        editTextFAT.setText("");
        editTextCLR .setText("");
        editTextSNF .setText("");
        editTextTemp .setText("");
        editTextAgency.setText("");
        editTextZone.setText("");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle the scan result
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // Handle the case when the scan is canceled
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // Display the scanned data in the TextView
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void scannedData(){
        // Clear the previous result before starting a new scan
      //  qrCodeResult.setText(""); // Clear previous data

        // Start the QR code scanning
        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE); // Only scan QR codes
        integrator.setPrompt("Scan a QR Code");
        integrator.setCameraId(0); // Use back camera
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false); // Don't save the scanned image
        integrator.initiateScan(); // Start the scan

    }
    //    scan qr code data

    public void scannedQRCodeData(){
        barcodeView.setSoundEffectsEnabled(true);
        containerBeforeScanQRCode.setVisibility(View.GONE);
            barcodeView.setVisibility(View.VISIBLE);
        barcodeView.setStatusText("Please Scan Your Agency QR Code");
        barcodeView.getBarcodeView()
                .setDecoderFactory(new DefaultDecoderFactory(Arrays.asList(BarcodeFormat.QR_CODE,BarcodeFormat.CODE_39)));
        barcodeView.initializeFromIntent(getIntent());
        barcodeView.decodeContinuous(callback);
    }

    public void getDipReadingValuesFromDataBase(GetAllAgencyModel agencyModel){
        int dipValue = Integer.parseInt(editTextDIPReading.getText().toString());
        Call<DipReadingModel> callDipReadingData = RetrofitClient
                .getApiServices()
                .fetchDipReadingDataFromServer(agencyModel.getAgencyCode(),dipValue);
        callDipReadingData.enqueue(new Callback<DipReadingModel>() {
            @Override
            public void onResponse(Call<DipReadingModel> call, Response<DipReadingModel> response) {
                if (response.isSuccessful()){
                    if (response.body().getData()!=null) {
                        dipReadingModel = response.body();
                        editTextQty.setText(String.valueOf(dipReadingModel.getData().getWeight()));
                    }
                }else {
                    editTextQty.setText("");
                }
            }

            @Override
            public void onFailure(Call<DipReadingModel> call, Throwable t) {
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
                    if (agencyModel.getAgencyZone().equals("EAST")
                            ||agencyModel.getAgencyZone().equals("LDH")){
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
        return clr / 4 + fat * 0.2 + 0.29;
    }

    private double calculationForCMQTY(double clr,double fat) {
        return ( clr + fat) / 4 + 0.30;
    }

    public void fetchAgencyDataForManual(){
        Call<ArrayList<GetAllAgencyModel>> callAgencyDataForManual = RetrofitClient
                .apiServices
                .getAllAgencyForManualDataFromServer();
        callAgencyDataForManual.enqueue(new Callback<ArrayList<GetAllAgencyModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetAllAgencyModel>> call, Response<ArrayList<GetAllAgencyModel>> response) {
                if (response.isSuccessful()){
                    ArrayList<GetAllAgencyModel> allAgencyModels = response.body();
                    allAgencyDialog = new GetAllAgencyDialog(MainActivity.this,
                            allAgencyModels,
                            MainActivity.this);
                    allAgencyDialog.show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<GetAllAgencyModel>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    private void showAlertDialog(String message,String tittle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tittle);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

}