import java.time.LocalTime;
import java.util.*;

public class MyTimerTask extends TimerTask
{
    PrototypeGUI pGUI;

    @Override
    public void run()
    {
        System.out.println("MY TIMER TASK RUN-------------------------------------------");


        String ab = (String) pGUI.jpCombo.getSelectedItem();

        assert ab != null;
        if(ab.equals("Depart at") && !LocalTime.parse((CharSequence) pGUI.timeSpinner.getValue()).equals(LocalTime.now()))
        {
            LocalTime lT = LocalTime.parse((CharSequence) pGUI.timeSpinner.getValue());
            long seconds = lT.getSecond();
            seconds = Math.round(seconds);
            int secondsInt = (int) seconds;
            secondsInt = 60 - secondsInt;
            long secondsLong = secondsInt*1000L;
            pGUI.updateTimer(secondsLong);
        }
        else if(ab.equals("Arrive by"))
        {

        }

        pGUI.btnPlanJourney_Click();

    }

    public void getInstance(PrototypeGUI temp)
    {
        pGUI = temp;
    }
}