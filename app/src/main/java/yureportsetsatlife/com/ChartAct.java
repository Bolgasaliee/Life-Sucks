package yureportsetsatlife.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

public class ChartAct extends AppCompatActivity {

    BarChart chart1, chart2;

    public void ToHelp(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Bolgasaliee/set-sat-life/wiki/%EC%95%B1-%EC%84%A4%EA%B3%84%EC%84%9C(%EC%84%9C%EB%B9%84%EC%8A%A4-%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4)")));
    }
    public void ToPg1(View v) {
        startActivity(new Intent(this, MainAct.class));
    }
    public void ToPg2(View v) {
        startActivity(new Intent(this, CalenderAct.class));
    }
    public void ToPg3(View v) {
        Toast.makeText(getApplicationContext(), "현재 페이지 입니다", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.act_chart);

        chart1 = (BarChart) findViewById(R.id.chart1);
        chart2 = (BarChart) findViewById(R.id.chart2);

        chart1.addBar(new BarModel(2.3f, 0xFF123456));
        chart1.addBar(new BarModel(2f, 0xFF343456));
        chart1.addBar(new BarModel(4f, 0xFF563456));
        chart1.addBar(new BarModel(1.1f, 0xFF873F56));
        chart1.addBar(new BarModel(2.7f, 0xFF56B7F1));
        chart1.addBar(new BarModel(2f, 0xFF343456));
        chart2.addBar(new BarModel(0.4f, 0xFF1FF4AC));
        chart2.addBar(new BarModel(4f, 0xFF1BA4E6));
        chart2.addBar(new BarModel(1.3f, 0xFF123456));
        chart2.addBar(new BarModel(3.5f, 0xFF56B7F1));
        chart2.addBar(new BarModel(3.7f, 0xFF563456));
        chart2.addBar(new BarModel(1.1f, 0xFF873F56));

        chart1.startAnimation();
        chart2.startAnimation();

        setTitle("통계");
    }
}
