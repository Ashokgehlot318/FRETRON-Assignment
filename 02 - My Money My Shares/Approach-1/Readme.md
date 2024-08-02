# Apple Distribution

## Approach

1. **Input Initialization**:
   - **List of Apples**: Predefined list of apple weights.
   - **Desired Proportions**: Target proportions (in percentage) for each individual: Ram, Sham, and Rahim.

2. **Calculate Total Weight**:
   - Compute the total weight of all apples to determine the proportion of apples for each individual based on their target percentages.

3. **Generate All Possible Distributions**:
   - **Recursive Generation**: Uses a recursive function to generate all permutations of apple distributions.
   - **Distribution Lists**: Each permutation is a potential distribution where apples are allocated in a sequence.

4. **Evaluate Distributions**:
   - For each distribution, allocate apples to Ram, Sham, and Rahim in a round-robin manner.
   - Calculate the actual proportion of apples for each individual.
   - Compute the difference between the actual proportions and the desired proportions.
   - Track the distribution with the minimum difference.

