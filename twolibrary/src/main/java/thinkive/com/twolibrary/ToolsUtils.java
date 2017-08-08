package thinkive.com.twolibrary;

/**
 * author: huang_yanhui
 * data:2017/6/29
 * time:15:36
 * emaill:huangyh@thinkive.com
 * description:
 */

public class ToolsUtils {
    public waiCengListener waiCengListener;

    private ToolsUtils() {
    }

    private static ToolsUtils toolsUtils = new ToolsUtils();

    public static ToolsUtils getInstance() {
        if (toolsUtils == null) {
            toolsUtils = new ToolsUtils();
        }
        return toolsUtils;
    }

    //获取app模块的数据-->传递给下一个接口
    public String geticon() {
        if (waiCengListener != null) {
            return waiCengListener.geticon();
        }
        return null;
    }

    //获取外界对象的app名字
    public String getAppName(){
        return waiCengListener.getName();
    }

    public interface waiCengListener {
        String geticon();

        String getName();

        void setFlag(String s);
    }

    //获得外界的对象
    public void setWaiCengListener(ToolsUtils.waiCengListener waiCengListener) {
        this.waiCengListener = waiCengListener;
    }


}
