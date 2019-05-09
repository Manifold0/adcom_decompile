// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

import android.text.TextUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.Map;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class AdUnitsState implements Parcelable
{
    public static final Parcelable$Creator<AdUnitsState> CREATOR;
    private String mDisplayedDemandSourceName;
    private int mDisplayedProduct;
    private String mInterstitialAppKey;
    private Map<String, String> mInterstitialExtraParams;
    private ArrayList<String> mInterstitialInitSuccess;
    private ArrayList<String> mInterstitialLoadSuccess;
    private ArrayList<String> mInterstitialReportInit;
    private ArrayList<String> mInterstitialReportLoad;
    private String mInterstitialUserId;
    private Map<String, String> mOfferWallExtraParams;
    private boolean mOfferwallInitSuccess;
    private boolean mOfferwallReportInit;
    private String mRVAppKey;
    private String mRVUserId;
    private boolean mShouldRestore;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<AdUnitsState>() {
            public AdUnitsState createFromParcel(final Parcel parcel) {
                return new AdUnitsState(parcel, null);
            }
            
            public AdUnitsState[] newArray(final int n) {
                return new AdUnitsState[n];
            }
        };
    }
    
    public AdUnitsState() {
        this.initialize();
    }
    
    private AdUnitsState(final Parcel parcel) {
        final boolean b = true;
        this.initialize();
        try {
            this.mShouldRestore = (parcel.readByte() != 0);
            this.mDisplayedProduct = parcel.readInt();
            this.mRVAppKey = parcel.readString();
            this.mRVUserId = parcel.readString();
            this.mDisplayedDemandSourceName = parcel.readString();
            this.mInterstitialAppKey = parcel.readString();
            this.mInterstitialUserId = parcel.readString();
            this.mInterstitialExtraParams = this.getMapFromJsonString(parcel.readString());
            this.mOfferwallInitSuccess = (parcel.readByte() != 0);
            this.mOfferwallReportInit = (parcel.readByte() != 0 && b);
            this.mOfferWallExtraParams = this.getMapFromJsonString(parcel.readString());
        }
        catch (Throwable t) {
            this.initialize();
        }
    }
    
    private Map<String, String> getMapFromJsonString(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s2 = keys.next();
                hashMap.put(s2, jsonObject.getString(s2));
            }
            goto Label_0065;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
        catch (Throwable t) {
            t.printStackTrace();
            return hashMap;
        }
    }
    
    private void initialize() {
        this.mShouldRestore = false;
        this.mDisplayedProduct = -1;
        this.mInterstitialReportInit = new ArrayList<String>();
        this.mInterstitialInitSuccess = new ArrayList<String>();
        this.mInterstitialReportLoad = new ArrayList<String>();
        this.mInterstitialLoadSuccess = new ArrayList<String>();
        this.mOfferwallReportInit = true;
        this.mOfferwallInitSuccess = false;
        this.mInterstitialUserId = "";
        this.mInterstitialAppKey = "";
        this.mInterstitialExtraParams = new HashMap<String, String>();
        this.mOfferWallExtraParams = new HashMap<String, String>();
    }
    
    public void adClosed() {
        this.mDisplayedProduct = -1;
    }
    
    public void adOpened(final int mDisplayedProduct) {
        this.mDisplayedProduct = mDisplayedProduct;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getDisplayedDemandSourceName() {
        return this.mDisplayedDemandSourceName;
    }
    
    public int getDisplayedProduct() {
        return this.mDisplayedProduct;
    }
    
    public String getInterstitialAppKey() {
        return this.mInterstitialAppKey;
    }
    
    public Map<String, String> getInterstitialExtraParams() {
        return this.mInterstitialExtraParams;
    }
    
    public String getInterstitialUserId() {
        return this.mInterstitialUserId;
    }
    
    public Map<String, String> getOfferWallExtraParams() {
        return this.mOfferWallExtraParams;
    }
    
    public boolean getOfferwallInitSuccess() {
        return this.mOfferwallInitSuccess;
    }
    
    public String getRVAppKey() {
        return this.mRVAppKey;
    }
    
    public String getRVUserId() {
        return this.mRVUserId;
    }
    
    public boolean isInterstitialInitSuccess(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && this.mInterstitialInitSuccess.indexOf(s) > -1;
    }
    
    public boolean isInterstitialLoadSuccess(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && this.mInterstitialLoadSuccess.indexOf(s) > -1;
    }
    
    public boolean reportInitInterstitial(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && this.mInterstitialReportInit.indexOf(s) > -1;
    }
    
    public boolean reportInitOfferwall() {
        return this.mOfferwallReportInit;
    }
    
    public boolean reportLoadInterstitial(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && this.mInterstitialReportLoad.indexOf(s) > -1;
    }
    
    public void setDisplayedDemandSourceName(final String mDisplayedDemandSourceName) {
        this.mDisplayedDemandSourceName = mDisplayedDemandSourceName;
    }
    
    public void setInterstitialAppKey(final String mInterstitialAppKey) {
        this.mInterstitialAppKey = mInterstitialAppKey;
    }
    
    public void setInterstitialExtraParams(final Map<String, String> mInterstitialExtraParams) {
        this.mInterstitialExtraParams = mInterstitialExtraParams;
    }
    
    public void setInterstitialInitSuccess(final String s, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (!b) {
                this.mInterstitialInitSuccess.remove(s);
                return;
            }
            if (this.mInterstitialInitSuccess.indexOf(s) == -1) {
                this.mInterstitialInitSuccess.add(s);
            }
        }
    }
    
    public void setInterstitialLoadSuccess(final String s, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (!b) {
                this.mInterstitialLoadSuccess.remove(s);
                return;
            }
            if (this.mInterstitialLoadSuccess.indexOf(s) == -1) {
                this.mInterstitialLoadSuccess.add(s);
            }
        }
    }
    
    public void setInterstitialUserId(final String mInterstitialUserId) {
        this.mInterstitialUserId = mInterstitialUserId;
    }
    
    public void setOfferWallExtraParams(final Map<String, String> mOfferWallExtraParams) {
        this.mOfferWallExtraParams = mOfferWallExtraParams;
    }
    
    public void setOfferwallInitSuccess(final boolean mOfferwallInitSuccess) {
        this.mOfferwallInitSuccess = mOfferwallInitSuccess;
    }
    
    public void setOfferwallReportInit(final boolean mOfferwallReportInit) {
        this.mOfferwallReportInit = mOfferwallReportInit;
    }
    
    public void setRVAppKey(final String mrvAppKey) {
        this.mRVAppKey = mrvAppKey;
    }
    
    public void setRVUserId(final String mrvUserId) {
        this.mRVUserId = mrvUserId;
    }
    
    public void setReportInitInterstitial(final String s, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (!b) {
                this.mInterstitialReportInit.remove(s);
                return;
            }
            if (this.mInterstitialReportInit.indexOf(s) == -1) {
                this.mInterstitialReportInit.add(s);
            }
        }
    }
    
    public void setReportLoadInterstitial(final String s, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            if (!b) {
                this.mInterstitialReportLoad.remove(s);
                return;
            }
            if (this.mInterstitialReportLoad.indexOf(s) == -1) {
                this.mInterstitialReportLoad.add(s);
            }
        }
    }
    
    public void setShouldRestore(final boolean mShouldRestore) {
        this.mShouldRestore = mShouldRestore;
    }
    
    public boolean shouldRestore() {
        return this.mShouldRestore;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("shouldRestore:").append(this.mShouldRestore).append(", ");
            sb.append("displayedProduct:").append(this.mDisplayedProduct).append(", ");
            sb.append("ISReportInit:").append(this.mInterstitialReportInit).append(", ");
            sb.append("ISInitSuccess:").append(this.mInterstitialInitSuccess).append(", ");
            sb.append("ISAppKey").append(this.mInterstitialAppKey).append(", ");
            sb.append("ISUserId").append(this.mInterstitialUserId).append(", ");
            sb.append("ISExtraParams").append(this.mInterstitialExtraParams).append(", ");
            sb.append("OWReportInit").append(this.mOfferwallReportInit).append(", ");
            sb.append("OWInitSuccess").append(this.mOfferwallInitSuccess).append(", ");
            sb.append("OWExtraParams").append(this.mOfferWallExtraParams).append(", ");
            return sb.toString();
        }
        catch (Throwable t) {
            return sb.toString();
        }
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        try {
            if (this.mShouldRestore) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeByte((byte)n);
            parcel.writeInt(this.mDisplayedProduct);
            parcel.writeString(this.mRVAppKey);
            parcel.writeString(this.mRVUserId);
            parcel.writeString(this.mDisplayedDemandSourceName);
            parcel.writeString(this.mInterstitialAppKey);
            parcel.writeString(this.mInterstitialUserId);
            parcel.writeString(new JSONObject((Map)this.mInterstitialExtraParams).toString());
            if (this.mOfferwallInitSuccess) {
                n = 1;
            }
            else {
                n = 0;
            }
            parcel.writeByte((byte)n);
            if (this.mOfferwallReportInit) {
                n = n2;
            }
            else {
                n = 0;
            }
            parcel.writeByte((byte)n);
            parcel.writeString(new JSONObject((Map)this.mOfferWallExtraParams).toString());
        }
        catch (Throwable t) {}
    }
}
