package com.prashant.test.UI.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prashant.test.R;
import com.prashant.test.DI.DIComponent;
import com.prashant.test.DI.DIModule;
import com.prashant.test.DI.DaggerDIComponent;
import com.prashant.test.DI.DIComponent;
import com.prashant.test.business.DataObject;
import com.prashant.test.business.BasicUseCase;
import com.prashant.test.business.DataObject;
import com.prashant.test.business.DomainModel;
import com.prashant.test.UI.mvp.ListAdapter;
import com.prashant.test.UI.mvp.ListContractor;
import com.prashant.test.UI.mvp.ListPresenter;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ListContractor.View{

    private static final String TAG = ListFragment.class.getSimpleName();
    OnRowSelectedListener mCallback;

    @Inject
    BasicUseCase mCoreUsecase;

    @BindView(R.id.rootView)
    ViewGroup mRootView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.content_rv)
    RecyclerView mContentRecyclerView;

    private ListContractor.Presenter mPresenter;
    private ListAdapter mAdapter;

   public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.fragment_content_list, container, false);
        ButterKnife.bind(this,mRoot);
        DIComponent mActorComponent = DaggerDIComponent.builder().dIModule(new DIModule(getContext())).build();
        mActorComponent.inject(this);
        return mRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ListPresenter(mCoreUsecase, this);
        mContentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ListAdapter(mPresenter, getContext());
        mContentRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnRowSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override

    public void onResume()
    {
        super.onResume();
        mPresenter.getDataList();
    }

    @Override
    public void loadDataList(DomainModel dataList) {
        hideLoading();
        mAdapter.displayData(dataList.getmData());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void openDataDetails(DataObject mData) {
       mCallback.onArticleSelected(mData);
    }

    @Override
    public void displayErrorResponse(String errorMessage) {
        hideLoading();
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
        Log.e(TAG, errorMessage);
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    public interface OnRowSelectedListener {
        public void onArticleSelected(DataObject mData);

    }
}
