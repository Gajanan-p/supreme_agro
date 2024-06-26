package com.samprama.milkrecieptapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDataModels {

    @SerializedName("rectNo")
    @Expose
    private Integer rectNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseDataModels() {
    }

    /**
     *
     * @param rectNo
     */
    public ResponseDataModels(Integer rectNo) {
        super();
        this.rectNo = rectNo;
    }

    public Integer getRectNo() {
        return rectNo;
    }

    public void setRectNo(Integer rectNo) {
        this.rectNo = rectNo;
    }

}