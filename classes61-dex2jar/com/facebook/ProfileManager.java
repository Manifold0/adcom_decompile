// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import com.facebook.internal.Utility;
import android.support.annotation.Nullable;
import android.os.Parcelable;
import android.content.Intent;
import com.facebook.internal.Validate;
import android.support.v4.content.LocalBroadcastManager;

public final class ProfileManager
{
    public static final String ACTION_CURRENT_PROFILE_CHANGED = "com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED";
    public static final String EXTRA_NEW_PROFILE = "com.facebook.sdk.EXTRA_NEW_PROFILE";
    public static final String EXTRA_OLD_PROFILE = "com.facebook.sdk.EXTRA_OLD_PROFILE";
    private static volatile ProfileManager instance;
    private Profile currentProfile;
    private final LocalBroadcastManager localBroadcastManager;
    private final ProfileCache profileCache;
    
    ProfileManager(final LocalBroadcastManager localBroadcastManager, final ProfileCache profileCache) {
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(profileCache, "profileCache");
        this.localBroadcastManager = localBroadcastManager;
        this.profileCache = profileCache;
    }
    
    static ProfileManager getInstance() {
        Label_0041: {
            if (ProfileManager.instance != null) {
                break Label_0041;
            }
            synchronized (ProfileManager.class) {
                if (ProfileManager.instance == null) {
                    ProfileManager.instance = new ProfileManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new ProfileCache());
                }
                return ProfileManager.instance;
            }
        }
    }
    
    private void sendCurrentProfileChangedBroadcast(final Profile profile, final Profile profile2) {
        final Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", (Parcelable)profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", (Parcelable)profile2);
        this.localBroadcastManager.sendBroadcast(intent);
    }
    
    private void setCurrentProfile(@Nullable final Profile currentProfile, final boolean b) {
        final Profile currentProfile2 = this.currentProfile;
        this.currentProfile = currentProfile;
        if (b) {
            if (currentProfile != null) {
                this.profileCache.save(currentProfile);
            }
            else {
                this.profileCache.clear();
            }
        }
        if (!Utility.areObjectsEqual(currentProfile2, currentProfile)) {
            this.sendCurrentProfileChangedBroadcast(currentProfile2, currentProfile);
        }
    }
    
    Profile getCurrentProfile() {
        return this.currentProfile;
    }
    
    boolean loadCurrentProfile() {
        boolean b = false;
        final Profile load = this.profileCache.load();
        if (load != null) {
            this.setCurrentProfile(load, false);
            b = true;
        }
        return b;
    }
    
    void setCurrentProfile(@Nullable final Profile profile) {
        this.setCurrentProfile(profile, true);
    }
}
