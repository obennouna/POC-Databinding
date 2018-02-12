package manddev.com.testavito.viewmodel;

import android.content.Context;

import manddev.com.testavito.R;
import manddev.com.testavito.bo.GhibliItem;

/**
 * @author Metrozal on 12/02/2018.
 */

public class GhibliViewModel {

    private final GhibliItem mItem;

    public GhibliViewModel(GhibliItem item) {
        this.mItem = item;
    }

    public String getTitle(final Context context) {
        return mItem != null ? mItem.title : context.getString(R.string.default_title);
    }

    public String getScore(final Context context) {
        return mItem != null ? mItem.rt_score : context.getString(R.string.default_score);
    }
}
