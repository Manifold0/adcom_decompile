// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics;

import android.text.TextUtils;
import net.hockeyapp.android.utils.HockeyLog;
import android.content.Context;
import java.lang.ref.WeakReference;
import java.io.File;
import java.util.ArrayList;

class Persistence
{
    private static final String BIT_TELEMETRY_DIRECTORY = "/net.hockeyapp.android/telemetry/";
    private static final Object LOCK;
    private static final Integer MAX_FILE_COUNT;
    private static final String TAG = "HA-MetricsPersistence";
    protected ArrayList<File> mServedFiles;
    protected final File mTelemetryDirectory;
    private final WeakReference<Context> mWeakContext;
    protected WeakReference<Sender> mWeakSender;
    
    static {
        LOCK = new Object();
        MAX_FILE_COUNT = 50;
    }
    
    protected Persistence(final Context context, final File mTelemetryDirectory, final Sender sender) {
        this.mWeakContext = new WeakReference<Context>(context);
        this.mServedFiles = new ArrayList<File>(51);
        this.mTelemetryDirectory = mTelemetryDirectory;
        this.mWeakSender = new WeakReference<Sender>(sender);
        this.createDirectoriesIfNecessary();
    }
    
    protected Persistence(final Context context, final Sender sender) {
        this(context, new File(context.getFilesDir().getAbsolutePath() + "/net.hockeyapp.android/telemetry/"), null);
        this.setSender(sender);
    }
    
    private Context getContext() {
        Context context = null;
        if (this.mWeakContext != null) {
            context = this.mWeakContext.get();
        }
        return context;
    }
    
    protected void createDirectoriesIfNecessary() {
        if (this.mTelemetryDirectory != null && !this.mTelemetryDirectory.exists()) {
            if (!this.mTelemetryDirectory.mkdirs()) {
                HockeyLog.info("HA-MetricsPersistence", "Error creating directory");
                return;
            }
            HockeyLog.info("HA-MetricsPersistence", "Successfully created directory");
        }
    }
    
    protected void deleteFile(final File file) {
        if (file != null) {
            synchronized (Persistence.LOCK) {
                if (!file.delete()) {
                    HockeyLog.warn("HA-MetricsPersistence", "Error deleting telemetry file " + file.toString());
                }
                else {
                    HockeyLog.warn("HA-MetricsPersistence", "Successfully deleted telemetry file at: " + file.toString());
                    this.mServedFiles.remove(file);
                }
                return;
            }
        }
        HockeyLog.warn("HA-MetricsPersistence", "Couldn't delete file, the reference to the file was null");
    }
    
    protected Sender getSender() {
        Sender sender = null;
        if (this.mWeakSender != null) {
            sender = this.mWeakSender.get();
        }
        return sender;
    }
    
    protected boolean hasFilesAvailable() {
        return this.nextAvailableFileInDirectory() != null;
    }
    
    protected boolean isFreeSpaceAvailable() {
        final boolean b = false;
        synchronized (Persistence.LOCK) {
            final Context context = this.getContext();
            if (context.getFilesDir() != null) {
                final String string = context.getFilesDir().getAbsolutePath() + "/net.hockeyapp.android/telemetry/";
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    final File[] listFiles = new File(string).listFiles();
                    boolean b2 = b;
                    if (listFiles != null) {
                        b2 = b;
                        if (listFiles.length < Persistence.MAX_FILE_COUNT) {
                            b2 = true;
                        }
                    }
                    return b2;
                }
            }
            return false;
        }
    }
    
    protected String load(final File p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore          8
        //     9: aload_1        
        //    10: ifnull          145
        //    13: aconst_null    
        //    14: astore          6
        //    16: aconst_null    
        //    17: astore          7
        //    19: aconst_null    
        //    20: astore          5
        //    22: aload           5
        //    24: astore_3       
        //    25: aload           6
        //    27: astore          4
        //    29: getstatic       net/hockeyapp/android/metrics/Persistence.LOCK:Ljava/lang/Object;
        //    32: astore          9
        //    34: aload           5
        //    36: astore_3       
        //    37: aload           6
        //    39: astore          4
        //    41: aload           9
        //    43: monitorenter   
        //    44: aload           7
        //    46: astore_3       
        //    47: new             Ljava/io/BufferedReader;
        //    50: dup            
        //    51: new             Ljava/io/InputStreamReader;
        //    54: dup            
        //    55: new             Ljava/io/FileInputStream;
        //    58: dup            
        //    59: aload_1        
        //    60: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    63: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    66: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    69: astore_1       
        //    70: aload_1        
        //    71: invokevirtual   java/io/BufferedReader.read:()I
        //    74: istore_2       
        //    75: iload_2        
        //    76: iconst_m1      
        //    77: if_icmpeq       151
        //    80: aload           8
        //    82: iload_2        
        //    83: i2c            
        //    84: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    87: pop            
        //    88: goto            70
        //    91: astore          5
        //    93: aload_1        
        //    94: astore_3       
        //    95: aload           9
        //    97: monitorexit    
        //    98: aload_1        
        //    99: astore_3       
        //   100: aload_1        
        //   101: astore          4
        //   103: aload           5
        //   105: athrow         
        //   106: astore_1       
        //   107: aload_3        
        //   108: astore          4
        //   110: ldc             "HA-MetricsPersistence"
        //   112: new             Ljava/lang/StringBuilder;
        //   115: dup            
        //   116: invokespecial   java/lang/StringBuilder.<init>:()V
        //   119: ldc             "Error reading telemetry data from file with exception message "
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: aload_1        
        //   125: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   134: invokestatic    net/hockeyapp/android/utils/HockeyLog.warn:(Ljava/lang/String;Ljava/lang/String;)V
        //   137: aload_3        
        //   138: ifnull          145
        //   141: aload_3        
        //   142: invokevirtual   java/io/BufferedReader.close:()V
        //   145: aload           8
        //   147: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   150: areturn        
        //   151: aload           9
        //   153: monitorexit    
        //   154: aload_1        
        //   155: ifnull          145
        //   158: aload_1        
        //   159: invokevirtual   java/io/BufferedReader.close:()V
        //   162: goto            145
        //   165: astore_1       
        //   166: ldc             "HA-MetricsPersistence"
        //   168: new             Ljava/lang/StringBuilder;
        //   171: dup            
        //   172: invokespecial   java/lang/StringBuilder.<init>:()V
        //   175: ldc             "Error closing stream."
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: aload_1        
        //   181: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   190: invokestatic    net/hockeyapp/android/utils/HockeyLog.warn:(Ljava/lang/String;Ljava/lang/String;)V
        //   193: goto            145
        //   196: astore_1       
        //   197: ldc             "HA-MetricsPersistence"
        //   199: new             Ljava/lang/StringBuilder;
        //   202: dup            
        //   203: invokespecial   java/lang/StringBuilder.<init>:()V
        //   206: ldc             "Error closing stream."
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: aload_1        
        //   212: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   215: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   218: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   221: invokestatic    net/hockeyapp/android/utils/HockeyLog.warn:(Ljava/lang/String;Ljava/lang/String;)V
        //   224: goto            145
        //   227: astore_1       
        //   228: aload           4
        //   230: ifnull          238
        //   233: aload           4
        //   235: invokevirtual   java/io/BufferedReader.close:()V
        //   238: aload_1        
        //   239: athrow         
        //   240: astore_3       
        //   241: ldc             "HA-MetricsPersistence"
        //   243: new             Ljava/lang/StringBuilder;
        //   246: dup            
        //   247: invokespecial   java/lang/StringBuilder.<init>:()V
        //   250: ldc             "Error closing stream."
        //   252: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: aload_3        
        //   256: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   259: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   262: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   265: invokestatic    net/hockeyapp/android/utils/HockeyLog.warn:(Ljava/lang/String;Ljava/lang/String;)V
        //   268: goto            238
        //   271: astore          5
        //   273: aload_3        
        //   274: astore_1       
        //   275: goto            93
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  29     34     106    145    Ljava/lang/Exception;
        //  29     34     227    271    Any
        //  41     44     106    145    Ljava/lang/Exception;
        //  41     44     227    271    Any
        //  47     70     271    278    Any
        //  70     75     91     93     Any
        //  80     88     91     93     Any
        //  95     98     271    278    Any
        //  103    106    106    145    Ljava/lang/Exception;
        //  103    106    227    271    Any
        //  110    137    227    271    Any
        //  141    145    196    227    Ljava/io/IOException;
        //  151    154    91     93     Any
        //  158    162    165    196    Ljava/io/IOException;
        //  233    238    240    271    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    protected void makeAvailable(final File file) {
        final Object lock = Persistence.LOCK;
        // monitorenter(lock)
        if (file == null) {
            return;
        }
        try {
            this.mServedFiles.remove(file);
        }
        finally {
        }
        // monitorexit(lock)
    }
    
    protected File nextAvailableFileInDirectory() {
        synchronized (Persistence.LOCK) {
            if (this.mTelemetryDirectory != null) {
                final File[] listFiles = this.mTelemetryDirectory.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (int i = 0; i <= listFiles.length - 1; ++i) {
                        final File file = listFiles[i];
                        if (!this.mServedFiles.contains(file)) {
                            HockeyLog.info("HA-MetricsPersistence", "The directory " + file.toString() + " (ADDING TO SERVED AND RETURN)");
                            this.mServedFiles.add(file);
                            return file;
                        }
                        HockeyLog.info("HA-MetricsPersistence", "The directory " + file.toString() + " (WAS ALREADY SERVED)");
                    }
                }
            }
            if (this.mTelemetryDirectory != null) {
                HockeyLog.info("HA-MetricsPersistence", "The directory " + this.mTelemetryDirectory.toString() + " did not contain any unserved files");
            }
            return null;
        }
    }
    
    protected void persist(final String[] array) {
        if (!this.isFreeSpaceAvailable()) {
            HockeyLog.warn("HA-MetricsPersistence", "Failed to persist file: Too many files on disk.");
            this.getSender().triggerSending();
        }
        else {
            final StringBuilder sb = new StringBuilder();
            for (int length = array.length, i = 0; i < length; ++i) {
                final String s = array[i];
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(s);
            }
            if (this.writeToDisk(sb.toString())) {
                this.getSender().triggerSending();
            }
        }
    }
    
    protected void setSender(final Sender sender) {
        this.mWeakSender = new WeakReference<Sender>(sender);
    }
    
    protected boolean writeToDisk(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //     6: astore          9
        //     8: iconst_0       
        //     9: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    12: astore          4
        //    14: aconst_null    
        //    15: astore          6
        //    17: aconst_null    
        //    18: astore          7
        //    20: aconst_null    
        //    21: astore          5
        //    23: aload           6
        //    25: astore_3       
        //    26: aload           7
        //    28: astore_2       
        //    29: getstatic       net/hockeyapp/android/metrics/Persistence.LOCK:Ljava/lang/Object;
        //    32: astore          8
        //    34: aload           6
        //    36: astore_3       
        //    37: aload           7
        //    39: astore_2       
        //    40: aload           8
        //    42: monitorenter   
        //    43: aload           5
        //    45: astore_3       
        //    46: new             Ljava/io/File;
        //    49: dup            
        //    50: new             Ljava/lang/StringBuilder;
        //    53: dup            
        //    54: invokespecial   java/lang/StringBuilder.<init>:()V
        //    57: aload_0        
        //    58: getfield        net/hockeyapp/android/metrics/Persistence.mTelemetryDirectory:Ljava/io/File;
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    64: ldc             "/"
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: aload           9
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    77: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    80: astore          6
        //    82: aload           5
        //    84: astore_3       
        //    85: new             Ljava/io/FileOutputStream;
        //    88: dup            
        //    89: aload           6
        //    91: iconst_1       
        //    92: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //    95: astore_2       
        //    96: aload_2        
        //    97: aload_1        
        //    98: invokevirtual   java/lang/String.getBytes:()[B
        //   101: invokevirtual   java/io/FileOutputStream.write:([B)V
        //   104: ldc             "HA-MetricsPersistence"
        //   106: new             Ljava/lang/StringBuilder;
        //   109: dup            
        //   110: invokespecial   java/lang/StringBuilder.<init>:()V
        //   113: ldc_w           "Saving data to: "
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: aload           6
        //   121: invokevirtual   java/io/File.toString:()Ljava/lang/String;
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   130: invokestatic    net/hockeyapp/android/utils/HockeyLog.warn:(Ljava/lang/String;Ljava/lang/String;)V
        //   133: aload           8
        //   135: monitorexit    
        //   136: iconst_1       
        //   137: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   140: astore_1       
        //   141: aload_2        
        //   142: ifnull          264
        //   145: aload_2        
        //   146: invokevirtual   java/io/FileOutputStream.close:()V
        //   149: aload_1        
        //   150: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   153: ireturn        
        //   154: astore_1       
        //   155: aload_3        
        //   156: astore_2       
        //   157: aload_2        
        //   158: astore_3       
        //   159: aload           8
        //   161: monitorexit    
        //   162: aload_2        
        //   163: astore_3       
        //   164: aload_1        
        //   165: athrow         
        //   166: astore_1       
        //   167: aload_3        
        //   168: astore_2       
        //   169: ldc             "HA-MetricsPersistence"
        //   171: new             Ljava/lang/StringBuilder;
        //   174: dup            
        //   175: invokespecial   java/lang/StringBuilder.<init>:()V
        //   178: ldc_w           "Failed to save data with exception: "
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: aload_1        
        //   185: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   194: invokestatic    net/hockeyapp/android/utils/HockeyLog.warn:(Ljava/lang/String;Ljava/lang/String;)V
        //   197: aload           4
        //   199: astore_1       
        //   200: aload_3        
        //   201: ifnull          149
        //   204: aload_3        
        //   205: invokevirtual   java/io/FileOutputStream.close:()V
        //   208: aload           4
        //   210: astore_1       
        //   211: goto            149
        //   214: astore_1       
        //   215: aload_1        
        //   216: invokevirtual   java/io/IOException.printStackTrace:()V
        //   219: aload           4
        //   221: astore_1       
        //   222: goto            149
        //   225: astore_2       
        //   226: aload_2        
        //   227: invokevirtual   java/io/IOException.printStackTrace:()V
        //   230: goto            149
        //   233: astore_1       
        //   234: aload_2        
        //   235: ifnull          242
        //   238: aload_2        
        //   239: invokevirtual   java/io/FileOutputStream.close:()V
        //   242: aload_1        
        //   243: athrow         
        //   244: astore_2       
        //   245: aload_2        
        //   246: invokevirtual   java/io/IOException.printStackTrace:()V
        //   249: goto            242
        //   252: astore_1       
        //   253: goto            234
        //   256: astore_1       
        //   257: goto            167
        //   260: astore_1       
        //   261: goto            157
        //   264: goto            149
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  29     34     166    225    Ljava/lang/Exception;
        //  29     34     233    252    Any
        //  40     43     166    225    Ljava/lang/Exception;
        //  40     43     233    252    Any
        //  46     82     154    157    Any
        //  85     96     154    157    Any
        //  96     136    260    264    Any
        //  145    149    225    233    Ljava/io/IOException;
        //  159    162    154    157    Any
        //  164    166    166    225    Ljava/lang/Exception;
        //  164    166    233    252    Any
        //  169    197    233    252    Any
        //  204    208    214    225    Ljava/io/IOException;
        //  238    242    244    252    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.util.ConcurrentModificationException
        //     at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
        //     at java.util.ArrayList$Itr.next(ArrayList.java:851)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2863)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
}
