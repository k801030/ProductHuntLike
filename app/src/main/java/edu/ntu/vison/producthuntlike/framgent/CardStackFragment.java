package edu.ntu.vison.producthuntlike.framgent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ntu.vison.producthuntlike.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardStackFragment extends Fragment {


    public CardStackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_stack, container, false);
    }


}
