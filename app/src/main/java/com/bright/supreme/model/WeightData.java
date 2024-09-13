package com.bright.supreme.model;


 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class WeightData {

    @SerializedName("weight")
    @Expose
    private double weight;

    /**
     * No args constructor for use in serialization
     *
     */
    public WeightData() {
    }

    /**
     *
     * @param weight
     */
    public WeightData(double weight) {
        super();
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}