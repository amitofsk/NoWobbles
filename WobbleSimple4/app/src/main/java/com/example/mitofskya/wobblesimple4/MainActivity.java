package com.example.mitofskya.wobblesimple4;

/*
This program displays values from the accelerometer.
It plays a sound if the tablet is tipped over too far.
It was written by Andy Mitofsky and Tim Carver for the
International Space Apps Challenge 2015.

We used the following tutorials as references when writing this code:
Accelerometer tutorial:
http://webhole.net/2011/08/20/android-sdk-accelerometer-example-tutorial/
Sound tutorial:
http://www.vogella.com/tutorials/AndroidMedia/article.html
Hardware sparkfun tutorial:
https://learn.sparkfun.com/tutorials/dotbar-display-driver-hookup-guide?_ga=1.62450789.832249666.1427846498

 */


import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.hardware.SensorEvent;
import android.widget.Button;
import android.view.View;
import android.widget.Switch;
import android.media.AudioManager;
import android.media.SoundPool;
//import android.media.SoundPool.OnLoadCompleteListener;
import android.app.Activity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    TextView xc;
    TextView yc;
    TextView zc;
    private Switch xxSwitch;
    private Switch yySwitch;
    private Switch zzSwitch;
    float xold1=0;
    float xold2=0;
    float xold3=0;
    float xold4=0;
    // GraphView graph = (GraphView) findViewById(R.id.graph);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xc=(TextView)findViewById(R.id.xcoor);
        yc=(TextView)findViewById(R.id.ycoor);
        zc=(TextView)findViewById(R.id.zcoor);

        xxSwitch=(Switch)findViewById(R.id.xswitch);
        xxSwitch.setChecked(false);
        yySwitch=(Switch)findViewById(R.id.yswitch);
        yySwitch.setChecked(false);
        zzSwitch=(Switch)findViewById(R.id.zswitch);
        zzSwitch.setChecked(false);

        //Set up the listener for the accelerometer
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void onAccuracyChanged (Sensor sensor, int accuracy)
    {

    }

    public void onSensorChanged(SensorEvent event) {

        int soundID;
        float volume;
        //Change xLimit if you want to change the cutoff value for what tilt causes sounds
        int xLimit = 5;

        TextView tv1=(TextView) findViewById(R.id.textView1);

        //Set up the sound effects
        SoundPool soundPool;
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundID = soundPool.load(this, R.raw.robotnoise, 1);


        //When accelerometer changes, do something.
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            if (xxSwitch.isChecked()) {
                x = x * 10;
            }
            ;
            if (yySwitch.isChecked()) {
                y = y * 10;
            }
            ;
            if (zzSwitch.isChecked()) {
                z = z * 10;
            };

            xc.setText("X: " + x);
            yc.setText("Y: " + y);
            zc.setText("Z: " + z);

            if (((x > xLimit)||(x<-xLimit)||(z>xLimit)||(z<-xLimit))) {
           // if (((x > xLimit)||(x<-xLimit)||(z>xLimit)||(z<-xLimit))&&(now.second-lastPlayed.second >5)) {
                soundPool.play(soundID, volume, volume, 1, 0, 1f);

            }


            xold1=x;
            xold2=xold1;
            xold3=xold2;
            xold4=xold3;
            //tv1.setText("Previous X values: \n"+xold1+"\n"+xold2+"\n"+xold3+"\n"+xold4);

       // series.appendData(new DataPoint (1,1));



        }



    }

    public void onClick2 (View view) {
        Button bb1 = (Button) findViewById(R.id.stopButton);
        Button bb2 = (Button) findViewById(R.id.showButton);
        Button bb3 = (Button) findViewById(R.id.saveButton);
        TextView tv1 = (TextView) findViewById(R.id.textView1);
        Boolean dataOn=false;
        FileOutputStream fout;

        if (view.getId() == R.id.stopButton) {
            bb1.setText("Button pressed");
            finish();
            System.exit(0);
        }

        if (view.getId() == R.id.showButton) {

            if (bb2.getText()=="Show Data") {
                bb2.setText("Hide Data");
                tv1.setText("Previous X values: \n" + xold1 + "\n" + xold2 + "\n" + xold3 + "\n" + xold4);
                //dataOn = true;
            }
            else  {
                bb2.setText("Show Data");
                 tv1.setText("");
                //dataOn = false;
            }
        }
        String tempstring="mystring";

        //String filename="mylogfile.txt";
        //File myfile= new File("/storage/sdcard0/files/mylogfile.txt");
        if (view.getId() == R.id.saveButton) {
            bb3.setText("Button Pushed");
            try {
               fout= new FileOutputStream("mylogfile.txt");

               fout.write(tempstring.getBytes());

                fout.close();
            }
           catch(IOException e)
            {
            }
        }




    }}





