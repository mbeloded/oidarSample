package com.oidar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oidar.R;
import com.oidar.fragment.base.RadioFragment;
import com.oidar.sql.SqlHandler;

/**
 * Created by mbeloded on 9/18/14.
 */
public class LiveNewsFragment  extends RadioFragment {
    public LiveNewsFragment() {
    }

    /**
     * Static creation to avoid problems on rotation.
     */
    public static LiveNewsFragment newInstance() {
        return new LiveNewsFragment();
    }

    /**
     * Called when the view is created.
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        initViews(view);
        initValues();

        return view;
    }

    private void initValues() {
    }

    private void initViews(View view)
    {

    }

    /**
     * Called to save the new data.
     */
    @Override
    public void saveData(SqlHandler handler) {


    }
}
