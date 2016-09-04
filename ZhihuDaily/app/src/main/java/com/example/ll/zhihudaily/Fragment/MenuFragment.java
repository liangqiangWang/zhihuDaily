package com.example.ll.zhihudaily.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ll.zhihudaily.R;

/**

 */
public class MenuFragment extends BaseFragment {
    private TextView tv_download,tv_main,tv_login,tv_backup;
    private ListView lv_item;
    private LinearLayout ll_menu;
    private Handler handler=new Handler();


    private static String[] ITEMS = { "热门新闻","日常心理学", "用户推荐日报", "电影日报", "旅行日报","不许无聊",
     "设计日报", "大公司日报", "财经日报", "互联网安全", "开始游戏", "音乐日报", "动漫日报", "体育日报" };

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);
        ll_menu = (LinearLayout) view.findViewById(R.id.ll_menu);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_backup = (TextView) view.findViewById(R.id.tv_backup);
        tv_download = (TextView) view.findViewById(R.id.tv_download);
        tv_main = (TextView) view.findViewById(R.id.tv_main);
        lv_item = (ListView) view.findViewById(R.id.lv_item);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        ArrayAdapter<String> newsTypeAdapter=new ArrayAdapter<String>(getContext(),R.layout.menu_item,ITEMS);
        lv_item.setAdapter(newsTypeAdapter);

    }



}
