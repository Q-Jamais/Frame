package com.lxhf.frame.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jamais on 17/6/28.
 * E-mail : liutl@hfvast.com
 */
@Entity
public class Baby implements Parcelable {
    @Id
    private long id;
    @Property(nameInDb = "Name")
    private String name;
    @Property(nameInDb = "Age")
    private int age;
    @Property(nameInDb = "Weight")
    private int weight;

    @Generated(hash = 271319111)
    public Baby(long id, String name, int age, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Generated(hash = 1845915441)
    public Baby() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Baby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeInt(this.weight);
    }

    protected Baby(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.age = in.readInt();
        this.weight = in.readInt();
    }

    public static final Parcelable.Creator<Baby> CREATOR = new Parcelable.Creator<Baby>() {
        @Override
        public Baby createFromParcel(Parcel source) {
            return new Baby(source);
        }

        @Override
        public Baby[] newArray(int size) {
            return new Baby[size];
        }
    };
}
