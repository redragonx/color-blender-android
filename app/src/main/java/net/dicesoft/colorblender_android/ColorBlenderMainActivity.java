package net.dicesoft.colorblender_android;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

public class ColorBlenderMainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private final String MY_COLOR_PICKER_APP = "msud.cs390H.ACTION_COLOR";


    private View colorView;
    private View colorView2;

    private View previewColor1View;
    private View previewColor2View;

    SeekBar colorBlenderSeekbar;

    // custom intent request codes
    private final int FIRST_COLOR = 1;
    private final int SECOND_COLOR = 2;

    private int color1 = 50;
    private int color2 = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blender_main);

        colorView = findViewById(R.id.colorView);
        colorView2 = findViewById(R.id.colorView2);

        previewColor1View = findViewById(R.id.preview1);
        previewColor2View = findViewById(R.id.preview2);

        colorBlenderSeekbar = (SeekBar) findViewById(R.id.seekbar_colorblender);
        colorBlenderSeekbar.setOnSeekBarChangeListener(this);
        colorBlenderSeekbar.setMax(255);
        colorBlenderSeekbar.setProgress(127);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_blender_main, menu);
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

    public void colorSelect1(View v) {
        Intent pickColorIntent = new Intent(MY_COLOR_PICKER_APP);
        // pickColorIntent.setComponent(new ComponentName("net.dicesoft", MY_COLOR_PICKER_APP));
        switch (v.getId())  {
            case R.id.btn_pickcolor1:
                startActivityForResult(pickColorIntent, FIRST_COLOR);


                break;
            case R.id.btn_pickcolor2:
                // pickColorIntent.putExtra("secondColor", "1");
                startActivityForResult(pickColorIntent, SECOND_COLOR);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == FIRST_COLOR) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
               // Log.d("color", String.valueOf(extras.getInt("red")));
                color1 = Color.rgb(extras.getInt("red"), extras.getInt("green"), extras.getInt("blue"));

                colorView = findViewById(R.id.colorView);

                colorView.setBackgroundColor(color1);
                previewColor1View.setBackgroundColor(color1);
            }
        } else if (requestCode == SECOND_COLOR) {

            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();

                //Log.d("color", String.valueOf(extras.getInt("red")));
                color2 = Color.rgb(extras.getInt("red"), extras.getInt("green"), extras.getInt("blue"));

                colorView2 = findViewById(R.id.colorView2);

                colorView2.setBackgroundColor(color2);
                previewColor2View.setBackgroundColor(color2);
            }
        }


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.getId() == R.id.seekbar_colorblender) {
            colorView2.setAlpha((float) progress / 255);
        }
//        newColor
      //  colorView.setBackgroundColor(newColor);

    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
