package com.adjust.sdk;

import java.util.HashMap;
import java.util.Map;

public class SessionParameters {
    Map<String, String> callbackParameters;
    Map<String, String> partnerParameters;

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        SessionParameters otherSessionParameters = (SessionParameters) other;
        if (!Util.equalObject(this.callbackParameters, otherSessionParameters.callbackParameters)) {
            return false;
        }
        if (Util.equalObject(this.partnerParameters, otherSessionParameters.partnerParameters)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((Util.hashObject(this.callbackParameters) + 629) * 37) + Util.hashObject(this.partnerParameters);
    }

    public SessionParameters deepCopy() {
        SessionParameters newSessionParameters = new SessionParameters();
        if (this.callbackParameters != null) {
            newSessionParameters.callbackParameters = new HashMap(this.callbackParameters);
        }
        if (this.partnerParameters != null) {
            newSessionParameters.partnerParameters = new HashMap(this.partnerParameters);
        }
        return newSessionParameters;
    }
}
