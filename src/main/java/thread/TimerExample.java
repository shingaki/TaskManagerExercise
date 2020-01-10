package thread;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: Crunchify.com
 */

public class TimerExample {

//   public TimerExample(int seconds) {
//      Timer timer1 = new Timer();
//      timer1.schedule(new TimerReminder(timer1), seconds * 1000);
//      Timer timer2 = new Timer();
//      timer2.schedule(new TimerReminder(timer2), seconds * 1000);
//   }

   public void createTimer(int seconds) {
      Timer timer = new Timer();
      timer.schedule(new TimerReminder(timer, this), seconds * 1000);
   }

   public void addJob(String jobName) {
      System.out.println("Add job " + jobName);
   }

   class TimerReminder extends TimerTask {
      private Timer timer;
      private TimerExample timerExample;

      public TimerReminder(Timer timer, TimerExample timerExample) {
         this.timer = timer;
         this.timerExample = timerExample;
      }
      public void run() {
         System.out.format("Timer Task Finished..!%n");
         timerExample.addJob("Tamami");
         timer.cancel(); // Terminate the timer thread

      }
   }

   public static void main(String args[]) {
      TimerExample timerExample = new TimerExample();
      timerExample.createTimer(5);
      timerExample.createTimer(10);
      System.out.format("Task scheduled.. Now wait for 5 sec to see next message..%n");

   }
}
