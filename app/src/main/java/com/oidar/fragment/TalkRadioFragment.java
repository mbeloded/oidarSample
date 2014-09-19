package com.oidar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oidar.R;
import com.oidar.fragment.base.RadioFragment;
import com.oidar.sql.SqlHandler;

public class TalkRadioFragment extends RadioFragment {
    public TalkRadioFragment() {
    }

    /**
     * Static creation to avoid problems on rotation.
     */
    public static TalkRadioFragment newInstance() {
        return new TalkRadioFragment();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_talk_radio, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Called to save the new data.
     */
    @Override
    public void saveData(SqlHandler handler) {


    }
}
