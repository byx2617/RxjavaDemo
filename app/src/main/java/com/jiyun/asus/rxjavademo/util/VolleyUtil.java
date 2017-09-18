package com.jiyun.asus.rxjavademo.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class VolleyUtil implements VolleyCallback{
    private static VolleyUtil volleyUtil;
    private final RequestQueue queue;

    private VolleyUtil(Context   context){
        queue = Volley.newRequestQueue(context);
    }
    public static synchronized VolleyUtil getInstance(Context context){
        if(volleyUtil==null){
            volleyUtil=new VolleyUtil(context);
        }
        return volleyUtil;
    }

    @Override
    public void Get(String url, final FANGfa fanGfa) {

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                App.actiyity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fanGfa.success(response);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                App.actiyity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fanGfa.fail("错误信息"+error);
                    }
                });

            }
        });
        queue.add(request);
    }
}
