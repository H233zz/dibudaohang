package com.example.dell.w;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 */

public class Utils {

    public static String loadConnection(String path){
        try {
            URL url = new URL(path);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.connect();
            if (conn.getResponseCode()==200){
                StringBuffer sb = new StringBuffer();
                InputStream in = conn.getInputStream();
                int len=-1;
                byte buffer[]=new byte[1024];
                while ((len=in.read(buffer))!=-1){
                    sb.append(new String(buffer,0,len));
                }
                in.close();
                return  sb.toString();
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
