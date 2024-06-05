package com.mobitech.maingoquizagain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewQuestion extends AppCompatActivity {
    EditText et1,et2,et3,et4,et5,etAns;
    Button btnSubmitAns;
    TextView tvTotal;
    static int total = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_question);

        et1 = findViewById(R.id.etQuestion);
        et2 = findViewById(R.id.etOpt1);
        et3 = findViewById(R.id.etOpt2);
        et4 = findViewById(R.id.etOpt3);
        et5 = findViewById(R.id.etOpt4);
        etAns = findViewById(R.id.etAnswer);
        tvTotal = findViewById(R.id.tvTotal);

        btnSubmitAns = findViewById(R.id.btnSubQues);

        btnSubmitAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ques = et1.getText().toString();
                String op1 = et2.getText().toString();
                String op2 = et3.getText().toString();
                String op3 = et4.getText().toString();
                String op4 = et5.getText().toString();
                int answer = Integer.parseInt(etAns.getText().toString());

                if (ques.equals("") || op1.equals("") || op2.equals("")|| op3.equals("")|| op3.equals("")|| op4.equals("")){
                    Toast.makeText(AddNewQuestion.this,"Field can not be blank",Toast.LENGTH_LONG).show();
                }else {
                    QuizDbHelper quizDbHelper = new QuizDbHelper(AddNewQuestion.this);
                    Boolean result = quizDbHelper.insertQuestion(ques,op1,op2,op3,op4,answer);
                    if(result == true){
                        etAns.setText(null);
                        et1.setText(null);
                        et2.setText(null);
                        et3.setText(null);
                        et4.setText(null);
                        et5.setText(null);
                        etAns.setText(null);
                        //String tot = Integer.toString(total);
                        tvTotal.setText(Integer.toString(total));
                        total++;
                        Toast.makeText(AddNewQuestion.this,"Successful from Admin",Toast.LENGTH_LONG).show();
                        //Intent i = new Intent(getApplicationContext(),QuizActivity.class);
                        //startActivity(i);
                    }
                }
            }
        });
    }
}