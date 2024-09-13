package com.bright.supreme.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TesterNameData {

    @SerializedName("testerName")
    @Expose
    private String testerName;
    @SerializedName("testerID")
    @Expose
    private int testerID;

    /**
     * No args constructor for use in serialization
     *
     */
    public TesterNameData() {
    }

    /**
     *
     * @param testerID
     * @param testerName
     */
    public TesterNameData(String testerName, int testerID) {
        super();
        this.testerName = testerName;
        this.testerID = testerID;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public int getTesterID() {
        return testerID;
    }

    public void setTesterID(int testerID) {
        this.testerID = testerID;
    }

}