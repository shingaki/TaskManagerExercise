import model.Job
import service.JobStorage
import service.TaskManager
import spock.lang.Specification

class SortedSetTest extends Specification {

    def "ordering jobs"() {
        expect:
        true
    }


    def "check ordering of Job works"() {
        setup:
        JobStorage jobStorage = new JobStorage(3)
        Job job1 = new Job(1, 3)
        Job job2 = new Job(2, 1)
        Job job3 = new Job(3, 2)

        jobStorage.addJob(job1)
        jobStorage.addJob(job2)
        jobStorage.addJob(job3)

        println(jobStorage)

        when:
        Job first = jobStorage.getNextJob()
        println(jobStorage)

        then:
        first == job2
        println(first)

        when:
        Job second = jobStorage.getNextJob()
        println(jobStorage)

        then:
        second == job3
        println(second)


        when:
        Job third = jobStorage.getNextJob()
        println(jobStorage)

        then:
        third == job1
        println(third)

    }

    def "check inserting non-Recurring Jobs and Recurring Jobs correctly"() {
        setup:
        JobStorage jobStorage = new JobStorage(4)
        TaskManager taskManager = new TaskManager(4)
        taskManager.insertTask(10011, 3) // 3
        sleep(2000)
        taskManager.insertTask(10012, 1) // 1
        sleep(2000)
        taskManager.insertRecurringTask(10014, 3, 2000) // 4
        sleep(2000)
        taskManager.insertRecurringTask(10013, 2, 1000) // 2


        println(jobStorage)

        when:
        int first = taskManager.getNextJobNumber()

        then:
        first == 10012

        when:
        int second = taskManager.getNextJobNumber()

        then:
        second == 10013

        when:
        int third = taskManager.getNextJobNumber()

        then:
        third == 10011

        when:
        int four = taskManager.getNextJobNumber()

        then:
        four == 10014

    }

    }
