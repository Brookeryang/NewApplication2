package com.example.yyh.newapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yyh.newapplication.adapter.RefresAdapter;
import com.example.yyh.newapplication.api.Api;
import com.example.yyh.newapplication.entity.HotSellVo;
import com.jaydenxiao.common.basebean.BaseRespose;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.baserx.SimpleSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

public class RefreshActivity extends AppCompatActivity {
    SmartRefreshLayout refreshlayout;
    private RecyclerView reGoodsList;
    private RefresAdapter adapter;
    private int pageNo = 0;
    private int pageSize = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initViews();
    }

    private void initViews() {
        reGoodsList = (RecyclerView) findViewById(R.id.re_goods_list);
        refreshlayout = (SmartRefreshLayout) findViewById(R.id.refresh_layout);
        getHttpData();
        adapter = new RefresAdapter(RefreshActivity.this, null, R.layout.fragment_hot_sell_goods_item);
        reGoodsList.setLayoutManager(new LinearLayoutManager(RefreshActivity.this));
        reGoodsList.setAdapter(adapter);
    }

    private void getHttpData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("pageSize", pageSize + "");
        params.put("pageNo", pageNo + "");
        Api.getDefault().getGuessLikeGoods(params).compose(RxSchedulers.<BaseRespose<List<HotSellVo>>>io_main())
                .subscribe(new SimpleSubscriber<List<HotSellVo>>(RefreshActivity.this) {
                    @Override
                    public void onError(String error) {
                        ToastUitl.showToastDefault(RefreshActivity.this, error + "热销的商品");
                        //刷新结束
                        refreshlayout.finishRefresh();
                    }

                    @Override
                    public void onSuccess(List<HotSellVo> data) {
                        if (pageNo == 0) {
                            adapter.mData.clear();
                            adapter.mData.addAll(data);
                        } else {
                            adapter.mData.addAll(data);
                        }
                        adapter.notifyDataSetChanged();
                        //这里判断刷新是否结束
                        refreshlayout.finishRefresh();
                        if (data.size() == 0 || data.size() < pageSize) {
                            refreshlayout.setLoadmoreFinished(true);
                            refreshlayout.finishLoadmore();
                        } else {
                            refreshlayout.setLoadmoreFinished(false);
                            refreshlayout.finishLoadmore();
                        }
                    }
                });
        //刷新方法
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //1.初始化nexPager
                pageNo = 0;
                //2.请求网络数据
                getHttpData();
            }
        });
        //加载方法
        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNo++;
                //2.请求网络数据
                getHttpData();

            }
        });
    }


}
