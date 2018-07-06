package com.prashant.test.business;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DomainModel {

    @SerializedName("rows")
    private List<DataObject> mData;

    public List<DataObject> getmData() {
        return mData;
    }

    public void setmData(List<DataObject> mData) {
        this.mData = mData;
    }
}
