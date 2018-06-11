package com.example.welfare.module_welfare.bean;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Created by TOME .
 * @时间 2018/6/6 16:20
 * @描述 ${序列化}
 */

public class PreviewBean implements Parcelable {

    private String urlString;

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.urlString);
    }

    public PreviewBean() {
    }

    protected PreviewBean(Parcel in) {
        this.urlString = in.readString();
    }

    public static final Parcelable.Creator<PreviewBean> CREATOR = new Parcelable.Creator<PreviewBean>() {
        @Override
        public PreviewBean createFromParcel(Parcel source) {
            return new PreviewBean(source);
        }

        @Override
        public PreviewBean[] newArray(int size) {
            return new PreviewBean[size];
        }
    };
}
