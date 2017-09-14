package com.yiguo.recordinganimation.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * author: huang_yanhui
 * data:2017/9/8
 * time:10:44
 * emaill:huangyh@thinkive.com
 * description:
 */

public class packageUtils {

    public void 获取订阅的广播(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("com.yiguo.recordinganimation.douban.action.NEW_MESSAGE");
        List<ResolveInfo> resolveInfos = packageManager.queryBroadcastReceivers(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        ResolveInfo resolveInfo = resolveInfos.get(0);
    }
}
