package com.oidar.fragment.base;

/**
 * Created by mbeloded on 9/17/14.
 */
public class DrawerTalkRadioFragment extends DrawerFragment {

    public DrawerTalkRadioFragment (){}

    @Override
    public String getFragmentTag() {
        return null;
    }

    public static DrawerTalkRadioFragment newInstance(){
        return new DrawerTalkRadioFragment();
    }
}
