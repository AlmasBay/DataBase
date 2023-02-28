package com.example.almasdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPass;
    private Button btnEnter;
    private TextView tvReg;
    private TextView tvReset;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmailAuth);
        etPass = findViewById(R.id.etPassAuth);
        btnEnter = findViewById(R.id.btnEnter);
        tvReg = findViewById(R.id.tvReg);
        tvReset = findViewById(R.id.tvReset);

        mAuth = FirebaseAuth.getInstance();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailStr = etEmail.getText().toString();
                String passStr = etPass.getText().toString();
                if (!emailStr.isEmpty() && !passStr.isEmpty()){
                    mAuth.signInWithEmailAndPassword(emailStr.trim(),passStr.trim())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(MainActivity.this,"Login Succes",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else Toast.makeText(MainActivity.this,"Fields cant't be empty",Toast.LENGTH_SHORT).show();


            }
        });
         tvReg.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
             }
         });

         tvReset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (!etEmail.getText().toString().isEmpty()){
                     mAuth.sendPasswordResetEmail(etEmail.getText().toString())
                             .addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void unused) {
                                     Toast.makeText(MainActivity.this,"Email sent",Toast.LENGTH_SHORT).show();
                                 }
                             })
                             .addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                 }
                             });
                 }
                 else Toast.makeText(MainActivity.this,"Email field is empty!!!",Toast.LENGTH_SHORT).show();
             }
         });
    }
}