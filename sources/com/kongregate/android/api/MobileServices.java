package com.kongregate.android.api;

import android.content.Context;
import android.net.Uri;
import android.view.View;

public interface MobileServices {
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

    void closeKongregateWindow(Context context);

    View getButton(Context context);

    Uri getOpenURL();

    void openKongregateWindow(Context context);

    void openKongregateWindow(Context context, String str);

    void openKongregateWindow(Context context, String str, String str2);

    @Deprecated
    void trackPurchase(String str);

    void trigger(String str);
}
