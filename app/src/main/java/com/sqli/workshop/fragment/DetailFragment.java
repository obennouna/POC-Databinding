package com.sqli.workshop.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqli.workshop.R;
import com.sqli.workshop.databinding.FragmentDetailBinding;
import com.sqli.workshop.viewmodel.GhibliViewModel;

/**
 * @author Metrozal on 12/02/2018.
 */

public class DetailFragment extends Fragment {

    private static final String ARG_VIEW_MODEL = "ARG_VIEW_MODEL";
    private FragmentDetailBinding mBinding;


    public static DetailFragment instance(GhibliViewModel model) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable(ARG_VIEW_MODEL, model);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle bundle) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, root, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final GhibliViewModel model = getArguments().getParcelable(ARG_VIEW_MODEL);
        mBinding.setModel(model);
        mBinding.executePendingBindings();
    }
}
