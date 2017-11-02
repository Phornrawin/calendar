package common.model;

import java.io.Serializable;

public final class EventType implements Serializable{
    public static final String EMPTY = "never";
    public static final String DAILY = "daily";
    public static final String WEEKLY = "weekly";
    public static final String MONTHLY = "monthly";
    public static final String YEARLY = "yearly";
}
