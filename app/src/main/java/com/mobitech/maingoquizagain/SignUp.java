package com.mobitech.maingoquizagain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    EditText etId,etPass,etName;
    Button btnSignUp,btnBack;

    DbManager2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etId = findViewById(R.id.etUId);
        etPass = findViewById(R.id.etUPass);
        etName = findViewById(R.id.etName);

        db = new DbManager2(SignUp.this);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnBack = findViewById(R.id.btnback);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String pass = etPass.getText().toString();
                String name = etName.getText().toString();

                if (id.equals("") || pass.equals("") || name.equals("")){
                    Toast.makeText(SignUp.this,"Field can not be blank",Toast.LENGTH_LONG).show();
                }else {
                    Boolean insert = db.insert(id,pass,name);
                    if(insert == true){
                        Toast.makeText(SignUp.this,"Register Successful",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });
    }

}