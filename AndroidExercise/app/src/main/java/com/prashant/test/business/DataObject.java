package com.prashant.test.business;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataObject implements Serializable {

    @SerializedName("title")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("imageHref")
    private String mImage;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
