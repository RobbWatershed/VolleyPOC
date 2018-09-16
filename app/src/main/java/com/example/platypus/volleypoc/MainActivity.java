package com.example.platypus.volleypoc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.platypus.volleypoc.utils.InputStreamVolleyRequest;
import com.example.platypus.volleypoc.utils.RequestQueueManager;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Timber.plant(new Timber.DebugTree());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i=1; i<60; i++) urls.add("https://ba.hitomi.la/galleries/1287389/"+compensateStringLength(i,2)+"_1_"+i+".jpg");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int threads = Integer.parseInt(((EditText)findViewById(R.id.editTextThreads)).getText().toString());
                boolean useHentoidUserAgent = ((CheckBox)findViewById(R.id.checkBoxUserAgent)).isChecked();
                boolean useOkHttp = ((CheckBox)findViewById(R.id.checkBoxOkHttp)).isChecked();
                runVolley(threads, useHentoidUserAgent, useOkHttp);
            }
        });
    }

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

    private void runVolley(int threads, boolean useHentoidUserAgent, boolean useOkHttp)
    {
        if (useHentoidUserAgent) InputStreamVolleyRequest.USER_AGENT = "Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76K)"
                + " AppleWebKit/535.19 (KHTML, like Gecko)"
                + " Chrome/18.0.1025.166 Mobile Safari/535.19"
                + " Hentoid/v" + BuildConfig.VERSION_NAME;

        RequestQueueManager manager = RequestQueueManager.getInstance(this.getApplicationContext(), threads, useOkHttp);

        for (String s : urls) manager.addToQueue(s);
    }

    public static String compensateStringLength(int value, int length) {
        String result = String.valueOf(value);

        if (result.length() > length) {
            result = result.substring(0, length);
        } else if (result.length() < length) {
            result = String.format("%1$" + length + "s", result).replace(' ', '0');
        }

        return result;
    }

}
