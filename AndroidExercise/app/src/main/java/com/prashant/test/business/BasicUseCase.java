package com.prashant.test.business;

import com.prashant.test.business.DomainModel;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class BasicUseCase {

    private static final String TAG = BasicUseCase.class.getSimpleName();
    private BasicApi mBasicApi;

    public BasicUseCase(BasicApi actApi) {
        if(actApi != null)
        {
            mBasicApi = actApi;
        }
    }

    public Observable<DomainModel> getContent() {
        return Observable.defer(() -> {
            return mBasicApi.getJsonContent();
        })
                .subscribeOn(Schedulers.io())
                .map(Response::body);
    }
}
