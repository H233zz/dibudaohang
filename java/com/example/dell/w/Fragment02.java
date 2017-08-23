package com.example.dell.w;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/**
 *
 */

public class Fragment02 extends Fragment {
    private Banner banner ;
    private ListView listView;
    MyBase2 adapter;
    private List<TwoData.DataBeanXX.HeadPicsBean> listimage;
    private List<TwoData.DataBeanXX.ListBean> listtext;
    private List<TwoData.DataBeanXX.ListBean.DataBean> listtext2;
    private List<String> list;
    private String url="http://api.litchi.jstv.com/nav/14639?OrderIndex=0&ver=4.0&client=android&val=0075D4F573A0E22E9C2F95774ED63BE4";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02,container,false);
        banner=(Banner)view.findViewById(R.id.banner);
        listView=(ListView) view.findViewById(R.id.listview2);
        login();
        listimage=new ArrayList<>();
        list=new ArrayList<>();
        listtext=new ArrayList<>();
        listtext2=new ArrayList<>();
        GetTask2 getTask2 = new GetTask2();
        getTask2.execute(url);
        return view;
    }

    private void login() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                intent.putExtra("image",listtext.get(position).getData().getPhoto());
                intent.putExtra("text",listtext.get(position).getData().getSummary());
                startActivity(intent);
            }
        });
    }


    class GetTask2 extends AsyncTask<String,Integer,TwoData>{
        @Override
        protected TwoData doInBackground(String... params) {
            return loadData(params[0]);
        }
        public TwoData loadData(String url){
            String result = Utils.loadConnection(url);
            Gson gson = new Gson();
            TwoData twoData = gson.fromJson(result, TwoData.class);
            return twoData;
        }

        @Override
        protected void onPostExecute(TwoData twoData) {
            super.onPostExecute(twoData);
            listimage.addAll(twoData.getData().getHeadPics());
                for (TwoData.DataBeanXX.HeadPicsBean bean : listimage){
                    list.add(bean.getData().getPhoto());
                }
            listtext.addAll(twoData.getData().getList());
            for (TwoData.DataBeanXX.ListBean bean: listtext){
                listtext2.add(bean.getData());
            }
            banner.setImageLoader(new ImageActivity(getActivity()));
            banner.setImages(list);
            banner.setDelayTime(2000);
            banner.start();
            adapter = new MyBase2(listtext2,getActivity());
            listView.setAdapter(adapter);
        }
    }
}
