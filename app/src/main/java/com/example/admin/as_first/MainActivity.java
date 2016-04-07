package com.example.admin.as_first;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PullToRefreshListView listView;
    private MyAdapter adapter;
    private List<String> NameList;
    private CheckBox checkBox;
    private TextView tv_money,tv_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NameList=new ArrayList<>();
        adapter=new MyAdapter(listView);
        checkBox=(CheckBox)this.findViewById(R.id.main_cb);
        tv_money=(TextView)this.findViewById(R.id.main_money);
        tv_pay=(TextView)this.findViewById(R.id.main_pay);
        listView=(PullToRefreshListView)this.findViewById(R.id.listview);
       // listView.setShowIndicator(true);//控制显示下拉刷新，上拉加载的
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        
        //listView.setRefreshing(true);//设置显示刷新

        //listView.getLoadingLayoutProxy()//设置下拉刷新显示的内容或者图片

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
                Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
                String    str    =    formatter.format(curDate);

                refreshView.getLoadingLayoutProxy   ().setLastUpdatedLabel(str);

                initData(false);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                SimpleDateFormat    formatter    =   new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
                Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
                String    str    =    formatter.format(curDate);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(str);

                initData(false);
            }
        });

        listView.setAdapter(adapter);
        initData(true);
    }
    public void toggleState(View view){
        if(checkBox.isChecked()) {
            checkBox.setChecked(false);
        }else{
            checkBox.setChecked(true);
        }
    }
    public void showDetail(View view){
        Toast.makeText(MainActivity.this, "金额是：", Toast.LENGTH_LONG).show();
    }
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    adapter.notifyDataSetChanged();
                   // listView.setRefreshing(false);
                    listView.onRefreshComplete();

                    break;
                case 1:
                    adapter.notifyDataSetChanged();
                    listView.onRefreshComplete();

                    break;
                default:
                    break;
            }
            return false;
        }
    });
    private void initData(boolean check) {
        Message message=Message.obtain();
        message.what=0;
        handler.sendMessageDelayed(message,2000);
        if(check) {
            for (int i = 1; i <= 20; i++) {
                NameList.add("测试数据" + i);
            }
        }else{
        }
    }

    class MyAdapter extends BaseAdapter{
        private PullToRefreshListView listview;
        public MyAdapter(PullToRefreshListView listview)
        {
            this.listview=listview;
        }
        @Override
        public int getCount() {
            return NameList.size();
        }

        @Override
        public Object getItem(int position) {
            return NameList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler hodler;
            if(convertView==null)
            {
                hodler=new ViewHodler();
                convertView=getLayoutInflater().inflate(R.layout.item,null);
                hodler.img=(ImageView)convertView.findViewById(R.id.img);
                hodler.cb=(CheckBox)convertView.findViewById(R.id.cb);
                hodler.tv=(TextView)convertView.findViewById(R.id.tv);
                convertView.setTag(hodler);
            }else
                 hodler=(ViewHodler)convertView.getTag();
            hodler.tv.setText(NameList.get(position));
            return convertView;
        }
        class ViewHodler{
            TextView tv;
            ImageView img;
            CheckBox cb;
        }

    }
}
