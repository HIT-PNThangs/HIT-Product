package com.example.hit.nhom5.product.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.api_interface.ApiServer;
import com.example.hit.nhom5.product.databinding.ActivitySignInBinding;
import com.example.hit.nhom5.product.model.GetUserByEmailResponse;
import com.example.hit.nhom5.product.model.Login;
import com.example.hit.nhom5.product.model.LoginResponse;
import com.example.hit.nhom5.product.model.User;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        setListener();
    }

    private void setListener() {
        binding.txtSignUp.setOnClickListener(v -> {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
        );

        binding.btLogin.setOnClickListener(v -> Login());

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
            }
        );

        binding.forgetPassword.setOnClickListener(v -> onClickForgetPassword());

        binding.btnGoogle.setOnClickListener(v -> onClickSignInGoogle());
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public boolean isEmail(String email) {
        String EMAIL_PATTERN = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1}$";
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public boolean isPassword(String password) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    private void Login() {
        String email = binding.inputEmailSignIn.getText().toString();
        String password = binding.inputPassword.getText().toString();

        if (email.isEmpty()) {
            showToast("Enter email");
        } else if (!isEmail(email)) {
            showToast("Enter valid email");
        } else if (password.isEmpty()) {
            showToast("Enter password");
        } else if (!isPassword(password)) {
            showToast("Enter valid password");
        } else {
            binding.progressBar3.setVisibility(View.VISIBLE);
            binding.btLogin.setVisibility(View.INVISIBLE);

            auth.signInWithEmailAndPassword(email, password);

            ApiServer.apiServer.login(new Login(email, password)).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    LoginResponse loginResponse = response.body();

                    if(response.isSuccessful() && loginResponse != null) {
                        binding.progressBar3.setVisibility(View.GONE);
                        binding.btLogin.setVisibility(View.VISIBLE);

                        ApiServer.apiServer.getUserByEmail(email).enqueue(new Callback<GetUserByEmailResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<GetUserByEmailResponse> call, @NonNull Response<GetUserByEmailResponse> response) {
                                if(response.body() != null && response.isSuccessful()) {
                                    User user = response.body().getResult();

                                    if(!user.getStatus()) {
                                        Intent intent = new Intent(getApplicationContext(), UpdateInformationActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(0, 0);
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(0, 0);
                                    }
                                } else {
                                    showToast(response.message());
                                    Log.d("Sign In", Integer.toString(response.code()));
                                    Log.d("Sign In", response.message());
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<GetUserByEmailResponse> call, @NonNull Throwable t) {
                                showToast(t.getMessage());
                                Log.d("Sign In", t.getMessage());
                            }
                        });
                    } else {
                        binding.progressBar3.setVisibility(View.GONE);
                        binding.btLogin.setVisibility(View.VISIBLE);
                        Log.d("Sign In: ", Integer.toString(response.code()).toString());
                        Log.d("Sign In: ", response.message());

                        showToast("Login Failure.");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    binding.progressBar3.setVisibility(View.GONE);
                    binding.btLogin.setVisibility(View.VISIBLE);
                    Log.d("Sign In: ", t.getMessage());
                    showToast("Login Failure.");
                }
            });

//            ApiServer.apiServer.getUserByEmail(email).enqueue(new Callback<GetUserByEmailResponse>() {
//                @Override
//                public void onResponse(@NonNull Call<GetUserByEmailResponse> call, @NonNull Response<GetUserByEmailResponse> response) {
//                    if(response.body() != null && response.isSuccessful()) {
//                        User user = response.body().getResult();
//
//                        if(!user.getStatus()) {
//                            Intent intent = new Intent(getApplicationContext(), UpdateInformationActivity.class);
//                            startActivity(intent);
//                            overridePendingTransition(0, 0);
//                        } else {
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                            startActivity(intent);
//                            overridePendingTransition(0, 0);
//                        }
//                    } else {
//                        showToast(response.message());
//                        Log.d("Sign In", Integer.toString(response.code()));
//                        Log.d("Sign In", response.message());
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<GetUserByEmailResponse> call, @NonNull Throwable t) {
//                    showToast(t.getMessage());
//                    Log.d("Sign In", t.getMessage());
//                }
//            });
        }
    }

    private void onClickForgetPassword() {
        Dialog dialog = new Dialog(SignInActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.show();

        Button btCancel, btAgree;
        EditText edtDialogEmail;

        btAgree = dialog.findViewById(R.id.btUpdate);
        btCancel = dialog.findViewById(R.id.btCancel);
        edtDialogEmail = dialog.findViewById(R.id.inputDialogEmail);

        btCancel.setOnClickListener(v -> dialog.cancel());

        btAgree.setOnClickListener(v -> {
                String strEmail = edtDialogEmail.getText().toString().trim();

                if (strEmail.isEmpty()) {
                    showToast("Enter email");
                } else if (!isEmail(strEmail)) {
                    showToast("Enter valid email");
                } else {
                    resetPassword(strEmail);
                }
            });
    }

    private void onClickSignInGoogle() {

    }

    private void resetPassword(String str) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(str).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    showToast("Check your email to reset your password!");
                } else {
                    showToast("Try again! Something wrong happened.");
                }
            }
        });
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