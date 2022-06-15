package com.example.hit.nhom5.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.nhom5.product.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();
    }

    private void setListener() {
        binding.txtLognIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            overridePendingTransition(0, 0);
            finish();
        });

        binding.btSignUp.setOnClickListener(v -> SignUp());
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public boolean isEmail(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public boolean isPassword(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    private boolean isValidSignUpDetails() {
        String strFirstName = binding.inputFirstName.getText().toString().trim();
        String strLastName = binding.inputLastName.getText().toString().trim();
        String strEmail = binding.inputEmailSignUp.getText().toString().trim();
        String strPassword = binding.inputPassword.getText().toString().trim();
        String strConfirmPassword = binding.inputPassword.getText().toString().trim();

        boolean is = false;

        if (strFirstName.isEmpty()) {
            showToast("Enter first name");
        } else if(strLastName.isEmpty()) {
            showToast("Enter last name");
        } else if (strEmail.isEmpty()) {
            showToast("Enter email");
        } else if (!isEmail(strEmail)) {
            showToast("Enter valid email");
        } else if (strPassword.isEmpty()) {
            showToast("Enter password");
        } else if(!isPassword(strPassword)) {
            showToast("Password must be 8-20 characters long, contain uppercase and lowercase characters, numbers and special characters");
        } else if (strConfirmPassword.isEmpty()) {
            showToast("Enter confirm password");
        } else if (!strPassword.equals(strConfirmPassword)) {
            showToast("Password && Confirm password must be same");
        } else is = true;

        return is;
    }

    private void SignUp() {
        if (isValidSignUpDetails()) {
            String email = binding.inputEmailSignUp.getText().toString().trim();
            String password = binding.inputPassword.getText().toString().trim();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();

            binding.progressBar4.setVisibility(View.VISIBLE);
            binding.btSignUp.setVisibility(View.INVISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                binding.progressBar4.setVisibility(View.INVISIBLE);
                                binding.btSignUp.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finishAffinity();
                            } else {
                                showToast("Authentication failed.");
                            }
                        }
                    });
        }
    }

    private Toast mToast;
    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            super.onBackPressed();
            return;
        } else {
            mToast = Toast.makeText(getApplicationContext(), "Press back again to exit the application", Toast.LENGTH_LONG);
            mToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}