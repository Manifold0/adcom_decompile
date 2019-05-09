// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public enum ActivityKind
{
    ATTRIBUTION, 
    CLICK, 
    EVENT, 
    INFO, 
    REATTRIBUTION, 
    REVENUE, 
    SESSION, 
    UNKNOWN;
    
    public static ActivityKind fromString(final String s) {
        if ("session".equals(s)) {
            return ActivityKind.SESSION;
        }
        if ("event".equals(s)) {
            return ActivityKind.EVENT;
        }
        if ("click".equals(s)) {
            return ActivityKind.CLICK;
        }
        if ("attribution".equals(s)) {
            return ActivityKind.ATTRIBUTION;
        }
        if ("info".equals(s)) {
            return ActivityKind.INFO;
        }
        return ActivityKind.UNKNOWN;
    }
    
    @Override
    public String toString() {
        switch (this) {
            default: {
                return "unknown";
            }
            case SESSION: {
                return "session";
            }
            case EVENT: {
                return "event";
            }
            case CLICK: {
                return "click";
            }
            case ATTRIBUTION: {
                return "attribution";
            }
            case INFO: {
                return "info";
            }
        }
    }
}
