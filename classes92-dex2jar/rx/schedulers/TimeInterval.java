// 
// Decompiled by Procyon v0.5.34
// 

package rx.schedulers;

public class TimeInterval<T>
{
    private final long intervalInMilliseconds;
    private final T value;
    
    public TimeInterval(final long intervalInMilliseconds, final T value) {
        this.value = value;
        this.intervalInMilliseconds = intervalInMilliseconds;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final TimeInterval timeInterval = (TimeInterval)o;
            if (this.intervalInMilliseconds != timeInterval.intervalInMilliseconds) {
                return false;
            }
            if (this.value == null) {
                if (timeInterval.value != null) {
                    return false;
                }
            }
            else if (!this.value.equals(timeInterval.value)) {
                return false;
            }
        }
        return true;
    }
    
    public long getIntervalInMilliseconds() {
        return this.intervalInMilliseconds;
    }
    
    public T getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        final int n = (int)(this.intervalInMilliseconds ^ this.intervalInMilliseconds >>> 32);
        int hashCode;
        if (this.value == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.value.hashCode();
        }
        return 31 * (n + 31) + hashCode;
    }
    
    @Override
    public String toString() {
        return "TimeInterval [intervalInMilliseconds=" + this.intervalInMilliseconds + ", value=" + this.value + "]";
    }
}
