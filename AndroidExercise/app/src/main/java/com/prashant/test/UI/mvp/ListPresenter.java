package com.prashant.test.UI.mvp;

import android.view.View;

import com.prashant.test.business.BasicUseCase;
import com.prashant.test.business.BasicUseCase;
import com.prashant.test.business.DataObject;
import com.prashant.test.UI.mvp.ListContractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ListPresenter implements ListContractor.Presenter{

    private ListContractor.View mView;
    private BasicUseCase mBasicUsecase;

    public ListPresenter(final BasicUseCase coreUseCase , final ListContractor.View conView)
    {
        mBasicUsecase = coreUseCase;
        mView = conView;
    }

    public ListPresenter(BasicUseCase mMockCoreUsecase, View mMockView) {
    }

    @Override
    public void getDataList() {
        mBasicUsecase.getContent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> mView.loadDataList(response),
                        error -> mView.displayErrorResponse(error.getMessage())
                );
    }

    @Override
    public void onDataClicked(DataObject mActor) {
        mView.openDataDetails(mActor);
    }
}
