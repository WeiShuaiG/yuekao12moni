package com.umeng.soexample.yuekao12moni;

import com.umeng.soexample.yuekao12moni.model.ShouModel;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by W on 2018/12/20.
 */

public class OkHttputils {
    private OkHttpClient okHttpClient;

    private OkHttputils() {
        okHttpClient = new OkHttpClient();
    }
    public static OkHttputils getInstance(){
        return OkHttpHolder.utils;
    }
    static class OkHttpHolder{
        private static final OkHttputils utils = new OkHttputils();
    }
    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
