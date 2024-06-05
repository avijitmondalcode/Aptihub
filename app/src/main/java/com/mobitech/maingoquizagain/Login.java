package com.mobitech.maingoquizagain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText etId,etPass;
    Button btnSignIn,btnSignUp;
    DbManager2 db;
    private FirebaseAuth auth;

    SharedPreferences sharedPreferences;

    public static final String fileName = "login";
    public static final String Username = "username";
    public static final String Password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        etId = findViewById(R.id.etId);
        etPass = findViewById(R.id.etPass);

        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);

        if(sharedPreferences.contains(Username)){
            Intent i = new Intent(Login.this,QuizActivity.class);
            startActivity(i);
        }

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        db = new DbManager2(Login.this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });

    }
    private void login(){
        String email = etId.getText().toString();
        String pass = etPass.getText().toString();

        Boolean emailpass = db.emailpass(email,pass);

        if (emailpass == true){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Username,email);
            editor.putString(Password,pass);
            editor.commit();
            Intent i = new Intent(Login.this,QuizActivity.class);
            i.putExtra("Email",email);
            startActivity(i);
        }else if(!email.equals("") && !pass.equals("")){
            //Toast.makeText(Login.this,"Wrong Details..",Toast.LENGTH_LONG).show();
            auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent i =new Intent(Login.this,AddNewQuestion.class);
                        startActivity(i);

                        Toast.makeText(Login.this,"Successful",Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(Login.this,"Unsuccessful",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else
            Toast.makeText(Login.this,"Invalid details...",Toast.LENGTH_LONG).show();
    }

}