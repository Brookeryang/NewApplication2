package com.example.yyh.newapplication.http;

import com.example.yyh.newapplication.http.bean.IpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/11/20 14:21
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public interface IpServiceForPost {
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Call<IpModel> getIpMsg(@Field("ip") String first);
}
