package com.prashant.test.UI.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prashant.test.R;
import com.prashant.test.UI.activity.BaseFragment;
import com.prashant.test.UI.activity.ListFragment;
import com.prashant.test.business.DataObject;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRowSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
        initFragment(ListFragment.newInstance());
    }

    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, detailFragment);
        transaction.commit();
    }

    @Override
    public void onArticleSelected(DataObject mData) {
        Bundle args = new Bundle();
        args.putSerializable(DataObject.class.getSimpleName(),mData);

        Fragment mFragment = BaseFragment.newInstance();
        mFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.contentFrame,mFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
