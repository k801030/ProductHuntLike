package edu.ntu.vison.producthuntlike.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/27.
 */
public class ProductItemDetail extends ProductItem {
    @SerializedName("comments") ArrayList<Comment> comments;

    @SerializedName("makers") ArrayList<User> maker;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<User> getMaker() {
        return maker;
    }
}
