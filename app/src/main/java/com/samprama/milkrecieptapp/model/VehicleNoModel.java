package com.samprama.milkrecieptapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleNoModel {

    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private VehicleNoData data;

    /**
     * No args constructor for use in serialization
     *
     */
    public VehicleNoModel() {
    }

    /**
     *
     * @param data
     * @param message
     * @param statusCode
     * @param status
     */
    public VehicleNoModel(Integer statusCode, String status, String message, VehicleNoData data) {
        super();
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VehicleNoData getData() {
        return data;
    }

    public void setData(VehicleNoData data) {
        this.data = data;
    }

}