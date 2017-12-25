package com.example.yyh.newapplication.adapter;

import android.content.Context;

import com.example.yyh.newapplication.R;
import com.example.yyh.newapplication.api.ApiService;
import com.example.yyh.newapplication.entity.HotSellVo;

import java.util.List;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/11/6 15:33
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class RefresAdapter extends CommonAdapter4RecyclerView<HotSellVo> {
    public RefresAdapter(Context context, List<HotSellVo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, HotSellVo hotSellVo) {
        holder.setImageViewImage(R.id.iv_hot_image, ApiService.SERVER_API_URL + hotSellVo.getShowPicture());//显示的图片
        holder.setTextViewText(R.id.tv_hotsell_name, hotSellVo.getNAME());
        holder.setTextViewText(R.id.tv_hot_sell_price, String.format("￥%.2f", hotSellVo.getPrice()) + " / " + hotSellVo.getUnit());
        holder.setTextViewText(R.id.tv_storename, "店铺: " + hotSellVo.getStorename());
        holder.setTextViewText(R.id.tv_sales, "销量: " + ((int) hotSellVo.getSales()));

    }
}
