package com.yiguo.recordinganimation.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.View.adapter.FixLayoutAdapter;
import com.yiguo.recordinganimation.View.adapter.FloatLayoutAdapter;
import com.yiguo.recordinganimation.View.adapter.GridHelperAdapter;
import com.yiguo.recordinganimation.View.adapter.LinearAdapter;
import com.yiguo.recordinganimation.View.adapter.OneToNAdapter;
import com.yiguo.recordinganimation.View.adapter.SingleLayoutAdapter;
import com.yiguo.recordinganimation.View.adapter.StaggeredGridLayoutAdapter;
import com.yiguo.recordinganimation.View.adapter.StickyLayoutAdapter;

import java.util.ArrayList;

public class VLayoutActivity extends AppCompatActivity {

    private RecyclerView rvList;

    private ArrayList<String> lists = new ArrayList<>();
    private ArrayList<Integer> imgSrc = new ArrayList<>();
    private ArrayList<Integer> goodSrc = new ArrayList<>();
    private ArrayList<Integer> stagSrc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);
        rvList = (RecyclerView) findViewById(R.id.rv_home);
        initLinearData();
        //1设置回收复用池大小
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        //2初始化LayoutManager
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        //3加载数据
        DelegateAdapter adapters = new DelegateAdapter(layoutManager, true);

        //栏格布局，都布局在一排
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
       // columnLayoutHelper.
        adapters.addAdapter(new FixLayoutAdapter(this,columnLayoutHelper));

        //floatLayoutHelper 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置
        FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
        layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
        layoutHelper.setDefaultLocation(100, 400);
        adapters.addAdapter(new FloatLayoutAdapter(this, layoutHelper));


        //singleHelper 只会显示一个组件View 自定义Adapter 可以用作轮播图
        SingleLayoutHelper singHelper = new SingleLayoutHelper();
        singHelper.setBgColor(R.color.colorPrimary);
        singHelper.setMargin(5, 0, 5, 5);
        adapters.addAdapter(new SingleLayoutAdapter(this, singHelper));

        //模拟数据加载
        initGridData();
        // 进行Grid布局
        GridLayoutHelper gridHelper = new GridLayoutHelper(5);
        gridHelper.setMarginTop(30);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        gridHelper.setVGap(5);
        //设置水平方向条目的间隔
        gridHelper.setHGap(5);
        gridHelper.setMarginLeft(30);
        gridHelper.setMarginBottom(30);
         //自动填充满布局
        gridHelper.setAutoExpand(true);
        adapters.addAdapter(new GridHelperAdapter(imgSrc, gridHelper));


        //吸顶的Helper  固定
        StickyLayoutHelper stickyHelper = new StickyLayoutHelper();
        adapters.addAdapter(new StickyLayoutAdapter(stickyHelper));


        initOnePlusData();
        //onePlusNHelper 一拖N布局，可以配置1-5个子元素
        OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
        helper.setBgColor(R.color.colorPrimary);
        helper.setPadding(5, 5, 5, 5);
        helper.setColWeights(new float[]{50f});

        helper.setMargin(10, 20, 10, 10);
        adapters.addAdapter(new OneToNAdapter(goodSrc.subList(0, 2), helper));


        OnePlusNLayoutHelper helper2 = new OnePlusNLayoutHelper();
        helper2.setBgColor(R.color.colorPrimary);
        helper2.setPadding(5, 5, 5, 5);
        helper2.setColWeights(new float[]{65f, 35f});

        helper.setMargin(10, 20, 10, 10);
        adapters.addAdapter(new OneToNAdapter(goodSrc.subList(2, 4), helper2));


        OnePlusNLayoutHelper helper3 = new OnePlusNLayoutHelper();
        helper3.setBgColor(0xffef8ba3);
        helper3.setAspectRatio(2.0f);
        helper3.setColWeights(new float[]{40f});
        helper3.setRowWeight(30f);
        helper3.setMargin(10, 20, 10, 20);
        helper3.setPadding(10, 10, 10, 10);
        adapters.addAdapter(new OneToNAdapter(goodSrc.subList(4, 9), helper3));


        //Linear 布局
        LinearLayoutHelper linearHelper = new LinearLayoutHelper(10);
        adapters.addAdapter(new LinearAdapter(this, lists, linearHelper));


        //StaggerGridLayoutHelper
        initStagData();
        StaggeredGridLayoutHelper stagHelp = new StaggeredGridLayoutHelper(2);
        stagHelp.setHGap(5);
        stagHelp.setVGap(5);
        adapters.addAdapter(new StaggeredGridLayoutAdapter(this, stagSrc, stagHelp));


        rvList.setAdapter(adapters);

    }

    private void initStagData() {
        stagSrc.add(R.mipmap.g1);
        stagSrc.add(R.mipmap.zl);
        stagSrc.add(R.mipmap.ic);
        stagSrc.add(R.mipmap.g4);
        stagSrc.add(R.mipmap.g5);
    }

    private void initOnePlusData() {
        goodSrc.add(R.mipmap.img1);
        goodSrc.add(R.mipmap.img2);
        goodSrc.add(R.mipmap.tow1);
        goodSrc.add(R.mipmap.two2);
        goodSrc.add(R.mipmap.g1);
        goodSrc.add(R.mipmap.g2);
        goodSrc.add(R.mipmap.g3);
        goodSrc.add(R.mipmap.g4);
        goodSrc.add(R.mipmap.g5);
    }

    private void initGridData() {
        imgSrc.add(R.mipmap.i1);
        imgSrc.add(R.mipmap.i2);
        imgSrc.add(R.mipmap.i3);
        imgSrc.add(R.mipmap.i4);
        imgSrc.add(R.mipmap.i5);
        imgSrc.add(R.mipmap.i6);
        imgSrc.add(R.mipmap.i7);
        imgSrc.add(R.mipmap.i8);
        imgSrc.add(R.mipmap.i9);
        imgSrc.add(R.mipmap.i10);
    }

    private void initLinearData() {
        for (int i = 0; i < 18; i++) {
            lists.add(" LinearHelper :" + i);
        }
    }
}
