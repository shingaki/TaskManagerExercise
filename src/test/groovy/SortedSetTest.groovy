import model.Job
import service.JobStorage
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

}
