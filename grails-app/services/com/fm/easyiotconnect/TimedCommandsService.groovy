package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.TimedCommand
import com.fm.easyiotconnect.mq.TimedRecurType
import grails.transaction.Transactional
import grails.util.Environment
import org.fm.pimq.PinMQ
import org.fm.pimq.PinStateMQ
import org.fm.pimq.impl.PinMessageImpl


@Transactional
class TimedCommandsService {

    def deviceService

    Boolean needToExecute(TimedCommand command, Date executionTime = new Date()) {
        if(command == null) {
            return false
        }

        if(command.executionTime == executionTime) {
            return true
        }
        else if(command.recurringType == TimedRecurType.NONE) {
            return false
        }
        else if(command.recurringEndTime && command.recurringEndTime < executionTime) {
            return false
        }
        else {
            boolean toExecute = false

            Calendar executionCal = executionTime.toCalendar()
            Calendar timedCal = command.executionTime.toCalendar()

            if(!isInRecurringWeekDays(command, executionCal)) {
                return toExecute
            }

            int executionMinute = executionCal.get(Calendar.MINUTE)
            int executionHour = executionCal.get(Calendar.HOUR)
            int executionDay = executionCal.get(Calendar.DAY_OF_MONTH)
            int executionWeekDay = executionCal.get(Calendar.DAY_OF_WEEK)
            int executionMonth = executionCal.get(Calendar.MONTH)

            switch (command.recurringType) {
                case TimedRecurType.MINUTES_5:
                    toExecute = (timedCal.get(Calendar.MINUTE) % 5 == 0)
                    break
                case TimedRecurType.MINUTES_15:
                    toExecute = isMinuteInRage(timedCal, executionCal, 0, 15, 30, 45)
                    break
                case TimedRecurType.MINUTES_30:
                    toExecute = isMinuteInRage(timedCal, executionCal, 0, 30)
                    break
                case TimedRecurType.MINUTES_45:
                    toExecute = isMinuteInRage(timedCal, executionCal, 0, 45)
                    break
                case TimedRecurType.HOURLY:
                    toExecute = (timedCal.get(Calendar.MINUTE) == executionMinute)
                    break
                case TimedRecurType.HOURS_2:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (isHourInRage(timedCal, executionCal, 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22)))
                    break
                case TimedRecurType.HOURS_4:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (isHourInRage(timedCal, executionCal, 0, 4, 8, 12, 16, 20)))
                    break
                case TimedRecurType.HOURS_6:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (isHourInRage(timedCal, executionCal, 0, 6, 12, 18)))
                    break
                case TimedRecurType.HOURS_8:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (isHourInRage(timedCal, executionCal, 0, 8, 16)))
                    break
                case TimedRecurType.HOURS_10:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (isHourInRage(timedCal, executionCal, 0, 10, 20)))
                    break
                case TimedRecurType.HOURS_12:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (isHourInRage(timedCal, executionCal, 0, 12)))
                    break
                case TimedRecurType.DAILY:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (timedCal.get(Calendar.HOUR) == executionHour))
                    break
                case TimedRecurType.WEEKLY:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (timedCal.get(Calendar.HOUR) == executionHour)
                                 && (timedCal.get(Calendar.DAY_OF_WEEK) == executionWeekDay))
                    break
                case TimedRecurType.MONTHLY:
                    if(timedCal.get(Calendar.DAY_OF_MONTH) > executionCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                        toExecute = ((timedCal.get(Calendar.MINUTE) == executionMinute)
                                && (timedCal.get(Calendar.HOUR) == executionHour)
                                && (executionCal.getActualMaximum(Calendar.DAY_OF_MONTH) == executionDay))
                    }
                    else {
                        toExecute = ((timedCal.get(Calendar.MINUTE) == executionMinute)
                                && (timedCal.get(Calendar.HOUR) == executionHour)
                                && (timedCal.get(Calendar.DAY_OF_MONTH) == executionDay))
                    }
                    break
                case TimedRecurType.YEARLY:
                    toExecute = (   (timedCal.get(Calendar.MINUTE) == executionMinute)
                                 && (timedCal.get(Calendar.HOUR) == executionHour)
                                 && (timedCal.get(Calendar.DAY_OF_MONTH) == executionDay)
                                 && (timedCal.get(Calendar.MONTH) == executionMonth))
                    break
                default:
                    toExecute = false
            }

            return toExecute
        }
    }


    private boolean isInRecurringWeekDays(TimedCommand command, Calendar execution) {
        int executionDayOfWeek = execution.get(Calendar.DAY_OF_WEEK)

        boolean okRecur = false

        switch (executionDayOfWeek) {

            case Calendar.SUNDAY:
                okRecur = command.recurringOnSunday
                break

            case Calendar.MONDAY:
                okRecur = command.recurringOnMonday
                break

            case Calendar.TUESDAY:
                okRecur = command.recurringOnThursday
                break

            case Calendar.WEDNESDAY:
                okRecur = command.recurringOnWednesday
                break

            case Calendar.THURSDAY:
                okRecur = command.recurringOnThursday
                break

            case Calendar.FRIDAY:
                okRecur = command.recurringOnFriday
                break

            case Calendar.SATURDAY:
                okRecur = command.recurringOnSaturday
                break
        }

        return okRecur
    }


    private boolean isMinuteInRage(Calendar command, Calendar execution, int[] ranges) {
        int executionMinute = execution.get(Calendar.MINUTE)
        List minutesRanges = []

        ranges.each { range ->
            Calendar cal = command.clone()
            cal.add(Calendar.MINUTE, range)

            minutesRanges << cal.get(Calendar.MINUTE)
        }

        return (executionMinute in minutesRanges)
    }


    private boolean isHourInRage(Calendar command, Calendar execution, int[] ranges) {
        int executionHour = execution.get(Calendar.HOUR)
        List hoursRanges = []

        ranges.each { range ->
            Calendar cal = command.clone()
            cal.add(Calendar.HOUR, range)

            hoursRanges << cal.get(Calendar.HOUR)
        }

        return (executionHour in hoursRanges)
    }



    Boolean execute(TimedCommand command) {
        if(command && command.deviceInfos && command.deviceInfos.device) {
            log.info "Executing command ${command.id} - date ${(new Date()).format("dd/MM/yy HH:mm:ss")}"

            if (Environment.current == Environment.DEVELOPMENT) {
                return true
            } else {
                PinMQ mqPin = new PinMQ(command.gpioId)
                PinStateMQ mqState = command.type == TimedCommand.TYPE_SEND_OFF ? PinStateMQ.LOW : PinStateMQ.HIGH

                return deviceService.sendCommand(command.deviceInfos.device, new PinMessageImpl(mqPin, mqState))
            }
        }

        return false
    }
}
