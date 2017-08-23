package com.example.dell.w;

import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;

/***
 *
 */

public class Fragment01 extends Fragment implements XListView.IXListViewListener{
    private XListView listView;
    int page = 1;
    MyBase adapter;
    private List<Data.DataBean.ReturnDataBean.ComicsBean> list;
    private String URL="http://app.u17.com/v3/appV3_3/android/phone/list/commonComicList?argValue=23&argName=sort&argCon=0&android_id=4058040115108878&v=3330110&model=GT-P5210&come_from=Tg002%20HTTP/1.1&page=";
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01,container,false);
        listView=(XListView) view.findViewById(R.id.listview);
        listView.setPullLoadEnable(true);
        listView.setXListViewListener(this);
        list=new ArrayList<>();
        loadTask(URL+page);
        adapter = new MyBase(list,getActivity());
        listView.setAdapter(adapter);
        return view;
    }

    private void loadTask(String url) {
        GetTask getTask = new GetTask();
        getTask.execute(url);
    }


    class GetTask extends AsyncTask<String,Integer,Data>{
        @Override
        protected Data doInBackground(String... params) {
            return loadData(params[0]);
        }
        public Data loadData(String url) {
            String result = Utils.loadConnection(url);
            Gson gson = new Gson();
            Data data = gson.fromJson(result, Data.class);
            return data;
        }

        @Override
        protected void onPostExecute(Data data) {
            super.onPostExecute(data);
            list.addAll(data.getData().getReturnData().getComics());
            listView.stopRefresh();
            listView.stopLoadMore();
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onRefresh() {
        page=1;
        loadTask(URL+page);
    }

    @Override
    public void onLoadMore() {
        page++;
        loadTask(URL+page);
    }
}
