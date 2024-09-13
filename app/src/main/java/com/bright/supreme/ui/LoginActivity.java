package com.bright.supreme.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bright.supreme.MainActivity;
import com.bright.supreme.R;
import com.bright.supreme.model.CheckMobileModel;
import com.bright.supreme.model.LoginData;
import com.bright.supreme.utils.PreferenceUtil;
import com.bright.supreme.webservices.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextPassword;
    private AppCompatButton buttonLogin;
    LoginData loginData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPassword = findViewById(R.id.editTextPassword);

        buttonLogin = findViewById(R.id.buttonLogin);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blue_tool_bar));
            window.setTitle("Login");
        }

        loginData = new LoginData();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = editTextPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(editTextPassword.getText().toString())) {
                   fetchMobileData("91"+password);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Input filter to allow only digits in the phone number EditText
    public class PhoneNumberInputFilter implements InputFilter {
        private final Pattern mPattern;

        public PhoneNumberInputFilter() {
            mPattern = Pattern.compile("[0-9]*");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher = mPattern.matcher(source);
            if (!matcher.matches()) {
                Toast.makeText(LoginActivity.this, "Please enter only digits", Toast.LENGTH_SHORT).show();
                return "";
            }
            return null;
        }
    }

    public void fetchMobileData(String mobileNo){
        ProgressDialog progressDialog1 = createProgressDialog(LoginActivity.this);
        Call<CheckMobileModel> callMobileData = RetrofitClient
                .getApiServices()
                .fetchMobileNoDataFromServer(mobileNo);
        callMobileData.enqueue(new Callback<CheckMobileModel>() {
            @Override
            public void onResponse(Call<CheckMobileModel> call, Response<CheckMobileModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("Valid MobileNo")) {
                        LoginData loginData1 = new LoginData();
                        loginData1.setMobileNo(mobileNo);
                        PreferenceUtil.setLoginDataPreferences(LoginActivity.this,loginData1);
                        Intent i  = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        progressDialog1.dismiss();
                    } else {
                        progressDialog1.dismiss();
                        showAlertDialog(mobileNo);
                    }
                }else {
                    progressDialog1.dismiss();
                    showAlertDialog(mobileNo);
                }
            }

            @Override
            public void onFailure(Call<CheckMobileModel> call, Throwable t) {
                Log.e("Error", t.getMessage());
                progressDialog1.dismiss();
            }
        });
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

    private void showAlertDialog(String mobile) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert User Authentication");
        builder.setMessage("Your Mobile Number Is Not Valid !\n"+mobile);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
        alertDialog.setCancelable(false);
    }

}