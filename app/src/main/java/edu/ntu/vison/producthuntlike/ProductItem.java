package edu.ntu.vison.producthuntlike;

import java.util.Objects;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductItem {
    private int id, vote;
    private String name, time_stamp;
    private CharSequence description;
    private Object profile_pic;
    public ProductItem(int id, String name, CharSequence description, Object profile_pic, int vote, String time_stamp) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.profile_pic = profile_pic;
        this.vote = vote;
        this.time_stamp = time_stamp;
    }

    public int getVote() {
        return vote;
    }

    public String getName() {
        return name;
    }

    public CharSequence getDescription() {
        return description;
    }
}
