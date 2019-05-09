package net.hockeyapp.android.metrics;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import net.hockeyapp.android.utils.HockeyLog;

class Persistence {
    private static final String BIT_TELEMETRY_DIRECTORY = "/net.hockeyapp.android/telemetry/";
    private static final Object LOCK = new Object();
    private static final Integer MAX_FILE_COUNT = Integer.valueOf(50);
    private static final String TAG = "HA-MetricsPersistence";
    protected ArrayList<File> mServedFiles;
    protected final File mTelemetryDirectory;
    private final WeakReference<Context> mWeakContext;
    protected WeakReference<Sender> mWeakSender;

    protected Persistence(Context context, File telemetryDirectory, Sender sender) {
        this.mWeakContext = new WeakReference(context);
        this.mServedFiles = new ArrayList(51);
        this.mTelemetryDirectory = telemetryDirectory;
        this.mWeakSender = new WeakReference(sender);
        createDirectoriesIfNecessary();
    }

    protected Persistence(Context context, Sender sender) {
        this(context, new File(context.getFilesDir().getAbsolutePath() + BIT_TELEMETRY_DIRECTORY), null);
        setSender(sender);
    }

    protected void persist(String[] data) {
        if (isFreeSpaceAvailable()) {
            StringBuilder buffer = new StringBuilder();
            for (String aData : data) {
                if (buffer.length() > 0) {
                    buffer.append('\n');
                }
                buffer.append(aData);
            }
            if (writeToDisk(buffer.toString())) {
                getSender().triggerSending();
                return;
            }
            return;
        }
        HockeyLog.warn(TAG, "Failed to persist file: Too many files on disk.");
        getSender().triggerSending();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean writeToDisk(java.lang.String r11) {
        /*
        r10 = this;
        r6 = java.util.UUID.randomUUID();
        r5 = r6.toString();
        r6 = 0;
        r2 = java.lang.Boolean.valueOf(r6);
        r3 = 0;
        r7 = LOCK;	 Catch:{ Exception -> 0x006c }
        monitor-enter(r7);	 Catch:{ Exception -> 0x006c }
        r1 = new java.io.File;	 Catch:{ all -> 0x0069 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0069 }
        r6.<init>();	 Catch:{ all -> 0x0069 }
        r8 = r10.mTelemetryDirectory;	 Catch:{ all -> 0x0069 }
        r6 = r6.append(r8);	 Catch:{ all -> 0x0069 }
        r8 = "/";
        r6 = r6.append(r8);	 Catch:{ all -> 0x0069 }
        r6 = r6.append(r5);	 Catch:{ all -> 0x0069 }
        r6 = r6.toString();	 Catch:{ all -> 0x0069 }
        r1.<init>(r6);	 Catch:{ all -> 0x0069 }
        r4 = new java.io.FileOutputStream;	 Catch:{ all -> 0x0069 }
        r6 = 1;
        r4.<init>(r1, r6);	 Catch:{ all -> 0x0069 }
        r6 = r11.getBytes();	 Catch:{ all -> 0x00ac }
        r4.write(r6);	 Catch:{ all -> 0x00ac }
        r6 = "HA-MetricsPersistence";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ac }
        r8.<init>();	 Catch:{ all -> 0x00ac }
        r9 = "Saving data to: ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x00ac }
        r9 = r1.toString();	 Catch:{ all -> 0x00ac }
        r8 = r8.append(r9);	 Catch:{ all -> 0x00ac }
        r8 = r8.toString();	 Catch:{ all -> 0x00ac }
        net.hockeyapp.android.utils.HockeyLog.warn(r6, r8);	 Catch:{ all -> 0x00ac }
        monitor-exit(r7);	 Catch:{ all -> 0x00ac }
        r6 = 1;
        r2 = java.lang.Boolean.valueOf(r6);	 Catch:{ Exception -> 0x00a9, all -> 0x00a6 }
        if (r4 == 0) goto L_0x00af;
    L_0x0060:
        r4.close();	 Catch:{ IOException -> 0x0094 }
        r3 = r4;
    L_0x0064:
        r6 = r2.booleanValue();
        return r6;
    L_0x0069:
        r6 = move-exception;
    L_0x006a:
        monitor-exit(r7);	 Catch:{ all -> 0x0069 }
        throw r6;	 Catch:{ Exception -> 0x006c }
    L_0x006c:
        r0 = move-exception;
    L_0x006d:
        r6 = "HA-MetricsPersistence";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009a }
        r7.<init>();	 Catch:{ all -> 0x009a }
        r8 = "Failed to save data with exception: ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x009a }
        r8 = r0.toString();	 Catch:{ all -> 0x009a }
        r7 = r7.append(r8);	 Catch:{ all -> 0x009a }
        r7 = r7.toString();	 Catch:{ all -> 0x009a }
        net.hockeyapp.android.utils.HockeyLog.warn(r6, r7);	 Catch:{ all -> 0x009a }
        if (r3 == 0) goto L_0x0064;
    L_0x008b:
        r3.close();	 Catch:{ IOException -> 0x008f }
        goto L_0x0064;
    L_0x008f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0064;
    L_0x0094:
        r0 = move-exception;
        r0.printStackTrace();
        r3 = r4;
        goto L_0x0064;
    L_0x009a:
        r6 = move-exception;
    L_0x009b:
        if (r3 == 0) goto L_0x00a0;
    L_0x009d:
        r3.close();	 Catch:{ IOException -> 0x00a1 }
    L_0x00a0:
        throw r6;
    L_0x00a1:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00a0;
    L_0x00a6:
        r6 = move-exception;
        r3 = r4;
        goto L_0x009b;
    L_0x00a9:
        r0 = move-exception;
        r3 = r4;
        goto L_0x006d;
    L_0x00ac:
        r6 = move-exception;
        r3 = r4;
        goto L_0x006a;
    L_0x00af:
        r3 = r4;
        goto L_0x0064;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.metrics.Persistence.writeToDisk(java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.lang.String load(java.io.File r12) {
        /*
        r11 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        if (r12 == 0) goto L_0x004c;
    L_0x0007:
        r4 = 0;
        r8 = LOCK;	 Catch:{ Exception -> 0x002a }
        monitor-enter(r8);	 Catch:{ Exception -> 0x002a }
        r3 = new java.io.FileInputStream;	 Catch:{ all -> 0x00b9 }
        r3.<init>(r12);	 Catch:{ all -> 0x00b9 }
        r6 = new java.io.InputStreamReader;	 Catch:{ all -> 0x00b9 }
        r6.<init>(r3);	 Catch:{ all -> 0x00b9 }
        r5 = new java.io.BufferedReader;	 Catch:{ all -> 0x00b9 }
        r5.<init>(r6);	 Catch:{ all -> 0x00b9 }
    L_0x001a:
        r1 = r5.read();	 Catch:{ all -> 0x0026 }
        r7 = -1;
        if (r1 == r7) goto L_0x0051;
    L_0x0021:
        r7 = (char) r1;	 Catch:{ all -> 0x0026 }
        r0.append(r7);	 Catch:{ all -> 0x0026 }
        goto L_0x001a;
    L_0x0026:
        r7 = move-exception;
        r4 = r5;
    L_0x0028:
        monitor-exit(r8);	 Catch:{ all -> 0x00b9 }
        throw r7;	 Catch:{ Exception -> 0x002a }
    L_0x002a:
        r2 = move-exception;
        r7 = "HA-MetricsPersistence";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0094 }
        r8.<init>();	 Catch:{ all -> 0x0094 }
        r9 = "Error reading telemetry data from file with exception message ";
        r8 = r8.append(r9);	 Catch:{ all -> 0x0094 }
        r9 = r2.getMessage();	 Catch:{ all -> 0x0094 }
        r8 = r8.append(r9);	 Catch:{ all -> 0x0094 }
        r8 = r8.toString();	 Catch:{ all -> 0x0094 }
        net.hockeyapp.android.utils.HockeyLog.warn(r7, r8);	 Catch:{ all -> 0x0094 }
        if (r4 == 0) goto L_0x004c;
    L_0x0049:
        r4.close();	 Catch:{ IOException -> 0x0076 }
    L_0x004c:
        r7 = r0.toString();
        return r7;
    L_0x0051:
        monitor-exit(r8);	 Catch:{ all -> 0x0026 }
        if (r5 == 0) goto L_0x004c;
    L_0x0054:
        r5.close();	 Catch:{ IOException -> 0x0058 }
        goto L_0x004c;
    L_0x0058:
        r2 = move-exception;
        r7 = "HA-MetricsPersistence";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Error closing stream.";
        r8 = r8.append(r9);
        r9 = r2.getMessage();
        r8 = r8.append(r9);
        r8 = r8.toString();
        net.hockeyapp.android.utils.HockeyLog.warn(r7, r8);
        goto L_0x004c;
    L_0x0076:
        r2 = move-exception;
        r7 = "HA-MetricsPersistence";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Error closing stream.";
        r8 = r8.append(r9);
        r9 = r2.getMessage();
        r8 = r8.append(r9);
        r8 = r8.toString();
        net.hockeyapp.android.utils.HockeyLog.warn(r7, r8);
        goto L_0x004c;
    L_0x0094:
        r7 = move-exception;
        if (r4 == 0) goto L_0x009a;
    L_0x0097:
        r4.close();	 Catch:{ IOException -> 0x009b }
    L_0x009a:
        throw r7;
    L_0x009b:
        r2 = move-exception;
        r8 = "HA-MetricsPersistence";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Error closing stream.";
        r9 = r9.append(r10);
        r10 = r2.getMessage();
        r9 = r9.append(r10);
        r9 = r9.toString();
        net.hockeyapp.android.utils.HockeyLog.warn(r8, r9);
        goto L_0x009a;
    L_0x00b9:
        r7 = move-exception;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.hockeyapp.android.metrics.Persistence.load(java.io.File):java.lang.String");
    }

    protected boolean hasFilesAvailable() {
        return nextAvailableFileInDirectory() != null;
    }

    protected File nextAvailableFileInDirectory() {
        File file;
        synchronized (LOCK) {
            if (this.mTelemetryDirectory != null) {
                File[] files = this.mTelemetryDirectory.listFiles();
                if (files != null && files.length > 0) {
                    for (int i = 0; i <= files.length - 1; i++) {
                        file = files[i];
                        if (!this.mServedFiles.contains(file)) {
                            HockeyLog.info(TAG, "The directory " + file.toString() + " (ADDING TO SERVED AND RETURN)");
                            this.mServedFiles.add(file);
                            break;
                        }
                        HockeyLog.info(TAG, "The directory " + file.toString() + " (WAS ALREADY SERVED)");
                    }
                }
            }
            if (this.mTelemetryDirectory != null) {
                HockeyLog.info(TAG, "The directory " + this.mTelemetryDirectory.toString() + " did not contain any unserved files");
            }
            file = null;
        }
        return file;
    }

    protected void deleteFile(File file) {
        if (file != null) {
            synchronized (LOCK) {
                if (file.delete()) {
                    HockeyLog.warn(TAG, "Successfully deleted telemetry file at: " + file.toString());
                    this.mServedFiles.remove(file);
                } else {
                    HockeyLog.warn(TAG, "Error deleting telemetry file " + file.toString());
                }
            }
            return;
        }
        HockeyLog.warn(TAG, "Couldn't delete file, the reference to the file was null");
    }

    protected void makeAvailable(File file) {
        synchronized (LOCK) {
            if (file != null) {
                this.mServedFiles.remove(file);
            }
        }
    }

    protected boolean isFreeSpaceAvailable() {
        boolean z = false;
        synchronized (LOCK) {
            Context context = getContext();
            if (context.getFilesDir() != null) {
                String path = context.getFilesDir().getAbsolutePath() + BIT_TELEMETRY_DIRECTORY;
                if (!TextUtils.isEmpty(path)) {
                    File[] files = new File(path).listFiles();
                    if (files != null && files.length < MAX_FILE_COUNT.intValue()) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    protected void createDirectoriesIfNecessary() {
        String successMessage = "Successfully created directory";
        String errorMessage = "Error creating directory";
        if (this.mTelemetryDirectory != null && !this.mTelemetryDirectory.exists()) {
            if (this.mTelemetryDirectory.mkdirs()) {
                HockeyLog.info(TAG, successMessage);
            } else {
                HockeyLog.info(TAG, errorMessage);
            }
        }
    }

    private Context getContext() {
        if (this.mWeakContext != null) {
            return (Context) this.mWeakContext.get();
        }
        return null;
    }

    protected Sender getSender() {
        if (this.mWeakSender != null) {
            return (Sender) this.mWeakSender.get();
        }
        return null;
    }

    protected void setSender(Sender sender) {
        this.mWeakSender = new WeakReference(sender);
    }
}
