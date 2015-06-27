package edu.ntu.vison.producthuntlike.model;

import java.util.Objects;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductItem {
    private int id, votes_count;
    private String name, created_at;
    private CharSequence tagline;
    private String screenshot_url;

    // without profile_pic
    public ProductItem(int id, String name, CharSequence tagline, int votes_count, String created_at) {
        super();
        this.id = id;
        this.name = name;
        this.tagline = tagline;
        this.votes_count = votes_count;
        this.created_at = created_at;
    }
    public int getId() { return id; }

    public int getVoteCount() {
        return votes_count;
    }

    public String getName() {
        return name;
    }

    public CharSequence getTagline() { return tagline; }
}
