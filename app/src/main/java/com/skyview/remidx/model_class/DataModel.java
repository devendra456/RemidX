package com.skyview.remidx.model_class;

public class DataModel implements Comparable<DataModel>{
    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getTruckName() {
        return truckName;
    }

    public void setTruckName(String truckName) {
        this.truckName = truckName;
    }

    public String getExpiryOn() {
        return ExpiryOn;
    }

    public void setExpiryOn(String expiryOn) {
        ExpiryOn = expiryOn;
    }

    public String getRemainDays() {
        return RemainDays;
    }

    public void setRemainDays(String remainDays) {
        RemainDays = remainDays;
    }

    private String truckNumber;
    private String truckName;
    private String ExpiryOn;
    private String RemainDays;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int day;

    @Override
    public int compareTo(DataModel dataModel) {
        return Integer.parseInt(this.RemainDays)-Integer.parseInt(dataModel.getRemainDays());
    }
}
