// 
// Decompiled by Procyon v0.5.34
// 

package android.arch.lifecycle;

import android.support.annotation.Nullable;
import android.arch.core.internal.SafeIterableMap$IteratorWithAdditions;
import java.util.Iterator;
import java.util.Map;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import android.arch.core.internal.FastSafeIterableMap;

public class LifecycleRegistry extends Lifecycle
{
    private int mAddingObserverCounter;
    private boolean mHandlingEvent;
    private final LifecycleOwner mLifecycleOwner;
    private boolean mNewEventOccurred;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap;
    private ArrayList<Lifecycle$State> mParentStates;
    private Lifecycle$State mState;
    
    public LifecycleRegistry(@NonNull final LifecycleOwner mLifecycleOwner) {
        this.mObserverMap = (FastSafeIterableMap<LifecycleObserver, ObserverWithState>)new FastSafeIterableMap();
        this.mAddingObserverCounter = 0;
        this.mHandlingEvent = false;
        this.mNewEventOccurred = false;
        this.mParentStates = new ArrayList<Lifecycle$State>();
        this.mLifecycleOwner = mLifecycleOwner;
        this.mState = Lifecycle$State.INITIALIZED;
    }
    
    private void backwardPass() {
        final Iterator descendingIterator = this.mObserverMap.descendingIterator();
        while (descendingIterator.hasNext() && !this.mNewEventOccurred) {
            final Map.Entry<K, ObserverWithState> entry = descendingIterator.next();
            final ObserverWithState observerWithState = entry.getValue();
            while (observerWithState.mState.compareTo((Enum)this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains((Object)entry.getKey())) {
                final Lifecycle$Event downEvent = downEvent(observerWithState.mState);
                this.pushParentState(getStateAfter(downEvent));
                observerWithState.dispatchEvent(this.mLifecycleOwner, downEvent);
                this.popParentState();
            }
        }
    }
    
    private Lifecycle$State calculateTargetState(final LifecycleObserver lifecycleObserver) {
        final Map.Entry ceil = this.mObserverMap.ceil((Object)lifecycleObserver);
        Lifecycle$State mState;
        if (ceil != null) {
            mState = ceil.getValue().mState;
        }
        else {
            mState = null;
        }
        Lifecycle$State lifecycle$State;
        if (!this.mParentStates.isEmpty()) {
            lifecycle$State = this.mParentStates.get(this.mParentStates.size() - 1);
        }
        else {
            lifecycle$State = null;
        }
        return min(min(this.mState, mState), lifecycle$State);
    }
    
    private static Lifecycle$Event downEvent(final Lifecycle$State lifecycle$State) {
        switch (lifecycle$State) {
            default: {
                throw new IllegalArgumentException("Unexpected state value " + lifecycle$State);
            }
            case INITIALIZED: {
                throw new IllegalArgumentException();
            }
            case CREATED: {
                return Lifecycle$Event.ON_DESTROY;
            }
            case STARTED: {
                return Lifecycle$Event.ON_STOP;
            }
            case RESUMED: {
                return Lifecycle$Event.ON_PAUSE;
            }
            case DESTROYED: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    private void forwardPass() {
        final SafeIterableMap$IteratorWithAdditions iteratorWithAdditions = this.mObserverMap.iteratorWithAdditions();
        while (((Iterator)iteratorWithAdditions).hasNext() && !this.mNewEventOccurred) {
            final Map.Entry<K, ObserverWithState> entry = ((Iterator<Map.Entry<K, ObserverWithState>>)iteratorWithAdditions).next();
            final ObserverWithState observerWithState = entry.getValue();
            while (observerWithState.mState.compareTo((Enum)this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains((Object)entry.getKey())) {
                this.pushParentState(observerWithState.mState);
                observerWithState.dispatchEvent(this.mLifecycleOwner, upEvent(observerWithState.mState));
                this.popParentState();
            }
        }
    }
    
    static Lifecycle$State getStateAfter(final Lifecycle$Event lifecycle$Event) {
        switch (lifecycle$Event) {
            default: {
                throw new IllegalArgumentException("Unexpected event value " + lifecycle$Event);
            }
            case ON_CREATE:
            case ON_STOP: {
                return Lifecycle$State.CREATED;
            }
            case ON_START:
            case ON_PAUSE: {
                return Lifecycle$State.STARTED;
            }
            case ON_RESUME: {
                return Lifecycle$State.RESUMED;
            }
            case ON_DESTROY: {
                return Lifecycle$State.DESTROYED;
            }
        }
    }
    
    private boolean isSynced() {
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        final Lifecycle$State mState = this.mObserverMap.eldest().getValue().mState;
        final Lifecycle$State mState2 = this.mObserverMap.newest().getValue().mState;
        return mState == mState2 && this.mState == mState2;
    }
    
    static Lifecycle$State min(@NonNull final Lifecycle$State lifecycle$State, @Nullable final Lifecycle$State lifecycle$State2) {
        if (lifecycle$State2 != null && lifecycle$State2.compareTo((Enum)lifecycle$State) < 0) {
            return lifecycle$State2;
        }
        return lifecycle$State;
    }
    
    private void popParentState() {
        this.mParentStates.remove(this.mParentStates.size() - 1);
    }
    
    private void pushParentState(final Lifecycle$State lifecycle$State) {
        this.mParentStates.add(lifecycle$State);
    }
    
    private void sync() {
        while (!this.isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo((Enum)this.mObserverMap.eldest().getValue().mState) < 0) {
                this.backwardPass();
            }
            final Map.Entry newest = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && newest != null && this.mState.compareTo((Enum)newest.getValue().mState) > 0) {
                this.forwardPass();
            }
        }
        this.mNewEventOccurred = false;
    }
    
    private static Lifecycle$Event upEvent(final Lifecycle$State lifecycle$State) {
        switch (lifecycle$State) {
            default: {
                throw new IllegalArgumentException("Unexpected state value " + lifecycle$State);
            }
            case INITIALIZED:
            case DESTROYED: {
                return Lifecycle$Event.ON_CREATE;
            }
            case CREATED: {
                return Lifecycle$Event.ON_START;
            }
            case STARTED: {
                return Lifecycle$Event.ON_RESUME;
            }
            case RESUMED: {
                throw new IllegalArgumentException();
            }
        }
    }
    
    public void addObserver(final LifecycleObserver lifecycleObserver) {
        Lifecycle$State lifecycle$State;
        if (this.mState == Lifecycle$State.DESTROYED) {
            lifecycle$State = Lifecycle$State.DESTROYED;
        }
        else {
            lifecycle$State = Lifecycle$State.INITIALIZED;
        }
        final ObserverWithState observerWithState = new ObserverWithState(lifecycleObserver, lifecycle$State);
        if (this.mObserverMap.putIfAbsent((Object)lifecycleObserver, (Object)observerWithState) != null) {
            return;
        }
        final boolean b = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
        Lifecycle$State lifecycle$State2 = this.calculateTargetState(lifecycleObserver);
        ++this.mAddingObserverCounter;
        while (observerWithState.mState.compareTo((Enum)lifecycle$State2) < 0 && this.mObserverMap.contains((Object)lifecycleObserver)) {
            this.pushParentState(observerWithState.mState);
            observerWithState.dispatchEvent(this.mLifecycleOwner, upEvent(observerWithState.mState));
            this.popParentState();
            lifecycle$State2 = this.calculateTargetState(lifecycleObserver);
        }
        if (!b) {
            this.sync();
        }
        --this.mAddingObserverCounter;
    }
    
    public Lifecycle$State getCurrentState() {
        return this.mState;
    }
    
    public int getObserverCount() {
        return this.mObserverMap.size();
    }
    
    public void handleLifecycleEvent(final Lifecycle$Event lifecycle$Event) {
        this.mState = getStateAfter(lifecycle$Event);
        if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
            this.mNewEventOccurred = true;
            return;
        }
        this.mHandlingEvent = true;
        this.sync();
        this.mHandlingEvent = false;
    }
    
    public void markState(final Lifecycle$State mState) {
        this.mState = mState;
    }
    
    public void removeObserver(final LifecycleObserver lifecycleObserver) {
        this.mObserverMap.remove((Object)lifecycleObserver);
    }
    
    static class ObserverWithState
    {
        GenericLifecycleObserver mLifecycleObserver;
        Lifecycle$State mState;
        
        ObserverWithState(final LifecycleObserver lifecycleObserver, final Lifecycle$State mState) {
            this.mLifecycleObserver = Lifecycling.getCallback((Object)lifecycleObserver);
            this.mState = mState;
        }
        
        void dispatchEvent(final LifecycleOwner lifecycleOwner, final Lifecycle$Event lifecycle$Event) {
            final Lifecycle$State stateAfter = LifecycleRegistry.getStateAfter(lifecycle$Event);
            this.mState = LifecycleRegistry.min(this.mState, stateAfter);
            this.mLifecycleObserver.onStateChanged(lifecycleOwner, lifecycle$Event);
            this.mState = stateAfter;
        }
    }
}
