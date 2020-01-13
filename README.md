## Task Manager Exercise

### The Application

The Task Manager is a class that organizes job requests by priority.
It decides which job should be handled next.
It arranges all the jobs by their priority – High, Normal, and Low (1, 2, 3).

All jobs with high priority should be handled (retrieved by consumers) before the normal priority jobs.
All jobs with normal priority should be handled (retrieved by consumers) before the low priority jobs.
The order between the jobs with the same priority should be according to the job submission time.

A job can be a one time task or a recurring task.
A recurring task should first be handled as soon as possible (as a onetime task according its priority) 
and then (after it has been handled) after XX time, 
should be treated as if it was submitted now (according to its priority) and so on...

The job requests are submitted by multiple clients (not part of this exercise) who call the InsertJob or InsertRecurringTask function.

The job requests are retrieved by multiple clients (not part of this exercise) who call the GetNextJob function.

The Task Manager limits the number of tasks it can manage at any given time.

The Task Manager should supply the following capabilities:
- Boolean InsertTask(int JobNumber, int Priority) – returns false if there is no more space left for tasks
- Boolean InsertReoccurrenceTask(int JobNumber, int Priority, int Interval) – returns false if there is no more space left for tasks
- The interval is measured in milliseconds (1sec = 1000 ms) 
- int GetNextJob(); returns -1 if no jobs are available
 


###My Approach

The following are the major modules for this application:
1. JobStorage stores the maximum number of jobs allowed.
I used the SortedSet interface for sorting the jobs by their priorities. 
https://www.geeksforgeeks.org/sortedset-java-examples/
 

I needed to define the criteria to use while sorting. 
2. It was important to create a test to ensure the sort was working properly as this is key for the application.
3. Some jobs provide an interval. I used the interval value to indicate how long to hold the job before inserting the job back into JobStorage. For this I created a timer, once the timer 
was stopped based on the interval, the application will try to insert it back into the storage. If there is not any room in the storage then the internval
job will not be inserted. 



