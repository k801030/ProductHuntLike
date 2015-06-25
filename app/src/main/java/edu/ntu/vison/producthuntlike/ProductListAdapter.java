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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private ArrayList<ProductItem> productItems;

    private void getSampleData() {
        productItems = new ArrayList<ProductItem>();
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        productItems.add(new ProductItem(1,"Name","Description","@drawable/profile_pic",25,"time"));
        requestForProducts();
    }

    private void requestForProducts() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.activity.getApplicationContext());
        // url for facebook profile;
        String url_fb ="https://graph.facebook.com/v2.3/100001278242344?access_token=CAACEdEose0cBAEZAOwJhzPIHI9kW1GVZCZBGgBzxQn2bIpu69l3a8SSDm3PJZCmOvJFcAPyYX853KxOFZABS7Y4PhUq3dJ7WkuH5UNbx9hf3xuF4sdfnugjdMcAYjoIcGMPEsCUKkhrDq1fjHS7LSZA5Mo5DUx3SfuHzZBWtfTARqwjGobc1OFNNwu0TMZBBwdbrsFmTaOBai1f9LHSZC97mVYhfJcRSeWrYZD";
        String url = "https://api.producthunt.com/v1/posts";

        // token by producthunt
        final String token = "cc294d0d5f4560468654144e152a95ede6ae1cc488a7f3794f040d58ba25449d";

        // Request a string response from the provided URL.
        Response.Listener listener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Log.i("INFO", response);
            }
        };


        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, url,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Display the first 500 characters of the response string.
                    Log.i("RESPONSE", response.toString());
                }
            },
            new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.toString());
            }
        }) {
            // function to make custom headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headers = new HashMap();
                headers.put("Content-Type","application/json");
                headers.put("Authorization","Bearer " + token);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjRequest);
    }



    public ProductListAdapter(Activity activity) {
        this.activity = activity;
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
