package com.example.today.base;

import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class BaseActivity extends Fragment implements Callback.CommonCallback<String> {
    public void loadData(String url){
        RequestParams requestParams = new RequestParams(url);   // 请求参数
        x.http().get(requestParams, this);              // get方式
    }
    @Override
    public void onSuccess(String result) {

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
}
