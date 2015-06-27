package edu.ntu.vison.producthuntlike.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/27.
 */
public class Comment {
    @SerializedName("id")
    public int id;


    public String body; // comment's body
    public int post_id;
    public int user_id;
    public int votes;
    public int child_comments_count;
    public User user;
    public ArrayList<Comment> child_comments;
}
