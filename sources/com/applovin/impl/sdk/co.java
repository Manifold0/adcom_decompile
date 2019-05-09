package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

class co {
    /* renamed from: a */
    private final String f2233a;
    /* renamed from: b */
    private final String f2234b;

    co(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No classname specified");
        } else {
            this.f2233a = str.toLowerCase(Locale.ENGLISH);
            this.f2234b = str2;
        }
    }

    /* renamed from: a */
    static co m2450a(String str, AppLovinLogger appLovinLogger) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        if (str.contains(":")) {
            try {
                int indexOf = str.indexOf(58);
                if (indexOf > 0 && indexOf < str.length() - 1) {
                    return new co(str.substring(0, indexOf).toLowerCase(Locale.ENGLISH), str.substring(indexOf + 1, str.length()));
                }
                appLovinLogger.userError("MediationAdapterManager", "Unable to parse config '" + str + "': malformed string");
                return null;
            } catch (Throwable th) {
                appLovinLogger.userError("MediationAdapterManager", "Unable to parse config '" + str + "'", th);
                return null;
            }
        }
        String toLowerCase = str.toLowerCase(Locale.ENGLISH);
        if (cn.f2226a.containsKey(toLowerCase)) {
            return new co(toLowerCase, (String) cn.f2226a.get(toLowerCase));
        }
        appLovinLogger.userError("MediationAdapterManager", "Unable to create config '" + str + "': unknown name");
        return null;
    }

    /* renamed from: a */
    static String m2451a(Collection<co> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (co c : collection) {
            stringBuilder.append(c.m2455c());
            stringBuilder.append(',');
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /* renamed from: b */
    static Collection<co> m2452b(String str, AppLovinLogger appLovinLogger) {
        if (appLovinLogger == null) {
            throw new IllegalArgumentException("No logger specified");
        }
        Collection arrayList = new ArrayList();
        for (String a : aa.m2193a(str)) {
            co a2 = m2450a(a, appLovinLogger);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    String m2453a() {
        return this.f2234b;
    }

    /* renamed from: b */
    String m2454b() {
        return this.f2233a;
    }

    /* renamed from: c */
    String m2455c() {
        return this.f2233a + ":" + this.f2234b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        co coVar = (co) obj;
        return (this.f2233a == null ? coVar.f2233a != null : !this.f2233a.equals(coVar.f2233a)) ? false : this.f2234b != null ? this.f2234b.equals(coVar.f2234b) : coVar.f2234b == null;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f2233a != null ? this.f2233a.hashCode() : 0) * 31;
        if (this.f2234b != null) {
            i = this.f2234b.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "[Adapter Spec: " + m2455c() + RequestParameters.RIGHT_BRACKETS;
    }
}
