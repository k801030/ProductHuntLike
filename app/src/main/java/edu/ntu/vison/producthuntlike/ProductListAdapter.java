package edu.ntu.vison.producthuntlike;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductListAdapter extends BaseAdapter {
    ProductListAdapter adapter;
    private LayoutInflater inflater;
    private Activity activity;
    private ArrayList<ProductItem> productItems;

    // Constructor
    public ProductListAdapter(Activity activity) {
        this.adapter = this;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
        // execute it to create sample data
        //getSampleData();

        productItems = new ArrayList<ProductItem>();
        requestForProducts();

    }


    private void getSampleData() {

        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        productItems.add(new ProductItem(1, "Name", "Description", "@drawable/profile_pic",25,"time"));

    }

    private void requestForProducts() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.activity.getApplicationContext());
        // url for facebook profile;
        String url_fb ="https://graph.facebook.com/v2.3/100001278242344?access_token=CAACEdEose0cBAEZAOwJhzPIHI9kW1GVZCZBGgBzxQn2bIpu69l3a8SSDm3PJZCmOvJFcAPyYX853KxOFZABS7Y4PhUq3dJ7WkuH5UNbx9hf3xuF4sdfnugjdMcAYjoIcGMPEsCUKkhrDq1fjHS7LSZA5Mo5DUx3SfuHzZBWtfTARqwjGobc1OFNNwu0TMZBBwdbrsFmTaOBai1f9LHSZC97mVYhfJcRSeWrYZD";
        String url = "https://api.producthunt.com/v1/posts";

        // token by ProductHunt
        final String token = "cc294d0d5f4560468654144e152a95ede6ae1cc488a7f3794f040d58ba25449d";

        // initialize request with listener, headers
        AuthRequest authRequest = new AuthRequest(Request.Method.GET, url, new PostsListener(),
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR",error.toString());
                    }
                });
        authRequest.setHeaders("Content-Type", "application/json");
        authRequest.setHeaders("Authorization", "Bearer " + token);


        // Add the request to the RequestQueue.
        queue.add(authRequest);
    }

    private class PostsListener implements Response.Listener<JSONObject> {
        @Override
        public void onResponse(JSONObject res) {
            Log.d("RESPONSE", res.toString());
            try {
                // get array from response
                JSONArray posts = res.getJSONArray("posts");
                for(int i=0;i<posts.length();i++) {
                    JSONObject each = posts.getJSONObject(i);
                    int id = each.getInt("id");
                    String name = each.getString("name");
                    String tagline = each.getString("tagline");
                    int votes_count = each.getInt("votes_count");
                    String created_at = each.getString("created_at");

                    // create an product item, and add it to lists
                    ProductItem item = new ProductItem(id,name,tagline,votes_count,created_at);
                    productItems.add(item);
                }
                // tell the adapter to set data
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    };

    private class AuthRequest extends JsonObjectRequest {

        private Map headers;

        public AuthRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        // function to make custom headers
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return this.headers;
        }

        public void setHeaders(String key, String value) {
            Map headers = new HashMap();
            headers.put(key,value);
            this.headers = headers;
        }
    };

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

        voteButton.setText("▲\n" + productItem.getVoteCount());
        nameText.setText(productItem.getName());
        descriptionText.setText(productItem.getTagline());

        return view;
    }
}
