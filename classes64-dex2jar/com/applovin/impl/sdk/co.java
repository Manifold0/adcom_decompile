// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinLogger;
import java.util.Locale;

class co
{
    private final String a;
    private final String b;
    
    co(final String s, final String b) {
        if (s == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No classname specified");
        }
        this.a = s.toLowerCase(Locale.ENGLISH);
        this.b = b;
    }
    
    static co a(final String s, final AppLovinLogger appLovinLogger) {
        if (!AppLovinSdkUtils.isValidString(s)) {
            return null;
        }
        if (s.contains(":")) {
            try {
                final int index = s.indexOf(58);
                if (index > 0 && index < s.length() - 1) {
                    return new co(s.substring(0, index).toLowerCase(Locale.ENGLISH), s.substring(index + 1, s.length()));
                }
                appLovinLogger.userError("MediationAdapterManager", "Unable to parse config '" + s + "': malformed string");
                return null;
            }
            catch (Throwable t) {
                appLovinLogger.userError("MediationAdapterManager", "Unable to parse config '" + s + "'", t);
                return null;
            }
        }
        final String lowerCase = s.toLowerCase(Locale.ENGLISH);
        if (cn.a.containsKey(lowerCase)) {
            return new co(lowerCase, (String)cn.a.get(lowerCase));
        }
        appLovinLogger.userError("MediationAdapterManager", "Unable to create config '" + s + "': unknown name");
        return null;
    }
    
    static String a(final Collection<co> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<co> iterator = collection.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().c());
            sb.append(',');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    static Collection<co> b(final String s, final AppLovinLogger appLovinLogger) {
        if (appLovinLogger == null) {
            throw new IllegalArgumentException("No logger specified");
        }
        final ArrayList<co> list = new ArrayList<co>();
        final Iterator<String> iterator = aa.a(s).iterator();
        while (iterator.hasNext()) {
            final co a = a(iterator.next(), appLovinLogger);
            if (a != null) {
                list.add(a);
            }
        }
        return list;
    }
    
    String a() {
        return this.b;
    }
    
    String b() {
        return this.a;
    }
    
    String c() {
        return this.a + ":" + this.b;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final co co = (co)o;
            Label_0059: {
                if (this.a != null) {
                    if (this.a.equals(co.a)) {
                        break Label_0059;
                    }
                }
                else if (co.a == null) {
                    break Label_0059;
                }
                return false;
            }
            if (this.b != null) {
                return this.b.equals(co.b);
            }
            if (co.b != null) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.a != null) {
            hashCode2 = this.a.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        if (this.b != null) {
            hashCode = this.b.hashCode();
        }
        return hashCode2 * 31 + hashCode;
    }
    
    @Override
    public String toString() {
        return "[Adapter Spec: " + this.c() + "]";
    }
}
