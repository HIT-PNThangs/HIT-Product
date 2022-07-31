package com.example.hit.nhom5.product.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hit.nhom5.product.databinding.ActivityUpdateInformationBinding;
import com.example.hit.nhom5.product.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class UpdateInformationActivity extends AppCompatActivity {
    private ActivityUpdateInformationBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storageReference;
    Uri imageUri;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Users")
                .child(Objects.requireNonNull(auth.getUid()));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);

                if (user != null) {
                    binding.updateName.setText(user.getName());
                    binding.updateNumberPhone.setText(user.getTelephone());
                    if (user.getAvt() != null) {
                        Glide.with(getApplicationContext()).load(user.getAvt()).into(binding.imageProfile);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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

    private final ActivityResultLauncher<Intent> pickImage =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                imageUri = result.getData().getData();

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
        showToast("123");
        if (binding.txtAddImage.getVisibility() == View.VISIBLE) {
            showToast("Select Image Avatar");
        } else if (binding.updateName.getText().toString().isEmpty()) {
            showToast("Enter Name");
        } else if (binding.updateNumberPhone.getText().toString().isEmpty()) {
            showToast("Enter Phone Number");
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
            storageReference = FirebaseStorage.
                    getInstance().
                    getReference("Images/" + formatter.format(new Date()));

            storageReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        storageReference.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    user.setAvt(uri.toString());
                                });
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
        finish();
    }
}