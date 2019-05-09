package com.adjust.sdk;

import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.TJAdUnitConstants.String;

public enum ActivityKind {
    UNKNOWN,
    SESSION,
    EVENT,
    CLICK,
    ATTRIBUTION,
    REVENUE,
    REATTRIBUTION,
    INFO;

    public static ActivityKind fromString(String string) {
        if (C0578a.f789b.equals(string)) {
            return SESSION;
        }
        if ("event".equals(string)) {
            return EVENT;
        }
        if ("click".equals(string)) {
            return CLICK;
        }
        if ("attribution".equals(string)) {
            return ATTRIBUTION;
        }
        if (String.VIDEO_INFO.equals(string)) {
            return INFO;
        }
        return UNKNOWN;
    }

    public String toString() {
        switch (this) {
            case SESSION:
                return C0578a.f789b;
            case EVENT:
                return "event";
            case CLICK:
                return "click";
            case ATTRIBUTION:
                return "attribution";
            case INFO:
                return String.VIDEO_INFO;
            default:
                return "unknown";
        }
    }
}
