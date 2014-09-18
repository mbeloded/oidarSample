package com.oidar.fragment.base;

import android.support.v4.app.Fragment;

import com.oidar.sql.SqlHandler;

public abstract class RadioFragment extends Fragment {
    /**
     * Called to save the needed data in the edit fragments.
     */
    public abstract void saveData(SqlHandler handler);
}
