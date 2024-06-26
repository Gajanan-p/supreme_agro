package com.samprama.milkrecieptapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllTesterModel {

    @SerializedName("testerID")
    @Expose
    private Integer testerID;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("testerName")
    @Expose
    private String testerName;
    @SerializedName("mobileNo")
    @Expose
    private Long mobileNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllTesterModel() {
    }

    /**
     *
     * @param code
     * @param testerID
     * @param mobileNo
     * @param testerName
     */
    public GetAllTesterModel(Integer testerID, String code, String testerName, Long mobileNo) {
        super();
        this.testerID = testerID;
        this.code = code;
        this.testerName = testerName;
        this.mobileNo = mobileNo;
    }

    public Integer getTesterID() {
        return testerID;
    }

    public void setTesterID(Integer testerID) {
        this.testerID = testerID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

}