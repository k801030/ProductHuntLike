package edu.ntu.vison.producthuntlike.model;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vison on 2015/6/27.
 */
public class User {
    int id;
    String name;

    @SerializedName("image_url") public ImageUrl imageUrl;

    public class ImageUrl {
        @SerializedName("30px") String px30;
        public String getPx30() { return px30; }

        @SerializedName("50px") String px50;
        public String getPx50() { return px50; }
    }

    // Getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ImageUrl getImageUrl() {
        return imageUrl;
    }

}
