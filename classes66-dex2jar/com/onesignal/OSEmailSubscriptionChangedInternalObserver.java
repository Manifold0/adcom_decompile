// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

class OSEmailSubscriptionChangedInternalObserver
{
    static void fireChangesToPublicObserver(final OSEmailSubscriptionState osEmailSubscriptionState) {
        final OSEmailSubscriptionStateChanges osEmailSubscriptionStateChanges = new OSEmailSubscriptionStateChanges();
        osEmailSubscriptionStateChanges.from = OneSignal.lastEmailSubscriptionState;
        osEmailSubscriptionStateChanges.to = (OSEmailSubscriptionState)osEmailSubscriptionState.clone();
        if (OneSignal.getEmailSubscriptionStateChangesObserver().notifyChange(osEmailSubscriptionStateChanges)) {
            (OneSignal.lastEmailSubscriptionState = (OSEmailSubscriptionState)osEmailSubscriptionState.clone()).persistAsFrom();
        }
    }
    
    void changed(final OSEmailSubscriptionState osEmailSubscriptionState) {
        fireChangesToPublicObserver(osEmailSubscriptionState);
    }
}
