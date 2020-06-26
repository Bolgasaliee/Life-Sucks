package yureportsetsatlife.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EndPopupAct extends Activity {

    double avg;
    ProgressBar avg_satisfaction_bar;
    TextView avg_satisfaction;
    TextView evaluate;

    public void Cancel(View v){
        finish();
    }

    public void Submit(View v){
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.act_end_popup);

        avg_satisfaction_bar = (ProgressBar)findViewById(R.id.avg_satisfaction_bar);
        avg_satisfaction = (TextView)findViewById(R.id.avg_satisfaction);
        evaluate = (TextView)findViewById(R.id.evaluate);

        Intent getData = getIntent();
        avg = Double.valueOf(getData.getStringExtra("avg_data")).doubleValue();
        avg_satisfaction_bar.setProgress((int)Math.round(avg/5*100));
        avg_satisfaction.setText(String.format("%.2f",avg/5*100)+"%");

        if (avg <= 5)
            evaluate.setText("대단해요! 만족스러운 하루!");
        if (avg <= 4)
            evaluate.setText("충분히 만족스러운 하루!");
        if (avg <= 3)
            evaluate.setText("조금만 더 노력해봐요!");
        if (avg <= 2)
            evaluate.setText("무슨 안좋은일 있나요?");
        if (avg <= 1)
            evaluate.setText("잘못 누르신거 아닌가요?");
    }
}
