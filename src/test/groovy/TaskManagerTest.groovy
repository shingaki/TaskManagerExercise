import model.Job
import model.RecurringJob
import service.TaskManager
import spock.lang.Specification

class TaskManagerTest extends Specification {

    def "verify that a task can be inserted and retrieved"() {
        setup:
        int maxNumberOfJobs = 2
        TaskManager taskManager = new TaskManager(maxNumberOfJobs)
        when:
        taskManager.insertTask(10001, 1)
        Job retrievedJob = taskManager.getNextJob()
        then:
        retrievedJob != null
        println(retrievedJob)
    }

    def "verify that a recurring task can be inserted and retrieved"() {
        setup:
        int maxNumberOfJobs = 2
        TaskManager taskManager = new TaskManager(maxNumberOfJobs)
        when:
        taskManager.insertRecurringTask(10002, 2, 10000)
        Job retrievedJob = taskManager.getNextJob()
        then:
        retrievedJob != null
        println(retrievedJob)
    }

    def "verify timer (interval) is working as expected"() {
        setup:
        long interval = 5000
        long start = System.currentTimeMillis()
        RecurringJob recurringJob = new RecurringJob(10004, 2, interval)
        TaskManager taskManager = new TaskManager(2)
        when:
        taskManager.createTimer(recurringJob)
        sleep(interval + 1000) // Wait one more second than interval
        then:
        Job testJob = taskManager.getNextJob()
        long stop = testJob.getAddedDate()
        interval <= stop - start
    }
}