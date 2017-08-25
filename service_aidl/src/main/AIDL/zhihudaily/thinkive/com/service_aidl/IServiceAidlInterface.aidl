// IServiceAidlInterface.aidl
package zhihudaily.thinkive.com.service_aidl;
import zhihudaily.thinkive.com.service_aidl.bean.User;
// Declare any non-default types here with import statements

interface IServiceAidlInterface {
    void addAge();//用于service传递数据给界面
       void setName(String name);//用于界面修改service的数据
           User getUser();//传递数据给界面
}
