package com.bright.supreme.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportReqModel {

    @SerializedName("m_FDate")
    @Expose
    private String mFDate;
    @SerializedName("m_TDate")
    @Expose
    private String mTDate;
    @SerializedName("m_Mobile")
    @Expose
    private long mMobile;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReportReqModel() {
    }

    /**
     *
     * @param mTDate
     * @param mMobile
     * @param mFDate
     */
    public ReportReqModel(String mFDate, String mTDate, long mMobile) {
        super();
        this.mFDate = mFDate;
        this.mTDate = mTDate;
        this.mMobile = mMobile;
    }

    public String getmFDate() {
        return mFDate;
    }

    public void setmFDate(String mFDate) {
        this.mFDate = mFDate;
    }

    public String getmTDate() {
        return mTDate;
    }

    public void setmTDate(String mTDate) {
        this.mTDate = mTDate;
    }

    public long getmMobile() {
        return mMobile;
    }

    public void setmMobile(long mMobile) {
        this.mMobile = mMobile;
    }

}