package hades.org.sensorapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by Hades on 16/10/8.
 */
public class Compass extends Activity implements SensorEventListener{
    //定义显示指南针的图片
    private ImageView znz_iv;
    //Sensor管理器
    private SensorManager sensorManager;
    //定义转过的角度
    private float currentDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        znz_iv = (ImageView) findViewById(R.id.znz_iv);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //为系统注册方向传感器监听器
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //获取触发event的传感器类型
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                //获取绕Z轴转过的角度
                float degree = sensorEvent.values[0];
                //创建旋转动画
                RotateAnimation ra = new RotateAnimation(currentDegree, -degree
                        , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                //设置动画持续时间
                ra.setDuration(200);
                //运行动画
                znz_iv.startAnimation(ra);
                //更新当前角度
                currentDegree = -degree;
                //Toast.makeText(this, "角度" + currentDegree, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onStop();
    }
}
