# Apple Distribution

## Approach

1. **Input Initialization**:
   - **List of Apples**: Predefined list of apple weights.
   - **Contribution Percentages**: Desired weight proportions for each individual (Ram: 50%, Sham: 30%, Rahim: 20%).

2. **Calculate Targets**:
   - Compute the total weight of all apples.
   - Calculate the target weight for each individual based on their contribution percentages.

3. **Sort Apples**:
   - Convert the list of apple weights to an array.
   - Sort the apple weights in descending order to use a greedy approach for distribution.

4. **Greedy Distribution**:
   - Iterate through the sorted list of apple weights.
   - Allocate each apple to the individual whose current weight is closest to their target weight.

