
function largestValsFromLabels(values: number[], labelIDs: number[], maxLabelsToPick: number, limitPerLabelID: number): number {
    const labels = createSortedLabelsByValue(values, labelIDs);
    const frequencyLabelIDs = new Array(Math.max(...labelIDs) + 1).fill(0);
    return findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID);
};

class Label {

    ID: number;
    value: number;

    constructor(ID, value) {
        this.ID = ID;
        this.value = value;
    }
}

function createSortedLabelsByValue(values: number[], labelsID: number[]): Label[] {
    const labels = new Array(labelsID.length);
    for (let i = 0; i < labels.length; ++i) {
        labels[i] = new Label(labelsID[i], values[i]);
    }
    labels.sort((x, y) => y.value - x.value);
    return labels;
}

function findMaxSumValues(labels: Label[], frequencyLabelIDs: number[], maxLabelsToPick: number, limitPerLabelID: number): number {
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
