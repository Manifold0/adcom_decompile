// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

class OSSubscriptionChangedInternalObserver
{
    static void fireChangesToPublicObserver(final OSSubscriptionState osSubscriptionState) {
        final OSSubscriptionStateChanges osSubscriptionStateChanges = new OSSubscriptionStateChanges();
        osSubscriptionStateChanges.from = OneSignal.lastSubscriptionState;
        osSubscriptionStateChanges.to = (OSSubscriptionState)osSubscriptionState.clone();
        if (OneSignal.getSubscriptionStateChangesObserver().notifyChange(osSubscriptionStateChanges)) {
            (OneSignal.lastSubscriptionState = (OSSubscriptionState)osSubscriptionState.clone()).persistAsFrom();
        }
    }
    
    public void changed(final OSSubscriptionState osSubscriptionState) {
        fireChangesToPublicObserver(osSubscriptionState);
    }
}
