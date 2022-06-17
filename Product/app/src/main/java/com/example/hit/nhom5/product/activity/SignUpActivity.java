package com.example.hit.nhom5.product.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.api_interface.ApiServer;
import com.example.hit.nhom5.product.databinding.ActivitySignUpBinding;
import com.example.hit.nhom5.product.model.SignUp;
import com.example.hit.nhom5.product.model.SignUpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
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

        binding.imgShowPassword.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (binding.inputPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    binding.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.imgShowPassword.setImageResource(R.drawable.ic_close_eye);
                    binding.inputPassword.setSelection(binding.inputPassword.getText().toString().length());
                } else {
                    binding.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.imgShowPassword.setImageResource(R.drawable.ic_eye);
                    binding.inputPassword.setSelection(binding.inputPassword.getText().toString().length());
                }
            }
        });

        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (binding.inputConfirmPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    binding.inputConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.imageView3.setImageResource(R.drawable.ic_close_eye);
                    binding.inputConfirmPassword.setSelection(binding.inputConfirmPassword.getText().toString().length());
                } else {
                    binding.inputConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.imageView3.setImageResource(R.drawable.ic_eye);
                    binding.inputConfirmPassword.setSelection(binding.inputConfirmPassword.getText().toString().length());
                }
            }
        });
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
        } else if (strLastName.isEmpty()) {
            showToast("Enter last name");
        } else if (strEmail.isEmpty()) {
            showToast("Enter email");
        } else if (!isEmail(strEmail)) {
            showToast("Enter valid email");
        } else if (strPassword.isEmpty()) {
            showToast("Enter password");
        } else if (!isPassword(strPassword)) {
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
            String firstName = binding.inputFirstName.getText().toString().trim();
            String lastName = binding.inputLastName.getText().toString().trim();
            String email = binding.inputEmailSignUp.getText().toString().trim();
            String password = binding.inputPassword.getText().toString().trim();

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            binding.progressBar4.setVisibility(View.VISIBLE);
            binding.btSignUp.setVisibility(View.INVISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                binding.progressBar4.setVisibility(View.GONE);
                                binding.btSignUp.setVisibility(View.VISIBLE);

                                HashMap<String, String> map = new HashMap<>();
                                map.put("firstName", firstName);
                                map.put("lastName", lastName);
                                map.put("email", email);

                                String id = task.getResult().getUser().getUid().toString();

                                database.getReference().child("Users").child(id).setValue(map);
                            }
                        }
                    });

            ApiServer.apiServer.signUp(new SignUp(firstName, lastName, email, password)).enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.isSuccessful()) {
                        binding.progressBar4.setVisibility(View.GONE);
                        binding.btSignUp.setVisibility(View.VISIBLE);

                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        overridePendingTransition(0, 0);
                        finishAffinity();
                    } else {
                        binding.progressBar4.setVisibility(View.GONE);
                        binding.btSignUp.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    binding.progressBar4.setVisibility(View.GONE);
                    binding.btSignUp.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Sign Up Failure.", Toast.LENGTH_LONG).show();
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