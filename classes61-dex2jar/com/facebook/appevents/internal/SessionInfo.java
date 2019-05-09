// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import android.preference.PreferenceManager;
import com.facebook.FacebookSdk;
import java.util.UUID;

class SessionInfo
{
    private static final String INTERRUPTION_COUNT_KEY = "com.facebook.appevents.SessionInfo.interruptionCount";
    private static final String LAST_SESSION_INFO_END_KEY = "com.facebook.appevents.SessionInfo.sessionEndTime";
    private static final String LAST_SESSION_INFO_START_KEY = "com.facebook.appevents.SessionInfo.sessionStartTime";
    private static final String SESSION_ID_KEY = "com.facebook.appevents.SessionInfo.sessionId";
    private Long diskRestoreTime;
    private int interruptionCount;
    private UUID sessionId;
    private Long sessionLastEventTime;
    private Long sessionStartTime;
    private SourceApplicationInfo sourceApplicationInfo;
    
    public SessionInfo(final Long n, final Long n2) {
        this(n, n2, UUID.randomUUID());
    }
    
    public SessionInfo(final Long sessionStartTime, final Long sessionLastEventTime, final UUID sessionId) {
        this.sessionStartTime = sessionStartTime;
        this.sessionLastEventTime = sessionLastEventTime;
        this.sessionId = sessionId;
    }
    
    public static void clearSavedSessionFromDisk() {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.remove("com.facebook.appevents.SessionInfo.sessionStartTime");
        edit.remove("com.facebook.appevents.SessionInfo.sessionEndTime");
        edit.remove("com.facebook.appevents.SessionInfo.interruptionCount");
        edit.remove("com.facebook.appevents.SessionInfo.sessionId");
        edit.apply();
        SourceApplicationInfo.clearSavedSourceApplicationInfoFromDisk();
    }
    
    public static SessionInfo getStoredSessionInfo() {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        final long long1 = defaultSharedPreferences.getLong("com.facebook.appevents.SessionInfo.sessionStartTime", 0L);
        final long long2 = defaultSharedPreferences.getLong("com.facebook.appevents.SessionInfo.sessionEndTime", 0L);
        final String string = defaultSharedPreferences.getString("com.facebook.appevents.SessionInfo.sessionId", (String)null);
        if (long1 == 0L || long2 == 0L || string == null) {
            return null;
        }
        final SessionInfo sessionInfo = new SessionInfo(long1, long2);
        sessionInfo.interruptionCount = defaultSharedPreferences.getInt("com.facebook.appevents.SessionInfo.interruptionCount", 0);
        sessionInfo.sourceApplicationInfo = SourceApplicationInfo.getStoredSourceApplicatioInfo();
        sessionInfo.diskRestoreTime = System.currentTimeMillis();
        sessionInfo.sessionId = UUID.fromString(string);
        return sessionInfo;
    }
    
    public long getDiskRestoreTime() {
        if (this.diskRestoreTime == null) {
            return 0L;
        }
        return this.diskRestoreTime;
    }
    
    public int getInterruptionCount() {
        return this.interruptionCount;
    }
    
    public UUID getSessionId() {
        return this.sessionId;
    }
    
    public Long getSessionLastEventTime() {
        return this.sessionLastEventTime;
    }
    
    public long getSessionLength() {
        if (this.sessionStartTime == null || this.sessionLastEventTime == null) {
            return 0L;
        }
        return this.sessionLastEventTime - this.sessionStartTime;
    }
    
    public Long getSessionStartTime() {
        return this.sessionStartTime;
    }
    
    public SourceApplicationInfo getSourceApplicationInfo() {
        return this.sourceApplicationInfo;
    }
    
    public void incrementInterruptionCount() {
        ++this.interruptionCount;
    }
    
    public void setSessionLastEventTime(final Long sessionLastEventTime) {
        this.sessionLastEventTime = sessionLastEventTime;
    }
    
    public void setSourceApplicationInfo(final SourceApplicationInfo sourceApplicationInfo) {
        this.sourceApplicationInfo = sourceApplicationInfo;
    }
    
    public void writeSessionToDisk() {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.putLong("com.facebook.appevents.SessionInfo.sessionStartTime", (long)this.sessionStartTime);
        edit.putLong("com.facebook.appevents.SessionInfo.sessionEndTime", (long)this.sessionLastEventTime);
        edit.putInt("com.facebook.appevents.SessionInfo.interruptionCount", this.interruptionCount);
        edit.putString("com.facebook.appevents.SessionInfo.sessionId", this.sessionId.toString());
        edit.apply();
        if (this.sourceApplicationInfo != null) {
            this.sourceApplicationInfo.writeSourceApplicationInfoToDisk();
        }
    }
}
