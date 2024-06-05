package com.mobitech.maingoquizagain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAllQues extends AppCompatActivity {

    ListView listView;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    int correct = 0;

    ArrayList<Questions> alist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_ques);

        listView = findViewById(R.id.lvAllQues);
        QuizDbHelper dbManager = new QuizDbHelper(ViewAllQues.this);

        //setColor();

        alist = dbManager.getAllData();

        listView.setAdapter(new myAdapter());

    }

    public void setColor(){
        if(correct == R.id.tvOpt1){
            tv2.setBackgroundColor(getResources().getColor(R.color.green));
        }else if(correct == R.id.tvOpt2){
            tv3.setBackgroundColor(getResources().getColor(R.color.green));
        }else if(correct == R.id.tvOpt3){
            tv4.setBackgroundColor(getResources().getColor(R.color.green));
        }else if(correct == R.id.tvOpt4){
            tv5.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    public class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return alist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View row = layoutInflater.inflate(R.layout.child_show,null);
            tv1 = row.findViewById(R.id.tvQues);
            tv2 = row.findViewById(R.id.tvOpt1);
            tv3 = row.findViewById(R.id.tvOpt2);
            tv4 = row.findViewById(R.id.tvOpt3);
            tv5 = row.findViewById(R.id.tvOpt4);
            tv6 = row.findViewById(R.id.tvAnswer);

            setColor();

            tv1.setText(alist.get(position).getQuestion());
            tv2.setText(alist.get(position).getOption1());
            tv3.setText(alist.get(position).getOption2());
            tv4.setText(alist.get(position).getOption3());
            tv5.setText(alist.get(position).getOption4());
            correct = alist.get(position).getAnswerNr();
            //Log.e("Correct",Integer.toString(correct));
            tv6.setText(Integer.toString(alist.get(position).getAnswerNr()));

            return row;
        }
    }
}