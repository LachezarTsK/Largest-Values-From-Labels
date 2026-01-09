
/**
 * @param {number[]} values
 * @param {number[]} labelIDs
 * @param {number} maxLabelsToPick
 * @param {number} limitPerLabelID
 * @return {number}
 */
var largestValsFromLabels = function (values, labelIDs, maxLabelsToPick, limitPerLabelID) {
    const labels = createSortedLabelsByValue(values, labelIDs);
    const frequencyLabelIDs = new Array(Math.max(...labelIDs) + 1).fill(0);
    return findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID);
};

class Label {

    constructor(ID, value) {
        this.ID = ID;
        this.value = value;
    }
}

/**
 * @param {number[]} values
 * @param {number[]} labelsID
 * @return {Label[]}
 */
function createSortedLabelsByValue(values, labelsID) {
    const labels = new Array(labelsID.length);
    for (let i = 0; i < labels.length; ++i) {
        labels[i] = new Label(labelsID[i], values[i]);
    }
    labels.sort((x, y) => y.value - x.value);
    return labels;
}

/**
 * @param {Label[]} labels
 * @param {number[]} frequencyLabelIDs
 * @param {number} maxLabelsToPick
 * @param {number} limitPerLabelID
 * @return {number}
 */
function findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID) {
    let maxSumValues = 0;
    for (let i = 0; i < labels.length && maxLabelsToPick > 0; ++i) {
        if (frequencyLabelIDs[labels[i].ID] < limitPerLabelID) {
            maxSumValues += labels[i].value;
            ++frequencyLabelIDs[labels[i].ID];
            --maxLabelsToPick;
        }
    }
    return maxSumValues;
}
