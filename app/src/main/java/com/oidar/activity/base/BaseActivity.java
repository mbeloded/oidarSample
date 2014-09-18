package com.oidar.activity.base;

import android.support.v4.app.FragmentActivity;

/**
 * Custom Activity which has animations and Google Analytics support.
 */
public abstract class BaseActivity extends FragmentActivity {

    /**
     * Called when the activity is paused.
     */
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * Called when the activity is started.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Called when the activity is stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }
}
