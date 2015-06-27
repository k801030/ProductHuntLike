package edu.ntu.vison.producthuntlike.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/27.
 */
public class ProductItemDetail extends ProductItem {
    @SerializedName("comments")
    public ArrayList<Comment> comments;

    @SerializedName("makers")
    public ArrayList<User> maker;

    public ProductItemDetail() {

    }
    public ProductItemDetail(int id, String name, String tagline, int votes_count, String created_at) {
        super(id, name, tagline, votes_count, created_at);
    }



}
