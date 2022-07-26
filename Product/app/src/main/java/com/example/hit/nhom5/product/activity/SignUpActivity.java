package com.example.hit.nhom5.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivitySignUpBinding;
import com.example.hit.nhom5.product.model.Role;
import com.example.hit.nhom5.product.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        setListener();
    }

    private void setListener() {
        binding.txtLognIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            overridePendingTransition(0, 0);
            finish();
        });

        binding.btSignUp.setOnClickListener(v -> SignUp());

        binding.imgShowPassword.setOnClickListener(v -> {
            if (binding.inputPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                binding.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.imgShowPassword.setImageResource(R.drawable.ic_close_eye);
                binding.inputPassword.setSelection(binding.inputPassword.getText().toString().length());
            } else {
                binding.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.imgShowPassword.setImageResource(R.drawable.ic_eye);
                binding.inputPassword.setSelection(binding.inputPassword.getText().toString().length());
            }
        });

        binding.imageView3.setOnClickListener(v -> {
            if (binding.inputConfirmPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                binding.inputConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                binding.imageView3.setImageResource(R.drawable.ic_close_eye);
                binding.inputConfirmPassword.setSelection(binding.inputConfirmPassword.getText().toString().length());
            } else {
                binding.inputConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                binding.imageView3.setImageResource(R.drawable.ic_eye);
                binding.inputConfirmPassword.setSelection(binding.inputConfirmPassword.getText().toString().length());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public boolean isEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public boolean isPassword(String password) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    private boolean isValidSignUpDetails() {
        String strName = binding.inputName.getText().toString().trim();
        String strEmail = binding.inputEmailSignUp.getText().toString().trim();
        String strPassword = binding.inputPassword.getText().toString().trim();
        String strConfirmPassword = binding.inputPassword.getText().toString().trim();

        boolean is = false;

        if (strName.isEmpty()) {
            showToast("Enter first name");
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
        } else
            is = true;

        return is;
    }

    private void SignUp() {
        if (isValidSignUpDetails()) {
            String name = binding.inputName.getText().toString().trim();
            String email = binding.inputEmailSignUp.getText().toString().trim();
            String password = binding.inputPassword.getText().toString().trim();

            binding.progressBar4.setVisibility(View.VISIBLE);
            binding.btSignUp.setVisibility(View.INVISIBLE);

            // Create user on Firebase
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            binding.progressBar4.setVisibility(View.GONE);
                            binding.btSignUp.setVisibility(View.VISIBLE);

                            User user = new User();

                            user.setName(name);
                            user.setEmail(email);
                            user.setStatus(false);
                            user.setAddress("");
                            user.setTelephone("");
                            user.setCarts(new ArrayList<>());
                            List<Role> roles = new ArrayList<>();
                            roles.add(new Role(2, "ROLE_USER"));
                            user.setRoles(roles);

                            String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                            database.getReference().child("Users").child(id).setValue(user);

                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);

                            intent.putExtra("data", user);

                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            overridePendingTransition(0, 0);
                            finishAffinity();
                        }
                    }).addOnFailureListener(e -> {
                        binding.progressBar4.setVisibility(View.GONE);
                        binding.btSignUp.setVisibility(View.VISIBLE);

                        showToast(e.getMessage());
                        Log.d("Sign Up", "onFailure: " + e.getMessage());
                    });

//            // Create user on Database
//            SignUp signUp = new SignUp(name, email, password);
//
//            ApiServer.apiServer.signUp(signUp).enqueue(new Callback<SignUpResponse>() {
//                @Override
//                public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
//                    binding.progressBar4.setVisibility(View.GONE);
//                    binding.btSignUp.setVisibility(View.VISIBLE);
//
//                    if (response.isSuccessful()) {
//                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
//                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                        overridePendingTransition(0, 0);
//                        finishAffinity();
//                    } else {
//                        Log.d("Sign Up: ", Integer.toString(response.code()));
//                        Log.d("Sign Up: ", response.message());
//                        if (!response.message().isEmpty())
//                            showToast(response.message());
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
//                    binding.progressBar4.setVisibility(View.GONE);
//                    binding.btSignUp.setVisibility(View.VISIBLE);
//
//                    showToast(t.getMessage());
//                    Log.d("Sign Up", "onFailure: " + t.getMessage());
//                }
//            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(0, 0);
        finish();
    }
}