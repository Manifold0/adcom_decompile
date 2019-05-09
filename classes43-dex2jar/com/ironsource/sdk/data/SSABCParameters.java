// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

public class SSABCParameters extends SSAObj
{
    private String CONNECTION_RETRIES;
    private String mConnectionRetries;
    
    public SSABCParameters() {
        this.CONNECTION_RETRIES = "connectionRetries";
    }
    
    public SSABCParameters(final String s) {
        super(s);
        this.CONNECTION_RETRIES = "connectionRetries";
        if (this.containsKey(this.CONNECTION_RETRIES)) {
            this.setConnectionRetries(this.getString(this.CONNECTION_RETRIES));
        }
    }
    
    public String getConnectionRetries() {
        return this.mConnectionRetries;
    }
    
    public void setConnectionRetries(final String mConnectionRetries) {
        this.mConnectionRetries = mConnectionRetries;
    }
}
