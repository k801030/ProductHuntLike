package edu.ntu.vison.producthuntlike.model;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/27.
 */
public class ProductItemDetail extends ProductItem {
    ArrayList<Comment> comments;
    ArrayList<User> users;

    public ProductItemDetail(int id, String name, CharSequence tagline, int votes_count, String created_at) {
        super(id, name, tagline, votes_count, created_at);
    }



}
