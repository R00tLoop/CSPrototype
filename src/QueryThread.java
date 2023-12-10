import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class QueryThread extends PrototypeGUI implements Runnable
{
    @Override
    public void run()
    {
        int prevMinute = LocalTime.now().getMinute();
        while(true)
        {
            try
            {
                Thread.sleep(1000);
                LocalTime time = LocalTime.now();
                if(time.getMinute() != prevMinute)
                {
                    prevMinute = time.getMinute();
                    System.out.println("Trying to update");
                    //updateTimes();
                    btnPlanJourney_Click();
                }
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
