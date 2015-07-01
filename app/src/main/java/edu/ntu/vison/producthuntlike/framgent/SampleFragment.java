package edu.ntu.vison.producthuntlike.framgent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.ntu.vison.producthuntlike.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SampleFragment newInstance(int page, String title) {
        // Required empty public constructor
        SampleFragment SampleFragment = new SampleFragment();
        return SampleFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.adapter_product_list, container, false);
        TextView textView = (TextView)view.findViewById(R.id.main_product_name);
        return view;
    }


}
