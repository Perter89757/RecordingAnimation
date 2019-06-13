package com.yiguo.recordinganimation.UI;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.UI.adapter.SortAdapter;
import com.yiguo.recordinganimation.UI.bean.SortBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private RecyclerView rcView;
    private FrameLayout frameLayout;
    private SortBean mSortBean;
    private SortDetailFragment mSortDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();
        initData();
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        rcView = findViewById(R.id.rc_view);
        frameLayout = findViewById(R.id.lin_fragment);
        //把二级目录页面添加到右侧
        addFragment();
    }

    private void addFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mSortDetailFragment = new SortDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("right", mSortBean.getCategoryOneArray());
        mSortDetailFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.lin_fragment, mSortDetailFragment);
        fragmentTransaction.commit();
    }

    private void initData() {
        //把本地json文件转成String
        //获取asset目录下的资源文件
        String assetsData = getAssetsData("sort.json");
        Gson gson = new Gson();
        mSortBean = gson.fromJson(assetsData, SortBean.class);

        //获取左侧一级目录数据
        List<SortBean.CategoryOneArrayBean> categoryOneArray = mSortBean.getCategoryOneArray();
        List<String> list = new ArrayList<>();
        //初始化左侧列表数据
        for (int i = 0; i < categoryOneArray.size(); i++) {
            list.add(categoryOneArray.get(i).getName());
        }
        SortAdapter sortAdapter = new SortAdapter(R.layout.item_sort_list,list);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(sortAdapter);

    }

    //从资源文件中获取分类json
    private String getAssetsData(String path) {
        String result = "";
        try {
            //获取输入流
            InputStream mAssets = getAssets().open(path);
            //获取文件的字节数
            int lenght = mAssets.available();//获取文件的总大小
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("fuck", e.getMessage());
            return result;
        }
    }
}
