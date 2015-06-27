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
import java.util.Objects;

import edu.ntu.vison.producthuntlike.model.ProductItem;

/**
 * Created by Vison on 2015/6/26.
 */
public class RequestHandler {
    // access token by ProductHunt
    private final static String token = "cc294d0d5f4560468654144e152a95ede6ae1cc488a7f3794f040d58ba25449d";


    public interface getPostDetailCallback{
        void onSuccess(ProductItem result);
    }

    public interface getPostsCallback{
        void onSuccess(ArrayList<ProductItem> results);
    }

    public static AuthRequest getPostDetail(final getPostDetailCallback callback, int product_id) {
        String url = "https://api.producthunt.com/v1/posts/" + product_id;

        Response.Listener detailListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                try {
                    // get array from response
                    JSONObject detail = res.getJSONObject("post");

                        int id = detail.getInt("id");
                        String name = detail.getString("name");
                        String tagline = detail.getString("tagline");
                        int votes_count = detail.getInt("votes_count");
                        String created_at = detail.getString("created_at");

                        // create an product item, and add it to lists
                    ProductItem item = new ProductItem(id,name,tagline,votes_count,created_at);
                    callback.onSuccess(item);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        AuthRequest authRequest = new AuthRequest(Request.Method.GET, url, detailListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.toString());
                    }
                });
        return authRequest;
    }

    /**
     *
     * @return get the request function
     */
    public static AuthRequest getPosts(final getPostsCallback callback) {
        String url = "https://api.producthunt.com/v1/posts";
        // initialize request with listener, headers
        Response.Listener postsListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                Log.d("RESPONSE", res.toString());
                try {
                    // get array from response
                    JSONArray postsData = res.getJSONArray("posts");
                    ArrayList<ProductItem> posts = new ArrayList<ProductItem>();
                    for (int i = 0; i < postsData.length(); i++) {
                        JSONObject each = postsData.getJSONObject(i);
                        int id = each.getInt("id");
                        String name = each.getString("name");
                        String tagline = each.getString("tagline");
                        int votes_count = each.getInt("votes_count");
                        String created_at = each.getString("created_at");

                        // create an product item, and add it to lists
                        ProductItem item = new ProductItem(id, name, tagline, votes_count, created_at);
                        posts.add(item);
                    }
                    callback.onSuccess(posts);

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
