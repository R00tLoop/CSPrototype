import java.util.*;
import java.io.*;
import java.time.*;

public class StopSort extends QuickSort
{
    public LocalTime sortByAttribute(String[][] csl, int index)
    {
        // Gets passed parameter of an array and an index
        // Each child class has a different attribute that its sortByAttribute returns the value of
        // This one returns the rating of the object at the index in the csl array
        return LocalTime.parse(csl[index][1]);
        //Convert arrivalTime to localTime
        //alter QuickSort algorithm to work with localTime
        //return localTime as value
    }
}