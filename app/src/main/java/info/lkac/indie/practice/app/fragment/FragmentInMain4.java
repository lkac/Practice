package info.lkac.indie.practice.app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import info.lkac.indie.practice.app.R;

/**
 * Created by Lusic on 11/24/2015.
 */
public class FragmentInMain4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("" + System.currentTimeMillis() + "\n" + this.toString());

        return rootView;
    }
}
