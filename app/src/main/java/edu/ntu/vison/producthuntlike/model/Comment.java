package edu.ntu.vison.producthuntlike.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Vison on 2015/6/27.
 */
public class Comment {
    @SerializedName("id") int id;

    @SerializedName("body") String body;

    @SerializedName("post_id") int postId;

    @SerializedName("user_id") int userId;

    @SerializedName("votes") int votes;

    @SerializedName("child_comments count") int childCommentsCount;

    @SerializedName("user") User user;

    @SerializedName("child_comments") ArrayList<Comment> child_comments;

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public int getVotes() {
        return votes;
    }

    public int getChildCommentsCount() {
        return childCommentsCount;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Comment> getChild_comments() {
        return child_comments;
    }
}
