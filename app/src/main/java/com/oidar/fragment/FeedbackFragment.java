package com.oidar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oidar.R;
import com.oidar.fragment.base.DrawerFragment;

/**
 * Created by mbeloded on 9/19/14.
 */
public class FeedbackFragment extends DrawerFragment {

    private static final String TAG = FeedbackFragment.class.getCanonicalName();

    @Override
    public String getFragmentTag() {
        return TAG;
    }

    /**
     * Static creation to avoid problems on rotation.
     */
    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    /**
     * Called when the view is created.
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feedback_fragment, container, false);

        return view;
    }
}
