import java.util.*;

public class MyTimerTask extends TimerTask
{
    PrototypeGUI pGUI;

    @Override
    public void run()
    {
        System.out.println("MY TIMER TASK RUN-------------------------------------------");
        pGUI.btnPlanJourney_Click();
    }

    public void getInstance(PrototypeGUI temp)
    {
        pGUI = temp;
    }
}