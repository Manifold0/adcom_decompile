// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.log;

class DeviceLogEntry
{
    private DeviceLogLevel _logLevel;
    private String _originalMessage;
    private StackTraceElement _stackTraceElement;
    
    public DeviceLogEntry(final DeviceLogLevel logLevel, final String originalMessage, final StackTraceElement stackTraceElement) {
        this._logLevel = null;
        this._originalMessage = null;
        this._stackTraceElement = null;
        this._logLevel = logLevel;
        this._originalMessage = originalMessage;
        this._stackTraceElement = stackTraceElement;
    }
    
    public DeviceLogLevel getLogLevel() {
        return this._logLevel;
    }
    
    public String getParsedMessage() {
        final String originalMessage = this._originalMessage;
        String className = "UnknownClass";
        String methodName = "unknownMethod";
        int lineNumber = -1;
        if (this._stackTraceElement != null) {
            className = this._stackTraceElement.getClassName();
            methodName = this._stackTraceElement.getMethodName();
            lineNumber = this._stackTraceElement.getLineNumber();
        }
        String string;
        if ((string = originalMessage) != null) {
            string = originalMessage;
            if (!originalMessage.isEmpty()) {
                string = " :: " + originalMessage;
            }
        }
        String s;
        if ((s = string) == null) {
            s = "";
        }
        return className + "." + methodName + "()" + (" (line:" + lineNumber + ")") + s;
    }
}
