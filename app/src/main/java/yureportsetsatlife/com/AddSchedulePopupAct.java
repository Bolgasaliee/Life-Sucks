package yureportsetsatlife.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddSchedulePopupAct extends Activity {

    Spinner year, month, day;
    EditText sch_name;

    public void Cancel(View v){
        finish();
    }

    public void Submit(View v){
        if(sch_name.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "일정 이름을 입력하세요.", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent();
            intent.putExtra("year_data", year.getSelectedItem().toString());
            intent.putExtra("month_data", month.getSelectedItem().toString());
            intent.putExtra("day_data", day.getSelectedItem().toString());
            intent.putExtra("sch_name_data", sch_name.getText().toString());
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
        setContentView(R.layout.act_add_schedule_popup);

        year = (Spinner)findViewById(R.id.year);
        month = (Spinner)findViewById(R.id.month);
        day = (Spinner)findViewById(R.id.day);
        sch_name = (EditText)findViewById(R.id.sch_name);
    }
}
