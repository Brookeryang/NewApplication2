package com.example.yyh.newapplication.api;

import com.example.yyh.newapplication.entity.HotSellVo;
import com.jaydenxiao.common.basebean.BaseRespose;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/11/6 15:13
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public interface ApiService {
    public static final String SERVER_API_URL = "http://42.63.14.245:8001";//服务端api地址

    // 首页 热销的商品列表 getGuessLikeGoods
    @FormUrlEncoded
    @POST("app-biz/buyer/getGuessYouLikeGoods.action")
    Observable<BaseRespose<List<HotSellVo>>> getGuessLikeGoods(@FieldMap Map<String, String> params);
}
