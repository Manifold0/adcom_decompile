// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.utils;

import java.util.TimerTask;
import java.util.Iterator;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import java.util.GregorianCalendar;
import java.util.Random;
import com.ironsource.mediationsdk.AbstractSmash;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.HashMap;
import java.util.Timer;
import java.util.Map;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import android.content.Context;

public class DailyCappingManager
{
    private static final int RAND_MINUTES = 10;
    private String mAdUnitName;
    private Context mContext;
    private DailyCappingListener mListener;
    private IronSourceLoggerManager mLogger;
    private Map<String, Integer> mSmashIdToCounter;
    private Map<String, String> mSmashIdToCounterDate;
    private Map<String, Integer> mSmashIdToMaxShowsPerDay;
    private Timer mTimer;
    
    public DailyCappingManager(final String mAdUnitName, final DailyCappingListener mListener) {
        this.mTimer = null;
        this.mAdUnitName = mAdUnitName;
        this.mListener = mListener;
        this.mSmashIdToMaxShowsPerDay = new HashMap<String, Integer>();
        this.mSmashIdToCounter = new HashMap<String, Integer>();
        this.mSmashIdToCounterDate = new HashMap<String, String>();
        this.mLogger = IronSourceLoggerManager.getLogger();
        this.scheduleTimer();
    }
    
    private int getCounter(final String s) {
        if (this.mSmashIdToCounter.containsKey(s)) {
            return this.mSmashIdToCounter.get(s);
        }
        final int intFromSharedPrefs = IronSourceUtils.getIntFromSharedPrefs(this.mContext, this.getCounterKeyName(s), 0);
        this.mSmashIdToCounter.put(s, intFromSharedPrefs);
        return intFromSharedPrefs;
    }
    
    private String getCounterDate(final String s) {
        if (this.mSmashIdToCounterDate.containsKey(s)) {
            return this.mSmashIdToCounterDate.get(s);
        }
        final String stringFromSharedPrefs = IronSourceUtils.getStringFromSharedPrefs(this.mContext, this.getDayKeyName(s), this.getTodayDate());
        this.mSmashIdToCounterDate.put(s, stringFromSharedPrefs);
        return stringFromSharedPrefs;
    }
    
    private String getCounterKeyName(final String s) {
        return s + "_counter";
    }
    
    private String getDayKeyName(final String s) {
        return s + "_day";
    }
    
    private String getTodayDate() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date());
    }
    
    private int getTodayShowCount(final String s) {
        if (!this.getTodayDate().equalsIgnoreCase(this.getCounterDate(s))) {
            this.zeroCounter(s);
        }
        return this.getCounter(s);
    }
    
    private String getUniqueId(final AbstractSmash abstractSmash) {
        return this.mAdUnitName + "_" + abstractSmash.getSubProviderId() + "_" + abstractSmash.getName();
    }
    
    private Date getUtcMidnight() {
        final Random random = new Random();
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"), Locale.US);
        gregorianCalendar.set(11, 0);
        gregorianCalendar.set(12, random.nextInt(10));
        gregorianCalendar.set(13, random.nextInt(60));
        gregorianCalendar.set(14, random.nextInt(1000));
        gregorianCalendar.add(5, 1);
        return gregorianCalendar.getTime();
    }
    
    private void onTimerTick() {
        synchronized (this) {
            Label_0059: {
                try {
                    final Iterator<String> iterator = this.mSmashIdToMaxShowsPerDay.keySet().iterator();
                    while (iterator.hasNext()) {
                        this.zeroCounter(iterator.next());
                    }
                    break Label_0059;
                }
                catch (Exception ex) {
                    this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "onTimerTick", ex);
                }
                return;
            }
            this.mListener.onDailyCapReleased();
            this.scheduleTimer();
        }
    }
    
    private void saveCounter(final String s, final int n) {
        this.mSmashIdToCounter.put(s, n);
        this.mSmashIdToCounterDate.put(s, this.getTodayDate());
        IronSourceUtils.saveIntToSharedPrefs(this.mContext, this.getCounterKeyName(s), n);
        IronSourceUtils.saveStringToSharedPrefs(this.mContext, this.getDayKeyName(s), this.getTodayDate());
    }
    
    private void scheduleTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
        (this.mTimer = new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                DailyCappingManager.this.onTimerTick();
            }
        }, this.getUtcMidnight());
    }
    
    private void zeroCounter(final String s) {
        this.mSmashIdToCounter.put(s, 0);
        this.mSmashIdToCounterDate.put(s, this.getTodayDate());
        IronSourceUtils.saveIntToSharedPrefs(this.mContext, this.getCounterKeyName(s), 0);
        IronSourceUtils.saveStringToSharedPrefs(this.mContext, this.getDayKeyName(s), this.getTodayDate());
    }
    
    public void addSmash(final AbstractSmash abstractSmash) {
        synchronized (this) {
            try {
                if (abstractSmash.getMaxAdsPerDay() != 99) {
                    this.mSmashIdToMaxShowsPerDay.put(this.getUniqueId(abstractSmash), abstractSmash.getMaxAdsPerDay());
                }
            }
            catch (Exception ex) {
                this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "addSmash", ex);
            }
        }
    }
    
    public void increaseShowCounter(final AbstractSmash abstractSmash) {
        // monitorenter(this)
        try {
            try {
                final String uniqueId = this.getUniqueId(abstractSmash);
                if (!this.mSmashIdToMaxShowsPerDay.containsKey(uniqueId)) {
                    return;
                }
                this.saveCounter(uniqueId, this.getTodayShowCount(uniqueId) + 1);
            }
            finally {
            }
            // monitorexit(this)
        }
        catch (Exception ex) {
            this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "increaseShowCounter", ex);
        }
    }
    
    public boolean isCapped(final AbstractSmash abstractSmash) {
        // monitorenter(this)
        try {
            while (true) {
                try {
                    final String uniqueId = this.getUniqueId(abstractSmash);
                    if (!this.mSmashIdToMaxShowsPerDay.containsKey(uniqueId)) {
                        return false;
                    }
                    if (this.mSmashIdToMaxShowsPerDay.get(uniqueId) <= this.getTodayShowCount(uniqueId)) {
                        return true;
                    }
                }
                finally {
                }
                // monitorexit(this)
                return false;
            }
        }
        catch (Exception ex) {
            this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "isCapped", ex);
            // monitorexit(this)
            return false;
        }
    }
    
    public void setContext(final Context mContext) {
        this.mContext = mContext;
    }
    
    public boolean shouldSendCapReleasedEvent(final AbstractSmash abstractSmash) {
        // monitorenter(this)
        try {
            while (true) {
                try {
                    final String uniqueId = this.getUniqueId(abstractSmash);
                    if (!this.mSmashIdToMaxShowsPerDay.containsKey(uniqueId)) {
                        return false;
                    }
                    if (this.getTodayDate().equalsIgnoreCase(this.getCounterDate(uniqueId))) {
                        return false;
                    }
                    if (this.mSmashIdToMaxShowsPerDay.get(uniqueId) <= this.getCounter(uniqueId)) {
                        return true;
                    }
                }
                finally {
                }
                // monitorexit(this)
                return false;
            }
        }
        catch (Exception ex) {
            this.mLogger.logException(IronSourceLogger.IronSourceTag.INTERNAL, "shouldSendCapReleasedEvent", ex);
            // monitorexit(this)
            return false;
        }
    }
}
