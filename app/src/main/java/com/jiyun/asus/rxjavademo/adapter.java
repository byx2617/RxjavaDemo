package com.jiyun.asus.rxjavademo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapter extends BaseAdapter {

    private List<Bean.DataBean> listdata;
    private Context context;

    public adapter(List<Bean.DataBean> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder ;
            if (view==null){
                holder=new ViewHolder();
                view= LayoutInflater.from(context).inflate(R.layout.my_item,null);

                    holder.imageView=view.findViewById(R.id.img);
                    holder.tvname=view.findViewById(R.id.tv_name);

                view.setTag(holder);
            }else{
                holder= (ViewHolder) view.getTag();
            }

                        holder.tvname.setText(listdata.get(i).getGoods_name());

             Glide.with(context).load(listdata.get(i).getGoods_img()).into(holder.imageView);

        return view;
    }


    private class ViewHolder{
        private ImageView imageView;
        private TextView tvname;
    }
}
