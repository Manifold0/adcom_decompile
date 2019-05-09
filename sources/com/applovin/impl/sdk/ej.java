package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

abstract class ej extends dx {
    /* renamed from: a */
    private final an f2458a;
    /* renamed from: b */
    private AppLovinAdLoadListener f2459b;
    /* renamed from: h */
    private final av f2460h;
    /* renamed from: i */
    private final Collection<Character> f2461i;
    /* renamed from: j */
    private final C1298z f2462j;

    ej(String str, an anVar, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super(str, appLovinSdkImpl);
        if (anVar == null) {
            throw new IllegalArgumentException("No ad specified.");
        }
        this.f2458a = anVar;
        this.f2459b = appLovinAdLoadListener;
        this.f2460h = appLovinSdkImpl.getFileManager();
        this.f2461i = m2754d();
        this.f2462j = new C1298z();
    }

    /* renamed from: a */
    private Uri m2752a(Uri uri, String str) {
        if (uri != null) {
            String uri2 = uri.toString();
            if (AppLovinSdkUtils.isValidString(uri2)) {
                this.e.mo4172d(this.c, "Caching " + str + " image...");
                return m2759b(uri2);
            }
            this.e.mo4172d(this.c, "Failed to cache " + str + " image");
        } else {
            this.e.mo4172d(this.c, "No " + str + " image to cache");
        }
        return null;
    }

    /* renamed from: a */
    private String m2753a(String str, String str2) {
        String replace = str2.replace("/", "_");
        String P = this.f2458a.m1803P();
        if (AppLovinSdkUtils.isValidString(P)) {
            replace = P + replace;
        }
        File a = this.f2460h.m2297a(replace, this.d.getApplicationContext(), true);
        if (a == null) {
            return null;
        }
        if (a.exists()) {
            this.f2462j.m3093b(a.length());
            return "file://" + a.getAbsolutePath();
        }
        return this.f2460h.m2302a(a, new StringBuilder().append(str).append(str2).toString(), Arrays.asList(new String[]{str}), this.f2462j) ? "file://" + a.getAbsolutePath() : null;
    }

    /* renamed from: d */
    private Collection<Character> m2754d() {
        Collection<Character> hashSet = new HashSet();
        for (char valueOf : ((String) this.d.get(ea.aQ)).toCharArray()) {
            hashSet.add(Character.valueOf(valueOf));
        }
        hashSet.add(Character.valueOf('\"'));
        return hashSet;
    }

    /* renamed from: a */
    Uri m2755a(String str) {
        return m2756a(str, this.f2458a.m1802O(), true);
    }

    /* renamed from: a */
    Uri m2756a(String str, List<String> list, boolean z) {
        try {
            if (AppLovinSdkUtils.isValidString(str)) {
                this.e.mo4172d(this.c, "Caching video " + str + "...");
                String a = this.f2460h.m2298a(this.f, str, this.f2458a.m1803P(), list, z, this.f2462j);
                if (AppLovinSdkUtils.isValidString(a)) {
                    File a2 = this.d.getFileManager().m2297a(a, this.f, false);
                    if (a2 != null) {
                        Uri fromFile = Uri.fromFile(a2);
                        if (fromFile != null) {
                            this.e.mo4172d(this.c, "Finish caching video for ad #" + this.f2458a.getAdIdNumber() + ". Updating ad with cachedVideoFilename = " + a);
                            return fromFile;
                        }
                        this.e.mo4173e(this.c, "Unable to create URI from cached video file = " + a2);
                    } else {
                        this.e.mo4173e(this.c, "Unable to cache video = " + str + "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!");
                    }
                } else if (((Boolean) this.d.get(ea.f2388L)).booleanValue()) {
                    this.e.mo4173e(this.c, "Failed to cache video");
                    gd.m2943a(this.f2459b, this.f2458a.mo3997t(), AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES, this.d);
                    this.f2459b = null;
                } else {
                    this.e.mo4173e(this.c, "Failed to cache video, but not failing ad load");
                }
            }
        } catch (Throwable e) {
            this.e.mo4174e(this.c, "Encountered exception while attempting to cache video.", e);
        }
        return null;
    }

    /* renamed from: a */
    String m2757a(String str, List<String> list) {
        return m2764c(str, list, true);
    }

    /* renamed from: a */
    protected void m2758a(C1227q c1227q) {
        C1280g.m2908a(this.f2462j, c1227q, this.d);
    }

    /* renamed from: b */
    Uri m2759b(String str) {
        return m2760b(str, this.f2458a.m1802O(), true);
    }

    /* renamed from: b */
    Uri m2760b(String str, List<String> list, boolean z) {
        try {
            String a = this.f2460h.m2298a(this.f, str, this.f2458a.m1803P(), list, z, this.f2462j);
            if (AppLovinSdkUtils.isValidString(a)) {
                File a2 = this.d.getFileManager().m2297a(a, this.f, false);
                if (a2 != null) {
                    Uri fromFile = Uri.fromFile(a2);
                    if (fromFile != null) {
                        return fromFile;
                    }
                    this.e.mo4173e(this.c, "Unable to extract Uri from image file");
                } else {
                    this.e.mo4173e(this.c, "Unable to retrieve File from cached image filename = " + a);
                }
            }
        } catch (Throwable e) {
            this.e.mo4174e(this.c, "Failed to cache image at url = " + str, e);
        }
        return null;
    }

    /* renamed from: b */
    protected String m2761b(String str, List<String> list) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return str;
        }
        if (((Boolean) this.d.get(ea.f2387K)).booleanValue()) {
            StringBuilder stringBuilder = new StringBuilder(str);
            for (String str2 : list) {
                int i = 0;
                int i2 = 0;
                while (i2 < stringBuilder.length()) {
                    i2 = stringBuilder.indexOf(str2, i);
                    if (i2 == -1) {
                        continue;
                        break;
                    }
                    int length = stringBuilder.length();
                    i = i2;
                    while (!this.f2461i.contains(Character.valueOf(stringBuilder.charAt(i))) && i < length) {
                        i++;
                    }
                    if (i <= i2 || i == length) {
                        this.e.mo4173e(this.c, "Unable to cache resource; ad HTML is invalid.");
                        return str;
                    }
                    String substring = stringBuilder.substring(str2.length() + i2, i);
                    if (AppLovinSdkUtils.isValidString(substring)) {
                        substring = m2753a(str2, substring);
                        if (substring != null) {
                            stringBuilder.replace(i2, i, substring);
                        }
                    } else {
                        this.e.mo4172d(this.c, "Skip caching of non-resource " + substring);
                    }
                }
            }
            return stringBuilder.toString();
        }
        this.e.mo4172d(this.c, "Resource caching is disabled, skipping cache...");
        return str;
    }

    /* renamed from: b */
    void m2762b() {
        this.e.mo4172d(this.c, "Caching mute images...");
        Uri a = m2752a(this.f2458a.al(), "mute");
        if (a != null) {
            this.f2458a.m1817b(a);
        }
        a = m2752a(this.f2458a.am(), "unmute");
        if (a != null) {
            this.f2458a.m1819c(a);
        }
        this.e.mo4172d(this.c, "Ad updated with muteImageFilename = " + this.f2458a.al() + ", unmuteImageFilename = " + this.f2458a.am());
    }

    /* renamed from: c */
    String m2763c(String str) {
        String str2 = null;
        if (AppLovinSdkUtils.isValidString(str)) {
            AtomicReference atomicReference = new AtomicReference(null);
            this.d.getConnectionManager().m2222a(str, new ek(this, atomicReference, str));
            str2 = (String) atomicReference.get();
            if (str2 != null) {
                this.f2462j.m3093b((long) str2.length());
            }
        }
        return str2;
    }

    /* renamed from: c */
    String m2764c(String str, List<String> list, boolean z) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        if (parse == null) {
            this.e.mo4172d(this.c, "Nothing to cache, skipping...");
            return null;
        }
        String lastPathSegment = parse.getLastPathSegment();
        if (AppLovinSdkUtils.isValidString(this.f2458a.m1803P())) {
            lastPathSegment = this.f2458a.m1803P() + lastPathSegment;
        }
        File a = this.f2460h.m2297a(lastPathSegment, this.f, true);
        ByteArrayOutputStream a2 = (a == null || !a.exists()) ? null : this.f2460h.m2295a(a);
        if (a2 == null) {
            a2 = this.f2460h.m2296a(str, (List) list, z);
            if (a2 != null) {
                this.f2460h.m2301a(a2, a);
                this.f2462j.m3091a((long) a2.size());
            }
        } else {
            this.f2462j.m3093b((long) a2.size());
        }
        try {
            return a2.toString("UTF-8");
        } catch (Throwable e) {
            this.e.mo4174e(this.c, "UTF-8 encoding not supported.", e);
            return null;
        } catch (Throwable e2) {
            this.e.mo4174e(this.c, "String resource at " + str + " failed to load.", e2);
            return null;
        }
    }

    /* renamed from: c */
    void m2765c() {
        if (this.f2459b != null) {
            this.d.getLogger().mo4172d(m2482a(), "Rendered new ad:" + this.f2458a);
            this.f2459b.adReceived(this.f2458a);
            this.f2459b = null;
        }
    }
}
