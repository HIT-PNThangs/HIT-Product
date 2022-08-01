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
import com.example.hit.nhom5.product.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Dialog dialog;

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
                }
        );

        binding.btLogin.setOnClickListener(v -> Login());

        binding.imgShowPassword.setOnClickListener(v -> {
                    if (binding.inputPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                        binding.inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        binding.imgShowPassword.setImageResource(R.drawable.ic_close_eye);
                    } else {
                        binding.inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        binding.imgShowPassword.setImageResource(R.drawable.ic_eye);
                    }

                    binding.inputPassword.setSelection(binding.inputPassword.getText().toString().length());
                }
        );

        binding.forgetPassword.setOnClickListener(v -> onClickForgetPassword());

        binding.btnGoogle.setOnClickListener(v -> onClickSignInGoogle());
    }

    private void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public boolean isEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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
            binding.btLogin.setVisibility(View.GONE);

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        binding.progressBar3.setVisibility(View.GONE);
                        binding.btLogin.setVisibility(View.VISIBLE);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    })
                    .addOnFailureListener(e -> {
                        binding.progressBar3.setVisibility(View.GONE);
                        binding.btLogin.setVisibility(View.VISIBLE);

                        Log.d("Sign In: ", e.getMessage());
                        showToast("Sign in: " + e.getMessage());
                    });
        }
    }

    private void onClickForgetPassword() {
        dialog = new Dialog(SignInActivity.this);

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

        auth.sendPasswordResetEmail(str).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                showToast("Check your email to reset your password!");
                dialog.cancel();
            } else {
                showToast("Try again! Something wrong happened.");
                dialog.cancel();
            }
        });
    }

    private Toast mToast;
    private long backPressedTime;

    @Override
    public void onBackPressed() {
        if (backPressedTime + 1500 > System.currentTimeMillis()) {
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