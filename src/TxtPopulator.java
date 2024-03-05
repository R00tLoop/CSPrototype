import java.io.*;
import java.util.*;
import java.time.*;
public class TxtPopulator
{
    Random rand = new Random();
    Service s = new Service();

    String[] array = {"Stop A", "Stop B", "Stop C", "Stop D", "Stop E", "Stop F", "Stop G", "Stop H", "Stop I"};

    public static void main(String[] args)
    {
        TxtPopulator t = new TxtPopulator();
        for(int i = 0; i < 10; i++)
        {
            t.createNewSav(i);
            Service s = new Service();
        }
    }

    public void createNewSav(int iV)
    {
        s.sName = ("T" + (iV+1));
        iV = iV + rand.nextInt(8);
        if(iV < 10)
        {
            s.dID = ("D0000" + iV);
            s.vID = ("V0000" + iV);
        }
        else
        {
            s.dID = ("D000" + iV);
            s.vID = ("V000" + iV);
        }

        LocalTime prevTime = LocalTime.of(16,0,0);
        String[] strAry = new String[2];

        for(int i = 0; i < 8; i++)
        {
            strAry[0] = array[rand.nextInt(8)];
            strAry[1] = prevTime.toString();
            System.out.println(strAry[0]);
            System.out.println("Time is " + prevTime.toString());
            prevTime = prevTime.plusMinutes(rand.nextInt(60));
            s.stopTimes.add(strAry);
            strAry = new String[2];
        }
        strAry = new String[2];
        s.newSaveFile(s.sName);
        s.save();
        s = new Service();
    }
}
