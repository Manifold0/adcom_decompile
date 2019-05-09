// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdkSettings;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;
import android.content.Context;
import com.applovin.sdk.AppLovinLogger;

class ed
{
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private final Context c;
    private final SharedPreferences d;
    private final Object[] e;
    
    ed(final AppLovinSdkImpl a) {
        this.e = new Object[ea.b()];
        this.a = a;
        this.b = a.getLogger();
        this.c = a.getApplicationContext();
        this.d = this.c.getSharedPreferences("com.applovin.sdk.1", 0);
    }
    
    private static Object a(final String s, final JSONObject jsonObject, final Object o) throws JSONException {
        if (o instanceof Boolean) {
            return jsonObject.getBoolean(s);
        }
        if (o instanceof Float) {
            return (float)jsonObject.getDouble(s);
        }
        if (o instanceof Integer) {
            return jsonObject.getInt(s);
        }
        if (o instanceof Long) {
            return jsonObject.getLong(s);
        }
        if (o instanceof String) {
            return jsonObject.getString(s);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + o.getClass());
    }
    
    private String d() {
        return "com.applovin.sdk." + gd.a(this.a.getSdkKey()) + ".";
    }
    
    public ec<?> a(final String s, final ec<?> ec) {
        for (final ec<?> ec2 : ea.a()) {
            if (ec2.b().equals(s)) {
                return ec2;
            }
        }
        return ec;
    }
    
    public <T> T a(final ec<T> ec) {
        if (ec == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        final Object[] e = this.e;
        // monitorenter(e)
        try {
            try {
                final Object o = this.e[ec.a()];
                if (o != null) {
                    return ec.a(o);
                }
                return ec.c();
            }
            finally {
            }
            // monitorexit(e)
        }
        catch (Throwable t) {
            this.a.getLogger().e("SettingsManager", "Unable to retrieve value for setting " + ec.b() + "; using default...");
            // monitorexit(e)
            return ec.c();
        }
    }
    
    void a() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.b.i("SettingsManager", "Saving settings with the application...");
        final String d = this.d();
        synchronized (this.e) {
            for (final ec<?> ec : ea.a()) {
                final Object o = this.e[ec.a()];
                if (o != null) {
                    this.a.put(d + ec.b(), o, this.d);
                }
            }
        }
        // monitorexit(array)
        this.b.d("SettingsManager", "Settings saved with the application.");
    }
    
    public <T> void a(final ec<?> ec, final Object o) {
        if (ec == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        if (o == null) {
            throw new IllegalArgumentException("No new value specified");
        }
        synchronized (this.e) {
            this.e[ec.a()] = o;
            // monitorexit(this.e)
            this.b.d("SettingsManager", "Setting update: " + ec.b() + " set to \"" + o + "\"");
        }
    }
    
    void a(final AppLovinSdkSettings appLovinSdkSettings) {
        long max = 0L;
        boolean b = false;
        boolean b2 = false;
        this.b.i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings == null) {
            return;
        }
        // monitorexit(array)
        while (true) {
            Label_0671: {
                Label_0557: {
                    boolean b4 = false;
                    int n2 = 0;
                Label_0285_Outer:
                    while (true) {
                        while (true) {
                            boolean b3 = false;
                            Label_0646: {
                                String s = null;
                            Label_0410:
                                while (true) {
                                    Object o = null;
                                    Label_0381: {
                                        synchronized (this.e) {
                                            if (this.a.get(ea.l)) {
                                                this.e[ea.l.a()] = appLovinSdkSettings.isVerboseLoggingEnabled();
                                            }
                                            final long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
                                            if (bannerAdRefreshSeconds >= 0L) {
                                                if (bannerAdRefreshSeconds > 0L) {
                                                    max = Math.max(30L, bannerAdRefreshSeconds);
                                                }
                                                this.e[ea.D.a()] = max;
                                                this.e[ea.C.a()] = true;
                                            }
                                            else if (bannerAdRefreshSeconds == -1L) {
                                                this.e[ea.C.a()] = false;
                                            }
                                            if (this.a.get(ea.d)) {
                                                s = (String)(o = appLovinSdkSettings.getAutoPreloadSizes());
                                                if (!AppLovinSdkUtils.isValidString(s)) {
                                                    o = "NONE";
                                                }
                                                if (!((String)o).equals("NONE")) {
                                                    break Label_0381;
                                                }
                                                this.e[ea.N.a()] = "";
                                                this.e[ea.O.a()] = "";
                                            }
                                            if (!this.a.get(ea.e)) {
                                                break Label_0557;
                                            }
                                            s = (String)(o = appLovinSdkSettings.getAutoPreloadTypes());
                                            if (!AppLovinSdkUtils.isValidString(s)) {
                                                o = "NONE";
                                            }
                                            if ("NONE".equals(o)) {
                                                break Label_0671;
                                            }
                                            o = aa.a((String)o).iterator();
                                            b3 = false;
                                            int n = 0;
                                            b = b2;
                                            b4 = b3;
                                            n2 = n;
                                            if (!((Iterator)o).hasNext()) {
                                                break;
                                            }
                                            s = ((Iterator<String>)o).next();
                                            if (s.equals(AppLovinAdType.REGULAR.getLabel())) {
                                                b = b3;
                                                n = 1;
                                                b3 = b2;
                                                b2 = b;
                                                break Label_0646;
                                            }
                                            break Label_0410;
                                        }
                                    }
                                    this.e[ea.N.a()] = o;
                                    this.e[ea.O.a()] = o;
                                    continue Label_0285_Outer;
                                }
                                if (!s.equals(AppLovinAdType.INCENTIVIZED.getLabel()) && !s.contains("INCENT") && !s.contains("REWARD")) {
                                    if (s.equals(AppLovinAdType.NATIVE.getLabel())) {
                                        final boolean b5 = true;
                                        b2 = b3;
                                        b3 = b5;
                                    }
                                    else {
                                        final boolean b6 = b2;
                                        b2 = b3;
                                        b3 = b6;
                                    }
                                }
                                else {
                                    b3 = b2;
                                    b2 = true;
                                }
                            }
                            b = b2;
                            b2 = b3;
                            b3 = b;
                            continue;
                        }
                    }
                    if (n2 == 0) {
                        this.e[ea.N.a()] = "";
                        this.e[ea.O.a()] = "";
                    }
                    this.e[ea.P.a()] = b4;
                    this.e[ea.Q.a()] = b4;
                    this.e[ea.bt.a()] = b;
                }
                if (appLovinSdkSettings instanceof bt) {
                    for (final Map.Entry<ec<?>, Object> entry : ((bt)appLovinSdkSettings).b().entrySet()) {
                        this.e[entry.getKey().a()] = entry.getValue();
                    }
                }
                return;
            }
            boolean b4 = false;
            int n2 = 0;
            continue;
        }
    }
    
    void a(final JSONObject jsonObject) {
        this.b.d("SettingsManager", "Loading settings from JSON array...");
        final Object[] e = this.e;
        // monitorenter(e)
        try {
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                Object a = keys.next();
                if (a != null && ((String)a).length() > 0) {
                    try {
                        final ec<?> a2 = this.a((String)a, null);
                        if (a2 == null) {
                            goto Label_0181;
                        }
                        a = a((String)a, jsonObject, a2.c());
                        this.e[a2.a()] = a;
                        this.b.d("SettingsManager", "Setting update: " + a2.b() + " set to \"" + a + "\"");
                    }
                    catch (JSONException ex) {
                        this.b.e("SettingsManager", "Unable to parse JSON settings array", (Throwable)ex);
                    }
                    catch (Throwable t) {
                        this.b.e("SettingsManager", "Unable to convert setting object ", t);
                    }
                }
            }
        }
        finally {}
    }
    // monitorexit(e)
    
    public List<String> b(final ec<String> ec) {
        return aa.a(this.a(ec));
    }
    
    void b() {
        if (this.c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        while (true) {
            this.b.i("SettingsManager", "Loading settings saved with the application...");
            final String d = this.d();
            while (true) {
                String string = null;
                Label_0197: {
                    synchronized (this.e) {
                        for (final ec<Object> ec : ea.a()) {
                            try {
                                string = d + ec.b();
                                final Object c = ec.c();
                                final Object value = this.a.get(string, c, c.getClass(), this.d);
                                if (value == null) {
                                    break Label_0197;
                                }
                                this.e[ec.a()] = value;
                            }
                            catch (Exception string) {
                                this.b.e("SettingsManager", "Unable to load \"" + ec.b() + "\"", (Throwable)string);
                            }
                        }
                        break;
                    }
                }
                this.b.e("SettingsManager", "Unable to find value for setting: " + string);
                continue;
            }
        }
    }
    // monitorexit(array)
    
    void c() {
        synchronized (this.e) {
            Arrays.fill(this.e, null);
            // monitorexit(this.e)
            this.a.clear(this.d);
        }
    }
}
