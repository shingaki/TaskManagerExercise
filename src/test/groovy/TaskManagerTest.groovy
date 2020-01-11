import model.Job
import model.RecurringJob
import service.JobStorage
import service.TaskManager
import spock.lang.Specification

class TaskManagerTest extends Specification {
    def "verify that a task can be inserted and retrieved"() {
        setup:
        int maxNumberOfJobs = 2
        TaskManager taskManager = new TaskManager(maxNumberOfJobs)
        JobStorage jobStorage = new JobStorage(maxNumberOfJobs)

        when:
        taskManager.insertTask(10001, 1)

        then:
        jobStorage.addJob(new Job(10001, 1))
        println(jobStorage.getNextJob())
    }

    def "verify that a recurring task can be inserted and retrieved"() {
        setup:
        int maxNumberOfJobs = 2
        TaskManager taskManager = new TaskManager(maxNumberOfJobs)
        JobStorage jobStorage = new JobStorage(maxNumberOfJobs)

        when:
        taskManager.insertRecurringTask(10002, 2, 10000)

        then:
        jobStorage.addJob(new Job(10002, 2))
        println(jobStorage.getNextJob())    }


    def "verify timer (internval) is working as expected"() {
        setup:
        long interval = 5000
        int start = System.currentTimeMillis()
        RecurringJob recurringJob = new RecurringJob(10004, 2, interval)
        TaskManager taskManager = new TaskManager(2)

        when:
        taskManager.createTimer(recurringJob)
        int stop = System.currentTimeMillis()


        then:
        interval == stop - start

    }
}
