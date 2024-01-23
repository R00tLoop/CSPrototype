public class SLSort
{
    public int partition(Stop[] csl, int low, int high)
    {
        Stop pivot = csl[high];
        int i = low-1;
        Stop temp = new Stop();
        for(int j = low; j <= high-1; j++)
        {
            if((csl[j].stName).compareTo(pivot.stName) <0)
            {
                i++; // Increment index of smaller element
                temp = csl[i]; // Triangle swap of i and j
                csl[i] = csl[j];
                csl[j] = temp;
            }
        }
        temp = csl[i+1]; // Triangle swap of i+1 and high
        csl[i+1] = csl[high];
        csl[high] = temp;
        return (i+1); // Return index of pivot
    }

    public Stop[] quickSort(Stop[] csl, int low, int high)
    {
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
}
