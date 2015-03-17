package com.t70.vector.client.constant;

/**
 * Event schedule type.
 * 
 * <p>
 * Currently the only supported types are: {@link ScheduleType#NOW NOW}, {@link ScheduleType#ONE_TIME ONE_TIME} and
 * {@link ScheduleType#DAILY DAILY}.
 * </p>
 * 
 * <ul>
 * <li>{@link ScheduleType#NOW NOW}</li>
 * <li>{@link ScheduleType#ONE_TIME ONE_TIME}</li>
 * <li>{@link ScheduleType#SECOND SECOND}</li>
 * <li>{@link ScheduleType#MINUTE MINUTE}</li>
 * <li>{@link ScheduleType#HOUR HOUR}</li>
 * <li>{@link ScheduleType#DAILY DAILY}</li>
 * <li>{@link ScheduleType#WEEKLY WEEKLY}</li>
 * <li>{@link ScheduleType#MONTHLY MONTHLY}</li>
 * <li>{@link ScheduleType#YEARLY YEARLY}</li>
 * </ul>
 *
 */
public enum ScheduleType
{
    /**
     * Event is run immediately.
     */
    NOW(1),

    /**
     * Event will run once in the future and stop.
     */
    ONE_TIME(2),

    /**
     * Event will run every second. This is a place holder value and is not supported.
     */
    SECOND(3),

    /**
     * Event will run every minute. This is a place holder value and is not supported.
     */
    MINUTE(4),

    /**
     * The event will run every hour. This is a place holder value and is not supported.
     */
    HOUR(5),

    /**
     * The event will run once a day.
     */
    DAILY(6),

    /**
     * The event will run once a week. This is a place holder value and is not supported.
     */
    WEEKLY(7),

    /**
     * The event will run once a month. This is a place holder value and is not supported.
     */
    MONTHLY(8),

    /**
     * The event will run once a year. This is a place holder value and is not supported.
     */
    YEARLY(9);

    private int m_scheduleType;

    ScheduleType(int scheduleType)
    {
        m_scheduleType = scheduleType;
    }

    public int getValue()
    {
        return m_scheduleType;
    }

    public static ScheduleType scheduleType(int scheduleType)
    {
        ScheduleType type = null;

        switch (scheduleType)
        {
        case 1:
            type = ScheduleType.valueOf("Now");
            break;

        case 2:
            type = ScheduleType.valueOf("OneTime");
            break;

        case 3:
            type = ScheduleType.valueOf("Second");
            break;

        case 4:
            type = ScheduleType.valueOf("Minute");
            break;

        case 5:
            type = ScheduleType.valueOf("Hour");
            break;

        case 6:
            type = ScheduleType.valueOf("DAILY");
            break;

        case 7:
            type = ScheduleType.valueOf("WEEKLY");
            break;

        case 8:
            type = ScheduleType.valueOf("MONTHLY");
            break;

        case 9:
            type = ScheduleType.valueOf("YEARLY");
            break;
        }

        return type;
    }

}
