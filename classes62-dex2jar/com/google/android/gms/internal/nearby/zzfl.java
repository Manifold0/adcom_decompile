// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import android.util.Log;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import com.google.android.gms.nearby.connection.Payload;

public final class zzfl
{
    static Pair<zzfh, Pair<ParcelFileDescriptor, ParcelFileDescriptor>> zza(final Payload payload) throws IOException {
        switch (payload.getType()) {
            default: {
                final IllegalArgumentException ex = new IllegalArgumentException(String.format("Outgoing Payload %d has unknown type %d", payload.getId(), payload.getType()));
                Log.wtf("NearbyConnections", "Unknown payload type!", (Throwable)ex);
                throw ex;
            }
            case 1: {
                return (Pair<zzfh, Pair<ParcelFileDescriptor, ParcelFileDescriptor>>)Pair.create((Object)new zzfj().zzb(payload.getId()).zzd(payload.getType()).zzb(payload.asBytes()).zzr(), (Object)null);
            }
            case 2: {
                String absolutePath;
                if (payload.asFile().asJavaFile() == null) {
                    absolutePath = null;
                }
                else {
                    absolutePath = payload.asFile().asJavaFile().getAbsolutePath();
                }
                return (Pair<zzfh, Pair<ParcelFileDescriptor, ParcelFileDescriptor>>)Pair.create((Object)new zzfj().zzb(payload.getId()).zzd(payload.getType()).zzc(payload.asFile().asParcelFileDescriptor()).zze(absolutePath).zzc(payload.asFile().getSize()).zzr(), (Object)null);
            }
            case 3: {
                try {
                    final ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
                    final ParcelFileDescriptor[] pipe2 = ParcelFileDescriptor.createPipe();
                    return (Pair<zzfh, Pair<ParcelFileDescriptor, ParcelFileDescriptor>>)Pair.create((Object)new zzfj().zzb(payload.getId()).zzd(payload.getType()).zzc(pipe[0]).zzd(pipe2[0]).zzr(), (Object)Pair.create((Object)pipe[1], (Object)pipe2[1]));
                }
                catch (IOException ex2) {
                    Log.e("NearbyConnections", String.format("Unable to create PFD pipe for streaming payload %d from client to service.", payload.getId()), (Throwable)ex2);
                    throw ex2;
                }
                break;
            }
        }
    }
    
    static Payload zza(final zzfh zzfh) {
        final long id = zzfh.getId();
        switch (zzfh.getType()) {
            default: {
                Log.w("NearbyConnections", String.format("Incoming ParcelablePayload %d has unknown type %d", zzfh.getId(), zzfh.getType()));
                return null;
            }
            case 1: {
                return Payload.zza(zzfh.getBytes(), id);
            }
            case 2: {
                final String zzp = zzfh.zzp();
                if (zzp != null) {
                    while (true) {
                        while (true) {
                            Label_0157: {
                                try {
                                    return Payload.zza(Payload.File.zza(new File(zzp), zzfh.zzq()), id);
                                }
                                catch (FileNotFoundException ex) {
                                    final String value = String.valueOf(zzp);
                                    if (value.length() == 0) {
                                        break Label_0157;
                                    }
                                    final String concat = "Failed to create Payload from ParcelablePayload: Java file not found at ".concat(value);
                                    Log.w("NearbyConnections", concat, (Throwable)ex);
                                }
                                break;
                            }
                            final String concat = new String("Failed to create Payload from ParcelablePayload: Java file not found at ");
                            continue;
                        }
                    }
                }
                return Payload.zza(Payload.File.zza(zzfh.zzo()), id);
            }
            case 3: {
                return Payload.zza(Payload.Stream.zzb(zzfh.zzo()), id);
            }
        }
    }
}
