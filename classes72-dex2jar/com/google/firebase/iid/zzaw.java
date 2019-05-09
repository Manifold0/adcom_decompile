// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.Iterator;
import android.content.SharedPreferences$Editor;
import java.io.IOException;
import android.util.Log;
import java.io.File;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.content.Context;
import android.support.annotation.GuardedBy;
import java.util.Map;
import android.content.SharedPreferences;

final class zzaw
{
    private final SharedPreferences zzdc;
    private final zzy zzdd;
    @GuardedBy("this")
    private final Map<String, zzz> zzde;
    private final Context zzx;
    
    public zzaw(final Context context) {
        this(context, new zzy());
    }
    
    private zzaw(final Context zzx, final zzy zzdd) {
        this.zzde = (Map<String, zzz>)new ArrayMap();
        this.zzx = zzx;
        this.zzdc = zzx.getSharedPreferences("com.google.android.gms.appid", 0);
        this.zzdd = zzdd;
        final File file = new File(ContextCompat.getNoBackupFilesDir(this.zzx), "com.google.android.gms.appid-no-backup");
        if (file.exists()) {
            return;
        }
        try {
            if (file.createNewFile() && !this.isEmpty()) {
                Log.i("FirebaseInstanceId", "App restored, clearing state");
                this.zzal();
                FirebaseInstanceId.getInstance().zzm();
            }
        }
        catch (IOException ex) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                final String value = String.valueOf(ex.getMessage());
                String concat;
                if (value.length() != 0) {
                    concat = "Error creating file in no backup dir: ".concat(value);
                }
                else {
                    concat = new String("Error creating file in no backup dir: ");
                }
                Log.d("FirebaseInstanceId", concat);
            }
        }
    }
    
    private final boolean isEmpty() {
        synchronized (this) {
            return this.zzdc.getAll().isEmpty();
        }
    }
    
    private static String zza(final String s, final String s2, final String s3) {
        return new StringBuilder(String.valueOf(s).length() + 4 + String.valueOf(s2).length() + String.valueOf(s3).length()).append(s).append("|T|").append(s2).append("|").append(s3).toString();
    }
    
    static String zzd(final String s, final String s2) {
        return new StringBuilder(String.valueOf(s).length() + 3 + String.valueOf(s2).length()).append(s).append("|S|").append(s2).toString();
    }
    
    public final void zza(final String s, final String s2, final String s3, String zza, final String s4) {
        synchronized (this) {
            zza = zzax.zza(zza, s4, System.currentTimeMillis());
            if (zza != null) {
                final SharedPreferences$Editor edit = this.zzdc.edit();
                edit.putString(zza(s, s2, s3), zza);
                edit.commit();
            }
        }
    }
    
    public final String zzak() {
        synchronized (this) {
            return this.zzdc.getString("topic_operaion_queue", "");
        }
    }
    
    public final void zzal() {
        synchronized (this) {
            this.zzde.clear();
            zzy.zza(this.zzx);
            this.zzdc.edit().clear().commit();
        }
    }
    
    public final zzax zzb(final String s, final String s2, final String s3) {
        synchronized (this) {
            return zzax.zzi(this.zzdc.getString(zza(s, s2, s3), (String)null));
        }
    }
    
    public final void zzc(String zza, final String s, final String s2) {
        synchronized (this) {
            zza = zza(zza, s, s2);
            final SharedPreferences$Editor edit = this.zzdc.edit();
            edit.remove(zza);
            edit.commit();
        }
    }
    
    public final void zzf(final String s) {
        synchronized (this) {
            this.zzdc.edit().putString("topic_operaion_queue", s).apply();
        }
    }
    
    public final zzz zzg(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/firebase/iid/zzaw.zzde:Ljava/util/Map;
        //     6: aload_1        
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: checkcast       Lcom/google/firebase/iid/zzz;
        //    15: astore_2       
        //    16: aload_2        
        //    17: ifnull          24
        //    20: aload_0        
        //    21: monitorexit    
        //    22: aload_2        
        //    23: areturn        
        //    24: aload_0        
        //    25: getfield        com/google/firebase/iid/zzaw.zzdd:Lcom/google/firebase/iid/zzy;
        //    28: aload_0        
        //    29: getfield        com/google/firebase/iid/zzaw.zzx:Landroid/content/Context;
        //    32: aload_1        
        //    33: invokevirtual   com/google/firebase/iid/zzy.zzb:(Landroid/content/Context;Ljava/lang/String;)Lcom/google/firebase/iid/zzz;
        //    36: astore_2       
        //    37: aload_0        
        //    38: getfield        com/google/firebase/iid/zzaw.zzde:Ljava/util/Map;
        //    41: aload_1        
        //    42: aload_2        
        //    43: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    48: pop            
        //    49: goto            20
        //    52: astore_1       
        //    53: aload_0        
        //    54: monitorexit    
        //    55: aload_1        
        //    56: athrow         
        //    57: astore_2       
        //    58: ldc             "FirebaseInstanceId"
        //    60: ldc             "Stored data is corrupt, generating new identity"
        //    62: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    65: pop            
        //    66: invokestatic    com/google/firebase/iid/FirebaseInstanceId.getInstance:()Lcom/google/firebase/iid/FirebaseInstanceId;
        //    69: invokevirtual   com/google/firebase/iid/FirebaseInstanceId.zzm:()V
        //    72: aload_0        
        //    73: getfield        com/google/firebase/iid/zzaw.zzdd:Lcom/google/firebase/iid/zzy;
        //    76: aload_0        
        //    77: getfield        com/google/firebase/iid/zzaw.zzx:Landroid/content/Context;
        //    80: aload_1        
        //    81: invokevirtual   com/google/firebase/iid/zzy.zzc:(Landroid/content/Context;Ljava/lang/String;)Lcom/google/firebase/iid/zzz;
        //    84: astore_2       
        //    85: goto            37
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                          
        //  -----  -----  -----  -----  ------------------------------
        //  2      16     52     57     Any
        //  24     37     57     88     Lcom/google/firebase/iid/zzaa;
        //  24     37     52     57     Any
        //  37     49     52     57     Any
        //  58     85     52     57     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public final void zzh(String concat) {
        final SharedPreferences$Editor edit;
        synchronized (this) {
            concat = String.valueOf(concat).concat("|T|");
            edit = this.zzdc.edit();
            for (final String s : this.zzdc.getAll().keySet()) {
                if (s.startsWith(concat)) {
                    edit.remove(s);
                }
            }
        }
        edit.commit();
    }
    // monitorexit(this)
}
