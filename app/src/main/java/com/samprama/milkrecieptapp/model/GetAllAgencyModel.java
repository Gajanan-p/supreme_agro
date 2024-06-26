package com.samprama.milkrecieptapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllAgencyModel {

    @SerializedName("agencyId")
    @Expose
    private Integer agencyId;
    @SerializedName("agencyCode")
    @Expose
    private String agencyCode;
    @SerializedName("agencyName")
    @Expose
    private String agencyName;
    @SerializedName("agencyZone")
    @Expose
    private String agencyZone;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetAllAgencyModel() {
    }

    /**
     *
     * @param agencyZone
     * @param agencyId
     * @param agencyCode
     * @param agencyName
     */
    public GetAllAgencyModel(Integer agencyId, String agencyCode, String agencyName, String agencyZone) {
        super();
        this.agencyId = agencyId;
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
        this.agencyZone = agencyZone;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyZone() {
        return agencyZone;
    }

    public void setAgencyZone(String agencyZone) {
        this.agencyZone = agencyZone;
    }

}