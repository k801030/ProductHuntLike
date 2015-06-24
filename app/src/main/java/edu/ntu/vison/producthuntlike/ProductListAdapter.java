package edu.ntu.vison.producthuntlike;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ProductItem> productItems;

    private void getSampleData() {
        productItems = new ArrayList<ProductItem>();
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
    }

    public ProductListAdapter(Activity activity) {
        this.inflater = LayoutInflater.from(activity);
        // execute it to create sample data
        getSampleData();
    }

    @Override
    public int getCount() {
        return productItems.size();
    }

    @Override
    public Object getItem(int i) {
        return productItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null)
            view = inflater.inflate(R.layout.fragment_products, null);


        ProductItem productItem = productItems.get(i);
        Button voteButton = (Button) view.findViewById(R.id.vote);
        TextView nameText = (TextView) view.findViewById(R.id.name);
        TextView descriptionText = (TextView) view.findViewById(R.id.description);

        voteButton.setText("▲\n" + productItem.getVote());
        nameText.setText(productItem.getName());
        descriptionText.setText(productItem.getDescription());

        return view;
    }
}
