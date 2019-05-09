// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.io.ObjectStreamClass;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.Collection;
import com.facebook.appevents.internal.AppEventUtility;

class AppEventStore
{
    private static final String PERSISTED_EVENTS_FILENAME = "AppEventsLogger.persistedevents";
    private static final String TAG;
    
    static {
        TAG = AppEventStore.class.getName();
    }
    
    public static void persistEvents(final AccessTokenAppIdPair accessTokenAppIdPair, final SessionEventsState sessionEventsState) {
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            final PersistedEvents andClearStore = readAndClearStore();
            if (andClearStore.containsKey(accessTokenAppIdPair)) {
                andClearStore.get(accessTokenAppIdPair).addAll(sessionEventsState.getEventsToPersist());
            }
            else {
                andClearStore.addEvents(accessTokenAppIdPair, sessionEventsState.getEventsToPersist());
            }
            saveEventsToDisk(andClearStore);
        }
    }
    
    public static void persistEvents(final AppEventCollection collection) {
        final PersistedEvents andClearStore;
        synchronized (AppEventStore.class) {
            AppEventUtility.assertIsNotMainThread();
            andClearStore = readAndClearStore();
            for (final AccessTokenAppIdPair accessTokenAppIdPair : collection.keySet()) {
                andClearStore.addEvents(accessTokenAppIdPair, collection.get(accessTokenAppIdPair).getEventsToPersist());
            }
        }
        saveEventsToDisk(andClearStore);
    }
    // monitorexit(AppEventStore.class)
    
    public static PersistedEvents readAndClearStore() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: monitorenter   
        //     3: invokestatic    com/facebook/appevents/internal/AppEventUtility.assertIsNotMainThread:()V
        //     6: aconst_null    
        //     7: astore          4
        //     9: aconst_null    
        //    10: astore_1       
        //    11: aconst_null    
        //    12: astore_2       
        //    13: aconst_null    
        //    14: astore_3       
        //    15: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
        //    18: astore          5
        //    20: new             Lcom/facebook/appevents/AppEventStore$MovedClassObjectInputStream;
        //    23: dup            
        //    24: new             Ljava/io/BufferedInputStream;
        //    27: dup            
        //    28: aload           5
        //    30: ldc             "AppEventsLogger.persistedevents"
        //    32: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    35: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    38: invokespecial   com/facebook/appevents/AppEventStore$MovedClassObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    41: astore_0       
        //    42: aload_0        
        //    43: invokevirtual   com/facebook/appevents/AppEventStore$MovedClassObjectInputStream.readObject:()Ljava/lang/Object;
        //    46: checkcast       Lcom/facebook/appevents/PersistedEvents;
        //    49: astore_1       
        //    50: aload_0        
        //    51: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    54: aload           5
        //    56: ldc             "AppEventsLogger.persistedevents"
        //    58: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //    61: invokevirtual   java/io/File.delete:()Z
        //    64: pop            
        //    65: aload_1        
        //    66: astore_0       
        //    67: aload_0        
        //    68: astore_1       
        //    69: aload_0        
        //    70: ifnonnull       81
        //    73: new             Lcom/facebook/appevents/PersistedEvents;
        //    76: dup            
        //    77: invokespecial   com/facebook/appevents/PersistedEvents.<init>:()V
        //    80: astore_1       
        //    81: ldc             Lcom/facebook/appevents/AppEventStore;.class
        //    83: monitorexit    
        //    84: aload_1        
        //    85: areturn        
        //    86: astore_0       
        //    87: getstatic       com/facebook/appevents/AppEventStore.TAG:Ljava/lang/String;
        //    90: ldc             "Got unexpected exception when removing events file: "
        //    92: aload_0        
        //    93: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    96: pop            
        //    97: aload_1        
        //    98: astore_0       
        //    99: goto            67
        //   102: aload_0        
        //   103: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   106: aload           5
        //   108: ldc             "AppEventsLogger.persistedevents"
        //   110: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //   113: invokevirtual   java/io/File.delete:()Z
        //   116: pop            
        //   117: aload_3        
        //   118: astore_0       
        //   119: goto            67
        //   122: astore_0       
        //   123: getstatic       com/facebook/appevents/AppEventStore.TAG:Ljava/lang/String;
        //   126: ldc             "Got unexpected exception when removing events file: "
        //   128: aload_0        
        //   129: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   132: pop            
        //   133: aload_3        
        //   134: astore_0       
        //   135: goto            67
        //   138: astore_0       
        //   139: ldc             Lcom/facebook/appevents/AppEventStore;.class
        //   141: monitorexit    
        //   142: aload_0        
        //   143: athrow         
        //   144: astore_2       
        //   145: aload           4
        //   147: astore_0       
        //   148: aload_0        
        //   149: astore_1       
        //   150: getstatic       com/facebook/appevents/AppEventStore.TAG:Ljava/lang/String;
        //   153: ldc             "Got unexpected exception while reading events: "
        //   155: aload_2        
        //   156: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   159: pop            
        //   160: aload_0        
        //   161: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   164: aload           5
        //   166: ldc             "AppEventsLogger.persistedevents"
        //   168: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //   171: invokevirtual   java/io/File.delete:()Z
        //   174: pop            
        //   175: aload_3        
        //   176: astore_0       
        //   177: goto            67
        //   180: astore_0       
        //   181: getstatic       com/facebook/appevents/AppEventStore.TAG:Ljava/lang/String;
        //   184: ldc             "Got unexpected exception when removing events file: "
        //   186: aload_0        
        //   187: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   190: pop            
        //   191: aload_3        
        //   192: astore_0       
        //   193: goto            67
        //   196: aload_1        
        //   197: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   200: aload           5
        //   202: ldc             "AppEventsLogger.persistedevents"
        //   204: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //   207: invokevirtual   java/io/File.delete:()Z
        //   210: pop            
        //   211: aload_0        
        //   212: athrow         
        //   213: astore_1       
        //   214: getstatic       com/facebook/appevents/AppEventStore.TAG:Ljava/lang/String;
        //   217: ldc             "Got unexpected exception when removing events file: "
        //   219: aload_1        
        //   220: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   223: pop            
        //   224: goto            211
        //   227: astore_2       
        //   228: aload_0        
        //   229: astore_1       
        //   230: aload_2        
        //   231: astore_0       
        //   232: goto            196
        //   235: astore_2       
        //   236: goto            148
        //   239: astore_1       
        //   240: goto            102
        //   243: astore_0       
        //   244: aload_2        
        //   245: astore_0       
        //   246: goto            102
        //   249: astore_0       
        //   250: goto            196
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  3      6      138    144    Any
        //  15     20     138    144    Any
        //  20     42     243    249    Ljava/io/FileNotFoundException;
        //  20     42     144    148    Ljava/lang/Exception;
        //  20     42     249    253    Any
        //  42     50     239    243    Ljava/io/FileNotFoundException;
        //  42     50     235    239    Ljava/lang/Exception;
        //  42     50     227    235    Any
        //  50     54     138    144    Any
        //  54     65     86     102    Ljava/lang/Exception;
        //  54     65     138    144    Any
        //  73     81     138    144    Any
        //  87     97     138    144    Any
        //  102    106    138    144    Any
        //  106    117    122    138    Ljava/lang/Exception;
        //  106    117    138    144    Any
        //  123    133    138    144    Any
        //  150    160    249    253    Any
        //  160    164    138    144    Any
        //  164    175    180    196    Ljava/lang/Exception;
        //  164    175    138    144    Any
        //  181    191    138    144    Any
        //  196    200    138    144    Any
        //  200    211    213    227    Ljava/lang/Exception;
        //  200    211    138    144    Any
        //  211    213    138    144    Any
        //  214    224    138    144    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
    
    private static void saveEventsToDisk(final PersistedEvents p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: aconst_null    
        //     3: astore_3       
        //     4: invokestatic    com/facebook/FacebookSdk.getApplicationContext:()Landroid/content/Context;
        //     7: astore          4
        //     9: new             Ljava/io/ObjectOutputStream;
        //    12: dup            
        //    13: new             Ljava/io/BufferedOutputStream;
        //    16: dup            
        //    17: aload           4
        //    19: ldc             "AppEventsLogger.persistedevents"
        //    21: iconst_0       
        //    22: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    25: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    28: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    31: astore_2       
        //    32: aload_2        
        //    33: aload_0        
        //    34: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    37: aload_2        
        //    38: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    41: return         
        //    42: astore_2       
        //    43: aload_3        
        //    44: astore_0       
        //    45: aload_0        
        //    46: astore_1       
        //    47: getstatic       com/facebook/appevents/AppEventStore.TAG:Ljava/lang/String;
        //    50: ldc             "Got unexpected exception while persisting events: "
        //    52: aload_2        
        //    53: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    56: pop            
        //    57: aload_0        
        //    58: astore_1       
        //    59: aload           4
        //    61: ldc             "AppEventsLogger.persistedevents"
        //    63: invokevirtual   android/content/Context.getFileStreamPath:(Ljava/lang/String;)Ljava/io/File;
        //    66: invokevirtual   java/io/File.delete:()Z
        //    69: pop            
        //    70: aload_0        
        //    71: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    74: return         
        //    75: astore_0       
        //    76: aload_1        
        //    77: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    80: aload_0        
        //    81: athrow         
        //    82: astore_0       
        //    83: aload_2        
        //    84: astore_1       
        //    85: goto            76
        //    88: astore_1       
        //    89: goto            70
        //    92: astore_1       
        //    93: aload_2        
        //    94: astore_0       
        //    95: aload_1        
        //    96: astore_2       
        //    97: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      32     42     45     Ljava/lang/Exception;
        //  9      32     75     76     Any
        //  32     37     92     100    Ljava/lang/Exception;
        //  32     37     82     88     Any
        //  47     57     75     76     Any
        //  59     70     88     92     Ljava/lang/Exception;
        //  59     70     75     76     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 60, Size: 60
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
    
    private static class MovedClassObjectInputStream extends ObjectInputStream
    {
        private static final String ACCESS_TOKEN_APP_ID_PAIR_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1";
        private static final String APP_EVENT_SERIALIZATION_PROXY_V1_CLASS_NAME = "com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1";
        
        public MovedClassObjectInputStream(final InputStream inputStream) throws IOException {
            super(inputStream);
        }
        
        @Override
        protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
            final ObjectStreamClass classDescriptor = super.readClassDescriptor();
            ObjectStreamClass lookup;
            if (classDescriptor.getName().equals("com.facebook.appevents.AppEventsLogger$AccessTokenAppIdPair$SerializationProxyV1")) {
                lookup = ObjectStreamClass.lookup(AccessTokenAppIdPair.SerializationProxyV1.class);
            }
            else {
                lookup = classDescriptor;
                if (classDescriptor.getName().equals("com.facebook.appevents.AppEventsLogger$AppEvent$SerializationProxyV1")) {
                    return ObjectStreamClass.lookup(AppEvent.SerializationProxyV1.class);
                }
            }
            return lookup;
        }
    }
}
