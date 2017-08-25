
package zhihudaily.thinkive.com.service_aidl;

interface NotifyCallBack  {
    void notifyMainUiThread(String name,boolean joinOrLeave,int count);
}
