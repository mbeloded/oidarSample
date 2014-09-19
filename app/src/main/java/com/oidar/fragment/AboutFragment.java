package com.oidar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.oidar.R;
import com.oidar.fragment.base.DrawerFragment;

/**
 * About fragment which displays helpful information.
 */
public class AboutFragment extends DrawerFragment implements AdapterView.OnItemClickListener {

    public static final String TAG = AboutFragment.class.getName();

    public AboutFragment() {
    }

    /**
     * Static creation to avoid problems on rotation.
     */
    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    /**
     * Called when the view is created.
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.about_fragment, container, false);


//        AboutAdapter adapter = new AboutAdapter(
//                getActivity(),
//                generateListItems());
//
//        ListView list = (ListView) view.findViewById(R.id.listView);
//        list.setAdapter(adapter);
//        list.setOnItemClickListener(this);
        return view;
    }

    /**
     * Called when a list item is clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        switch (position) {
//            case 0:
//                ChangelogDialog.newInstance()
//                        .show(getActivity().getSupportFragmentManager(), ChangelogDialog.TAG);
//                break;
//            case 1:
//                Intent sendIntent = new Intent(
//                        Intent.ACTION_SENDTO,
//                        Uri.fromParts("mailto",
//                                "lolbolljohan@gmail.com",
//                                null)
//                );
//
//                sendIntent.putExtra(
//                        Intent.EXTRA_SUBJECT,
//                        getString(R.string.app_name) + " " +
//                                Util.getCurrentAppVersion(getActivity()) + " " + "feedback"
//                );
//                startActivity(
//                        Intent.createChooser(sendIntent, getString(R.string.send_email_title)));
//                break;
//            case 2:
//                LicensesDialogFragment.newInstance(R.raw.notices, false)
//                        .show(getActivity().getSupportFragmentManager(), null);
//                break;
//        }
    }

    @Override
    public String getFragmentTag() {
        return TAG;
    }

//    /**
//     * Generate a list of items to be displayed in the list.
//     */
//    private ArrayList<WendlerListItem> generateListItems() {
//        ArrayList<WendlerListItem> items = new ArrayList<WendlerListItem>();
//        String version = Util.getCurrentAppVersion(getActivity());
//        version = String.format(getString(R.string.version), version);
//
//        items.add(new WendlerListItem(
//                ListItemType.REGULAR,
//                getString(R.string.change_log),
//                version,
//                R.drawable.ic_changelog));
//
//        items.add(new WendlerListItem(
//                ListItemType.REGULAR,
//                getString(R.string.contact),
//                null,
//                R.drawable.ic_mail));
//
//        items.add(new WendlerListItem(
//                ListItemType.REGULAR,
//                getString(R.string.licences),
//                null,
//                R.drawable.ic_licenses));
//        return items;
//    }
}
