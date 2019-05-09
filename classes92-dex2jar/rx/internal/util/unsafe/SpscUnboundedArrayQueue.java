// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

import java.util.Iterator;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
public class SpscUnboundedArrayQueue<E> extends SpscUnboundedArrayQueueConsumerField<E> implements QueueProgressIndicators
{
    private static final long C_INDEX_OFFSET;
    private static final Object HAS_NEXT;
    static final int MAX_LOOK_AHEAD_STEP;
    private static final long P_INDEX_OFFSET;
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: sipush          4096
        //     5: invokestatic    java/lang/Integer.getInteger:(Ljava/lang/String;I)Ljava/lang/Integer;
        //     8: invokevirtual   java/lang/Integer.intValue:()I
        //    11: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.MAX_LOOK_AHEAD_STEP:I
        //    14: new             Ljava/lang/Object;
        //    17: dup            
        //    18: invokespecial   java/lang/Object.<init>:()V
        //    21: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.HAS_NEXT:Ljava/lang/Object;
        //    24: getstatic       rx/internal/util/unsafe/UnsafeAccess.UNSAFE:Lsun/misc/Unsafe;
        //    27: ldc             [Ljava/lang/Object;.class
        //    29: invokevirtual   sun/misc/Unsafe.arrayIndexScale:(Ljava/lang/Class;)I
        //    32: istore_0       
        //    33: iconst_4       
        //    34: iload_0        
        //    35: if_icmpne       91
        //    38: iconst_2       
        //    39: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.REF_ELEMENT_SHIFT:I
        //    42: getstatic       rx/internal/util/unsafe/UnsafeAccess.UNSAFE:Lsun/misc/Unsafe;
        //    45: ldc             [Ljava/lang/Object;.class
        //    47: invokevirtual   sun/misc/Unsafe.arrayBaseOffset:(Ljava/lang/Class;)I
        //    50: i2l            
        //    51: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.REF_ARRAY_BASE:J
        //    54: ldc             Lrx/internal/util/unsafe/SpscUnboundedArrayQueueProducerFields;.class
        //    56: ldc             "producerIndex"
        //    58: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    61: astore_1       
        //    62: getstatic       rx/internal/util/unsafe/UnsafeAccess.UNSAFE:Lsun/misc/Unsafe;
        //    65: aload_1        
        //    66: invokevirtual   sun/misc/Unsafe.objectFieldOffset:(Ljava/lang/reflect/Field;)J
        //    69: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.P_INDEX_OFFSET:J
        //    72: ldc             Lrx/internal/util/unsafe/SpscUnboundedArrayQueueConsumerField;.class
        //    74: ldc             "consumerIndex"
        //    76: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    79: astore_1       
        //    80: getstatic       rx/internal/util/unsafe/UnsafeAccess.UNSAFE:Lsun/misc/Unsafe;
        //    83: aload_1        
        //    84: invokevirtual   sun/misc/Unsafe.objectFieldOffset:(Ljava/lang/reflect/Field;)J
        //    87: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.C_INDEX_OFFSET:J
        //    90: return         
        //    91: bipush          8
        //    93: iload_0        
        //    94: if_icmpne       104
        //    97: iconst_3       
        //    98: putstatic       rx/internal/util/unsafe/SpscUnboundedArrayQueue.REF_ELEMENT_SHIFT:I
        //   101: goto            42
        //   104: new             Ljava/lang/IllegalStateException;
        //   107: dup            
        //   108: ldc             "Unknown pointer size"
        //   110: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   113: athrow         
        //   114: astore_1       
        //   115: new             Ljava/lang/InternalError;
        //   118: dup            
        //   119: invokespecial   java/lang/InternalError.<init>:()V
        //   122: astore_2       
        //   123: aload_2        
        //   124: aload_1        
        //   125: invokevirtual   java/lang/InternalError.initCause:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   128: pop            
        //   129: aload_2        
        //   130: athrow         
        //   131: astore_1       
        //   132: new             Ljava/lang/InternalError;
        //   135: dup            
        //   136: invokespecial   java/lang/InternalError.<init>:()V
        //   139: astore_2       
        //   140: aload_2        
        //   141: aload_1        
        //   142: invokevirtual   java/lang/InternalError.initCause:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   145: pop            
        //   146: aload_2        
        //   147: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  54     72     114    131    Ljava/lang/NoSuchFieldException;
        //  72     90     131    148    Ljava/lang/NoSuchFieldException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0091:
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
    
    public SpscUnboundedArrayQueue(int roundToPowerOfTwo) {
        roundToPowerOfTwo = Pow2.roundToPowerOfTwo(roundToPowerOfTwo);
        final long n = roundToPowerOfTwo - 1;
        final Object[] array = new Object[roundToPowerOfTwo + 1];
        this.producerBuffer = (E[])array;
        this.producerMask = n;
        this.adjustLookAheadStep(roundToPowerOfTwo);
        this.consumerBuffer = (E[])array;
        this.consumerMask = n;
        this.producerLookAhead = n - 1L;
        this.soProducerIndex(0L);
    }
    
    private void adjustLookAheadStep(final int n) {
        this.producerLookAheadStep = Math.min(n / 4, SpscUnboundedArrayQueue.MAX_LOOK_AHEAD_STEP);
    }
    
    private static long calcDirectOffset(final long n) {
        return SpscUnboundedArrayQueue.REF_ARRAY_BASE + (n << SpscUnboundedArrayQueue.REF_ELEMENT_SHIFT);
    }
    
    private static long calcWrappedOffset(final long n, final long n2) {
        return calcDirectOffset(n & n2);
    }
    
    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, SpscUnboundedArrayQueue.C_INDEX_OFFSET);
    }
    
    private static <E> Object lvElement(final E[] array, final long n) {
        return UnsafeAccess.UNSAFE.getObjectVolatile(array, n);
    }
    
    private E[] lvNext(final E[] array) {
        return (E[])lvElement(array, calcDirectOffset(array.length - 1));
    }
    
    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, SpscUnboundedArrayQueue.P_INDEX_OFFSET);
    }
    
    private E newBufferPeek(final E[] consumerBuffer, final long n, final long n2) {
        this.consumerBuffer = consumerBuffer;
        return (E)lvElement(consumerBuffer, calcWrappedOffset(n, n2));
    }
    
    private E newBufferPoll(final E[] consumerBuffer, final long n, long calcWrappedOffset) {
        this.consumerBuffer = consumerBuffer;
        calcWrappedOffset = calcWrappedOffset(n, calcWrappedOffset);
        final Object lvElement = lvElement(consumerBuffer, calcWrappedOffset);
        if (lvElement == null) {
            return null;
        }
        soElement(consumerBuffer, calcWrappedOffset, null);
        this.soConsumerIndex(1L + n);
        return (E)lvElement;
    }
    
    private void resize(final E[] array, final long n, final long n2, final E e, final long n3) {
        final Object[] producerBuffer = new Object[array.length];
        this.producerBuffer = (E[])producerBuffer;
        this.producerLookAhead = n + n3 - 1L;
        soElement(producerBuffer, n2, e);
        this.soNext(array, (E[])producerBuffer);
        soElement(array, n2, SpscUnboundedArrayQueue.HAS_NEXT);
        this.soProducerIndex(n + 1L);
    }
    
    private void soConsumerIndex(final long n) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, SpscUnboundedArrayQueue.C_INDEX_OFFSET, n);
    }
    
    private static void soElement(final Object[] array, final long n, final Object o) {
        UnsafeAccess.UNSAFE.putOrderedObject(array, n, o);
    }
    
    private void soNext(final E[] array, final E[] array2) {
        soElement(array, calcDirectOffset(array.length - 1), array2);
    }
    
    private void soProducerIndex(final long n) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, SpscUnboundedArrayQueue.P_INDEX_OFFSET, n);
    }
    
    private boolean writeToQueue(final E[] array, final E e, final long n, final long n2) {
        soElement(array, n2, e);
        this.soProducerIndex(1L + n);
        return true;
    }
    
    @Override
    public long currentConsumerIndex() {
        return this.lvConsumerIndex();
    }
    
    @Override
    public long currentProducerIndex() {
        return this.lvProducerIndex();
    }
    
    @Override
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public final boolean offer(final E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        final E[] producerBuffer = this.producerBuffer;
        final long producerIndex = this.producerIndex;
        final long producerMask = this.producerMask;
        final long calcWrappedOffset = calcWrappedOffset(producerIndex, producerMask);
        if (producerIndex < this.producerLookAhead) {
            return this.writeToQueue(producerBuffer, e, producerIndex, calcWrappedOffset);
        }
        final int producerLookAheadStep = this.producerLookAheadStep;
        if (lvElement(producerBuffer, calcWrappedOffset(producerLookAheadStep + producerIndex, producerMask)) == null) {
            this.producerLookAhead = producerLookAheadStep + producerIndex - 1L;
            return this.writeToQueue(producerBuffer, e, producerIndex, calcWrappedOffset);
        }
        if (lvElement(producerBuffer, calcWrappedOffset(1L + producerIndex, producerMask)) != null) {
            return this.writeToQueue(producerBuffer, e, producerIndex, calcWrappedOffset);
        }
        this.resize(producerBuffer, producerIndex, calcWrappedOffset, e, producerMask);
        return true;
    }
    
    @Override
    public final E peek() {
        final E[] consumerBuffer = this.consumerBuffer;
        final long consumerIndex = this.consumerIndex;
        final long consumerMask = this.consumerMask;
        Object o;
        if ((o = lvElement(consumerBuffer, calcWrappedOffset(consumerIndex, consumerMask))) == SpscUnboundedArrayQueue.HAS_NEXT) {
            o = this.newBufferPeek(this.lvNext(consumerBuffer), consumerIndex, consumerMask);
        }
        return (E)o;
    }
    
    @Override
    public final E poll() {
        final E[] consumerBuffer = this.consumerBuffer;
        final long consumerIndex = this.consumerIndex;
        final long consumerMask = this.consumerMask;
        final long calcWrappedOffset = calcWrappedOffset(consumerIndex, consumerMask);
        final Object lvElement = lvElement(consumerBuffer, calcWrappedOffset);
        boolean b;
        if (lvElement == SpscUnboundedArrayQueue.HAS_NEXT) {
            b = true;
        }
        else {
            b = false;
        }
        if (lvElement != null && !b) {
            soElement(consumerBuffer, calcWrappedOffset, null);
            this.soConsumerIndex(1L + consumerIndex);
            return (E)lvElement;
        }
        if (b) {
            return this.newBufferPoll(this.lvNext(consumerBuffer), consumerIndex, consumerMask);
        }
        return null;
    }
    
    @Override
    public final int size() {
        long n = this.lvConsumerIndex();
        long n2;
        long n3;
        long lvProducerIndex;
        do {
            n2 = n;
            lvProducerIndex = this.lvProducerIndex();
            n3 = (n = this.lvConsumerIndex());
        } while (n2 != n3);
        return (int)(lvProducerIndex - n3);
    }
}
