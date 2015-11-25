package info.lkac.indie.practice.app.CustomTabs;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabWidget;
import info.lkac.indie.practice.app.R;

import java.util.ArrayList;

/**
 * Created by Lusic on 11/24/2015.
 */
public class CustomTabs {
    /* Stored outer parameter */
    private FragmentActivity mFragmentActivity;
    private ViewGroup parent;
    private ArrayList<TabItem> mTabList;
    /* Created by instance */
    private View rootView;
    private TabWidget mTabWidget;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Button[] mTabButtonsArray;

    /* Get instance with static method 'with' in one step*/
    private CustomTabs() {
    }

    /**
     * IllegalStateException threw with null parameter
     *
     * @param fragmentActivity Need Activity and SupportFragmentManager
     * @param parent           The container to show tabs and fragment
     * @return Instance to handle CustomTabs
     */
    public static CustomTabs with(FragmentActivity fragmentActivity, ViewGroup parent) {
        if (fragmentActivity == null) {
            throw new IllegalStateException("<fragmentActivity> is null.");
        } else if (parent == null) {
            throw new IllegalStateException("<parent> is null.");
        }

        CustomTabs instance = new CustomTabs();
        // Store outer parameters
        instance.mFragmentActivity = fragmentActivity;
        instance.parent = parent;
        // Initialize
        instance.init();
        instance.setUp();

        return instance;
    }

    private LayoutInflater getInflater() {
        return mFragmentActivity.getLayoutInflater();
    }

    private void init() {
        mTabList = new ArrayList<TabItem>();
        rootView = getInflater().inflate(R.layout.custom_tabs, parent, true);
        mTabWidget = (TabWidget) rootView.findViewById(R.id.tabWidgetInCustomTabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewPagerInCustomTabs);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getTabList(), getFragmentManager());
    }

    private void setUp() {
        // Header
        setUpTabWidget();
        // Content
        setUpViewPager();
    }

    private FragmentManager getFragmentManager() {
        return mFragmentActivity.getSupportFragmentManager();
    }

    private void setUpTabWidget() {
    }

    private void setUpViewPager() {
        getViewPager().setAdapter(getSectionsPagerAdapter());
        getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                getTabWidget().setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setUpTabButtons() {
        if (getTabList() == null || getTabList().size() < 1) {
            return;
        }

        //Clear old views
        getTabWidget().removeAllViewsInLayout();

        mTabButtonsArray = new Button[getTabList().size()];
        for (int i = 0; i < mTabList.size(); i++) {
            TabItem tabItem = mTabList.get(i);

            if (tabItem == null) {
                Log.w(CustomTabs.class.getSimpleName(), "<mTabList> is null with index:" + i);
                continue;
            }

            final int position = i;
            Button button = (Button) getInflater().inflate(R.layout.custom_tabs_button, getTabWidget(), false);
            button.setFocusable(true);
            button.setText(tabItem.getTabName());
            getTabWidget().addView(button);
            // Set OnClickListener after added it to TabWidget
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Just switch ViewPager, and let ViewPager call back to highlight TabWidget
                    getViewPager().setCurrentItem(position);
                }
            });

            mTabButtonsArray[i] = button;
        }
    }

    /**
     * Set data for the instance with tabList
     *
     * @param tabList
     * @return instance for chain calling
     */
    public CustomTabs setData(ArrayList<TabItem> tabList) {
        if (tabList == null || tabList.size() < 1) {
            throw new IllegalStateException("<tabList> is invalid.");
        }

        getTabList().clear();
        getTabList().addAll(tabList);
        getSectionsPagerAdapter().notifyDataSetChanged();

        setUpTabButtons();

        // Switch to the first tab by default
        int defaultIndex = getTabWidget().getTabCount() - 1;
        getTabWidget().setCurrentTab(defaultIndex);
        // Because TabWidget do not highlight the tab when the selected index is the same and Tabs are changed,
        // and now call setCurrentTab twice
        defaultIndex = 0;
        getTabWidget().setCurrentTab(defaultIndex);
        getViewPager().setCurrentItem(defaultIndex);

        return this;
    }

    /**
     * Get TabWidget after auto initialize
     *
     * @return
     */
    public TabWidget getTabWidget() {
        return mTabWidget;
    }

    /**
     * Get ViewPager after auto initialize
     *
     * @return
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * Get SectionsPagerAdapter after auto initialize
     *
     * @return
     */
    public SectionsPagerAdapter getSectionsPagerAdapter() {
        return mSectionsPagerAdapter;
    }

    /**
     * Get the TabList which the instance holds
     *
     * @return
     */
    public ArrayList<TabItem> getTabList() {
        return mTabList;
    }

    public Button[] getTabButtonsArray() {
        return mTabButtonsArray;
    }

    public void destroy() {
        // Collection object need been clear by hand when destroying
        if (getTabList() != null) {
            getTabList().clear();
        }
    }
}
