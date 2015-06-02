package com.fm.easyiotconnect.mq

import java.text.SimpleDateFormat


class TimedCommand {

    static final String TYPE_SEND_ON  = 'send_on'
    static final String TYPE_SEND_OFF = 'send_off'

    static belongsTo = [deviceInfos: DeviceInfos]

    Integer gpioId
    String type
    Date executionTime

    TimedRecurType recurringType

    Boolean recurringOnMonday
    Boolean recurringOnTuesday
    Boolean recurringOnWednesday
    Boolean recurringOnThursday
    Boolean recurringOnFriday
    Boolean recurringOnSaturday
    Boolean recurringOnSunday

    Date lastExecutionTime

    SendOffValues sendOffAfter

    String timeZoneName

    static constraints = {
        gpioId  (nullable: false, inList: 0..20)
        type    (nullable: false, blank: false, inList: [TYPE_SEND_ON, TYPE_SEND_OFF])

        executionTime   (nullable: false)

        recurringType        (nullable: true)
        recurringOnMonday    (nullable: true)
        recurringOnTuesday   (nullable: true)
        recurringOnWednesday (nullable: true)
        recurringOnThursday  (nullable: true)
        recurringOnFriday    (nullable: true)
        recurringOnSaturday  (nullable: true)
        recurringOnSunday    (nullable: true)

        lastExecutionTime    (nullable: true)

        sendOffAfter         (nullable: false)

        timeZoneName         (nullable: false)
    }


    String getExecutionTimeWithTZ() {
        SimpleDateFormat sdfAmerica = new SimpleDateFormat("dd/MMM/yyyy hh:mm a")
        sdfAmerica.setTimeZone(TimeZone.getTimeZone(timeZoneName))

        return sdfAmerica.format(executionTime)
    }
}

public enum TimedRecurType {
    NONE('None'),
    /*MINUTES_5('5 Minutes'),
    MINUTES_15('15 Minutes'), */
    MINUTES_30('30 Minutes'),
    MINUTES_45('45 Minutes'),
    HOURLY('Hourly'),
    HOURS_2("2 Hours"),
    HOURS_4("4 Hours"),
    HOURS_6("6 Hours"),
    HOURS_8("8 Hours"),
    HOURS_10("10 Hours"),
    HOURS_12("12 Hours"),
    DAILY('Daily'),
    WEEKLY('Weekly'),
    MONTHLY('Monthly'),
    YEARLY('Yearly')

    String name

    TimedRecurType(String name) {
        this.name = name
    }
}


public enum SendOffValues {
    NONE('None', null, null),
    MINUTES_5('5 Minutes',    5, Calendar.MINUTE),
    MINUTES_10('10 Minutes', 10, Calendar.MINUTE),
    MINUTES_15('15 Minutes', 15, Calendar.MINUTE),
    MINUTES_20('20 Minutes', 20, Calendar.MINUTE),
    MINUTES_25('25 Minutes', 25, Calendar.MINUTE),
    MINUTES_30('30 Minutes', 30, Calendar.MINUTE),
    MINUTES_45('45 Minutes', 45, Calendar.MINUTE),
    HOURLY('1 Hour',          1, Calendar.HOUR),
    HOURS_2("2 Hours",        2, Calendar.HOUR),
    HOURS_4("4 Hours",        4, Calendar.HOUR),
    HOURS_6("6 Hours",        6, Calendar.HOUR),
    HOURS_8("8 Hours",        8, Calendar.HOUR),
    HOURS_10("10 Hours",     10, Calendar.HOUR),
    HOURS_12("12 Hours",     12, Calendar.HOUR)
    /* HOURS_14("14 Hours",     12, Calendar.HOUR),
    HOURS_16("16 Hours",     12, Calendar.HOUR),
    HOURS_18("18 Hours",     12, Calendar.HOUR),
    HOURS_20("20 Hours",     12, Calendar.HOUR),
    HOURS_22("22 Hours",     12, Calendar.HOUR),
    HOURS_24("24 Hours",     12, Calendar.HOUR) */

    String name
    Integer value
    Integer type

    SendOffValues(String name, Integer value, Integer type) {
        this.name = name
        this.value = value
        this.type = type
    }
}
