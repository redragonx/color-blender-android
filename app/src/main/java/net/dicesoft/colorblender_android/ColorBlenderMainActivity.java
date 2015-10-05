package net.dicesoft.colorblender_android;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ColorBlenderMainActivity extends AppCompatActivity {

    private final String MY_COLOR_PICKER_APP = "msud.cs390H.ACTION_COLOR";
    private final int CHOOSE_COLOR = 1;
    private int color1;
    private Color color2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blender_main);
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
                startActivityForResult(pickColorIntent, CHOOSE_COLOR);


                break;
            case R.id.btn_pickcolor2:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CHOOSE_COLOR) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Log.d("color", String.valueOf(extras.getInt("red")));
                color1 = Color.rgb(extras.getInt("red"), extras.getInt("green"), extras.getInt("blue"));

                View colorView = findViewById(R.id.colorView);

                colorView.setBackgroundColor(color1);
            }
        }
    }

}
