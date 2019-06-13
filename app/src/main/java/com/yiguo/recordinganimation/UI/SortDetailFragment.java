package com.yiguo.recordinganimation.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.UI.adapter.ClassifyDetailAdapter;
import com.yiguo.recordinganimation.UI.bean.RightBean;
import com.yiguo.recordinganimation.UI.bean.SortBean;

import java.util.ArrayList;
import java.util.List;

public class SortDetailFragment extends Fragment {
    private List<RightBean> mDatas = new ArrayList<>();
    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort_detail, container, false);
        mRv = view.findViewById(R.id.rv);
        initData();
        return view;
    }
    private void initData() {
        ArrayList<SortBean.CategoryOneArrayBean> rightList = getArguments().getParcelableArrayList("right");
        for (int i = 0; i < rightList.size(); i++) {
            RightBean head = new RightBean(rightList.get(i).getName());
            //头部设置为true
            head.setTitle(true);
            head.setTitleName(rightList.get(i).getName());
            head.setTag(String.valueOf(i));
            mDatas.add(head);
            List<SortBean.CategoryOneArrayBean.CategoryTwoArrayBean> categoryTwoArray = rightList.get(i).getCategoryTwoArray();
            for (int j = 0; j < categoryTwoArray.size(); j++) {
                RightBean body = new RightBean(categoryTwoArray.get(j).getName());
                body.setTag(String.valueOf(i));
                String name = rightList.get(i).getName();
                body.setTitleName(name);
                mDatas.add(body);
            }
        }
        //多ItemType
        ClassifyDetailAdapter adapter = new ClassifyDetailAdapter();
    }

}
