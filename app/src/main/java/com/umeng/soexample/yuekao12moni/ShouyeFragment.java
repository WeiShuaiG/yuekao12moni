package com.umeng.soexample.yuekao12moni;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recker.flybanner.FlyBanner;
import com.umeng.soexample.yuekao12moni.bean.Porduct;
import com.umeng.soexample.yuekao12moni.presenter.PresenterImpl;
import com.umeng.soexample.yuekao12moni.presenter.ShouPresenterimpl;
import com.umeng.soexample.yuekao12moni.view.Iview;
import com.umeng.soexample.yuekao12moni.view.ShouView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShouyeFragment extends Fragment implements ShouView{



    private int index = 1;

    private ShouPresenterimpl presenter;
    private FlyBanner banner;
    private RecyclerView recy;
    private List<Porduct.DataBean> list;
    private String mUrls = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private RecyleAdapeter adapeter;
    private String[] imgs = { "http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg",
            "http://f.expoon.com/sub/news/2016/01/21/580828_230x162_0.jpg",
            "http://f.expoon.com/sub/news/2016/01/21/745921_230x162_0.jpg"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        banner = view.findViewById(R.id.fly_banner);
        recy = view.findViewById(R.id.recy_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recy.setLayoutManager(staggeredGridLayoutManager);
        list = new ArrayList<>();
        adapeter = new RecyleAdapeter(getActivity(),list);
        recy.setAdapter(adapeter);
        presenter = new ShouPresenterimpl(this);
        presenter.start(mUrls);



        List<String> imgUrl = new ArrayList<>();
        for (int i =0;i<imgs.length;i++){
            imgUrl.add(imgs[i]);
        }
        banner.setImagesUrl(imgUrl);

    }


    @Override
    public void success(Object data) {
        Porduct porduct = (Porduct) data;
        list.addAll(porduct.getData());
        adapeter.notifyDataSetChanged();
    }

    @Override
    public void error(Object error) {

    }
}
