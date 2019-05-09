package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class LifecycleRegistry extends Lifecycle {
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private final LifecycleOwner mLifecycleOwner;
    private boolean mNewEventOccurred = false;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap = new FastSafeIterableMap();
    private ArrayList<State> mParentStates = new ArrayList();
    private State mState;

    /* renamed from: android.arch.lifecycle.LifecycleRegistry$1 */
    static /* synthetic */ class C09881 {
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$Event = new int[Event.values().length];
        static final /* synthetic */ int[] $SwitchMap$android$arch$lifecycle$Lifecycle$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$State[State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$arch$lifecycle$Lifecycle$Event[Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    static class ObserverWithState {
        GenericLifecycleObserver mLifecycleObserver;
        State mState;

        ObserverWithState(LifecycleObserver observer, State initialState) {
            this.mLifecycleObserver = Lifecycling.getCallback(observer);
            this.mState = initialState;
        }

        void dispatchEvent(LifecycleOwner owner, Event event) {
            State newState = LifecycleRegistry.getStateAfter(event);
            this.mState = LifecycleRegistry.min(this.mState, newState);
            this.mLifecycleObserver.onStateChanged(owner, event);
            this.mState = newState;
        }
    }

    public LifecycleRegistry(@NonNull LifecycleOwner provider) {
        this.mLifecycleOwner = provider;
        this.mState = State.INITIALIZED;
    }

    public void markState(State state) {
        this.mState = state;
    }

    public void handleLifecycleEvent(Event event) {
        this.mState = getStateAfter(event);
        if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
            this.mNewEventOccurred = true;
            return;
        }
        this.mHandlingEvent = true;
        sync();
        this.mHandlingEvent = false;
    }

    private boolean isSynced() {
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        State eldestObserverState = ((ObserverWithState) this.mObserverMap.eldest().getValue()).mState;
        State newestObserverState = ((ObserverWithState) this.mObserverMap.newest().getValue()).mState;
        boolean z = eldestObserverState == newestObserverState && this.mState == newestObserverState;
        return z;
    }

    private State calculateTargetState(LifecycleObserver observer) {
        State siblingState;
        State parentState;
        Entry<LifecycleObserver, ObserverWithState> previous = this.mObserverMap.ceil(observer);
        if (previous != null) {
            siblingState = ((ObserverWithState) previous.getValue()).mState;
        } else {
            siblingState = null;
        }
        if (this.mParentStates.isEmpty()) {
            parentState = null;
        } else {
            parentState = (State) this.mParentStates.get(this.mParentStates.size() - 1);
        }
        return min(min(this.mState, siblingState), parentState);
    }

    public void addObserver(LifecycleObserver observer) {
        ObserverWithState statefulObserver = new ObserverWithState(observer, this.mState == State.DESTROYED ? State.DESTROYED : State.INITIALIZED);
        if (((ObserverWithState) this.mObserverMap.putIfAbsent(observer, statefulObserver)) == null) {
            boolean isReentrance = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
            State targetState = calculateTargetState(observer);
            this.mAddingObserverCounter++;
            while (statefulObserver.mState.compareTo(targetState) < 0 && this.mObserverMap.contains(observer)) {
                pushParentState(statefulObserver.mState);
                statefulObserver.dispatchEvent(this.mLifecycleOwner, upEvent(statefulObserver.mState));
                popParentState();
                targetState = calculateTargetState(observer);
            }
            if (!isReentrance) {
                sync();
            }
            this.mAddingObserverCounter--;
        }
    }

    private void popParentState() {
        this.mParentStates.remove(this.mParentStates.size() - 1);
    }

    private void pushParentState(State state) {
        this.mParentStates.add(state);
    }

    public void removeObserver(LifecycleObserver observer) {
        this.mObserverMap.remove(observer);
    }

    public int getObserverCount() {
        return this.mObserverMap.size();
    }

    public State getCurrentState() {
        return this.mState;
    }

    static State getStateAfter(Event event) {
        switch (C09881.$SwitchMap$android$arch$lifecycle$Lifecycle$Event[event.ordinal()]) {
            case 1:
            case 2:
                return State.CREATED;
            case 3:
            case 4:
                return State.STARTED;
            case 5:
                return State.RESUMED;
            case 6:
                return State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    private static Event downEvent(State state) {
        switch (C09881.$SwitchMap$android$arch$lifecycle$Lifecycle$State[state.ordinal()]) {
            case 1:
                throw new IllegalArgumentException();
            case 2:
                return Event.ON_DESTROY;
            case 3:
                return Event.ON_STOP;
            case 4:
                return Event.ON_PAUSE;
            case 5:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private static Event upEvent(State state) {
        switch (C09881.$SwitchMap$android$arch$lifecycle$Lifecycle$State[state.ordinal()]) {
            case 1:
            case 5:
                return Event.ON_CREATE;
            case 2:
                return Event.ON_START;
            case 3:
                return Event.ON_RESUME;
            case 4:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + state);
        }
    }

    private void forwardPass() {
        Iterator<Entry<LifecycleObserver, ObserverWithState>> ascendingIterator = this.mObserverMap.iteratorWithAdditions();
        while (ascendingIterator.hasNext() && !this.mNewEventOccurred) {
            Entry<LifecycleObserver, ObserverWithState> entry = (Entry) ascendingIterator.next();
            ObserverWithState observer = (ObserverWithState) entry.getValue();
            while (observer.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                pushParentState(observer.mState);
                observer.dispatchEvent(this.mLifecycleOwner, upEvent(observer.mState));
                popParentState();
            }
        }
    }

    private void backwardPass() {
        Iterator<Entry<LifecycleObserver, ObserverWithState>> descendingIterator = this.mObserverMap.descendingIterator();
        while (descendingIterator.hasNext() && !this.mNewEventOccurred) {
            Entry<LifecycleObserver, ObserverWithState> entry = (Entry) descendingIterator.next();
            ObserverWithState observer = (ObserverWithState) entry.getValue();
            while (observer.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                Event event = downEvent(observer.mState);
                pushParentState(getStateAfter(event));
                observer.dispatchEvent(this.mLifecycleOwner, event);
                popParentState();
            }
        }
    }

    private void sync() {
        while (!isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo(((ObserverWithState) this.mObserverMap.eldest().getValue()).mState) < 0) {
                backwardPass();
            }
            Entry<LifecycleObserver, ObserverWithState> newest = this.mObserverMap.newest();
            if (!(this.mNewEventOccurred || newest == null || this.mState.compareTo(((ObserverWithState) newest.getValue()).mState) <= 0)) {
                forwardPass();
            }
        }
        this.mNewEventOccurred = false;
    }

    static State min(@NonNull State state1, @Nullable State state2) {
        return (state2 == null || state2.compareTo(state1) >= 0) ? state1 : state2;
    }
}
