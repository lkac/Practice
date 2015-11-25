package info.lkac.indie.practice.app.CustomTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Lusic on 11/24/2015.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<TabItem> mTabList;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public SectionsPagerAdapter(ArrayList<TabItem> tabList, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabList = tabList;
    }

    private ArrayList<TabItem> getTabList() {
        return mTabList;
    }

    @Override
    public Fragment getItem(int position) {
        if (getTabList() != null && position >= 0 && position < getTabList().size()) {
            Bundle args = new Bundle();
            args.putInt(SectionsPagerAdapter.ARG_SECTION_NUMBER, position + 1);

            Fragment newFragment = null;
            try {
                newFragment = mTabList.get(position).getFragmentClass().newInstance();
            } catch (IllegalAccessException e) {
                Log.e(SectionsPagerAdapter.class.getSimpleName(), e.toString());
                return null;
            } catch (InstantiationException e) {
                Log.e(SectionsPagerAdapter.class.getSimpleName(), e.toString());
                return null;
            }
            newFragment.setArguments(args);
            return newFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        if (getTabList() == null) {
            return 0;
        }
        return getTabList().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (getTabList() != null && position < getTabList().size()) {
            return getTabList().get(position).getTabName();
        }
        return null;
    }
}