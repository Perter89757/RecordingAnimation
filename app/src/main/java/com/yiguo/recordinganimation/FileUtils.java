//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yiguo.recordinganimation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.text.DecimalFormat;

public final class FileUtils {
    public static String SDCARD_PATH;
    public static String APP_PATH;
    private static final String TEMP_DIR_NAME = "temp";
    private static final String CACH_DIR_NAME = "cach";
    private static final String LOG_DIR_NAME = "log";
    private static final String CRASH_DIR_NAME = "crash";
    public static File TEMP_DIR = null;
    public static File CACHE_DIR = null;
    public static File LOG_DIR = null;
    public static File CRASH_DIR = null;

    private FileUtils() {
    }

    public static void initFileDir(Context context) {
        if(Environment.getExternalStorageState().equals("mounted")) {
            SDCARD_PATH = Environment.getExternalStorageDirectory().toString();
        } else {
            SDCARD_PATH = context.getCacheDir().toString();
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String appName = (String)context.getPackageManager().getApplicationLabel(applicationInfo);
        APP_PATH = SDCARD_PATH + File.separator + appName;
        TEMP_DIR = new File(APP_PATH + File.separator + "temp" + File.separator);
        TEMP_DIR.mkdirs();
        CACHE_DIR = new File(APP_PATH + File.separator + "cach" + File.separator);
        CACHE_DIR.mkdirs();
        LOG_DIR = new File(APP_PATH + File.separator + "log" + File.separator);
        LOG_DIR.mkdirs();
        CRASH_DIR = new File(APP_PATH + File.separator + "crash" + File.separator);
        CRASH_DIR.mkdirs();
    }

    private static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            File e = new File(folderPath);
            e.delete();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static boolean delAllFile(String path) {
        File file = new File(path);
        if(!file.exists()) {
            return false;
        } else if(!file.isDirectory()) {
            return false;
        } else {
            String[] tempList = file.list();
            File temp = null;

            for(int i = 0; i < tempList.length; ++i) {
                if(path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if(temp.isFile()) {
                    temp.delete();
                }

                if(temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);
                    delFolder(path + "/" + tempList[i]);
                }
            }

            return true;
        }
    }

    public static String getFileSize(long size) {
        DecimalFormat localDecimalFormat = new DecimalFormat("###.00");
        return size < 1024L?size + "bytes":(size < 1048576L?localDecimalFormat.format((double)((float)size / 1024.0F)) + "KB":(size < 1073741824L?localDecimalFormat.format((double)((float)size / 1024.0F / 1024.0F)) + "MB":(size < 0L?localDecimalFormat.format((double)((float)size / 1024.0F / 1024.0F / 1024.0F)) + "GB":"error")));
    }

    public static void openFile(File paramFile, Activity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(268435456);
        localIntent.setAction("android.intent.action.VIEW");
        String str = getMIMEType(paramFile);
        localIntent.setDataAndType(Uri.fromFile(paramFile), str);

        try {
            activity.startActivity(localIntent);
        } catch (Exception var5) {
            var5.printStackTrace();
          //  ToastUtils.Toast(activity, "没有找到打开此类文件的程序");
        }

    }

    private static String getMIMEType(File paramFile) {
        String str1 = "";
        String str2 = paramFile.getName();
        String str3 = str2.substring(str2.lastIndexOf(".") + 1, str2.length()).toLowerCase();
        str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str3);
        return str1;
    }
}
