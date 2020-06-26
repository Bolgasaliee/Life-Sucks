package yureportsetsatlife.com;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalenderAct extends AppCompatActivity {

    TextView cal_date;
    CompactCalendarView calendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy", Locale.getDefault());
    String sch_name_data = "asd";

    public void ScrollPrev(View v) {
        calendar.scrollLeft();
    }

    public void ScrollNext(View v) {
        calendar.scrollRight();
    }

    public void PopupManageSchedule(View v) {
        startActivityForResult(new Intent(this, ManaSchePopupAct.class),3);
    }

    public void PopupManageRecord(View v) {
        startActivityForResult(new Intent(this, ManaRecordPopupAct.class),4);
    }

    public void PopupAddSchedule(View v) {
        startActivityForResult(new Intent(this, AddSchedulePopupAct.class),5);
    }

    public void ToHelp(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Bolgasaliee/set-sat-life/wiki/%EC%95%B1-%EC%84%A4%EA%B3%84%EC%84%9C(%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4)")));
    }
    public void ToPg1(View v) {
        startActivity(new Intent(this, MainAct.class));
    }
    public void ToPg2(View v) {
        Toast.makeText(getApplicationContext(), "현재 페이지 입니다", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.act_calender);

        setTitle("캘린더 2020");

        cal_date = (TextView)findViewById(R.id.cal_date);
        calendar = (CompactCalendarView)findViewById(R.id.calendar);
        calendar.setUseThreeLetterAbbreviation(true);

        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
            }

            @Override
            public void onMonthScroll(Date date_today) {
                String month = dateFormatMonth.format(date_today);
                int year = Integer.parseInt(dateFormatYear.format(date_today));
                if(month.equals("January"))
                    cal_date.setText("1월");
                if(month.equals("February"))
                    cal_date.setText("2월");
                if(month.equals("March"))
                    cal_date.setText("3월");
                if(month.equals("April"))
                    cal_date.setText("4월");
                if(month.equals("May"))
                    cal_date.setText("5월");
                if(month.equals("June"))
                    cal_date.setText("6월");
                if(month.equals("July"))
                    cal_date.setText("7월");
                if(month.equals("August"))
                    cal_date.setText("8월");
                if(month.equals("September"))
                    cal_date.setText("9월");
                if(month.equals("October"))
                    cal_date.setText("10월");
                if(month.equals("November"))
                    cal_date.setText("11월");
                if(month.equals("December"))
                    cal_date.setText("12월");
                setTitle("캘린더 "+year);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3) {
            if (resultCode == RESULT_OK) {

            }
        }
        if(requestCode==4) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "미구현 기능", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==5){
            if(resultCode==RESULT_OK){
                sch_name_data = data.getStringExtra("sch_name_data");
                int year_data = Integer.parseInt(data.getStringExtra("year_data"));
                int month_data = Integer.parseInt(data.getStringExtra("month_data"));
                int day_data = Integer.parseInt(data.getStringExtra("day_data"));
                int day_tot = 0;
                long standard = 1577869200000L;//2020-01-01-09:00:00
                long one_day = 86400000L;

                if(year_data>=2020&&month_data>=1&&day_data>=1)
                {
                    for (int i = year_data-2020; i>0; i--)
                    {
                        if(i == 1)
                            day_tot += 366;
                        else
                            day_tot += 365;
                    }

                    switch (month_data-1){
                        case 11:
                            day_tot += 30;
                        case 10:
                            day_tot += 31;
                        case 9:
                            day_tot += 30;
                        case 8:
                            day_tot += 31;
                        case 7:
                            day_tot += 31;
                        case 6:
                            day_tot += 30;
                        case 5:
                            day_tot += 31;
                        case 4:
                            day_tot += 30;
                        case 3:
                            day_tot += 31;
                        case 2:
                            if((year_data)%4 == 0)
                                day_tot += 29;
                            else
                                day_tot += 28;
                        case 1:
                            day_tot += 31;
                        case 0:
                            day_tot += day_data-1;
                    }

                    long sch_date = standard+day_tot*one_day;
                    Event schedule = new Event(Color.RED, sch_date, sch_name_data);
                    calendar.addEvent(schedule);
                }
            }
        }
    }
}
