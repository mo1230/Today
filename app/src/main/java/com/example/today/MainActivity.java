package com.example.today;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.today.base.BaseActivity;
import com.example.today.base.HistoryBean;
import com.example.today.base.LaoHuangLiBean;
import com.example.today.base.TodaysUrl;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 添加碎片
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragments, new HomeContent());
        fragmentTransaction.commit();

        // 抽屉式导航栏
        this.navigationView = findViewById(R.id.nav);
        this.navigationView.setCheckedItem(R.id.hoem);
        this.drawerLayout = findViewById(R.id.drawer);
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){
                    case R.id.hoem:

                        fragmentTransaction.replace(R.id.fragments, new HomeContent());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        MainActivity.this.drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.setting:
//                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        fragmentTransaction.replace(R.id.fragments, new Setting());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                        menuItem.setCheckable(true);

                        MainActivity.this.drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.quit:
                        MainActivity.this.finish();
                        break;
                }
                return true;
            }
        });

        // 工具栏
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置工具栏左边的按钮

        actionBar.setHomeAsUpIndicator(R.drawable.menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.drawerLayout.openDrawer(GravityCompat.START);
                break;

        }
        return true;
    }
}
