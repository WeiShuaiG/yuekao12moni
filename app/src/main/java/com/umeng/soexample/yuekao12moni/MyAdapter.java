package com.umeng.soexample.yuekao12moni;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.yuekao12moni.bean.MyData;

import java.util.List;

/**
 * Created by W on 2018/12/18.
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private List<MyData.DataBean> list;
    private Context context;

    public MyAdapter(List<MyData.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getSpus().size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder groupHolder = null;
        if (view == null){
            groupHolder = new GroupHolder();
            view = View.inflate(context,R.layout.group_layout,null);
            groupHolder.mGroupCheck = view.findViewById(R.id.check_group);
            groupHolder.mGroupName = view.findViewById(R.id.group_name);
            view.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) view.getTag();
        }
        groupHolder.mGroupName.setText(list.get(i).getName()+"");
        boolean childAllCheck = isChildAllCheck(i);
        groupHolder.mGroupCheck.setChecked(childAllCheck);
        groupHolder.mGroupCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterCallBack!= null){
                    adapterCallBack.setGrouCheck(i);
                }
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder childHolder = null;
        if (view == null){
            childHolder = new ChildHolder();
            view = View.inflate(context,R.layout.child_layout,null);
            childHolder.mChildName = view.findViewById(R.id.child_name);
            childHolder.mChildPrice = view.findViewById(R.id.child_price);
            childHolder.mChildCheck = view.findViewById(R.id.child_check);
            childHolder.mImage = view.findViewById(R.id.img_view);
            childHolder.jiajianView = view.findViewById(R.id.jia_jian_view);
            view.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) view.getTag();
        }
        MyData.DataBean.SpusBean spusBean = list.get(i).getSpus().get(i1);
        Glide.with(context).load(spusBean.getPic_url()).into(childHolder.mImage);
        childHolder.mChildName.setText(spusBean.getName()+"");
        childHolder.mChildPrice.setText(spusBean.getSkus().get(0).getPrice());
        childHolder.mChildCheck.setChecked(spusBean.isChildChecked());
        childHolder.jiajianView.setNumber(spusBean.getPraise_num());
        childHolder.mChildCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterCallBack!= null){
                    adapterCallBack.setChildCheck(i,i1);
                }
            }
        });
        childHolder.jiajianView.setonChange(new JiajianView.onCountChange() {
            @Override
            public void setCount(int count) {
                if (adapterCallBack!=null){
                    adapterCallBack.setNumber(i,i1,count);
                }
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    class GroupHolder{
        private CheckBox mGroupCheck;
        private TextView mGroupName;
    }
    class ChildHolder{
        private CheckBox mChildCheck;
        private ImageView mImage;
        private TextView mChildName;
        private TextView mChildPrice;
        private JiajianView jiajianView;
    }
    public void childAllCheck(int groupPosition,boolean isCheck){
        MyData.DataBean dataBean = list.get(groupPosition);
        List<MyData.DataBean.SpusBean> spusBeans = dataBean.getSpus();
        for (int i = 0; i <spusBeans.size() ; i++) {
            MyData.DataBean.SpusBean bean = spusBeans.get(i);
            bean.setChildChecked(isCheck);
        }


    }
    public boolean isChildAllCheck(int groupPosition){
        boolean boo = true;
        MyData.DataBean dataBean = list.get(groupPosition);
        List<MyData.DataBean.SpusBean> spusBeans = dataBean.getSpus();
        for (int i = 0; i <spusBeans.size() ; i++) {
            MyData.DataBean.SpusBean bean = spusBeans.get(i);
            if (!bean.isChildChecked()){
                return false;
            }
        }
        return boo;
    }
    public void setChildCheck(int GroupPosition,int ChildPosition,boolean ischecked){
        MyData.DataBean.SpusBean spusBean = list.get(GroupPosition).getSpus().get(ChildPosition);
        spusBean.setChildChecked(ischecked);
    }
    public boolean isChildCheck(int groupPosition,int childPosition){
        MyData.DataBean.SpusBean spusBean = list.get(groupPosition).getSpus().get(childPosition);
        if (spusBean.isChildChecked()){
            return true;
        }
        return false;
    }
    public void setShangPinNumber(int groupPosition,int childPosition,int number){
        MyData.DataBean.SpusBean spusBean = list.get(groupPosition).getSpus().get(childPosition);
        spusBean.setPraise_num(number);
    }
    public boolean isAllGoods(){
        boolean boo = true;
        for (int i = 0; i <list.size() ; i++) {
            MyData.DataBean dataBean = list.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                MyData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (!spusBean.isChildChecked()){
                    boo = false;
                }
            }
        }
        return boo;
    }
    public void setAllGoodsIsChecked(boolean isAllCheck){
        for (int i = 0; i <list.size() ; i++) {
            MyData.DataBean dataBean = list.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                MyData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                spusBean.setChildChecked(isAllCheck);
            }

        }
    }
    public float getAllgoodsPrice(){
        float allPrice = 0;
        for (int i = 0; i <list.size() ; i++) {
            MyData.DataBean dataBean = list.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                MyData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (spusBean.isChildChecked()){
                    allPrice  = allPrice+spusBean.getPraise_num() * Float.parseFloat(spusBean.getSkus().get(0).getPrice());
                }
            }
        }
        return allPrice;
    }
    public int getAllGoodsNumber(){
        int number = 0;
        for (int i = 0; i <list.size() ; i++) {
            MyData.DataBean dataBean = list.get(i);
            for (int j = 0; j <dataBean.getSpus().size() ; j++) {
                MyData.DataBean.SpusBean spusBean = dataBean.getSpus().get(j);
                if (spusBean.isChildChecked()){
                    number  = number+spusBean.getPraise_num();
                }
            }
        }
        return number;
    }
    public interface AdapterCallBack{
        void setGrouCheck(int groupPosition);
        void setChildCheck(int groupPosition, int childPosition);
        void setNumber(int groupPosition, int childPosition, int number);

    }
    private AdapterCallBack adapterCallBack;
    public void setCallBack(AdapterCallBack adapterCallBack){
        this.adapterCallBack = adapterCallBack;
    }
}
