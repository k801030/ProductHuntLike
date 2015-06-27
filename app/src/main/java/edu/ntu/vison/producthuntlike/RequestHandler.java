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
                Gson gson = new Gson();
                try {
                    String post_json = res.getJSONObject("post").toString();

                    ProductItemDetail post = gson.fromJson(post_json, ProductItemDetail.class);
                    // create an product item, and add it to lists
                    callback.onSuccess(post);

                    /**
                    // get array from response
                    JSONObject post_json = res.getJSONObject("post");
                    ProductItemDetail item = new ProductItemDetail();

                    item.id = post_json.getInt("id");
                    item.name = post_json.getString("name");
                    item.tagline = post_json.getString("tagline");
                    item.votes_count = post_json.getInt("votes_count");
                    item.created_at = post_json.getString("created_at");
                    JSONArray comments_json_array = post_json.getJSONArray("comments");
                    // Comments
                    ArrayList<Comment> comments = new ArrayList<Comment>();
                    for(int i=0;i<comments_json_array.length();i++) {
                        Comment c = new Comment();
                        JSONObject comment_json = comments_json_array.getJSONObject(i);
                        c.id = comment_json.getInt("id");
                        c.body = comment_json.getString("body");
                        c.post_id = comment_json.getInt("post_id");
                        c.user_id = comment_json.getInt("user_id");
                        c.votes = comment_json.getInt("votes");
                        c.child_comments_count = comment_json.getInt("child_comments count");

                        // Child Comments
                        JSONArray child_comments_json_array = comment_json.getJSONArray("child_comments");
                        for(int j=0;j<child_comments_json_array.length();j++) {
                            Comment cc = new Comment();
                            JSONObject child_comment_json = child_comments_json_array.getJSONObject(i);
                            cc.id = child_comment_json.getInt("id");
                            cc.body = child_comment_json.getString("body");
                            cc.post_id = child_comment_json.getInt("post_id");
                            cc.user_id = child_comment_json.getInt("user_id");
                            cc.votes = child_comment_json.getInt("votes");
                            cc.child_comments_count = comment_json.getInt("child_comments count");
                        }
                        // Users
                        JSONArray users_json_array = comment_json.getJSONArray("users");
                        for(int j=0;j<users_json_array.length();j++) {
                            User u = new User();
                            JSONObject
                        }

                    }
                    item.comments = detail
                    **/


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
