package com.example.admin.as_first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    public void showDropDownActivity(View view)
    {
        Intent intent=new Intent(BaseActivity.this,DropDownActivity.class);
        startActivity(intent);
    }
    public void showMainActivity(View view)
    {
        Intent intent=new Intent(BaseActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void showExpandLayout(View view)
    {
        Intent intent=new Intent(BaseActivity.this,ExpandlayoutActivity.class);
        startActivity(intent);
    }
}
