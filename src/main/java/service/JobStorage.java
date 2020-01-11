package service;
import model.Job;

import java.util.*;

public class JobStorage {
   private static int maxStorageSize;
   private static SortedSet<Job> storage = Collections.synchronizedSortedSet(new TreeSet());

   public JobStorage(int maxStorageSize) {
      this.maxStorageSize = maxStorageSize;
   }

   private boolean canAddJob(Job job) {
      if(storage.size() <= (maxStorageSize - 1) && !storage.contains(job)) {
            return true;
      }
      else {
         return false; }
   }

   public boolean addJob(Job job) {
      if(canAddJob(job)) {
         storage.add(job);
         return true;
      } else
         return false;
   }


   public Job getNextJob() {
      if(storage.size() > 0) {
         Job job = storage.last();
         storage.remove(job);
         return job;
      } else
         return null;
   }

   @Override
   public String toString() {

      return "JobStorage{" + storage + "}";
   }
}
