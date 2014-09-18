package com.oidar.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oidar.R;
import com.oidar.activity.base.BaseActivity;
import com.oidar.adapter.DrawerAdapter;
import com.oidar.fragment.base.DrawerFragment;
import com.oidar.fragment.base.DrawerLiveNewsFragment;
import com.oidar.fragment.base.DrawerTalkRadioFragment;
import com.oidar.model.DrawerListItem;
import com.oidar.model.ListItemType;
import com.oidar.sql.SqlHandler;
import com.oidar.util.MyLog;
import com.oidar.view.CustomDrawerLayout;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mbeloded on 9/17/14.
 */
public class MainActivity extends BaseActivity {

    public static final String ACTION_UPDATE = "action_update";

    private static final String EXTRA_SAVED_SELECTION = "savedSelection";
    private static final String EXTRA_SAVED_EDIT_PAGE = "savedEditPage";
    private static final String EXTRA_IS_DRAWER_OPEN = "isDrawerOpen";

    private ArrayList<DrawerListItem> mListItems;
    private CustomDrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private boolean mDrawerIsLocked = false;
    private boolean mIsDrawerOpen = false;
    private DrawerAdapter mAdapter;
    private int mSavedSelection = 0;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mListItems = generateListItems();
        mDrawerLayout = (CustomDrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerToggle = getActionBarDrawerToggle();

        mAdapter = new DrawerAdapter(this, mListItems);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if (getResources().getBoolean(R.bool.drawer_locked_open)) {
            mDrawerLayout.setScrimColor(getResources().getColor(R.color.drawer_no_shadow));
            mDrawerIsLocked = true;
            mDrawerLayout.setOnHideListener(mOnHideListener);
        }

        if (!mDrawerIsLocked && getActionBar() != null) {
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }

        int savedPosition = 0;
        if (savedInstanceState != null) {
            mSavedSelection = savedInstanceState.getInt(EXTRA_SAVED_SELECTION, 0);
            savedPosition = savedInstanceState.getInt(EXTRA_SAVED_EDIT_PAGE, 0);
            mIsDrawerOpen = savedInstanceState.getBoolean(EXTRA_IS_DRAWER_OPEN, false);
        }
        selectItem(mSavedSelection, savedPosition);
    }

    /**
     * Called when the configuration has changed.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Called when an option item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    /**
     * Called to set the title of the ActionBar.
     */
    @Override
    public void setTitle(CharSequence title) {
        if (getActionBar() != null) {
            getActionBar().setTitle(title);
        }
    }

    /**
     * Called when the Activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mIsDrawerOpen && !mDrawerIsLocked) {
            MyLog.d("Drawer was previously open, open it again!");
            mDrawerLayout.openDrawer(GravityCompat.START);
        } else if (!mDrawerIsLocked) {
            MyLog.d("Drawer was previously closed, make sure it's closed!");
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Called when the Activity needs to save it's current instance.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save our currently selected page
        outState.putInt(EXTRA_SAVED_SELECTION, mSavedSelection);

//        // If we're currently editing something in the edit section save that selection
//        DrawerEditFragment fragment = (DrawerEditFragment)
//                getSupportFragmentManager().findFragmentByTag(DrawerEditFragment.TAG);
//        int page = fragment != null ? fragment.getCurrentPage() : 0;
//        outState.putInt(EXTRA_SAVED_EDIT_PAGE, page);

        // Save the state of our drawer
        outState.putBoolean(EXTRA_IS_DRAWER_OPEN, mIsDrawerOpen);
    }

    /**
     * Called when we receive a result code from the started activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLog.d("Received result with code " + resultCode);
        Intent intent = new Intent();
        intent.setAction(ACTION_UPDATE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Called when activity start-up is complete.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
        if (mDrawerIsLocked) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            mDrawerLayout.openDrawer(Gravity.START);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    /**
     * Listener for pressing back in our drawer.
     */
    private final CustomDrawerLayout.OnHideListener mOnHideListener = new CustomDrawerLayout.OnHideListener() {
        @Override
        public void onBackPressed() {
            MainActivity.this.finish();
        }
    };

    /**
     * Generate items for the drawer.
     */
    private ArrayList<DrawerListItem> generateListItems() {

        ArrayList<DrawerListItem> items = new ArrayList<DrawerListItem>();

        String[] titles = getResources().getStringArray(R.array.drawer);
        for (String title : titles) {
            items.add(new DrawerListItem(ListItemType.REGULAR, title, null, -1));
        }

        items.add(new DrawerListItem(
                ListItemType.SMALL,
                getString(R.string.action_item_settings),
                null,
                R.drawable.ic_action_settings));

        items.add(new DrawerListItem(
                ListItemType.SMALL,
                getString(R.string.action_item_about),
                null,
                R.drawable.ic_action_about_drawer));

        return items;
    }

    /**
     * Create a ActionbarDrawerToggle to use.
     */
    private ActionBarDrawerToggle getActionBarDrawerToggle() {
        return new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.title_open,
                R.string.title_close) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
                mIsDrawerOpen = false;
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                mIsDrawerOpen = true;
            }
        };
    }

    /**
     * Swaps fragments in the main content view if needed.
     */
    private void selectItem(int position, int selectedRadio) {
        if (!mAdapter.isPositionSelected(position)) {
            DrawerFragment fragment = getFragmentToDisplay(position, selectedRadio);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment, fragment.getFragmentTag())
                    .commit();

            setTitle(mListItems.get(position).getTitle());
        }
    }

    /**
     * Switch over the position and get the fragment to display.
     */
    private DrawerFragment getFragmentToDisplay(int position, int selectedRadio) {
        SqlHandler handler = new SqlHandler(this);

        DrawerFragment fragment = null;

        switch (position) {
            case 0:

                try {
                    handler.open();

                    fragment = DrawerTalkRadioFragment.newInstance(selectedRadio, handler);

                } catch (SQLException e) {
                    MyLog.e("Error purging extra exercises", e);
                } finally {

                    handler.close();
                }

                return fragment;

            case 1:

                try {
                    handler.open();

                    fragment = DrawerLiveNewsFragment.newInstance(selectedRadio, handler);

                } catch (SQLException e) {
                    MyLog.e("Error purging extra exercises", e);
                } finally {

                    handler.close();
                }

                return fragment;

            default:
                try {
                    handler.open();

                    fragment = DrawerTalkRadioFragment.newInstance(selectedRadio, handler);

                } catch (SQLException e) {
                    MyLog.e("Error purging extra exercises", e);
                } finally {

                    handler.close();
                }

                return fragment;
//            case 2:
//                return DrawerBookmarksFragment.newInstance(editPos);
//            default:
//                return DrawerBackupManagerFragment.newInstance();
        }
    }

    /**
     * ClickListener for the Drawer.
     */
    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, final int position, long id) {
            if (!mDrawerIsLocked) {
                mDrawerLayout.closeDrawer(mDrawerList);
            }

            // Don't update the selected item until the FragmentTransaction is completed
            switch (mListItems.get(position).getType()) {
                case REGULAR:
                    mDrawerLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            selectItem(position, 0);
                        }
                    }, 200);

                    mSavedSelection = position;
                    break;

                case SMALL:
                    if (mListItems.get(position).getTitle()
                            .equals(getString(R.string.action_item_settings))) {
                        launchActivity(SettingsActivity.class);
                    } else if (mListItems.get(position).getTitle()
                            .equals(getString(R.string.action_item_about))) {
                        launchActivity(AboutActivity.class);
                    }
                    break;
            }
        }

        /**
         * Launch provided class with a delay to avoid lag when the drawer closes.
         */
        private void launchActivity(final Class<?> target) {
            mDrawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, target));
                }
            }, 200);
        }
    }
}
