package edu.ntu.vison.producthuntlike.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Vison on 2015/6/24.
 */
public class ProductItem {
    @SerializedName("id") int id;

    @SerializedName("name") String name;

    @SerializedName("day") String day;

    @SerializedName("votes_count") int votesCount;

    @SerializedName("created_at") String createdAt;

    @SerializedName("tagline") String tagline;

    @SerializedName("user") User user;

    @SerializedName("makers") ArrayList<User> makers;

    @SerializedName("screenshot_url") ScreenshotUrl screenshotUrl;

    public User getUser() {
        return user;
    }

    public ArrayList<User> getMakers() {
        return makers;
    }

    public class ScreenshotUrl {
        @SerializedName("300px") String px300;

        @SerializedName("850px") String px850;

        public String getUrl_300px() { return px300; }

        public String getUrl_850px() {
            return px850;
        }
    }

    // Getter

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTagline() {
        return tagline;
    }

    public ScreenshotUrl getScreenshotUrl() {
        return screenshotUrl;
    }
}
