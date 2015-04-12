package raw.app.src.main.java.com.example.mitofskya.wobblesimple4;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.app.Activity;

import com.example.mitofskya.wobblesimple4.R;


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

        //  GraphView graph = (GraphView) findViewById(R.id.graph);




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

        SoundPool soundPool;
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        int soundID;
        float volume;
        int xLimit = 5;

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundID = soundPool.load(this, R.raw.robotnoise, 1);
        soundID = soundPool.load(this, R.raw.robotnoise, 1);


//check sensor type
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
            }
            ;

            xc.setText("X: " + x);
            yc.setText("Y: " + y);
            zc.setText("Z: " + z);
            if ((x > xLimit)||(x<-xLimit)||(z>xLimit)||(z<-xLimit)) {
                soundPool.play(soundID, volume, volume, 1, 0, 1f);
            }
        }
    }

    public void onClick2 (View view) {
        Button bb1 = (Button) findViewById(R.id.stopButton);

        if(view.getId() == R.id.stopButton)
        {
            bb1.setText("Button pressed");
            finish();
            System.exit(0);
        }

    }




}
