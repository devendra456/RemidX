
package com.skyview.remidx.model_class;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DriverModel implements Serializable {

    @SerializedName("AadharNo")
    private Object mAadharNo;
    @SerializedName("Address")
    private Object mAddress;
    @SerializedName("DL_valid_upto")
    private String mDLValidUpto;
    @SerializedName("Dateofbirth")
    private String mDateofbirth;
    @SerializedName("DriverName")
    private String mDriverName;
    @SerializedName("Driving_License")
    private String mDrivingLicense;
    @SerializedName("ID")
    private Long mID;
    @SerializedName("PanId")
    private Object mPanId;
    @SerializedName("Photo")
    private Object mPhoto;
    @SerializedName("SalaryAmtPerMonth")
    private Object mSalaryAmtPerMonth;
    @SerializedName("Sign")
    private Object mSign;
    @SerializedName("WechileNo")
    private Object mWechileNo;

    public Object getAadharNo() {
        return mAadharNo;
    }

    public void setAadharNo(Object aadharNo) {
        mAadharNo = aadharNo;
    }

    public Object getAddress() {
        return mAddress;
    }

    public void setAddress(Object address) {
        mAddress = address;
    }

    public String getDLValidUpto() {
        return mDLValidUpto;
    }

    public void setDLValidUpto(String dLValidUpto) {
        mDLValidUpto = dLValidUpto;
    }

    public String getDateofbirth() {
        return mDateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        mDateofbirth = dateofbirth;
    }

    public String getDriverName() {
        return mDriverName;
    }

    public void setDriverName(String driverName) {
        mDriverName = driverName;
    }

    public String getDrivingLicense() {
        return mDrivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        mDrivingLicense = drivingLicense;
    }

    public Long getID() {
        return mID;
    }

    public void setID(Long iD) {
        mID = iD;
    }

    public Object getPanId() {
        return mPanId;
    }

    public void setPanId(Object panId) {
        mPanId = panId;
    }

    public Object getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Object photo) {
        mPhoto = photo;
    }

    public Object getSalaryAmtPerMonth() {
        return mSalaryAmtPerMonth;
    }

    public void setSalaryAmtPerMonth(Object salaryAmtPerMonth) {
        mSalaryAmtPerMonth = salaryAmtPerMonth;
    }

    public Object getSign() {
        return mSign;
    }

    public void setSign(Object sign) {
        mSign = sign;
    }

    public Object getWechileNo() {
        return mWechileNo;
    }

    public void setWechileNo(Object wechileNo) {
        mWechileNo = wechileNo;
    }

}
