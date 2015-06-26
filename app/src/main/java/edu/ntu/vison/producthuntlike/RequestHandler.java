package edu.ntu.vison.producthuntlike;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vison on 2015/6/26.
 */
public class RequestHandler {
    // access token by ProductHunt
    private final static String token = "cc294d0d5f4560468654144e152a95ede6ae1cc488a7f3794f040d58ba25449d";

    /**
     *
     * @param posts the target data when there is a response
     * @param adapter when data has changed, call its notifyDataSetChanged method
     * @return get the request function
     */
    public static AuthRequest getPosts(final ArrayList<ProductItem> posts, final ProductListAdapter adapter) {
        String url = "https://api.producthunt.com/v1/posts";
        // initialize request with listener, headers
        Response.Listener postsListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                Log.d("RESPONSE", res.toString());
                try {
                    // get array from response
                    JSONArray postsData = res.getJSONArray("posts");
                    for(int i=0;i<postsData.length();i++) {
                        JSONObject each = postsData.getJSONObject(i);
                        int id = each.getInt("id");
                        String name = each.getString("name");
                        String tagline = each.getString("tagline");
                        int votes_count = each.getInt("votes_count");
                        String created_at = each.getString("created_at");

                        // create an product item, and add it to lists
                        ProductItem item = new ProductItem(id,name,tagline,votes_count,created_at);
                        posts.add(item);
                    }
                    // tell the adapter to set data
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        AuthRequest authRequest = new AuthRequest(Request.Method.GET, url, postsListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.toString());
                    }
                });

        return authRequest;
    }


    private static class AuthRequest extends JsonObjectRequest {
        private Map headers;
        public AuthRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        // function to make custom headers
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map headers = new HashMap();
            headers.put("Content-Type", "application/json");
            headers.put("Authorization", "Bearer " + token);
            return headers;
        }
    };

    public void setToken(){

    }
}
