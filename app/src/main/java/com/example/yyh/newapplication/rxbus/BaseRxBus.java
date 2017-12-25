package com.example.yyh.newapplication.rxbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * <pre>
 *     author : yyh
 *     time :  2017/12/20 16:40
 *     version: 1.2
 *     desc   : 描述:封装的RxBus
 * </pre>
 */
public class BaseRxBus {
    private HashMap<Object, List<Subject>> maps = new HashMap<>();
    private static BaseRxBus instance;

    private BaseRxBus() {
    }

    /**
     * GCL
     */
    public static BaseRxBus get() {
        if (instance == null) {
            synchronized (BaseRxBus.class) {
                if (instance == null) {
                    instance = new BaseRxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 注册Bus
     *
     * @param tag
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> Observable<T> register(Object tag, Class<T> clazz) {
        List<Subject> subjects = maps.get(tag);
        if (subjects == null) {
            subjects = new ArrayList<>();
            maps.put(tag, subjects);
        }
        Subject<T, T> subject = PublishSubject.<T>create();
        subjects.add(subject);
        return subject;
    }

    /**
     * 注销RxBus
     *
     * @param tag
     * @param observable
     */
    public void unregister(Object tag, Observable observable) {
        List<Subject> subjects = maps.get(tag);
        if (subjects != null) {
            subjects.remove(observable);
            if (subjects.isEmpty()) {
                maps.remove(tag);
            }
        }
    }

    /**
     * 发送方法
     *
     * @param o
     */
    public void post(Object o) {
        post(o.getClass().getSimpleName());
    }

    /**
     * 发送方法
     *
     * @param tag
     * @param o
     */
    public void post(Object tag, Object o) {
        List<Subject> subjects = maps.get(tag);
        if (subjects != null && !subjects.isEmpty()) {
            for (Subject s : subjects) {
                s.onNext(o);
            }
        }
    }

}
