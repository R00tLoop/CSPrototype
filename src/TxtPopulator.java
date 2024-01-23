import java.io.*;
import java.util.*;
public class TxtPopulator
{
    Random rand = new Random();
    Service s = new Service();

    String[] array = {"Stop A", "Stop B", "Stop C", "Stop D", "Stop E", "Stop F", "Stop G"};

    public static void main(String[] args)
    {
        TxtPopulator t = new TxtPopulator();
        for(int i = 0; i < 15; i++)
        {
            t.createNewSav(i);
            Service s = new Service();
        }
    }

    public void createNewSav(int iV)
    {
        s.sName = ("T" + (iV+14));
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

        /*
        s.stopTimes[0][0] = array[rand.nextInt(6)];
        s.stopTimes[0][1] = "11:00";
        s.stopTimes[1][0] = array[rand.nextInt(6)];
        s.stopTimes[1][1] = "12:00";
        s.stopTimes[2][0] = array[rand.nextInt(6)];
        s.stopTimes[2][1] = "13:00";



        s.newSaveFile(s.sName);
        s.save();

         */
    }
}
