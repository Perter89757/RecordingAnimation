package thinkive.com.mylibrary;

import thinkive.com.twolibrary.ToolsUtils;

/**
 * author: huang_yanhui
 * data:2017/6/29
 * time:14:49
 * emaill:huangyh@thinkive.com
 * description:
 */

public class TKNotifier {

    private TKNotifier.defultListener defultListener;

    private TKNotifier() {
        if (defultListener == null){
            defultListener = new defultListener();
        }
    }
    private static TKNotifier notifier = new TKNotifier();
    public static TKNotifier getInstance(){
        return notifier;
    }

    public  String iconApp(){
        return defultListener.getName();
    }

    public onListener listener;

    public void setListener(onListener listener) {
        this.listener = listener;
    }

    interface onListener{
        String getName( );
    }

    class defultListener implements onListener{
        @Override
        public String getName( ) {
          return ToolsUtils.getInstance().geticon();
        }
    }

}
