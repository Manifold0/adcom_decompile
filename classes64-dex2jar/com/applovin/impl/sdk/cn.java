// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Locale;
import java.util.ArrayList;
import java.util.Collections;
import com.applovin.mediation.AppLovinMediationAdapter;
import java.util.Iterator;
import android.content.pm.ApplicationInfo;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import com.applovin.sdk.AppLovinLogger;
import java.util.Map;

class cn
{
    private static final Map<String, String> a;
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    private final Object d;
    private final Map<String, cp> e;
    private final Collection<String> f;
    private final Collection<co> g;
    
    static {
        (a = new HashMap<String, String>()).put("chartboost", "com.applovin.mediation.impl.AppLovinChartboostMediationAdapter");
        cn.a.put("facebook", "com.applovin.mediation.impl.AppLovinFacebookMediationAdapter");
        cn.a.put("google", "com.applovin.mediation.impl.AppLovinGoogleMediationAdapter");
        cn.a.put("heyzap", "com.applovin.mediation.impl.AppLovinHeyzapMediationAdapter");
        cn.a.put("inmobi", "com.applovin.mediation.impl.AppLovinInMobiMediationAdapter");
        cn.a.put("mopub", "com.applovin.mediation.impl.AppLovinMoPubMediationAdapter");
        cn.a.put("ironsource", "com.applovin.mediation.impl.AppLovinIronSourceMediationAdapter");
        cn.a.put("vungle", "com.applovin.mediation.impl.AppLovinVungleMediationAdapter");
        cn.a.put("unity", "com.applovin.mediation.impl.AppLovinUnityMediationAdapter");
    }
    
    cn(final AppLovinSdkImpl b) {
        this.d = new Object();
        this.e = new HashMap<String, cp>();
        this.f = new HashSet<String>();
        this.g = new HashSet<co>();
        if (b == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.b = b;
        this.c = b.getLogger();
    }
    
    private cp a(final co co, final Map<String, String> map) {
        if (co == null) {
            throw new IllegalArgumentException("No adapter spec specified");
        }
        // monitorexit(o)
        final Throwable t;
        Label_0183: {
            final String a;
            synchronized (this.d) {
                a = co.a();
                if (this.f.contains(a)) {
                    break Label_0183;
                }
                if (this.e.containsKey(a)) {
                    return this.e.get(a);
                }
                final cp b = this.b(co, map);
                if (b != null) {
                    this.c.d("MediationAdapterManager", "Loaded " + co);
                    this.e.put(a, b);
                    return b;
                }
            }
            this.c.e("MediationAdapterManager", "Failed to load " + t);
            this.f.add(a);
            return null;
        }
        this.c.d("MediationAdapterManager", "Not attempting to load " + t + " due to prior errors");
        // monitorexit(o)
        return null;
    }
    
    private String a(final String s) {
        if (!AppLovinSdkUtils.isValidString(s)) {
            return null;
        }
        try {
            final ApplicationInfo applicationInfo = this.b.getApplicationContext().getPackageManager().getApplicationInfo(this.b.getApplicationContext().getPackageName(), 128);
            final Collection<co> b = co.b(applicationInfo.metaData.getString("applovin.mediation:load"), this.c);
            if (!b.isEmpty()) {
                for (final co co : b) {
                    if (co.b().equalsIgnoreCase(s) && AppLovinSdkUtils.isValidString(co.a())) {
                        return co.a();
                    }
                }
            }
            return applicationInfo.metaData.getString("applovin.mediation." + s + ":" + "class");
        }
        catch (Throwable t) {
            this.c.e("MediationAdapterManager", "Unable to retrieve classname from Android Manifest for adapter name: " + s, t);
            return null;
        }
    }
    
    private void a(final Collection<co> collection, final String s) {
        for (final co co : collection) {
            final cp a = this.a(co.b(), co.a(), null);
            if (a != null) {
                this.c.i("MediationAdapterManager", "Loaded " + s + " adapter: " + a);
            }
        }
    }
    
    private cp b(final co co, final Map<String, String> map) {
        try {
            final Class<?> forName = Class.forName(co.a());
            if (forName == null) {
                this.c.userError("MediationAdapterManager", "No class found for " + co);
                return null;
            }
            final Object instance = forName.newInstance();
            if (!(instance instanceof AppLovinMediationAdapter)) {
                this.c.userError("MediationAdapterManager", co + " error: not an instance of '" + AppLovinMediationAdapter.class.getName() + "'.");
                return null;
            }
            final cp cp = new cp(co.b(), (AppLovinMediationAdapter)instance, this.b);
            cp.a(map);
            if (cp.b()) {
                return cp;
            }
            this.c.userError("MediationAdapterManager", "Failed to initialize " + co);
            return null;
        }
        catch (Throwable t) {
            this.c.userError("MediationAdapterManager", "Failed to load: " + co, t);
            return null;
        }
    }
    
    private Collection<co> e() {
        while (true) {
            String string = null;
        Label_0377:
            while (true) {
                co co = null;
                Label_0333: {
                    ApplicationInfo applicationInfo = null;
                    ArrayList list = null;
                    Label_0161: {
                        try {
                            applicationInfo = this.b.getApplicationContext().getPackageManager().getApplicationInfo(this.b.getApplicationContext().getPackageName(), 128);
                            if (applicationInfo.metaData == null) {
                                return (Collection<co>)Collections.emptyList();
                            }
                            string = applicationInfo.metaData.getString("applovin.mediation:load");
                            final Collection<co> b = com.applovin.impl.sdk.co.b(string, this.c);
                            if (b != null && !b.isEmpty()) {
                                list = new ArrayList<co>(b.size());
                                final Iterator<co> iterator = b.iterator();
                                while (iterator.hasNext()) {
                                    co = iterator.next();
                                    if (!AppLovinSdkUtils.isValidString(co.b())) {
                                        break Label_0333;
                                    }
                                    if (!AppLovinSdkUtils.isValidString(co.a())) {
                                        break Label_0161;
                                    }
                                    list.add(co);
                                }
                                return (Collection<co>)list;
                            }
                            break Label_0377;
                        }
                        catch (Throwable t) {
                            this.c.userError("MediationAdapterManager", "Unable to load applovin.mediation:loadfrom AndroidManifest.xml", t);
                            return (Collection<co>)Collections.emptyList();
                        }
                    }
                    final String string2 = applicationInfo.metaData.getString("applovin.mediation." + co.b() + ":" + "class");
                    if (AppLovinSdkUtils.isValidString(string2)) {
                        list.add(new co(co.b(), string2));
                        continue;
                    }
                    final String s = cn.a.get(co.b());
                    if (AppLovinSdkUtils.isValidString(s)) {
                        list.add(new co(co.b(), s));
                        continue;
                    }
                    this.c.userError("MediationAdapterManager", "Ignored loading of " + co.b() + ": no default adapter class found");
                    continue;
                }
                this.c.userError("MediationAdapterManager", "Ignored loading of adapter with class " + co.a() + ": no name specified");
                continue;
            }
            this.c.d("MediationAdapterManager", "No adapter specs found in: '" + string + "'");
            return (Collection<co>)Collections.emptyList();
        }
    }
    
    private Collection<co> f() {
        return co.b(this.b.get(ef.f), this.c);
    }
    
    private void g() {
        synchronized (this.d) {
            final String a = co.a(this.g);
            // monitorexit(this.d)
            this.b.put(ef.f, a);
        }
    }
    
    cp a(final String s, String a, final Map<String, String> map) {
        if (!AppLovinSdkUtils.isValidString(s)) {
            this.c.e("MediationAdapterManager", "No adapter name provided for " + a + ", not loading the adapter ");
            return null;
        }
        if (AppLovinSdkUtils.isValidString(a)) {
            this.c.d("MediationAdapterManager", "Loading adapter using explicit classname: " + a);
        }
        else if (map != null && map.containsKey("class")) {
            a = map.get("class");
            if (!AppLovinSdkUtils.isValidString(a)) {
                this.c.w("MediationAdapterManager", "Invalid configured classname for '" + s + "'");
                return null;
            }
            this.c.d("MediationAdapterManager", "Loading '" + s + "' adapter using configured classname: " + a);
        }
        else if (!AppLovinSdkUtils.isValidString(a = this.a(s))) {
            a = cn.a.get(s.toLowerCase(Locale.ENGLISH));
            if (!AppLovinSdkUtils.isValidString(a)) {
                this.c.w("MediationAdapterManager", "Unable to find default classname for '" + s + "'");
                return null;
            }
            this.c.d("MediationAdapterManager", "Loading '" + s + "' adapter using resolved default classname: " + a);
        }
        return this.a(new co(s, a), map);
    }
    
    void a() {
        synchronized (this.d) {
            if (this.b.get(ea.dC)) {
                this.a(this.f(), "last used");
            }
            if (this.b.get(ea.dD)) {
                this.a(this.e(), "AndroidManifest");
            }
        }
    }
    
    void a(final cp cp) {
        if (cp == null) {
            return;
        }
        final co co = new co(cp.a(), cp.f());
        synchronized (this.d) {
            if (!this.g.contains(co)) {
                this.g.add(co);
                this.g();
            }
        }
    }
    
    Collection<String> b() {
        synchronized (this.d) {
            return new ArrayList<String>(this.f);
        }
    }
    
    Collection<cp> c() {
        synchronized (this.d) {
            return new ArrayList<cp>(this.e.values());
        }
    }
}
