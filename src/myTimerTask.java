import java.util.*;

public class myTimerTask extends TimerTask
{
    PrototypeGUI pGUI;

    @Override
    public void run()
    {
        System.out.println("MY TIMER TASK RUN-------------------------------------------");
        pGUI.myPlanJourneyClick();
    }

    public void getInstance(PrototypeGUI temp)
    {
        pGUI = temp;
    }
}