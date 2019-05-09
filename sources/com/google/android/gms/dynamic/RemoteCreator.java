package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public abstract class RemoteCreator<T> {
    private final String zzic;
    private T zzid;

    @KeepForSdk
    public static class RemoteCreatorException extends Exception {
        public RemoteCreatorException(String str) {
            super(str);
        }

        public RemoteCreatorException(String str, Throwable th) {
            super(str, th);
        }
    }

    @KeepForSdk
    protected RemoteCreator(String str) {
        this.zzic = str;
    }

    @KeepForSdk
    protected abstract T getRemoteCreator(IBinder iBinder);

    @KeepForSdk
    protected final T getRemoteCreatorInstance(Context context) throws RemoteCreatorException {
        if (this.zzid == null) {
            Preconditions.checkNotNull(context);
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext == null) {
                throw new RemoteCreatorException("Could not get remote context.");
            }
            try {
                this.zzid = getRemoteCreator((IBinder) remoteContext.getClassLoader().loadClass(this.zzic).newInstance());
            } catch (Throwable e) {
                throw new RemoteCreatorException("Could not load creator class.", e);
            } catch (Throwable e2) {
                throw new RemoteCreatorException("Could not instantiate creator.", e2);
            } catch (Throwable e22) {
                throw new RemoteCreatorException("Could not access creator.", e22);
            }
        }
        return this.zzid;
    }
}
