package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.TimedCommand

/**
 * Created by fabiomarini on 23/05/15.
 */
class TimedCommandsJob {

    def timedCommandsService


    static triggers = {
        cron cronExpression: "0 0/5 * * * ?"
    }


    def execute() {
        Date executionTime = new Date()
        log.info "Executing timed commands for ${executionTime.format("hh:mm:ss")}"

        List<TimedCommand> commands = TimedCommand.findAllByExecutionTimeLessThanEquals(executionTime)
        log.info "Found ${commands?.size()?:0} for the current execution"

        for (TimedCommand timedCommand : commands) {
            try {
                if (timedCommandsService.needToExecute(timedCommand, executionTime)) {
                    log.info "Executing timed command ${timedCommand.id}"

                    timedCommandsService.execute(timedCommand)
                }
            }
            catch (Throwable thr) {
                log.error("Exception processing timed command ${timedCommand.id}", thr)
            }
        }

        log.info("TimedCommands job finished")
    }
}
