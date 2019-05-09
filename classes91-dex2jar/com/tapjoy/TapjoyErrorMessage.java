// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

public class TapjoyErrorMessage
{
    private ErrorType a;
    private String b;
    
    public TapjoyErrorMessage(final ErrorType a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public ErrorType getType() {
        return this.a;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Type=" + this.a.toString());
        sb.append(";Message=" + this.b);
        return sb.toString();
    }
    
    public enum ErrorType
    {
        INTEGRATION_ERROR("INTEGRATION_ERROR", 3), 
        INTERNAL_ERROR("INTERNAL_ERROR", 0), 
        NETWORK_ERROR("NETWORK_ERROR", 4), 
        SDK_ERROR("SDK_ERROR", 1), 
        SERVER_ERROR("SERVER_ERROR", 2);
        
        private ErrorType(final String s, final int n) {
        }
    }
}
