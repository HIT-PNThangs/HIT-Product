package com.example.hit.nhom5.product.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.hit.nhom5.product.R;
import com.example.hit.nhom5.product.databinding.ActivityUpdateInformationBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateInformationActivity extends AppCompatActivity {

    private ActivityUpdateInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();
    }

    private void setListener() {
        binding.imageProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

        binding.btCancel.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0, 0);
        });

        binding.btUpdate.setOnClickListener(v -> Update());

        binding.back.setOnClickListener(v -> onBackPressed());
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new
                    ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK) {
                    if(result.getData() != null) {
                        Uri imageUri = result.getData().getData();

                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.txtAddImage.setVisibility(View.GONE);
                            binding.imageProfile.setImageBitmap(bitmap);
                        } catch (FileNotFoundException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            });

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void Update() {
        if(binding.txtAddImage.getVisibility() == View.VISIBLE) {
            showToast("Select Image Avatar");
        } else if(binding.updateNumberPhone.getText().toString().isEmpty()) {
            showToast("Enter Phone Number");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}