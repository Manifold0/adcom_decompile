// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

import android.net.Uri;
import android.view.View;
import android.content.Context;

public interface MobileServices
{
    public static final String GDPR_ALERT_TRIGGER = "gdpr_alert";
    public static final String PANEL_EVENT_GO_TO_GUILDS = "GO_TO_GUILDS";
    public static final String PANEL_TRANSITION_SLIDE_FROM_LEFT = "slideFromLeft";
    public static final String PANEL_TRANSITION_SLIDE_FROM_RIGHT = "slideFromRight";
    public static final String TARGET_FORUMS = "forums";
    public static final String TARGET_GUILD_CHAT = "guild_chat";
    public static final String TARGET_HIGH_SCORES = "high_scores";
    public static final String TARGET_MESSAGES = "messages";
    public static final String TARGET_MORE_GAMES = "more_games";
    public static final String TARGET_OFFERS = "offer_rewards";
    public static final String TARGET_PRIVACY = "privacy";
    public static final String TARGET_REGISTRATION = "registration";
    public static final String TARGET_SUPPORT = "support";
    public static final String TARGET_TERMS = "terms";
    public static final String TARGET_TOPICS = "topics";
    
    void closeKongregateWindow(final Context p0);
    
    View getButton(final Context p0);
    
    Uri getOpenURL();
    
    void openKongregateWindow(final Context p0);
    
    void openKongregateWindow(final Context p0, final String p1);
    
    void openKongregateWindow(final Context p0, final String p1, final String p2);
    
    @Deprecated
    void trackPurchase(final String p0);
    
    void trigger(final String p0);
}
