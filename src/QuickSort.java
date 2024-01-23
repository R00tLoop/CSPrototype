import java.time.*;

abstract class QuickSort // Parent class that sorts by any field of integers or apparent integers
{
    // QuickSort algorithm takes three parameters, an array (here it is the allSongs array from a CustomSongList object)
    // It also takes two integers, low and high
    // Its aim is to pick a pivot point, here it is the last element in the array
    // It puts all elements with ratings lower than that of the pivot point and puts them in order before the pivot
    // Then it puts all elements with ratings higher than that of the pivot point and puts them ordered after
    // It does this with average O(nlog(n)) if done correclty which is much more efficient than bubble sort and typically more efficient than merge sort
    public int partition(String[][] csl, int low, int high)
    {
        // This sets the pivot integer as the rating of the last element in the array
        //The pivot is the element chosen to be placed at the correct position in the array
        //# Need to sort by one of several attributes but inefficient to write code for each attribute user could sort by
        //# Passing attribute in as parameter makes swapping and indexes difficult (given that its an array of objects) and would be an inelegant solution
        //# So abstract method made called sortByAttribute which takes an array of CustomSong objects and an integer index
        //# The method is declared inside a child class which has its own attribute to sort by
        //# It returns the value of the given attribute at the passed index within the passed array
        //# This allows the parent class to be applicable to any attribute whilst only having to be written once
        //# Due to recursive nature of quicksort algorithm, pivot holds many different values and thusly is inefficient to declare from a parameter
        LocalTime pivot = sortByAttribute(csl, high);
        // This sets the integer var i as being 1 lower than the low parameter, indicates the correct position of pivot found so far
        int i = low-1;
        // Declare a temp object of CustomSong so that swaps can be made with an entire object
        String[] temp = new String[2];

        // Iterate from the lowest element index to the panultimate
        //# This loop puts everything with a lower rating than the pivot lower in the array
        for(int j = low; j <= high-1; j++)
        {
            // If the current element goes before the pivot
            if(sortByAttribute(csl, j).isBefore(pivot))
            {
                i++; // Increment index of smaller element
                temp = csl[i]; // Triangle swap of i and j
                csl[i] = csl[j];
                csl[j] = temp;
            }
        }
        //# Place pivot element at correct position in array
        temp = csl[i+1]; // Triangle swap of i+1 and high
        csl[i+1] = csl[high];
        csl[high] = temp;

        //# At this point the array is (unsorted elements lower than pivot), (pivot), (unsorted elements higher than pivot)

        return (i+1); // Return index of pivot
    }

    public String[][] quickSort(String[][] csl, int low, int high)
    {
        // If the starting index is lower than the ending index (The algorithm is not complete yet)
        if(low < high)
        {
            // Get the index of the pivot, everything rated less than the pivot is beneath it and vise versa
            int partIndex = partition(csl, low, high);
            // Run this method with the ending element being the one before the pivot
            // This will recursively sort the elements before the pivot
            quickSort(csl, low, partIndex-1);
            // This will recursively sort the elements after the pivot
            quickSort(csl, partIndex+1, high);
        }
        // Return the sorted array
        return csl;
    }

    public abstract LocalTime sortByAttribute(String[][] csl, int index);
}
