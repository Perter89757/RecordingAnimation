package zhihudaily.thinkive.com.service_aidl.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by qiujuer on 15/7/15.
 */
public class User implements Parcelable {
    private UUID id;
    private int age;
    private String name;
    private Account account;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
        this.id = UUID.randomUUID();
    }

    protected User(Parcel in) {
        // Id
        long m = in.readLong();
        long l = in.readLong();
        id = new UUID(m, l);

        age = in.readInt();
        name = in.readString();
        account = in.readParcelable(Account.class.getClassLoader());
    }

    /**
     *  此静态属性，则是为了反编译时使用，
     *  在Parcelable进行反编译时将会调用该属性，所以名称写法基本是固定不变的
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * describeContents 为描述方法，通常返回0
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *  具体的写入操作，在这里对需要传输的数据进行写入，
     *  请一定需要注意的是写入顺序则决定了读取顺序。
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // ID
        long m = id.getMostSignificantBits();
        long l = id.getLeastSignificantBits();
        dest.writeLong(m);
        dest.writeLong(l);
        //user
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeParcelable(account, flags);
    }

    @Override
    public String toString() {
        return "Id:" + id.toString() + " Age:" + age + " Name:" + name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
