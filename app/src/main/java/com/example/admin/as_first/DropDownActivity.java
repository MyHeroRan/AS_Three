package com.example.admin.as_first;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DropDownActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout layout1,layout2,layout3;
    FrameLayout bg_fragme,con_fragme;
    private List<View> Views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_drop_down);
        initView();
    }
    private void initView()
    {
        Views=new ArrayList<>();
        View view=getLayoutInflater().inflate(R.layout.drop_down1,null);
        Views.add(view);

        View view2=getLayoutInflater().inflate(R.layout.drop_down2,null);
        Views.add(view2);
        View view3=getLayoutInflater().inflate(R.layout.drop_down3,null);
        Views.add(view3);
        layout1=(LinearLayout)this.findViewById(R.id.tv1);
        layout2=(LinearLayout)this.findViewById(R.id.tv2);
        layout3=(LinearLayout)this.findViewById(R.id.tv3);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        bg_fragme=(FrameLayout)this.findViewById(R.id.bg_frame);
        con_fragme=(FrameLayout)this.findViewById(R.id.con_frame);
        bg_fragme.setOnClickListener(this);
        con_fragme.setOnClickListener(this);
        con_fragme.addView(view, 0);
        con_fragme.addView(view2,1);
        con_fragme.addView(view3,2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv1:
                toggleState(1);
                break;
            case R.id.tv2:
                toggleState(2);
                break;
            case R.id.tv3:
                toggleState(3);
                break;
            case R.id.bg_frame:
                toggleState(0);
                break;
            case R.id.con_frame:
                Toast.makeText(DropDownActivity.this,"你点击了内容区域",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
    private void resetState()
    {
        int count=con_fragme.getChildCount();
        for (int i=0;i<count;i++)
        {
            con_fragme.getChildAt(i).setVisibility(View.GONE);
        }
    }
    private void toggleState(int type)
    {
        if(con_fragme.getVisibility()==View.VISIBLE)
        {
            bg_fragme.setVisibility(View.GONE);
            //bg_fragme.setAlpha((float) 0.5);
            con_fragme.setAnimation(AnimationUtils.loadAnimation(DropDownActivity.this, R.anim.slide_out_to_top));
            con_fragme.setVisibility(View.GONE);

        }
        else if(con_fragme.getVisibility()==View.GONE)
        {

            resetState();//把所有的View都设置成gone
            con_fragme.getChildAt(type-1).setVisibility(View.VISIBLE);

            con_fragme.setVisibility(View.VISIBLE);
            con_fragme.setAnimation(AnimationUtils.loadAnimation(DropDownActivity.this, R.anim.slide_in_from_top));
            bg_fragme.setBackgroundColor(Color.parseColor("#7F000000"));
            //bg_fragme.getBackground().setAlpha(180);
            bg_fragme.setVisibility(View.VISIBLE);
        }
    }
}

