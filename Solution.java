
import java.util.Arrays;

public class Solution {

    private record Label(int ID, int value) {}

    public int largestValsFromLabels(int[] values, int[] labelIDs, int maxLabelsToPick, int limitPerLabelID) {
        Label[] labels = createSortedLabelsByValue(values, labelIDs);
        int[] frequencyLabelIDs = createFrequencyLabelsIDs(labelIDs);
        return findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID);
    }

    private Label[] createSortedLabelsByValue(int[] values, int[] labelsID) {
        Label[] labels = new Label[labelsID.length];
        for (int i = 0; i < labels.length; ++i) {
            labels[i] = new Label(labelsID[i], values[i]);
        }
        Arrays.sort(labels, (x, y) -> y.value - x.value);
        return labels;
    }

    private int[] createFrequencyLabelsIDs(int[] labelIDs) {
        /*
         Alternatively: int maxLabelID = Arrays.stream(labelIDs).max().getAsInt();
         This alternative, for the given input constraints, has runntime 
         that is much slower than finding the maxLabelID with a loop. 
         */
        int maxLabelID = 0;
        for (int ID : labelIDs) {
            maxLabelID = Math.max(maxLabelID, ID);
        }
        return new int[maxLabelID + 1];
    }

    private int findMaxSumValues(Label[] labels, int[] frequencyLabelIDs, int maxLabelsToPick, int limitPerLabelID) {
        int maxSumValues = 0;
        for (int i = 0; i < labels.length && maxLabelsToPick > 0; ++i) {
            if (frequencyLabelIDs[labels[i].ID] < limitPerLabelID) {
                maxSumValues += labels[i].value;
                ++frequencyLabelIDs[labels[i].ID];
                --maxLabelsToPick;
            }
        }
        return maxSumValues;
    }
}
