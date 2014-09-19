package com.oidar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oidar.R;
import com.oidar.animation.ZoomOutPageTransformer;
import com.oidar.fragment.base.DrawerFragment;
import com.oidar.fragment.base.RadioFragment;
import com.oidar.model.Radio;
import com.oidar.sql.SqlHandler;
import com.oidar.util.MyLog;
import com.oidar.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mbeloded on 9/17/14.
 */
public class DrawerLiveNewsFragment extends DrawerFragment {

    private static final String EXTRA_RADIO_LIST = "radioList";
    private static final String EXTRA_CURRENT_PAGE = "currentPage";
    private ArrayList<Radio> mListOfRadios;

    public static final String TAG = DrawerLiveNewsFragment.class.getName();

    private ViewPager mViewPager;
    private SectionsPagerAdapter mAdapter;

    /**
     * Return the tag of the fragment.
     */
    @Override
    public String getFragmentTag() {
        return TAG;
    }

    public DrawerLiveNewsFragment(){}


    public static DrawerLiveNewsFragment newInstance(int type, SqlHandler sqlHandler){
        DrawerLiveNewsFragment fragment = new DrawerLiveNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_RADIO_LIST, sqlHandler.getRadiosForList(type));
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Called when the view is created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_view_pager_layout, container, false);

        mAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setAdapter(mAdapter);

//        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
//        slidingTabLayout.setViewPager(mViewPager);

        int pos = 0;
        if (getArguments() != null) {
            pos = getArguments().getInt(EXTRA_CURRENT_PAGE, 0);
        }
        mViewPager.setCurrentItem(pos);

        return view;
    }

    /**
     * Called when the view is stopped.
     */
    @Override
    public void onStop() {
        super.onStop();
        SqlHandler handler = new SqlHandler(getActivity());
        try {
            handler.open();
            for (RadioFragment fragment : mAdapter.getFragments()) {
                fragment.saveData(handler);
            }
        } catch (SQLException e) {
            MyLog.e("Failed to save data ", e);
        } catch (NullPointerException ignored) {
            MyLog.e("Failed to store data", ignored);
        } finally {
            handler.close();
        }

        Util.hideKeyboard(getActivity());
    }

    /**
     * Return the current page of the fragment.
     */
    public int getCurrentPage() {
        return mViewPager.getCurrentItem();
    }

    /**
     * Adapter for the ViewPager.
     */
    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private final RadioFragment[] fragments;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new LiveNewsFragment[]{LiveNewsFragment.newInstance(), LiveNewsFragment.newInstance(), LiveNewsFragment.newInstance()};
        }

        /**
         * Return the child fragments.
         */
        public RadioFragment[] getFragments() {
            return fragments;
        }

        /**
         * Return a fragment at a given position.
         */
        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        /**
         * Return the number of fragments in the view.
         */
        @Override
        public int getCount() {
            return fragments.length;
        }

        /**
         * Return the title of a given position.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.edit_order_title).toUpperCase();
                case 1:
                    return getString(R.string.edit_percentages_title).toUpperCase();
                default:
                    return getString(R.string.edit_other_title).toUpperCase();
            }
        }
    }
}
