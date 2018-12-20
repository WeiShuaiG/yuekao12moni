package com.umeng.soexample.yuekao12moni.view;

/**
 * Created by W on 2018/12/20.
 */

public interface ShouView<T> {
    void success(T data);
    void error(T error);
}
