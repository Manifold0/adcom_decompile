package com.unity3d.services.core.log;

class DeviceLogEntry {
    private DeviceLogLevel _logLevel = null;
    private String _originalMessage = null;
    private StackTraceElement _stackTraceElement = null;

    public DeviceLogEntry(DeviceLogLevel logLevel, String originalMessage, StackTraceElement stackTraceElement) {
        this._logLevel = logLevel;
        this._originalMessage = originalMessage;
        this._stackTraceElement = stackTraceElement;
    }

    public DeviceLogLevel getLogLevel() {
        return this._logLevel;
    }

    public String getParsedMessage() {
        String message = this._originalMessage;
        String className = "UnknownClass";
        String methodName = "unknownMethod";
        int lineNumber = -1;
        if (this._stackTraceElement != null) {
            className = this._stackTraceElement.getClassName();
            methodName = this._stackTraceElement.getMethodName();
            lineNumber = this._stackTraceElement.getLineNumber();
        }
        if (!(message == null || message.isEmpty())) {
            message = " :: " + message;
        }
        if (message == null) {
            message = "";
        }
        return className + "." + methodName + "()" + (" (line:" + lineNumber + ")") + message;
    }
}
