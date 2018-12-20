package com.umeng.soexample.yuekao12moni;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by W on 2018/12/18.
 */

public class JiajianView extends LinearLayout implements View.OnClickListener {
    private TextView mAdd;
    private TextView mDelete;
    private TextView mNumber;
    private int mCount;
    public JiajianView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.jia_jian_layout, this);
        mAdd = findViewById(R.id.txt_jia);
        mDelete = findViewById(R.id.txt_jian);
        mNumber = findViewById(R.id.goods_num);
        mAdd.setOnClickListener(this);
        mDelete.setOnClickListener(this);
    }
    public void setNumber(int number){
        this.mCount = number;
        mNumber.setText(number+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_jia:
                mCount++;
                mNumber.setText(mCount+"");
                if (monCountChange!=null){
                    monCountChange.setCount(mCount);
                }
            break;
            case R.id.txt_jian:
                if (mCount>0){
                    mCount--;
                    mNumber.setText(mNumber+"");
                    if (monCountChange!=null){
                        monCountChange.setCount(mCount);
                    }else {
                        Toast.makeText(getContext(), "商品已售空", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
    public interface onCountChange{
        void setCount(int count);
    }
    private onCountChange monCountChange;
    public void setonChange(onCountChange onCountChange){
        this.monCountChange = onCountChange;
    }
}
