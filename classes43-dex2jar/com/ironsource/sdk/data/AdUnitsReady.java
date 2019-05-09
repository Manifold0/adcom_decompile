// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

public class AdUnitsReady extends SSAObj
{
    private static String FIRST_CAMPAIGN_CREDITS;
    private static String NUM_OF_AD_UNITS;
    private static String PRODUCT_TYPE;
    private static String TOTAL_NUMBER_CREDITS;
    private static String TYPE;
    private String mFirstCampaignCredits;
    private String mNumOfAdUnits;
    private boolean mNumOfAdUnitsExist;
    private String mProductType;
    private String mTotalNumberCredits;
    private String mType;
    
    static {
        AdUnitsReady.TYPE = "type";
        AdUnitsReady.NUM_OF_AD_UNITS = "numOfAdUnits";
        AdUnitsReady.FIRST_CAMPAIGN_CREDITS = "firstCampaignCredits";
        AdUnitsReady.TOTAL_NUMBER_CREDITS = "totalNumberCredits";
        AdUnitsReady.PRODUCT_TYPE = "productType";
    }
    
    public AdUnitsReady(final String s) {
        super(s);
        if (this.containsKey(AdUnitsReady.TYPE)) {
            this.setType(this.getString(AdUnitsReady.TYPE));
        }
        if (this.containsKey(AdUnitsReady.NUM_OF_AD_UNITS)) {
            this.setNumOfAdUnits(this.getString(AdUnitsReady.NUM_OF_AD_UNITS));
            this.setNumOfAdUnitsExist(true);
        }
        else {
            this.setNumOfAdUnitsExist(false);
        }
        if (this.containsKey(AdUnitsReady.FIRST_CAMPAIGN_CREDITS)) {
            this.setFirstCampaignCredits(this.getString(AdUnitsReady.FIRST_CAMPAIGN_CREDITS));
        }
        if (this.containsKey(AdUnitsReady.TOTAL_NUMBER_CREDITS)) {
            this.setTotalNumberCredits(this.getString(AdUnitsReady.TOTAL_NUMBER_CREDITS));
        }
        if (this.containsKey(AdUnitsReady.PRODUCT_TYPE)) {
            this.setProductType(this.getString(AdUnitsReady.PRODUCT_TYPE));
        }
    }
    
    private void setNumOfAdUnitsExist(final boolean mNumOfAdUnitsExist) {
        this.mNumOfAdUnitsExist = mNumOfAdUnitsExist;
    }
    
    public String getFirstCampaignCredits() {
        return this.mFirstCampaignCredits;
    }
    
    public String getNumOfAdUnits() {
        return this.mNumOfAdUnits;
    }
    
    public String getProductType() {
        return this.mProductType;
    }
    
    public String getTotalNumberCredits() {
        return this.mTotalNumberCredits;
    }
    
    public String getType() {
        return this.mType;
    }
    
    public boolean isNumOfAdUnitsExist() {
        return this.mNumOfAdUnitsExist;
    }
    
    public void setFirstCampaignCredits(final String mFirstCampaignCredits) {
        this.mFirstCampaignCredits = mFirstCampaignCredits;
    }
    
    public void setNumOfAdUnits(final String mNumOfAdUnits) {
        this.mNumOfAdUnits = mNumOfAdUnits;
    }
    
    public void setProductType(final String mProductType) {
        this.mProductType = mProductType;
    }
    
    public void setTotalNumberCredits(final String mTotalNumberCredits) {
        this.mTotalNumberCredits = mTotalNumberCredits;
    }
    
    public void setType(final String mType) {
        this.mType = mType;
    }
}
