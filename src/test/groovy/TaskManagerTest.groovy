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
        int intervalValidated = 0
        RecurringJob recurringJob = new RecurringJob(10004, 2, 20000)
        Timer timer = new Timer()
        TaskManager taskManager = new TaskManager(2)
        timer.schedule(new TaskManager.JobTimer(timer, taskManager, 10000))

        when:
        taskManager.insertRecurringTask(recurringJob.getJobNumber(), recurringJob.getPriority(), recurringJob.getInterval())

        then:
        interValidated = 20000;

    }
}
