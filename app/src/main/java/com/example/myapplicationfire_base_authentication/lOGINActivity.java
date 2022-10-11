package com.example.myapplicationfire_base_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplicationfire_base_authentication.databinding.ActivityLoginactivityBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class lOGINActivity extends AppCompatActivity {
    ActivityLoginactivityBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.Emailaddress.toString().trim();
                String password = binding.Password.getPrivateImeOptions().toString().trim();

                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();

                                Toast.makeText(lOGINActivity.this, "Login suceessful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(lOGINActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        binding.reset.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                 String email = binding.Emailaddress.getText().toString();
                                                 progressDialog.setTitle("sending mail");
                                                 progressDialog.show();
                                                 firebaseAuth.sendPasswordResetEmail(email)
                                                         .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                             @Override
                                                             public void onSuccess(Void unused) {
                                                                 progressDialog.cancel();
                                                                 Toast.makeText(lOGINActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                                             }
                                                      })
                                                         .addOnFailureListener(new OnFailureListener() {
                                                             @Override
                                                             public void onFailure(@NonNull Exception e) {
                                                                 progressDialog.cancel();
                                                                 Toast.makeText(lOGINActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                             }
                                                         });

                                             }
                                         }
        );
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(lOGINActivity.this,MainActivity.class));
            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(lOGINActivity.this,textActivity.class));
            }
        });

    }

    }
