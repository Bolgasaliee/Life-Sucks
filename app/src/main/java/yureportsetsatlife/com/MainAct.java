package yureportsetsatlife.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainAct extends AppCompatActivity {

    TextView[] time_bar = new TextView[24];
    TextView[] sat = new TextView[5];
    double tot = 0.0, avg = 0.0;
    int totaltime = 0;
    TextView Wtd;
    int sequence = 0;
    String allresult = "";

    Date currentTime = Calendar.getInstance().getTime();
    String year = new SimpleDateFormat("yyyy", Locale.getDefault()).format(currentTime);
    String month = new SimpleDateFormat("MM", Locale.getDefault()).format(currentTime);
    String day = new SimpleDateFormat("dd", Locale.getDefault()).format(currentTime);

    public void Suggestion(View v) {
        Random random = new Random();
        int suggest = random.nextInt(5);
        String suggestion = "";
        switch (suggest)
        {
            case 0:
                suggestion = "시체놀이";
                break;
            case 1:
                suggestion = "사체놀이";
                break;
            case 2:
                suggestion = "송장놀이";
                break;
            case 3:
                suggestion = "주검놀이";
                break;
            case 4:
                suggestion = "시신놀이";
                break;
        }
        suggestion = suggestion+"를 하는건 어때요?";
        Wtd.setText(suggestion);
    }

    public void PopupRecord(View v) {
        startActivityForResult(new Intent(this, RecordPopupAct.class).putExtra("sequence_data", String.valueOf(sequence)),1);
    }

    public void End_day(View v) {
        startActivityForResult(new Intent(this, EndPopupAct.class).putExtra("avg_data", String.valueOf(avg)),2);
    }

    public void ToHelp(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Bolgasaliee/set-sat-life/wiki/%EC%95%B1-%EC%84%A4%EA%B3%84%EC%84%9C(%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4)")));
    }
    public void ToPg1(View v) {
        Toast.makeText(getApplicationContext(), "현재 페이지 입니다", Toast.LENGTH_SHORT).show();
    }
    public void ToPg2(View v) {
        startActivity(new Intent(this, CalenderAct.class));
    }
    public void ToPg3(View v) {
        startActivity(new Intent(this, ChartAct.class));
    }
    public void ToSettings(View v) {
        startActivity(new Intent(this, SettingsAct.class));
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        time_bar[0] = (TextView)findViewById(R.id.am1_bar);
        time_bar[1] = (TextView)findViewById(R.id.am2_bar);
        time_bar[2] = (TextView)findViewById(R.id.am3_bar);
        time_bar[3] = (TextView)findViewById(R.id.am4_bar);
        time_bar[4] = (TextView)findViewById(R.id.am5_bar);
        time_bar[5] = (TextView)findViewById(R.id.am6_bar);
        time_bar[6] = (TextView)findViewById(R.id.am7_bar);
        time_bar[7] = (TextView)findViewById(R.id.am8_bar);
        time_bar[8] = (TextView)findViewById(R.id.am9_bar);
        time_bar[9] = (TextView)findViewById(R.id.am10_bar);
        time_bar[10] = (TextView)findViewById(R.id.am11_bar);
        time_bar[11] = (TextView)findViewById(R.id.am12_bar);
        time_bar[12] = (TextView)findViewById(R.id.pm1_bar);
        time_bar[13] = (TextView)findViewById(R.id.pm2_bar);
        time_bar[14] = (TextView)findViewById(R.id.pm3_bar);
        time_bar[15] = (TextView)findViewById(R.id.pm4_bar);
        time_bar[16] = (TextView)findViewById(R.id.pm5_bar);
        time_bar[17] = (TextView)findViewById(R.id.pm6_bar);
        time_bar[18] = (TextView)findViewById(R.id.pm7_bar);
        time_bar[19] = (TextView)findViewById(R.id.pm8_bar);
        time_bar[20] = (TextView)findViewById(R.id.pm9_bar);
        time_bar[21] = (TextView)findViewById(R.id.pm10_bar);
        time_bar[22] = (TextView)findViewById(R.id.pm11_bar);
        time_bar[23] = (TextView)findViewById(R.id.pm12_bar);

        sat[0] = (TextView)findViewById(R.id.sat);
        sat[1] = (TextView)findViewById(R.id.sat2);
        sat[2] = (TextView)findViewById(R.id.sat3);
        sat[3] = (TextView)findViewById(R.id.sat4);
        sat[4] = (TextView)findViewById(R.id.sat5);
        Wtd = (TextView)findViewById(R.id.Wtd);

        setTitle(year+"년 "+month+"월 "+day+"일");

        for(int i = 0; i < 5; i++)
        {
            sat[i].setBackgroundResource(R.drawable.border);
        }
        sat[2].setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                String title_data = data.getStringExtra("title_data");
                int start_hour_data = Integer.parseInt(data.getStringExtra("start_hour_data"));
                int end_hour_data = Integer.parseInt(data.getStringExtra("end_hour_data"));
                String memo_data = data.getStringExtra("memo_data");
                double satisfaction_data = Double.valueOf(data.getStringExtra("satisfaction_data")).doubleValue();
                sequence = Integer.parseInt(data.getStringExtra("sequence_data"));
                if (sequence == 1)
                    allresult = "("+start_hour_data+":00 ~ "+end_hour_data+":00) "+title_data;
                else
                    allresult = allresult+"\n"+"("+start_hour_data+":00 ~ "+end_hour_data+":00) "+title_data;
                Wtd.setText(allresult);

                tot += (end_hour_data-start_hour_data)*satisfaction_data;
                totaltime += end_hour_data-start_hour_data;
                avg = tot/totaltime;

                for(int i = 0; i < 5; i++)
                {
                    sat[i].setBackgroundResource(R.drawable.border);
                }

                if(avg > 0.5)
                    sat[4].setBackgroundResource(R.drawable.border_fill_green);
                if(avg > 1.5)
                    sat[3].setBackgroundResource(R.drawable.border_fill_green);
                if(avg > 2.5)
                    sat[2].setBackgroundResource(R.drawable.border_fill_green);
                if(avg > 3.5)
                    sat[1].setBackgroundResource(R.drawable.border_fill_green);
                if(avg > 4.5)
                    sat[0].setBackgroundResource(R.drawable.border_fill_green);
                sat[2].setText(String.format("%.0f",avg/5*100)+"%");

                switch (sequence % 4) {
                    case 1:
                        for(int i = start_hour_data; i<end_hour_data; i++)
                            time_bar[i].setBackgroundResource(R.drawable.border_fill_magenta);
                        break;
                    case 2:
                        for(int i = start_hour_data; i<end_hour_data; i++)
                            time_bar[i].setBackgroundResource(R.drawable.border_fill_yellow);
                        break;
                    case 3:
                        for(int i = start_hour_data; i<end_hour_data; i++)
                            time_bar[i].setBackgroundResource(R.drawable.border_fill_green);
                        break;
                    case 0:
                        for(int i = start_hour_data; i<end_hour_data; i++)
                            time_bar[i].setBackgroundResource(R.drawable.border_fill_cyan);
                        break;
                }
            }
        }
        if(requestCode==2){
            if(resultCode==RESULT_OK)
                startActivity(new Intent(this, MainAct.class));
        }
    }
}
