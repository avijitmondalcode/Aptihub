package com.mobitech.maingoquizagain;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerDialog {
    
    private Context mContext;
    private Dialog TimerDialog;

    private TextView textviewTimeUP;

    private QuizActivity mquizActivity;

    public TimerDialog(Context mContext) {
        this.mContext = mContext;
    }
    
    public void timerDialog(final QuizActivity quizActivity){
        mquizActivity = quizActivity;
        
        TimerDialog = new Dialog(mContext);
        TimerDialog.setContentView(R.layout.timer_dialog);
        
        final Button btTimer =  (Button) TimerDialog.findViewById(R.id.bt_timer);
        
        
       
        btTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimerDialog.dismiss();
                mquizActivity.showQuestions();
                //Intent intent = new Intent(mContext,PlayActivity.class);
                //mContext.startActivity(intent);


            }
        });

        TimerDialog.show();
        TimerDialog.setCancelable(false);
        TimerDialog.setCanceledOnTouchOutside(false);

        TimerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
    }

   
}
