package com.bright.supreme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVehicleModel {

    @SerializedName("vehicleID")
    @Expose
    private Integer vehicleID;
    @SerializedName("vehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("shortNo")
    @Expose
    private String shortNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllVehicleModel() {
    }

    /**
     *
     * @param vehicleNo
     * @param vehicleID
     * @param shortNo
     */
    public GetAllVehicleModel(Integer vehicleID, String vehicleNo, String shortNo) {
        super();
        this.vehicleID = vehicleID;
        this.vehicleNo = vehicleNo;
        this.shortNo = shortNo;
    }

    public Integer getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Integer vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getShortNo() {
        return shortNo;
    }

    public void setShortNo(String shortNo) {
        this.shortNo = shortNo;
    }

}