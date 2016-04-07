package com.example.admin.as_first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.admin.as_first.coustom.ExpandableLayout;

public class ExpandlayoutActivity extends AppCompatActivity {

    private LinearLayout layoutl;
    private ExpandableLayout layout;
    private View headView,contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlayout);
        layoutl=(LinearLayout)this.findViewById(R.id.layout1);
        layout=(ExpandableLayout)this.findViewById(R.id.expand_layout);
        //headView=layout.getHeaderLayout();//获取头布局FrameLayout
       // contentView=layout.getContentLayout();//获取内容布局FrameLayout

        boolean state=layout.isOpened();//是不是展开状态
        layout.show();//展开内容布局
        state=layout.isOpened();//是不是展开状态
        layout.hide();//隐藏内容布局


    }
    public void showView(View view)
    {
        if(layoutl.getVisibility()==View.GONE) {
            layoutl.setAnimation(AnimationUtils.loadAnimation(ExpandlayoutActivity.this, R.anim.expandable_show));
            layoutl.setVisibility(View.VISIBLE);
        }else{
            layoutl.setAnimation(AnimationUtils.loadAnimation(ExpandlayoutActivity.this, R.anim.expandable_hide));
            layoutl.setVisibility(View.GONE);
         }
    }
}
