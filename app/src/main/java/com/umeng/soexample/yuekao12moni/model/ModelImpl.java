package com.umeng.soexample.yuekao12moni.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.umeng.soexample.yuekao12moni.bean.MyData;
import com.umeng.soexample.yuekao12moni.bean.Porduct;
import com.umeng.soexample.yuekao12moni.callback.MyCallBack;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by W on 2018/12/19.
 */

public class ModelImpl implements Model {
    private MyCallBack callBack;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            MyData data = (MyData) msg.obj;
            callBack.setSuccess(data);
            callBack.setError(data);
        }
    };
    @Override
    public void setStringStr(String url, MyCallBack callBack) {
        this.callBack = callBack;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Reader reader = response.body().charStream();
                MyData data = new Gson().fromJson(reader,MyData.class);
                handler.sendMessage(handler.obtainMessage(0,data));
            }
        });
    }


}
