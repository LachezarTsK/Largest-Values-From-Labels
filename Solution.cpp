
#include <span>
#include <ranges>
#include <vector>
using namespace std;

class Solution {

    struct Label {
        int ID;
        int value;
        Label(int ID, int value) :ID{ ID }, value{ value } {};
    };

public:
    int largestValsFromLabels(vector<int>& values, vector<int>& labelIDs, int maxLabelsToPick, int limitPerLabelID) const {
        vector<Label> labels = createSortedLabelsByValue(values, labelIDs);
        vector<int> frequencyLabelIDs(*ranges::max_element(labelIDs) + 1);
        return findMaxSumValues(labels, frequencyLabelIDs, maxLabelsToPick, limitPerLabelID);
    }

private:
    vector<Label> createSortedLabelsByValue(span<const int>values, span<const int> labelsID) const {
        vector<Label> labels;
        labels.reserve(labelsID.size());
        for (int i = 0; i < labelsID.size(); ++i) {
            labels.emplace_back(labelsID[i], values[i]);
        }
        ranges::sort(labels, [](const Label& first, const Label& second) {return first.value > second.value; });
        return labels;
    }

    int findMaxSumValues(span<Label> labels, span<int> frequencyLabelIDs, int maxLabelsToPick, int limitPerLabelID) const {
        int maxSumValues = 0;
        for (int i = 0; i < labels.size() && maxLabelsToPick > 0; ++i) {
            if (frequencyLabelIDs[labels[i].ID] < limitPerLabelID) {
                maxSumValues += labels[i].value;
                ++frequencyLabelIDs[labels[i].ID];
                --maxLabelsToPick;
            }
        }
        return maxSumValues;
    }
};
