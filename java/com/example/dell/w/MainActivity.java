package com.example.dell.w;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {
    private BottomTabBar bottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomTabBar=(BottomTabBar)findViewById(R.id.bottom_tab_bar);
        if (isNet()){
            Toast.makeText(MainActivity.this,"有网", Toast.LENGTH_SHORT).show();
            bottomTabBar.init(getSupportFragmentManager())
                    .addTabItem("主页",R.drawable.home_fill,R.drawable.home, Fragment01.class)
                    .addTabItem("喜欢",R.drawable.like_fill,R.drawable.like, Fragment02.class)
                    .addTabItem("位置",R.drawable.location_fill,R.drawable.location, Fragment03.class)
                    .addTabItem("我的",R.drawable.person_fill,R.drawable.person, Fragment04.class)
                    .isShowDivider(false);
        }else{
            Toast.makeText(MainActivity.this,"无网", Toast.LENGTH_SHORT).show();
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("提示如下")
                    .setMessage("是否进行网络设置")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(
                            Settings.ACTION_WIRELESS_SETTINGS));
                }
            })
                    .create();
            dialog.show();
        }
    }

    public boolean isNet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info= manager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();
    }
}
