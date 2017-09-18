package com.jiyun.asus.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jiyun.asus.rxjavademo.util.App;
import com.jiyun.asus.rxjavademo.util.FANGfa;
import com.jiyun.asus.rxjavademo.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Bean.DataBean> listdata =new ArrayList<>();
    private String url ="http://mobile.hmeili.com/yunifang/mobile/goods/getall";
    private String ss;
    private Bean mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.actiyity = this;
        initView();



        VolleyUtil.getInstance(this).Get(url, new FANGfa() {
            @Override
            public void success(String s) {

              ss=s.toString();
                Gson gson =new Gson();
                mess =gson.fromJson(ss,Bean.class);

                //1) 创建 Observer   //观察者
                Subscriber  subscriber = new Subscriber<String>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {


                        listdata.addAll(mess.getData());
                        adapter ada =new adapter(listdata,MainActivity.this);

                        lv.setAdapter(ada);
                        ada.notifyDataSetChanged();
                    }

                };

                //2) 创建 Observable     Observable 即被观察者

                Observable observable =Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {


                        subscriber.onNext(ss);



                        subscriber.onCompleted();
                    }

                });

                observable.subscribe(subscriber);


            }

            @Override
            public void fail(String s) {

            }



            //订阅

        });




        //

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {



            }
        });








    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }
}
