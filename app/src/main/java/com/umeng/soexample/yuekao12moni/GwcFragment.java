package com.umeng.soexample.yuekao12moni;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.umeng.soexample.yuekao12moni.bean.MyData;
import com.umeng.soexample.yuekao12moni.presenter.PresenterImpl;
import com.umeng.soexample.yuekao12moni.view.Iview;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GwcFragment extends Fragment implements Iview {


    private CheckBox Check_All;
    private TextView All_Price;
    private TextView Go_To_JS;
    private ExpandableListView Expand_View;
    private PresenterImpl presenter;

    private String mUrl = "http://www.wanandroid.com/tools/mockapi/6523/restaurant-list";
    private MyAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gwc, container, false);
        Check_All = view.findViewById(R.id.check_All);
        All_Price = view.findViewById(R.id.All_price);
        Go_To_JS = view.findViewById(R.id.go_to_js);
        Expand_View = view.findViewById(R.id.expand_lv);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Expand_View.setGroupIndicator(null);
        presenter = new PresenterImpl(this);
        presenter.getString(mUrl);


    }

    @Override
    public void getData(Object data) {
        final MyData bean = (MyData) data;
        List<MyData.DataBean> list = bean.getData();
        mAdapter = new MyAdapter(list,getActivity());
        Expand_View.setAdapter(mAdapter);
        for (int i = 0; i <list.size() ; i++) {
            Expand_View.expandGroup(i);
        }
        mAdapter.setCallBack(new MyAdapter.AdapterCallBack() {

            @Override
            public void setGrouCheck(int groupPosition) {
                boolean childAllChecl = mAdapter.isChildAllCheck(groupPosition);
                mAdapter.childAllCheck(groupPosition,!childAllChecl);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setChildCheck(int groupPosition, int childPosition) {
                boolean childAllCheck = mAdapter.isChildCheck(groupPosition,childPosition);
                mAdapter.setChildCheck(groupPosition,childPosition,!childAllCheck);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }

            @Override
            public void setNumber(int groupPosition, int childPosition, int number) {
                mAdapter.setShangPinNumber(groupPosition, childPosition, number);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }
        });
        Check_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean allGoods = mAdapter.isAllGoods();
                mAdapter.setAllGoodsIsChecked(!allGoods);
                mAdapter.notifyDataSetChanged();
                flushBottomLayout();
            }
        });

    }

    private void flushBottomLayout() {
        boolean allGoods = mAdapter.isAllGoods();
        Check_All.setChecked(allGoods);
        float allGoodsPrice = mAdapter.getAllgoodsPrice();
        int allGoodsNumber = mAdapter.getAllGoodsNumber();
        All_Price.setText("价格:" + allGoodsPrice);
        Go_To_JS.setText("去结算（"+allGoodsNumber+")");
    }
}
