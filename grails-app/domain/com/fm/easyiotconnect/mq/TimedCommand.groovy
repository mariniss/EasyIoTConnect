package com.fm.easyiotconnect.mq


class TimedCommand {

    static final String TYPE_SEND_ON  = 'send_on'
    static final String TYPE_SEND_OFF = 'send_off'

    static belongsTo = [deviceInfos: DeviceInfos]

    Integer gpioId
    String type
    Date executionTime

    TimedRecurType recurringType
    Date recurringEndTime

    Boolean recurringOnMonday
    Boolean recurringOnTuesday
    Boolean recurringOnWednesday
    Boolean recurringOnThursday
    Boolean recurringOnFriday
    Boolean recurringOnSaturday
    Boolean recurringOnSunday

    static constraints = {
        gpioId  (nullable: false, inList: 0..20)
        type    (nullable: false, blank: false, inList: [TYPE_SEND_ON, TYPE_SEND_OFF])

        executionTime   (nullable: false)

        recurringType        (nullable: true)
        recurringEndTime     (nullable: true)
        recurringOnMonday    (nullable: true)
        recurringOnTuesday   (nullable: true)
        recurringOnWednesday (nullable: true)
        recurringOnThursday  (nullable: true)
        recurringOnFriday    (nullable: true)
        recurringOnSaturday  (nullable: true)
        recurringOnSunday    (nullable: true)
    }
}

public enum TimedRecurType {
    NONE('None'),
    DAILY('Daily'),
    WEEKLY('Weekly'),
    MONTHLY('Monthly'),
    YEARLY('Yearly')

    String name

    TimedRecurType(String name) {
        this.name = name
    }
}
