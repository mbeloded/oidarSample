package com.oidar.fragment.base;

/**
 * Created by mbeloded on 9/17/14.
 */
public class DrawerLiveNewsFragment extends DrawerFragment {

    public DrawerLiveNewsFragment(){}

    @Override
    public String getFragmentTag() {
        return null;
    }

    public static DrawerLiveNewsFragment newInstance(){
        return new DrawerLiveNewsFragment();
    }

}
