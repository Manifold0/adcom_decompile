// 
// Decompiled by Procyon v0.5.34
// 

package rx.subscriptions;

import java.util.Iterator;
import java.util.List;
import rx.exceptions.Exceptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;
import rx.Subscription;

public final class CompositeSubscription implements Subscription
{
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;
    
    public CompositeSubscription() {
    }
    
    public CompositeSubscription(final Subscription... array) {
        this.subscriptions = new HashSet<Subscription>(Arrays.asList(array));
    }
    
    private static void unsubscribeFromAll(Collection<Subscription> o) {
        if (o == null) {
            return;
        }
        final Collection<Subscription> collection = null;
        final Iterator<Subscription> iterator = ((Collection<Subscription>)o).iterator();
        o = collection;
        while (iterator.hasNext()) {
            final Subscription subscription = iterator.next();
            try {
                subscription.unsubscribe();
            }
            catch (Throwable t) {
                Collection<Subscription> collection2 = (Collection<Subscription>)o;
                if (o == null) {
                    collection2 = new ArrayList<Subscription>();
                }
                ((List<Subscription>)collection2).add((Subscription)t);
                o = collection2;
            }
        }
        Exceptions.throwIfAny((List<? extends Throwable>)o);
    }
    
    public void add(final Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    if (this.subscriptions == null) {
                        this.subscriptions = new HashSet<Subscription>(4);
                    }
                    this.subscriptions.add(subscription);
                    return;
                }
            }
        }
        // monitorexit(this)
        subscription.unsubscribe();
    }
    
    public void addAll(final Subscription... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        rx/subscriptions/CompositeSubscription.unsubscribed:Z
        //     4: ifne            81
        //     7: aload_0        
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        rx/subscriptions/CompositeSubscription.unsubscribed:Z
        //    13: ifne            79
        //    16: aload_0        
        //    17: getfield        rx/subscriptions/CompositeSubscription.subscriptions:Ljava/util/Set;
        //    20: ifnonnull       36
        //    23: aload_0        
        //    24: new             Ljava/util/HashSet;
        //    27: dup            
        //    28: aload_1        
        //    29: arraylength    
        //    30: invokespecial   java/util/HashSet.<init>:(I)V
        //    33: putfield        rx/subscriptions/CompositeSubscription.subscriptions:Ljava/util/Set;
        //    36: aload_1        
        //    37: arraylength    
        //    38: istore_3       
        //    39: iconst_0       
        //    40: istore_2       
        //    41: iload_2        
        //    42: iload_3        
        //    43: if_icmpge       76
        //    46: aload_1        
        //    47: iload_2        
        //    48: aaload         
        //    49: astore          4
        //    51: aload           4
        //    53: invokeinterface rx/Subscription.isUnsubscribed:()Z
        //    58: ifne            111
        //    61: aload_0        
        //    62: getfield        rx/subscriptions/CompositeSubscription.subscriptions:Ljava/util/Set;
        //    65: aload           4
        //    67: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    72: pop            
        //    73: goto            111
        //    76: aload_0        
        //    77: monitorexit    
        //    78: return         
        //    79: aload_0        
        //    80: monitorexit    
        //    81: aload_1        
        //    82: arraylength    
        //    83: istore_3       
        //    84: iconst_0       
        //    85: istore_2       
        //    86: iload_2        
        //    87: iload_3        
        //    88: if_icmpge       118
        //    91: aload_1        
        //    92: iload_2        
        //    93: aaload         
        //    94: invokeinterface rx/Subscription.unsubscribe:()V
        //    99: iload_2        
        //   100: iconst_1       
        //   101: iadd           
        //   102: istore_2       
        //   103: goto            86
        //   106: astore_1       
        //   107: aload_0        
        //   108: monitorexit    
        //   109: aload_1        
        //   110: athrow         
        //   111: iload_2        
        //   112: iconst_1       
        //   113: iadd           
        //   114: istore_2       
        //   115: goto            41
        //   118: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      36     106    111    Any
        //  36     39     106    111    Any
        //  51     73     106    111    Any
        //  76     78     106    111    Any
        //  79     81     106    111    Any
        //  107    109    106    111    Any
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
    
    public void clear() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed || this.subscriptions == null) {
                    return;
                }
                final Set<Subscription> subscriptions = this.subscriptions;
                this.subscriptions = null;
                // monitorexit(this)
                unsubscribeFromAll(subscriptions);
            }
        }
    }
    
    public boolean hasSubscriptions() {
        final boolean b = false;
        if (!this.unsubscribed) {
            // monitorenter(this)
            boolean b2 = b;
            try {
                if (!this.unsubscribed) {
                    b2 = b;
                    if (this.subscriptions != null) {
                        b2 = b;
                        if (!this.subscriptions.isEmpty()) {
                            b2 = true;
                        }
                    }
                }
                return b2;
            }
            finally {
            }
            // monitorexit(this)
        }
        return false;
    }
    
    @Override
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }
    
    public void remove(final Subscription subscription) {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed || this.subscriptions == null) {
                    return;
                }
                final boolean remove = this.subscriptions.remove(subscription);
                // monitorexit(this)
                if (remove) {
                    subscription.unsubscribe();
                }
            }
        }
    }
    
    @Override
    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                final Set<Subscription> subscriptions = this.subscriptions;
                this.subscriptions = null;
                // monitorexit(this)
                unsubscribeFromAll(subscriptions);
            }
        }
    }
}
