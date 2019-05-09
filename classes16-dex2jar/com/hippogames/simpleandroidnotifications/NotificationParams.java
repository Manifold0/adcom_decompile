// 
// Decompiled by Procyon v0.5.34
// 

package com.hippogames.simpleandroidnotifications;

import java.io.Serializable;

public class NotificationParams implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String CallbackData;
    public String ChannelId;
    public String ChannelName;
    public String CustomSound;
    public int ExecuteMode;
    public String GroupName;
    public String GroupSummary;
    public int Id;
    public String LargeIcon;
    public boolean Light;
    public int LightColor;
    public int LightOffMs;
    public int LightOnMs;
    public String Message;
    public boolean Multiline;
    public boolean Repeat;
    public long RepeatIntervalMs;
    public String SmallIcon;
    public int SmallIconColor;
    public boolean Sound;
    public String Ticker;
    public String Title;
    public long TriggerAtMillis;
    public String UnityClass;
    public boolean Vibrate;
    public long[] Vibration;
}
