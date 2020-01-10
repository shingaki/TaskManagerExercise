package service;
import model.Job;

import java.util.*;

public class JobStorage {
   private static int maxStorageSize;
   //    private static SortedSet<Job> storage = new TreeSet<>();
   private static SortedSet<Job> storage = Collections.synchronizedSortedSet(new TreeSet());

   public JobStorage(int maxStorageSize) {
      this.maxStorageSize = maxStorageSize;
   }

   private boolean canAddJob(Job job) {
      if(storage.size() <= maxStorageSize -1 && !storage.contains(job))
         return true;
      else
         return false;
   }

   public boolean addJob(Job job) {
      if(canAddJob(job)) {
         storage.add(job);
         return true;
      } else
         return false;
   }


   // TODO: Add functionality to reAdd job if it is a Recurring Task
   public Job getNextJob() {
      if(storage.size() > 0) {
         Job job = storage.last();
         storage.remove(job);

         // Is this a Recurring job?
         // If it is start thread to count time
         // and then "addJob" to queue

         return job;
      } else
         return null;
   }

   @Override
   public String toString() {

      return "JobStorage{" + storage + "}";
   }
}
