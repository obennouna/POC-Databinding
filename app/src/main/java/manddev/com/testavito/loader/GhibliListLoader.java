package manddev.com.testavito.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import manddev.com.testavito.R;
import manddev.com.testavito.bo.GhibliItem;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Metrozal on 12/02/2018.
 */

public class GhibliListLoader extends AsyncTaskLoader<ArrayList<GhibliItem>> {

    // That's the closest and quickest thing to a "cache", bare with me :)
    private ArrayList<GhibliItem> mData;

    public GhibliListLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<GhibliItem> loadInBackground() {
        if (mData != null) {
            return mData;
        }
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(getContext().getString(R.string.ghibli_api))
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            Gson gson = new Gson();
            if (response != null && response.body() != null) {
                return gson.fromJson(response.body().string(), new TypeToken<ArrayList<GhibliItem>>() {}.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void deliverResult(ArrayList<GhibliItem> data) {
        mData = data;
        super.deliverResult(data);
    }
}
