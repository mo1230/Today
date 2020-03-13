package com.example.today;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.today.base.HistoryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    Context context;
    List<HistoryBean.ResultBean> dataList;
    TextView time, title;
    ImageView imageView;
    LinearLayout linearLayout;

    public HistoryAdapter(Context context, List<HistoryBean.ResultBean> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @Override
    public int getCount() {
        return this.dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null);
        this.time = convertView.findViewById(R.id.time);
        this.title = convertView.findViewById(R.id.title);
        this.imageView = convertView.findViewById(R.id.itemImages);
        this.linearLayout = convertView.findViewById(R.id.item);

        HistoryBean.ResultBean resultBean = this.dataList.get(position);

        if (position != 0){
            HistoryBean.ResultBean lastResult = this.dataList.get(position - 1);
            if (resultBean.getYear() == lastResult.getYear()){
                this.linearLayout.setVisibility(View.GONE);
            }else {
                this.linearLayout.setVisibility(View.VISIBLE);
            }
        }else {
            this.linearLayout.setVisibility(View.VISIBLE);
        }

        this.time.setText(resultBean.getYear()+"-"+resultBean.getMonth()+"-"+resultBean.getDay());
        this.title.setText(resultBean.getTitle());
        String picURL = resultBean.getPic();
        if (TextUtils.isEmpty(picURL)) {
            this.imageView.setVisibility(View.GONE);
        }else {
            this.imageView.setVisibility(View.VISIBLE);
            Picasso.with(context).load(picURL).into(this.imageView);
        }
        return convertView;
    }
}
