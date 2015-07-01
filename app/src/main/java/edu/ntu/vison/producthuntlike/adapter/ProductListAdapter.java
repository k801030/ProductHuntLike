package edu.ntu.vison.producthuntlike.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;

import edu.ntu.vison.producthuntlike.activity.DetailActivity;
import edu.ntu.vison.producthuntlike.R;
import edu.ntu.vison.producthuntlike.RequestHandler;
import edu.ntu.vison.producthuntlike.model.ProductItem;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ProductItem> posts;
    public final static String PRODUCT_ID = "edu.ntu.vison.producthuntlike.product_id";

    // Constructor
    public ProductListAdapter(Context context) {
        mContext = context;

        posts = new ArrayList<ProductItem>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mContext);
        // build a request
        JsonObjectRequest request = RequestHandler.getPosts(new RequestHandler.getPostsCallback() {
            @Override
            public void onSuccess(ArrayList<ProductItem> results) {
                posts = results;
                notifyDataSetChanged();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(request);

    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_product_list, null);
        }

        final ProductItem productItem = posts.get(i);
        Button voteButton = (Button) view.findViewById(R.id.main_product_votes);
        TextView nameText = (TextView) view.findViewById(R.id.main_product_name);
        TextView descriptionText = (TextView) view.findViewById(R.id.main_product_tagline);
        ImageView makerImage = (ImageView) view.findViewById(R.id.maker_image);


        voteButton.setText("▲\n" + productItem.getVotesCount());
        nameText.setText(productItem.getName());
        descriptionText.setText(productItem.getTagline().toString());
        String url = productItem.getUser().getImageUrl().getPx50();
        UrlImageViewHelper.setUrlDrawable(makerImage, url);


        // To start an another activity
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(PRODUCT_ID, productItem.getId());
                mContext.startActivity(intent);

            }
        });

        return view;
    }
}
