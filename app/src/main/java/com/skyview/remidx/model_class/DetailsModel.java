
package com.skyview.remidx.model_class;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DetailsModel implements Serializable {
    @SerializedName("CreatedBy")
    private String mCreatedBy;
    @SerializedName("Createdon")
    private Object mCreatedon;
    @SerializedName("Fitness_From_Date")
    private String mFitnessFromDate;
    @SerializedName("Fitness_To_Date")
    private String mFitnessToDate;
    @SerializedName("ID")
    private Long mID;
    @SerializedName("InsuranceCompany")
    private String mInsuranceCompany;
    @SerializedName("Insurance_From_Date")
    private String mInsuranceFromDate;
    @SerializedName("Insurance_To_Date")
    private String mInsuranceToDate;
    @SerializedName("Mobile")
    private String mMobile;
    @SerializedName("PUC_From")
    private String mPUCFrom;
    @SerializedName("PUC_To")
    private String mPUCTo;
    @SerializedName("Permit_Globel_FromDate")
    private Object mPermitGlobelFromDate;
    @SerializedName("Permit_Globel_ToDate")
    private Object mPermitGlobelToDate;
    @SerializedName("Permit_State_FromDate")
    private String mPermitStateFromDate;
    @SerializedName("Permit_State_ToDate")
    private String mPermitStateToDate;
    @SerializedName("Tax_From")
    private String mTaxFrom;
    @SerializedName("Tax_to")
    private String mTaxTo;
    @SerializedName("updatedon")
    private String mUpdatedon;
    @SerializedName("Whicle_Num")
    private String mWhicleNum;
    @SerializedName("Whicle_OwnerName")
    private String mWhicleOwnerName;

    public String getCreatedBy() {
        return mCreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public Object getCreatedon() {
        return mCreatedon;
    }

    public void setCreatedon(Object createdon) {
        mCreatedon = createdon;
    }

    public String getFitnessFromDate() {
        return mFitnessFromDate;
    }

    public void setFitnessFromDate(String fitnessFromDate) {
        mFitnessFromDate = fitnessFromDate;
    }

    public String getFitnessToDate() {
        return mFitnessToDate;
    }

    public void setFitnessToDate(String fitnessToDate) {
        mFitnessToDate = fitnessToDate;
    }

    public Long getID() {
        return mID;
    }

    public void setID(Long iD) {
        mID = iD;
    }

    public String getInsuranceCompany() {
        return mInsuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        mInsuranceCompany = insuranceCompany;
    }

    public String getInsuranceFromDate() {
        return mInsuranceFromDate;
    }

    public void setInsuranceFromDate(String insuranceFromDate) {
        mInsuranceFromDate = insuranceFromDate;
    }

    public String getInsuranceToDate() {
        return mInsuranceToDate;
    }

    public void setInsuranceToDate(String insuranceToDate) {
        mInsuranceToDate = insuranceToDate;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public String getPUCFrom() {
        return mPUCFrom;
    }

    public void setPUCFrom(String pUCFrom) {
        mPUCFrom = pUCFrom;
    }

    public String getPUCTo() {
        return mPUCTo;
    }

    public void setPUCTo(String pUCTo) {
        mPUCTo = pUCTo;
    }

    public Object getPermitGlobelFromDate() {
        return mPermitGlobelFromDate;
    }

    public void setPermitGlobelFromDate(Object permitGlobelFromDate) {
        mPermitGlobelFromDate = permitGlobelFromDate;
    }

    public Object getPermitGlobelToDate() {
        return mPermitGlobelToDate;
    }

    public void setPermitGlobelToDate(Object permitGlobelToDate) {
        mPermitGlobelToDate = permitGlobelToDate;
    }

    public String getPermitStateFromDate() {
        return mPermitStateFromDate;
    }

    public void setPermitStateFromDate(String permitStateFromDate) {
        mPermitStateFromDate = permitStateFromDate;
    }

    public String getPermitStateToDate() {
        return mPermitStateToDate;
    }

    public void setPermitStateToDate(String permitStateToDate) {
        mPermitStateToDate = permitStateToDate;
    }

    public String getTaxFrom() {
        return mTaxFrom;
    }

    public void setTaxFrom(String taxFrom) {
        mTaxFrom = taxFrom;
    }

    public String getTaxTo() {
        return mTaxTo;
    }

    public void setTaxTo(String taxTo) {
        mTaxTo = taxTo;
    }

    public String getUpdatedon() {
        return mUpdatedon;
    }

    public void setUpdatedon(String updatedon) {
        mUpdatedon = updatedon;
    }

    public String getWhicleNum() {
        return mWhicleNum;
    }

    public void setWhicleNum(String whicleNum) {
        mWhicleNum = whicleNum;
    }

    public String getWhicleOwnerName() {
        return mWhicleOwnerName;
    }

    public void setWhicleOwnerName(String whicleOwnerName) {
        mWhicleOwnerName = whicleOwnerName;
    }

}
