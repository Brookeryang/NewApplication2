package com.example.yyh.newapplication.http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yyh.newapplication.R;
import com.example.yyh.newapplication.http.bean.IpModel;
import com.example.yyh.newapplication.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpContentActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Show01;
    private Button button;
    private Intent mIntent;
    private final int RequestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_content);
        // testMethodtest();
        initView();
    }

    private void initView() {
        Show01 = (TextView) findViewById(R.id.tv_01);
        button = (Button) findViewById(R.id.but_01);
        button.setOnClickListener(this);

    }

    private void testMethodtest() {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //初始化接口
        IpServiceForPost ipServiceForPost = retrofit.create(IpServiceForPost.class);
        Call<IpModel> call = ipServiceForPost.getIpMsg("59.108.54.37");
        //异步请求
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                String content = response.body().getDate();
                ToastUtil.showToastDefault(HttpContentActivity.this, content);
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_01:
                mIntent = new Intent(this, DataActivity_02.class);
                startActivityForResult(mIntent, RequestCode);
                break;
            default:
                break;
        }
    }
    //获取页面2回传过来的数据


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == 1) {
                String content = data.getStringExtra("Data_01");
                if (!TextUtils.isEmpty(content)){
                    Show01.setText(content);
                }else {
                    ToastUtil.showToastDefault(this,"Data为空");
                }
            }
        }
    }
}
