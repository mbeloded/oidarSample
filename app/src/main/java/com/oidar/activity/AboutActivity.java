package com.oidar.activity;

import android.os.Bundle;

import com.oidar.activity.base.BaseActivity;
import com.oidar.fragment.base.AboutFragment;


/**
 * Activity for displaying the About-view
 */
public class AboutActivity extends BaseActivity {

    /**
     * Called when the activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }
}
