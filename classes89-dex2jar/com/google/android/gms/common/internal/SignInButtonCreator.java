// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import android.view.View;
import android.content.Context;
import com.google.android.gms.dynamic.RemoteCreator;

public final class SignInButtonCreator extends RemoteCreator<ISignInButtonCreator>
{
    private static final SignInButtonCreator zapf;
    
    static {
        zapf = new SignInButtonCreator();
    }
    
    private SignInButtonCreator() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }
    
    public static View createView(final Context context, final int n, final int n2) throws RemoteCreator$RemoteCreatorException {
        return SignInButtonCreator.zapf.zaa(context, n, n2);
    }
    
    private final View zaa(final Context context, final int n, final int n2) throws RemoteCreator$RemoteCreatorException {
        try {
            return (View)ObjectWrapper.unwrap(((ISignInButtonCreator)this.getRemoteCreatorInstance(context)).newSignInButtonFromConfig(ObjectWrapper.wrap((Object)context), new SignInButtonConfig(n, n2, null)));
        }
        catch (Exception ex) {
            throw new RemoteCreator$RemoteCreatorException(new StringBuilder(64).append("Could not get button with size ").append(n).append(" and color ").append(n2).toString(), (Throwable)ex);
        }
    }
    
    public final ISignInButtonCreator getRemoteCreator(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        if (queryLocalInterface instanceof ISignInButtonCreator) {
            return (ISignInButtonCreator)queryLocalInterface;
        }
        return new zah(binder);
    }
}
