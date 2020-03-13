package com.example.today;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.today.base.BaseActivity;
import com.example.today.base.HistoryBean;
import com.example.today.base.LaoHuangLiBean;
import com.example.today.base.TodaysUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HomeContent extends BaseActivity implements View.OnClickListener {
    private ListView listView;
    private ImageButton imageButton;
    private Calendar calendar;
    private Date date;
    private HistoryAdapter historyAdapter;
    private List<HistoryBean.ResultBean> dataList;
    private TextView yangli, day, week, nongli, baiji, wuxing, chongsha, jishen, xiongshen, yi, ji;
    private TextView more;
    private HistoryBean historyBean;
    private int add = 0;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_home_content, container, false);
        return this.view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 实例化属性
        this.imageButton = this.view.findViewById(R.id.main_imgbtn);
        this.listView = this.view.findViewById(R.id.lv);
        this.imageButton.setOnClickListener(this);

        this.dataList = new ArrayList<>();
        this.historyAdapter = new HistoryAdapter(getContext(), this.dataList);
        this.listView.setAdapter(this.historyAdapter);

        this.calendar = Calendar.getInstance();
        this.date = new Date();
        this.calendar.setTime(this.date);

        int month = this.calendar.get(Calendar.MONTH) + 1;
        int day = this.calendar.get(Calendar.DAY_OF_MONTH );

        String todayUrl = TodaysUrl.getTodayUrl("1.0", month, day);
        this.loadData(todayUrl);
        initHeaderAndFooter();      // 加载头布局和尾布局

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                HistoryBean.ResultBean resultBean = HomeContent.this.dataList.get(position - 1);
                String historyId = resultBean.get_id();
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                intent.putExtra("id", historyId);
                startActivity(intent);
            }
        });
    }

    private void initHeaderAndFooter() {
        // 加载头布局和尾布局
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.main_header_view, null);
        View footerView = LayoutInflater.from(getContext()).inflate(R.layout.main_footer, null);

        this.yangli = headerView.findViewById(R.id.yangli);
        this.day = headerView.findViewById(R.id.day);
        this.nongli = headerView.findViewById(R.id.nongli);
        this.baiji = headerView.findViewById(R.id.baiji);
        this.chongsha = headerView.findViewById(R.id.chongsha);
        this.jishen = headerView.findViewById(R.id.jishen);
        this.yi = headerView.findViewById(R.id.yi);
        this.ji = headerView.findViewById(R.id.ji);
        this.week = headerView.findViewById(R.id.week);
        this.wuxing = headerView.findViewById(R.id.wuxing);
        this.xiongshen = headerView.findViewById(R.id.xiongshen);

        this.more = footerView.findViewById(R.id.more);
        this.more.setTag("footer");
        this.more.setOnClickListener(this);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDay = simpleDateFormat.format(this.date);
        String laohuangliUrl = TodaysUrl.getLaoHuangLiUrl(strDay);
        loadHeaderData(laohuangliUrl);       // 加载头布局的数据
        this.listView.addHeaderView(headerView, null, false);

        this.listView.addFooterView(footerView);
    }

    private void loadHeaderData(String laohuangliUrl) {
        //加载头布局的数据
        RequestParams requestParams = new RequestParams(laohuangliUrl);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LaoHuangLiBean laoHuangLiBean = new Gson().fromJson(result, LaoHuangLiBean.class);
                LaoHuangLiBean.ResultBean laohuangliData = laoHuangLiBean.getResult();  // 老黄历的内容

                String[] yangliArr = laohuangliData.getYangli().split("-");
                String week = getWeek(Integer.parseInt(yangliArr[0]), Integer.parseInt(yangliArr[1]), Integer.parseInt(yangliArr[2]));
                HomeContent.this.yangli.setText(laohuangliData.getYangli() + " " + week);
                HomeContent.this.day.setText(yangliArr[2]);
                HomeContent.this.week.setText(week);
                HomeContent.this.baiji.setText("彭祖百忌：" + laohuangliData.getBaiji());
                HomeContent.this.wuxing.setText("五行：" + laohuangliData.getWuxing());
                HomeContent.this.chongsha.setText("冲煞：" + laohuangliData.getChongsha());
                HomeContent.this.jishen.setText("吉神宜驱：" + laohuangliData.getJishen());
                HomeContent.this.xiongshen.setText("凶神宜忌：" + laohuangliData.getXiongshen());
                HomeContent.this.yi.setText("宜：" + laohuangliData.getYi());
                HomeContent.this.ji.setText("忌：" + laohuangliData.getJi());
                HomeContent.this.nongli.setText("农历" + laohuangliData.getYinli());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private String getWeek(int year, int month, int day) {
//        根据年月日获取对应的星期
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month-1,day);
        String weeks[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int index = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (index<0){
            index = 0;
        }
        return weeks[index];
    }

    @Override
    public void onSuccess(String result) {
        this.dataList.clear();
        this.historyBean = new Gson().fromJson(result, HistoryBean.class);
        List<HistoryBean.ResultBean> list = historyBean.getResult();

        for (int i = 0; i < 5; i++) {
            this.dataList.add(list.get(i));
//            Log.d("data", this.dataList.get(i).getTitle());
        }
        this.historyAdapter.notifyDataSetChanged();         // 通知适配器更新
    }

    @Override
    public void onClick(View v) {
//        Log.d("tag", "点击加载更多");
        if (v.getId() == R.id.main_imgbtn){
            popCalendarDialog();
            return;
        }

        String tag = (String) v.getTag();
        Log.d("tag", tag);
        if (tag.equals("footer")){
//            Log.d("tag", "点击加载更多");
            this.appendData();
        }
    }

    private void appendData() {
        List<HistoryBean.ResultBean> list = this.historyBean.getResult();
        for (int i = 5 + this.add; i<10 + this.add; i++){
//            Log.d("tag", Integer.toString(i));
            if (i < list.size()) {
                this.dataList.add(i, list.get(i));
                Log.d("tag", Integer.toString(i));
            }else {
                Toast.makeText(getContext(),"已经是全部了哦~",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        this.add = this.add + 5;
        this.historyAdapter.notifyDataSetChanged();

    }

    private void popCalendarDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //                改变老黄历显示的内容
                String time = year + "-" + (month + 1) + "-" + dayOfMonth;
                String laohuangliURL = TodaysUrl.getLaoHuangLiUrl(time);
                loadHeaderData(laohuangliURL);

//                改变历史上的今天数据
                String todayHistoryURL = TodaysUrl.getTodayUrl("1.0", (month + 1), dayOfMonth);
                loadData(todayHistoryURL);
//                MainActivity.this.historyAdapter.notifyDataSetChanged();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
