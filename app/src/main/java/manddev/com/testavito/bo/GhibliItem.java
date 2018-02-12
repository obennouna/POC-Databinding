package manddev.com.testavito.bo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Metrozal on 12/02/2018.
 */

public class GhibliItem implements Parcelable {
    public String id;
    public String title;
    public String description;
    public String director;
    public String producer;
    public String release_date;
    public String rt_score;

    public static final Creator<GhibliItem> CREATOR = new Creator<GhibliItem>() {
        @Override
        public GhibliItem createFromParcel(Parcel in) {
            return new GhibliItem(in);
        }

        @Override
        public GhibliItem[] newArray(int size) {
            return new GhibliItem[size];
        }
    };

    public GhibliItem() {
        id = "";
        title = "";
        description = "";
        director = "";
        producer = "";
        release_date = "";
        rt_score = "";
    }

    private GhibliItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        director = in.readString();
        producer = in.readString();
        release_date = in.readString();
        rt_score = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(director);
        dest.writeString(producer);
        dest.writeString(release_date);
        dest.writeString(rt_score);
    }
}
