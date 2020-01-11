package model;


public class Job implements Comparable {
   private int jobNumber;
   private int priority; // lower number means higher priority
   private long addedDate;

   public Job(int jobNumber, int priority) {
      this.jobNumber = jobNumber;
      this.priority = priority;
      this.addedDate = System.currentTimeMillis();
   }

   public int getJobNumber() {
      return jobNumber;
   }

   public void setJobNumber(int jobNumber) {
      this.jobNumber = jobNumber;
   }

   public int getPriority() {
      return priority;
   }

   public void setPriority(int priority) {
      this.priority = priority;
   }

   @Override
   public String toString() {
      return "Job{" +
         "jobNumber=" + jobNumber +
         ", priority=" + priority +
         '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Job job = (Job) o;

      return jobNumber == job.jobNumber;
   }

   @Override
   public int hashCode()  {
      return jobNumber;
   }

   @Override
   public int compareTo(Object o) {
      Job jobToCompare = (Job)o;
      if(this.priority < jobToCompare.priority)
         return 1;
      if(this.priority > jobToCompare.priority)
         return -1;
      else { // In here  this.priority == jobToCompare.priority
         if(this.addedDate < jobToCompare.addedDate)
            return 1;
         else if(this.addedDate > jobToCompare.addedDate)
            return -1;
         else
            return 0;
      }
   }
}

