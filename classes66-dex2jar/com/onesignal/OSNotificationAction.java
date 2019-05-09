// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

public class OSNotificationAction
{
    public String actionID;
    public ActionType type;
    
    public enum ActionType
    {
        ActionTaken, 
        Opened;
    }
}
