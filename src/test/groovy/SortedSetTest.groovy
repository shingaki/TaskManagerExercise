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
        JobStorage jobStorage = new JobStorage(2)
        Job job1 = new Job(1, 3)
        Job job2 = new Job(2, 1)
        jobStorage.addJob(job1)
        jobStorage.addJob(job2)
        println(jobStorage)

        when:
        Job first = jobStorage.getNextJob()
        println(jobStorage)

        then:
        first == job2

        when:
        Job second = jobStorage.getNextJob()

        then:
        second == job1
    }

}
