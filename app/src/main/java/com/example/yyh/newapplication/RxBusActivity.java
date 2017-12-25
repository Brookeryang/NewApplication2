package com.example.yyh.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.yyh.newapplication.rxbus.BaseRxBus;
import com.example.yyh.newapplication.rxbus.Person;
import com.example.yyh.newapplication.rxbus.SecondActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;

/**
 * <pre>
 *     author : yyh
 *     e-mail : 1355857303@qq.com
 *     time   : 2017/12/20/15:01
 *     version: 1.0
 *     desc   : RxBus测试
 * </pre>
 */
public class RxBusActivity extends AppCompatActivity {

    @BindView(R.id.te_show)
    TextView mTeShow;
    // 使用 RxBus 版本2
    private Observable<Person> yang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        ButterKnife.bind(this);
        //  doSubscribe();
        //注册
        yang = BaseRxBus.get().register("yang", Person.class);
        //接受数据的方法
        RxBusdata();

    }

    private void RxBusdata() {
        yang.subscribe(new Action1<Person>() {
            @Override
            public void call(Person s) {
                mTeShow.setText("接收到的BeasRxBus数据 : " + s.getName() + "-----" + s.getAge());
            }
        });

    }

    public void SecondOnClick(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }


//    private void doSubscribe() {
//        Subscription subscription = RxBus.getmInstance()
//                .tObservable(String.class)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        mTeShow.setText("接收到的数据 : " + s);
//                    }
//                });
//        RxBus.getmInstance().addSubscription(this, subscription);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  RxBus.getmInstance().unSubscribe(this);

        BaseRxBus.get().unregister("yang", yang);
    }
}
