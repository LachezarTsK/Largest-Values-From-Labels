
class Solution {

    private data class Label(val ID: Int, val value: Int) {}

    fun largestValsFromLabels(values: IntArray, labelIDs: IntArray, maxLabelsToPick: Int, limitPerLabelID: Int): Int {
        val labels = createSortedLabelsByValue(values, labelIDs)
        val frequencyLabelIDs = IntArray(labelIDs.max() + 1)
        return findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID)
    }

    private fun createSortedLabelsByValue(values: IntArray, labelsID: IntArray): Array<Label?> {
        val labels = arrayOfNulls<Label>(labelsID.size)
        for (i in labels.indices) {
            labels[i] = Label(labelsID[i], values[i])
        }
        labels.sortWith() { x, y -> y!!.value - x!!.value }
        return labels
    }

    private fun findMaxSumValues(labels: Array<Label?>, frequencyLabelIDs: IntArray, maxLabelsToPick: Int, limitPerLabelID: Int): Int {
        var maxLabelsToPick = maxLabelsToPick
        var maxSumValues = 0
        var i = 0
        while (i < labels.size && maxLabelsToPick > 0) {
            if (frequencyLabelIDs[labels[i]!!.ID] < limitPerLabelID) {
                maxSumValues += labels[i]!!.value
                ++frequencyLabelIDs[labels[i]!!.ID]
                --maxLabelsToPick
            }
            ++i
        }
        return maxSumValues
    }
}
