// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.Notification;
import rx.functions.Action1;
import rx.Observer;

public final class ActionNotificationObserver<T> implements Observer<T>
{
    final Action1<Notification<? super T>> onNotification;
    
    public ActionNotificationObserver(final Action1<Notification<? super T>> onNotification) {
        this.onNotification = onNotification;
    }
    
    @Override
    public void onCompleted() {
        this.onNotification.call(Notification.createOnCompleted());
    }
    
    @Override
    public void onError(final Throwable t) {
        this.onNotification.call(Notification.createOnError(t));
    }
    
    @Override
    public void onNext(final T t) {
        this.onNotification.call(Notification.createOnNext(t));
    }
}
