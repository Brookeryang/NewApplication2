package com.example.yyh.newapplication.rxbus;

import java.util.HashMap;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/12/20 15:16
 *     version: 1.0
 *     desc   : 描述XXXX
 * </pre>
 */
public class RxBus {
    private static volatile RxBus mInstance;
    private SerializedSubject<Object, Object> mSubject;
    private HashMap<String, CompositeSubscription> mSubscriptionHashMap;

    private RxBus() {
        //由于Subject类是非线程安全的，所以我们通过它的子类SerializedSubject将PublishSubject转换成一个线程安全的Subject对象。
        // 之后可通过单例方法getInstance()进行RxBus的初始化
        /**
         * 一般情况我们都是先订阅事件，然后发送事件，如果我们反过来，先发送了事件，再进行订阅操作，怎么保证发送的事件不丢失呢？也就是EventBus中的StickyEven功能。
         其实通过RxJava实现类似的功能很简单，Subject有一个子类BehaviorSubject，在被订阅之前，它可以缓存最近一个发送给它的事件，当被订阅后，它会立刻将缓存事件发送给订阅者
         链接：http://www.jianshu.com/p/3a3462535b4d#
         來源：简书
         */
        mSubject = new SerializedSubject<>(BehaviorSubject.create());
//        mSubject = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 双重检测模式（GCL）
     *
     * @return
     */
    public static RxBus getmInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送事件
     *
     * @param o
     */
    public void post(Object o) {
        mSubject.onNext(o);
    }

    /**
     * 返回指定类型的Observable
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Observable<T> tObservable(final Class<T> type) {
        return mSubject.ofType(type);
    }

    /**
     * 判断是否有观察者订阅
     *
     * @return
     */
    public boolean hasObserver() {
        return mSubject.hasObservers();
    }

    /**
     * 一个默认的订阅方法
     *
     * @param <T>
     * @param type
     * @param next
     * @param error
     * @return
     */
    public <T> Subscription doSubscribe(Class<T> type, Action1<T> next, Action1<Throwable> error) {

        return tObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    /**
     * 保存订阅后的
     *
     * @param o
     * @param subscription
     */
    public void addSubscription(Object o, Subscription subscription) {
        if (mSubscriptionHashMap == null) {
            mSubscriptionHashMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionHashMap.get(key) != null) {
            mSubscriptionHashMap.get(key).add(subscription);
        } else {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            compositeSubscription.add(subscription);
            mSubscriptionHashMap.put(key, compositeSubscription);
        }
    }

    /**
     * 取消订阅
     *
     * @param o
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionHashMap == null) {
            return;
        }

        String key = o.getClass().getName();
        if (!mSubscriptionHashMap.containsKey(key)) {
            return;
        }

        if (mSubscriptionHashMap.get(key) != null) {
            mSubscriptionHashMap.get(key).unsubscribe();
        }
        mSubscriptionHashMap.remove(key);
    }
}
