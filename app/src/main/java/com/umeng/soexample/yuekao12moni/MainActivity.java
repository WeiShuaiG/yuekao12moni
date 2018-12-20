package com.umeng.soexample.yuekao12moni;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_tiao;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_tiao = findViewById(R.id.btn_tiao);
        img = findViewById(R.id.img_qq);
        ObjectAnimator animator = ObjectAnimator.ofFloat(img,"alpha",1f,0f,0.8f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img,"rotationY",0,180);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(animator1);
        animatorSet.setDuration(3000);
        animatorSet.start();
        btn_tiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tiao:
                Intent intent = new Intent(this,ShowActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }
}
