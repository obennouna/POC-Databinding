package com.sqli.workshop.viewmodel;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.sqli.workshop.R;
import com.sqli.workshop.bo.GhibliItem;

/**
 * @author Metrozal on 12/02/2018.
 */

public class GhibliViewModel implements Parcelable {

    private final GhibliItem mItem;

    public static final Creator<GhibliViewModel> CREATOR = new Creator<GhibliViewModel>() {
        @Override
        public GhibliViewModel createFromParcel(Parcel in) {
            return new GhibliViewModel(in);
        }

        @Override
        public GhibliViewModel[] newArray(int size) {
            return new GhibliViewModel[size];
        }
    };

    public GhibliViewModel(GhibliItem item) {
        this.mItem = item;
    }

    private GhibliViewModel(Parcel in) {
        mItem = in.readParcelable(GhibliItem.class.getClassLoader());
    }

    public String getTitle(final Context context) {
        return mItem != null ? context.getString(R.string.placeholder_title, mItem.title) : context.getString(R.string.default_title);
    }

    public String getTitleWithoutLabel(final Context context) {
        return mItem != null ? mItem.title : context.getString(R.string.default_title);
    }

    public String getScore(final Context context) {
        return mItem != null ? context.getString(R.string.placeholder_score, mItem.rt_score) : context.getString(R.string.default_score);
    }

    public String getDescription(final Context context) {
        return mItem != null ? context.getString(R.string.placeholder_description, mItem.description) : context.getString(R.string.default_description);
    }

    public String getDirector(final Context context) {
        return mItem != null ? context.getString(R.string.placeholder_director, mItem.director) : context.getString(R.string.default_director);
    }

    public String getProducer(final Context context) {
        return mItem != null ? context.getString(R.string.placeholder_producer, mItem.producer) : context.getString(R.string.default_producer);
    }

    public String getReleaseDate(final Context context) {
        return mItem != null ? context.getString(R.string.placeholder_release_date, mItem.release_date) : context.getString(R.string.default_release_date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mItem, flags);
    }
}
