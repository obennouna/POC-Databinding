package com.sqli.workshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sqli.workshop.R;
import com.sqli.workshop.fragment.DetailFragment;
import com.sqli.workshop.viewmodel.GhibliViewModel;

/**
 * @author Metrozal on 12/02/2018.
 */

public class DetailActivity extends AppCompatActivity {

    private final static String EXTRA_VIEW_MODEL = "EXTRA_VIEW_MODEL";

    public static Intent navigate(final Context context, final GhibliViewModel model) {
        final Intent intent = new Intent(context, DetailActivity.class);

        intent.putExtra(EXTRA_VIEW_MODEL, model);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        if (savedInstanceState == null) {
            GhibliViewModel model = getIntent().getExtras().getParcelable(EXTRA_VIEW_MODEL);
            if (model != null) {
                setTitle(model.getTitleWithoutLabel(this));
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_placeholder, DetailFragment.instance(model));
                transaction.commit();
            }
        }
    }

}
