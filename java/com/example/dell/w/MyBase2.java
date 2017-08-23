package com.example.dell.w;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 *
 */

public class MyBase2 extends BaseAdapter {
    private List<TwoData.DataBeanXX.ListBean.DataBean> list;
    private Context context;
    private static final int IMAGE = 0;
    private static final int TEXT = 1;
    public MyBase2(List<TwoData.DataBeanXX.ListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public TwoData.DataBeanXX.ListBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageHolder imageHolder=null;
        TextHolder textHolder = null;
        int type = getItemViewType(position);
        if (convertView==null){
            if (type==IMAGE){
                imageHolder=new ImageHolder();
                convertView=View.inflate(context,R.layout.one1,null);
                imageHolder.imageView=(ImageView)convertView.findViewById(R.id.one1image);
                imageHolder.textView=(TextView)convertView.findViewById(R.id.one1name);
                convertView.setTag(imageHolder);
            }else{
                textHolder = new TextHolder();
                convertView=View.inflate(context,R.layout.two2,null);
                textHolder.textView=(TextView)convertView.findViewById(R.id.two1name);
                convertView.setTag(textHolder);
            }
        }else{
            if (type==IMAGE){
                imageHolder=(ImageHolder) convertView.getTag();
            }else{
                textHolder=(TextHolder) convertView.getTag();
            }
        }
        if (type==IMAGE){
            ImageLoader.getInstance().displayImage(list.get(position).getPhoto(),imageHolder.imageView);
            imageHolder.textView.setText(list.get(position).getTitle());
        }else{
            textHolder.textView.setText(list.get(position).getSummary());
        }
        return convertView;
    }
    class ImageHolder{
        ImageView imageView;
        TextView textView;
    }
    class TextHolder{
        TextView textView;
    }
    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return IMAGE;
        }else{
            return TEXT;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
