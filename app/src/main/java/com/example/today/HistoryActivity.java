package com.example.today;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.today.base.BaseActivity;
import com.example.today.base.HistoryBean;
import com.example.today.base.TodaysUrl;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class HistoryActivity extends AppCompatActivity {
    TextView title, content;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.title = findViewById(R.id.contentTitle);
        this.content = findViewById(R.id.contents);
        this.image = findViewById(R.id.contentImage);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Log.d("id", id);
        String url = TodaysUrl.getTodayUrlById("1.0", id);
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                HistoryBean historyBean = new Gson().fromJson(result, HistoryBean.class);
                HistoryBean.ResultBean resultBean = historyBean.getResult().get(0);
                HistoryActivity.this.title.setText(resultBean.getTitle());
                HistoryActivity.this.content.setText(resultBean.getDes());
                String picUrl = resultBean.getPic();
                if (!picUrl.isEmpty()){
                    HistoryActivity.this.image.setVisibility(View.VISIBLE);
                    Picasso.with(HistoryActivity.this).load(picUrl).into(HistoryActivity.this.image);
                }else {
                    HistoryActivity.this.image.setVisibility(View.GONE);
                }
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
}

