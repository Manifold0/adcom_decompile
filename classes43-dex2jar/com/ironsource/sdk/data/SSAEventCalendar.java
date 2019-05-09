// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

public class SSAEventCalendar extends SSAObj
{
    private String DAILY;
    private String DAYS_IN_MONTH;
    private String DAYS_IN_WEEK;
    private String DAYS_IN_YEAR;
    private String DESCRIPTION;
    private String END;
    private String EXCEPTIONDATES;
    private String EXPIRES;
    private String FREQUENCY;
    private String ID;
    private String INTERVAL;
    private String MONTHLY;
    private String MONTHS_IN_YEAR;
    private String RECURRENCE;
    private String REMINDER;
    private String START;
    private String STATUS;
    private String WEEKLY;
    private String WEEKS_IN_MONTH;
    private String YEARLY;
    private String mDescription;
    private String mEnd;
    private String mStart;
    
    public SSAEventCalendar(final String s) {
        super(s);
        this.ID = "id";
        this.DESCRIPTION = "description";
        this.START = "init";
        this.END = "end";
        this.STATUS = "status";
        this.RECURRENCE = "recurrence";
        this.REMINDER = "reminder";
        this.FREQUENCY = "frequency";
        this.INTERVAL = "interval";
        this.EXPIRES = "expires";
        this.EXCEPTIONDATES = "exceptionDates";
        this.DAYS_IN_WEEK = "daysInWeek";
        this.DAYS_IN_MONTH = "daysInMonth";
        this.DAYS_IN_YEAR = "daysInYear";
        this.WEEKS_IN_MONTH = "weeksInMonth";
        this.MONTHS_IN_YEAR = "monthsInYear";
        this.DAILY = "daily";
        this.WEEKLY = "weekly";
        this.MONTHLY = "monthly";
        this.YEARLY = "yearly";
        if (this.containsKey(this.DESCRIPTION)) {
            this.setDescription(this.getString(this.DESCRIPTION));
        }
        if (this.containsKey(this.START)) {
            this.setStart(this.getString(this.START));
        }
        if (this.containsKey(this.END)) {
            this.setEnd(this.getString(this.END));
        }
    }
    
    public String getDescription() {
        return this.mDescription;
    }
    
    public String getEnd() {
        return this.mEnd;
    }
    
    public String getStart() {
        return this.mStart;
    }
    
    public void setDescription(final String mDescription) {
        this.mDescription = mDescription;
    }
    
    public void setEnd(final String mEnd) {
        this.mEnd = mEnd;
    }
    
    public void setStart(final String mStart) {
        this.mStart = mStart;
    }
}
