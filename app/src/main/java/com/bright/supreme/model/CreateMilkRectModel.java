package com.bright.supreme.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateMilkRectModel {

    @SerializedName("milkReceiptId")
    @Expose
    private int milkReceiptId;
    @SerializedName("rectNo")
    @Expose
    private int rectNo;
    @SerializedName("rectDate")
    @Expose
    private String rectDate;
    @SerializedName("mobileNo")
    @Expose
    private long mobileNo;
    @SerializedName("vehicleId")
    @Expose
    private int vehicleId;
    @SerializedName("agencyId")
    @Expose
    private int agencyId;
    @SerializedName("agencyZone")
    @Expose
    private String agencyZone;
    @SerializedName("testerId")
    @Expose
    private int testerId;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("fatPer")
    @Expose
    private String fatPer;
    @SerializedName("clr")
    @Expose
    private String clr;
    @SerializedName("snfPer")
    @Expose
    private String snfPer;
    @SerializedName("temperature")
    @Expose
    private String temperature;
    @SerializedName("flushValue")
    @Expose
    private String flushValue;
    @SerializedName("dipReading")
    @Expose
    private String dipReading;
    @SerializedName("bmQty")
    @Expose
    private String bmQty;
    @SerializedName("bmFat")
    @Expose
    private String bmFat;
    @SerializedName("bmClr")
    @Expose
    private String bmClr;
    @SerializedName("bmSnfPer")
    @Expose
    private String bmSnfPer;
    @SerializedName("cmQty")
    @Expose
    private String cmQty;
    @SerializedName("cmFat")
    @Expose
    private String cmFat;
    @SerializedName("cmClr")
    @Expose
    private String cmClr;
    @SerializedName("cmSnfPer")
    @Expose
    private String cmSnfPer;
    @SerializedName("dateInsert")
    @Expose
    private String dateInsert;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateMilkRectModel() {
    }

    /**
     *
     * @param dipReading
     * @param rectNo
     * @param quantity
     * @param bmSnfPer
     * @param cmSnfPer
     * @param clr
     * @param snfPer
     * @param bmClr
     * @param cmQty
     * @param agencyId
     * @param cmFat
     * @param mobileNo
     * @param milkReceiptId
     * @param flushValue
     * @param agencyZone
     * @param dateInsert
     * @param testerId
     * @param temperature
     * @param bmFat
     * @param cmClr
     * @param vehicleId
     * @param rectDate
     * @param bmQty
     * @param fatPer
     */
    public CreateMilkRectModel(int milkReceiptId, int rectNo, String rectDate, long mobileNo, int vehicleId, int agencyId, String agencyZone, int testerId, String quantity, String fatPer, String clr, String snfPer, String temperature, String flushValue, String dipReading, String bmQty, String bmFat, String bmClr, String bmSnfPer, String cmQty, String cmFat, String cmClr, String cmSnfPer, String dateInsert) {
        super();
        this.milkReceiptId = milkReceiptId;
        this.rectNo = rectNo;
        this.rectDate = rectDate;
        this.mobileNo = mobileNo;
        this.vehicleId = vehicleId;
        this.agencyId = agencyId;
        this.agencyZone = agencyZone;
        this.testerId = testerId;
        this.quantity = quantity;
        this.fatPer = fatPer;
        this.clr = clr;
        this.snfPer = snfPer;
        this.temperature = temperature;
        this.flushValue = flushValue;
        this.dipReading = dipReading;
        this.bmQty = bmQty;
        this.bmFat = bmFat;
        this.bmClr = bmClr;
        this.bmSnfPer = bmSnfPer;
        this.cmQty = cmQty;
        this.cmFat = cmFat;
        this.cmClr = cmClr;
        this.cmSnfPer = cmSnfPer;
        this.dateInsert = dateInsert;
    }

    public int getMilkReceiptId() {
        return milkReceiptId;
    }

    public void setMilkReceiptId(int milkReceiptId) {
        this.milkReceiptId = milkReceiptId;
    }

    public int getRectNo() {
        return rectNo;
    }

    public void setRectNo(int rectNo) {
        this.rectNo = rectNo;
    }

    public String getRectDate() {
        return rectDate;
    }

    public void setRectDate(String rectDate) {
        this.rectDate = rectDate;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyZone() {
        return agencyZone;
    }

    public void setAgencyZone(String agencyZone) {
        this.agencyZone = agencyZone;
    }

    public int getTesterId() {
        return testerId;
    }

    public void setTesterId(int testerId) {
        this.testerId = testerId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFatPer() {
        return fatPer;
    }

    public void setFatPer(String fatPer) {
        this.fatPer = fatPer;
    }

    public String getClr() {
        return clr;
    }

    public void setClr(String clr) {
        this.clr = clr;
    }

    public String getSnfPer() {
        return snfPer;
    }

    public void setSnfPer(String snfPer) {
        this.snfPer = snfPer;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFlushValue() {
        return flushValue;
    }

    public void setFlushValue(String flushValue) {
        this.flushValue = flushValue;
    }

    public String getDipReading() {
        return dipReading;
    }

    public void setDipReading(String dipReading) {
        this.dipReading = dipReading;
    }

    public String getBmQty() {
        return bmQty;
    }

    public void setBmQty(String bmQty) {
        this.bmQty = bmQty;
    }

    public String getBmFat() {
        return bmFat;
    }

    public void setBmFat(String bmFat) {
        this.bmFat = bmFat;
    }

    public String getBmClr() {
        return bmClr;
    }

    public void setBmClr(String bmClr) {
        this.bmClr = bmClr;
    }

    public String getBmSnfPer() {
        return bmSnfPer;
    }

    public void setBmSnfPer(String bmSnfPer) {
        this.bmSnfPer = bmSnfPer;
    }

    public String getCmQty() {
        return cmQty;
    }

    public void setCmQty(String cmQty) {
        this.cmQty = cmQty;
    }

    public String getCmFat() {
        return cmFat;
    }

    public void setCmFat(String cmFat) {
        this.cmFat = cmFat;
    }

    public String getCmClr() {
        return cmClr;
    }

    public void setCmClr(String cmClr) {
        this.cmClr = cmClr;
    }

    public String getCmSnfPer() {
        return cmSnfPer;
    }

    public void setCmSnfPer(String cmSnfPer) {
        this.cmSnfPer = cmSnfPer;
    }

    public String getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(String dateInsert) {
        this.dateInsert = dateInsert;
    }

}