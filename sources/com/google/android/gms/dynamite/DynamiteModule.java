package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public final class DynamiteModule {
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = new zzc();
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = new zzb();
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzif;
    @GuardedBy("DynamiteModule.class")
    private static zzi zzig;
    @GuardedBy("DynamiteModule.class")
    private static zzk zzih;
    @GuardedBy("DynamiteModule.class")
    private static String zzii;
    @GuardedBy("DynamiteModule.class")
    private static int zzij = -1;
    private static final ThreadLocal<zza> zzik = new ThreadLocal();
    private static final zza zzil = new zza();
    private static final VersionPolicy zzim = new zzg();
    private final Context zzin;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;
    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface VersionPolicy {

        public interface zza {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        public static class zzb {
            public int zzir = 0;
            public int zzis = 0;
            public int zzit = 0;
        }

        zzb zza(Context context, String str, zza zza) throws LoadingException;
    }

    private static class zza {
        public Cursor zzio;

        private zza() {
        }
    }

    private static class zzb implements zza {
        private final int zzip;
        private final int zziq = 0;

        public zzb(int i, int i2) {
            this.zzip = i;
        }

        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        public final int getLocalVersion(Context context, String str) {
            return this.zzip;
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        zzb zza;
        zza zza2 = (zza) zzik.get();
        zza zza3 = new zza();
        zzik.set(zza3);
        DynamiteModule zze;
        try {
            zza = versionPolicy.zza(context, str, zzil);
            Log.i("DynamiteModule", new StringBuilder((String.valueOf(str).length() + 68) + String.valueOf(str).length()).append("Considering local module ").append(str).append(":").append(zza.zzir).append(" and remote module ").append(str).append(":").append(zza.zzis).toString());
            if (zza.zzit == 0 || ((zza.zzit == -1 && zza.zzir == 0) || (zza.zzit == 1 && zza.zzis == 0))) {
                throw new LoadingException("No acceptable module found. Local version is " + zza.zzir + " and remote version is " + zza.zzis + ".");
            } else if (zza.zzit == -1) {
                zze = zze(context, str);
                if (zza3.zzio != null) {
                    zza3.zzio.close();
                }
                zzik.set(zza2);
                return zze;
            } else if (zza.zzit == 1) {
                zze = zza(context, str, zza.zzis);
                if (zza3.zzio != null) {
                    zza3.zzio.close();
                }
                zzik.set(zza2);
                return zze;
            } else {
                throw new LoadingException("VersionPolicy returned invalid code:" + zza.zzit);
            }
        } catch (Throwable e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to load remote module: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            if (zza.zzir == 0 || versionPolicy.zza(context, str, new zzb(zza.zzir, 0)).zzit != -1) {
                throw new LoadingException("Remote load failed. No local fallback found.", e);
            }
            zze = zze(context, str);
            if (zza3.zzio != null) {
                zza3.zzio.close();
            }
            zzik.set(zza2);
            return zze;
        } catch (Throwable th) {
            if (zza3.zzio != null) {
                zza3.zzio.close();
            }
            zzik.set(zza2);
        }
    }

    @KeepForSdk
    public static int getLocalVersion(Context context, String str) {
        String valueOf;
        try {
            Class loadClass = context.getApplicationContext().getClassLoader().loadClass(new StringBuilder(String.valueOf(str).length() + 61).append("com.google.android.gms.dynamite.descriptors.").append(str).append(".ModuleDescriptor").toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            valueOf = String.valueOf(declaredField.get(null));
            Log.e("DynamiteModule", new StringBuilder((String.valueOf(valueOf).length() + 51) + String.valueOf(str).length()).append("Module descriptor id '").append(valueOf).append("' didn't match expected id '").append(str).append("'").toString());
            return 0;
        } catch (ClassNotFoundException e) {
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 45).append("Local module descriptor class for ").append(str).append(" not found.").toString());
            return 0;
        } catch (Exception e2) {
            valueOf = "DynamiteModule";
            String str2 = "Failed to load module descriptor class: ";
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e(valueOf, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
            return 0;
        }
    }

    public static int zza(Context context, String str, boolean z) {
        String valueOf;
        try {
            Boolean bool;
            Object e;
            synchronized (DynamiteModule.class) {
                bool = zzif;
                if (bool == null) {
                    try {
                        Class loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                        Field declaredField = loadClass.getDeclaredField("sClassLoader");
                        synchronized (loadClass) {
                            ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                            if (classLoader != null) {
                                if (classLoader == ClassLoader.getSystemClassLoader()) {
                                    bool = Boolean.FALSE;
                                } else {
                                    try {
                                        zza(classLoader);
                                    } catch (LoadingException e2) {
                                    }
                                    bool = Boolean.TRUE;
                                }
                            } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int zzc = zzc(context, str, z);
                                    if (zzii == null || zzii.isEmpty()) {
                                        return zzc;
                                    }
                                    ClassLoader zzh = new zzh(zzii, ClassLoader.getSystemClassLoader());
                                    zza(zzh);
                                    declaredField.set(null, zzh);
                                    zzif = Boolean.TRUE;
                                    return zzc;
                                } catch (LoadingException e3) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                    zzif = bool;
                                    if (bool.booleanValue()) {
                                        return zzb(context, str, z);
                                    }
                                    try {
                                        return zzc(context, str, z);
                                    } catch (LoadingException e4) {
                                        String str2 = "DynamiteModule";
                                        String str3 = "Failed to retrieve remote module version: ";
                                        valueOf = String.valueOf(e4.getMessage());
                                        Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                                        return 0;
                                    }
                                }
                            }
                        }
                    } catch (ClassNotFoundException e5) {
                        e = e5;
                    } catch (IllegalAccessException e6) {
                        e = e6;
                    } catch (NoSuchFieldException e7) {
                        e = e7;
                    }
                }
            }
            valueOf = String.valueOf(e);
            Log.w("DynamiteModule", new StringBuilder(String.valueOf(valueOf).length() + 30).append("Failed to load module via V2: ").append(valueOf).toString());
            bool = Boolean.FALSE;
            zzif = bool;
            if (bool.booleanValue()) {
                return zzb(context, str, z);
            }
            return zzc(context, str, z);
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        zzi zzj = zzj(context);
        if (zzj == null) {
            return 0;
        }
        try {
            if (zzj.zzak() >= 2) {
                return zzj.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzj.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String str2 = "DynamiteModule";
            String str3 = "Failed to retrieve remote module version: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return 0;
        }
    }

    private static int zzc(Context context, String str, boolean z) throws LoadingException {
        Cursor query;
        Throwable e;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            String str2 = z ? "api_force_staging" : "api";
            query = contentResolver.query(Uri.parse(new StringBuilder((String.valueOf(str2).length() + 42) + String.valueOf(str).length()).append("content://com.google.android.gms.chimera/").append(str2).append("/").append(str).toString()), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int i = query.getInt(0);
                        if (i > 0) {
                            synchronized (DynamiteModule.class) {
                                zzii = query.getString(2);
                                int columnIndex = query.getColumnIndex("loaderVersion");
                                if (columnIndex >= 0) {
                                    zzij = query.getInt(columnIndex);
                                }
                            }
                            zza zza = (zza) zzik.get();
                            if (zza != null && zza.zzio == null) {
                                zza.zzio = query;
                                query = null;
                            }
                        }
                        if (query != null) {
                            query.close();
                        }
                        return i;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.");
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                if (e instanceof LoadingException) {
                    throw e;
                }
                throw new LoadingException("V2 version check failed", e);
            } catch (Throwable th) {
                e = th;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String str2 = "DynamiteModule";
        String str3 = "Selected local version of ";
        String valueOf = String.valueOf(str);
        Log.i(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        try {
            Boolean bool;
            synchronized (DynamiteModule.class) {
                bool = zzif;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.");
            } else if (bool.booleanValue()) {
                return zzb(context, str, i);
            } else {
                Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
                zzi zzj = zzj(context);
                if (zzj == null) {
                    throw new LoadingException("Failed to create IDynamiteLoader.");
                }
                IObjectWrapper zzb;
                if (zzj.zzak() >= 2) {
                    zzb = zzj.zzb(ObjectWrapper.wrap(context), str, i);
                } else {
                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                    zzb = zzj.zza(ObjectWrapper.wrap(context), str, i);
                }
                if (ObjectWrapper.unwrap(zzb) != null) {
                    return new DynamiteModule((Context) ObjectWrapper.unwrap(zzb));
                }
                throw new LoadingException("Failed to load remote module.");
            }
        } catch (Throwable e) {
            throw new LoadingException("Failed to load remote module.", e);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable e3) {
            CrashUtils.addDynamiteErrorToDropBox(context, e3);
            LoadingException loadingException = new LoadingException("Failed to load remote module.", e3);
        }
    }

    private static zzi zzj(Context context) {
        synchronized (DynamiteModule.class) {
            zzi zzi;
            if (zzig != null) {
                zzi = zzig;
                return zzi;
            } else if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                    if (iBinder == null) {
                        zzi = null;
                    } else {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                        if (queryLocalInterface instanceof zzi) {
                            zzi = (zzi) queryLocalInterface;
                        } else {
                            Object zzj = new zzj(iBinder);
                        }
                    }
                    if (zzi != null) {
                        zzig = zzi;
                        return zzi;
                    }
                } catch (Exception e) {
                    String str = "DynamiteModule";
                    String str2 = "Failed to load IDynamiteLoader from GmsCore: ";
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                    return null;
                }
            }
        }
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzin;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        Log.i("DynamiteModule", new StringBuilder(String.valueOf(str).length() + 51).append("Selected remote version of ").append(str).append(", version >= ").append(i).toString());
        synchronized (DynamiteModule.class) {
            zzk zzk = zzih;
        }
        if (zzk == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.");
        }
        zza zza = (zza) zzik.get();
        if (zza == null || zza.zzio == null) {
            throw new LoadingException("No result cursor");
        }
        IObjectWrapper zzb;
        Context applicationContext = context.getApplicationContext();
        Cursor cursor = zza.zzio;
        ObjectWrapper.wrap(null);
        if (zzaj().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            zzb = zzk.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            zzb = zzk.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
        }
        Context context2 = (Context) ObjectWrapper.unwrap(zzb);
        if (context2 != null) {
            return new DynamiteModule(context2);
        }
        throw new LoadingException("Failed to get module context");
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zzij >= 2);
        }
        return valueOf;
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader) throws LoadingException {
        Throwable e;
        try {
            zzk zzk;
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzk = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzk) {
                    zzk = (zzk) queryLocalInterface;
                } else {
                    Object zzl = new zzl(iBinder);
                }
            }
            zzih = zzk;
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new LoadingException("Failed to instantiate dynamite loader", e);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw new LoadingException("Failed to instantiate dynamite loader", e);
        } catch (InstantiationException e4) {
            e = e4;
            throw new LoadingException("Failed to instantiate dynamite loader", e);
        } catch (InvocationTargetException e5) {
            e = e5;
            throw new LoadingException("Failed to instantiate dynamite loader", e);
        } catch (NoSuchMethodException e6) {
            e = e6;
            throw new LoadingException("Failed to instantiate dynamite loader", e);
        }
    }

    @KeepForSdk
    public final IBinder instantiate(String str) throws LoadingException {
        Throwable e;
        String str2;
        String valueOf;
        try {
            return (IBinder) this.zzin.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException e2) {
            e = e2;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (InstantiationException e3) {
            e = e3;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new LoadingException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        } catch (IllegalAccessException e4) {
            e = e4;
            str2 = "Failed to instantiate module class: ";
            valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
            }
            throw new LoadingException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), e);
        }
    }

    private DynamiteModule(Context context) {
        this.zzin = (Context) Preconditions.checkNotNull(context);
    }
}
