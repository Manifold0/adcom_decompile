// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

public interface KongregateServices
{
    public static final String GUEST_GAME_AUTH_TOKEN = "Guest_Game_Auth_Token";
    public static final String GUEST_USERNAME = "Guest";
    public static final long GUEST_USER_ID = 0L;
    
    String getGameAuthToken();
    
    int getNotificationCount();
    
    long getUserId();
    
    String getUsername();
    
    boolean hasKongPlus();
    
    boolean hasUnreadGuildMessages();
    
    boolean isConnected();
    
    boolean isGuest();
    
    void setCharacterToken(final String p0);
}
