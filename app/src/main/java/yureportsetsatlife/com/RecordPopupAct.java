package yureportsetsatlife.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class RecordPopupAct extends Activity {

    EditText title;
    Spinner start_hour, end_hour;
    EditText memo;
    RatingBar ratingBar;
    float rate;
    int sequence;

    public void Cancel(View v){
        finish();
    }

    public void Submit(View v){
        if(title.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "제목을 입력하세요.", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent();
            intent.putExtra("title_data", title.getText().toString());
            intent.putExtra("start_hour_data", start_hour.getSelectedItem().toString());
            intent.putExtra("end_hour_data", end_hour.getSelectedItem().toString());
            intent.putExtra("memo_data", memo.getText().toString());
            intent.putExtra("satisfaction_data", String.valueOf(rate));
            sequence++;
            intent.putExtra("sequence_data", String.valueOf(sequence));
            setResult(RESULT_OK, intent);
            finish();
        }
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
        setContentView(R.layout.act_record_popup);

        title = (EditText)findViewById(R.id.title);
        start_hour = (Spinner)findViewById(R.id.start_hour);
        end_hour = (Spinner)findViewById(R.id.end_hour);
        memo = (EditText) findViewById(R.id.memo);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        Intent getData = getIntent();
        sequence = Integer.parseInt(getData.getStringExtra("sequence_data"));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = rating;
            }
        });
    }
}
