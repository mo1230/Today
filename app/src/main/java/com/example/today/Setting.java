package com.example.today;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Setting extends Fragment {
    private View view;
    private ListView listView;              // 列表
    private SimpleAdapter simpleAdapter;    // 适配器
    private ArrayAdapter<String> arrayAdapter;
    private String[] data;          // 数据源

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_setting, container, false);
        return this.view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listView = this.view.findViewById(R.id.listViewSetting);
        this.data = new String[]{
        "关于", "个人设置", "退出"
        };
        // this.simpleAdapter = new SimpleAdapter(getContext(), this.listData, android.R.layout.simple_list_item_1);
        this.arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, this.data);
        this.listView.setAdapter(this.arrayAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(getContext(), "历史上的今天", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(), "个人设置", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        // 关闭activity
                        getActivity().finish();
                        break;
                }
            }
        });
    }
}
