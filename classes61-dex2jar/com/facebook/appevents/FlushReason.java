// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

enum FlushReason
{
    EAGER_FLUSHING_EVENT, 
    EVENT_THRESHOLD, 
    EXPLICIT, 
    PERSISTED_EVENTS, 
    SESSION_CHANGE, 
    TIMER;
}
