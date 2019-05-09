// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

import com.ironsource.sdk.listeners.OnAdProductListener;
import java.util.Map;

public class DemandSource
{
    public static final int INIT_FAILED = 3;
    public static final int INIT_NOT_STARTED = 0;
    public static final int INIT_PENDING = 1;
    public static final int INIT_SUCCEEDED = 2;
    public static final int MEDIATION_STATE_NOT_SET = -1;
    private boolean mAvailabilityState;
    private Map<String, String> mExtraParams;
    private int mInitState;
    private OnAdProductListener mListener;
    private int mMediationState;
    private String mName;
    
    public DemandSource(final String mName, final Map<String, String> mExtraParams, final OnAdProductListener mListener) {
        this.mMediationState = -1;
        this.mName = mName;
        this.mExtraParams = mExtraParams;
        this.mListener = mListener;
        this.mInitState = 0;
        this.mAvailabilityState = false;
    }
    
    public boolean getAvailabilityState() {
        return this.mAvailabilityState;
    }
    
    public int getDemandSourceInitState() {
        return this.mInitState;
    }
    
    public String getDemandSourceName() {
        return this.mName;
    }
    
    public Map<String, String> getExtraParams() {
        return this.mExtraParams;
    }
    
    public OnAdProductListener getListener() {
        return this.mListener;
    }
    
    public int getMediationState() {
        return this.mMediationState;
    }
    
    public boolean isMediationState(final int n) {
        return this.mMediationState == n;
    }
    
    public void setAvailabilityState(final boolean mAvailabilityState) {
        this.mAvailabilityState = mAvailabilityState;
    }
    
    public void setDemandSourceInitState(final int mInitState) {
        synchronized (this) {
            this.mInitState = mInitState;
        }
    }
    
    public void setMediationState(final int mMediationState) {
        this.mMediationState = mMediationState;
    }
}
