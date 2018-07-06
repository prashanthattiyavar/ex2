package com.prashant.test.DI;

import android.content.Context;
import com.google.gson.GsonBuilder;
import com.prashant.test.BuildConfig;
import com.prashant.test.R;
import com.prashant.test.business.BasicApi;
import com.prashant.test.business.BasicUseCase;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DIModule {
    private Context mContext;

    public DIModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public BasicUseCase provideCoreUseCase(BasicApi coreApi) {
        return new BasicUseCase(coreApi);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor);
        } else {
            okHttpClientBuilder
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS);
        }

        return okHttpClientBuilder.build();
    }


    @Provides
    @Singleton
    public BasicApi provideCoreApi(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mContext.getApplicationContext().getString(R.string.base_url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(BasicApi.class);
    }
}
