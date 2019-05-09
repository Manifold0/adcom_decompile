// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.internal;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.android.gms.common.annotation.KeepForSdk;

@Deprecated
@KeepForSdk
public class FirebaseAppHelper
{
    @KeepForSdk
    public static void addIdTokenListener(final FirebaseApp firebaseApp, final FirebaseApp.IdTokenListener idTokenListener) {
        firebaseApp.addIdTokenListener(idTokenListener);
    }
    
    @KeepForSdk
    public static Task<GetTokenResult> getToken(final FirebaseApp firebaseApp, final boolean b) {
        return firebaseApp.getToken(b);
    }
    
    @KeepForSdk
    public static String getUid(final FirebaseApp firebaseApp) throws FirebaseApiNotAvailableException {
        return firebaseApp.getUid();
    }
    
    @KeepForSdk
    public static void removeIdTokenListener(final FirebaseApp firebaseApp, final FirebaseApp.IdTokenListener idTokenListener) {
        firebaseApp.removeIdTokenListener(idTokenListener);
    }
}
