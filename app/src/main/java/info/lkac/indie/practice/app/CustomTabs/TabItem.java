package info.lkac.indie.practice.app.CustomTabs;

import android.support.v4.app.Fragment;

/**
 * Created by Lusic on 11/25/2015.
 */
public class TabItem {
    private String tabName;
    private Class<? extends Fragment> fragmentClass;

    public TabItem(String tabName, Class<? extends Fragment> fragmentClass) {
        this.tabName = tabName;
        this.fragmentClass = fragmentClass;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

}
