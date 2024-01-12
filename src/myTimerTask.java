import java.util.*;

public class myTimerTask extends TimerTask
{
    PrototypeGUI pGUI;


    @Override
    public void run()
    {
        pGUI.btnSearch_Click();
    }

    public void getInstance(PrototypeGUI temp)
    {
        pGUI = temp;
    }
}