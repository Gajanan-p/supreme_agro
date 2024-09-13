package com.bright.supreme.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RectNoModel {

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
    private ResponseDataModels data;

    /**
     * No args constructor for use in serialization
     *
     */
    public RectNoModel() {
    }

    /**
     *
     * @param data
     * @param message
     * @param statusCode
     * @param status
     */
    public RectNoModel(Integer statusCode, String status, String message, ResponseDataModels data) {
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

    public String getMSG() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseDataModels getData() {
        return data;
    }

    public void setData(ResponseDataModels data) {
        this.data = data;
    }

}