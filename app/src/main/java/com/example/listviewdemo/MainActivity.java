package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.listviewdemo.R.drawable.ic_launcher_background;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView testList;
    private Button button;
    private List<String> testData=new ArrayList<>();
    private ViewHolderAdapter viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testList=findViewById(R.id.lv_content);
        button=findViewById(R.id.btn_test);

        button.setOnClickListener(this);
        for (int i = 0; i < 20; i++) {
            testData.add("" + i);
        }
        //设置条目之间的分割线
//        testList.setDivider(new ColorDrawable(this.getResources().getColor(R.color.colorPrimaryDark)));
        testList.setDivider(null);//设置为null时表示无分割线
        //设置条目之间分割线的宽度
        testList.setDividerHeight(20);
        testList.setEmptyView(findViewById(R.id.iv_test));//当数据源无数据时显示的图片
//        testList.setSelection(5);//设置默认从第几项开始显示，（未实现）
//        testList.setSelector(ic_launcher_background);//设置点击效果
        viewHolder=new ViewHolderAdapter(this,testData);
        testList.setAdapter(viewHolder);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_test:
                int i=testData.size();
                testData.remove(--i  );
                viewHolder.notifyDataSetChanged();//在listView中新增数据
                break;
        }
    }

    private class ViewHolderAdapter extends BaseAdapter{

        private List<String> mData;
        private LayoutInflater mInflater;

        public ViewHolderAdapter(Context context,List<String> data) {
            this.mData=data;
            mInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int i) {
            return mData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder=null;
            //判断是否存在缓存
            if(view==  null)    {
                holder=new ViewHolder();
                view=mInflater.inflate(R.layout.item,null  );
                holder.imageView=view.findViewById(R.id.iv_icon);
                holder.textView=view.findViewById(R.id.tv_content);
                view.setTag(holder);
            }else{//通过tag找到了缓存的布局
                holder=(ViewHolder) view.getTag();
            }
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
            holder.textView.setText(mData.get(i));

            return view;
        }


        private  class ViewHolder{
            private ImageView imageView;
            private TextView textView;
        }
    }


}