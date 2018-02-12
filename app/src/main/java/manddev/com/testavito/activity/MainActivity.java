package manddev.com.testavito.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import manddev.com.testavito.R;
import manddev.com.testavito.fragment.MainFragment;
import manddev.com.testavito.viewmodel.GhibliViewModel;

public class MainActivity extends AppCompatActivity implements MainFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_placeholder, MainFragment.instance());
            transaction.commit();
        }
    }

    @Override
    public void onClickItem(GhibliViewModel model) {
        startActivity(DetailActivity.navigate(this, model));
    }
}
