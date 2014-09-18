package com.oidar.activity;

import android.os.Bundle;

import com.oidar.activity.base.BaseActivity;
import com.oidar.fragment.base.SettingsFragment;

/**
 * The activity for displaying our settings.
 */
public class SettingsActivity extends BaseActivity {

    /**
     * Called when the Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, SettingsFragment.newInstance(), SettingsFragment.TAG)
                .commit();
    }
}