package com.yiguo.recordinganimation.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * author: huang_yanhui
 * data:2017/10/23
 * time:15:44
 * emaill:huangyh@thinkive.com
 * description:
 */

public class viewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<FragmentInfo> fragmentInfos;

    public viewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentInfos = new ArrayList<>();
        fragmentInfos.add(new FragmentInfo("one", oneFragment.class));
        fragmentInfos.add(new FragmentInfo("two", twoFragment.class));
        fragmentInfos.add(new FragmentInfo("three", threeFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) fragmentInfos.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentInfos.size();
    }
}
