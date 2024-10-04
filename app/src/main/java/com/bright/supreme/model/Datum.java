package com.bright.supreme.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("milkReceiptID")
    @Expose
    private int milkReceiptID;
    @SerializedName("vehicleNo")
    @Expose
    private String vehicleNo;
    @SerializedName("mobileNo")
    @Expose
    private long mobileNo;
    @SerializedName("rectDate")
    @Expose
    private String rectDate;
    @SerializedName("agencycode")
    @Expose
    private String agencycode;
    @SerializedName("agencyname")
    @Expose
    private String agencyname;
    @SerializedName("dipqty")
    @Expose
    private String dipqty;
    @SerializedName("milkQty")
    @Expose
    private String milkQty;
    @SerializedName("fatper")
    @Expose
    private String fatper;
    @SerializedName("snf")
    @Expose
    private String snf;
    @SerializedName("clr")
    @Expose
    private String clr;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("flushvalue")
    @Expose
    private String flushvalue;
    @SerializedName("testername")
    @Expose
    private String testername;

    /**
     * No args constructor for use in serialization
     *
     */
    public Datum() {
    }

    /**
     *
     * @param snf
     * @param mobileNo
     * @param dipqty
     * @param milkQty
     * @param milkReceiptID
     * @param vehicleNo
     * @param flushvalue
     * @param temperature
     * @param agencycode
     * @param rectDate
     * @param agencyname
     * @param testername
     * @param fatper
     */
    public Datum(int milkReceiptID, String vehicleNo, long mobileNo, String rectDate, String agencycode, String agencyname,
                 String dipqty, String milkQty, String fatper, String snf, String clr, String temperature, String flushvalue,
                 String testername) {
        super();
        this.milkReceiptID = milkReceiptID;
        this.vehicleNo = vehicleNo;
        this.mobileNo = mobileNo;
        this.rectDate = rectDate;
        this.agencycode = agencycode;
        this.agencyname = agencyname;
        this.dipqty = dipqty;
        this.milkQty = milkQty;
        this.fatper = fatper;
        this.snf = snf;
        this.clr = clr;
        this.temperature = temperature;
        this.flushvalue = flushvalue;
        this.testername = testername;
    }

    public int getMilkReceiptID() {
        return milkReceiptID;
    }

    public void setMilkReceiptID(int milkReceiptID) {
        this.milkReceiptID = milkReceiptID;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRectDate() {
        return rectDate;
    }

    public void setRectDate(String rectDate) {
        this.rectDate = rectDate;
    }

    public String getAgencycode() {
        return agencycode;
    }

    public void setAgencycode(String agencycode) {
        this.agencycode = agencycode;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getDipqty() {
        return dipqty;
    }

    public void setDipqty(String dipqty) {
        this.dipqty = dipqty;
    }

    public String getMilkQty() {
        return milkQty;
    }

    public void setMilkQty(String milkQty) {
        this.milkQty = milkQty;
    }

    public String getFatper() {
        return fatper;
    }

    public void setFatper(String fatper) {
        this.fatper = fatper;
    }

    public String getSnf() {
        return snf;
    }

    public void setSnf(String snf) {
        this.snf = snf;
    }

    public String getClr() {
        return clr;
    }

    public void setClr(String clr) {
        this.clr = clr;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFlushvalue() {
        return flushvalue;
    }

    public void setFlushvalue(String flushvalue) {
        this.flushvalue = flushvalue;
    }

    public String getTestername() {
        return testername;
    }

    public void setTestername(String testername) {
        this.testername = testername;
    }

}