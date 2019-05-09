package com.facebook.appevents.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.facebook.FacebookSdk;
import java.util.UUID;

class SessionInfo {
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

    public SessionInfo(Long sessionStartTime, Long sessionLastEventTime) {
        this(sessionStartTime, sessionLastEventTime, UUID.randomUUID());
    }

    public SessionInfo(Long sessionStartTime, Long sessionLastEventTime, UUID sessionId) {
        this.sessionStartTime = sessionStartTime;
        this.sessionLastEventTime = sessionLastEventTime;
        this.sessionId = sessionId;
    }

    public static SessionInfo getStoredSessionInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        long startTime = sharedPreferences.getLong(LAST_SESSION_INFO_START_KEY, 0);
        long endTime = sharedPreferences.getLong(LAST_SESSION_INFO_END_KEY, 0);
        String sessionIDStr = sharedPreferences.getString(SESSION_ID_KEY, null);
        if (startTime == 0 || endTime == 0 || sessionIDStr == null) {
            return null;
        }
        SessionInfo sessionInfo = new SessionInfo(Long.valueOf(startTime), Long.valueOf(endTime));
        sessionInfo.interruptionCount = sharedPreferences.getInt(INTERRUPTION_COUNT_KEY, 0);
        sessionInfo.sourceApplicationInfo = SourceApplicationInfo.getStoredSourceApplicatioInfo();
        sessionInfo.diskRestoreTime = Long.valueOf(System.currentTimeMillis());
        sessionInfo.sessionId = UUID.fromString(sessionIDStr);
        return sessionInfo;
    }

    public static void clearSavedSessionFromDisk() {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        editor.remove(LAST_SESSION_INFO_START_KEY);
        editor.remove(LAST_SESSION_INFO_END_KEY);
        editor.remove(INTERRUPTION_COUNT_KEY);
        editor.remove(SESSION_ID_KEY);
        editor.apply();
        SourceApplicationInfo.clearSavedSourceApplicationInfoFromDisk();
    }

    public Long getSessionStartTime() {
        return this.sessionStartTime;
    }

    public Long getSessionLastEventTime() {
        return this.sessionLastEventTime;
    }

    public void setSessionLastEventTime(Long sessionLastEventTime) {
        this.sessionLastEventTime = sessionLastEventTime;
    }

    public int getInterruptionCount() {
        return this.interruptionCount;
    }

    public void incrementInterruptionCount() {
        this.interruptionCount++;
    }

    public long getDiskRestoreTime() {
        return this.diskRestoreTime == null ? 0 : this.diskRestoreTime.longValue();
    }

    public UUID getSessionId() {
        return this.sessionId;
    }

    public long getSessionLength() {
        if (this.sessionStartTime == null || this.sessionLastEventTime == null) {
            return 0;
        }
        return this.sessionLastEventTime.longValue() - this.sessionStartTime.longValue();
    }

    public SourceApplicationInfo getSourceApplicationInfo() {
        return this.sourceApplicationInfo;
    }

    public void setSourceApplicationInfo(SourceApplicationInfo sourceApplicationInfo) {
        this.sourceApplicationInfo = sourceApplicationInfo;
    }

    public void writeSessionToDisk() {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        editor.putLong(LAST_SESSION_INFO_START_KEY, this.sessionStartTime.longValue());
        editor.putLong(LAST_SESSION_INFO_END_KEY, this.sessionLastEventTime.longValue());
        editor.putInt(INTERRUPTION_COUNT_KEY, this.interruptionCount);
        editor.putString(SESSION_ID_KEY, this.sessionId.toString());
        editor.apply();
        if (this.sourceApplicationInfo != null) {
            this.sourceApplicationInfo.writeSourceApplicationInfoToDisk();
        }
    }
}
