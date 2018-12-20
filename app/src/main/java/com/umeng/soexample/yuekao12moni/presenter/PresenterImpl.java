package com.umeng.soexample.yuekao12moni.presenter;

import com.umeng.soexample.yuekao12moni.R;
import com.umeng.soexample.yuekao12moni.callback.MyCallBack;
import com.umeng.soexample.yuekao12moni.model.ModelImpl;
import com.umeng.soexample.yuekao12moni.view.Iview;

/**
 * Created by W on 2018/12/19.
 */

public class PresenterImpl implements Presenter {
    private Iview iview;
    private ModelImpl model;
    public PresenterImpl(Iview iview){
        this.iview = iview;
        model = new ModelImpl();
    }
    @Override
    public void getString(String url) {
        model.setStringStr(url, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iview.getData(success);
            }

            @Override
            public void setError(Object error) {
                iview.getData(error);
            }
        });
    }


}
