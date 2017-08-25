// ITaskBinder.aidl
package zhihudaily.thinkive.com.service_aidl;

// Declare any non-default types here with import statements
import zhihudaily.thinkive.com.service_aidl.NotifyCallBack;
interface RestaurantAidlInterface  {
    //新来了一个顾客
    void join(IBinder token,String name);
    //走了一个顾客
    void leave();
    //注册回调接口
    void registerCallBack(NotifyCallBack cb);
    void unregisterCallBack(NotifyCallBack cb);
}
