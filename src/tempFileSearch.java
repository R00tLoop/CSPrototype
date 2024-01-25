import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class tempFileSearch
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

    public sortStop load(String s, sortStop sorSt)
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
        sortStop tempSortStop;
        StopList tempStopList = new StopList();
        sortStop tempSaveStop = new sortStop();
        StopList sL1 = new StopList();
        StopList sL2 = new StopList();
        StopList sL3 = new StopList();
        for(Stop s : paramStopList.allStops)
        {
            tempSortStop = (sortStop) s;
            String[][] allServicesArray = new String[tempSortStop.sortServiceTimes.size()][2];
            for(int i = 0; i < tempSortStop.sortServiceTimes.size(); i++)
            {
                allServicesArray[i][0] = tempSortStop.sortServiceTimes.get(i)[0];
                allServicesArray[i][1] = tempSortStop.sortServiceTimes.get(i)[1];
            }
            ArrayList<String[]> sortedStops = new ArrayList<>(Arrays.asList(sortAlgorithm.quickSort(allServicesArray, 0, tempSortStop.sortServiceTimes.size()-1)));
            tempSaveStop.stName = tempSortStop.stName;
            for(String[] string : sortedStops)
            {
                tempSaveStop.serviceTimes.add(string[0] + "," + string[1]);
            }
            if(tempStopList.getRegionFileName(tempSortStop.stName).equals("StopList1"))
            {
                sL1.allStops.add(tempSaveStop);
            }
            else if(tempStopList.getRegionFileName(tempSortStop.stName).equals("StopList2"))
            {
                sL2.allStops.add(tempSaveStop);
            }
            else
            {
                sL3.allStops.add(tempSaveStop);
            }
        }
        sL1.saveAllStops(new File("Saves\\Stops\\StopList1.txt"));
        sL2.saveAllStops(new File("Saves\\Stops\\StopList2.txt"));
        sL3.saveAllStops(new File("Saves\\Stops\\StopList3.txt"));
    }
}