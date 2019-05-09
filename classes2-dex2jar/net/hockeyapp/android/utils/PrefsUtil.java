// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public class PrefsUtil
{
    private SharedPreferences mFeedbackTokenPrefs;
    private SharedPreferences$Editor mFeedbackTokenPrefsEditor;
    private SharedPreferences mNameEmailSubjectPrefs;
    private SharedPreferences$Editor mNameEmailSubjectPrefsEditor;
    
    private PrefsUtil() {
    }
    
    public static PrefsUtil getInstance() {
        return PrefsUtilHolder.INSTANCE;
    }
    
    public String getFeedbackTokenFromPrefs(final Context context) {
        if (context != null) {
            this.mFeedbackTokenPrefs = context.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
            if (this.mFeedbackTokenPrefs != null) {
                return this.mFeedbackTokenPrefs.getString("net.hockeyapp.android.prefs_key_feedback_token", (String)null);
            }
        }
        return null;
    }
    
    public String getNameEmailFromPrefs(final Context context) {
        if (context != null) {
            this.mNameEmailSubjectPrefs = context.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
            if (this.mNameEmailSubjectPrefs != null) {
                return this.mNameEmailSubjectPrefs.getString("net.hockeyapp.android.prefs_key_name_email", (String)null);
            }
        }
        return null;
    }
    
    public void saveFeedbackTokenToPrefs(final Context context, final String s) {
        if (context != null) {
            this.mFeedbackTokenPrefs = context.getSharedPreferences("net.hockeyapp.android.prefs_feedback_token", 0);
            if (this.mFeedbackTokenPrefs != null) {
                (this.mFeedbackTokenPrefsEditor = this.mFeedbackTokenPrefs.edit()).putString("net.hockeyapp.android.prefs_key_feedback_token", s);
                this.mFeedbackTokenPrefsEditor.apply();
            }
        }
    }
    
    public void saveNameEmailSubjectToPrefs(final Context context, final String s, final String s2, final String s3) {
        if (context != null) {
            this.mNameEmailSubjectPrefs = context.getSharedPreferences("net.hockeyapp.android.prefs_name_email", 0);
            if (this.mNameEmailSubjectPrefs != null) {
                this.mNameEmailSubjectPrefsEditor = this.mNameEmailSubjectPrefs.edit();
                if (s == null || s2 == null || s3 == null) {
                    this.mNameEmailSubjectPrefsEditor.putString("net.hockeyapp.android.prefs_key_name_email", (String)null);
                }
                else {
                    this.mNameEmailSubjectPrefsEditor.putString("net.hockeyapp.android.prefs_key_name_email", String.format("%s|%s|%s", s, s2, s3));
                }
                this.mNameEmailSubjectPrefsEditor.apply();
            }
        }
    }
    
    private static class PrefsUtilHolder
    {
        public static final PrefsUtil INSTANCE;
        
        static {
            INSTANCE = new PrefsUtil(null);
        }
    }
}
