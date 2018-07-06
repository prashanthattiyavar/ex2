package com.prashant.test;

import com.prashant.test.business.BasicUseCase;
import com.prashant.test.business.DataObject;
import com.prashant.test.business.DomainModel;
import com.prashant.test.ui.mvp.ListContractor;
import com.prashant.test.ui.mvp.ListPresenter;

import org.junit.Test;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListPresenterTest{

    @Mock
    private ListContractor.View mMockView;

    @Mock
    private BasicUseCase mMockCoreUsecase;

    private ListPresenter mDataListPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mDataListPresenter = new ListPresenter(mMockCoreUsecase, mMockView);
    }

    /**
     * Test success response for {@link BasicUseCase#getContent()}
     */
    @Test
    public void testLoadActorsMethodIsCalledIfRequestSuccess() {
        when(mMockCoreUsecase.getContent()).thenReturn(Observable.just(getActorsResponse()));
        mDataListPresenter.getDataList();
        verify(mMockView).loadDataList(any());
    }

    /**
     * Mock data
     * @return {@link DomainModel}
     */
    private DomainModel getActorsResponse() {
        DomainModel dataResponse = new DomainModel();
        List<DataObject> data = new ArrayList<>();
        DataObject objData = new DataObject();
        objData.setmName("Name");
        objData.setmDescription("Description");
        objData.setmImage("ImageUrl");
        data.add(objData);
        dataResponse.setmData(data);

        return dataResponse;
    }

}