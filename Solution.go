
package main
import "slices"

type Label struct {
    ID    int
    value int
}

func NewLabel(ID int, value int) Label {
    return Label{ID: ID, value: value}
}

func largestValsFromLabels(values []int, labelIDs []int, maxLabelsToPick int, limitPerLabelID int) int {
    labels := createSortedLabelsByValue(values, labelIDs)
    frequencyLabelIDs := make([]int, slices.Max(labelIDs) + 1)
    return findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID)
}

func createSortedLabelsByValue(values []int, labelsID []int) []Label {
    labels := make([]Label, len(labelsID))
    for i := range labels {
        labels[i] = NewLabel(labelsID[i], values[i])
    }
    slices.SortFunc(labels, func(x Label, y Label) int { return y.value - x.value })
    return labels
}

func findMaxSumValues(labels []Label, frequencyLabelIDs []int, maxLabelsToPick int, limitPerLabelID int) int {
    maxSumValues := 0
    i := 0
    for i < len(labels) && maxLabelsToPick > 0 {
        if frequencyLabelIDs[labels[i].ID] < limitPerLabelID {
            maxSumValues += labels[i].value
            frequencyLabelIDs[labels[i].ID]++
            maxLabelsToPick--
        }
        i++
    }
    return maxSumValues
}
