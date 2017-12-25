package com.example.yyh.newapplication.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;


/**
 * <pre>
 *     author : 程志强
 *     e-mail : 18394188838@163.com
 *     time   : 2017/06/09
 *     version: 1.0.0
 *     desc   : SpannableString和SpannableStringBuilder相关的帮助类
 *     http://blog.csdn.net/harvic880925/article/details/38984705
 *     http://blog.csdn.net/lovexjyong/article/details/17021235
 * </pre>
 */

public class SpannUtils {
    public SpannUtils() {
        throw new UnsupportedOperationException("实例化异常");
    }

    /**
     * 改变局部字体颜色
     *
     * @param strResId 更改颜色的文本的资源第
     * @param ctx      上下文
     * @param id       颜色id
     * @return
     */
    public static SpannableString setSpanWithIncludeAll(@StringRes int strResId, Context ctx, @ColorRes int id) {
        String strByRes = ctx.getResources().getString(strResId);
        return setSpanWithIncludeAll(strByRes, ctx, id);
    }

    /**
     * 改变局部字体颜色
     *
     * @param txt 更改颜色的文本
     * @param ctx 上下文
     * @param id  颜色id
     * @return
     */
    public static SpannableString setSpanWithIncludeAll(CharSequence txt, Context ctx, @ColorRes int id) {
        if (TextUtils.isEmpty(txt)) return null;
        SpannableString spanStr = new SpannableString(txt);
        ForegroundColorSpan fcspan = new ForegroundColorSpan(ContextCompat.getColor(ctx, id));
        spanStr.setSpan(fcspan, 0, txt.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spanStr;
    }


    /**
     * 改变局部字体颜色
     *
     * @param txt   文本
     * @param ctx   上下文
     * @param start 开始位置
     * @param end   结束位置
     * @param id    颜色id
     * @return
     */
    public static SpannableString setSpanWithIncludeAll(CharSequence txt, Context ctx, int start, int end, @ColorRes int id) {
        if (TextUtils.isEmpty(txt)) return null;
        SpannableString spanStr = new SpannableString(txt);
        ForegroundColorSpan fcspan = new ForegroundColorSpan(ContextCompat.getColor(ctx, id));
        spanStr.setSpan(fcspan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spanStr;
    }

    /**
     * 改变局部字体颜色、字体大小
     *
     * @param txt
     * @param ctx
     * @param start
     * @param end
     * @param id
     * @param size
     * @return
     */

    public static SpannableString setSpanWithIncludeAll(CharSequence txt, Context ctx, int start, int end, @ColorRes int id, int size) {
        if (TextUtils.isEmpty(txt)) return null;
        SpannableString spanStr = new SpannableString(txt);
        ForegroundColorSpan fcspan = new ForegroundColorSpan(ContextCompat.getColor(ctx, id));
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(size);
        spanStr.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spanStr.setSpan(fcspan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spanStr;
    }

    /**
     * 设置字体大小
     *
     * @param txt
     * @param start
     * @param end
     * @param size
     * @return
     */
    public static SpannableString setSpanWithIncludeSizeAll(CharSequence txt, int start, int end, int size) {
        if (TextUtils.isEmpty(txt)) return null;
        SpannableString spanStr = new SpannableString(txt);
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(size);
        spanStr.setSpan(sizeSpan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spanStr;
    }


}
