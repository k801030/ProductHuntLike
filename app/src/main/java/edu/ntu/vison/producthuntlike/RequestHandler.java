package edu.ntu.vison.producthuntlike;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.ntu.vison.producthuntlike.model.Comment;
import edu.ntu.vison.producthuntlike.model.ProductItem;
import edu.ntu.vison.producthuntlike.model.ProductItemDetail;
import edu.ntu.vison.producthuntlike.model.User;

/**
 * Created by Vison on 2015/6/26.
 */
public class RequestHandler {
    // access token by ProductHunt
    private final static String token = "cc294d0d5f4560468654144e152a95ede6ae1cc488a7f3794f040d58ba25449d";


    public interface getPostDetailCallback{
        void onSuccess(ProductItemDetail result);
    }

    public interface getPostsCallback{
        void onSuccess(ArrayList<ProductItem> results);
    }

    public static AuthRequest getPostDetail(final getPostDetailCallback callback, int product_id) {
        String url = "https://api.producthunt.com/v1/posts/" + product_id;

        Response.Listener detailListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                Gson gson = new Gson();
                try {
                    String post_json = res.getJSONObject("post").toString();

                    ProductItemDetail post = gson.fromJson(post_json, ProductItemDetail.class);
                    // create an product item, and add it to lists
                    callback.onSuccess(post);


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
        String url = "https://api.producthunt.com/v1/posts?days_ago=1";
        // initialize request with listener, headers
        Response.Listener postsListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                Gson gson = new Gson();
                try {
                    String posts_json = res.getJSONArray("posts").toString();
                    // Give Gson info for the generic type used
                    Type postsClassType = new TypeToken<ArrayList<ProductItem>>(){}.getType();
                    ArrayList<ProductItem> posts = gson.fromJson(posts_json, postsClassType);

                    // create an product item, and add it to lists
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
