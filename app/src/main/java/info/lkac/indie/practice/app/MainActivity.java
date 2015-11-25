package info.lkac.indie.practice.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import info.lkac.indie.practice.app.CustomTabs.CustomTabs;
import info.lkac.indie.practice.app.CustomTabs.TabItem;
import info.lkac.indie.practice.app.CustomTabs.fragment.FragmentInMain;
import info.lkac.indie.practice.app.CustomTabs.fragment.FragmentInMain2;
import info.lkac.indie.practice.app.CustomTabs.fragment.FragmentInMain3;
import info.lkac.indie.practice.app.CustomTabs.fragment.FragmentInMain4;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trySome();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void trySome() {
        customTabsDemo();
    }

    private void customTabsDemo(){
        final ArrayList<TabItem> tabList = new ArrayList<TabItem>();
        tabList.add(new TabItem("Tab one", FragmentInMain.class));
        tabList.add(new TabItem("Tab two", FragmentInMain2.class));
        tabList.add(new TabItem("Tab three", FragmentInMain3.class));

        final CustomTabs customTabs = CustomTabs.with(this, (FrameLayout) findViewById(R.id.showCustomTabs));
        customTabs.setData(tabList);

        Button action = (Button) findViewById(R.id.action);
        action.setText("Action");
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabList.size() > 3) {
                    for (int i = 3; i < tabList.size(); i++) {
                        tabList.remove(i);
                    }
                    customTabs.setData(tabList);
                    return;
                }

                tabList.add(new TabItem("Tab forth", FragmentInMain4.class));
                customTabs.setData(tabList);
            }
        });
    }
}
