import model.Job
import service.JobStorage
import spock.lang.Specification

class JobStorageTest extends Specification {

    def "Check that you can't add the same job more than once"() {
        setup:
        JobStorage jobStorage = new JobStorage(2)
        Job job1 = new Job(1, 3)
        Job job2 = new Job(1, 3)
        jobStorage.addJob(job1)

        when:
        boolean canAdd = jobStorage.addJob(job2)

        then:
        canAdd == false
    }

    def "Check if you can add job when a slot is available"() {
        setup:
        JobStorage jobStorage = new JobStorage(2)
        Job job1 = new Job(1, 3)
        jobStorage.addJob(job1)

        when:
        Job job2 = new Job(2, 1)
        boolean canAdd = jobStorage.canAddJob(job2)

        then:
        canAdd == true
    }


    def "Check you cannot add job when a slot is unavailable"() {
        setup:
        JobStorage jobStorage = new JobStorage(2)
        Job job1 = new Job(1, 3)
        Job job2 = new Job(2, 1)
        jobStorage.addJob(job1)
        jobStorage.addJob(job2)


        when:
        Job job3 = new Job(3, 1)
        boolean canAdd = jobStorage.canAddJob(job3)

        then:
        canAdd == false
    }

    def "Check if you can get jobs in the the right priority slot"() {
        setup:
        JobStorage jobStorage = new JobStorage(3)

        Job job1 = new Job(100001, 3) // third
        Job job2 = new Job(100002, 1) // first
        Job job3 = new Job(100003, 2) // second

        jobStorage.addJob(job1)
        jobStorage.addJob(job2)
        jobStorage.addJob(job3)

        println(jobStorage)

        when:
        Job first = jobStorage.getNextJob()
        println(jobStorage)

        then:
        first == job2

        when:
        Job second = jobStorage.getNextJob()
        println(jobStorage)


        then:
        second == job3

        when:
        Job third = jobStorage.getNextJob()
        println(jobStorage)


        then:
        third == job1

    }
}
