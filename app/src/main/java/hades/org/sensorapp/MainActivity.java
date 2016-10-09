package hades.org.sensorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button compass_bt;
    private Button spirit_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compass_bt = (Button) findViewById(R.id.start_compass);
        spirit_bt = (Button) findViewById(R.id.start_spirit);
    }

    public void starter(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.start_compass:
                Toast.makeText(this, "正在启动指南针程序", Toast.LENGTH_SHORT).show();
                intent.setClass(this, Compass.class);
                break;
            case R.id.start_spirit:
                Toast.makeText(this, "正在启动水平仪程序", Toast.LENGTH_SHORT).show();
                intent.setClass(this, Spirit.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
