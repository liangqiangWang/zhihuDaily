package com.example.ll.zhihudaily.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.ll.zhihudaily.R;
import com.example.ll.zhihudaily.Util.Kanner;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_content;
    private DrawerLayout mDrawerLayout;
    private SwipeRefreshLayout sr;
    private long firstTime;
    private Toolbar toolbar;
    private Kanner kanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        assert sr != null;
        sr.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sr.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        sr.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();

        kanner=(Kanner)findViewById(R.id.kan);

        int[] imageRes={
                R.drawable.index1, R.drawable.index2,R.drawable.index3,
                R.drawable.index4,R.drawable.index5
        };
        kanner.setImagesRes(imageRes);




    }






    public void closeMenu() {
        mDrawerLayout.closeDrawers();
    }

    public void setSwipeRefreshEnable(boolean enable) {
        sr.setEnabled(enable);
    }

    public void setToolbarTitle(String text) {
        toolbar.setTitle(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(0).setTitle("切换主题");
        return true;
    }

    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            closeMenu();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Snackbar sb = Snackbar.make(fl_content, "再按一次退出", Snackbar.LENGTH_SHORT);
                sb.getView().setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
                sb.show();
                firstTime = secondTime;
            } else {
                finish();
            }
        }

    }


    private void setStatusBarColor(int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // If both system bars are black, we can remove these from our layout,
            // removing or shrinking the SurfaceFlinger overlay required for our views.
            Window window = this.getWindow();
            if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            window.setStatusBarColor(statusBarColor);
        }
    }




}
