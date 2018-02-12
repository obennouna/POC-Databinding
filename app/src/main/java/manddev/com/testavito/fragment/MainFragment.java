package manddev.com.testavito.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import manddev.com.testavito.R;
import manddev.com.testavito.adapter.GhibliAdapter;
import manddev.com.testavito.bo.GhibliItem;
import manddev.com.testavito.business.GhibliBusinessService;
import manddev.com.testavito.loader.GhibliListLoader;
import manddev.com.testavito.viewmodel.GhibliViewModel;

/**
 * @author Metrozal on 12/02/2018.
 */

public class MainFragment extends Fragment implements GhibliAdapter.Listener, LoaderManager.LoaderCallbacks<ArrayList<GhibliItem>> {

    private final static int LOADER_ID = 1;

    // Views
    private RecyclerView mRecycler;
    private GhibliAdapter mAdapter;

    // Business
    private Listener mListener;
    private GhibliBusinessService mService;

    public static MainFragment instance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_main, root, false);

        mRecycler = view.findViewById(R.id.recycler);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mService = new GhibliBusinessService();

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new GhibliAdapter(this);
        mRecycler.setAdapter(mAdapter);

        // Because we use compatibility AsyncTaskLoader we need to use forceload
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<ArrayList<GhibliItem>> onCreateLoader(int id, Bundle args) {
        return new GhibliListLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<GhibliItem>> loader, ArrayList<GhibliItem> data) {
        final ArrayList<GhibliViewModel> models = mService.generateViewModels(data);

        mAdapter.setData(models);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<GhibliItem>> loader) {

    }

    @Override
    public void onItemClick(GhibliViewModel model) {
        if (mListener != null) {
            mListener.onClickItem(model);
        }
    }

    public interface Listener {
        void onClickItem(final GhibliViewModel model);
    }
}
