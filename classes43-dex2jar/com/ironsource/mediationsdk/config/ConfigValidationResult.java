// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.config;

import com.ironsource.mediationsdk.logger.IronSourceError;

public class ConfigValidationResult
{
    private IronSourceError mIronSourceError;
    private boolean mIsValid;
    
    public ConfigValidationResult() {
        this.mIsValid = true;
        this.mIronSourceError = null;
    }
    
    public IronSourceError getIronSourceError() {
        return this.mIronSourceError;
    }
    
    public boolean isValid() {
        return this.mIsValid;
    }
    
    public void setInvalid(final IronSourceError mIronSourceError) {
        this.mIsValid = false;
        this.mIronSourceError = mIronSourceError;
    }
    
    public void setValid() {
        this.mIsValid = true;
        this.mIronSourceError = null;
    }
    
    @Override
    public String toString() {
        if (this.isValid()) {
            return "valid:" + this.mIsValid;
        }
        return "valid:" + this.mIsValid + ", IronSourceError:" + this.mIronSourceError;
    }
}
