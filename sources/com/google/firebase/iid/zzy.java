package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.firebase_messaging.zzc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;

final class zzy {
    zzy() {
    }

    @WorkerThread
    final zzz zzb(Context context, String str) throws zzaa {
        zzz zzd = zzd(context, str);
        return zzd != null ? zzd : zzc(context, str);
    }

    @WorkerThread
    final zzz zzc(Context context, String str) {
        zzz zzz = new zzz(zza.zzb(), System.currentTimeMillis());
        zzz zza = zza(context, str, zzz, true);
        if (zza == null || zza.equals(zzz)) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Generated new key");
            }
            zza(context, str, zzz);
            return zzz;
        } else if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            return zza;
        } else {
            Log.d("FirebaseInstanceId", "Loaded key after generating new one, using loaded one");
            return zza;
        }
    }

    static void zza(Context context) {
        for (File file : zzb(context).listFiles()) {
            if (file.getName().startsWith("com.google.InstanceId")) {
                file.delete();
            }
        }
    }

    @Nullable
    private final zzz zzd(Context context, String str) throws zzaa {
        zzaa zzaa;
        zzaa zzaa2;
        try {
            zzz zze = zze(context, str);
            if (zze != null) {
                zza(context, str, zze);
                return zze;
            }
            zzaa = null;
            try {
                zze = zza(context.getSharedPreferences("com.google.android.gms.appid", 0), str);
                if (zze != null) {
                    zza(context, str, zze, false);
                    return zze;
                }
                zzaa2 = zzaa;
                if (zzaa2 == null) {
                    return null;
                }
                throw zzaa2;
            } catch (zzaa e) {
                zzaa2 = e;
            }
        } catch (zzaa zzaa22) {
            zzaa = zzaa22;
        }
    }

    private static KeyPair zzc(String str, String str2) throws zzaa {
        Exception e;
        String valueOf;
        try {
            byte[] decode = Base64.decode(str, 8);
            byte[] decode2 = Base64.decode(str2, 8);
            try {
                KeyFactory instance = KeyFactory.getInstance("RSA");
                return new KeyPair(instance.generatePublic(new X509EncodedKeySpec(decode)), instance.generatePrivate(new PKCS8EncodedKeySpec(decode2)));
            } catch (InvalidKeySpecException e2) {
                e = e2;
                valueOf = String.valueOf(e);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid key stored ").append(valueOf).toString());
                throw new zzaa(e);
            } catch (NoSuchAlgorithmException e3) {
                e = e3;
                valueOf = String.valueOf(e);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 19).append("Invalid key stored ").append(valueOf).toString());
                throw new zzaa(e);
            }
        } catch (Exception e4) {
            throw new zzaa(e4);
        }
    }

    @Nullable
    private final zzz zze(Context context, String str) throws zzaa {
        Object e;
        String valueOf;
        File zzf = zzf(context, str);
        if (!zzf.exists()) {
            return null;
        }
        try {
            return zza(zzf);
        } catch (zzaa e2) {
            e = e2;
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                valueOf = String.valueOf(e);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 40).append("Failed to read key from file, retrying: ").append(valueOf).toString());
            }
            try {
                return zza(zzf);
            } catch (Exception e3) {
                String valueOf2 = String.valueOf(e3);
                Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf2).length() + 45).append("IID file exists, but failed to read from it: ").append(valueOf2).toString());
                throw new zzaa(e3);
            }
        } catch (IOException e4) {
            e = e4;
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                valueOf = String.valueOf(e);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 40).append("Failed to read key from file, retrying: ").append(valueOf).toString());
            }
            return zza(zzf);
        }
    }

    @Nullable
    private final zzz zza(Context context, String str, zzz zzz, boolean z) {
        Object e;
        String valueOf;
        Throwable th;
        Throwable th2;
        Throwable th3;
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to properties file");
        }
        Properties properties = new Properties();
        properties.setProperty("pub", zzz.zzv());
        properties.setProperty("pri", zzz.zzw());
        properties.setProperty("cre", String.valueOf(zzz.zzbs));
        File zzf = zzf(context, str);
        try {
            zzf.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(zzf, "rw");
            try {
                FileChannel channel = randomAccessFile.getChannel();
                try {
                    channel.lock();
                    if (z && channel.size() > 0) {
                        try {
                            channel.position(0);
                            zzz = zza(channel);
                            if (channel != null) {
                                zza(null, channel);
                            }
                            zza(null, randomAccessFile);
                            return zzz;
                        } catch (IOException e2) {
                            e = e2;
                            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                                valueOf = String.valueOf(e);
                                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 64).append("Tried reading key pair before writing new one, but failed with: ").append(valueOf).toString());
                            }
                            channel.position(0);
                            properties.store(Channels.newOutputStream(channel), null);
                            if (channel != null) {
                                zza(null, channel);
                            }
                            zza(null, randomAccessFile);
                            return zzz;
                        } catch (zzaa e3) {
                            e = e3;
                            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                                valueOf = String.valueOf(e);
                                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 64).append("Tried reading key pair before writing new one, but failed with: ").append(valueOf).toString());
                            }
                            channel.position(0);
                            properties.store(Channels.newOutputStream(channel), null);
                            if (channel != null) {
                                zza(null, channel);
                            }
                            zza(null, randomAccessFile);
                            return zzz;
                        }
                    }
                    channel.position(0);
                    properties.store(Channels.newOutputStream(channel), null);
                    if (channel != null) {
                        zza(null, channel);
                    }
                    zza(null, randomAccessFile);
                    return zzz;
                } catch (Throwable th22) {
                    th3 = th22;
                    th22 = th;
                    th = th3;
                }
                if (channel != null) {
                    zza(th22, channel);
                }
                throw th;
                zza(th22, randomAccessFile);
                throw th;
            } catch (Throwable th222) {
                th3 = th222;
                th222 = th;
                th = th3;
            }
        } catch (IOException e4) {
            valueOf = String.valueOf(e4);
            Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 21).append("Failed to write key: ").append(valueOf).toString());
            return null;
        }
    }

    private static File zzb(Context context) {
        File noBackupFilesDir = ContextCompat.getNoBackupFilesDir(context);
        if (noBackupFilesDir != null && noBackupFilesDir.isDirectory()) {
            return noBackupFilesDir;
        }
        Log.w("FirebaseInstanceId", "noBackupFilesDir doesn't exist, using regular files directory instead");
        return context.getFilesDir();
    }

    private static File zzf(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = "com.google.InstanceId.properties";
        } else {
            try {
                str2 = Base64.encodeToString(str.getBytes("UTF-8"), 11);
                str2 = new StringBuilder(String.valueOf(str2).length() + 33).append("com.google.InstanceId_").append(str2).append(".properties").toString();
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
        return new File(zzb(context), str2);
    }

    private final zzz zza(File file) throws zzaa, IOException {
        Throwable th = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        Throwable th2;
        try {
            Throwable th3;
            FileChannel channel = fileInputStream.getChannel();
            try {
                channel.lock(0, Long.MAX_VALUE, true);
                zzz zza = zza(channel);
                if (channel != null) {
                    zza(null, channel);
                }
                zza(null, fileInputStream);
                return zza;
            } catch (Throwable th32) {
                Throwable th4 = th32;
                th32 = th2;
                th2 = th4;
            }
            zza(th, fileInputStream);
            throw th2;
            if (channel != null) {
                zza(th32, channel);
            }
            throw th2;
        } catch (Throwable th5) {
            th = th2;
            th2 = th5;
        }
    }

    private static zzz zza(FileChannel fileChannel) throws zzaa, IOException {
        Properties properties = new Properties();
        properties.load(Channels.newInputStream(fileChannel));
        String property = properties.getProperty("pub");
        String property2 = properties.getProperty("pri");
        if (property == null || property2 == null) {
            throw new zzaa("Invalid properties file");
        }
        try {
            return new zzz(zzc(property, property2), Long.parseLong(properties.getProperty("cre")));
        } catch (Exception e) {
            throw new zzaa(e);
        }
    }

    @Nullable
    private static zzz zza(SharedPreferences sharedPreferences, String str) throws zzaa {
        String string = sharedPreferences.getString(zzaw.zzd(str, "|P|"), null);
        String string2 = sharedPreferences.getString(zzaw.zzd(str, "|K|"), null);
        if (string == null || string2 == null) {
            return null;
        }
        return new zzz(zzc(string, string2), zzb(sharedPreferences, str));
    }

    private final void zza(Context context, String str, zzz zzz) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.appid", 0);
        try {
            if (zzz.equals(zza(sharedPreferences, str))) {
                return;
            }
        } catch (zzaa e) {
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            Log.d("FirebaseInstanceId", "Writing key to shared preferences");
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(zzaw.zzd(str, "|P|"), zzz.zzv());
        edit.putString(zzaw.zzd(str, "|K|"), zzz.zzw());
        edit.putString(zzaw.zzd(str, "cre"), String.valueOf(zzz.zzbs));
        edit.commit();
    }

    private static long zzb(SharedPreferences sharedPreferences, String str) {
        String string = sharedPreferences.getString(zzaw.zzd(str, "cre"), null);
        if (string != null) {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    private static /* synthetic */ void zza(Throwable th, FileChannel fileChannel) {
        if (th != null) {
            try {
                fileChannel.close();
                return;
            } catch (Throwable th2) {
                zzc.zza(th, th2);
                return;
            }
        }
        fileChannel.close();
    }

    private static /* synthetic */ void zza(Throwable th, RandomAccessFile randomAccessFile) {
        if (th != null) {
            try {
                randomAccessFile.close();
                return;
            } catch (Throwable th2) {
                zzc.zza(th, th2);
                return;
            }
        }
        randomAccessFile.close();
    }

    private static /* synthetic */ void zza(Throwable th, FileInputStream fileInputStream) {
        if (th != null) {
            try {
                fileInputStream.close();
                return;
            } catch (Throwable th2) {
                zzc.zza(th, th2);
                return;
            }
        }
        fileInputStream.close();
    }
}
