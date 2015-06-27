package edu.ntu.vison.producthuntlike.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductItem {
    @SerializedName("id") public int id;

    @SerializedName("name") public String name;

    @SerializedName("day") public String day;

    @SerializedName("votes_count") public int votesCount;

    @SerializedName("created_at") public String createdAt;

    @SerializedName("tagline") public String tagline;

    @SerializedName("screenshot_url") public ScreenshotUrl screenshotUrl;

    public class ScreenshotUrl {
        @SerializedName("300px") public String url_300px;
        @SerializedName("850px") public String url_850px;
    }


    public ProductItem() {

    }

    // without profile_pic
    public ProductItem(int id, String name, String tagline, int votes_count, String created_at) {
        super();
        this.id = id;
        this.tagline = tagline;
        this.votesCount = votes_count;
        this.createdAt = created_at;
    }
}
