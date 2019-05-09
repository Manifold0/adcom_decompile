// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util.unsafe;

abstract class SpscArrayQueueColdField<E> extends ConcurrentCircularArrayQueue<E>
{
    private static final Integer MAX_LOOK_AHEAD_STEP;
    protected final int lookAheadStep;
    
    static {
        MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    }
    
    public SpscArrayQueueColdField(final int n) {
        super(n);
        this.lookAheadStep = Math.min(n / 4, SpscArrayQueueColdField.MAX_LOOK_AHEAD_STEP);
    }
}
