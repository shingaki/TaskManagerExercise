package service;

import model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;


/**
* The Job Storage  creates SortedSet for the Job Objects
 * SortedSet is restricted as to how many elements it can accept based on the max Storage Size
 */
public class JobStorage {

   static final Logger log = LoggerFactory.getLogger(JobStorage.class);

   private int maxStorageSize;

   private SortedSet<Job> storage = Collections.synchronizedSortedSet(new TreeSet());

   public JobStorage(int initialStorageSize) {

      maxStorageSize = initialStorageSize;
//      purgeStorage();
      log.info("Created job storage with max capacity " + maxStorageSize);
   }

   /**
    * This checks if there is room to add another job
    * Checks if the job already exists, so it can handle it appropriately by returning false
    * @param job
    * @return
    */
   private boolean canAddJob(Job job) {
      if(storage.size() <= (maxStorageSize - 1) && !storage.contains(job)) {
         log.info("Job Is Being Added");
         return true;
      }
      else {
         log.warn("Job could not be added");
         return false;
      }
   }

   /**
    * Adds the job to the Job Storage and returns true
    * If cannot add job since max number of jobs is reached, will return false
    */
   public boolean addJob(Job job) {
      if(canAddJob(job)) {
         storage.add(job);
         return true;
      } else
         // returns false - cannot add any more jobs
         return false;
   }

   /**
    * Checks if there are jobs, if there are jobs, gets the next available job which
    * is the highest priority of the jobs currently in Job Storage
    * @return
    */
   public Job getNextJob() {
      if(storage.size() > 0) {
         Job job = storage.last();
         storage.remove(job);
         return job;
      } else
         return null;
   }

//   public void purgeStorage() {
//      log.info("Clearing the storage");
//      storage.clear();
//   }

   @Override
   public String toString() {
      return "JobStorage{" + storage + "}";
   }
}
