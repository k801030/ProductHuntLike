package edu.ntu.vison.producthuntlike.model;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/27.
 */
public class Comment {
    int id;
    String body; // comment's body
    int post_id;
    int user_id;
    int votes;
    int child_comments_count;
    User user;
    ArrayList<Comment> child_comments;
}
