package com.sqli.workshop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.sqli.workshop.R;
import com.sqli.workshop.adapter.GhibliAdapter;
import com.sqli.workshop.bo.GhibliItem;
import com.sqli.workshop.business.GhibliBusinessService;
import com.sqli.workshop.loader.GhibliListLoader;
import com.sqli.workshop.viewmodel.GhibliViewModel;

/**
 * @author Metrozal on 12/02/2018.
 */

public class MainFragment extends Fragment implements GhibliAdapter.Listener, LoaderManager.LoaderCallbacks<ArrayList<GhibliItem>>, SwipeRefreshLayout.OnRefreshListener {

    private final static int LOADER_ID = 1;

    // Views
    private SwipeRefreshLayout mSwipe;
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

        mSwipe = view.findViewById(R.id.swipe_to_refresh);
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mService = new GhibliBusinessService();

        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                mRecycler.getContext(),
                layoutManager.getOrientation()
        );
        mRecycler.addItemDecoration(decoration);

        mAdapter = new GhibliAdapter(this);
        mRecycler.setAdapter(mAdapter);

        mSwipe.setOnRefreshListener(this);

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
        mSwipe.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(LOADER_ID, null, this).forceLoad();
    }

    public interface Listener {
        void onClickItem(final GhibliViewModel model);
    }
}
