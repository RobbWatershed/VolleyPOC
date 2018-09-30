package com.example.platypus.volleypoc.utils;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.platypus.volleypoc.MainActivity;

import timber.log.Timber;

// Don't do that at home, this is ugly. Remember, it's just a one-shot POC
public class ScreenTree extends Timber.Tree {

    private LinearLayout layout;
    private MainActivity activity;
    private ScrollView scroll;

    public ScreenTree(MainActivity activity, LinearLayout layout, ScrollView scroll)
    {
        this.activity = activity;
        this.layout = layout;
        this.scroll = scroll;
    }

    @Override
    protected void log(int priority, String tag, @NonNull String message, Throwable t) {

        activity.runOnUiThread(() -> {
            TextView text = new TextView(activity.getApplicationContext());
            text.setText(message);

            layout.addView(text);

            scroll.pageScroll(View.FOCUS_DOWN);
        });
    }
}
