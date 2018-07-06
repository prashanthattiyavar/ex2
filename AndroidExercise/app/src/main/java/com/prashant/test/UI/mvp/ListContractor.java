package com.prashant.test.UI.mvp;

import com.prashant.test.business.DataObject;
import com.prashant.test.business.DomainModel;

public interface ListContractor {

    interface View {

    void loadDataList(DomainModel dataList);
    void openDataDetails(DataObject mData);
    void displayErrorResponse(String errorMessage);

    void showLoading();
    void hideLoading();
    }

    interface Presenter {
        void getDataList();
        void onDataClicked(DataObject mData);

    }
}

