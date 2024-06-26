package com.samprama.milkrecieptapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TesterNameModel {

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
    private TesterNameData data;

    /**
     * No args constructor for use in serialization
     *
     */
    public TesterNameModel() {
    }

    /**
     *
     * @param data
     * @param message
     * @param statusCode
     * @param status
     */
    public TesterNameModel(Integer statusCode, String status, String message, TesterNameData data) {
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

    public TesterNameData getData() {
        return data;
    }

    public void setData(TesterNameData data) {
        this.data = data;
    }

}