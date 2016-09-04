package com.example.ll.zhihudaily.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * Created by Administrator on 2016/9/3.
 *
 */
public class HttpUtils {
    private static AsyncHttpClient asClient=new AsyncHttpClient();

    public static void get(String url, ResponseHandlerInterface responseHandler){
        asClient.get(Constant.BASEURL + url, responseHandler);
    }

    public static void getImage(String url,ResponseHandlerInterface responseHandler){
        asClient.get(url,responseHandler);

    }
    public static boolean isNetworkConnected(Context context){
        if(context!=null) {
            ConnectivityManager mConnectivityManager=(ConnectivityManager)context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=mConnectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }


}
