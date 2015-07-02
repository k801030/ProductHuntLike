package edu.ntu.vison.producthuntlike.framgent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.ntu.vison.producthuntlike.R;
import edu.ntu.vison.producthuntlike.adapter.CardStackAdapter;
import edu.ntu.vison.producthuntlike.view.CardStackAdapterView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardStackFragment extends Fragment {

    /**
     * The Adapter which will be used to populate the AdapterView with
     * Views.
     */
    CardStackAdapter mAdapter;
    /**
     * The fragment's AdapterView
     */

    CardStackAdapterView mCardStackView;

    public CardStackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_stack, container, false);

        // Set adapter
        mAdapter = new CardStackAdapter(this.getActivity());
        mCardStackView = (CardStackAdapterView) view.findViewById(R.id.card_stack);
        mCardStackView.setAdapter(mAdapter);

        return view;
    }


}
