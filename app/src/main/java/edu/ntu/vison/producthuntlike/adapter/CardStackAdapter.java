package edu.ntu.vison.producthuntlike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.ntu.vison.producthuntlike.R;
import edu.ntu.vison.producthuntlike.model.ProductItem;

/**
 * Created by Vison on 2015/7/1.
 */
public class CardStackAdapter extends BaseAdapter {
    private ArrayList<ProductItem> cards;
    private Context mContext;

    // constructor
    public CardStackAdapter(Context context,ArrayList<ProductItem> cards) {
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int i) {
        return cards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_card_stack, null);
        }
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(cards.get(i).getName());
        return view;
    }
}
