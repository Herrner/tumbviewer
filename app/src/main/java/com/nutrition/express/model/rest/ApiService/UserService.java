package com.nutrition.express.model.rest.ApiService;

import com.nutrition.express.model.rest.bean.BaseBean;
import com.nutrition.express.model.rest.bean.BlogPosts;
import com.nutrition.express.model.rest.bean.FollowingBlog;
import com.nutrition.express.model.rest.bean.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by huang on 10/18/16.
 */

public interface UserService {
    @GET("/v2/user/following")
    Call<BaseBean<FollowingBlog>> getFollowing(@Header("Authorization") String auth,
                                               @QueryMap Map<String, String> options);

    @GET("/v2/user/info")
    Call<BaseBean<UserInfo>> getInfo(@Header("Authorization") String auth);

    @GET("/v2/user/dashboard")
    Call<BaseBean<BlogPosts>> getDashboard(@Header("Authorization") String auth,
                                           @QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST("/v2/user/follow")
    Call<BaseBean<Void>> follow(@Field("url") String url);

    @FormUrlEncoded
    @POST("/v2/user/unfollow")
    Call<BaseBean<Void>> unfollow(@Field("url") String url);

    @FormUrlEncoded
    @POST("/v2/user/like")
    Call<BaseBean<Void[]>> like(@Field("id") long id, @Field("reblog_key") String key);

    @FormUrlEncoded
    @POST("/v2/user/unlike")
    Call<BaseBean<Void[]>> unlike(@Field("id") long id, @Field("reblog_key") String key);

}
