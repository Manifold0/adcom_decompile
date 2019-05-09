// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdjustEvent
{
    private static ILogger logger;
    Map<String, String> callbackParameters;
    String currency;
    String eventToken;
    String orderId;
    Map<String, String> partnerParameters;
    Double revenue;
    
    static {
        AdjustEvent.logger = AdjustFactory.getLogger();
    }
    
    public AdjustEvent(final String eventToken) {
        if (!checkEventToken(eventToken, AdjustEvent.logger)) {
            return;
        }
        this.eventToken = eventToken;
    }
    
    private static boolean checkEventToken(final String s, final ILogger logger) {
        if (s == null) {
            logger.error("Missing Event Token", new Object[0]);
            return false;
        }
        if (s.length() != 6) {
            logger.error("Malformed Event Token '%s'", s);
            return false;
        }
        return true;
    }
    
    private boolean checkRevenue(final Double n, final String s) {
        if (n != null) {
            if (n < 0.0) {
                AdjustEvent.logger.error("Invalid amount %.5f", n);
                return false;
            }
            if (s == null) {
                AdjustEvent.logger.error("Currency must be set with revenue", new Object[0]);
                return false;
            }
            if (s.equals("")) {
                AdjustEvent.logger.error("Currency is empty", new Object[0]);
                return false;
            }
        }
        else if (s != null) {
            AdjustEvent.logger.error("Revenue must be set with currency", new Object[0]);
            return false;
        }
        return true;
    }
    
    public void addCallbackParameter(final String s, final String s2) {
        if (Util.isValidParameter(s, "key", "Callback") && Util.isValidParameter(s2, "value", "Callback")) {
            if (this.callbackParameters == null) {
                this.callbackParameters = new LinkedHashMap<String, String>();
            }
            if (this.callbackParameters.put(s, s2) != null) {
                AdjustEvent.logger.warn("Key %s was overwritten", s);
            }
        }
    }
    
    public void addPartnerParameter(final String s, final String s2) {
        if (Util.isValidParameter(s, "key", "Partner") && Util.isValidParameter(s2, "value", "Partner")) {
            if (this.partnerParameters == null) {
                this.partnerParameters = new LinkedHashMap<String, String>();
            }
            if (this.partnerParameters.put(s, s2) != null) {
                AdjustEvent.logger.warn("Key %s was overwritten", s);
            }
        }
    }
    
    public boolean isValid() {
        return this.eventToken != null;
    }
    
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }
    
    public void setRevenue(final double n, final String currency) {
        if (!this.checkRevenue(n, currency)) {
            return;
        }
        this.revenue = n;
        this.currency = currency;
    }
}
