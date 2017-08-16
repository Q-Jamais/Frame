package com.lxhf.frame.mvp.retrofit;

import com.lxhf.frame.mvp.model.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 网络请求参数
 * Created by Jamais on 17/6/15.
 * E-mail : liutl@hfvast.com
 */
public interface ApiStores {

    //baseUrl
    String API_SERVER_URL = "http://www.weather.com.cn/";

    @GET("adat/sk/{cityId}.html")
    Call<MainModel> loadDataByRetrofit(@Path("cityId") String cityId);

    @GET("adat/sk/{cityId}.html")
    Observable<MainModel> loadDataByRetrofitRxjava(@Path("cityId") String cityId);

}
