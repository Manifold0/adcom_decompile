// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.content;

import java.util.Set;
import android.net.Uri;
import android.util.Log;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.os.Looper;
import android.content.BroadcastReceiver;
import android.os.Handler;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager
{
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock;
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions;
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts;
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers;
    
    static {
        mLock = new Object();
    }
    
    private LocalBroadcastManager(final Context mAppContext) {
        this.mReceivers = new HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>>();
        this.mActions = new HashMap<String, ArrayList<ReceiverRecord>>();
        this.mPendingBroadcasts = new ArrayList<BroadcastRecord>();
        this.mAppContext = mAppContext;
        this.mHandler = new Handler(mAppContext.getMainLooper()) {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                    }
                    case 1: {
                        LocalBroadcastManager.this.executePendingBroadcasts();
                    }
                }
            }
        };
    }
    
    private void executePendingBroadcasts() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        android/support/v4/content/LocalBroadcastManager.mReceivers:Ljava/util/HashMap;
        //     4: astore          5
        //     6: aload           5
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        android/support/v4/content/LocalBroadcastManager.mPendingBroadcasts:Ljava/util/ArrayList;
        //    13: invokevirtual   java/util/ArrayList.size:()I
        //    16: istore_1       
        //    17: iload_1        
        //    18: ifgt            25
        //    21: aload           5
        //    23: monitorexit    
        //    24: return         
        //    25: iload_1        
        //    26: anewarray       Landroid/support/v4/content/LocalBroadcastManager$BroadcastRecord;
        //    29: astore          4
        //    31: aload_0        
        //    32: getfield        android/support/v4/content/LocalBroadcastManager.mPendingBroadcasts:Ljava/util/ArrayList;
        //    35: aload           4
        //    37: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //    40: pop            
        //    41: aload_0        
        //    42: getfield        android/support/v4/content/LocalBroadcastManager.mPendingBroadcasts:Ljava/util/ArrayList;
        //    45: invokevirtual   java/util/ArrayList.clear:()V
        //    48: aload           5
        //    50: monitorexit    
        //    51: iconst_0       
        //    52: istore_1       
        //    53: iload_1        
        //    54: aload           4
        //    56: arraylength    
        //    57: if_icmpge       0
        //    60: aload           4
        //    62: iload_1        
        //    63: aaload         
        //    64: astore          5
        //    66: aload           5
        //    68: getfield        android/support/v4/content/LocalBroadcastManager$BroadcastRecord.receivers:Ljava/util/ArrayList;
        //    71: invokevirtual   java/util/ArrayList.size:()I
        //    74: istore_3       
        //    75: iconst_0       
        //    76: istore_2       
        //    77: iload_2        
        //    78: iload_3        
        //    79: if_icmpge       136
        //    82: aload           5
        //    84: getfield        android/support/v4/content/LocalBroadcastManager$BroadcastRecord.receivers:Ljava/util/ArrayList;
        //    87: iload_2        
        //    88: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //    91: checkcast       Landroid/support/v4/content/LocalBroadcastManager$ReceiverRecord;
        //    94: astore          6
        //    96: aload           6
        //    98: getfield        android/support/v4/content/LocalBroadcastManager$ReceiverRecord.dead:Z
        //   101: ifne            121
        //   104: aload           6
        //   106: getfield        android/support/v4/content/LocalBroadcastManager$ReceiverRecord.receiver:Landroid/content/BroadcastReceiver;
        //   109: aload_0        
        //   110: getfield        android/support/v4/content/LocalBroadcastManager.mAppContext:Landroid/content/Context;
        //   113: aload           5
        //   115: getfield        android/support/v4/content/LocalBroadcastManager$BroadcastRecord.intent:Landroid/content/Intent;
        //   118: invokevirtual   android/content/BroadcastReceiver.onReceive:(Landroid/content/Context;Landroid/content/Intent;)V
        //   121: iload_2        
        //   122: iconst_1       
        //   123: iadd           
        //   124: istore_2       
        //   125: goto            77
        //   128: astore          4
        //   130: aload           5
        //   132: monitorexit    
        //   133: aload           4
        //   135: athrow         
        //   136: iload_1        
        //   137: iconst_1       
        //   138: iadd           
        //   139: istore_1       
        //   140: goto            53
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      17     128    136    Any
        //  21     24     128    136    Any
        //  25     51     128    136    Any
        //  130    133    128    136    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    public static LocalBroadcastManager getInstance(final Context context) {
        synchronized (LocalBroadcastManager.mLock) {
            if (LocalBroadcastManager.mInstance == null) {
                LocalBroadcastManager.mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            return LocalBroadcastManager.mInstance;
        }
    }
    
    public void registerReceiver(final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            final ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList<ReceiverRecord> list;
            if ((list = this.mReceivers.get(broadcastReceiver)) == null) {
                list = new ArrayList<ReceiverRecord>(1);
                this.mReceivers.put(broadcastReceiver, list);
            }
            list.add(receiverRecord);
            for (int i = 0; i < intentFilter.countActions(); ++i) {
                final String action = intentFilter.getAction(i);
                ArrayList<ReceiverRecord> list2;
                if ((list2 = this.mActions.get(action)) == null) {
                    list2 = new ArrayList<ReceiverRecord>(1);
                    this.mActions.put(action, list2);
                }
                list2.add(receiverRecord);
            }
        }
    }
    
    public boolean sendBroadcast(final Intent intent) {
        // monitorexit(hashMap)
        while (true) {
            while (true) {
            Block_4_Outer:
                while (true) {
                    String action;
                    String resolveTypeIfNeeded;
                    Uri data;
                    String scheme;
                    Set categories;
                    int n;
                    ArrayList<ReceiverRecord> list;
                    ArrayList<ReceiverRecord> list2 = null;
                    Object o = null;
                    ReceiverRecord receiverRecord;
                    int match;
                    int n2 = 0;
                    String s;
                    Block_11_Outer:Block_16_Outer:Label_0161_Outer:
                    while (true) {
                        Label_0521: {
                            while (true) {
                                Label_0510: {
                                    Label_0502: {
                                        synchronized (this.mReceivers) {
                                            action = intent.getAction();
                                            resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
                                            data = intent.getData();
                                            scheme = intent.getScheme();
                                            categories = intent.getCategories();
                                            if ((intent.getFlags() & 0x8) == 0x0) {
                                                break Label_0521;
                                            }
                                            n = 1;
                                            if (n != 0) {
                                                Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
                                            }
                                            list = this.mActions.get(intent.getAction());
                                            if (list == null) {
                                                break;
                                            }
                                            if (n != 0) {
                                                Log.v("LocalBroadcastManager", "Action list: " + list);
                                            }
                                            break Label_0502;
                                            // iftrue(Label_0323:, o = list2 != null)
                                            // iftrue(Label_0346:, match < 0)
                                            // iftrue(Label_0214:, n == 0)
                                            // iftrue(Label_0245:, !receiverRecord.broadcasting)
                                            // iftrue(Label_0510:, n == 0)
                                            // iftrue(Label_0554:, n2 >= list.size())
                                            // iftrue(Label_0305:, n == 0)
                                        Block_14:
                                            while (true) {
                                                Block_15: {
                                                    while (true) {
                                                        Block_17: {
                                                            while (true) {
                                                                Label_0214: {
                                                                Block_12_Outer:
                                                                    while (true) {
                                                                        break Block_17;
                                                                        while (true) {
                                                                            Log.v("LocalBroadcastManager", "Matching against filter " + receiverRecord.filter);
                                                                            break Label_0214;
                                                                            Label_0245: {
                                                                                match = receiverRecord.filter.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                                                                            }
                                                                            break Block_15;
                                                                            receiverRecord = list.get(n2);
                                                                            continue Block_11_Outer;
                                                                        }
                                                                        Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                                                                        continue Block_12_Outer;
                                                                    }
                                                                    ((ArrayList<ReceiverRecord>)o).add(receiverRecord);
                                                                    receiverRecord.broadcasting = true;
                                                                    break Label_0510;
                                                                }
                                                                o = list2;
                                                                break Block_14;
                                                                continue Block_16_Outer;
                                                            }
                                                        }
                                                        o = new ArrayList<ReceiverRecord>();
                                                        continue Label_0161_Outer;
                                                    }
                                                }
                                                continue Label_0161_Outer;
                                            }
                                            Log.v("LocalBroadcastManager", "  Filter's target already added");
                                            o = list2;
                                            break Label_0510;
                                        }
                                        Label_0346: {
                                            o = list2;
                                        }
                                        if (n != 0) {
                                            switch (match) {
                                                default: {
                                                    s = "unknown reason";
                                                    break;
                                                }
                                                case -3: {
                                                    s = "action";
                                                    break;
                                                }
                                                case -4: {
                                                    s = "category";
                                                    break;
                                                }
                                                case -2: {
                                                    s = "data";
                                                    break;
                                                }
                                                case -1: {
                                                    s = "type";
                                                    break;
                                                }
                                            }
                                            Log.v("LocalBroadcastManager", "  Filter did not match: " + s);
                                            o = list2;
                                        }
                                        break Label_0510;
                                    }
                                    list2 = null;
                                    n2 = 0;
                                    continue Block_4_Outer;
                                }
                                ++n2;
                                list2 = (ArrayList<ReceiverRecord>)o;
                                continue Block_4_Outer;
                            }
                        }
                        n = 0;
                        continue Block_4_Outer;
                    }
                    while (true) {
                        this.mHandler.sendEmptyMessage(1);
                        Label_0454: {
                            Label_0492: {
                                break Label_0492;
                                int i = 0;
                                while (i < list2.size()) {
                                    list2.get(i).broadcasting = false;
                                    ++i;
                                }
                                break Label_0454;
                            }
                            return true;
                        }
                        this.mPendingBroadcasts.add(new BroadcastRecord(intent, list2));
                        continue;
                    }
                }
                // monitorexit(hashMap)
                // iftrue(Label_0492:, this.mHandler.hasMessages(1))
                return false;
                Label_0554: {
                    final ArrayList<ReceiverRecord> list2;
                    if (list2 != null) {
                        final int i = 0;
                        continue;
                    }
                }
                break;
            }
            continue;
        }
    }
    
    public void sendBroadcastSync(final Intent intent) {
        if (this.sendBroadcast(intent)) {
            this.executePendingBroadcasts();
        }
    }
    
    public void unregisterReceiver(final BroadcastReceiver broadcastReceiver) {
        while (true) {
        Label_0062_Outer:
            while (true) {
                int n = 0;
            Label_0203:
                while (true) {
                    int n2 = 0;
                    Label_0196: {
                        while (true) {
                            int n3;
                            synchronized (this.mReceivers) {
                                final ArrayList<ReceiverRecord> list = this.mReceivers.remove(broadcastReceiver);
                                if (list == null) {
                                    return;
                                }
                                n = list.size() - 1;
                                if (n < 0) {
                                    return;
                                }
                                final ReceiverRecord receiverRecord = list.get(n);
                                receiverRecord.dead = true;
                                n2 = 0;
                                if (n2 >= receiverRecord.filter.countActions()) {
                                    break Label_0203;
                                }
                                final String action = receiverRecord.filter.getAction(n2);
                                final ArrayList<ReceiverRecord> list2 = this.mActions.get(action);
                                if (list2 == null) {
                                    break Label_0196;
                                }
                                n3 = list2.size() - 1;
                                if (n3 >= 0) {
                                    final ReceiverRecord receiverRecord2 = list2.get(n3);
                                    if (receiverRecord2.receiver == broadcastReceiver) {
                                        receiverRecord2.dead = true;
                                        list2.remove(n3);
                                    }
                                }
                                else {
                                    if (list2.size() <= 0) {
                                        this.mActions.remove(action);
                                    }
                                    break Label_0196;
                                }
                            }
                            --n3;
                            continue;
                        }
                    }
                    ++n2;
                    continue;
                }
                --n;
                continue Label_0062_Outer;
            }
        }
    }
    
    private static final class BroadcastRecord
    {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;
        
        BroadcastRecord(final Intent intent, final ArrayList<ReceiverRecord> receivers) {
            this.intent = intent;
            this.receivers = receivers;
        }
    }
    
    private static final class ReceiverRecord
    {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;
        
        ReceiverRecord(final IntentFilter filter, final BroadcastReceiver receiver) {
            this.filter = filter;
            this.receiver = receiver;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.receiver);
            sb.append(" filter=");
            sb.append(this.filter);
            if (this.dead) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }
}
