// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.io.Closeable;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.io.OutputStream;
import android.os.ParcelFileDescriptor$AutoCloseOutputStream;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "LargeParcelTeleporterCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzaev extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaev> CREATOR;
    @SafeParcelable$Field(id = 2)
    private ParcelFileDescriptor zzcft;
    private Parcelable zzcfu;
    private boolean zzcfv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaex();
    }
    
    @SafeParcelable$Constructor
    public zzaev(@SafeParcelable$Param(id = 2) final ParcelFileDescriptor zzcft) {
        this.zzcft = zzcft;
        this.zzcfu = null;
        this.zzcfv = true;
    }
    
    public zzaev(final SafeParcelable zzcfu) {
        this.zzcft = null;
        this.zzcfu = (Parcelable)zzcfu;
        this.zzcfv = false;
    }
    
    private final <T> ParcelFileDescriptor zze(final byte[] array) {
        ParcelFileDescriptor[] pipe = null;
        Object o;
        try {
            pipe = ParcelFileDescriptor.createPipe();
            o = new ParcelFileDescriptor$AutoCloseOutputStream(pipe[1]);
            final zzaev zzaev = this;
            final Object o2 = o;
            final byte[] array2 = array;
            final zzaew zzaew = new zzaew(zzaev, (OutputStream)o2, array2);
            final Thread thread = new Thread(zzaew);
            thread.start();
            final ParcelFileDescriptor[] array3 = pipe;
            final int n = 0;
            final ParcelFileDescriptor parcelFileDescriptor = array3[n];
            return parcelFileDescriptor;
        }
        catch (IOException ex) {
            o = null;
        }
        while (true) {
            try {
                final zzaev zzaev = this;
                final Object o2 = o;
                final byte[] array2 = array;
                final zzaew zzaew = new zzaew(zzaev, (OutputStream)o2, array2);
                final Thread thread = new Thread(zzaew);
                thread.start();
                final ParcelFileDescriptor[] array3 = pipe;
                final int n = 0;
                final ParcelFileDescriptor parcelFileDescriptor2;
                final ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptor2 = array3[n];
                return parcelFileDescriptor2;
                final IOException ex;
                zzakb.zzb("Error transporting the ad response", (Throwable)ex);
                zzbv.zzeo().zza(ex, "LargeParcelTeleporter.pipeData.2");
                IOUtils.closeQuietly((Closeable)o);
                return null;
            }
            catch (IOException ex) {
                continue;
            }
            break;
        }
    }
    
    private final ParcelFileDescriptor zzoc() {
        Label_0040: {
            if (this.zzcft != null) {
                break Label_0040;
            }
            final Parcel obtain = Parcel.obtain();
            try {
                this.zzcfu.writeToParcel(obtain, 0);
                final byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzcft = this.zze(marshall);
                return this.zzcft;
            }
            finally {
                obtain.recycle();
            }
        }
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        this.zzoc();
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzcft, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <T extends SafeParcelable> T zza(final Parcelable$Creator<T> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzaev.zzcfv:Z
        //     4: ifeq            99
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzaev.zzcft:Landroid/os/ParcelFileDescriptor;
        //    11: ifnonnull       21
        //    14: ldc             "File descriptor is empty, returning null."
        //    16: invokestatic    com/google/android/gms/internal/ads/zzakb.e:(Ljava/lang/String;)V
        //    19: aconst_null    
        //    20: areturn        
        //    21: new             Ljava/io/DataInputStream;
        //    24: dup            
        //    25: new             Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
        //    28: dup            
        //    29: aload_0        
        //    30: getfield        com/google/android/gms/internal/ads/zzaev.zzcft:Landroid/os/ParcelFileDescriptor;
        //    33: invokespecial   android/os/ParcelFileDescriptor$AutoCloseInputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
        //    36: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //    39: astore_2       
        //    40: aload_2        
        //    41: invokevirtual   java/io/DataInputStream.readInt:()I
        //    44: newarray        B
        //    46: astore_3       
        //    47: aload_2        
        //    48: aload_3        
        //    49: iconst_0       
        //    50: aload_3        
        //    51: arraylength    
        //    52: invokevirtual   java/io/DataInputStream.readFully:([BII)V
        //    55: aload_2        
        //    56: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    59: invokestatic    android/os/Parcel.obtain:()Landroid/os/Parcel;
        //    62: astore_2       
        //    63: aload_2        
        //    64: aload_3        
        //    65: iconst_0       
        //    66: aload_3        
        //    67: arraylength    
        //    68: invokevirtual   android/os/Parcel.unmarshall:([BII)V
        //    71: aload_2        
        //    72: iconst_0       
        //    73: invokevirtual   android/os/Parcel.setDataPosition:(I)V
        //    76: aload_0        
        //    77: aload_1        
        //    78: aload_2        
        //    79: invokeinterface android/os/Parcelable$Creator.createFromParcel:(Landroid/os/Parcel;)Ljava/lang/Object;
        //    84: checkcast       Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;
        //    87: putfield        com/google/android/gms/internal/ads/zzaev.zzcfu:Landroid/os/Parcelable;
        //    90: aload_2        
        //    91: invokevirtual   android/os/Parcel.recycle:()V
        //    94: aload_0        
        //    95: iconst_0       
        //    96: putfield        com/google/android/gms/internal/ads/zzaev.zzcfv:Z
        //    99: aload_0        
        //   100: getfield        com/google/android/gms/internal/ads/zzaev.zzcfu:Landroid/os/Parcelable;
        //   103: checkcast       Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;
        //   106: areturn        
        //   107: astore_1       
        //   108: ldc             "Could not read from parcel file descriptor"
        //   110: aload_1        
        //   111: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   114: aload_2        
        //   115: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //   118: aconst_null    
        //   119: areturn        
        //   120: astore_1       
        //   121: aload_2        
        //   122: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //   125: aload_1        
        //   126: athrow         
        //   127: astore_1       
        //   128: aload_2        
        //   129: invokevirtual   android/os/Parcel.recycle:()V
        //   132: aload_1        
        //   133: athrow         
        //    Signature:
        //  <T:Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;>(Landroid/os/Parcelable$Creator<TT;>;)TT; [from metadata: <T::Lcom/google/android/gms/common/internal/safeparcel/SafeParcelable;>(Landroid/os/Parcelable$Creator<TT;>;)TT;]
        //  
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  40     55     107    120    Ljava/io/IOException;
        //  40     55     120    127    Any
        //  63     90     127    134    Any
        //  108    114    120    127    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0099:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
