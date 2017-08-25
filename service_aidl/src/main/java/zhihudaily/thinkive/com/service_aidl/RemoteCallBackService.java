package zhihudaily.thinkive.com.service_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zhihudaily.thinkive.com.service_aidl.bean.User;


/**
 * author: huang_yanhui
 * data:2017/8/25
 * time:11:24
 * emaill:huangyh@thinkive.com
 * description:
 */

public class RemoteCallBackService extends Service {
    //这个list 就是用来存储当前餐厅有多少顾客 注意我们为什么没有用顾客的名字来存储？
    //而是用了这个CustomerClient的类 看这个类的注释即可明白
    private List<CustomerClient> mClientsList = new ArrayList<>();

    //上面用CustomerClient 的原因是因为害怕客户端异常销毁时，服务器收不到消息 造成资源浪费等异常
    //同样的 我们在服务端通知客户端消息的时候 也害怕 服务端 会异常销毁 导致客户端收不到消息
    //好在谷歌早就为我们考虑到这种情况  提供了RemoteCallbackList 来完成对应的功能
    //避免我们再重复一遍上述的过程
    private RemoteCallbackList<NotifyCallBack> mCallBacks = new RemoteCallbackList<>();

    private final RestaurantAidlInterface.Stub mBinder = new RestaurantAidlInterface.Stub() {


        @Override
        public void join(IBinder token, String name) throws RemoteException {
            CustomerClient cl = new CustomerClient(token, name);
            mClientsList.add(cl);//记录顾客
            notifyCallBack(name, true,mClientsList.size());//回调给界面
            User user = new User(22,name);
        }

        @Override
        public void leave() throws RemoteException {
            //顾客离开的时候 我们随机让他离开一个就行了
            int length = mClientsList.size();
            int randomIndex = new Random().nextInt(length-1);
            mClientsList.remove(randomIndex);

            notifyCallBack(mClientsList.get(randomIndex).mCustomerName, false,mClientsList.size());
        }

        @Override
        public void registerCallBack(NotifyCallBack cb) throws RemoteException {
            mCallBacks.register(cb);
        }

        @Override
        public void unregisterCallBack(NotifyCallBack cb) throws RemoteException {
            mCallBacks.unregister(cb);
        }
    };

    private void notifyCallBack(String customerName, boolean joinOrLeave,int count) {
        final int len = mCallBacks.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                // 通知回调
                //mCallBacks.getBroadcastItem(i)获得接口
                mCallBacks.getBroadcastItem(i).notifyMainUiThread(customerName, joinOrLeave,count);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mCallBacks.finishBroadcast();
    }

    //http://developer.android.com/intl/zh-cn/reference/android/os/Binder.html#linkToDeath(android.os.IBinder.DeathRecipient, int)
    //实际上 这个接口 就是用来 当客户端自己发生崩溃时， 我们的服务端也能收到这个崩溃的消息
    //并且会调用binderDied 这个回调方法，所以你看这个内部类的代码 就明白了 无非就是保证当客户端异常销毁的时候
    //我们服务端也要保证收到这个消息 然后做出相应的应对
    final class CustomerClient implements IBinder.DeathRecipient {

        public final IBinder mToken;

        public CustomerClient(IBinder mToken, String mCustomerName) {
            this.mToken = mToken;
            this.mCustomerName = mCustomerName;
        }

        public final String mCustomerName;

        @Override
        public void binderDied() {
            //我们的应对方法就是当客户端 也就是顾客异常消失的时候 我们要把这个list里面 的对象也移出掉
            if (mClientsList.indexOf(this) >= 0) {
                mClientsList.remove(this);
            }
        }
    }


    @Override
    public void onDestroy() {
        //销毁回调资源 否则要内存泄露
        mCallBacks.kill();
        super.onDestroy();
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
