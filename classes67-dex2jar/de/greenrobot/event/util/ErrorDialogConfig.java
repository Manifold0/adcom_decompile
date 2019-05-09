// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event.util;

import android.util.Log;
import android.content.res.Resources;
import de.greenrobot.event.EventBus;

public class ErrorDialogConfig
{
    int defaultDialogIconId;
    final int defaultErrorMsgId;
    Class<?> defaultEventTypeOnDialogClosed;
    final int defaultTitleId;
    EventBus eventBus;
    boolean logExceptions;
    final ExceptionToResourceMapping mapping;
    final Resources resources;
    String tagForLoggingExceptions;
    
    public ErrorDialogConfig(final Resources resources, final int defaultTitleId, final int defaultErrorMsgId) {
        this.logExceptions = true;
        this.resources = resources;
        this.defaultTitleId = defaultTitleId;
        this.defaultErrorMsgId = defaultErrorMsgId;
        this.mapping = new ExceptionToResourceMapping();
    }
    
    public ErrorDialogConfig addMapping(final Class<? extends Throwable> clazz, final int n) {
        this.mapping.addMapping(clazz, n);
        return this;
    }
    
    public void disableExceptionLogging() {
        this.logExceptions = false;
    }
    
    EventBus getEventBus() {
        if (this.eventBus != null) {
            return this.eventBus;
        }
        return EventBus.getDefault();
    }
    
    public int getMessageIdForThrowable(final Throwable t) {
        final Integer mapThrowable = this.mapping.mapThrowable(t);
        if (mapThrowable != null) {
            return mapThrowable;
        }
        Log.d(EventBus.TAG, "No specific message ressource ID found for " + t);
        return this.defaultErrorMsgId;
    }
    
    public void setDefaultDialogIconId(final int defaultDialogIconId) {
        this.defaultDialogIconId = defaultDialogIconId;
    }
    
    public void setDefaultEventTypeOnDialogClosed(final Class<?> defaultEventTypeOnDialogClosed) {
        this.defaultEventTypeOnDialogClosed = defaultEventTypeOnDialogClosed;
    }
    
    public void setEventBus(final EventBus eventBus) {
        this.eventBus = eventBus;
    }
    
    public void setTagForLoggingExceptions(final String tagForLoggingExceptions) {
        this.tagForLoggingExceptions = tagForLoggingExceptions;
    }
}
