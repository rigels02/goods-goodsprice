
package goodsprice.time;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Raitis
 */
public class TimeProcess {
   
    private final Timer timer;

    public TimeProcess( TimerTask task, long delay,long period) {
        if(period< 1000){
         throw new IllegalArgumentException("Period < 1000 ms !");
        }
        timer = new Timer("TimeProcess");
        timer.schedule(task,delay, period);
        
    }
    
    public void cancel(){
    timer.cancel();
    }
    
    
    
}
