// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.HashMap;
import java.util.Map;

public class SessionParameters
{
    Map<String, String> callbackParameters;
    Map<String, String> partnerParameters;
    
    public SessionParameters deepCopy() {
        final SessionParameters sessionParameters = new SessionParameters();
        if (this.callbackParameters != null) {
            sessionParameters.callbackParameters = new HashMap<String, String>(this.callbackParameters);
        }
        if (this.partnerParameters != null) {
            sessionParameters.partnerParameters = new HashMap<String, String>(this.partnerParameters);
        }
        return sessionParameters;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final SessionParameters sessionParameters = (SessionParameters)o;
            if (!Util.equalObject(this.callbackParameters, sessionParameters.callbackParameters)) {
                return false;
            }
            if (!Util.equalObject(this.partnerParameters, sessionParameters.partnerParameters)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return (Util.hashObject(this.callbackParameters) + 629) * 37 + Util.hashObject(this.partnerParameters);
    }
}
