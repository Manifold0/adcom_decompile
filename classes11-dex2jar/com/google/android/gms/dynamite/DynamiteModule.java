// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import com.google.android.gms.common.util.DynamiteApi;
import android.net.Uri;
import android.database.Cursor;
import android.os.IInterface;
import java.lang.reflect.InvocationTargetException;
import android.os.IBinder;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.common.util.CrashUtils;
import android.os.RemoteException;
import java.lang.reflect.Field;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class DynamiteModule
{
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION;
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING;
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION;
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL;
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE;
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzif;
    @GuardedBy("DynamiteModule.class")
    private static zzi zzig;
    @GuardedBy("DynamiteModule.class")
    private static zzk zzih;
    @GuardedBy("DynamiteModule.class")
    private static String zzii;
    @GuardedBy("DynamiteModule.class")
    private static int zzij;
    private static final ThreadLocal<zza> zzik;
    private static final VersionPolicy.zza zzil;
    private static final VersionPolicy zzim;
    private final Context zzin;
    
    static {
        DynamiteModule.zzij = -1;
        zzik = new ThreadLocal<zza>();
        zzil = (VersionPolicy.zza)new com.google.android.gms.dynamite.zza();
        PREFER_REMOTE = (VersionPolicy)new com.google.android.gms.dynamite.zzb();
        PREFER_LOCAL = (VersionPolicy)new zzc();
        PREFER_HIGHEST_OR_LOCAL_VERSION = (VersionPolicy)new zzd();
        PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = (VersionPolicy)new zze();
        PREFER_HIGHEST_OR_REMOTE_VERSION = (VersionPolicy)new zzf();
        zzim = (VersionPolicy)new zzg();
    }
    
    private DynamiteModule(final Context context) {
        this.zzin = Preconditions.checkNotNull(context);
    }
    
    @KeepForSdk
    public static int getLocalVersion(final Context context, final String s) {
        try {
            final Class<?> loadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf(s).length() + 61).append("com.google.android.gms.dynamite.descriptors.").append(s).append(".ModuleDescriptor").toString());
            final Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            final Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (!declaredField.get(null).equals(s)) {
                final String value = String.valueOf(declaredField.get(null));
                Log.e("DynamiteModule", new StringBuilder(String.valueOf(value).length() + 51 + String.valueOf(s).length()).append("Module descriptor id '").append(value).append("' didn't match expected id '").append(s).append("'").toString());
                return 0;
            }
            return declaredField2.getInt(null);
        }
        catch (ClassNotFoundException ex2) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(s).length() + 45).append("Local module descriptor class for ").append(s).append(" not found.").toString());
        }
        catch (Exception ex) {
            final String value2 = String.valueOf(ex.getMessage());
            String concat;
            if (value2.length() != 0) {
                concat = "Failed to load module descriptor class: ".concat(value2);
            }
            else {
                concat = new String("Failed to load module descriptor class: ");
            }
            Log.e("DynamiteModule", concat);
            goto Label_0188;
        }
    }
    
    @KeepForSdk
    public static int getRemoteVersion(final Context context, final String s) {
        return zza(context, s, false);
    }
    
    @KeepForSdk
    public static DynamiteModule load(final Context context, final VersionPolicy versionPolicy, final String s) throws LoadingException {
        final zza zza = DynamiteModule.zzik.get();
        final zza zza2 = new zza(null);
        DynamiteModule.zzik.set(zza2);
        VersionPolicy.zzb zza3;
        try {
            zza3 = versionPolicy.zza(context, s, DynamiteModule.zzil);
            Log.i("DynamiteModule", new StringBuilder(String.valueOf(s).length() + 68 + String.valueOf(s).length()).append("Considering local module ").append(s).append(":").append(zza3.zzir).append(" and remote module ").append(s).append(":").append(zza3.zzis).toString());
            if (zza3.zzit == 0 || (zza3.zzit == -1 && zza3.zzir == 0) || (zza3.zzit == 1 && zza3.zzis == 0)) {
                throw new LoadingException(new StringBuilder(91).append("No acceptable module found. Local version is ").append(zza3.zzir).append(" and remote version is ").append(zza3.zzis).append(".").toString(), (com.google.android.gms.dynamite.zza)null);
            }
        }
        finally {
            if (zza2.zzio != null) {
                zza2.zzio.close();
            }
            DynamiteModule.zzik.set(zza);
        }
        if (zza3.zzit == -1) {
            final DynamiteModule zze = zze(context, s);
            if (zza2.zzio != null) {
                zza2.zzio.close();
            }
            DynamiteModule.zzik.set(zza);
            return zze;
        }
        if (zza3.zzit == 1) {
            try {
                final DynamiteModule zza4 = zza(context, s, zza3.zzis);
                if (zza2.zzio != null) {
                    zza2.zzio.close();
                }
                DynamiteModule.zzik.set(zza);
                return zza4;
            }
            catch (LoadingException ex) {
                final String value = String.valueOf(ex.getMessage());
                String concat;
                if (value.length() != 0) {
                    concat = "Failed to load remote module: ".concat(value);
                }
                else {
                    concat = new String("Failed to load remote module: ");
                }
                Log.w("DynamiteModule", concat);
                if (zza3.zzir != 0 && versionPolicy.zza(context, s, (VersionPolicy.zza)new zzb(zza3.zzir, 0)).zzit == -1) {
                    final DynamiteModule zze2 = zze(context, s);
                    if (zza2.zzio != null) {
                        zza2.zzio.close();
                    }
                    DynamiteModule.zzik.set(zza);
                    return zze2;
                }
                throw new LoadingException("Remote load failed. No local fallback found.", ex, null);
            }
        }
        throw new LoadingException(new StringBuilder(47).append("VersionPolicy returned invalid code:").append(zza3.zzit).toString(), (com.google.android.gms.dynamite.zza)null);
    }
    
    public static int zza(final Context p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: monitorenter   
        //     3: getstatic       com/google/android/gms/dynamite/DynamiteModule.zzif:Ljava/lang/Boolean;
        //     6: astore          6
        //     8: aload           6
        //    10: astore          5
        //    12: aload           6
        //    14: ifnonnull       84
        //    17: aload_0        
        //    18: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //    21: invokevirtual   android/content/Context.getClassLoader:()Ljava/lang/ClassLoader;
        //    24: ldc             Lcom/google/android/gms/dynamite/DynamiteModule$DynamiteLoaderClassLoader;.class
        //    26: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    29: invokevirtual   java/lang/ClassLoader.loadClass:(Ljava/lang/String;)Ljava/lang/Class;
        //    32: astore          6
        //    34: aload           6
        //    36: ldc_w           "sClassLoader"
        //    39: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    42: astore          5
        //    44: aload           6
        //    46: monitorenter   
        //    47: aload           5
        //    49: aconst_null    
        //    50: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    53: checkcast       Ljava/lang/ClassLoader;
        //    56: astore          7
        //    58: aload           7
        //    60: ifnull          121
        //    63: aload           7
        //    65: invokestatic    java/lang/ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //    68: if_acmpne       108
        //    71: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    74: astore          5
        //    76: aload           6
        //    78: monitorexit    
        //    79: aload           5
        //    81: putstatic       com/google/android/gms/dynamite/DynamiteModule.zzif:Ljava/lang/Boolean;
        //    84: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //    86: monitorexit    
        //    87: aload           5
        //    89: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    92: istore          4
        //    94: iload           4
        //    96: ifeq            374
        //    99: aload_0        
        //   100: aload_1        
        //   101: iload_2        
        //   102: invokestatic    com/google/android/gms/dynamite/DynamiteModule.zzc:(Landroid/content/Context;Ljava/lang/String;Z)I
        //   105: istore_3       
        //   106: iload_3        
        //   107: ireturn        
        //   108: aload           7
        //   110: invokestatic    com/google/android/gms/dynamite/DynamiteModule.zza:(Ljava/lang/ClassLoader;)V
        //   113: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   116: astore          5
        //   118: goto            76
        //   121: ldc_w           "com.google.android.gms"
        //   124: aload_0        
        //   125: invokevirtual   android/content/Context.getApplicationContext:()Landroid/content/Context;
        //   128: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   131: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   134: ifeq            154
        //   137: aload           5
        //   139: aconst_null    
        //   140: invokestatic    java/lang/ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //   143: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   146: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   149: astore          5
        //   151: goto            76
        //   154: aload_0        
        //   155: aload_1        
        //   156: iload_2        
        //   157: invokestatic    com/google/android/gms/dynamite/DynamiteModule.zzc:(Landroid/content/Context;Ljava/lang/String;Z)I
        //   160: istore_3       
        //   161: getstatic       com/google/android/gms/dynamite/DynamiteModule.zzii:Ljava/lang/String;
        //   164: ifnull          180
        //   167: getstatic       com/google/android/gms/dynamite/DynamiteModule.zzii:Ljava/lang/String;
        //   170: invokevirtual   java/lang/String.isEmpty:()Z
        //   173: istore          4
        //   175: iload           4
        //   177: ifeq            203
        //   180: aload           6
        //   182: monitorexit    
        //   183: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //   185: monitorexit    
        //   186: iload_3        
        //   187: ireturn        
        //   188: astore_1       
        //   189: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //   191: monitorexit    
        //   192: aload_1        
        //   193: athrow         
        //   194: astore_1       
        //   195: aload_0        
        //   196: aload_1        
        //   197: invokestatic    com/google/android/gms/common/util/CrashUtils.addDynamiteErrorToDropBox:(Landroid/content/Context;Ljava/lang/Throwable;)Z
        //   200: pop            
        //   201: aload_1        
        //   202: athrow         
        //   203: new             Lcom/google/android/gms/dynamite/zzh;
        //   206: dup            
        //   207: getstatic       com/google/android/gms/dynamite/DynamiteModule.zzii:Ljava/lang/String;
        //   210: invokestatic    java/lang/ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //   213: invokespecial   com/google/android/gms/dynamite/zzh.<init>:(Ljava/lang/String;Ljava/lang/ClassLoader;)V
        //   216: astore          7
        //   218: aload           7
        //   220: invokestatic    com/google/android/gms/dynamite/DynamiteModule.zza:(Ljava/lang/ClassLoader;)V
        //   223: aload           5
        //   225: aconst_null    
        //   226: aload           7
        //   228: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   231: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   234: putstatic       com/google/android/gms/dynamite/DynamiteModule.zzif:Ljava/lang/Boolean;
        //   237: aload           6
        //   239: monitorexit    
        //   240: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //   242: monitorexit    
        //   243: iload_3        
        //   244: ireturn        
        //   245: astore          7
        //   247: aload           5
        //   249: aconst_null    
        //   250: invokestatic    java/lang/ClassLoader.getSystemClassLoader:()Ljava/lang/ClassLoader;
        //   253: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   256: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   259: astore          5
        //   261: goto            76
        //   264: astore          5
        //   266: aload           6
        //   268: monitorexit    
        //   269: aload           5
        //   271: athrow         
        //   272: astore          5
        //   274: aload           5
        //   276: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   279: astore          5
        //   281: ldc             "DynamiteModule"
        //   283: new             Ljava/lang/StringBuilder;
        //   286: dup            
        //   287: aload           5
        //   289: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   292: invokevirtual   java/lang/String.length:()I
        //   295: bipush          30
        //   297: iadd           
        //   298: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   301: ldc_w           "Failed to load module via V2: "
        //   304: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   307: aload           5
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   315: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   318: pop            
        //   319: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //   322: astore          5
        //   324: goto            79
        //   327: astore_1       
        //   328: aload_1        
        //   329: invokevirtual   com/google/android/gms/dynamite/DynamiteModule$LoadingException.getMessage:()Ljava/lang/String;
        //   332: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   335: astore_1       
        //   336: aload_1        
        //   337: invokevirtual   java/lang/String.length:()I
        //   340: ifeq            360
        //   343: ldc_w           "Failed to retrieve remote module version: "
        //   346: aload_1        
        //   347: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   350: astore_1       
        //   351: ldc             "DynamiteModule"
        //   353: aload_1        
        //   354: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   357: pop            
        //   358: iconst_0       
        //   359: ireturn        
        //   360: new             Ljava/lang/String;
        //   363: dup            
        //   364: ldc_w           "Failed to retrieve remote module version: "
        //   367: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   370: astore_1       
        //   371: goto            351
        //   374: aload_0        
        //   375: aload_1        
        //   376: iload_2        
        //   377: invokestatic    com/google/android/gms/dynamite/DynamiteModule.zzb:(Landroid/content/Context;Ljava/lang/String;Z)I
        //   380: istore_3       
        //   381: iload_3        
        //   382: ireturn        
        //   383: astore          5
        //   385: goto            113
        //   388: astore          5
        //   390: goto            274
        //   393: astore          5
        //   395: goto            274
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                             
        //  -----  -----  -----  -----  -----------------------------------------------------------------
        //  0      3      194    203    Ljava/lang/Throwable;
        //  3      8      188    194    Any
        //  17     47     272    274    Ljava/lang/ClassNotFoundException;
        //  17     47     393    398    Ljava/lang/IllegalAccessException;
        //  17     47     388    393    Ljava/lang/NoSuchFieldException;
        //  17     47     188    194    Any
        //  47     58     264    272    Any
        //  63     76     264    272    Any
        //  76     79     264    272    Any
        //  79     84     188    194    Any
        //  84     87     188    194    Any
        //  87     94     194    203    Ljava/lang/Throwable;
        //  99     106    327    374    Lcom/google/android/gms/dynamite/DynamiteModule$LoadingException;
        //  99     106    194    203    Ljava/lang/Throwable;
        //  108    113    383    388    Lcom/google/android/gms/dynamite/DynamiteModule$LoadingException;
        //  108    113    264    272    Any
        //  113    118    264    272    Any
        //  121    151    264    272    Any
        //  154    175    245    264    Lcom/google/android/gms/dynamite/DynamiteModule$LoadingException;
        //  154    175    264    272    Any
        //  180    183    264    272    Any
        //  183    186    188    194    Any
        //  189    192    188    194    Any
        //  192    194    194    203    Ljava/lang/Throwable;
        //  203    237    245    264    Lcom/google/android/gms/dynamite/DynamiteModule$LoadingException;
        //  203    237    264    272    Any
        //  237    240    264    272    Any
        //  240    243    188    194    Any
        //  247    261    264    272    Any
        //  266    269    264    272    Any
        //  269    272    272    274    Ljava/lang/ClassNotFoundException;
        //  269    272    393    398    Ljava/lang/IllegalAccessException;
        //  269    272    388    393    Ljava/lang/NoSuchFieldException;
        //  269    272    188    194    Any
        //  274    324    188    194    Any
        //  328    351    194    203    Ljava/lang/Throwable;
        //  351    358    194    203    Ljava/lang/Throwable;
        //  360    371    194    203    Ljava/lang/Throwable;
        //  374    381    194    203    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 192, Size: 192
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    private static DynamiteModule zza(final Context ex, final String s, final int n) throws LoadingException {
        try {
            // monitorenter(DynamiteModule.class)
            final Boolean b = DynamiteModule.zzif;
            final Class<DynamiteModule> clazz = DynamiteModule.class;
            // monitorexit(clazz)
            final Boolean b2 = b;
            if (b2 == null) {
                final String s2 = "Failed to determine which loading route to use.";
                final com.google.android.gms.dynamite.zza zza = null;
                throw new LoadingException(s2, zza);
            }
            goto Label_0049;
        }
        catch (RemoteException ex) {
            throw new LoadingException("Failed to load remote module.", (Throwable)ex, null);
        }
        catch (LoadingException ex) {
            throw ex;
        }
        catch (Throwable t) {
            CrashUtils.addDynamiteErrorToDropBox((Context)ex, t);
            throw new LoadingException("Failed to load remote module.", t, null);
        }
        try {
            final Boolean b = DynamiteModule.zzif;
            final Class<DynamiteModule> clazz = DynamiteModule.class;
            // monitorexit(clazz)
            final Boolean b2 = b;
            if (b2 == null) {
                final String s2 = "Failed to determine which loading route to use.";
                final com.google.android.gms.dynamite.zza zza = null;
                throw new LoadingException(s2, zza);
            }
            goto Label_0049;
        }
        finally {}
        final zzi zzi;
        IObjectWrapper objectWrapper;
        if (zzi.zzak() >= 2) {
            final String s3;
            objectWrapper = zzi.zzb(ObjectWrapper.wrap(ex), s3, n);
        }
        else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
            final String s3;
            objectWrapper = zzi.zza(ObjectWrapper.wrap(ex), s3, n);
        }
        if (ObjectWrapper.unwrap(objectWrapper) == null) {
            throw new LoadingException("Failed to load remote module.", (com.google.android.gms.dynamite.zza)null);
        }
        return new DynamiteModule((Context)ObjectWrapper.unwrap(objectWrapper));
    }
    
    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader zzih) throws LoadingException {
        try {
            zzih = (InstantiationException)((ClassLoader)zzih).loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
            if (zzih == null) {
                zzih = null;
            }
            else {
                final IInterface queryLocalInterface = ((IBinder)zzih).queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzk) {
                    zzih = (InstantiationException)queryLocalInterface;
                }
                else {
                    zzih = (InstantiationException)new zzl((IBinder)zzih);
                }
            }
            DynamiteModule.zzih = (zzk)zzih;
        }
        catch (ClassNotFoundException ex) {}
        catch (InstantiationException zzih) {
            goto Label_0074;
        }
        catch (IllegalAccessException zzih) {
            goto Label_0074;
        }
        catch (NoSuchMethodException zzih) {
            goto Label_0074;
        }
        catch (InvocationTargetException zzih) {
            goto Label_0074;
        }
    }
    
    private static Boolean zzaj() {
        while (true) {
            while (true) {
                synchronized (DynamiteModule.class) {
                    if (DynamiteModule.zzij >= 2) {
                        // monitorexit(DynamiteModule.class)
                        return true;
                    }
                }
                final boolean b = false;
                continue;
            }
        }
    }
    
    private static int zzb(final Context context, final String s, final boolean b) {
        final zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzak() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), s, b);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), s, b);
        }
        catch (RemoteException ex) {
            final String value = String.valueOf(ex.getMessage());
            String concat;
            if (value.length() != 0) {
                concat = "Failed to retrieve remote module version: ".concat(value);
            }
            else {
                concat = new String("Failed to retrieve remote module version: ");
            }
            Log.w("DynamiteModule", concat);
            return 0;
        }
    }
    
    private static DynamiteModule zzb(Context applicationContext, final String s, final int n) throws LoadingException, RemoteException {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(s).length() + 51).append("Selected remote version of ").append(s).append(", version >= ").append(n).toString());
        final zzk zzih;
        synchronized (DynamiteModule.class) {
            zzih = DynamiteModule.zzih;
            // monitorexit(DynamiteModule.class)
            if (zzih == null) {
                throw new LoadingException("DynamiteLoaderV2 was not cached.", (com.google.android.gms.dynamite.zza)null);
            }
        }
        final zza zza = DynamiteModule.zzik.get();
        if (zza == null || zza.zzio == null) {
            throw new LoadingException("No result cursor", (com.google.android.gms.dynamite.zza)null);
        }
        final Context context;
        applicationContext = context.getApplicationContext();
        final Cursor zzio = zza.zzio;
        ObjectWrapper.wrap((Object)null);
        IObjectWrapper objectWrapper;
        if (zzaj()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            objectWrapper = zzih.zzb(ObjectWrapper.wrap(applicationContext), s, n, ObjectWrapper.wrap(zzio));
        }
        else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            objectWrapper = zzih.zza(ObjectWrapper.wrap(applicationContext), s, n, ObjectWrapper.wrap(zzio));
        }
        applicationContext = (Context)ObjectWrapper.unwrap(objectWrapper);
        if (applicationContext == null) {
            throw new LoadingException("Failed to get module context", (com.google.android.gms.dynamite.zza)null);
        }
        return new DynamiteModule(applicationContext);
    }
    
    private static int zzc(Context query, String s, final boolean b) throws LoadingException {
        while (true) {
            try {
                Object contentResolver = query.getContentResolver();
                String s2;
                if (b) {
                    s2 = "api_force_staging";
                }
                else {
                    s2 = "api";
                }
                query = (Context)((ContentResolver)contentResolver).query(Uri.parse(new StringBuilder(String.valueOf(s2).length() + 42 + String.valueOf(s).length()).append("content://com.google.android.gms.chimera/").append(s2).append("/").append(s).toString()), (String[])null, (String)null, (String[])null, (String)null);
                while (true) {
                    if (query != null) {
                        s = (String)query;
                        Label_0152: {
                            try {
                                try {
                                    if (!((Cursor)query).moveToFirst()) {
                                        s = (String)query;
                                        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                        s = (String)query;
                                        throw new LoadingException("Failed to connect to dynamite module ContentResolver.", (com.google.android.gms.dynamite.zza)null);
                                    }
                                    break Label_0152;
                                }
                                catch (Exception ex) {}
                                s = (String)query;
                                if (contentResolver instanceof LoadingException) {
                                    s = (String)query;
                                    throw contentResolver;
                                }
                                throw new LoadingException("V2 version check failed", (Throwable)contentResolver, null);
                            }
                            finally {}
                            if (s != null) {
                                ((Cursor)s).close();
                            }
                            throw query;
                        }
                        final int int1 = ((Cursor)query).getInt(0);
                        contentResolver = query;
                        Label_0259: {
                            if (int1 <= 0) {
                                break Label_0259;
                            }
                            synchronized (DynamiteModule.class) {
                                DynamiteModule.zzii = ((Cursor)query).getString(2);
                                final int columnIndex = ((Cursor)query).getColumnIndex("loaderVersion");
                                if (columnIndex >= 0) {
                                    DynamiteModule.zzij = ((Cursor)query).getInt(columnIndex);
                                }
                                // monitorexit(DynamiteModule.class)
                                final zza zza = DynamiteModule.zzik.get();
                                contentResolver = query;
                                if (zza != null) {
                                    contentResolver = query;
                                    if (zza.zzio == null) {
                                        zza.zzio = (Cursor)query;
                                        contentResolver = null;
                                    }
                                }
                                if (contentResolver != null) {
                                    ((Cursor)contentResolver).close();
                                }
                                return int1;
                            }
                        }
                        throw new LoadingException("V2 version check failed", (Throwable)contentResolver, null);
                    }
                    continue;
                }
            }
            catch (Exception contentResolver) {}
            finally {
                s = null;
                continue;
            }
            break;
        }
    }
    
    private static DynamiteModule zze(final Context context, String s) {
        s = String.valueOf(s);
        if (s.length() != 0) {
            s = "Selected local version of ".concat(s);
        }
        else {
            s = new String("Selected local version of ");
        }
        Log.i("DynamiteModule", s);
        return new DynamiteModule(context.getApplicationContext());
    }
    
    private static zzi zzj(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: monitorenter   
        //     3: getstatic       com/google/android/gms/dynamite/DynamiteModule.zzig:Lcom/google/android/gms/dynamite/zzi;
        //     6: ifnull          18
        //     9: getstatic       com/google/android/gms/dynamite/DynamiteModule.zzig:Lcom/google/android/gms/dynamite/zzi;
        //    12: astore_0       
        //    13: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //    15: monitorexit    
        //    16: aload_0        
        //    17: areturn        
        //    18: invokestatic    com/google/android/gms/common/GoogleApiAvailabilityLight.getInstance:()Lcom/google/android/gms/common/GoogleApiAvailabilityLight;
        //    21: aload_0        
        //    22: invokevirtual   com/google/android/gms/common/GoogleApiAvailabilityLight.isGooglePlayServicesAvailable:(Landroid/content/Context;)I
        //    25: ifeq            33
        //    28: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //    30: monitorexit    
        //    31: aconst_null    
        //    32: areturn        
        //    33: aload_0        
        //    34: ldc_w           "com.google.android.gms"
        //    37: iconst_3       
        //    38: invokevirtual   android/content/Context.createPackageContext:(Ljava/lang/String;I)Landroid/content/Context;
        //    41: invokevirtual   android/content/Context.getClassLoader:()Ljava/lang/ClassLoader;
        //    44: ldc_w           "com.google.android.gms.chimera.container.DynamiteLoaderImpl"
        //    47: invokevirtual   java/lang/ClassLoader.loadClass:(Ljava/lang/String;)Ljava/lang/Class;
        //    50: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //    53: checkcast       Landroid/os/IBinder;
        //    56: astore_0       
        //    57: aload_0        
        //    58: ifnonnull       82
        //    61: aconst_null    
        //    62: astore_0       
        //    63: aload_0        
        //    64: ifnull          150
        //    67: aload_0        
        //    68: putstatic       com/google/android/gms/dynamite/DynamiteModule.zzig:Lcom/google/android/gms/dynamite/zzi;
        //    71: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //    73: monitorexit    
        //    74: aload_0        
        //    75: areturn        
        //    76: astore_0       
        //    77: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //    79: monitorexit    
        //    80: aload_0        
        //    81: athrow         
        //    82: aload_0        
        //    83: ldc_w           "com.google.android.gms.dynamite.IDynamiteLoader"
        //    86: invokeinterface android/os/IBinder.queryLocalInterface:(Ljava/lang/String;)Landroid/os/IInterface;
        //    91: astore_1       
        //    92: aload_1        
        //    93: instanceof      Lcom/google/android/gms/dynamite/zzi;
        //    96: ifeq            107
        //    99: aload_1        
        //   100: checkcast       Lcom/google/android/gms/dynamite/zzi;
        //   103: astore_0       
        //   104: goto            63
        //   107: new             Lcom/google/android/gms/dynamite/zzj;
        //   110: dup            
        //   111: aload_0        
        //   112: invokespecial   com/google/android/gms/dynamite/zzj.<init>:(Landroid/os/IBinder;)V
        //   115: astore_0       
        //   116: goto            63
        //   119: astore_0       
        //   120: aload_0        
        //   121: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   124: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   127: astore_0       
        //   128: aload_0        
        //   129: invokevirtual   java/lang/String.length:()I
        //   132: ifeq            155
        //   135: ldc_w           "Failed to load IDynamiteLoader from GmsCore: "
        //   138: aload_0        
        //   139: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   142: astore_0       
        //   143: ldc             "DynamiteModule"
        //   145: aload_0        
        //   146: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   149: pop            
        //   150: ldc             Lcom/google/android/gms/dynamite/DynamiteModule;.class
        //   152: monitorexit    
        //   153: aconst_null    
        //   154: areturn        
        //   155: new             Ljava/lang/String;
        //   158: dup            
        //   159: ldc_w           "Failed to load IDynamiteLoader from GmsCore: "
        //   162: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   165: astore_0       
        //   166: goto            143
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      16     76     82     Any
        //  18     31     76     82     Any
        //  33     57     119    169    Ljava/lang/Exception;
        //  33     57     76     82     Any
        //  67     71     119    169    Ljava/lang/Exception;
        //  67     71     76     82     Any
        //  71     74     76     82     Any
        //  77     80     76     82     Any
        //  82     104    119    169    Ljava/lang/Exception;
        //  82     104    76     82     Any
        //  107    116    119    169    Ljava/lang/Exception;
        //  107    116    76     82     Any
        //  120    143    76     82     Any
        //  143    150    76     82     Any
        //  150    153    76     82     Any
        //  155    166    76     82     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
    
    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzin;
    }
    
    @KeepForSdk
    public final IBinder instantiate(final String o) throws LoadingException {
        try {
            return (IBinder)this.zzin.getClassLoader().loadClass((String)o).newInstance();
        }
        catch (ClassNotFoundException ex) {}
        catch (InstantiationException binder) {
            goto Label_0021;
        }
        catch (IllegalAccessException binder) {
            goto Label_0021;
        }
    }
    
    @KeepForSdk
    public static class LoadingException extends Exception
    {
        private LoadingException(final String s) {
            super(s);
        }
        
        private LoadingException(final String s, final Throwable t) {
            super(s, t);
        }
    }
    
    @DynamiteApi
    public static class DynamiteLoaderClassLoader
    {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }
    
    public interface VersionPolicy
    {
        zzb zza(final Context p0, final String p1, final zza p2) throws LoadingException;
        
        public interface zza
        {
            int getLocalVersion(final Context p0, final String p1);
            
            int zza(final Context p0, final String p1, final boolean p2) throws LoadingException;
        }
        
        public static final class zzb
        {
            public int zzir;
            public int zzis;
            public int zzit;
            
            public zzb() {
                this.zzir = 0;
                this.zzis = 0;
                this.zzit = 0;
            }
        }
    }
    
    private static final class zza
    {
        public Cursor zzio;
    }
    
    private static final class zzb implements VersionPolicy.zza
    {
        private final int zzip;
        private final int zziq;
        
        public zzb(final int zzip, final int n) {
            this.zzip = zzip;
            this.zziq = 0;
        }
        
        @Override
        public final int getLocalVersion(final Context context, final String s) {
            return this.zzip;
        }
        
        @Override
        public final int zza(final Context context, final String s, final boolean b) {
            return 0;
        }
    }
}
