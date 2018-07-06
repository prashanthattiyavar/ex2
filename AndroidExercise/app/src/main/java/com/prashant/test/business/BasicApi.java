package com.prashant.test.business;

import com.prashant.test.business.DomainModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface BasicApi {

    @GET("facts")
    Observable<Response<DomainModel>> getJsonContent();

}
