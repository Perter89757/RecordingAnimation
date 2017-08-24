package zhihudaily.thinkive.com.service_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


import zhihudaily.thinkive.com.service_aidl.bean.User;

/**
 * author: huang_yanhui
 * data:2017/8/24
 * time:18:36
 * emaill:huangyh@thinkive.com
 * description:
 */

public class IndependentService  extends Service {

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
        ServiceBinder seriveBinder = new ServiceBinder();
        return seriveBinder;
    }

    class ServiceBinder extends IServiceAidlInterface.Stub{

        private User user;

        public ServiceBinder() {
            user = new User(21, "service_XiaoMing_21");
        }


        @Override
        public void addAge() throws RemoteException {
            user.setAge(user.getAge() + 1);
        }

        @Override
        public void setName(String name) throws RemoteException {
            user.setName(name);
        }

        @Override
        public User getUser() throws RemoteException {
            return user;
        }
    }
}
