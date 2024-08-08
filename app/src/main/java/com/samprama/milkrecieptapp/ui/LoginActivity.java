package com.samprama.milkrecieptapp.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.text.Spanned;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.textfield.TextInputEditText;
import com.samprama.milkrecieptapp.MainActivity;
import com.samprama.milkrecieptapp.R;
import com.samprama.milkrecieptapp.model.LoginData;
import com.samprama.milkrecieptapp.utils.PreferenceUtil;

public class LoginActivity extends AppCompatActivity {


    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private AppCompatButton buttonLogin;
    LoginData loginData;
    ActionBar mActionBar;
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
                if (isValidCredentials(password)) {
                    // Simulate successful login
                    loginData.setPassword(password);
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                  //  loginData.setPassword("1234");
                    PreferenceUtil.setLoginDataPreferences(LoginActivity.this,loginData);
//                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
//                    navController.navigate(R.id.nav_home);
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isValidCredentials( String password) {
        // Here you can implement your own logic to validate the email and password
        // For simplicity, we'll just check if they are not empty
        return  password.equals("9503510446");
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

}