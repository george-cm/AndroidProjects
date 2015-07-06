package com.georgemurga.bladderdiary;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.georgemurga.bladderdiary.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        // Intent intent = new Intent(this, DisplayMessageActivity.class);
        /*EditText editText = (EditText) findViewById(R.id.edit_message);

        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);*/
        TextView textView = (TextView) findViewById(R.id.textView);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path, "bladder_diary.csv");
        try {
            // Make sure the downloads directory exists
            path.mkdirs();
            OutputStream fos = new FileOutputStream(file, true);
            String currentDateAndTime = sdf.format(Calendar.getInstance().getTime())
                    + ";1" + System.getProperty("line.separator");
            fos.write(currentDateAndTime.getBytes());
            fos.close();
            textView.setTextSize(20);
            textView.setText(currentDateAndTime);
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing" + file, e);
        }
//        startActivity(intent);
    }
}
