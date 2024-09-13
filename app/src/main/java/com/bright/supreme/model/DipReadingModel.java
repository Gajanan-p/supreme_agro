package com.bright.supreme.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DipReadingModel {

    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private WeightData data;

    /**
     * No args constructor for use in serialization
     *
     */
    public DipReadingModel() {
    }

    /**
     *
     * @param data
     * @param message
     * @param statusCode
     * @param status
     */
    public DipReadingModel(int statusCode, String status, String message, WeightData data) {
        super();
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
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

    public WeightData getData() {
        return data;
    }

    public void setData(WeightData data) {
        this.data = data;
    }

}