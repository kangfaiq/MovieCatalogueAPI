package net.fortinity.moviecatalogueapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TVShow implements Parcelable {
    private String title;
    private String photo;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.photo);
        dest.writeString(this.description);
    }

    public TVShow() {
    }

    protected TVShow(Parcel in) {
        this.title = in.readString();
        this.photo = in.readString();
        this.description = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    public TVShow(JSONObject object) {
        try {
            String title = object.getString("name");
            String photo = object.getString("poster_path");
            String description = object.getString("overview");

            this.title = title;
            this.photo = photo;
            this.description = description;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
