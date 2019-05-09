// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

class OSPermissionChangedInternalObserver
{
    static void fireChangesToPublicObserver(final OSPermissionState osPermissionState) {
        final OSPermissionStateChanges osPermissionStateChanges = new OSPermissionStateChanges();
        osPermissionStateChanges.from = OneSignal.lastPermissionState;
        osPermissionStateChanges.to = (OSPermissionState)osPermissionState.clone();
        if (OneSignal.getPermissionStateChangesObserver().notifyChange(osPermissionStateChanges)) {
            (OneSignal.lastPermissionState = (OSPermissionState)osPermissionState.clone()).persistAsFrom();
        }
    }
    
    static void handleInternalChanges(final OSPermissionState osPermissionState) {
        if (!osPermissionState.getEnabled()) {
            BadgeCountUpdater.updateCount(0, OneSignal.appContext);
        }
        OneSignalStateSynchronizer.setPermission(OneSignal.areNotificationsEnabledForSubscribedState());
    }
    
    void changed(final OSPermissionState osPermissionState) {
        handleInternalChanges(osPermissionState);
        fireChangesToPublicObserver(osPermissionState);
    }
}
