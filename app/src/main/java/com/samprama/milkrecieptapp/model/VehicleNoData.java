package com.samprama.milkrecieptapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleNoData {

    @SerializedName("vehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("vehicleID")
    @Expose
    private int vehicleID;

    /**
     * No args constructor for use in serialization
     *
     */
    public VehicleNoData() {
    }

    /**
     *
     * @param vehicleNo
     * @param vehicleID
     */
    public VehicleNoData(String vehicleNo, int vehicleID) {
        super();
        this.vehicleNo = vehicleNo;
        this.vehicleID = vehicleID;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

}