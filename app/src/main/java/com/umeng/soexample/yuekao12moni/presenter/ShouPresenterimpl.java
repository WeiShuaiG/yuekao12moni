package com.umeng.soexample.yuekao12moni.presenter;

import com.umeng.soexample.yuekao12moni.bean.MyData;
import com.umeng.soexample.yuekao12moni.callback.MyCallBack;
import com.umeng.soexample.yuekao12moni.model.ShouModelImpl;
import com.umeng.soexample.yuekao12moni.view.ShouView;

/**
 * Created by W on 2018/12/20.
 */

public class ShouPresenterimpl implements ShouPresenter {
    private ShouModelImpl model;
    private ShouView view;
    public ShouPresenterimpl(ShouView view){
        this.view = view;
        model = new ShouModelImpl();
    }
    @Override
    public void start(String url) {
        model.setString(url, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                view.success(success);
            }

            @Override
            public void setError(Object error) {
                view.error(error);
            }
        });

    }
}
