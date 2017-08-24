// IServiceAidlInterface.aidl
package zhihudaily.thinkive.com.service_aidl;
import zhihudaily.thinkive.com.service_aidl.bean.User;
// Declare any non-default types here with import statements

interface IServiceAidlInterface {
    void addAge();
       void setName(String name);
           User getUser();
}
