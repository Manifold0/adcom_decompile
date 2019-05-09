// 
// Decompiled by Procyon v0.5.34
// 

package rx.schedulers;

public final class Timestamped<T>
{
    private final long timestampMillis;
    private final T value;
    
    public Timestamped(final long timestampMillis, final T value) {
        this.value = value;
        this.timestampMillis = timestampMillis;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Timestamped)) {
                return false;
            }
            final Timestamped timestamped = (Timestamped)o;
            if (this.timestampMillis != timestamped.timestampMillis || (this.value != timestamped.value && (this.value == null || !this.value.equals(timestamped.value)))) {
                return false;
            }
        }
        return true;
    }
    
    public long getTimestampMillis() {
        return this.timestampMillis;
    }
    
    public T getValue() {
        return this.value;
    }
    
    @Override
    public int hashCode() {
        final int n = (int)(this.timestampMillis ^ this.timestampMillis >>> 32);
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
        return String.format("Timestamped(timestampMillis = %d, value = %s)", this.timestampMillis, this.value.toString());
    }
}
