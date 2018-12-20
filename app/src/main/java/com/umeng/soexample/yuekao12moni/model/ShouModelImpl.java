package com.umeng.soexample.yuekao12moni.model;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.umeng.soexample.yuekao12moni.OkHttputils;
import com.umeng.soexample.yuekao12moni.bean.Porduct;
import com.umeng.soexample.yuekao12moni.callback.MyCallBack;

import java.io.IOException;

/**
 * Created by W on 2018/12/20.
 */

public class ShouModelImpl implements ShouModel{
    private MyCallBack callBack;
    @Override
    public void setString(String url, MyCallBack callBack) {
        this.callBack = callBack;
        new MyTask().execute(url);
    }
    class MyTask extends AsyncTask<String,Void,Porduct>{


        @Override
        protected Porduct doInBackground(String... strings) {
            try {
                String jsonStr = OkHttputils.getInstance().get(strings[0]);
                Gson gson = new Gson();
                Porduct porduct = gson.fromJson(jsonStr,Porduct.class);
                return porduct;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Porduct porduct) {
            super.onPostExecute(porduct);
            callBack.setSuccess(porduct);
        }
    }
}
