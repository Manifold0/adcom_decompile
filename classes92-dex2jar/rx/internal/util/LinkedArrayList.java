// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import java.util.ArrayList;
import java.util.List;

public class LinkedArrayList
{
    final int capacityHint;
    Object[] head;
    int indexInTail;
    volatile int size;
    Object[] tail;
    
    public LinkedArrayList(final int capacityHint) {
        this.capacityHint = capacityHint;
    }
    
    public void add(final Object o) {
        if (this.size == 0) {
            this.head = new Object[this.capacityHint + 1];
            this.tail = this.head;
            this.head[0] = o;
            this.indexInTail = 1;
            this.size = 1;
            return;
        }
        if (this.indexInTail == this.capacityHint) {
            final Object[] tail = new Object[this.capacityHint + 1];
            tail[0] = o;
            this.tail[this.capacityHint] = tail;
            this.tail = tail;
            this.indexInTail = 1;
            ++this.size;
            return;
        }
        this.tail[this.indexInTail] = o;
        ++this.indexInTail;
        ++this.size;
    }
    
    public int capacityHint() {
        return this.capacityHint;
    }
    
    public Object[] head() {
        return this.head;
    }
    
    public int indexInTail() {
        return this.indexInTail;
    }
    
    public int size() {
        return this.size;
    }
    
    public Object[] tail() {
        return this.tail;
    }
    
    List<Object> toList() {
        final int capacityHint = this.capacityHint;
        final int size = this.size;
        final ArrayList list = new ArrayList<Object>(size + 1);
        Object[] head = this.head();
        int i = 0;
        int n = 0;
        while (i < size) {
            list.add(head[n]);
            final int n2 = i + 1;
            final int n3 = n + 1;
            i = n2;
            if ((n = n3) == capacityHint) {
                n = 0;
                head = (Object[])head[capacityHint];
                i = n2;
            }
        }
        return (List<Object>)list;
    }
    
    @Override
    public String toString() {
        return this.toList().toString();
    }
}
