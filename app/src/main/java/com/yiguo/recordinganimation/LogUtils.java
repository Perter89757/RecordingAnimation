//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yiguo.recordinganimation;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogUtils {
    private static String TAG = "unknown";
    public static final int LOG_OFF = 0;
    public static final int LEVEL_ERROR = 1;
    public static final int LEVEL_WARN = 2;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_DEBUG = 4;
    public static boolean log = true;
    public static int logLevel = 4;
    public static boolean LOG_TO_FILE = true;
    private static final String LOG_FILE_PREFIX = "tk_im_sdk_";
    private static final String LOG_FILE_SUFFIX = ".log";
    private static String todayLogFile = "";
    private static SimpleDateFormat sdf;
    private static Writer mWriter;
    private static final String ERROR_TAG = "E";
    private static final String WARN_TAG = "W";
    private static final String INFO_TAG = "I";
    private static final String DEBUG_TAG = "D";

    private LogUtils() {
    }

    public static void init(Context context) {
        try {
            TAG = context.getPackageName();
        } catch (Exception var2) {
            TAG = "unknown";
        }

        sdf = new SimpleDateFormat("[yy-MM-dd hh:mm:ss]: ", Locale.getDefault());
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int level) {
        if(level != 1 && level != 2 && level != 3 && level != 4 && level != 0) {
            throw new IllegalArgumentException("error log level : " + level);
        } else {
            logLevel = level;
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(Throwable throwable) {
        d(TAG, throwable);
    }

    public static void d(String tag, Throwable throwable) {
        d(tag, throwable, (String)null);
    }

    public static void d(String tag, Throwable throwable, String desc) {
        if(throwable != null && logLevel >= 4) {
            String msg = getExceptionInfo(throwable);
            if(desc != null) {
                d(tag, "[DEBUG DESC] " + desc);
            }

            d(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if(msg != null && logLevel >= 4) {
            if(log) {
                Log.d(tag, msg);
            }

            writeLog2SD("D", tag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(Throwable throwable) {
        i(TAG, throwable);
    }

    public static void i(String tag, Throwable throwable) {
        i(tag, throwable, (String)null);
    }

    public static void i(String tag, Throwable throwable, String desc) {
        if(throwable != null && logLevel >= 3) {
            String msg = getExceptionInfo(throwable);
            if(desc != null) {
                i(tag, "[INFO DESC] " + desc);
            }

            i(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if(msg != null && logLevel >= 3) {
            if(log) {
                Log.i(tag, msg);
            }

            writeLog2SD("I", tag, msg);
        }
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(Throwable throwable) {
        w(TAG, throwable);
    }

    public static void w(Throwable throwable, String desc) {
        w(TAG, throwable, desc);
    }

    public static void w(String tag, Throwable throwable) {
        w(tag, throwable, (String)null);
    }

    public static void w(String tag, Throwable throwable, String desc) {
        if(throwable != null && logLevel >= 2) {
            String msg = getExceptionInfo(throwable);
            if(desc != null) {
                w(tag, "[WARN DESC] " + desc);
            }

            w(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if(msg != null && logLevel >= 2) {
            if(log) {
                Log.w(tag, msg);
            }

            writeLog2SD("W", tag, msg);
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(Throwable throwable) {
        e(TAG, throwable);
    }

    public static void e(Throwable throwable, String desc) {
        e(TAG, throwable, desc);
    }

    public static void e(String tag, Throwable throwable) {
        e(tag, throwable, (String)null);
    }

    public static void e(String tag, Throwable throwable, String desc) {
        if(throwable != null && logLevel >= 1) {
            String msg = getExceptionInfo(throwable);
            if(desc != null) {
                e(tag, "[ERROR DESC] " + desc);
            }

            e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if(msg != null && logLevel >= 1) {
            if(log) {
                Log.e(tag, msg);
            }

            writeLog2SD("E", tag, msg);
        }
    }

    private static synchronized void writeLog2SD(String logTag, String tag, String msg) {
        if(LOG_TO_FILE) {
            try {
                getFileWriter();
                mWriter.write(sdf.format(new Date()));
                mWriter.write(logTag + "/" + tag);
                mWriter.write("(" + Thread.currentThread().getId() + ") : ");
                mWriter.write(msg);
                mWriter.write("\n");
                mWriter.flush();
            } catch (IOException var6) {
                if(mWriter != null) {
                    try {
                        mWriter.close();
                        mWriter = null;
                    } catch (IOException var5) {
                        var5.printStackTrace();
                    }
                }
            }
        }

    }

    private static Writer getFileWriter() throws IOException {
        String logFileName = getLogFileName();
        if(mWriter == null || !logFileName.equals(todayLogFile)) {
            todayLogFile = logFileName;
            File newFile = createLogFile(todayLogFile);
            if(!newFile.exists()) {
                boolean var2 = cleanLog();
            }

            mWriter = new BufferedWriter(new FileWriter(newFile, true), 2048);
        }

        return mWriter;
    }

    public static File createLogFile(String fileName) {
        File logFile = new File(fileName);
        return logFile;
    }

    public static boolean cleanLog() {
        try {
            getLogDirectory().deleteOnExit();
            return getLogDirectory().mkdirs();
        } catch (Throwable var1) {
            return false;
        }
    }

    public static String getLogFileName() {
        String logDate = (new SimpleDateFormat("yy_MM_dd", Locale.getDefault())).format(new Date());
        return getLogDirectory() + File.separator + "tk_im_sdk_" + logDate + ".log";
    }

    public static File getLogDirectory() {
        return FileUtils.LOG_DIR;
    }

    public static String getExceptionInfo(Throwable throwable) {
        if(throwable == null) {
            return "";
        } else {
            String stack = throwable.toString();
            StackTraceElement[] traceElements;
            int length;
            StackTraceElement traceElement;
            int i;
            if((traceElements = throwable.getStackTrace()) != null) {
                length = traceElements.length;

                for(i = 0; i < length; ++i) {
                    traceElement = traceElements[i];
                    stack = stack + "\n\t\t\t\t\tat " + traceElement.toString();
                }
            }

            throwable = throwable.getCause();
            if(throwable != null) {
                stack = stack + "\n\t\t\tCaused by: " + throwable.toString();
                if((traceElements = throwable.getStackTrace()) != null) {
                    length = traceElements.length;

                    for(i = 0; i < length; ++i) {
                        traceElement = traceElements[i];
                        stack = stack + "\n\t\t\t\t\tat " + traceElement.toString();
                    }
                }
            }

            return stack;
        }
    }
}
