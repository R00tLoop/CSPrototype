import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TempFileSearch
{
    File stopSaveFile = new File("Saves\\temp\\Stops");
    File stopListSaveFile = new File("Saves\\temp\\StopLists");

    public boolean searchForStop(String s)
    {
        boolean isThere = false;
        for(File f : Objects.requireNonNull(stopSaveFile.listFiles()))
        {
            if (f.getName().equals(s+".txt")) {
                isThere = true;
                break;
            }
        }
        return isThere;
    }

    public void saveAString(String s, File f)
    {
        try(FileWriter fW = new FileWriter(f))
        {
            fW.write(s);
        }
        catch(Exception e)
        {
            System.out.println("String written to file");
        }
    }

    public boolean searchForStopList(String s)
    {
        boolean isThere = false;
        for(File f : Objects.requireNonNull(stopListSaveFile.listFiles()))
        {
            if (f.getName().equals(s+".txt"))
            {
                isThere = true;
                break;
            }
        }
        return isThere;
    }

    public Stop load(String s, Stop sorSt)
    {
        for(File f : Objects.requireNonNull(stopSaveFile.listFiles()))
        {
            if(f.getName().equals(s+".txt"))
            {
                sorSt.load(f);
                break;
            }
        }
        return sorSt;
    }

    public void makeTempFile(String s)
    {
        File f = new File("Saves\\temp\\Stops" + s + ".txt");
        boolean worked = false;
        try
        {
            worked = f.createNewFile();
        }
        catch(Exception e)
        {
            System.out.println("Failed to create file");
        }
        if(worked)
        {
            System.out.println("File created successfully");
        }
        else
        {
            System.out.println("File could not be created");
        }
    }

    public void sortAll(StopList paramStopList)
    {
        StopSort sortAlgorithm = new StopSort();
        Stop tempStop;
        StopList tempStopList = new StopList();
        Stop tempSaveStop = new Stop();
        StopList sL1 = new StopList();
        StopList sL2 = new StopList();
        StopList sL3 = new StopList();
        for(Stop s : paramStopList.allStops)
        {
            tempStop = s;
            System.out.println("------------RUNNING WITH STOP " + tempStop.stName);
            String[][] allServicesArray = new String[tempStop.sortServiceTimes.size()][2];
            for(int i = 0; i < tempStop.sortServiceTimes.size(); i++)
            {
                allServicesArray[i][0] = tempStop.sortServiceTimes.get(i)[0];
                System.out.println(tempStop.sortServiceTimes.get(i)[0] + "," + tempStop.sortServiceTimes.get(i)[1]);
                allServicesArray[i][1] = tempStop.sortServiceTimes.get(i)[1];
            }
            ArrayList<String[]> sortedStops = new ArrayList<>(Arrays.asList(sortAlgorithm.quickSort(allServicesArray, 0, tempStop.sortServiceTimes.size()-1)));

            System.out.println("--SORTING--");
            for(String[] abc : sortedStops)
            {
                System.out.println(abc[0] + "," + abc[1]);
            }
            System.out.println("--SORTED--");

            tempSaveStop.stName = tempStop.stName;
            for(String[] string : sortedStops)
            {
                tempSaveStop.serviceTimes.add(string[0] + "," + string[1]);
            }
            if(tempStopList.getRegionFileName(tempStop.stName).equals("StopList1"))
            {
                System.out.println("---ADDING " + tempSaveStop.stName + " TO StopList1");
                sL1.allStops.add(tempSaveStop);
                sL1.outputAllStops();
                tempSaveStop = new Stop();
            }
            else if(tempStopList.getRegionFileName(tempStop.stName).equals("StopList2"))
            {
                System.out.println("---ADDING " + tempSaveStop.stName + " TO StopList2");
                sL2.allStops.add(tempSaveStop);
                sL2.outputAllStops();
                tempSaveStop = new Stop();
            }
            else
            {
                System.out.println("---ADDING " + tempSaveStop.stName + " TO StopList3");
                sL3.allStops.add(tempSaveStop);
                sL3.outputAllStops();
                tempSaveStop = new Stop();
            }
        }
        System.out.println("--------------------------------------SAVING ALL STOPS FOR STOP LIST 1-----------------------------------------------");
        sL1.saveAllStops(new File("Saves\\Stops\\StopList1.txt"));
        System.out.println("--------------------------------------SAVING ALL STOPS FOR STOP LIST 2-----------------------------------------------");
        sL2.saveAllStops(new File("Saves\\Stops\\StopList2.txt"));
        System.out.println("--------------------------------------SAVING ALL STOPS FOR STOP LIST 3-----------------------------------------------");
        sL3.saveAllStops(new File("Saves\\Stops\\StopList3.txt"));
    }
}