package com.umeng.soexample.yuekao12moni.callback;

/**
 * Created by W on 2018/12/19.
 */

public interface MyCallBack<T> {
    void setSuccess(T success);
    void setError(T error);
}
