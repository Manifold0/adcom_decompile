// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import java.util.Collection;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import android.util.Pair;
import java.util.Vector;

public class IronSourceSegment
{
    public static final String AGE = "age";
    public static final String GENDER = "gen";
    public static final String IAPT = "iapt";
    public static final String LEVEL = "lvl";
    public static final String PAYING = "pay";
    private static final String SEGMENT_NAME = "segName";
    public static final String USER_CREATION_DATE = "ucd";
    private final String CUSTOM;
    private final int MAX_CUSTOMS;
    private double MAX_IAPT;
    private int MAX_LEVEL;
    private int mAge;
    private Vector<Pair<String, String>> mCustoms;
    private String mGender;
    private double mIapt;
    private AtomicBoolean mIsPaying;
    private int mLevel;
    private String mSegmentName;
    private long mUcd;
    
    public IronSourceSegment() {
        this.MAX_LEVEL = 999999;
        this.MAX_IAPT = 999999.99;
        this.CUSTOM = "custom";
        this.MAX_CUSTOMS = 5;
        this.mAge = -1;
        this.mLevel = -1;
        this.mIsPaying = null;
        this.mIapt = -1.0;
        this.mUcd = 0L;
        this.mCustoms = new Vector<Pair<String, String>>();
    }
    
    private boolean validateAlphanumeric(final String s) {
        return s != null && s.matches("^[a-zA-Z0-9]*$");
    }
    
    private boolean validateLength(final String s, final int n, final int n2) {
        return s != null && s.length() >= n && s.length() <= n2;
    }
    
    public int getAge() {
        return this.mAge;
    }
    
    public String getGender() {
        return this.mGender;
    }
    
    public double getIapt() {
        return this.mIapt;
    }
    
    public AtomicBoolean getIsPaying() {
        return this.mIsPaying;
    }
    
    public int getLevel() {
        return this.mLevel;
    }
    
    Vector<Pair<String, String>> getSegmentData() {
        final Vector<Pair> vector = (Vector<Pair>)new Vector<Pair<String, String>>();
        if (this.mAge != -1) {
            vector.add(new Pair((Object)"age", (Object)(this.mAge + "")));
        }
        if (!TextUtils.isEmpty((CharSequence)this.mGender)) {
            vector.add(new Pair((Object)"gen", (Object)this.mGender));
        }
        if (this.mLevel != -1) {
            vector.add(new Pair((Object)"lvl", (Object)(this.mLevel + "")));
        }
        if (this.mIsPaying != null) {
            vector.add(new Pair((Object)"pay", (Object)(this.mIsPaying + "")));
        }
        if (this.mIapt != -1.0) {
            vector.add(new Pair((Object)"iapt", (Object)(this.mIapt + "")));
        }
        if (this.mUcd != 0L) {
            vector.add(new Pair((Object)"ucd", (Object)(this.mUcd + "")));
        }
        if (!TextUtils.isEmpty((CharSequence)this.mSegmentName)) {
            vector.add(new Pair((Object)"segName", (Object)this.mSegmentName));
        }
        vector.addAll(this.mCustoms);
        return (Vector<Pair<String, String>>)vector;
    }
    
    public String getSegmentName() {
        return this.mSegmentName;
    }
    
    public long getUcd() {
        return this.mUcd;
    }
    
    public void setAge(final int mAge) {
        if (mAge > 0 && mAge <= 199) {
            this.mAge = mAge;
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setAge( " + mAge + " ) age must be between 1-199", 2);
    }
    
    public void setCustom(String string, final String s) {
        try {
            if (this.validateAlphanumeric(string) && this.validateAlphanumeric(s) && this.validateLength(string, 1, 32) && this.validateLength(s, 1, 32)) {
                string = "custom_" + string;
                if (this.mCustoms.size() < 5) {
                    this.mCustoms.add((Pair<String, String>)new Pair((Object)string, (Object)s));
                    return;
                }
                this.mCustoms.remove(0);
                this.mCustoms.add((Pair<String, String>)new Pair((Object)string, (Object)s));
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setCustom( " + string + " , " + s + " ) key and value must be alphanumeric and 1-32 in length", 2);
    }
    
    public void setGender(final String mGender) {
        if (!TextUtils.isEmpty((CharSequence)mGender) && (mGender.toLowerCase().equals("male") || mGender.toLowerCase().equals("female"))) {
            this.mGender = mGender;
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setGender( " + mGender + " ) is invalid", 2);
    }
    
    public void setIAPTotal(final double n) {
        if (n > 0.0 && n < this.MAX_IAPT) {
            this.mIapt = Math.floor(n * 100.0) / 100.0;
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setIAPTotal( " + n + " ) iapt must be between 0-" + this.MAX_IAPT, 2);
    }
    
    public void setIsPaying(final boolean b) {
        if (this.mIsPaying == null) {
            this.mIsPaying = new AtomicBoolean();
        }
        this.mIsPaying.set(b);
    }
    
    public void setLevel(final int mLevel) {
        if (mLevel > 0 && mLevel < this.MAX_LEVEL) {
            this.mLevel = mLevel;
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setLevel( " + mLevel + " ) level must be between 1-" + this.MAX_LEVEL, 2);
    }
    
    public void setSegmentName(final String mSegmentName) {
        if (this.validateAlphanumeric(mSegmentName) && this.validateLength(mSegmentName, 1, 32)) {
            this.mSegmentName = mSegmentName;
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setSegmentName( " + mSegmentName + " ) segment name must be alphanumeric and 1-32 in length", 2);
    }
    
    public void setUserCreationDate(final long mUcd) {
        if (mUcd > 0L) {
            this.mUcd = mUcd;
            return;
        }
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, "setUserCreationDate( " + mUcd + " ) is an invalid timestamp", 2);
    }
}
