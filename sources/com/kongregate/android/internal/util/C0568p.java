package com.kongregate.android.internal.util;

/* renamed from: com.kongregate.android.internal.util.p */
public enum C0568p {
    SUCCESS,
    FAILURE,
    ERROR_HTTP,
    ERROR_NETWORK,
    ERROR_DATA,
    ERROR_FILESYSTEM,
    ERROR_SESSION,
    ERROR_CANCELED,
    ERROR_CONFLICT,
    ERROR_PARAMS,
    ERROR_OAUTH,
    ERROR_TIMEOUT;

    /* renamed from: a */
    public boolean m790a() {
        return equals(SUCCESS);
    }

    /* renamed from: b */
    public boolean m791b() {
        return !m790a();
    }
}
