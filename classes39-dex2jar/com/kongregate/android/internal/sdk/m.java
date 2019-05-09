// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import com.kongregate.o.j.a;
import com.kongregate.android.api.KongregateServices;

public class m implements KongregateServices
{
    @Override
    public String getGameAuthToken() {
        return a.a().v();
    }
    
    @Override
    public int getNotificationCount() {
        return a.a().r();
    }
    
    @Override
    public long getUserId() {
        return a.a().g();
    }
    
    @Override
    public String getUsername() {
        return a.a().h();
    }
    
    @Override
    public boolean hasKongPlus() {
        return a.a().i();
    }
    
    @Override
    public boolean hasUnreadGuildMessages() {
        return a.a().s();
    }
    
    @Override
    public boolean isConnected() {
        return false;
    }
    
    @Override
    public boolean isGuest() {
        return a.a().f();
    }
    
    @Override
    public void setCharacterToken(final String s) {
        a.a().c(s);
    }
}
