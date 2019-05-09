// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Iterator;
import java.net.MalformedURLException;
import com.applovin.sdk.AppLovinSdk;
import java.util.List;
import java.util.HashSet;
import java.io.File;
import java.util.Arrays;
import com.applovin.sdk.AppLovinSdkUtils;
import android.net.Uri;
import java.util.Collection;
import com.applovin.sdk.AppLovinAdLoadListener;

abstract class ej extends dx
{
    private final an a;
    private AppLovinAdLoadListener b;
    private final av h;
    private final Collection<Character> i;
    private final z j;
    
    ej(final String s, final an a, final AppLovinAdLoadListener b, final AppLovinSdkImpl appLovinSdkImpl) {
        super(s, appLovinSdkImpl);
        if (a == null) {
            throw new IllegalArgumentException("No ad specified.");
        }
        this.a = a;
        this.b = b;
        this.h = appLovinSdkImpl.getFileManager();
        this.i = this.d();
        this.j = new z();
    }
    
    private Uri a(final Uri uri, final String s) {
        if (uri != null) {
            final String string = uri.toString();
            if (AppLovinSdkUtils.isValidString(string)) {
                this.e.d(this.c, "Caching " + s + " image...");
                return this.b(string);
            }
            this.e.d(this.c, "Failed to cache " + s + " image");
        }
        else {
            this.e.d(this.c, "No " + s + " image to cache");
        }
        return null;
    }
    
    private String a(final String s, String string) {
        final String replace = string.replace("/", "_");
        final String p2 = this.a.P();
        String string2 = replace;
        if (AppLovinSdkUtils.isValidString(p2)) {
            string2 = p2 + replace;
        }
        final File a = this.h.a(string2, this.d.getApplicationContext(), true);
        if (a == null) {
            return null;
        }
        if (a.exists()) {
            this.j.b(a.length());
            return "file://" + a.getAbsolutePath();
        }
        string = s + string;
        if (this.h.a(a, string, Arrays.asList(s), this.j)) {
            return "file://" + a.getAbsolutePath();
        }
        return null;
    }
    
    private Collection<Character> d() {
        final HashSet<Character> set = new HashSet<Character>();
        final char[] charArray = this.d.get(ea.aQ).toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            set.add(charArray[i]);
        }
        set.add('\"');
        return set;
    }
    
    Uri a(final String s) {
        return this.a(s, this.a.O(), true);
    }
    
    Uri a(final String s, final List<String> list, final boolean b) {
        try {
            if (!AppLovinSdkUtils.isValidString(s)) {
                return null;
            }
            this.e.d(this.c, "Caching video " + s + "...");
            final String a = this.h.a(this.f, s, this.a.P(), list, b, this.j);
            if (AppLovinSdkUtils.isValidString(a)) {
                final File a2 = this.d.getFileManager().a(a, this.f, false);
                if (a2 == null) {
                    this.e.e(this.c, "Unable to cache video = " + s + "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!");
                    return null;
                }
                final Uri fromFile = Uri.fromFile(a2);
                if (fromFile != null) {
                    this.e.d(this.c, "Finish caching video for ad #" + this.a.getAdIdNumber() + ". Updating ad with cachedVideoFilename = " + a);
                    return fromFile;
                }
                this.e.e(this.c, "Unable to create URI from cached video file = " + a2);
                return null;
            }
        }
        catch (Exception ex) {
            this.e.e(this.c, "Encountered exception while attempting to cache video.", ex);
            return null;
        }
        if (this.d.get(ea.L)) {
            this.e.e(this.c, "Failed to cache video");
            gd.a(this.b, this.a.t(), -202, this.d);
            this.b = null;
        }
        else {
            this.e.e(this.c, "Failed to cache video, but not failing ad load");
        }
        return null;
    }
    
    String a(final String s, final List<String> list) {
        return this.c(s, list, true);
    }
    
    protected void a(final q q) {
        com.applovin.impl.sdk.g.a(this.j, q, this.d);
    }
    
    Uri b(final String s) {
        return this.b(s, this.a.O(), true);
    }
    
    Uri b(final String s, final List<String> list, final boolean b) {
        try {
            final String a = this.h.a(this.f, s, this.a.P(), list, b, this.j);
            if (AppLovinSdkUtils.isValidString(a)) {
                final File a2 = this.d.getFileManager().a(a, this.f, false);
                if (a2 != null) {
                    final Uri fromFile = Uri.fromFile(a2);
                    if (fromFile != null) {
                        return fromFile;
                    }
                    this.e.e(this.c, "Unable to extract Uri from image file");
                }
                else {
                    this.e.e(this.c, "Unable to retrieve File from cached image filename = " + a);
                }
            }
        }
        catch (MalformedURLException ex) {
            this.e.e(this.c, "Failed to cache image at url = " + s, ex);
        }
        return null;
    }
    
    protected String b(final String s, final List<String> list) {
        if (!AppLovinSdkUtils.isValidString(s)) {
            return s;
        }
        if (!this.d.get(ea.K)) {
            this.e.d(this.c, "Resource caching is disabled, skipping cache...");
            return s;
        }
        final StringBuilder sb = new StringBuilder(s);
        for (final String s2 : list) {
            int n = 0;
            int i = 0;
            while (i < sb.length()) {
                final int index = sb.indexOf(s2, n);
                if (index == -1) {
                    break;
                }
                int length;
                int n2;
                for (length = sb.length(), n2 = index; !this.i.contains(sb.charAt(n2)) && n2 < length; ++n2) {}
                if (n2 <= index || n2 == length) {
                    this.e.e(this.c, "Unable to cache resource; ad HTML is invalid.");
                    return s;
                }
                final String substring = sb.substring(s2.length() + index, n2);
                if (AppLovinSdkUtils.isValidString(substring)) {
                    final String a = this.a(s2, substring);
                    n = n2;
                    i = index;
                    if (a == null) {
                        continue;
                    }
                    sb.replace(index, n2, a);
                    n = n2;
                    i = index;
                }
                else {
                    this.e.d(this.c, "Skip caching of non-resource " + substring);
                    n = n2;
                    i = index;
                }
            }
        }
        return sb.toString();
    }
    
    void b() {
        this.e.d(this.c, "Caching mute images...");
        final Uri a = this.a(this.a.al(), "mute");
        if (a != null) {
            this.a.b(a);
        }
        final Uri a2 = this.a(this.a.am(), "unmute");
        if (a2 != null) {
            this.a.c(a2);
        }
        this.e.d(this.c, "Ad updated with muteImageFilename = " + this.a.al() + ", unmuteImageFilename = " + this.a.am());
    }
    
    String c(String s) {
        final String s2 = null;
        if (!AppLovinSdkUtils.isValidString(s)) {
            s = s2;
        }
        else {
            final AtomicReference<String> atomicReference = new AtomicReference<String>(null);
            this.d.getConnectionManager().a(s, new ek(this, atomicReference, s));
            final String s3 = atomicReference.get();
            if ((s = s3) != null) {
                this.j.b(s3.length());
                return s3;
            }
        }
        return s;
    }
    
    String c(final String s, final List<String> list, final boolean b) {
        if (AppLovinSdkUtils.isValidString(s)) {
            final Uri parse = Uri.parse(s);
            if (parse == null) {
                this.e.d(this.c, "Nothing to cache, skipping...");
                return null;
            }
            String s2 = parse.getLastPathSegment();
            if (AppLovinSdkUtils.isValidString(this.a.P())) {
                s2 = this.a.P() + s2;
            }
            final File a = this.h.a(s2, this.f, true);
            Label_0187: {
                if (a == null || !a.exists()) {
                    break Label_0187;
                }
                ByteArrayOutputStream a2 = this.h.a(a);
                while (true) {
                    Label_0193: {
                        if (a2 != null) {
                            break Label_0193;
                        }
                        final ByteArrayOutputStream a3 = this.h.a(s, list, b);
                        if ((a2 = a3) != null) {
                            this.h.a(a3, a);
                            this.j.a(a3.size());
                            a2 = a3;
                        }
                        try {
                            return a2.toString("UTF-8");
                            a2 = null;
                            continue;
                            this.j.b(a2.size());
                            return a2.toString("UTF-8");
                        }
                        catch (UnsupportedEncodingException ex) {
                            this.e.e(this.c, "UTF-8 encoding not supported.", ex);
                            return null;
                        }
                        catch (Throwable t) {
                            this.e.e(this.c, "String resource at " + s + " failed to load.", t);
                            return null;
                        }
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    void c() {
        if (this.b != null) {
            this.d.getLogger().d(this.a(), "Rendered new ad:" + this.a);
            this.b.adReceived(this.a);
            this.b = null;
        }
    }
}
