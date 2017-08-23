package com.example.dell.w;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 *
 */

public class MyBase extends BaseAdapter{
    List<Data.DataBean.ReturnDataBean.ComicsBean> list;
    Context context;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    public MyBase(List<Data.DataBean.ReturnDataBean.ComicsBean> list, Context context) {
        this.list = list;
        this.context = context;
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);

        //将configuration配置到imageloader中
        imageLoader= ImageLoader.getInstance();
        imageLoader.init(configuration);

        options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .build();
    }

    int COUNT = 2;
    int YOU=0;
    int NONE = 1;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Data.DataBean.ReturnDataBean.ComicsBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        YoutuHolder youtuHolder = null;
        MeituHolder meituHolder = null;
        int type = getItemViewType(position);
        if (convertView==null){
            if (type==YOU){
                youtuHolder = new YoutuHolder();
                convertView=View.inflate(context,R.layout.one,null);
                youtuHolder.textname=(TextView)convertView.findViewById(R.id.onename);
                youtuHolder.textdesc=(TextView)convertView.findViewById(R.id.onedesc);
                youtuHolder.imageView=(ImageView)convertView.findViewById(R.id.oneimage);
                convertView.setTag(youtuHolder);
            }else if(type==NONE){
                meituHolder = new MeituHolder();
                convertView=View.inflate(context,R.layout.two,null);
                meituHolder.textdesc=(TextView)convertView.findViewById(R.id.twodesn);
                meituHolder.textname=(TextView)convertView.findViewById(R.id.twoname);
                convertView.setTag(meituHolder);
            }
        }else{
            if (type==YOU){
                youtuHolder=(YoutuHolder) convertView.getTag();
            }else if(type==NONE){
                meituHolder=(MeituHolder)convertView.getTag();
            }
        }
        Data.DataBean.ReturnDataBean.ComicsBean bean = list.get(position);
        if (type==YOU){
            youtuHolder.textdesc.setText("简介："+bean.getDescription());
            youtuHolder.textname.setText("名称："+bean.getName());
            ImageLoader.getInstance().displayImage(bean.getCover(),youtuHolder.imageView);
        }else if(type==NONE){
            meituHolder.textdesc.setText("简介："+bean.getDescription());
            meituHolder.textname.setText("名称："+bean.getName());
        }
        return convertView;
    }
    class YoutuHolder{
        TextView textname,textdesc;
        ImageView imageView;
    }
    class MeituHolder{
        TextView textname,textdesc;
    }
    @Override
    public int getItemViewType(int position) {
        Data.DataBean.ReturnDataBean.ComicsBean bean = getItem(position);
        if (bean.getFlag()==3){
            return YOU;
        }else{
            return NONE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return COUNT;
    }
}
