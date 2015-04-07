package com.example.mitofskya.wobblesimple3;

/*
This program is initially written by Andy Mitofsky and Tim Carver

This program reads data from the accelerometer and allows you to zoom in on the data.
The example at
http://webhole.net/2011/08/20/android-sdk-accelerometer-example-tutorial/
was used as a reference when getting the accelerometer working.
The example aat
http://www.mysamplecode.com/2013/04/android-switch-button-example.html
was used as a reference for getting the switches working.
 */

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.hardware.SensorEvent;
import android.widget.Button;
import android.view.View;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    TextView xc;
    TextView yc;
    TextView zc;
    private Switch xxSwitch;
    private Switch yySwitch;
    private Switch zzSwitch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xc=(TextView)findViewById(R.id.xcoor);
        yc=(TextView)findViewById(R.id.ycoor);
        zc=(TextView)findViewById(R.id.zcoor);

        xxSwitch=(Switch)findViewById(R.id.xswitch);
        xxSwitch.setChecked(true);
        yySwitch=(Switch)findViewById(R.id.yswitch);
        yySwitch.setChecked(true);
        zzSwitch=(Switch)findViewById(R.id.zswitch);
        zzSwitch.setChecked(true);



        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        //Include the listener for the sensors
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);


    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
   public void onAccuracyChanged (Sensor sensor, int accuracy)
   {

   }

    public void onSensorChanged(SensorEvent event) {
//check sensor type
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)  {

            float x=event.values[0];
            float y=event.values[1];
            float z=event.values[2];

            if(xxSwitch.isChecked())
                {x=x*10;};
            if(yySwitch.isChecked())
                {y=y*10;};
            if(zzSwitch.isChecked())
                {z=z*10;};

            xc.setText("X: "+x);
            yc.setText("Y: "+y);
            zc.setText("Z: "+z);
        }
    }




}
