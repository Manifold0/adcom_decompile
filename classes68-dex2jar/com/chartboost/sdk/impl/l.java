// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.util.concurrent.TimeUnit;
import com.chartboost.sdk.Model.CBError;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import com.chartboost.sdk.Model.b;
import java.util.Map;
import com.chartboost.sdk.Libraries.CBLogging;
import java.io.File;
import java.util.PriorityQueue;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.Model.e;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.Executor;

public class l
{
    int a;
    private final Executor b;
    private final ah c;
    private final ai d;
    private final AtomicReference<e> e;
    private final i f;
    private final a g;
    private final f h;
    private k i;
    private final PriorityQueue<j> j;
    
    public l(final Executor b, final f h, final ah c, final ai d, final AtomicReference<e> e, final i f, final a g) {
        this.a = 1;
        this.i = null;
        this.b = b;
        this.h = h;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.j = new PriorityQueue<j>();
    }
    
    private void d() {
        if (this.i != null) {
            final j j = this.j.peek();
            if (j != null && this.i.a.a > j.a && this.i.b()) {
                this.j.add(this.i.a);
                this.i = null;
            }
        }
        while (this.i == null) {
            final j i = this.j.poll();
            if (i == null) {
                break;
            }
            if (i.e.get() <= 0) {
                continue;
            }
            final File file = new File(this.h.d().a, i.d);
            if (!file.exists() && !file.mkdirs() && !file.isDirectory()) {
                CBLogging.b("Downloader", "Unable to create directory " + file.getPath());
                i.a(this.b, false);
            }
            else {
                final File file2 = new File(file, i.b);
                if (file2.exists()) {
                    this.h.c(file2);
                    i.a(this.b, true);
                }
                else {
                    this.i = new k(this, this.d, i, file2);
                    this.c.a((ad<Object>)this.i);
                    this.g.a(i.c, i.b);
                }
            }
        }
        if (this.i != null) {
            if (this.a != 2) {
                CBLogging.a("Downloader", "Change state to DOWNLOADING");
                this.a = 2;
            }
        }
        else if (this.a != 1) {
            CBLogging.a("Downloader", "Change state to IDLE");
            this.a = 1;
        }
    }
    
    public void a() {
        while (true) {
            Label_0059: {
                synchronized (this) {
                    switch (this.a) {
                        case 1: {
                            CBLogging.a("Downloader", "Change state to PAUSED");
                            this.a = 4;
                            break;
                        }
                        case 2: {
                            break Label_0059;
                        }
                    }
                    return;
                }
            }
            if (this.i.b()) {
                this.j.add(this.i.a);
                this.i = null;
                CBLogging.a("Downloader", "Change state to PAUSED");
                this.a = 4;
                return;
            }
            CBLogging.a("Downloader", "Change state to PAUSING");
            this.a = 3;
        }
    }
    
    public void a(final int n, final Map<String, b> map, final AtomicInteger atomicInteger, final h h) {
        synchronized (this) {
            final long b = this.f.b();
            final AtomicInteger atomicInteger2 = new AtomicInteger();
            final AtomicReference<h> atomicReference = new AtomicReference<h>(h);
            for (final b b2 : map.values()) {
                this.j.add(new j(this.f, n, b2.b, b2.c, b2.a, atomicInteger, atomicReference, b, atomicInteger2));
            }
        }
        if (this.a == 1 || this.a == 2) {
            this.d();
        }
    }
    // monitorexit(this)
    
    void a(final k k, CBError b, ag append) {
    Label_0178_Outer:
        while (true) {
        Label_0333:
            while (true) {
                Object o = null;
                long millis = 0L;
                long millis2 = 0L;
                long millis3 = 0L;
            Label_0212:
                while (true) {
                    Label_0206: {
                        synchronized (this) {
                            switch (this.a) {
                                case 2:
                                case 3: {
                                    if (k != this.i) {
                                        break;
                                    }
                                    o = k.a;
                                    this.i = null;
                                    millis = TimeUnit.NANOSECONDS.toMillis(k.g);
                                    ((j)o).f.addAndGet((int)millis);
                                    final Executor b2 = this.b;
                                    if (b != null) {
                                        break Label_0206;
                                    }
                                    final boolean b3 = true;
                                    ((j)o).a(b2, b3);
                                    millis2 = TimeUnit.NANOSECONDS.toMillis(k.h);
                                    millis3 = TimeUnit.NANOSECONDS.toMillis(k.i);
                                    if (b != null) {
                                        break Label_0212;
                                    }
                                    this.g.a(((j)o).c, millis, millis2, millis3);
                                    CBLogging.a("Downloader", "Downloaded " + ((j)o).c);
                                    if (this.a == 3) {
                                        CBLogging.a("Downloader", "Change state to PAUSED");
                                        this.a = 4;
                                        break;
                                    }
                                    break Label_0333;
                                }
                            }
                            return;
                        }
                    }
                    final boolean b3 = false;
                    continue Label_0178_Outer;
                }
                b = (CBError)b.b();
                this.g.a(((j)o).c, (String)b, millis, millis2, millis3);
                o = new StringBuilder().append("Failed to download ").append(((j)o).c);
                String string;
                if (append != null) {
                    string = " Status code=" + append.a;
                }
                else {
                    string = "";
                }
                append = (ag)((StringBuilder)o).append(string);
                String string2;
                if (b != null) {
                    string2 = " Error message=" + (String)b;
                }
                else {
                    string2 = "";
                }
                CBLogging.a("Downloader", ((StringBuilder)append).append(string2).toString());
                continue;
            }
            this.d();
        }
    }
    
    public void a(final AtomicInteger atomicInteger) {
        while (true) {
            while (true) {
                Label_0090: {
                    synchronized (this) {
                        atomicInteger.set(-10000);
                        switch (this.a) {
                            case 2: {
                                if (this.i.a.e != atomicInteger) {
                                    break Label_0090;
                                }
                                final int n = 1;
                                if (n != 0 && this.i.b()) {
                                    this.i = null;
                                    this.d();
                                    break;
                                }
                                break;
                            }
                        }
                        return;
                    }
                }
                final int n = 0;
                continue;
            }
        }
    }
    
    public void b() {
        while (true) {
            Label_0063: {
                synchronized (this) {
                    switch (this.a) {
                        case 3: {
                            CBLogging.a("Downloader", "Change state to DOWNLOADING");
                            this.a = 2;
                            break;
                        }
                        case 4: {
                            break Label_0063;
                        }
                    }
                    return;
                }
            }
            CBLogging.a("Downloader", "Change state to IDLE");
            this.a = 1;
            this.d();
        }
    }
    
    public void c() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/chartboost/sdk/impl/l.a:I
        //     6: istore_1       
        //     7: iload_1        
        //     8: iconst_1       
        //     9: if_icmpeq       15
        //    12: aload_0        
        //    13: monitorexit    
        //    14: return         
        //    15: ldc             "Downloader"
        //    17: ldc_w           "########### Trimming the disk cache"
        //    20: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;)V
        //    23: aload_0        
        //    24: getfield        com/chartboost/sdk/impl/l.h:Lcom/chartboost/sdk/Libraries/f;
        //    27: invokevirtual   com/chartboost/sdk/Libraries/f.d:()Lcom/chartboost/sdk/Libraries/g;
        //    30: getfield        com/chartboost/sdk/Libraries/g.a:Ljava/io/File;
        //    33: astore          17
        //    35: new             Ljava/util/ArrayList;
        //    38: dup            
        //    39: invokespecial   java/util/ArrayList.<init>:()V
        //    42: astore          18
        //    44: aload           17
        //    46: invokevirtual   java/io/File.list:()[Ljava/lang/String;
        //    49: astore          19
        //    51: aload           19
        //    53: ifnull          187
        //    56: aload           19
        //    58: arraylength    
        //    59: ifle            187
        //    62: aload           19
        //    64: arraylength    
        //    65: istore_2       
        //    66: iconst_0       
        //    67: istore_1       
        //    68: iload_1        
        //    69: iload_2        
        //    70: if_icmpge       187
        //    73: aload           19
        //    75: iload_1        
        //    76: aaload         
        //    77: astore          20
        //    79: aload           20
        //    81: ldc_w           "requests"
        //    84: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    87: ifne            628
        //    90: aload           20
        //    92: ldc_w           "track"
        //    95: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    98: ifne            628
        //   101: aload           20
        //   103: ldc_w           "session"
        //   106: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   109: ifne            628
        //   112: aload           20
        //   114: ldc_w           "videoCompletionEvents"
        //   117: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   120: ifne            628
        //   123: aload           20
        //   125: ldc_w           "."
        //   128: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   131: ifeq            137
        //   134: goto            628
        //   137: aload           18
        //   139: new             Ljava/io/File;
        //   142: dup            
        //   143: aload           17
        //   145: aload           20
        //   147: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   150: iconst_1       
        //   151: invokestatic    com/chartboost/sdk/Libraries/CBUtility.a:(Ljava/io/File;Z)Ljava/util/ArrayList;
        //   154: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   159: pop            
        //   160: goto            628
        //   163: astore          17
        //   165: aload_0        
        //   166: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   169: ldc_w           "reduceCacheSize"
        //   172: aload           17
        //   174: invokestatic    com/chartboost/sdk/Tracking/a.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Exception;)V
        //   177: goto            12
        //   180: astore          17
        //   182: aload_0        
        //   183: monitorexit    
        //   184: aload           17
        //   186: athrow         
        //   187: aload           18
        //   189: invokeinterface java/util/List.size:()I
        //   194: anewarray       Ljava/io/File;
        //   197: astore          17
        //   199: aload           18
        //   201: aload           17
        //   203: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   208: pop            
        //   209: aload           17
        //   211: arraylength    
        //   212: iconst_1       
        //   213: if_icmple       229
        //   216: aload           17
        //   218: new             Lcom/chartboost/sdk/impl/l$1;
        //   221: dup            
        //   222: aload_0        
        //   223: invokespecial   com/chartboost/sdk/impl/l$1.<init>:(Lcom/chartboost/sdk/impl/l;)V
        //   226: invokestatic    java/util/Arrays.sort:([Ljava/lang/Object;Ljava/util/Comparator;)V
        //   229: aload           17
        //   231: arraylength    
        //   232: ifle            607
        //   235: aload_0        
        //   236: getfield        com/chartboost/sdk/impl/l.e:Ljava/util/concurrent/atomic/AtomicReference;
        //   239: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //   242: checkcast       Lcom/chartboost/sdk/Model/e;
        //   245: astore          18
        //   247: aload           18
        //   249: getfield        com/chartboost/sdk/Model/e.u:I
        //   252: i2l            
        //   253: lstore          11
        //   255: aload_0        
        //   256: getfield        com/chartboost/sdk/impl/l.h:Lcom/chartboost/sdk/Libraries/f;
        //   259: aload_0        
        //   260: getfield        com/chartboost/sdk/impl/l.h:Lcom/chartboost/sdk/Libraries/f;
        //   263: invokevirtual   com/chartboost/sdk/Libraries/f.d:()Lcom/chartboost/sdk/Libraries/g;
        //   266: getfield        com/chartboost/sdk/Libraries/g.g:Ljava/io/File;
        //   269: invokevirtual   com/chartboost/sdk/Libraries/f.b:(Ljava/io/File;)J
        //   272: lstore          5
        //   274: aload_0        
        //   275: getfield        com/chartboost/sdk/impl/l.f:Lcom/chartboost/sdk/Libraries/i;
        //   278: invokevirtual   com/chartboost/sdk/Libraries/i.a:()J
        //   281: lstore          13
        //   283: aload           18
        //   285: getfield        com/chartboost/sdk/Model/e.d:Ljava/util/List;
        //   288: astore          19
        //   290: ldc             "Downloader"
        //   292: new             Ljava/lang/StringBuilder;
        //   295: dup            
        //   296: invokespecial   java/lang/StringBuilder.<init>:()V
        //   299: ldc_w           "Total local file count:"
        //   302: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   305: aload           17
        //   307: arraylength    
        //   308: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   311: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   314: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;)V
        //   317: ldc             "Downloader"
        //   319: new             Ljava/lang/StringBuilder;
        //   322: dup            
        //   323: invokespecial   java/lang/StringBuilder.<init>:()V
        //   326: ldc_w           "Video Folder Size in bytes :"
        //   329: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   332: lload           5
        //   334: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   337: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   340: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;)V
        //   343: ldc             "Downloader"
        //   345: new             Ljava/lang/StringBuilder;
        //   348: dup            
        //   349: invokespecial   java/lang/StringBuilder.<init>:()V
        //   352: ldc_w           "Max Bytes allowed:"
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: lload           11
        //   360: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   363: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   366: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;)V
        //   369: aload           17
        //   371: arraylength    
        //   372: istore          4
        //   374: iconst_0       
        //   375: istore_1       
        //   376: iload_1        
        //   377: iload           4
        //   379: if_icmpge       607
        //   382: aload           17
        //   384: iload_1        
        //   385: aaload         
        //   386: astore          20
        //   388: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   391: lload           13
        //   393: aload           20
        //   395: invokevirtual   java/io/File.lastModified:()J
        //   398: lsub           
        //   399: invokevirtual   java/util/concurrent/TimeUnit.toDays:(J)J
        //   402: aload           18
        //   404: getfield        com/chartboost/sdk/Model/e.w:I
        //   407: i2l            
        //   408: lcmp           
        //   409: iflt            651
        //   412: iconst_1       
        //   413: istore_2       
        //   414: aload           20
        //   416: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   419: ldc_w           ".tmp"
        //   422: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   425: istore          15
        //   427: aload           20
        //   429: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   432: astore          21
        //   434: aload           21
        //   436: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   439: ldc_w           "/videos"
        //   442: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   445: istore          16
        //   447: lload           5
        //   449: lload           11
        //   451: lcmp           
        //   452: ifle            656
        //   455: iload           16
        //   457: ifeq            656
        //   460: iconst_1       
        //   461: istore_3       
        //   462: aload           20
        //   464: invokevirtual   java/io/File.length:()J
        //   467: lconst_0       
        //   468: lcmp           
        //   469: ifeq            635
        //   472: iload           15
        //   474: ifne            635
        //   477: iload_2        
        //   478: ifne            635
        //   481: aload           19
        //   483: aload           21
        //   485: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   488: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   493: ifne            635
        //   496: iload_3        
        //   497: ifeq            661
        //   500: goto            635
        //   503: lload           5
        //   505: lstore          9
        //   507: iload_2        
        //   508: ifeq            640
        //   511: lload           5
        //   513: lstore          7
        //   515: iload           16
        //   517: ifeq            530
        //   520: lload           5
        //   522: aload           20
        //   524: invokevirtual   java/io/File.length:()J
        //   527: lsub           
        //   528: lstore          7
        //   530: ldc             "Downloader"
        //   532: new             Ljava/lang/StringBuilder;
        //   535: dup            
        //   536: invokespecial   java/lang/StringBuilder.<init>:()V
        //   539: ldc_w           "Deleting file at path:"
        //   542: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   545: aload           20
        //   547: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   550: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   553: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   556: invokestatic    com/chartboost/sdk/Libraries/CBLogging.a:(Ljava/lang/String;Ljava/lang/String;)V
        //   559: lload           7
        //   561: lstore          9
        //   563: aload           20
        //   565: invokevirtual   java/io/File.delete:()Z
        //   568: ifne            640
        //   571: ldc             "Downloader"
        //   573: new             Ljava/lang/StringBuilder;
        //   576: dup            
        //   577: invokespecial   java/lang/StringBuilder.<init>:()V
        //   580: ldc_w           "Unable to delete "
        //   583: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   586: aload           20
        //   588: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   591: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   594: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   597: invokestatic    com/chartboost/sdk/Libraries/CBLogging.b:(Ljava/lang/String;Ljava/lang/String;)V
        //   600: lload           7
        //   602: lstore          9
        //   604: goto            640
        //   607: aload_0        
        //   608: getfield        com/chartboost/sdk/impl/l.h:Lcom/chartboost/sdk/Libraries/f;
        //   611: invokevirtual   com/chartboost/sdk/Libraries/f.e:()Lorg/json/JSONObject;
        //   614: astore          17
        //   616: aload_0        
        //   617: getfield        com/chartboost/sdk/impl/l.g:Lcom/chartboost/sdk/Tracking/a;
        //   620: aload           17
        //   622: invokevirtual   com/chartboost/sdk/Tracking/a.a:(Lorg/json/JSONObject;)V
        //   625: goto            12
        //   628: iload_1        
        //   629: iconst_1       
        //   630: iadd           
        //   631: istore_1       
        //   632: goto            68
        //   635: iconst_1       
        //   636: istore_2       
        //   637: goto            503
        //   640: iload_1        
        //   641: iconst_1       
        //   642: iadd           
        //   643: istore_1       
        //   644: lload           9
        //   646: lstore          5
        //   648: goto            376
        //   651: iconst_0       
        //   652: istore_2       
        //   653: goto            414
        //   656: iconst_0       
        //   657: istore_3       
        //   658: goto            462
        //   661: iconst_0       
        //   662: istore_2       
        //   663: goto            503
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      7      180    187    Any
        //  15     51     163    180    Ljava/lang/Exception;
        //  15     51     180    187    Any
        //  56     66     163    180    Ljava/lang/Exception;
        //  56     66     180    187    Any
        //  79     134    163    180    Ljava/lang/Exception;
        //  79     134    180    187    Any
        //  137    160    163    180    Ljava/lang/Exception;
        //  137    160    180    187    Any
        //  165    177    180    187    Any
        //  187    229    163    180    Ljava/lang/Exception;
        //  187    229    180    187    Any
        //  229    374    163    180    Ljava/lang/Exception;
        //  229    374    180    187    Any
        //  388    412    163    180    Ljava/lang/Exception;
        //  388    412    180    187    Any
        //  414    447    163    180    Ljava/lang/Exception;
        //  414    447    180    187    Any
        //  462    472    163    180    Ljava/lang/Exception;
        //  462    472    180    187    Any
        //  481    496    163    180    Ljava/lang/Exception;
        //  481    496    180    187    Any
        //  520    530    163    180    Ljava/lang/Exception;
        //  520    530    180    187    Any
        //  530    559    163    180    Ljava/lang/Exception;
        //  530    559    180    187    Any
        //  563    600    163    180    Ljava/lang/Exception;
        //  563    600    180    187    Any
        //  607    625    163    180    Ljava/lang/Exception;
        //  607    625    180    187    Any
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
}
