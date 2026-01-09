
using System;

public class Solution
{
    private record Label(int ID, int value) { }

    public int LargestValsFromLabels(int[] values, int[] labelIDs, int maxLabelsToPick, int limitPerLabelID)
    {
        Label[] labels = CreateSortedLabelsByValue(values, labelIDs);
        int[] frequencyLabelIDs = CreateFrequencyLabelsIDs(labelIDs);
        return FindMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID);
    }

    private Label[] CreateSortedLabelsByValue(int[] values, int[] labelsID)
    {
        Label[] labels = new Label[labelsID.Length];
        for (int i = 0; i < labels.Length; ++i)
        {
            labels[i] = new Label(labelsID[i], values[i]);
        }
        Array.Sort(labels, (x, y) => y.value - x.value);
        return labels;
    }

    private int[] CreateFrequencyLabelsIDs(int[] labelIDs)
    {
        /*
         Alternatively: int maxLabelID = labelIDs.Max();
         This alternative, for the given input constraints, has runntime 
         that is much slower than finding the maxLabelID with a loop. 
         */
        int maxLabelID = 0;
        foreach (int ID in labelIDs)
        {
            maxLabelID = Math.Max(maxLabelID, ID);
        }
        return new int[maxLabelID + 1];
    }

    private int FindMaxSumValues(Label[] labels, int[] frequencyLabelIDs, int maxLabelsToPick, int limitPerLabelID)
    {
        int maxSumValues = 0;
        for (int i = 0; i < labels.Length && maxLabelsToPick > 0; ++i)
        {
            if (frequencyLabelIDs[labels[i].ID] < limitPerLabelID)
            {
                maxSumValues += labels[i].value;
                ++frequencyLabelIDs[labels[i].ID];
                --maxLabelsToPick;
            }
        }
        return maxSumValues;
    }
}
