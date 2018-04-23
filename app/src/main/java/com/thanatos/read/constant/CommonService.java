package com.thanatos.read.constant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by wang.ming5 on 2018/4/23.
 *  接口请求， 配置请求方式、url、参数等信息
 */

public interface CommonService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<String>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);
}
